package com.hcis.ipr.evalua.effect.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.regions.entity.Regions;
import com.hcis.ipanther.common.regions.utils.RegionsUtil;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipr.evalua.effect.entity.ProjectPerformanceTarget;
import com.hcis.ipr.evalua.effect.service.IProjectPerformanceTargetService;
import com.hcis.ipr.train.project.entity.Project;
import com.hcis.ipr.train.project.service.IProjectService;

@Controller
@RequestMapping("/evalua/performanceTarget")
public class ProjectPerformanceTargetController extends BaseController{

	
	@Autowired
	private IProjectPerformanceTargetService projectPerformanceTargetService;
	@Autowired
	private IProjectService projectService;
	
	
	
	@RequestMapping("add")
	public ModelAndView add(@FormModel("projectPerformanceTarget")ProjectPerformanceTarget projectPerformanceTarget){
		ModelAndView andView=new ModelAndView("/train/evaluate/performanceTarget");
		Project project=projectService.read(projectPerformanceTarget.getProjectId());
		Map<String, Object> map=projectPerformanceTargetService.selectPerformanceTargetById(StringUtils.isNoneEmpty(project.getId())?project.getId():projectPerformanceTarget.getProjectId());
		List<Map<String, Object>> lsit=projectPerformanceTargetService.selectRegionsCodeList(StringUtils.isNoneEmpty(project.getId())?project.getId():projectPerformanceTarget.getProjectId());
		if(CollectionUtils.isNotEmpty(lsit)){
			for (Map<String, Object> temp : lsit) {
				int count=0;
				String code=ObjectUtils.toString(temp.get("regionsCode"));
				String regionsLevel=ObjectUtils.toString(temp.get("regionsLevel"));
				for (Map<String, Object> tempMap : lsit) {
					String parentCode=ObjectUtils.toString(tempMap.get("parentCode"));
					String regionsLevelSon=ObjectUtils.toString(tempMap.get("regionsLevel"));
					if(StringUtils.equals(regionsLevel,"1")&&StringUtils.equals(regionsLevelSon,"2")){
						count++;
					}else if(StringUtils.equals(parentCode, code)){
						count++;
					}
				}
				temp.put("sumNum", count);
				List<Regions> regionsLsit=RegionsUtil.getChildRegions(code, true);
				if(CollectionUtils.isNotEmpty(regionsLsit)){
					temp.put("countNum", regionsLsit.size());
				}
			}
		}
		andView.addObject("project",project);
		andView.addObject("dataMap",map);
		andView.addObject("dataList",lsit);
		return andView;
	}
	
	@RequestMapping("saveFile")
	public String saveFile(DefaultMultipartHttpServletRequest requests,@ModelAttribute("projectPerformanceTarget")ProjectPerformanceTarget projectPerformanceTarget,HttpServletResponse response){
		LoginUser loginUser=LoginUser.loginUser(request);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) requests;  
		MultipartFile file = multipartRequest.getFile("uploadFile");
		if(file!=null&&!file.isEmpty()){
			String fileInfo=projectPerformanceTargetService.uploadFile(projectPerformanceTarget, file);
			if(StringUtils.isNotBlank(fileInfo)){
				return this.render(new Response("300",fileInfo).toString(), "text/html;charset=UTF-8", response);
			}
		}
		int count=projectPerformanceTargetService.create(projectPerformanceTarget, loginUser.getId());
		return this.render(Response.newDefaultResponse(count).toString(), "text/html;charset=UTF-8", response);
	}
	
	@RequestMapping("addAttachment")
	public ModelAndView addAttachment(@FormModel("projectPerformanceTarget")ProjectPerformanceTarget projectPerformanceTarget){
		ModelAndView andView=new ModelAndView("/train/evaluate/addFile");
		Project project=projectService.read(projectPerformanceTarget.getProjectId());
		andView.addObject("project",project);
		return andView;
	}
}
