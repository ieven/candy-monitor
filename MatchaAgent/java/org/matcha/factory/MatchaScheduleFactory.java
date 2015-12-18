package org.matcha.factory;

import java.util.Properties;

import org.matcha.service.proxy.MatchaConfigProxy;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 * MatchaAgent任务调度工厂
 * @author lichong
 *
 */
public class MatchaScheduleFactory extends StdSchedulerFactory{
	
	public MatchaScheduleFactory() throws SchedulerException
	{
		Properties properties = new Properties();
		properties.putAll(MatchaConfigProxy.getScheduleConfigMap());
		properties.putAll(System.getProperties());
		super.initialize(properties);
	} 
}
