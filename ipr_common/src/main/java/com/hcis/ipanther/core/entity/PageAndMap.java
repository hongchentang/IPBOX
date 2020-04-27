/*************************************************
*PageAndList.java
*
*2013-11-22
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.hcis.ipanther.core.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.hcis.ipanther.core.page.Pagination;

/**
 *
 *
 *@author 梁华璜
 */
public class PageAndMap<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Map<String,T> resultMap;
	
	private Pagination pagination;
	
	private T t;
	
	public PageAndMap(){
		
	}
	
	public PageAndMap(Map<String,T> resultMap,Pagination pagination){
		this.resultMap = resultMap;
		this.pagination = pagination;
	}
	
	public PageAndMap(Map<String,T> resultMap,Pagination pagination,T t){
		this.resultMap = resultMap;
		this.pagination = pagination;
		this.t = t;
	}

	public Map<String,T> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String,T> resultMap) {
		this.resultMap = resultMap;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

}
