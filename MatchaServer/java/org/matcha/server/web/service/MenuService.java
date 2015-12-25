package org.matcha.server.web.service;

import java.util.ArrayList;
import java.util.List;

import org.matcha.server.web.dao.MenuDao;
import org.matcha.server.web.menu.MenuFactory;
import org.matcha.server.web.menu.UrlMenu;
import org.matcha.server.web.menu.base.MenuItemBase;

public class MenuService {
	
	private static final String TABLENAME = "initial_data";
	
	public UrlMenu menuList()
	{
		MenuDao dao = new MenuDao(TABLENAME);
		List<String> list = dao.findMenuData();
		List<MenuItemBase> menuList = new ArrayList<MenuItemBase>(list.size());
		for(String text:list)
		{
			MenuItemBase itemBase = new MenuItemBase();
			itemBase.setText(text);
			menuList.add(itemBase);
		}
		return MenuFactory.createUrlMenu(menuList, true, "");
		
	}
}
