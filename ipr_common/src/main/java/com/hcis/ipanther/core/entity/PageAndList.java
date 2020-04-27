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

import com.hcis.ipanther.core.page.Pagination;

/**
 *
 *
 *@author 梁华璜
 */
public class PageAndList<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<T> resultList;
	
	private Pagination pagination;
	
	private T t;
	
	public PageAndList(){
		
	}
	
	public PageAndList(List<T> resultList,Pagination pagination){
		this.resultList = resultList;
		this.pagination = pagination;
	}
	
	public PageAndList(List<T> resultList,Pagination pagination,T t){
		this.resultList = resultList;
		this.pagination = pagination;
		this.t = t;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
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
