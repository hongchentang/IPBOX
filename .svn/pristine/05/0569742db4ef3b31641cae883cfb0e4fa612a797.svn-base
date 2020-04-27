package com.hcis.ipanther.common.login.web;

import com.haoyu.ipanther.common.login.service.IUserLoginLogService;
import com.hcis.ipanther.common.privilege.shiro.IShiroLoginService;
import com.hcis.ipanther.common.privilege.shiro.captcha.CaptchaFormAuthenticationFilter;
import com.hcis.ipanther.common.privilege.shiro.concurrent.ConcurrentAccessConfig;
import com.hcis.ipanther.common.privilege.shiro.concurrent.ConcurrentAccessService;
import com.hcis.ipanther.common.utils.ApiCode;
import com.hcis.ipanther.common.utils.BaseApi;
import com.hcis.ipanther.core.utils.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.service.ILoginService;
import com.hcis.ipanther.core.web.controller.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("")
public class LoginAction extends BaseController {

	@Autowired
	private ILoginService loginService;
	@Autowired
	private IShiroLoginService shiroLoginService;
	@Autowired
	private IUserLoginLogService userLoginLogService;

	
	@RequestMapping("entrance")
	public ModelAndView entrance(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("entrance");
		return mv;
	}
	
	/**
	 * 登录时避免globalRegions丢失，以免丢失当前子站点的信息
	 * @return
	 */
	@RequestMapping("login")
	public ModelAndView login(){
			ModelAndView mav = new ModelAndView();
		String globalRegions = (String) request.getSession().getAttribute("globalRegions");
		//防止用户已经登录再次登录时无法正确跳转
		Subject subject=SecurityUtils.getSubject();
		if(null!=subject) {
			subject.logout();
		}
		mav.setViewName("/login");
		request.getSession().setAttribute("globalRegions",globalRegions);
		return mav;
	}

	@RequestMapping("/loginV2")
	@ResponseBody
	public BaseApi loginSuccess(HttpServletRequest request, HttpServletResponse response){
		BaseApi api = new BaseApi();

		CaptchaFormAuthenticationFilter formAuthenticationFilter = new CaptchaFormAuthenticationFilter();
		formAuthenticationFilter.setShiroLoginService(shiroLoginService);
		formAuthenticationFilter.setUserLoginLogService(userLoginLogService);

		try {
			request.setAttribute("isVueRequest", true);
			formAuthenticationFilter.executeLogin(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			api.setCode(ApiCode.ERROR);
		}
		String errorMsg = (String) request.getAttribute("errorMsg");
		if(StringUtils.isNotEmpty(errorMsg)){
			api.setCode(ApiCode.ERROR);
			api.setMsg(errorMsg);
		}
		return api;
	}
	
	/**
	 * 登录时避免globalRegions丢失，以免丢失当前子站点的信息
	 * @return
	 */
	@RequestMapping("logout")
	public ModelAndView logout(){
		String globalRegions = (String) request.getSession().getAttribute("globalRegions");
		Subject subject=SecurityUtils.getSubject();
		subject.logout();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:login.do");
		request.getSession().setAttribute("globalRegions",globalRegions);
		return mav;
	}

	@RequestMapping("logoutV2")
	@ResponseBody
	public BaseApi logoutV2(){
		BaseApi api = new BaseApi();
		try {
			Subject subject=SecurityUtils.getSubject();
			subject.logout();
		}catch (Exception e){
			api.setError(e.getMessage());
		}
		return api;
	}

}
