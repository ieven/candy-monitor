package org.matcha.server.web.po;

public class Data {
	private String value;
	private String timestamp;
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
	public Data withValue(String value)
	{
		setValue(value);
		return this;
	}
	
	public Data withTimestamp(String timestamp)
	{
		setTimestamp(timestamp);
		return this;
	}
}
