package com.hcis.ipanther.common.login.utils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.utils.Constants;
 
@Component
public class LoginUtils {
	
	@PostConstruct
	public void init() throws Exception {}
	
	public final static void LoginMessage(HttpServletRequest request,String msg){
		HttpSession session=request.getSession();
		session.setAttribute(LoginConstants.LOGIN_MESSAGE,msg);
	}
	
	public final static void LoginInfoClear(HttpServletRequest request){
		HttpSession session=request.getSession();
		session.setAttribute(LoginConstants.LOGIN_USER,null);
	}
	
	public final static String getIpAddr(HttpServletRequest request)  {
		String ip = request.getHeader("X-Real-IP");
		if (StringUtils.isEmpty(ip)||ip.contains("unknown")) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (StringUtils.isEmpty(ip)||ip.contains("unknown")) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip)||ip.contains("unknown")) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip)||ip.contains("unknown")) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static LoginUser getLoginUser(HttpServletRequest request){
		if(request!=null){
			HttpSession session=request.getSession();
			LoginUser loginUser= (LoginUser) session.getAttribute(LoginConstants.LOGIN_USER);
			if(loginUser!=null&&(!loginUser.equals(null))){
				return loginUser;
			}
		}
		return null;
	}
}
