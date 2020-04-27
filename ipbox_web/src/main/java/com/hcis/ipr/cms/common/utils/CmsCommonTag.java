package com.hcis.ipr.cms.common.utils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;



/**
 * 标签公用
 * 2011-4-15
 * 
 * @author freeteam
 */
public class CmsCommonTag extends TagSupport{

	private static final long serialVersionUID = -4177412038871616265L;
	
	public HttpServletRequest getRequest(){
		return (HttpServletRequest)pageContext.getRequest();
	}
	public HttpSession getSession(){
		return getRequest().getSession();
	}
}