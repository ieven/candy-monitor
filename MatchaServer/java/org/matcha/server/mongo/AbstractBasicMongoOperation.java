package org.matcha.server.mongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.matcha.server.po.MongoBean;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.yuxindata.iomp.db.mongo.BasicDBOperationDao;
import com.yuxindata.iomp.db.mongo.proxy.DefaultMongoProxy;
import com.yuxindata.iomp.exception.MongoDBException;

/**
 * 抽象数据库基础操作类，继承使用
 * @author lichong
 *
 */
public abstract class AbstractBasicMongoOperation implements BasicDBOperationDao{

	@Override
	public DBCollection getCollection(String tableName) {
		// TODO Auto-generated method stub
		return DefaultMongoProxy.getDB().getCollection(tableName);
	}

	@Override
	public WriteResult save(Object object) {
		// TODO Auto-generated method stub
		return getDBCollection().save(beanToMongoObject(object));
	}
	
	@Override
	public WriteResult insert(Object object) {
		// TODO Auto-generated method stub
		return getDBCollection().insert(beanToMongoObject(object));
	}
	
	public WriteResult insertList(List<Object> list) {
		// TODO Auto-generated method stub
		List<DBObject> insertList = new ArrayList<>(list.size());
		for(Object object : list)
		{
			insertList.add(beanToMongoObject(object));
		}
		return getDBCollection().insert(insertList);
	}

	@Override
	public abstract  WriteResult update(Object object);

	@Override
	public Object findOne(Object object) {
		// TODO Auto-generated method stub
		return mongoObjectToBean(getDBCollection().findOne(beanToMongoObject(object)));
	}
	
	@Override
	public List<Object> find(Object object) {
		// TODO Auto-generated method stub
		DBCursor dbc = getDBCollection().find(beanToMongoObject(object));
		List<Object> result = new ArrayList<>();
		while (dbc.hasNext()) {
			result.add(mongoObjectToBean(dbc.next()));
        }
		return result;
	}
	
	
	@Override
	public WriteResult remove(Object object) {
		// TODO Auto-generated method stub
		return getDBCollection().remove(beanToMongoObject(object));
	}
	
	@Override
	public abstract Object mongoObjectToBean(DBObject dbo);

	@Override
	public BasicDBObject beanToMongoObject(Object object) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("target", ((MongoBean)object).getTarget());
		map.put("value",((MongoBean)object).getValue());
		map.put("timestamp", ((MongoBean)object).getTimestamp());
		return new BasicDBObject(map);
	}
	
	/**
	 * 设定需要操作的数据集合名称，即表名称
	 * @return
	 */
	public abstract String setTableName();
	
	/**
	 * 获取指定的数据库集合，仅限内部使用
	 * @return
	 */
	protected DBCollection getDBCollection()
	{
		String tableName = setTableName();
		if(tableName==null||tableName.equals(""))
		{
			throw new MongoDBException("未设置需要操作的表明");
		}
		return getCollection(tableName);
	}
	
	/**
	 * 创建mongodb索引
	 */
	public abstract void createIndices();
	
}
