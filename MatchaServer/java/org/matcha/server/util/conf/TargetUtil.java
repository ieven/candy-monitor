package org.matcha.server.util.conf;


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
}
