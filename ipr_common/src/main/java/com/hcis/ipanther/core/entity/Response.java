package com.hcis.ipanther.core.entity;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.FieldError;

import com.hcis.ipanther.core.utils.JsonUtil;

public class Response implements Serializable {
	
	private static final long serialVersionUID = 6601119505899497676L;
	//响应码：00代表成功
	private String responseCode;
	//响应消息：可自定义，默认响应码00为成功
	private String responseMsg;
	//响应成功返回的数据
	private String responseData;
	
	private int statusCode;
	
	private String message;
	
	private String forwardUrl;
	
	private List<ValidFieldResult> validFieldResults;
	
	public Response(){
		
	}

	public Response(String responseCode) {
		this.responseCode = responseCode;
	}

	public Response(String responseCode, String responseMsg, String responseData) {
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
		this.responseData = responseData;
	}

	public Response(String responseCode, String responseMsg) {
		super();
		this.responseCode = responseCode;
		this.responseMsg = responseMsg;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}
	
	public static Response newInstance(String json){
		try {
			return JsonUtil.fromJson(json, Response.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Success instance. return 00
	 *
	 * @return the response
	 */
	public static Response successInstance(){
		return new Response("00");
	}
	
	/**
	 * Fail instance. return 01
	 *
	 * @return the response
	 */
	public static Response failInstance(){
		return new Response("01");
	}
	
	
	/**
	 * Success instance. return 200
	 *
	 * @return the response
	 */
	public static Response successResponse(){
		return new Response("200");
	}
	
	/**
	 * Fail instance. return 300
	 *
	 * @return the response
	 */
	public static Response failResponse(){
		return new Response("300");
	}
	
	public Response responseMsg(String responseMsg){
		this.responseMsg = responseMsg;
		return this;
	}
	
	public Response responseData(String responseData){
		this.responseData = responseData;
		return this;
	}
	
	public String toString(){
		try {
			return JsonUtil.toJson(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Checks if is success. Checked 00
	 *
	 * @return true, if is success
	 */
	public boolean isSuccess(){
		if(StringUtils.equals(this.responseCode,"00")){
			return true;
		}
		return false;
	}

	/**
	 * Checks if is success response. Checked 200
	 *
	 * @return true, if is success response
	 */
	public boolean isSuccessResponse(){
		if(StringUtils.equals(this.responseCode,"200")){
			return true;
		}
		return false;
	}
	
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
		
	 
		public Response(int statusCode,String message){
			this.statusCode = statusCode;
			this.message = message;
		}
		
		public Response statusCode(int statusCode){
			this.setStatusCode(statusCode);
			return this;
		}
		
		public Response message(String message){
			this.setMessage(message);
			return this;
		}
		
		public Response validFieldResults(List<FieldError> fieldErrors){
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
		
		public Response forwardUrl(String forwardUrl){
			this.setForwardUrl(forwardUrl);
			return this;
		}
		
		public static Response newDefaultResponse(){
			return new Response(200,"操作成功!");
		}
		
		public static Response newDefaultResponse(int updateRecCount){
			if(updateRecCount>0){
				return new Response(200,"操作成功!");
			}else{
				return new Response(300,"操作失败!");
			}
		}
		
		public static Response newDefaultResponse(int updateRecCount,String message){
			if(updateRecCount>0){
				return new Response(200,message);
			}else{
				return new Response(300,message);
			}
		}
}

