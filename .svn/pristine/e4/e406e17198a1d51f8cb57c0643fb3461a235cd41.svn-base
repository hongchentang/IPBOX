/*************************************************
Copyright (C), 2012
Author:梁华璜 
Version: 
Date: 2013-9-22
Description: // 用于详细说明此程序文件完成的主要功能，与其他模块
// 或函数的接口，输出值、取值范围、含义及参数间的控
// 制、顺序、独立或依赖等关系
Function List: // 主要函数列表，每条记录应包括函数名及功能简要说明
1. ....
History: // 修改历史记录列表，每条修改记录应包括修改日期、修改
// 者及修改内容简述
1. Date:
Author:
Modification:
2. ...
*************************************************/
package com.hcis.ipanther.core.web.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

import com.hcis.ipanther.core.utils.JsonUtil;

public class AjaxReturnObject implements Serializable {

	private static final long serialVersionUID = 4416326573100123127L;
	
	private int statusCode;
	
	private String message;
	
	private String forwardUrl;
	
	private List<ValidFieldResult> validFieldResults;
	

	//字段校验结果
	class ValidFieldResult{
		//校验的字段名称
		private String field;
		//校验结果
		private String defaultMessage;
		
		ValidFieldResult(String field,String defaultMessage){
			this.field = field;
			this.defaultMessage = defaultMessage;
		}
		
		ValidFieldResult(FieldError fieldError){
			this.field = fieldError.getField();
			this.defaultMessage = fieldError.getDefaultMessage();
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getDefaultMessage() {
			return defaultMessage;
		}

		public void setDefaultMessage(String defaultMessage) {
			this.defaultMessage = defaultMessage;
		}
	}
	
	public List<ValidFieldResult> getValidFieldResults() {
		return validFieldResults;
	}

	public void setValidFieldResults(List<ValidFieldResult> validFieldResults) {
		this.validFieldResults = validFieldResults;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
	
	public AjaxReturnObject(){
	}
	
	public AjaxReturnObject(int statusCode,String message){
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public AjaxReturnObject statusCode(int statusCode){
		this.setStatusCode(statusCode);
		return this;
	}
	
	public AjaxReturnObject message(String message){
		this.setMessage(message);
		return this;
	}
	
	public AjaxReturnObject validFieldResults(List<FieldError> fieldErrors){
		if(fieldErrors!=null&&!fieldErrors.isEmpty()){
			if(validFieldResults==null){
				validFieldResults = new ArrayList<ValidFieldResult>();
			}
			for(FieldError fe:fieldErrors){
				validFieldResults.add(new ValidFieldResult(fe));
			}
		}
		return this;
	}
	
	public AjaxReturnObject forwardUrl(String forwardUrl){
		this.setForwardUrl(forwardUrl);
		return this;
	}
	
	public AjaxReturnObject(String message){
		this.message = message;
	}
	
	public static AjaxReturnObject newDefaultAjaxReturnObject(){
		return new AjaxReturnObject(200,"操作成功!");
	}
	
	public static AjaxReturnObject newDefaultAjaxReturnObject(int updateRecCount){
		if(updateRecCount>0){
			return new AjaxReturnObject(200,"操作成功!");
		}else{
			return new AjaxReturnObject(300,"操作失败!");
		}
	}
	
	public String toString(){
		try {
			return JsonUtil.toJson(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
