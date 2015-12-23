package org.matcha.server.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * serverSocket工厂类，用来实现ssl和非ssl的sockets
 * @author lichong
 *
 */
public interface ServerSocketFactory {
	/**
	 * 返回一个server socket，使用默认的host，只进行端口绑定
	 * @param port
	 * @return
	 * @throws IOException
	 * @throws InstantiationException
	 */
	ServerSocket createSocket(int port) throws IOException,InstantiationException;
	
	/**
	 * 返回一个server socket，使用默认的host，只进行端口绑定，并使用特定的backlog
	 * @param port
	 * @param backlog
	 * @return
	 * @throws IOException
	 * @throws InstantiationException
	 */
	ServerSocket createSocket(int port, int backlog) throws IOException,InstantiationException;
	
	/**
	 * 返回一个server socket，使用提供的IP地址，只进行端口绑定，并使用特定的backlog
	 * @param port
	 * @param backlog
	 * @param ifAddress
	 * @return
	 * @throws IOException
	 * @throws InstantiationException
	 */
	ServerSocket createSocket(int port, int backlog, InetAddress ifAddress)throws IOException, InstantiationException;
	
	/**
	 * 封装一层socket accept请求
	 * @param socket
	 * @return
	 * @throws IOException
	 */
	Socket acceptSocket(ServerSocket socket) throws IOException;
	
	/**
	 * 触发ssl握手。http请求则不会进行任何操作
	 * @param socket
	 * @throws IOException
	 */
	void handshake(Socket socket) throws IOException;
}
