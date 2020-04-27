package com.hcis.ipr.train.register.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.register.entity.CourseRegister;

public interface ICourseRegisterService extends IBaseService<CourseRegister>{

	public int batchUpdate(SearchParam searchParam);
	public List<Map<String,Object>> listScoreInfo(SearchParam searchParam);
	public void exportTemplate(SearchParam searchParam,HttpServletResponse response);
	public AjaxReturnObject saveImport(MultipartFile file,HttpServletRequest request);
}
