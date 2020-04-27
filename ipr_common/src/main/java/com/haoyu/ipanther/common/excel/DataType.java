package com.haoyu.ipanther.common.excel;

/**
 * 字段数据类型
 * @author lianghuahuang
 *
 */
public enum DataType {
	/**
	 * 日期
	 */
	DATE("date"),
	/**
	 * EMAIL
	 */
	EMAIL("email"),
	/**
	 * 身份证号
	 */
	ID_CARD("idCard"),
	/**
	 * 手机号
	 */
	MOBILE_PHONE("mobilePhone"),
	/**
	 * 数字
	 */
	NUMERIC("numeric"),
	/**
	 * 必填
	 */
	REQURIED("required"),
	/**
	 * 下拉选择
	 */
	OPTIONS("options");
	
	private String name;
	
	private DataType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
}
