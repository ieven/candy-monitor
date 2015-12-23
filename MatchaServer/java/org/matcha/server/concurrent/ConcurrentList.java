package org.matcha.server.concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.matcha.server.po.MongoBean;

public class ConcurrentList {
	private static List<MongoBean> list = new LinkedList<MongoBean>();
	private static ReentrantLock lock = new ReentrantLock();
	
	/**
	 * 将数据添加入list
	 * @param data
	 * @return
	 */
	public static boolean putDataInList(MongoBean data)
	{
		lock.lock();
		try {
			list.add(data);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}finally{
			lock.unlock();
		}
	}
	
	/**
	 * 获取list
	 * @return
	 */
	public static List<MongoBean> getList()
	{
		return list;
	}
	
	/**
	 * 清空list
	 */
	public static void clearList()
	{
		list.clear();
//		lock.lock();
//		try {
//			list.clear();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally{
//			lock.unlock();
//		}
	}
}
