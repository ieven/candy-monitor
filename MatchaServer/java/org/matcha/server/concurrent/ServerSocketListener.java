package org.matcha.server.concurrent;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;

import org.matcha.server.net.DefaultServerSocketFactory;
import org.matcha.server.net.SocketWrapper;
import org.matcha.server.po.CreateSocketType;
import org.matcha.server.po.MongoBean;
import org.matcha.server.po.SocketPropertityBean;
import org.matcha.server.proxy.ConfigProxy;
import org.matcha.server.scheduler.job.impl.DataTransferJobImmediate;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuxindata.iomp.util.StringUtils;

public class ServerSocketListener{

	Logger logger = LoggerFactory.getLogger(getClass());
	
	protected volatile boolean running = true; 
	
	public void start()
	{
		//申请处理线程池
		setExecutor(ExecutorFactory.getDefaultExecutor());
		ServerSocketThread serverSocketThread = new ServerSocketThread();
		Thread thread = new Thread(serverSocketThread);
		thread.start();
	}
	
	/**
	 * 关闭当前socket
	 * @param socket
	 */
	private void closeSocket(Socket socket) {
		// TODO Auto-generated method stub
		try {
            socket.close();
        } catch (IOException e) {
            // 直接忽略
        }
	}

	/**
	 * 线程执行者
	 */
	private Executor executor = null;
    public void setExecutor(Executor executor) {
        this.executor = executor;
    }
    public Executor getExecutor() { return executor; }
	
	/**
	 * 切断连接
	 */
	protected void countDownConnection() {
		// TODO Auto-generated method stub
		//未完待续
	}

	/**
	 * 若达到了最大连接数则等待
	 */
	protected void countUpOrAwaitConnection() {
		// TODO Auto-generated method stub
		//未完待续
	}
	
	private static final String SCHEDULE_SWITCH = "matcha.schedule.switch";
	
	/**
	 * 处理此次请求
	 * @param socket
	 * @return
	 */
	protected boolean processSocket(Socket socket) {
		//根据system配置文件决定接收的socket是否需要即时处理
		String offOrOn = (String) ConfigProxy.getSystemConfigMap().get(SCHEDULE_SWITCH);
		SocketWrapper<Socket> wrapper;
		if(offOrOn.equalsIgnoreCase("on"))
		{
			wrapper = new SocketWrapper<Socket>(socket);
		}
		else
		{
			wrapper = new SocketWrapper<Socket>(socket,true);
		}
		//如果停止了则直接返回
		if(!running)
		{
			return false;
		}
		getExecutor().execute(new SocketProcessor(wrapper));
		return true;
	}
	
	private class ServerSocketThread implements Runnable
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			//获取socket配置信息
			SocketPropertityBean socketPropertityBean = ConfigProxy.getSocketProperty();
			DefaultServerSocketFactory socketFactory = new DefaultServerSocketFactory();
			ServerSocket serverSocket = null;
			try{
				if(socketPropertityBean.getCreateSocketType().equals(CreateSocketType.BY_PORT))
				{
					serverSocket = socketFactory.createSocket(socketPropertityBean.getPort());
				}else if(socketPropertityBean.getCreateSocketType().equals(CreateSocketType.BY_PORT_BACKLOG))
				{
					serverSocket = socketFactory.createSocket(socketPropertityBean.getPort(), socketPropertityBean.getBacklog());
				}
				else if(socketPropertityBean.getCreateSocketType().equals(CreateSocketType.BY_PORT_BACKLOG_IP))
				{
					InetAddress ifAddress = InetAddress.getByName(socketPropertityBean.getIpAddress());
					serverSocket = socketFactory.createSocket(socketPropertityBean.getPort(), socketPropertityBean.getBacklog(), ifAddress);
				}
			}catch (InstantiationException e) {
				// TODO Auto-generated catch block
				//未完待续
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//未完待续
				e.printStackTrace();
			}
			while(running)
			{
				//如果达到最大连接数则等待
				countUpOrAwaitConnection();
				Socket socket = null;
				try {
					socket = socketFactory.acceptSocket(serverSocket);
					logger.info(socket.getInetAddress()+"已经连接上");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					countDownConnection();
					e.printStackTrace();
				}
				if(!processSocket(socket))
				{
					countDownConnection();
	                // Close socket right away
	                closeSocket(socket);
				}
			}
		}
		
	}
	
	protected class SocketProcessor implements Runnable
	{
		protected SocketWrapper<Socket> socket = null;
		
		public SocketProcessor(SocketWrapper<Socket> socket) {
            if (socket==null) throw new NullPointerException();
            this.socket = socket;
        }
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized (socket) {
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(socket.getSocket().getInputStream()));
					String line = in.readLine();
					while(line!=null)
					{
						if(socket.isImmediateProcessing())
						{
							String [] data = line.split(" ");
							MongoBean bean = new MongoBean()
												.withTarget(data[0])
												.withValue(data[1])
												.withTimestamp(data[2]);
							DataTransferJobImmediate dataTransferJobImmediate = new DataTransferJobImmediate(bean);
							try {
								dataTransferJobImmediate.execute(null);
							} catch (JobExecutionException e) {
								// TODO Auto-generated catch block
								//直接忽略
							}
						}
						else {
							String [] data = line.split(" ");
							ConcurrentList.putDataInList(new MongoBean()
															.withTarget(data[0])
															.withValue(data[1])
															.withTimestamp(data[2]));
						}
						line = in.readLine();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
