package org.matcha.util.conf;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.matcha.po.HostBean;
import org.matcha.po.PluginBean;
import org.matcha.service.proxy.MatchaConfigProxy;

import com.yuxindata.iomp.core.AbstractRegisterConfig;
import com.yuxindata.iomp.exception.ConfigException;
import com.yuxindata.iomp.po.RegistType;

/**
 * 注册matcha.xml中配置的插件信息，若配置的des和cron会覆盖默认配置
 * @author lichong
 *
 */
public class DefaultMatchaPluginConfig extends AbstractRegisterConfig{

	/**
	 * 配置文件路径
	 */
	private static final String MATCHA_CONFING_PATH = "org/matcha/conf/matcha.xml";
	
	private static final String CONFIG_FILE_NAME = "matcha.xml";
	
	public DefaultMatchaPluginConfig()
	{
		this(RegistType.SPECIFIC_XPATH, "content/plugin/leaf");
	}
	
	private DefaultMatchaPluginConfig(RegistType registType, String XPath) {
		super(registType, XPath);
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
			String name = element.attributeValue("name").toLowerCase();
			//根据名称获取对应插件bean类
			PluginBean pluginBean = MatchaConfigProxy.getPluginConfigMap().get(name);
			if(pluginBean==null)
			{
				throw new ConfigException("未找到名称为"+name+"的插件，请检查配置是否正确");
			}
			String des = element.attributeValue("des");
			String cron = element.attributeValue("cron");
			if(des!=null&&!des.equals(""))
			{
				pluginBean.setDes(des);
			}
			if(cron!=null&&!cron.equals(""))
			{
				pluginBean.setCron(cron.split(","));
			}
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
		return MATCHA_CONFING_PATH;
	}

}
