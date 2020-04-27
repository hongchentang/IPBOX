package com.hcis.ipanther.core.exception;

/**
 * 基础框架异常基类
 */
public class IPantherException extends RuntimeException {

	private static final long serialVersionUID = -1L;
	
	/*
	 * 异常代码（可让异常处理程序根据不同的代码转入到不同的提示页面） 
	 */
	private String exCode;
	
	// 为了处理自己的特殊业务对象
	private Object obj;
	
	public IPantherException() {
		super();
	}
	
	public IPantherException(String msg) {
		super(msg);
	}
	
	public IPantherException(Throwable ex) {
		super(ex);
	}
	
	public IPantherException(String msg, Throwable ex) {
		super(msg, ex);
	}

	public IPantherException(Object o) {
		super("");
		this.obj = o;
	}

	
	public String getExCode() {
		return exCode;
	}

	public void setExCode(String exCode) {
		this.exCode = exCode;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
