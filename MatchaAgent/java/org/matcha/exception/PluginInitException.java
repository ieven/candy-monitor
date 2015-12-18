package org.matcha.exception;

import com.yuxindata.iomp.exception.IompNestedRuntimeException;

public class PluginInitException extends IompNestedRuntimeException{

	public PluginInitException(String msg, Throwable cause) {
		super(msg, cause);
		// TODO Auto-generated constructor stub
	}

	public PluginInitException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
}
