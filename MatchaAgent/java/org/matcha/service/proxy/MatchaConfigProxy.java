package org.matcha.service.proxy;

import java.util.Map;

import org.matcha.po.HostBean;
import org.matcha.po.PluginBean;
import org.matcha.util.conf.DefaultHostConfig;
import org.matcha.util.conf.DefaultMatchaPluginConfig;
import org.matcha.util.conf.DefaultPluginConfig;
import org.matcha.util.conf.DefaultScheduleConfig;

import com.yuxindata.iomp.core.RegisterConfig;

/**
 * 配置文件代理
 * @author lichong
 *
 */
public class MatchaConfigProxy {
	private static RegisterConfig pluginConfig;
	
	private static RegisterConfig hostConfig;
	
	private static RegisterConfig scheduleConfig;
	
	private static RegisterConfig matchaPluginConfig;
	//由于涉及加载顺序此静态块顺序不能改变
	static{
		pluginConfig = new DefaultPluginConfig();
		
		hostConfig = new DefaultHostConfig();
		
		scheduleConfig = new DefaultScheduleConfig();
		
		matchaPluginConfig = new DefaultMatchaPluginConfig();
	}
	
	/**
	 * 获取所有plugin配置文件注册信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final Map<String, PluginBean> getPluginConfigMap()
	{
		return pluginConfig.getConfigMap();
	}
	
	/**
	 * 获取host配置文件注册信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final Map<String, HostBean> getHostConfigMap()
	{
		return hostConfig.getConfigMap();
	}
	
	/**
	 * 获取schedule配置文件注册信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final Map<String, String> getScheduleConfigMap()
	{
		return scheduleConfig.getConfigMap();
	}
	
	/**
	 * 获取需要加载的插件的map信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final Map<String, PluginBean> getLoadPluginMap()
	{
		return matchaPluginConfig.getConfigMap();
	}
}
