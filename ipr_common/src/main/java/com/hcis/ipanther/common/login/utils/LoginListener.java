package com.hcis.ipanther.common.login.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


import com.hcis.ipanther.common.login.service.ILoginLogService;
import com.hcis.ipanther.common.login.utils.LoginConstants;
import com.hcis.ipanther.common.login.vo.LoginLog;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.utils.BeanLocator;

/**
 * @author lianghuahuang
 *
 */
public class LoginListener implements HttpSessionListener,HttpSessionAttributeListener {
	
	public HttpServletRequest request=null;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		if(LoginConstants.LOGIN_USER.equals(event.getName())){
			ILoginLogService loginLogService = (ILoginLogService)BeanLocator.getBean("loginLogService");
			LoginUser loginUser  = (LoginUser)event.getValue();
			LoginLog loginLog=new LoginLog();
			loginLog.setUserId(loginUser.getId());
			loginLog.setSessionId(session.getId());
			loginLog.setIpAddress(loginUser.getIpAddress());
			loginLog.setOs(loginUser.getOs());
			loginLog.setCreator(loginUser.getId());
			loginLogService.addLoginLog(loginLog);
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		if(LoginConstants.LOGIN_USER.equals(event.getName())){
			ILoginLogService loginLogService = (ILoginLogService)BeanLocator.getBean("loginLogService");
			LoginUser loginUser  = (LoginUser)event.getValue();
			LoginLog loginLog=new LoginLog();
			loginLog.setUserId(loginUser.getId());
			loginLog.setSessionId(session.getId());
			loginLog.setUpdatedby(loginUser.getId());
			loginLog.setIsDeleted("Y");
			loginLogService.deleteByLogout(loginLog);
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
				
	}


}
