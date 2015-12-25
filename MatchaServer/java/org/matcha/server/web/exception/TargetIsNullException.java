package org.matcha.server.web.exception;

import com.yuxindata.iomp.exception.IompNestedCheckException;

public class TargetIsNullException extends IompNestedCheckException{

	public TargetIsNullException(String msg, Throwable e) {
		super(msg, e);
		// TODO Auto-generated constructor stub
	}

	public TargetIsNullException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
