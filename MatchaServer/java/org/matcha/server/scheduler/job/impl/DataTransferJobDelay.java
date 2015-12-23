package org.matcha.server.scheduler.job.impl;

import java.util.List;

import org.matcha.server.concurrent.ConcurrentList;
import org.matcha.server.mongo.impl.DefaultMongoDao;
import org.matcha.server.scheduler.job.AbstractJob;

import com.yuxindata.iomp.db.mongo.BasicDBOperationDao;

public class DataTransferJobDelay extends AbstractJob{

	private static final String TABLENAME = "initial_data";
	
	@Override
	public void handleJob() {
		// TODO Auto-generated method stub
		synchronized (ConcurrentList.getList()) {
			if(ConcurrentList.getList().isEmpty())
			{
				return;
			}
			//将list中的数据存入数据库
			stroeDataIntoDB(ConcurrentList.getList());
			//清空list中的数据
			ConcurrentList.clearList();
		}
	}

	/**
	 * 将数据存入数据库
	 * @param list
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void stroeDataIntoDB(List list)
	{
		BasicDBOperationDao dao = new DefaultMongoDao(TABLENAME);
		dao.insertList(list);
	}

	@Override
	public String getClazzName() {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getName());
		return this.getClass().getName();
	}
}
