package com.hcis.ipr.train.train.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.train.entity.TrainCourse;

public interface ITrainCourseService extends IBaseService<TrainCourse>{

	public int saveTrainCourse(String trainId,String courseIds,LoginUser loginUser);
	public List<Map<String,Object>> selectCourseByTrain(SearchParam searchParam);
	public int deleteByTrainId(String trainId);
	public int saveDivide(SearchParam searchParam, LoginUser loginUser);
	public List<Map<String,Object>> listTeacher(SearchParam searchParam);
	public String uploadCourseFile(SearchParam searchParam,MultipartFile[] files,HttpServletRequest request);
	public int deleteCourseFile(SearchParam searchParam,HttpServletRequest request);
}
