/*************************************************
 Copyright (C), 2012
 Author:梁华璜
 Version:
 Date: 2013-8-23
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
package com.hcis.ipanther.core.web.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.security.entity.Loginer;
import com.hcis.ipanther.core.utils.DateEditor;
import com.hcis.ipanther.core.utils.JsonUtil;

public abstract class BaseController {

	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	@Resource
	protected  HttpServletRequest request;

	public static final String SESSION_LOGINER = "loginer";

	public static final String ERROR_MESSAGES = "errorMessages";

	public static final String SUCCESS_MESSAGES = "successMessages";

	@InitBinder
	protected void initBinder(HttpServletRequest request,
							  ServletRequestDataBinder binder) throws Exception {
		//对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
		//  binder.registerCustomEditor(Date.class, new DateEditor());
	}

	protected ModelAndView newModelAndView(){
		return new ModelAndView();
	}
	/**
	 * 获取当前登录用户的信息
	 * @param request
	 * @return
	 */
	protected final Loginer getLoginer(){
		if(request!=null){
			return (Loginer)request.getSession().getAttribute(SESSION_LOGINER);
		}
		return null;
	}

	/**
	 * 设置当前登录用户的信息
	 * @param request
	 * @param loginer
	 */
	protected final void setLoginer(Loginer loginer){
		if(request!=null){
			request.getSession().setAttribute(SESSION_LOGINER, loginer);
		}
	}

	protected final HttpSession getSession(){
		if(request!=null){
			return request.getSession();
		}
		return null;
	}

	/***
	 * 设置request属性
	 * 等同于request.setAttribute
	 * @param name
	 * @param value
	 * @return this
	 */
	public BaseController setAttr(String name, Object value) {
		request.setAttribute(name, value);
		return this;
	}

	/**
	 * 删除request属性
	 * 等同于request.removeAttribute
	 * @param name
	 * @return this
	 */
	public BaseController removeAttr(String name) {
		request.removeAttribute(name);
		return this;
	}


	public BaseController setAttrs(Map<String, Object> attrMap) {
		for (Map.Entry<String, Object> entry : attrMap.entrySet())
			request.setAttribute(entry.getKey(), entry.getValue());
		return this;
	}


	public String getPara(String name) {
		return request.getParameter(name);
	}


	public String getPara(String name, String defaultValue) {
		String result = request.getParameter(name);
		return result != null && !"".equals(result) ? result : defaultValue;
	}


	public Map<String, String[]> getParaMap() {
		return request.getParameterMap();
	}


	public Enumeration<String> getParaNames() {
		return request.getParameterNames();
	}


	public String[] getParaValues(String name) {
		return request.getParameterValues(name);
	}


	public Enumeration<String> getAttrNames() {
		return request.getAttributeNames();
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttr(String name) {
		return (T)request.getAttribute(name);
	}

	/**
	 * 直接输出内容的简便函数.
	 */
	protected String render(String text, String contentType,HttpServletResponse response) {
		try {
			response.setContentType(contentType);
			response.getWriter().write(text);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 直接输出字符串.
	 */
	protected String renderText(String text,HttpServletResponse response) {
		return render(text, "text/plain;charset=GBK",response);
	}

	/**
	 * 直接输出GBK字符串.
	 */
	protected String renderText(int number,HttpServletResponse response) {
		return render(String.valueOf(number), "text/plain;charset=UTF-8",response);
	}

	/**
	 * 直接输出字符串GBK编码
	 * @param html
	 * @return
	 */
	protected String renderHtml(String html,HttpServletResponse response) {
		return render(html, "text/html;charset=UTF-8",response);
	}
	/**
	 * 直接输出字符串UTF8编码.
	 */
	protected String renderHtmlUTF8(String html,HttpServletResponse response) {
		return render(html, "text/html;charset=UTF-8",response);
	}

	/**
	 * 直接输出Json字符串
	 * @param src
	 * @return
	 * @throws IOException
	 */
	protected <T> String renderJson(T src,HttpServletResponse response) throws IOException{
		return renderText(JsonUtil.toJson(src),response);
	}

	/**
	 * 直接输出XML.
	 */
	protected String renderXML(String xml,HttpServletResponse response) {
		return render(xml, "text/xml;charset=UTF-8" ,response);
	}

	@SuppressWarnings("unchecked")
	public void saveError(String error) {
		List<String> errors = (List<String>) request.getAttribute(ERROR_MESSAGES);
		if (errors == null) {
			errors = new ArrayList<String>();
		}
		errors.add(error);
		request.setAttribute("errors", errors);
	}

	@SuppressWarnings("unchecked")
	public void saveSuccess(String msg) {
		List<String> messages = (List<String>) request.getAttribute(SUCCESS_MESSAGES);
		if (messages == null) {
			messages = new ArrayList<String>();
		}
		messages.add(msg);
		request.setAttribute(SUCCESS_MESSAGES, messages);
	}

	protected Pagination getPagination() {
		return getPagination(10, false);
	}

	protected Pagination getPagination(boolean isPaging) {
		return getPagination(10, isPaging);
	}

	protected Pagination getPagination(int pageSize, boolean isPaging) {
		String pz = request.getParameter("pageSize");
		if (StringUtils.isNotEmpty(pz)) {
			pageSize = Integer.parseInt(pz);
		}
		if(pageSize == 0){
			pageSize = 10;
		}
		String currentPage = request.getParameter("currentPage");
		boolean isChange = Boolean.valueOf(request.getParameter("isChange"));
		if (!isPaging && StringUtils.isEmpty(currentPage)) {
			return null;
		}else {
			Pagination pagination = new Pagination();
			if (StringUtils.isNotEmpty(currentPage)) {
				pagination.setCurrentPage(Integer.parseInt(currentPage));
			}
			pagination.setPageSize(pageSize);
			pagination.setChange(isChange);
			request.setAttribute("pagination", pagination);
			return pagination;
		}

	}
}

