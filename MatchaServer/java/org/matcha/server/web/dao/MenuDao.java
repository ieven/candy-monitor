package org.matcha.server.web.dao;

import java.util.List;

import org.matcha.server.mongo.AbstractBasicMongoOperation;

import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class MenuDao extends AbstractBasicMongoOperation{

	private String tablename;
	
	public MenuDao(String tablename)
	{
		this.tablename = tablename;
	}
	
	@Override
	public WriteResult update(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object mongoObjectToBean(DBObject dbo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setTableName() {
		// TODO Auto-generated method stub
		return tablename;
	}

	@Override
	public void createIndices() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 获取数据库中收集到的机器信息
	 * @return
	 */
	public List<String> findMenuData()
	{
		return getDBCollection().distinct("target");
	}

}
