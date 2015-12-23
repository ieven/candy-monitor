package org.matcha.server;

import org.matcha.server.startup.Bootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MatchaServer启动入口
 * @author lichong
 *
 */
public class StartUp {
	
	/**
	 * 引导程序
	 */
	private static Bootstrap daemon = null;
	
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(StartUp.class);
		Bootstrap bootstrap = new Bootstrap();
		try {
			bootstrap.init();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return;
		}
		daemon = bootstrap;
		try 
		{
            String command = "start";
            if (args.length > 0) {
                command = args[args.length - 1];
            }
            if (command.equals("start")) {
            	daemon.start();
            } else if (command.equals("stop")) {
            	//有待完善
            }else {
            	logger.error("没有找到对应命令与"+command+"匹配");
            }
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return;
		}
	}
}
