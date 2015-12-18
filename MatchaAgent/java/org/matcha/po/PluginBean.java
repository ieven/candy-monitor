package org.matcha.po;

public class PluginBean {
	private String name;
	
	private String des;
	
	private String clazz;
	
	private String[] cron;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String[] getCron() {
		return cron;
	}

	public void setCron(String[] cron) {
		this.cron = cron;
	}

	public PluginBean withName(String name)
	{
		setName(name);
		return this;
	}
	
	public PluginBean withDes(String des)
	{
		setDes(des);
		return this;
	}
	
	public PluginBean withClazz(String clazz)
	{
		setClazz(clazz);
		return this;
	}
	
	public PluginBean withCron(String[] cron)
	{
		setCron(cron);
		return this;
	}
}
