package com.hcis.ipr.evalua.effect.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipr.evalua.effect.entity.ProjectPerformanceTarget;

public interface IProjectPerformanceTargetService extends IBaseService<ProjectPerformanceTarget>{

	Map<String, Object> selectPerformanceTargetById(String projectId);

	List<Map<String, Object>> selectRegionsCodeList(String projectId);

	String uploadFile(ProjectPerformanceTarget projectPerformanceTarget, MultipartFile file);

}
