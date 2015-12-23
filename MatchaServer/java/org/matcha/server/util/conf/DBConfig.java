package org.matcha.server.util.conf;

import java.util.List;

import org.dom4j.Element;

import com.yuxindata.iomp.core.AbstractRegisterConfig;
import com.yuxindata.iomp.exception.ConfigException;
import com.yuxindata.iomp.po.RegistType;

public class DBConfig extends AbstractRegisterConfig{

	/**
	 * 配置文件路径
	 */
	private static final String SERVER_PATH = "org/matcha/server/conf/server.xml";
	
	/**
	 * 配置文件名称
	 */
	private static final String CONFIG_FILE_NAME = "server.xml";
	
	public DBConfig()
	{
		this(RegistType.SPECIFIC_XPATH, "content/db/leaf");
	}
	
	private DBConfig(RegistType registType, String XPath) {
		super(registType, XPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void register(List<Element> list) {
		// TODO Auto-generated method stub
		for(Element element : list)
		{
			if(element.attributeValue("key")==null||element.attributeValue("key").equals(""))
			{
				throw new ConfigException("server配置文件db配置部分key为空");
			}
			//由于mongodb用户名密码可以为空，所以此处不校验value值
			this.map.put(element.attributeValue("key"), element.attributeValue("value"));
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
		return SERVER_PATH;
	}

}
