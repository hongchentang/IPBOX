/**
 * 
 */
package com.hcis.ipanther.common.i18n.utils;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

/**
 * @author Chaos
 * 多国语言过滤器
 * 
 * 
	<!-- 多国语言过滤器 -->
	<filter>
		<filter-name>I18nFilter</filter-name>
		<filter-class>com.hcis.ipanther.common.i18n.utils.I18nFilter</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>I18nFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
 *
 */
public class I18nFilter implements Filter {
	
	private String localeParamName="locale";

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		String localeParam=request.getParameter(this.localeParamName);

		//切换语言
		if(StringUtils.isNotBlank(localeParam)){
			I18nUtils.setLocale(request,response, localeParam);
		}
		else{
			Locale local=I18nUtils.getLocale(request);
			I18nUtils.setLocale(request,response, local.toString());
		}
		
		filterChain.doFilter(servletRequest, servletResponse);
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		String localeParamNameConfig=config.getInitParameter("localeParamName");
		if(StringUtils.isNotBlank(localeParamNameConfig)){
			this.localeParamName=localeParamNameConfig;
		}
	}

}
