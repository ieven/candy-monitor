package org.matcha.plugins;

import java.util.List;

import org.matcha.po.BasicDataBean;

/**
 * matcha plugin接口
 * @author lichong
 *
 */
public interface MatchaPlugin {
	
	/**
	 * 收集数据
	 * @param dataBean
	 */
	public void doCollectData(List<BasicDataBean> dataBeanList);
	
	/**
	 * 收集数据之前做点儿什么
	 * @param dataBean
	 */
	public void beforeCollectData(List<BasicDataBean> dataBeanList);
	
	/**
	 * 收集数据之后做点儿什么
	 * @param dataBean
	 */
	public void afterCollectData(List<BasicDataBean> dataBeanList);
	
	/**
	 * 在发送数据之前做点儿什么
	 * @param dataBean
	 */
	public void beforeSendData(List<BasicDataBean> dataBeanList);
	
	/**
	 * 向目标主机发送数据
	 * @param dataBean
	 */
	public void sendData(List<BasicDataBean> dataBeanList);
	
	/**
	 * 在发送数据之后做点儿什么
	 * @param dataBean
	 */
	public void afterSendData(List<BasicDataBean> dataBeanList);
	
	/**
	 * 获取当前插件类名字
	 * @return
	 */
	public abstract String getClazzName();
}
