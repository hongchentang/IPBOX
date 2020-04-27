package com.hcis.ipr.train.project.web;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.dept.utils.DepartmentUtils;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.entity.Response;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.project.entity.Project;
import com.hcis.ipr.train.project.service.IProjectService;
import com.hcis.ipr.train.train.service.ITrainService;

@RequestMapping("/train/project")
@Controller
public class ProjectController extends BaseController{

	@Autowired
	private IProjectService projectService;
	@Autowired
	private ITrainService  trainService;
	// 项目列表 
	@RequestMapping("tabList")
	public ModelAndView listProject(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		projectService.listTodo(searchParam,request);
		return modelAndView;
	}
	
	//所有项目 
	@RequestMapping("listAll")
	public ModelAndView listAll(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("listProject", projectService.listAll(searchParam,request));
		return modelAndView;
	}
	
	//编辑中的项目 
	@RequestMapping("listEdit")
	public ModelAndView listEdit(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("creator", LoginUser.loginUser(request).getId());
		modelAndView.addObject("listProject", projectService.list(searchParam));
		return modelAndView;
	}
	
	//待办任务
	@RequestMapping("listTodo")
	public ModelAndView listTodo(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("listProject", projectService.listTodo(searchParam,request));
		return modelAndView;
	}
	
	//已办任务
	@RequestMapping("listDone")
	public ModelAndView listDone(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("listProject", projectService.listDone(searchParam, request));
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
	//编辑项目页面
	@RequestMapping("goAddProject")
	public ModelAndView goAddProject(Project project,SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/project/addProject");
		if(project!=null&&StringUtils.isNotBlank(project.getId())){
			modelAndView.addObject("project", projectService.read(project.getId()));
		}
		return modelAndView;
	}
	
	//保存项目
	@RequestMapping("saveProject")
	@ResponseBody
	public AjaxReturnObject saveProject(@ModelAttribute("project")Project project,DefaultMultipartHttpServletRequest request){
		LoginUser loginUser=LoginUser.loginUser(request);
		int statusCode=200;
		String msg="操作成功！";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		MultipartFile file = multipartRequest.getFile("uploadFile");
		if(file!=null&&!file.isEmpty()){
			String fileInfo=projectService.uploadFile(project, file);
			if(StringUtils.isNotBlank(fileInfo)){
				return new AjaxReturnObject(300, fileInfo);
			}
		}
		if(StringUtils.isNotBlank(project.getId())){
			projectService.update(project, loginUser.getId());
		}else{
			project.setStatus("00");
			int createCount=projectService.create(project, loginUser.getId());
		}
		
		return new AjaxReturnObject(statusCode, msg);
	}
	
	//删除项目
	@RequestMapping("deleteProject")
	@ResponseBody
	public String deleteProject(@ModelAttribute("project")Project project,HttpServletResponse response){
		LoginUser loginUser=LoginUser.loginUser(request);
		int statusCode=200;
		String msg="操作成功！";
		if(null!=project.getProcInstId()&&!"".equals(project.getProcInstId())){
			//抓获结束流程
			try{
				projectService.deleteWorkFlow(project.getProcInstId());
			}catch(ActivitiObjectNotFoundException e){}
		}
		SearchParam se=new SearchParam();
		se.getParamMap().put("projectId", project.getId());
		int count =0;
		if((trainService.list(se)).size()>0){	
			    count=projectService.deleteAll(project, loginUser.getId());
			}else{
			    count =projectService.delete(project, loginUser.getId()); 
			}
		
		return this.render(AjaxReturnObject.newDefaultAjaxReturnObject(count).toString(),  "text/plain;charset=UTF-8", response);
	}
	//隐藏审核通过的项目
	@RequestMapping("hiddenProject")
	@ResponseBody
	public AjaxReturnObject hiddenProject(@ModelAttribute("project")Project project){
		LoginUser loginUser=LoginUser.loginUser(request);
		int statusCode=200;
		String msg="操作成功！";
		projectService.update(project, loginUser.getId());
		return new AjaxReturnObject(statusCode, msg);
	}
	
	//申报项目
	@RequestMapping("declareProject")
	public @ResponseBody AjaxReturnObject declareProject(Project project){
		int statusCode=200;
		String msg="操作成功！";
		project=projectService.read(project.getId());
		projectService.declareCourse(project, request);
		return new AjaxReturnObject(statusCode, msg);
	}
	
	//审核项目页面
	@RequestMapping("goAudit")
	public ModelAndView goAudit(Project project){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/project/audit");
		if(project!=null&&StringUtils.isNotBlank(project.getId())){
			modelAndView.addObject("project", projectService.read(project.getId()));
		}
		return modelAndView;
	}
	
	//领导审核
	@RequestMapping("audit")
	public @ResponseBody AjaxReturnObject audit(Project project,SearchParam searchParam){
		int statusCode=200;
		String msg="操作成功！";
		projectService.audit(project, request, searchParam);
		return new AjaxReturnObject(statusCode, msg);
	}
	
	//查看详细
	@RequestMapping("showDetail")
	public ModelAndView showDetail(Project project){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("project", projectService.read(project.getId()));
		return modelAndView;
	}
	
	//查看页面
	@RequestMapping("view")
	public ModelAndView view(SearchParam searchParam){
		Map<String,Object> paramMap=searchParam.getParamMap();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/train/project/audit");
		if(paramMap.get("projectId")!=null&&StringUtils.isNotBlank(paramMap.get("projectId").toString())){
			modelAndView.addObject("project", projectService.read(paramMap.get("projectId").toString()));
		}
		modelAndView.addObject("viewValue", "view");
		return modelAndView;
	}
	
	/**
	 * 机构抽查选择项目列表
	 * 只列示审批通过的项目
	 * @author wuwentao
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("chooseProject")
	public ModelAndView chooseProject(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		searchParam.getParamMap().put("status", "02");
		Map<String,Object> paramMap = searchParam.getParamMap();
		String notIds = (String) paramMap.get("notIds");
		if(StringUtils.isNotEmpty(notIds)) {
			paramMap.put("notIds", notIds.split(","));
		} else {
			paramMap.put("notIds", null);
		}
		modelAndView.addObject("projects", projectService.list(searchParam));
		paramMap.put("notIds", notIds);
		return modelAndView;
	}
	
	
	/**
	 * 机构抽查选择项目列表
	 * 只列示审批通过的项目
	 * @author wuwentao
	 * @param searchParam
	 * @return
	 */
	@RequestMapping("performanceTarget")
	public ModelAndView performanceTarget(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView("/train/project/listProjectPerformance");
		LoginUser loginUser=LoginUser.loginUser(request);
		searchParam.getParamMap().put("status", "02");
		if(DepartmentUtils.isTrainOrg(loginUser.getDeptType())){
			searchParam.getParamMap().put("declareDept",loginUser.getFirstDeptId());
		}
		modelAndView.addObject("projects", projectService.list(searchParam));
		return modelAndView;
	}
	
}
