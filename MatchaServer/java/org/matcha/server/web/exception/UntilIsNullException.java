package org.matcha.server.web.exception;

import com.yuxindata.iomp.exception.IompNestedCheckException;

public class UntilIsNullException extends IompNestedCheckException{

	public UntilIsNullException(String msg, Throwable e) {
		super(msg, e);
		// TODO Auto-generated constructor stub
	}

	public UntilIsNullException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
