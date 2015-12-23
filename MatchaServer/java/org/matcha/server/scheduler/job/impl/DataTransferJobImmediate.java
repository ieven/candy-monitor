package org.matcha.server.scheduler.job.impl;

import org.matcha.server.mongo.impl.DefaultMongoDao;
import org.matcha.server.po.MongoBean;
import org.matcha.server.scheduler.job.AbstractJob;

import com.yuxindata.iomp.db.mongo.BasicDBOperationDao;

public class DataTransferJobImmediate extends AbstractJob{

	private static final String TABLENAME = "test";
	
	private MongoBean bean;
	
	public DataTransferJobImmediate(MongoBean bean)
	{
		this.bean = bean;
	}
	
	@Override
	public void handleJob() {
		// TODO Auto-generated method stub
		stroeDataIntoDB(bean);
	}

	/**
	 * 将数据存入数据库
	 * @param list
	 */
	public void stroeDataIntoDB(MongoBean bean)
	{
		BasicDBOperationDao dao = new DefaultMongoDao(TABLENAME);
		dao.insert(bean);
	}

	@Override
	public String getClazzName() {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getName());
		return this.getClass().getName();
	}

}
