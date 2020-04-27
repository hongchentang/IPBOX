package com.hcis.ipanther.common.user.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.entity.User.StudentStatus;
import com.hcis.ipanther.common.user.entity.User.TeacherStatus;
import com.hcis.ipanther.common.user.entity.UserParttime;
import com.hcis.ipanther.common.user.service.IUserParttimeService;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.core.web.controller.BaseController;

@Controller("common_userParttimeController")
@RequestMapping("/common/user/parttime")
public class UserParttimeController  extends BaseController {
	
	@Autowired
	private IUserParttimeService userParttimeService;
	@Autowired
	private IUserService userService;
	
	/**
	 * 列出用户下的所有信息
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(SearchParam searchParam) {
		ModelAndView mv = this.newModelAndView();
		
		/*
		 * 控制是否可以编辑用户的信息
		 */
		String userId = (String) searchParam.getParamMap().get("userId");
		User user = userService.read(userId);
		String teacherStatus = user.getTeacherStatus();
		String studentStatus = user.getStudentStatus();
		boolean canEdit = true;
		if(TeacherStatus.AUDIT.toString().equals(teacherStatus)||StudentStatus.AUDIT.toString().equals(studentStatus)) {
			canEdit = false;
		}
		mv.addObject("canEdit",canEdit);
		
		mv.addObject("parttimes", userParttimeService.list(searchParam));
		return mv;
	}
	
	/**
	 * 跳转到新增、修改页面
	 * @param searchParam
	 * @return
	 */
	@RequestMapping({"/edit","view"})
	public ModelAndView edit(UserParttime userParttime) {
		ModelAndView mv = this.newModelAndView();
		String id = userParttime.getId();
		if(StringUtils.isNotEmpty(id)) {
			userParttime = userParttimeService.read(id);
			mv.addObject("userParttime",userParttime);
		}
		return mv;
	}
	
	/**
	 * 新增或更新
	 * @param userParttime
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/save")
	@ResponseBody
	public AjaxReturnObject save(@FormModel("userParttime")UserParttime userParttime) throws IOException {
		int count = userParttimeService.save(userParttime);
		return new AjaxReturnObject().newDefaultAjaxReturnObject(count);
	}

	/**
	 * 删除
	 * @param userParttime
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public  String delete(UserParttime userParttime,HttpServletResponse response) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		int count=userParttimeService.delete(userParttime, loginUser.getId());
		return this.render(count>0?new Response("00","删除成功").toString():new Response("01","删除失败").toString(), "text/plain;charset=UTF-8",response);
	}
}
