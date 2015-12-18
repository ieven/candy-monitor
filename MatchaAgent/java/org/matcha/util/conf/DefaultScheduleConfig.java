package org.matcha.util.conf;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.yuxindata.iomp.core.RegisterConfig;
import com.yuxindata.iomp.core.io.ClassPathResource;
import com.yuxindata.iomp.core.io.Resource;
import com.yuxindata.iomp.exception.ConfigException;
import com.yuxindata.iomp.util.xml.XMLUtil;

public class DefaultScheduleConfig implements RegisterConfig{

	/**
	 * 配置文件路径
	 */
	private static final String SCHEDULE_PATH = "org/matcha/conf/schedule.xml";
	
	/**
	 * 数据结构<配置文件节点名称，对应属性值>
	 */
	private Map<String, String> map;
	
	public DefaultScheduleConfig()
	{
		List<Element> list = XMLUtil.getGivenConfigFileNodes(SCHEDULE_PATH);
		this.map = new HashMap<String, String>(list.size());
		beforeRegister(list);
		register(list);
		afterRegister(list);
	}
	@Override
	public void register(List<Element> list) {
		// TODO Auto-generated method stub
		for(Element element : list)
		{
			if(element.attributeValue("key")==null||element.attributeValue("key").equals(""))
			{
				throw new ConfigException("任务调度配置文件key为空");
			}
			else if(element.attributeValue("value")==null||element.attributeValue("value").equals(""))
			{
				throw new ConfigException("任务调度配置文件value为空");
			}
			this.map.put(element.attributeValue("key"), element.attributeValue("value"));
		}
	}

	@Override
	public void beforeRegister(List<Element> list) {
		// TODO Auto-generated method stub
		Resource resource = new ClassPathResource(SCHEDULE_PATH);
		XMLUtil xmlUtil = null;
		try {
			xmlUtil = new XMLUtil(resource.getInputStream());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			//此处直接忽略
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//此处直接忽略
		}
		//集群开关
		String clusterSwitch = list.get(0).attributeValue("value");
		if(clusterSwitch.equalsIgnoreCase("on"))
		{
			list.clear();
			list.addAll(xmlUtil.getNodesByXPath("content/cluster/leaf"));
		}
		else if(clusterSwitch.equalsIgnoreCase("off"))
		{
			list.clear();
			list.addAll(xmlUtil.getNodesByXPath("content/common/leaf"));
		}
		else
		{
			
		}
	}

	@Override
	public void afterRegister(List<Element> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map getConfigMap() {
		// TODO Auto-generated method stub
		return this.map;
	}

}
