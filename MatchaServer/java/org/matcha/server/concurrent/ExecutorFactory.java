package org.matcha.server.concurrent;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.matcha.server.proxy.ConfigProxy;

import com.yuxindata.iomp.exception.ConfigException;
import com.yuxindata.iomp.factory.IOMPThreadFactory;

/**
 * 线程池工厂
 * @author lichong
 *
 */
public class ExecutorFactory {
	
	private static Executor defaultExecutor;
	
	static{
		int num;
		try {
			num = (Integer) ConfigProxy.getSystemConfigMap().get("threadpool.num");
		} catch (Exception e) {
			// TODO: handle exception
			throw new ConfigException("system配置文件threadpool.num异常",e);
		}
//		defaultExecutor = Executors.newFixedThreadPool(num, new IOMPThreadFactory());
		defaultExecutor= Executors.newCachedThreadPool();
	}
	
	/**
	 * 获取system配置文件中配置的线程池
	 * @return
	 */
	public static Executor getDefaultExecutor()
	{
		return defaultExecutor;
	}
}
