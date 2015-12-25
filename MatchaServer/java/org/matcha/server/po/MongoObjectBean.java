package org.matcha.server.po;

import com.mongodb.BasicDBObject;

public class MongoObjectBean extends BasicDBObject{
	
	private MongoObjectBean(String field, Object value)
	{
		put(field,value);
	}
	
	public static MongoObjectBean object(String field, Object value)
	{
		return new MongoObjectBean(field, value);
	}
	
	public MongoObjectBean with(String field, Object value)
	{
		put(field,value);
		return this;
	}
}
