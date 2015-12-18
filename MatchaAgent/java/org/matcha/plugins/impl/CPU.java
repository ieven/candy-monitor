package org.matcha.plugins.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.matcha.plugins.AbstractPlugin;
import org.matcha.po.BasicDataBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuxindata.iomp.util.TimestampOpration;

public class CPU extends AbstractPlugin{

	@Override
	public String getClazzName() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
	}

	@Override
	public void doCollectData(List<BasicDataBean> dataBeanList) {
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestramp = TimestampOpration.DateToTimestamp(dateFormat.format(new Date()));
		BasicDataBean dataBean = new BasicDataBean().withKey("172_20_35_88.cpu.cputop")
				.withValue("17")
				.withTimestamp(timestramp);
		dataBeanList.add(dataBean);
		BasicDataBean dataBean2 = new BasicDataBean().withKey("172_20_35_88.df.cputop")
				.withValue("17")
				.withTimestamp(timestramp);
		dataBeanList.add(dataBean2);
	}

	
}
