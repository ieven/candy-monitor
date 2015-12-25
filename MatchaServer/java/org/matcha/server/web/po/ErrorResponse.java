package org.matcha.server.web.po;

public class ErrorResponse {
	private String errorCode;
	
	private String msg;
	
	public ErrorResponse(String errorCode,String msg)
	{
		this.errorCode = errorCode;
		this.msg = msg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getMsg() {
		return msg;
	}
	
}
