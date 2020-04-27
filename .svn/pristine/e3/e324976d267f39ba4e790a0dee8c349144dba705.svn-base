package com.hcis.ipr.train.project.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.web.multipart.MultipartFile;

import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.course.entity.Course;
import com.hcis.ipr.train.project.entity.Project;

public interface IProjectService extends IBaseService<Project>{

	public String uploadFile(Project project,MultipartFile file);
	
	public ProcessInstance startWorkFlow(Project project,HttpServletRequest request);
	
	public void deleteWorkFlow(String procInstId);
	
	public void declareCourse(Project project,HttpServletRequest request);
	
	public void audit(Project project,HttpServletRequest request,SearchParam searchParam);
	
	public List<Map<String,Object>> listTodo(SearchParam searchParam,HttpServletRequest request);
	
	public List<Map<String,Object>> listAll(SearchParam searchParam,HttpServletRequest request);
	
	public List<Map<String,Object>> listDone(SearchParam searchParam,HttpServletRequest request);

	public int deleteAll(Project project,String id);
	
}
