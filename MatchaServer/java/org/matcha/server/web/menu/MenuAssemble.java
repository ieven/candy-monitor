package org.matcha.server.web.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.matcha.server.web.menu.base.MenuItemBase;

/**
 * 组装菜单
 * 
 * @author lichong
 * 
 */
public class MenuAssemble {

	/**
	 * 需要处理的菜单list
	 */
	private List<MenuItemBase> menuItems;
	/**
	 * 此次请求菜单状态true为成功，false为失败
	 */
	private boolean status;
	/**
	 * 此次处理附加信息
	 */
	private String message;

	public MenuAssemble(boolean status, String message) {
		this.status = status;
		this.message = message;
	}

	public MenuAssemble(List<MenuItemBase> menuItems, boolean status,
			String message) {
		this.menuItems = menuItems;
		this.status = status;
		this.message = message;
	}

	/**
	 * 获取可选择的菜单
	 * 
	 * @return
	 */
	public SelectableMenu getSelectableMenu() {
		if (this.status) {
			// 创建准备返回的menu
			SelectableMenu menu = new SelectableMenu();
			menu.setData(assembleSelectableMunuData());
			menu.setMessage(this.message);
			return menu;
		} else {
			SelectableMenu menu = new SelectableMenu();
			menu.setStatus("0");
			menu.setMessage(this.message);
			return menu;
		}
	}

	/**
	 * 获取待url的菜单
	 * 
	 * @return
	 */
	public UrlMenu getUrlMenu() {
		if (this.status) {
			// 创建准备返回的menu
			UrlMenu menu = new UrlMenu();
			menu.setData(assembleMenuData());
			menu.setMessage(this.message);
			return menu;
		} else {
			UrlMenu menu = new UrlMenu();
			menu.setStatus("0");
			menu.setMessage(this.message);
			return menu;
		}
	}

	public ElementMenu getElementMenu() {
		if (this.status) {
			// 创建准备返回的menu
			ElementMenu menu = new ElementMenu();
			menu.setData(menuItems);
			menu.setMessage(this.message);
			return menu;
		} else {
			ElementMenu menu = new ElementMenu();
			menu.setStatus("0");
			menu.setMessage(this.message);
			return menu;
		}
	}

	public UrlMenu getRecourceLeftUrlMenu() {
		if (this.status) {
			// 创建准备返回的menu
			UrlMenu menu = new UrlMenu();
			menu.setData(assembleMunuData("9000"));
			menu.setMessage(this.message);
			return menu;
		} else {
			UrlMenu menu = new UrlMenu();
			menu.setStatus("0");
			menu.setMessage(this.message);
			return menu;
		}
	}

	/**
	 * 返回拼装的菜单map
	 * 
	 * @return
	 */
	public Map getMenuMap() {
		// 根据list个数来创建map初始化个数，提高性能
		int num = this.menuItems.size();
		// 制作一个map用来快速查找
		Map<String, MenuItemBase> map = new HashMap<String, MenuItemBase>(num);
		// 遍历list为map赋值
		for (MenuItemBase item : this.menuItems) {
			String keyName = item.getMod_id();
			map.put(keyName, item);
		}
		return map;
	}

	private List<MenuItemBase> assembleMenuData()
	{
		//需要返回的结果
		List<MenuItemBase> result = new ArrayList<>();
		int num = 0;
		for (MenuItemBase item : this.menuItems) {
			String target = item.getText();
			String[] targetArray = target.split("\\.");
			if(result.size()==0)
			{
				MenuItemBase itemBaseInner = new MenuItemBase();
				itemBaseInner.setText(targetArray[0]);
				handleMenuItem(itemBaseInner, 1, targetArray, targetArray[1]);
				result.add(itemBaseInner);
			}
			else
			{
				if(targetArray[0].equals(result.get(num).getText()))
				{
					handleMenuItem(result.get(num), 1, targetArray, targetArray[1]);
				}
				else
				{
					MenuItemBase itemBaseInner = new MenuItemBase();
					itemBaseInner.setText(targetArray[0]);
					handleMenuItem(itemBaseInner, 1, targetArray, targetArray[1]);
					result.add(itemBaseInner);
					num++;
				}
			}
		}
		return result;
	}
	
