package org.matcha.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.matcha.exception.PluginInitException;
import org.matcha.factory.MatchaScheduleFactory;
import org.matcha.plugins.AbstractPlugin;
import org.matcha.po.PluginBean;
import org.matcha.service.proxy.MatchaConfigProxy;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuxindata.iomp.factory.InstanceFactory;

/**
 * 启动入口
 * 
 * @author lichong
 * 
 */
public class StrartUp {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(StrartUp.class);
		try {
			// 创建资源调度工厂
			SchedulerFactory factory = new MatchaScheduleFactory();
			// 从工厂中获取任务并启动
			Scheduler scheduler = factory.getScheduler();
			scheduler.start();
			// 获取需要加载的插件列表
			Map<String, PluginBean> map = MatchaConfigProxy.getLoadPluginMap();
			// 遍历列表，并将插件任务添加到调度池中
			for (Map.Entry<String, PluginBean> entry : map.entrySet()) {
				PluginBean bean = entry.getValue();
				AbstractPlugin plugin = null;
				try {
					plugin = InstanceFactory.getInstance(bean.getClazz());
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException e) {
					// TODO Auto-generated catch block
					throw new PluginInitException("初始化插件失败，请检查对应类是否存在", e);
				}
				// 初始化jobDetail
				JobDetail jobDetail = JobBuilder.newJob(plugin.getClass())
				// 设定job的name和group
						.withIdentity(bean.getName(), bean.getName())
						// 设定job的描述信息
						.withDescription(bean.getDes())
						// 绑定到调度引擎
						.build();
				Set<Trigger> triggersForJob = new HashSet<Trigger>(
						bean.getCron().length);
				// 遍历job上需要绑定的trigger
				for (String cron : bean.getCron()) {
					// 开始实例化trigger
					Trigger trigger = TriggerBuilder
							.newTrigger()
							// 绑定触发策略
							.withSchedule(
									CronScheduleBuilder.cronSchedule(cron))
							.build();
					triggersForJob.add(trigger);
				}
				scheduler.scheduleJob(jobDetail, triggersForJob, true);
				logger.info("已完成插件" + bean.getName() + "的加载");
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
