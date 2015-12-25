package org.matcha.server.web.menu.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单标签类，目前只需设置text和mod_id、list_id、parent_id、state
 * @author lichong
 *
 */
public class MenuItemBase {
	/**
	 * 菜单id
	 */
	protected String mod_id;
	/**
	 * 父节点id
	 */
	protected String parent_id;
	
	/**
	 * 菜单显示
	 */
	protected String text;
	/**
	 * 子节点
	 */
	protected List<MenuItemBase> children = new ArrayList<MenuItemBase>();
	
	public List<MenuItemBase> getChildren() {
		return children;
	}
	public void setChildren(List<MenuItemBase> children) {
		this.children = children;
	}
	public String getMod_id() {
		return mod_id;
	}
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
