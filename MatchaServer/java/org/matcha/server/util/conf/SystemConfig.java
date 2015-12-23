package org.matcha.server.util.conf;

import java.io.IOException;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.yuxindata.iomp.core.AbstractRegisterConfig;
import com.yuxindata.iomp.core.io.ClassPathResource;
import com.yuxindata.iomp.core.io.Resource;
import com.yuxindata.iomp.exception.ConfigException;
import com.yuxindata.iomp.po.RegistType;
import com.yuxindata.iomp.util.xml.XMLUtil;

public class SystemConfig extends AbstractRegisterConfig{

	/**
	 * 配置文件路径
	 */
	private static final String SYSTEM_PATH = "org/matcha/server/util/conf/system.xml";
	
	/**
	 * 配置文件名称
	 */
	private static final String CONFIG_FILE_NAME = "system.xml";
	
	public SystemConfig()
	{
		this(RegistType.SPECIFIC_XPATH, "content/threadpool/leaf");
	}
	
	private SystemConfig(RegistType registType, String XPath) {
		super(registType, XPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void register(List<Element> list) {
		// TODO Auto-generated method stub
		//读取线程池配置信息
		Element element = list.get(0);
		try {
			int num = Integer.parseInt(element.attributeValue("num"));
			this.map.put("threadpool.num", num);
			this.map.putAll(System.getProperties());
		} catch (Exception e) {
			// TODO: handle exception
		}
		//读取文件信息
		Resource resource = new ClassPathResource(SYSTEM_PATH);
		XMLUtil xmlUtil = null;
		try {
			xmlUtil = new XMLUtil(resource.getInputStream());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			// 此处直接忽略
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// 此处直接忽略
		}
		//读取版本配置信息
		String version = xmlUtil.getNodeData("version");
		this.map.put("matcha.version", version);
		//读取定时缓存配置信息
		String offOrOn = xmlUtil.getNodeData("schedule.switch");
		if(offOrOn.equalsIgnoreCase("on"))
		{
			this.map.put("matcha.schedule.switch", offOrOn);
			String cron = xmlUtil.getNodeData("schedule.cron");
			this.map.put("matcha.schedule.cron", cron);
		}
		else if(offOrOn.equalsIgnoreCase("off"))
		{
			this.map.put("matcha.schedule.switch", offOrOn);
		}
		else
		{
			throw new ConfigException("system配置文件switch配置异常，未找到对应说明");
		}
	}

	@Override
	public void beforeRegister(List<Element> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterRegister(List<Element> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getConfigPath() {
		// TODO Auto-generated method stub
		return SYSTEM_PATH;
	}

}
