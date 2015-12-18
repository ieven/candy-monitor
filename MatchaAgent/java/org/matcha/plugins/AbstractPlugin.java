package org.matcha.plugins;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.matcha.po.BasicDataBean;
import org.matcha.po.HostBean;
import org.matcha.service.proxy.MatchaConfigProxy;
import org.matcha.util.http.HttpRequest;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPlugin implements Job, MatchaPlugin {

	Logger logger = LoggerFactory.getLogger(getClazzName());

	/**
	 * 将要上送的数据载体
	 */
	private List<BasicDataBean> dataBeanList;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		dataBeanList = new ArrayList<>();
		beforeCollectData(dataBeanList);
		logger.debug("插件开始收集数据了");
		doCollectData(dataBeanList);
		logger.debug("插件收集完数据了");
		afterCollectData(dataBeanList);
		beforeSendData(dataBeanList);
		logger.debug("插件开始发送数据了");
		sendData(dataBeanList);
		afterSendData(dataBeanList);
	}

	@Override
	public void beforeCollectData(List<BasicDataBean> dataBeanList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCollectData(List<BasicDataBean> dataBeanList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeSendData(List<BasicDataBean> dataBeanList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendData(List<BasicDataBean> dataBeanList) {
		// TODO Auto-generated method stub
		//拼接需要发送的数据
		String sendData = handlerSendData(dataBeanList);
		logger.debug("上送的数据为:"+sendData);
		// 遍历需要发送的主机
		Map<String, HostBean> map = MatchaConfigProxy.getHostConfigMap();
		for (Map.Entry<String, HostBean> entry : map.entrySet()) {
			HttpRequest.sendPostIgnoreResponse(entry.getValue().getTargetIp().trim(), sendData);
		}
	}

	@Override
	public void afterSendData(List<BasicDataBean> dataBeanList) {
		// TODO Auto-generated method stub

	}

	public String handlerSendData(List<BasicDataBean> dataBeanList)
	{
		StringBuffer buffer = new StringBuffer();
		String lineSeparator = System.getProperty("line.separator", "\n");
		for(BasicDataBean dataBean : dataBeanList)
		{
			buffer.append(dataBean.getKey()).append(" ")
			.append(dataBean.getValue()).append(" ")
			.append(dataBean.getTimestamp()).append(lineSeparator);
		}
		return buffer.toString();
	}
}
