package org.matcha.po;

public class HostBean {
	
	private String name;
	
	private String des;
	
	private String targetIp;

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
	
	public String getTargetIp() {
		return targetIp;
	}

	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
	}

	public HostBean withName(String name)
	{
		setName(name);
		return this;
	}
	
	public HostBean withDes(String des)
	{
		setDes(des);
		return this;
	}
	
	public HostBean withTargetIp(String targetIp)
	{
		setTargetIp(targetIp);
		return this;
	}
}
