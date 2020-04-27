package com.hcis.ipr.train.effect.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.core.web.controller.BaseController;
import com.hcis.ipr.train.effect.entity.Check;
import com.hcis.ipr.train.effect.entity.CheckResult;
import com.hcis.ipr.train.effect.service.ICheckResultService;
import com.hcis.ipr.train.effect.service.ICheckService;
import com.hcis.ipr.train.project.entity.Project;
import com.hcis.ipr.train.project.service.IProjectService;
import com.hcis.survey.entity.Survey;
/**
 * 培训效果评估子系统-抽查相关控制
 * @author wuwentao
 * @date 2015年4月22日
 */
@Controller
@RequestMapping("/train/effect/check")
public class CheckController  extends BaseController {
	
	@Autowired
	private ICheckService checkService;
	@Autowired
	private ICheckResultService checkReusltService;
	@Autowired
	private IProjectService projectService;
	@Autowired
	private IUserService userService;
	
	/**
	 * 管理列表
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list(SearchParam searchParam) {
		ModelAndView mv = new ModelAndView();
		List<Check> checks = checkService.list(searchParam);
		mv.addObject("checks", checks);
		return mv;
	}
	
	/**
	 * 抽查结果列表
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("listResult")
	public ModelAndView listResult(SearchParam searchParam) {
		ModelAndView mv = new ModelAndView();
		List<CheckResult> checkResults = checkReusltService.list(searchParam);
		mv.addObject("checkResults", checkResults);
		return mv;
	}
	
	/**
	 * 跳转到新增/修改页面
	 * @param check
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("edit")
	public ModelAndView edit(Check check) throws IOException {
		ModelAndView mv = new ModelAndView();
		String id = check.getId();
		if(StringUtils.isNotEmpty(id)) {
			check = checkService.read(id);
			/*
			 * 处理项目ID，转换成 项目ID,项目名称;项目ID,项目名称
			 */
			String projectIds = check.getProjectIds();
			if(StringUtils.isNotEmpty(projectIds)) {
				List<String> projectIdList = JsonUtil.fromJson(projectIds, List.class);
				projectIds = "";
				for (String projectId : projectIdList) {
					Project project = projectService.read(projectId);
					if(null!=project) {
						projectIds +="★"+project.getId()+"☆"+project.getProjectName();
					}
				}
				if(StringUtils.isNotEmpty(projectIds)) {
					projectIds = projectIds.substring(1);
				}
				check.setProjectIds(projectIds);
			}
			/*
			 * 处理教师ID，转换成 教师ID,教师名称;教师ID,教师名称
			 */
			String teacherIds = check.getTeacherIds();
			if(StringUtils.isNotEmpty(teacherIds)) {
				List<String> teacherIdList = JsonUtil.fromJson(teacherIds, List.class);
				teacherIds = "";
				for (String teacherId : teacherIdList) {
					User user = userService.read(teacherId);
					if(null!=user) {
						teacherIds +="★"+user.getId()+"☆"+user.getRealName();
					}
				}
				if(StringUtils.isNotEmpty(teacherIds)) {
					teacherIds = teacherIds.substring(1);
				}
				check.setTeacherIds(teacherIds);
			}
		}
		mv.addObject("check", check);
		return mv;
	}
	
	/**
	 * 新增/修改动作
	 * @param check
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Response save(Check check) throws Exception {
		int count = checkService.save(check);
		return Response.newDefaultResponse(count);
	}
	
	/**
	 * 逻辑删除抽查
	 * @param survey
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Response delete(Check check) {
		int count = checkService.delete(check,LoginUser.loginUser(request).getId());
		return count>0?Response.successInstance():Response.failInstance();
	}
	
	/**
	 * 发布抽查
	 * @param survey
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/publish")
	@ResponseBody
	public Response publish(Check check) throws IOException {
		String errorMsg = checkService.publish(check);
		return StringUtils.isEmpty(errorMsg)?Response.successInstance():new Response("01", errorMsg);
	}
}
