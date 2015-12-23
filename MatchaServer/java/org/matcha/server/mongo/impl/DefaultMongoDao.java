package org.matcha.server.mongo.impl;

import org.matcha.server.mongo.AbstractBasicMongoOperation;

import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public  class DefaultMongoDao extends AbstractBasicMongoOperation{

	private String tableName;
	public DefaultMongoDao(String tableName)
	{
		this.tableName = tableName;
	}
	
	@Override
	public String setTableName() {
		// TODO Auto-generated method stub
		return tableName;
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
	public void createIndices() {
		// TODO Auto-generated method stub
		
	}

}
