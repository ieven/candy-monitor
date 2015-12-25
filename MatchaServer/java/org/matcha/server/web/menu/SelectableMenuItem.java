package org.matcha.server.web.menu;

import org.matcha.server.web.menu.base.MenuItemBase;
import org.matcha.server.web.menu.base.State;

public class SelectableMenuItem extends MenuItemBase{
	
	/**
	 * jstree中的id
	 */
	private String data;
	/**
	 * 菜单类型（view、home）
	 */
	private String mod_type;

	/**
	 * 是否展示出来
	 */
	private String mod_display;
	/**
	 * 同级菜单中位于的先后顺序
	 */
	private String list_id;
	/**
	 * 菜单当前状态
	 */
	private State state = new State();
	
	public void setState(boolean selected)
	{
		this.state.setSelected(selected);
	}
	public State getState()
	{
		return this.state;
	}
	
	public String getMod_type() {
		return mod_type;
	}
	public void setMod_type(String mod_type) {
		this.mod_type = mod_type;
	}
	public String getMod_display() {
		return mod_display;
	}
	public void setMod_display(String mod_display) {
		this.mod_display = mod_display;
	}
	public String getList_id() {
		return list_id;
	}
	public void setList_id(String list_id) {
		this.list_id = list_id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
