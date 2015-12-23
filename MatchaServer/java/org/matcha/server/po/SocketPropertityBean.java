package org.matcha.server.po;

public class SocketPropertityBean {
	private int port = 0;
	
	private int backlog = 0;
	
	private String ipAddress;

	private CreateSocketType createSocketType;
	
	public CreateSocketType getCreateSocketType() {
		return createSocketType;
	}

	public void setCreateSocketType(CreateSocketType createSocketType) {
		this.createSocketType = createSocketType;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getBacklog() {
		return backlog;
	}

	public void setBacklog(int backlog) {
		this.backlog = backlog;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public SocketPropertityBean withPort(int port)
	{
		setPort(port);
		return this;
	}
	
	public SocketPropertityBean withBacklog(int backlog)
	{
		setBacklog(backlog);
		return this;
	}
	
	public SocketPropertityBean withIpAddress(String ipAddress)
	{
		setIpAddress(ipAddress);
		return this;
	}
	
	public SocketPropertityBean withCreateSocketType(CreateSocketType createSocketType)
	{
		setCreateSocketType(createSocketType);
		return this;
	}
}
