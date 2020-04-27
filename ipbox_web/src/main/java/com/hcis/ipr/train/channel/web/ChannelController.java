package com.hcis.ipr.train.channel.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.channel.service.IChannelService;
import com.hcis.ipr.train.course.entity.Course;
import com.hcis.ipr.train.course.service.ICourseService;
import com.hcis.ipr.train.project.entity.Project;
import com.hcis.ipr.train.project.service.IProjectService;
import com.hcis.ipr.train.train.entity.Train;
import com.hcis.ipr.train.train.entity.TrainCourse;
import com.hcis.ipr.train.train.service.ITrainCourseService;
import com.hcis.ipr.train.train.service.ITrainService;
 
@Controller
@RequestMapping("/train/channel")
public class ChannelController extends BaseController {
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private ITrainService trainService;
	
	@Autowired
	private ITrainCourseService trainCourseService;
	
	@Autowired
	private IChannelService channelService;
	
	public IChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(IChannelService channelService) {
		this.channelService = channelService;
	}

	public ICourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourseService courseService) {
		this.courseService = courseService;
	}

	public IProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public ITrainService getTrainService() {
		return trainService;
	}

	public void setTrainService(ITrainService trainService) {
		this.trainService = trainService;
	}

	public ITrainCourseService getTrainCourseService() {
		return trainCourseService;
	}

	public void setTrainCourseService(ITrainCourseService trainCourseService) {
		this.trainCourseService = trainCourseService;
	}

	@RequestMapping("tabList")
	public ModelAndView tabList(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		return modelAndView;
	}
	
	//编辑中的课程
	@RequestMapping("listEdit")
	public ModelAndView listEdit(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();		
		searchParam.getParamMap().put("creator", LoginUser.loginUser(request).getId());
		modelAndView.addObject("listCourse", courseService.list(searchParam));
		return modelAndView;
	}
	
	//编辑课程页面
	@RequestMapping("goAddCourse")
	public ModelAndView goAddCourse(Course course,SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/channel/addCourse");
		if(course!=null&&StringUtils.isNotBlank(course.getId())){
			modelAndView.addObject("course", courseService.read(course.getId()));
		}
		return modelAndView;
	}
	
	//编辑中的项目 
	@RequestMapping("listProEdit")
	public ModelAndView listProEdit(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("creator", LoginUser.loginUser(request).getId());
		modelAndView.addObject("listProject", projectService.list(searchParam));
		return modelAndView;
	}
	
	//编辑项目页面
	@RequestMapping("goAddProject")
	public ModelAndView goAddProject(Project project,SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/channel/addProject");
		if(project!=null&&StringUtils.isNotBlank(project.getId())){
			modelAndView.addObject("project", projectService.read(project.getId()));
		}
		return modelAndView;
	}
	
	//编辑培训班信息
	@RequestMapping("goAddTrain")
	public ModelAndView goAddTrain(Train train,SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/channel/addTrain");
		if(null!=train&&StringUtils.isNotBlank(train.getId())){
			train=trainService.read(train.getId());
			SearchParam sp=new SearchParam();
			sp.getParamMap().put("trainId", train.getId());
			modelAndView.addObject("courseList", trainCourseService.selectCourseByTrain(sp));
		}else {
			train.setProjectId(searchParam.getParamMap().get("projectId").toString());
			List courseList = new ArrayList();

			String[] ids = searchParam.getParamMap().get("ids").toString().split(",");
			String[] names = searchParam.getParamMap().get("names").toString().split(",");
			
			for (int i = 0; i < ids.length; i++) {
				Course course = new Course();
				course.setId(ids[i]);
				course.setCourseId(ids[i]);
				course.setCourseName(names[i]);
				courseList.add(course);
			}
			modelAndView.addObject("courseList", courseList);
		}
		modelAndView.addObject("train", train);
		return modelAndView;
	}
	
	//审核项目页面
	@RequestMapping("goAudit")
	public ModelAndView goAudit(Project project){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/channel/audit");
		if(project!=null&&StringUtils.isNotBlank(project.getId())){
			modelAndView.addObject("project", projectService.read(project.getId()));
		}
		return modelAndView;
	}
	
	//待办任务
	@RequestMapping("listTodo")
	public ModelAndView listTodo(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("listProject", projectService.listTodo(searchParam,request));
		return modelAndView;
	}
	
	//培训班列表
	@RequestMapping("listTrain")
	public ModelAndView listTrain(@ModelAttribute("searchParam")SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("listTrain", trainService.list(searchParam));
		return modelAndView;
	}
	
	//已通过
	@RequestMapping("listPass")
	public ModelAndView listPass(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("status", "02");
		//searchParam.getParamMap().put("isHidden", "N");//业务删除的不显示
		modelAndView.addObject("listProject", projectService.listDone(searchParam, request));
		return modelAndView;
	}
		
	//不通过
	@RequestMapping("listUnPass")
	public ModelAndView listUnPass(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("status", "04");
		modelAndView.addObject("listProject", projectService.listDone(searchParam, request));
		return modelAndView;
	}
	
	//邀请学员
	@RequestMapping("showAsk")
	public ModelAndView showAsk(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/channel/ask");
		modelAndView.addObject("projectId", searchParam.getParamMap().get("projectId").toString());
		return modelAndView;
	}
	
	//选择学员列表
	@RequestMapping("selectStudent")
	public ModelAndView selectStudent(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/channel/selectStudent");
		//角色为人才、学员类型的人
		searchParam.getParamMap().put("roleCode", "student");
		modelAndView.addObject("listStudent", trainCourseService.listTeacher(searchParam));
		return modelAndView;
	}
	
	//发送邀请邮件
	@RequestMapping("askMail")
	public @ResponseBody AjaxReturnObject askMail(SearchParam searchParam){
		int statusCode=200;
		String msg="发送成功！";
		System.out.println(searchParam.getParamMap().get("projectId"));
		System.out.println(searchParam.getParamMap().get("studentIds"));
		channelService.askMail(searchParam, request, LoginUser.loginUser(request));
		return new AjaxReturnObject(statusCode, msg);
	}
}
