package com.hcis.ipanther.core.web.vo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hcis.ipanther.core.page.IPage;
import com.hcis.ipanther.core.page.Pagination;


/**
 * 客户端请求参数对象
 */
public class SearchParam implements IPage{
	
	private static final long serialVersionUID = 8081267402507908402L;
	
	private Pagination pagination;
	
	private Map<String,Object> paramMap;
	
	public SearchParam(){
		this.paramMap = new HashMap<String,Object>();
	}
	
	public SearchParam(boolean pageAvailable){
		this.paramMap = new HashMap<String,Object>();
		if(pagination==null){
			pagination  =new Pagination();
		}
		pagination.setAvailable(pageAvailable);
	}
	
	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> param) {
		this.paramMap = param;
	}

	public void setPageAvailable(boolean available){
		if(pagination==null){
			pagination  =new Pagination();
		}
		pagination.setAvailable(available);
	}

	public Pagination getPagination() {
		return pagination;		
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	
		
}
