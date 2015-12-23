package org.matcha.server.proxy;

import java.util.Map;

import org.matcha.server.po.SocketPropertityBean;
import org.matcha.server.util.conf.DBConfig;
import org.matcha.server.util.conf.SocketConfig;
import org.matcha.server.util.conf.SystemConfig;

import com.yuxindata.iomp.core.RegisterConfig;

public class ConfigProxy {

	private static RegisterConfig serverConfigMap;
	
	private static RegisterConfig systemConfigMap;
	
	private static RegisterConfig DBConfigMap;

	static {
		serverConfigMap = new SocketConfig();
		
		systemConfigMap = new SystemConfig();
		
		DBConfigMap = new DBConfig();
	}

	/**
	 * 获取socket配置信息
	 * @return
	 */
	public static SocketPropertityBean getSocketProperty() {
		return (SocketPropertityBean) serverConfigMap.getConfigMap().get("0");
	}
	
	/**
	 * 获取系统配置信息
	 * @return
	 */
	public static Map getSystemConfigMap()
	{
		return systemConfigMap.getConfigMap();
	}
	
	/**
	 * 获取数据库配置信息
	 * @return
	 */
	public static Map<String, String> getDBConfigMap()
	{
		return DBConfigMap.getConfigMap();
	}
}
