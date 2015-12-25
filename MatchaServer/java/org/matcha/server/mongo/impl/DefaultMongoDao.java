package org.matcha.server.mongo.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.matcha.server.mongo.AbstractBasicMongoOperation;
import org.matcha.server.po.MongoObjectBean;

import com.mongodb.BasicDBList;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
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

	public void findData(String target,String beginTime,String endTime)
	{
		Pattern pattern = Pattern.compile(target);
		DBObject query = MongoObjectBean.object("target", pattern);
		
		BasicDBList basicDBList = new BasicDBList();
		basicDBList.add(MongoObjectBean.object("timeStamp", MongoObjectBean.object("$gte", beginTime)));
		basicDBList.add(MongoObjectBean.object("timeStamp", MongoObjectBean.object("$lte", endTime)));
		query.put("$and", basicDBList);
		
		DBCursor dbc = getDBCollection().find(query);
		while(dbc.hasNext())
        {
        	DBObject dbObject = dbc.next();
        	System.out.println(dbObject.get("target")+"------"+dbObject.get("timeStamp"));
        }
	}
	
}
