package org.matcha.server.po;

public enum CreateSocketType {
	/**
	 * 只提供了端口号
	 */
	BY_PORT,
	/**
	 * 提供了端口号和backlog
	 */
	BY_PORT_BACKLOG,
	/**
	 * 提供了端口号，backlog和IP地址
	 */
	BY_PORT_BACKLOG_IP
}
