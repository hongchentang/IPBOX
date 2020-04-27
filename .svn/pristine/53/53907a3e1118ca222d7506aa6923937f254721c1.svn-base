package com.hcis.ipanther.common.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hcis.ipanther.common.user.service.WechatUserService;
import com.hcis.ipanther.common.utils.ApiCode;
import com.hcis.ipanther.common.utils.BaseApi;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;

import java.util.List;
import java.util.Map;

@Controller("common_userMyInfoController")
@RequestMapping("/common/user")
public class UserMyInfoController  extends BaseController {

	@Autowired
	private IUserService  userService;
	@Autowired
	private WechatUserService wechatUserService;

	//跳转修改密码页面(在用)
	@RequestMapping("/goChangePassword")
	public ModelAndView changePassword(HttpServletRequest request,HttpServletResponse response) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		ModelAndView mv = this.newModelAndView();
		User user=userService.read(loginUser.getId());
		mv.addObject("user", user);
		mv.setViewName("/common/user/changePassword");
		return mv;
	}

	//跳转绑定微信(在用)
	@RequestMapping("/bindWeChat")
	public ModelAndView bindWeChat(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/common/user/bindWeChat");
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		//查询提交了申请的微信用户
		List<Map> list = userService.selectWaitBind(loginUser.getId());
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping("/bindOrUnBind")
	@ResponseBody
	public BaseApi bindOrUnBind(String id){
		BaseApi api = new BaseApi();
		try {
			wechatUserService.bindOrUnBind(id);
		}catch (Exception e){
			api.setError(e.getMessage());
		}
		return api;
	}

	//保存修改密码(在用)
	@RequestMapping("/changePasswordSave")
	@ResponseBody
	public AjaxReturnObject changePasswordSave(@FormModel("user") User user,HttpServletRequest request,HttpServletResponse response) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPasswordPlain().getBytes()));
		int count=userService.update(user, loginUser.getId());
		return AjaxReturnObject.newDefaultAjaxReturnObject(count);
	}

	//保存修改密码(在用)
	@RequestMapping("/changePasswordSaveV2")
	@ResponseBody
	public BaseApi changePasswordSaveV2(@RequestBody User user) {
		BaseApi api = new BaseApi();
		try {
			LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			user.setPassword(DigestUtils.md5DigestAsHex(user.getPasswordPlain().getBytes()));

			String oldPsd = loginUser.getPasswordPlain();
			if(!oldPsd.equals(user.getOldPsd())){
				api.setError("原密码输入不正确!");
				return api;
			}
			if(oldPsd.equals(user.getPasswordPlain())){
				api.setError("密码不能和修改前一样！");
				return api;
			}
			int count = userService.update(user, loginUser.getId());
			loginUser.setPasswordPlain(user.getPasswordPlain());
			if(count != 1){
				api.setError("没有修改的数据！");
				return api;
			}
		} catch (Exception e) {
			api.setError(e.getMessage());
		}
		return api;
	}

	//个人信息修改页面(在用)
	@RequestMapping("/goChangeUserInfo")
	public ModelAndView userInfo(@FormModel("user") User user,HttpServletRequest request,HttpServletResponse response) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		ModelAndView mv = this.newModelAndView();
		user=userService.read(loginUser.getId());
		mv.addObject("user",user );
		mv.setViewName("/common/user/changeUserInfo");
		return mv;
	}

	@RequestMapping("/userInfo")
	@ResponseBody
	public BaseApi userInfoV2(){
		BaseApi api = new BaseApi();
		try {
			LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			api.setData(userService.read(loginUser.getId()));
		}catch (Exception e){
			logger.error(e.getMessage());
			api.setMsg(e.getMessage());
			api.setCode(ApiCode.ERROR);
		}
		return api;
	}

	//个人信息保存(在用)
	@RequestMapping("/changeUserInfoSave")
	@ResponseBody
	public AjaxReturnObject changeUserInfoSave(@FormModel("user") User user,HttpServletRequest request,HttpServletResponse response) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		int count=userService.update(user, loginUser.getId());
		return AjaxReturnObject.newDefaultAjaxReturnObject(count);
	}

	//个人信息保存(在用)
	@RequestMapping("/changeUserInfoSaveV2")
	@ResponseBody
	public BaseApi changeUserInfoSaveV2(@RequestBody User user) {
		BaseApi api = new BaseApi();
		try {
			LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			int count = userService.update(user, loginUser.getId());
			if(count != 1){
				api.setError("没有修改的数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			api.setCode(ApiCode.ERROR);
			api.setMsg(e.getMessage());
		}
		return api;
	}

}
