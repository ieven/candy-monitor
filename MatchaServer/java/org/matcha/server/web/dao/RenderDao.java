package org.matcha.server.web.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.matcha.server.mongo.AbstractBasicMongoOperation;
import org.matcha.server.po.MongoObjectBean;
import org.matcha.server.web.po.Data;
import org.matcha.server.web.po.TargetData;

import com.mongodb.BasicDBList;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class RenderDao extends AbstractBasicMongoOperation{
	
	/**
	 * tablename
	 */
	private String TABLENAME = "initial_data";
	
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
		return TABLENAME;
	}

	@Override
	public void createIndices() {
		// TODO Auto-generated method stub
		
	}

	public Map<String, TargetData> findTargetData(String targetPattern,String fromTimestamp,String untilTimestamp)
	{
		Pattern pattern = Pattern.compile(targetPattern);
		//取出库中所有target
		List<String> list = getDBCollection().distinct("target");
		//创建目标targetSet
		Set<String> targetSet = new HashSet<String>();
		//遍历list将目标target放入set
		for(String str : list)
		{
			if(pattern.matches(targetPattern, str))
			{
				targetSet.add(str);
			}
		}
		if(targetSet.size()==0)
		{
			return null;
		}
		DBObject query = MongoObjectBean.object("target", MongoObjectBean.object("$in", targetSet));
		//使用BasicDBList实现 1<timeStamp<2功能
		BasicDBList basicDBList = new BasicDBList();
		basicDBList.add(MongoObjectBean.object("timestamp", MongoObjectBean.object("$gt", fromTimestamp)));
		basicDBList.add(MongoObjectBean.object("timestamp", MongoObjectBean.object("$lt", untilTimestamp)));
		query.put("$and", basicDBList);
		DBCursor dbc = getDBCollection().find(query).sort(MongoObjectBean.object("timeStamp", -1));
		//初始化结果数据
		Map<String, TargetData> resultMap = new HashMap<String, TargetData>();
		while(dbc.hasNext())
        {
        	DBObject dbObject = dbc.next();
        	String target = (String) dbObject.get("target");
        	String timestamp = (String) dbObject.get("timestamp");
        	String value = (String) dbObject.get("value");
        	if(resultMap.containsKey(target))
        	{
        		resultMap.get(target).getDataList().add(new Data().withTimestamp(timestamp).withValue(value));
        	}
        	else
        	{
        		TargetData targetData = new TargetData();
        		targetData.setTarget(target);
        		targetData.getDataList().add(new Data().withTimestamp(timestamp).withValue(value));
        		resultMap.put(target, targetData);
        	}
        	targetSet.remove(target);
        }
		//检验是否请求的数据都有，没有的使用null填充
		for(String target: targetSet)
		{
			TargetData targetData = new TargetData();
    		targetData.setTarget(target);
			resultMap.put(target, targetData);
		}
		return resultMap;
	}
}
