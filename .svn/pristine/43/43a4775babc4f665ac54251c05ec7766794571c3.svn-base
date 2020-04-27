
package com.hcis.ipr.core.web.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.utils.DateEditor;

public abstract class BaseController extends com.hcis.ipanther.core.web.controller.BaseController{
	
	@InitBinder
	protected void initBinder(HttpServletRequest request,
	                              ServletRequestDataBinder binder) throws Exception {
	    //对于需要转换为Date类型的属性，使用DateEditor进行处理
	    binder.registerCustomEditor(Date.class, new DateEditor());
	}
	
	/**
	 * 截取请求的最末端URL
	 * 如请求/survey/preview.do
	 * 返回preview
	 * @return
	 */
	protected String getRequestAction() {
		String uri = request.getRequestURI();
		return uri.substring(uri.lastIndexOf("/")+1,uri.indexOf(".do"));
	}
	
	/**
	 * 得到当前用户
	 * @return
	 */
	protected LoginUser getLoginUser() {
		return LoginUser.loginUser(request);
	}
	
	/**
	 * 个人端 判断当前请求是否无封套
	 * @return
	 */
	protected boolean isNoskin() {
		return isSpace()&&request.getRequestURI().indexOf("/noskin/")>0;
	}
	
	/**
	 * 个人端 判断当前请求是否是个人端的
	 * @return
	 */
	protected boolean isSpace() {
		return request.getRequestURI().indexOf("/space/")==0;
	}
}

