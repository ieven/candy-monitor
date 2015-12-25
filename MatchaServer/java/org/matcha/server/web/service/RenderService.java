package org.matcha.server.web.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.matcha.server.util.TargetUtil;
import org.matcha.server.web.dao.RenderDao;
import org.matcha.server.web.po.Data;
import org.matcha.server.web.po.TargetData;

public class RenderService {
	
	/**
	 * 根据时间段和target获取数据
	 * @param from
	 * @param until
	 * @param target
	 * @return
	 */
	public List getTargetData(String from,String until,String target)
	{
		//根据target获取对应的正则表达式
		String targetPattern = TargetUtil.targetToPattern(target);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		//获取from开始时间戳
		String fromTimestamp = TargetUtil.getTimestamp(date, from);
		//获取until结束时间戳
		String untilTimestamp = TargetUtil.getTimestamp(date, until);
		RenderDao dao = new RenderDao();
		Map<String, TargetData> map = dao.findTargetData(targetPattern, fromTimestamp, untilTimestamp);
		List resultList = new ArrayList<>(map.size());
		for(Map.Entry<String, TargetData> entry : map.entrySet())
		{
			Map innerMap = new HashMap(2);
			innerMap.put("target", entry.getValue().getTarget());
			innerMap.put("datapoints", litToArray(entry.getValue().getDataList()));
			resultList.add(innerMap);
		}
		return resultList;
	}
	
	private String[][] litToArray(List<Data> list)
	{
		int num = list.size();
		String[][] result = new String[num][2];
		for(int i=0;i<list.size();i++)
		{
			result[i][0] = list.get(i).getValue();
			result[i][1] = list.get(i).getTimestamp();
		}
		return result;
	}
}