	public void handleMenuItem(MenuItemBase itemBase,int i,String[] targetArray,String currentStr)
	{
		boolean match = false;
		for(int j=0;j<itemBase.getChildren().size();j++)
		{
			if(itemBase.getChildren().get(j).getText().equals(currentStr))
			{
				match = true;
				handleMenuItem(itemBase.getChildren().get(j), i+1, targetArray, targetArray[i+1]);
				break;
			}
		}
//		for(MenuItemBase itemBaseInner : itemBase.getChildren())
//		{
//			if(itemBaseInner.getText().equals(currentStr))
//			{
//				match = true;
//				handleMenuItem(itemBaseInner, i, targetArray, targetArray[i+1]);
//				break;
//			}
//		}
		if(!match)
		{
			MenuItemBase itemBaseInner = new MenuItemBase();
			itemBaseInner.setText(currentStr);
			itemBase.getChildren().add(itemBaseInner);
			if(i==targetArray.length-1)
			{
				
			}
			else
			{
				handleMenuItem(itemBaseInner, i+1, targetArray, targetArray[i+1]);
			}
		}
	}
	/**
	 * 组装要返回的菜单数据
	 * 
	 * @return
	 */
	private List<MenuItemBase> assembleMunuData() {
		// 根据list个数来创建map初始化个数，提高性能
		int num = this.menuItems.size();
		// 制作一个map用来快速查找
		Map<String, MenuItemBase> map = new HashMap<String, MenuItemBase>(num);
		// 遍历list为map赋值
		for (MenuItemBase item : this.menuItems) {
			String keyName = item.getMod_id();
			map.put(keyName, item);
		}
		// 创建一个list用来存储第一级菜单
		List<String> menuItemNum = new ArrayList<String>();
		// 根据item的parentid在对应id的menuitem下插入孩子节点
		for (MenuItemBase item : this.menuItems) {
			// 若自己的id与父id相同则认为是第一级菜单
			if (item.getParent_id().equals(item.getMod_id())) {
				menuItemNum.add(item.getMod_id());
			}
			// 若找不到父节点也认为是第一层
			else if (map.get(item.getParent_id()) == null) {
				menuItemNum.add(item.getMod_id());
			}
			// 不相同则为其寻找父节点
			else {
				map.get(item.getParent_id()).getChildren().add(item);
			}
		}

		List<MenuItemBase> assembledMenuItem = new ArrayList<MenuItemBase>(
				menuItemNum.size());
		for (String menuName : menuItemNum) {
			assembledMenuItem.add(map.get(menuName));
		}
		return assembledMenuItem;
	}

	/**
	 * 获取指定rootId下的菜单列表
	 * 
	 * @param rootId
	 * @return
	 */
	private List<MenuItemBase> assembleMunuData(String rootId) {
		// 根据list个数来创建map初始化个数，提高性能
		int num = this.menuItems.size();
		// 制作一个map用来快速查找
		Map<String, MenuItemBase> map = new HashMap<String, MenuItemBase>(num);
		// 遍历list为map赋值
		for (MenuItemBase item : this.menuItems) {
			String keyName = item.getMod_id();
			map.put(keyName, item);
		}
		// 根据item的parentid在对应id的menuitem下插入孩子节点
		for (MenuItemBase item : this.menuItems) {
			// 若自己的id与父id相同则认为是第一级菜单
			if (item.getParent_id().equals(item.getMod_id())) {

			}
			// 若找不到父节点也认为是第一层
			else if (map.get(item.getParent_id()) == null) {

			}
			// 不相同则为其寻找父节点
			else {
				map.get(item.getParent_id()).getChildren().add(item);
			}
		}
		// 从map中取出id为9000，即资源管理系统菜单,并取其孩子节点即为菜单
		List<MenuItemBase> children = map.get(rootId).getChildren();
		return children;
	}

	/**
	 * 组装selectable的menu
	 * 
	 * @return
	 */
	private List<MenuItemBase> assembleSelectableMunuData() {
		// 根据list个数来创建map初始化个数，提高性能
		int num = this.menuItems.size();
		// 制作一个map用来快速查找
		Map<String, MenuItemBase> map = new HashMap<String, MenuItemBase>(num);
		// 遍历list为map赋值
		for (MenuItemBase item : this.menuItems) {
			String keyName = item.getMod_id();
			map.put(keyName, item);
		}
		// 创建一个list用来存储第一级菜单
		List<String> menuItemNum = new ArrayList<String>();
		// 根据item的parentid在对应id的menuitem下插入孩子节点
		for (MenuItemBase item : this.menuItems) {
			// 若自己的id与父id相同则认为是第一级菜单
			if (item.getParent_id().equals(item.getMod_id())) {
				menuItemNum.add(item.getMod_id());
			}
			// 若找不到父节点也认为是第一层
			else if (map.get(item.getParent_id()) == null) {
				menuItemNum.add(item.getMod_id());
			}
			// 不相同则为其寻找父节点
			else {
				map.get(item.getParent_id()).getChildren().add(item);
				((SelectableMenuItem)map.get(item.getParent_id())).setState(false);
			}
		}

		List<MenuItemBase> assembledMenuItem = new ArrayList<MenuItemBase>(
				menuItemNum.size());
		for (String menuName : menuItemNum) {
			assembledMenuItem.add(map.get(menuName));
		}
		return assembledMenuItem;
	}
}
