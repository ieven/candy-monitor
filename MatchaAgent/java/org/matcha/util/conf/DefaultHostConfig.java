package org.matcha.util.conf;

import java.util.List;

import org.dom4j.Element;
import org.matcha.po.HostBean;
import org.matcha.po.PluginBean;

import com.yuxindata.iomp.core.AbstractRegisterConfig;
import com.yuxindata.iomp.exception.ConfigException;
import com.yuxindata.iomp.po.RegistType;

/**
 * 主机信息注册信息读取配置文件类
 * @author lichong
 *
 */
public class DefaultHostConfig extends AbstractRegisterConfig{

	/**
	 * 配置文件路径
	 */
	private static final String MATCHA_CONFING_PATH = "org/matcha/conf/matcha.xml";
	
	private static final String CONFIG_FILE_NAME = "matcha.xml";
	
	public DefaultHostConfig()
	{
		this(RegistType.SPECIFIC_XPATH,"content/host/leaf");
	}
	
	private DefaultHostConfig(RegistType registType, String XPath) {
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
			else if(element.attributeValue("des")==null||element.attributeValue("des").equals(""))
			{
				throw new ConfigException("agent配置文件"+CONFIG_FILE_NAME+" des为空");
			}
			else if(element.attributeValue("targetIp")==null||element.attributeValue("targetIp").equals(""))
			{
				throw new ConfigException("agent配置文件"+CONFIG_FILE_NAME+" targetIp为空");
			}
			String name = element.attributeValue("name").replace(".", "_");
			String des = element.attributeValue("des");
			String targetIp = element.attributeValue("targetIp");
			HostBean hostBean = new HostBean()
								.withDes(des)
								.withTargetIp(targetIp)
								.withName(name);
			this.map.put(name, hostBean);
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
		return this.MATCHA_CONFING_PATH;
	}

}
