package com.hcis.ipr.train.course.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.web.multipart.MultipartFile;

import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.intellectual.call.entity.PatentType;
import com.hcis.ipr.train.course.entity.Course;

public interface ICourseService extends IBaseService<Course> {

	public ProcessInstance startWorkFlow(Course course, HttpServletRequest request);

	public String uploadFile(Course course, MultipartFile file);

	public String uploadImg(Course course, MultipartFile file);

	public void deleteWorkFlow(String procInstId);

	public void declareCourse(Course course, HttpServletRequest request);

	public void audit(Course course, HttpServletRequest request, SearchParam searchParam);

	public List<Map<String, Object>> listAll(SearchParam searchParam, HttpServletRequest request);

	public List<Map<String, Object>> listTodo(SearchParam searchParam, HttpServletRequest request);

	public List<Map<String, Object>> listDone(SearchParam searchParam, HttpServletRequest request);

	public List<Map<String, Object>> listCourse(SearchParam searchParam);

	public List<Map<String, Object>> selectCourseTrain(SearchParam searchParam);

	public List<Map<String, Object>> selectTalentCourse(SearchParam searchParam);

	public List<Map<String, Object>> selectTrain(SearchParam searchParam);

	public String uploadExpertFile(SearchParam searchParam, MultipartFile[] upload, HttpServletRequest request);

	public List<Map<String, Object>> listTrainCoure(SearchParam searchParam);

	public int deleteExpertFile(SearchParam searchParam, HttpServletRequest request);

	public String uploadAvatar(Course course, MultipartFile file);

	/**
	 * 课程审核通过后向报名人员的邮箱定向推送消息
	 * 
	 * @param course
	 * @return
	 */
	public void sendEmeilByCourse(Course course, HttpServletRequest request);

	public List<Map<String, Object>> selectCourseHome(SearchParam searchParam);

	List<PatentType> getPatentType(String ID);
}
