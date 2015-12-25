package org.matcha.server.util;

import com.yuxindata.iomp.util.TimestampOpration;


/**
 * target辅助类
 * @author lichong
 *
 */
public class TargetUtil {
	
	/**
	 * 将原始target语句转换为数据库能够识别的正则表达式
	 * @param target
	 * @return
	 */
	public static String targetToPattern(String target)
	{
		return target.replace("*", ".*");
	}
	
	/**
	 * 获取时间戳
	 * @param date
	 * @param timeRange
	 * @return
	 */
	public static String getTimestamp(String date,String timeRange)
	{
		if(timeRange.equalsIgnoreCase("now"))
		{
			return TimestampOpration.DateToTimestamp(date);
		}
		else
		{
			String[] time = timeRange.split("minutes");
			int min = Integer.parseInt(time[0].substring(1, time[0].length()));
			long currentTime = Long.parseLong(TimestampOpration.DateToTimestamp(date));
			return (currentTime-(min*1000*60))+"";
		}
	}
}
