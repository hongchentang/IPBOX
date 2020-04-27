package com.hcis.ipr.wechat.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hcis.ipanther.core.utils.JSONUtils;

/**
 * 响应相关工具类
 * 从 com.haoyu.sip.core.web.AbstractBaseController 拷贝过来
 * @author wuwentao
 * @date 2016年6月7日
 */
public class ResponseUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(ResponseUtils.class);

	/**
	 * 直接输出内容的简便函数.
	 */
	public static String render(String text, String contentType,HttpServletResponse response) {
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
	public static String renderText(String text,HttpServletResponse response) {
		return render(text, "text/plain;charset=UTF-8",response);
	}
	
	/**
	 * 直接输出UTF-8字符串.
	 */
	public static String renderText(int number,HttpServletResponse response) {
		return render(String.valueOf(number), "text/plain;charset=UTF-8",response);
	}
	
	/**
	 * 将对象以转换成json输出
	 * @param obj
	 * @param response
	 * @return
	 */
	public static String renderJson(Object obj,HttpServletResponse response) {
		return render(String.valueOf(JSONUtils.getJSONString(obj)), "text/plain;charset=UTF-8",response);
	}
	
	/**
	 * 直接输出字符串UTF-8编码
	 * @param html
	 * @return
	 */
	public static String renderHtml(String html,HttpServletResponse response) {
		return render(html, "text/html;charset=UTF-8",response);
	}

	/**
	 * 直接输出XML.
	 */
	public static String renderXML(String xml,HttpServletResponse response) {
		return render(xml, "text/xml;charset=UTF-8" ,response);
	}
}
