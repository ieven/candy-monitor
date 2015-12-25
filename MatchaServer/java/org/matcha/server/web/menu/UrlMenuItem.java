package org.matcha.server.web.menu;

import org.matcha.server.web.menu.base.MenuItemBase;

public class UrlMenuItem extends MenuItemBase{
	/**
	 * 菜单图标
	 */
	private String icon;
	/**
	 * 菜单url
	 */
	private String url;
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
