package org.matcha.util.conf;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.matcha.po.PluginBean;

import com.yuxindata.iomp.core.AbstractRegisterConfig;
import com.yuxindata.iomp.core.RegisterConfig;
import com.yuxindata.iomp.exception.ConfigException;
import com.yuxindata.iomp.po.RegistType;

/**
 * 默认插件读取配置文件类
 * @author lichong
 *
 */
public class DefaultPluginConfig extends AbstractRegisterConfig{

	/**
	 * 配置文件路径
	 */
	private static final String PLUGINS_CONFING_PATH = "org/matcha/plugins/plugins.xml";
	
	private static final String CONFIG_FILE_NAME = "plugins.xml";
	
	private DefaultMatchaPluginConfig defaultMatchaPluginConfig;
	
	public DefaultPluginConfig()
	{
		this(RegistType.SPECIFIC);
	}

	public DefaultPluginConfig(RegistType registType) {
		super(registType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void register(List<Element> list) {
		// TODO Auto-generated method stub
		for(Element element:list)
		{
			if(element.attributeValue("name")==null||element.attributeValue("name").equals(""))
			{
				throw new ConfigException("agent配置文件"+CONFIG_FILE_NAME+" name为空");
			}
			else if(element.attributeValue("des")==null||element.attributeValue("des").equals(""))
			{
				throw new ConfigException("agent配置文件"+CONFIG_FILE_NAME+" des为空");
			}
			else if(element.attributeValue("clazz")==null||element.attributeValue("clazz").equals(""))
			{
				throw new ConfigException("agent配置文件"+CONFIG_FILE_NAME+" clazz为空");
			}
			else if(element.attributeValue("cron")==null||element.attributeValue("cron").equals(""))
			{
				throw new ConfigException("agent配置文件"+CONFIG_FILE_NAME+" cron为空");
			}
			String name = element.attributeValue("name").toLowerCase();
			String des = element.attributeValue("des");
			String clazz = element.attributeValue("clazz");
			String cron = element.attributeValue("cron");
			PluginBean pluginBean = new PluginBean()
									.withClazz(clazz)
									.withCron(cron.split(","))
									.withDes(des)
									.withName(name);
			this.map.put(name, pluginBean);
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
		return this.PLUGINS_CONFING_PATH;
	}
}
