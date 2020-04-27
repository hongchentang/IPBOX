package com.hcis.ipr.train.register.web;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.utils.RoleConstant;
import com.hcis.ipanther.common.security.utils.SecurityRoleUtils;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.register.service.ICourseRegisterService;
import com.hcis.ipr.train.train.service.ITrainService;

@RequestMapping("/train/courseRegister/")
@Controller
public class CourseRegisterController extends BaseController{

	@Autowired
	private ITrainService trainService;
	@Autowired
	private ICourseRegisterService courseRegisterService;
	
	//培训班列表
	@RequestMapping("listCourseRegisterTrain")
	public ModelAndView listCourseRegisterTrain(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/register/courseRegisterTrain");
		//若为知识产权局领导，则显示全部
		if(!SecurityRoleUtils.hasRole(RoleConstant.GD_IPR_LEADER, LoginUser.loginUser(request).getId())){
			searchParam.getParamMap().put("declareDept", LoginUser.loginUser(request).getFirstDeptId());
		}
		//项目状态
		searchParam.getParamMap().put("status", "02");
		searchParam.getParamMap().put("registerCount", "00");
		modelAndView.addObject("listTrain", trainService.listTrain(searchParam));
		return modelAndView;
	}
	
	//查看学员成绩列表
	@RequestMapping("listScoreInfo")
	public ModelAndView listScoreInfo(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/register/listScoreInfo");
		modelAndView.addObject("listScoreInfo", courseRegisterService.listScoreInfo(searchParam));
		return modelAndView;
	}
	
	//根据培训班导出数据模板
	@RequestMapping("exportTemplate")
	@ResponseBody
	public void exportTemplate(SearchParam searchParam,final HttpServletResponse response){
		courseRegisterService.exportTemplate(searchParam, response);
	}
	
	//跳往导出高级数据模板页面
	@RequestMapping("seniorTemplate")
	public ModelAndView seniorTemplate(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/register/seniorTemplate");
		return modelAndView;
	}
	
	//跳往成绩导入页面
	@RequestMapping("goImport")
	public ModelAndView goImport(){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/register/import");
		return modelAndView;
	}
	
	//保存成绩导入
	@RequestMapping("/saveImport")
	public @ResponseBody AjaxReturnObject importUser(MultipartFile file){
		return courseRegisterService.saveImport(file, request);
	}
	
	//跳往导入结果
	@RequestMapping("importResult")
	public ModelAndView importResult(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/register/importResult");
		Object obj=searchParam.getParamMap().get("batchId");
		if(null!=obj && StringUtils.isNotBlank(obj.toString())){
			Map<String, Object> results = (Map<String, Object>)request.getSession().getAttribute(obj.toString());
			modelAndView.addObject("results", results);
		}
		return modelAndView;
	}
}
