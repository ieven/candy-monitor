package org.matcha.server.startup;

import java.util.Map;

import org.matcha.server.concurrent.ServerSocketListener;
import org.matcha.server.po.CreateSocketType;
import org.matcha.server.po.SocketPropertityBean;
import org.matcha.server.proxy.ConfigProxy;
import org.matcha.server.scheduler.job.impl.DataTransferJobDelay;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuxindata.iomp.exception.ConfigException;
import com.yuxindata.iomp.po.DBType;
import com.yuxindata.iomp.po.MongoDb;
import com.yuxindata.iomp.util.StringUtils;

public class Bootstrap {
	
	private static final String VERSION = "matcha.version";
	
	private static final String SCHEDULE_SWITCH = "matcha.schedule.switch";
	
	private static final String SCHEDULE_CRON = "matcha.schedule.cron";
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 初始化
	 * @throws Exception
	 */
	public void init() throws Exception
	{
		logger.info("MatchaServer "+ConfigProxy.getSystemConfigMap().get(VERSION));
		long t1 = System.nanoTime();
		//初始化配置信息
		if(ConfigProxy.getSocketProperty()==null)
		{
			throw new Exception("初始化配置信息失败");
		}
		SocketPropertityBean socketPropertityBean = ConfigProxy.getSocketProperty();
		if(socketPropertityBean.getPort()!=0&&socketPropertityBean.getBacklog()!=0&&socketPropertityBean.getIpAddress()!=null)
		{
			socketPropertityBean.withCreateSocketType(CreateSocketType.BY_PORT_BACKLOG_IP);
		}else if(socketPropertityBean.getPort()!=0&&socketPropertityBean.getBacklog()!=0)
		{
			socketPropertityBean.withCreateSocketType(CreateSocketType.BY_PORT_BACKLOG);
		}
		else if (socketPropertityBean.getPort()!=0) {
			socketPropertityBean.withCreateSocketType(CreateSocketType.BY_PORT);
		}
		else
		{
			throw new Exception("初始化配置信息失败，请检查server配置文件是否合法");
		}
		//初始化系统信息
		Map<String, String> sysMap = ConfigProxy.getSystemConfigMap();
		for(Map.Entry entry:sysMap.entrySet()){   
			logger.info(entry.getKey()+"      "+entry.getValue());
		}
		//初始化数据库信息
		initDBConfig();
		//启动任务调度
		String offOrOn = (String) ConfigProxy.getSystemConfigMap().get(SCHEDULE_SWITCH);
		if(offOrOn.equalsIgnoreCase("on"))
		{
			scheduler();
		}
		else if(offOrOn.equalsIgnoreCase("off"))
		{
			//不做任何操作
		}
		long t2 = System.nanoTime();
		logger.info("Initialization config in "+((t2 - t1) / 1000000) + " ms");
	}
	
	private void scheduler() {
		// TODO Auto-generated method stub
		try {
			// 获取任务工厂
			SchedulerFactory factory = new StdSchedulerFactory();
			// 从工厂中获取任务
			Scheduler scheduler = factory.getScheduler();
			//开启任务调度引擎
			scheduler.start();
			// 定义一个job
			JobDetail job = JobBuilder.newJob(DataTransferJobDelay.class)
					.withIdentity("matchaJob", "matchaGroup").build();
			// 定义一个触发器
			Trigger trigger = TriggerBuilder.newTrigger()
					.forJob(job)
					.withIdentity("matchaTrigger", "matchaGroup")
					.withSchedule(CronScheduleBuilder.cronSchedule((String)ConfigProxy.getSystemConfigMap().get(SCHEDULE_CRON)))
					.build();
			//绑定触发器和job
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 启动入口
	 */
	public void start()
	{
		long t1 = System.nanoTime();
		//启动监听
		ServerSocketListener listener = new ServerSocketListener();
		listener.start();
		long t2 = System.nanoTime();
		logger.info("Initialization config in "+((t2 - t1) / 1000000) + " ms");
		
	}
	
	/**
	 * 初始化数据库
	 */
	public void initDBConfig()
	{
		Map<String, String> basicConfigMap = ConfigProxy.getDBConfigMap();
		String dbType = basicConfigMap.get("dbType").toUpperCase();
		if(dbType.equals(DBType.MONGODB.toString()))
		{
			//初始化mongodb
			try {
				MongoDb mongoDb = MongoDb.getInstance();
				String[] host = basicConfigMap.get("mongoUrl").split(",");
				int[] port = StringUtils.stringToInt(basicConfigMap.get("mongoPort").split(","));
				String userName  = basicConfigMap.get("userName");
				String password = basicConfigMap.get("password");
				String dBName = basicConfigMap.get("mongoDbName");
				int connectionsPerHost = basicConfigMap.get("connectionsPerHost").equals("")||basicConfigMap.get("connectionsPerHost")==null?0:Integer.parseInt(basicConfigMap.get("connectionsPerHost"));
				int threadsAllowedToBlockForConnectionMultiplier = basicConfigMap.get("threadsAllowedToBlockForConnectionMultiplier").equals("")||basicConfigMap.get("threadsAllowedToBlockForConnectionMultiplier")==null?0:Integer.parseInt(basicConfigMap.get("threadsAllowedToBlockForConnectionMultiplier"));
				boolean authentication = false;
				if(basicConfigMap.get("authentication").equalsIgnoreCase("false"))
				{
					
				}
				else if (basicConfigMap.get("authentication").equalsIgnoreCase("true")) {
					authentication = true;
				}
				else {
					
				}
				mongoDb.withHost(host)
				.withProt(port)
				.withUserName(userName)
				.withPassword(password)
				.withDBName(dBName)
				.withConnectionsPerHost(connectionsPerHost)
				.withThreadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier)
				.withAuthentication(authentication);
			} catch (Exception e) {
				// TODO: handle exception
				throw new ConfigException("初始化mongodb失败，请确认配置是否正确",e);
			}
		}
		else if(dbType.equals(DBType.MYSQL.toString()))
		{
			//初始化mysql
		}
		else if(dbType.equals(DBType.ORACLE.toString()))
		{
			//初始化oracle
		}
		else
		{
			throw new ConfigException("没有找到适合的数据库，IOMP graphite端初始化失败");
		}
	}
}
