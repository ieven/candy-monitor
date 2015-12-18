package org.matcha.po;

public class BasicDataBean {
	
	private String key;
	
	private String timestamp;
	
	private String value;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public BasicDataBean withTimestamp(String timestamp)
	{
		setTimestamp(timestamp);
		return this;
	}
	
	public BasicDataBean withValue(String value)
	{
		setValue(value);
		return this;
	}
	
	public BasicDataBean withKey(String key)
	{
		setKey(key);
		return this;
	}
}
