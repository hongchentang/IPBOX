/**
 * 
 */
package com.hcis.ipanther.common.i18n.utils;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;

import com.hcis.ipanther.core.utils.BeanLocator;

/**
 * @author Administrator
 *
 */
public class I18nUtils {
	
	public static Logger logger=LoggerFactory.getLogger(I18nUtils.class);
	
	public static Locale DEFAULT_LOCALE=Locale.SIMPLIFIED_CHINESE;
	
	/**
	 * 获取语言
	 * @param request
	 * @return
	 */
	public static Locale getLocale(HttpServletRequest request){
		LocaleResolver localeResolver=(LocaleResolver)BeanLocator.getBean("localeResolver");
		Locale locale=localeResolver.resolveLocale(request);
		return locale;
	}
	
	/**
	 * 设置语言
	 * 由于<spring:message>标签是从Spring RequestContext中获取，最终是存放在request中，所以必须保存到里面
	 * 每次请求都要设置一次，所以必须要一个过滤器来配合，见com.hcis.ipanther.common.i18n.utils.I18nFilter
	 * @param request
	 * @param response
	 * @param localeString
	 */
	public static void setLocale(HttpServletRequest request,HttpServletResponse response,String localeString){
		try{
			Locale locale=LocaleUtils.toLocale(localeString);
			LocaleResolver localeResolver=(LocaleResolver)BeanLocator.getBean("localeResolver");
			localeResolver.setLocale(request, response, locale);
			request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE, localeResolver);
		}
		catch(Exception e){
			logger.error(I18nUtils.class.getName(),e);
		}
	}
	
	public static String getMessage(HttpServletRequest request,String code,String[] args){
		return I18nUtils.getMessageLocal(code, args, getLocale(request));
	}
	
	public static String getMessageLocal(String code,String[] args,Locale locale){
		ReloadableResourceBundleMessageSource messageBundle=(ReloadableResourceBundleMessageSource)BeanLocator.getBean("messageSource");
		return messageBundle.getMessage(code, args, locale);
	}
	

}
