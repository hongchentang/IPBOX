/**
 * 
 */
package com.hcis.ipanther.common.login.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.hcis.ipanther.common.login.vo.LoginUser;

/**
 * @author Chaos
 * @date 2013-3-19
 * @time ����1:58:09
 *
 */
public class LoginFilter implements Filter {
	
	private String contextUrl="";
	private String loginUrl="";
	private String indexUrl="";
	private String loginSubmitUrl="";
	private String logoutUrl="";

	private String contextUrlTeacher="";
	private String loginUrlTeacher="";
	private String indexUrlTeacher="";
	private String loginSubmitUrlTeacher="";
	private String logoutUrlTeacher="";
	
	private String[] ignoreUrls=null;
	private String contentType="text/plain;charset=GBK";

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
		
		String statusCode="301";
		String message="������ʱ�������µ�½ϵͳ";
		String navTabId="";
		String rel="";
		String callbackType="";
		String forwardUrl="";
		String confirmMsg="";
		String loginPattern="?switch=login";
		
		String url=request.getRequestURI();
		boolean isLoginIn=this.isLogin(request);

		if(url.indexOf(loginUrl)>-1){
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		else if(url.indexOf(loginUrlTeacher)>-1){
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		else if(url.indexOf(loginSubmitUrl)>-1){
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		else if(url.indexOf(loginSubmitUrlTeacher)>-1){
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		else if(is_Ignore(url)){
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		else if(url.indexOf(indexUrl)>-1||url.equals(contextUrl)){
			if(!isLoginIn){
				response.sendRedirect(request.getContextPath()+loginUrl);
				return;
			}
			else{
				filterChain.doFilter(servletRequest, servletResponse);
				return;
			}
		}
		else if(url.indexOf(indexUrlTeacher)>-1||url.equals(contextUrlTeacher)){
			if(!isLoginIn){
				response.sendRedirect(request.getContextPath()+loginUrlTeacher);
				return;
			}
			else{
				filterChain.doFilter(servletRequest, servletResponse);
				return;
			}
		}
		else if(url.indexOf(logoutUrl)>-1){
			this.logout(request);
			response.sendRedirect(request.getContextPath()+loginUrl+loginPattern);
			return;
		}
		else if(url.indexOf(logoutUrlTeacher)>-1){
			this.logout(request);
			response.sendRedirect(request.getContextPath()+loginUrlTeacher+loginPattern);
			return;
		}
		else{
			if(!isLoginIn){
//				statusCode="301";
//				message="������ʱ�������µ�½ϵͳ";
//				DwzAjaxCallback dwzCallback=new DwzAjaxCallback(statusCode, message, navTabId, rel, callbackType, forwardUrl);
//				response.setContentType(contentType);
//				response.getWriter().write(DwzAjaxCallback.toJSONString(dwzCallback));
				if(url.indexOf(contextUrlTeacher)>-1){
					response.sendRedirect(request.getContextPath()+loginUrlTeacher+loginPattern);
					return;
				}
				else{
					response.sendRedirect(request.getContextPath()+loginUrl+loginPattern);
					return;
				}
			}
			else{
				filterChain.doFilter(servletRequest, servletResponse);
				return;
			}
		}
	}
	
	private boolean isLogin(HttpServletRequest request){
		HttpSession session = request.getSession();
		LoginUser user=(LoginUser)session.getAttribute(LoginConstants.LOGIN_USER);
		if(user==null){
			return false;
		}
		else{
			return true;
		}
	}
	
	private void logout(HttpServletRequest request){
		LoginUtils.LoginInfoClear(request);
	}
	
	private boolean is_Ignore(String url){
		boolean ignored=false;
		if(ArrayUtils.isNotEmpty(ignoreUrls)){
			for(String str:ignoreUrls){
				if(StringUtils.contains(url, str)){
					ignored=true;
					break;
				}
			}
		}
		return ignored;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
//		contextUrl=IConfig.getProperty(IConfigConstants.CONTEXT_URL);
//		indexUrl=IConfig.getProperty(IConfigConstants.INDEX_URL);
//		loginUrl=IConfig.getProperty(IConfigConstants.LOGIN_URL);
//		logoutUrl=IConfig.getProperty(IConfigConstants.LOGOUT_URL);
//		loginSubmitUrl=IConfig.getProperty(IConfigConstants.LOGIN_SUBMIT_URL);
//
//		contextUrlTeacher=IConfig.getProperty(IConfigConstants.CONTEXT_URL_TEACHER);
//		indexUrlTeacher=IConfig.getProperty(IConfigConstants.INDEX_URL_TEACHER);
//		loginUrlTeacher=IConfig.getProperty(IConfigConstants.LOGIN_URL_TEACHER);
//		logoutUrlTeacher=IConfig.getProperty(IConfigConstants.LOGOUT_URL_TEACHER);
//		loginSubmitUrlTeacher=IConfig.getProperty(IConfigConstants.LOGIN_SUBMIT_URL_TEACHER);
//		
//		String ignoreUrl=IConfig.getProperty(IConfigConstants.IGNORE_URL);
//		ignoreUrls=StringUtils.split(ignoreUrl, ";");
	}

}
