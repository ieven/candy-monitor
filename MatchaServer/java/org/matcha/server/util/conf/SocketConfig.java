package org.matcha.server.util.conf;

import java.util.List;

import org.dom4j.Element;
import org.matcha.server.po.SocketPropertityBean;

import com.yuxindata.iomp.core.AbstractRegisterConfig;
import com.yuxindata.iomp.exception.ConfigException;
import com.yuxindata.iomp.po.RegistType;

public class SocketConfig extends AbstractRegisterConfig{

	/**
	 * 配置文件路径
	 */
	private static final String SERVER_PATH = "org/matcha/server/conf/server.xml";
	
	/**
	 * 配置文件名称
	 */
	private static final String CONFIG_FILE_NAME = "server.xml";
	
	public SocketConfig()
	{
		this(RegistType.SPECIFIC_XPATH, "content/server/leaf");
	}
	
	private SocketConfig(RegistType registType, String XPath) {
		super(registType, XPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void register(List<Element> list) {
		// TODO Auto-generated method stub
		int i = 0;
		for(Element element : list)
		{
			if(element.attributeValue("port")==null||element.attributeValue("port").equals(""))
			{
				throw new ConfigException(CONFIG_FILE_NAME + "中server port标签为空");
			}
			String port = element.attributeValue("port");
			String ipAdderss = "0";
			String backlog = "0";
			if(element.attributeValue("ipAdderss")!=null&&!element.attributeValue("ipAdderss").equals(""))
			{
				ipAdderss = element.attributeValue("ipAdderss");
			}
			if(element.attributeValue("backlog")!=null&&!element.attributeValue("backlog").equals(""))
			{
				backlog = element.attributeValue("backlog");
			}
			try {
				SocketPropertityBean socketPropertityBean = new SocketPropertityBean()
				.withPort(Integer.parseInt(port))
				.withBacklog(Integer.parseInt(backlog))
				.withIpAddress(ipAdderss);
				this.map.put(i+"", socketPropertityBean);
				i++;
			} catch (Exception e) {
				// TODO: handle exception
				throw new ConfigException(CONFIG_FILE_NAME+"配置文件加载失败，请检查填写是否合法", e);
			}
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
