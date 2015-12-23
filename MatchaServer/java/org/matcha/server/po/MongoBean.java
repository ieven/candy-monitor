package org.matcha.server.po;

public class MongoBean {
	private String target;
	
	private String value;
	
	private String timestamp;

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public MongoBean withTarget(String target)
	{
		setTarget(target);
		return this;
	}
	
	public MongoBean withValue(String value)
	{
		setValue(value);
		return this;
	}
	
	public MongoBean withTimestamp(String timestamp)
	{
		setTimestamp(timestamp);
		return this;
	}
}
