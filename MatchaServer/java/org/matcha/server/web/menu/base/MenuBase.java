package org.matcha.server.web.menu.base;

import java.util.List;

public class MenuBase {
	
	/**
	 * 返回菜单状态
	 * 1是OK
	 * 0是error
	 * 默认不提供为1
	 */
	private String status = "1";
	private List<MenuItemBase> data;
	private String message;
	public String getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<MenuItemBase> getData() {
		return data;
	}
	public void setData(List<MenuItemBase> dataList) {
		this.data = dataList;
	}
	
}
