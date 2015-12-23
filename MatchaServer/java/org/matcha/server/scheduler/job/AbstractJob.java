package org.matcha.server.scheduler.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractJob implements Job{

	Logger logger = LoggerFactory.getLogger("org.matcha.server.scheduler.job.impl.DataTransferJobDelay");
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		logger.debug("我要开始处理数据了");
		beforeHandleJob();
		handleJob();
		afterHandleJob();
		logger.debug("我处理完数据了");
	}

	/**
	 * 处理工作
	 */
	public abstract void handleJob();
	
	 /**
	  * 在处理工作之前做点儿什么
	  */
	public void beforeHandleJob()
	{
		
	}
	/**
	  * 在处理工作之后做点儿什么
	  */
	public void afterHandleJob()
	{
		
	}
	
	/**
	 * 获取当前插件类名字
	 * @return
	 */
	public abstract String getClazzName();
}
