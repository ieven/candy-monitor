package org.matcha.server.web.menu;

import java.util.List;

import org.matcha.server.web.menu.base.MenuItemBase;


public class MenuFactory {
	
	/**
	 * 获取带url菜单
	 * @param menuList
	 * @param status
	 * @param message
	 * @return
	 */
	public static UrlMenu createUrlMenu(List<MenuItemBase> menuList,boolean status,String message)
	{
		MenuAssemble menuAssemble = new MenuAssemble(menuList, status,message);
		return menuAssemble.getUrlMenu();
	}

}
