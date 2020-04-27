package com.hcis.ipanther.common.login.web;

import com.hcis.ipanther.common.login.service.ILoginLogService;
import com.hcis.ipanther.common.login.vo.LoginLog;
import com.hcis.ipanther.core.web.controller.BaseController;

/**
 * 
 * @author Chaos
 * @date 2013-3-9
 * @time ����11:49:53
 *
 */
public class LoginLogAction extends BaseController {

	private static final long serialVersionUID = 5361532977277275981L;


	private ILoginLogService loginLogService;
	
	private LoginLog loginLog;
	
	public String listLoginLog(){
		return "SUCCESS";
	}
	
	public String goClearLoginLog(){
		return null;
	}
	
	public void clearLoginLog(){
	}


	public ILoginLogService getLoginLogService() {
		return loginLogService;
	}

	public void setLoginLogService(ILoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	public LoginLog getLoginLog() {
		return loginLog;
	}

	public void setLoginLog(LoginLog loginLog) {
		this.loginLog = loginLog;
	}

}
