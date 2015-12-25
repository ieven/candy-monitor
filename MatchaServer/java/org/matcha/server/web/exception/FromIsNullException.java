package org.matcha.server.web.exception;

import com.yuxindata.iomp.exception.IompNestedCheckException;

public class FromIsNullException extends IompNestedCheckException{

	public FromIsNullException(String msg, Throwable e) {
		super(msg, e);
		// TODO Auto-generated constructor stub
	}

	public FromIsNullException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
