package com.hcis.ipr.train.register.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.utils.RoleConstant;
import com.hcis.ipanther.common.security.utils.SecurityRoleUtils;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.course.service.ICourseService;
import com.hcis.ipr.train.register.service.ITrainRegisterService;
import com.hcis.ipr.train.train.service.ITrainCourseService;
import com.hcis.ipr.train.train.service.ITrainService;

@RequestMapping("/train/register")
@Controller
public class TrainRegisterController extends BaseController{
	
	@Autowired
	private ICourseService courseService;
	@Autowired
	private ITrainService trainService;
	@Autowired
	private ITrainCourseService trainCourseService;
	@Autowired
	private ITrainRegisterService trainRegisterService;
	
	
	//培训班列表
	@RequestMapping("listRegisterTrain")
	public ModelAndView listRegisterTrain(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/register/registerTrain");
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
	
	//培训查看报名情况
	@RequestMapping("showTrainDetail")
	public ModelAndView showTrainDetail(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/register/showDetail");
		String trainId=searchParam.getParamMap().get("trainId").toString();
		modelAndView.addObject("train",trainService.read(trainId));
		return modelAndView;
	}
	
	//已报名且确认通过的学员列表
	@RequestMapping("listRegTrainUser")
	public ModelAndView listRegTrainUser(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		//学员培训状态
		searchParam.getParamMap().put("status", "01");
		List list=trainRegisterService.list(searchParam);
		modelAndView.addObject("listregTrainUser", list);
		return modelAndView;
	}
	//选择学员列表
	@RequestMapping("selectStudent")
	public ModelAndView selectStudent(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		//角色为人才、学员类型的人
		searchParam.getParamMap().put("roleCode", "student");
		modelAndView.addObject("listStudent", trainCourseService.listTeacher(searchParam));
		return modelAndView;
	}
	
	//保存对应关系
	@RequestMapping("saveTrainRegister")
	@ResponseBody
	public AjaxReturnObject saveTrainRegister(SearchParam searchParam){
		int statusCode=200;
		String msg="操作成功！";
		int count=trainRegisterService.saveTrainRegister(searchParam, request);
		if(count<1){
			statusCode=300;
			msg="操作失败！";
		}
		return new AjaxReturnObject(statusCode, msg);
	}
	
	//删除已经报名的学员
	@RequestMapping("deleteTrainUser")
	@ResponseBody
	public AjaxReturnObject deleteTrainUser(SearchParam searchParam){
		int statusCode=200;
		String msg="操作成功！";
		trainRegisterService.batchDelete(searchParam, request);
		return new AjaxReturnObject(statusCode, msg);
	}
	
}
