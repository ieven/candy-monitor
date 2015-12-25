package org.matcha.server.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.matcha.server.startup.Bootstrap;

public class CollectDaemonServlet extends HttpServlet{

	/**
	 * 引导程序
	 */
	private static Bootstrap daemon = null;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		Bootstrap bootstrap = new Bootstrap();
		//初始化配置信息
		try {
			bootstrap.init();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return;
		}
		daemon = bootstrap;
		//启动监听守护进程
		try 
		{
			daemon.start();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return;
		}
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//nothing to do
	}
	
	
}
