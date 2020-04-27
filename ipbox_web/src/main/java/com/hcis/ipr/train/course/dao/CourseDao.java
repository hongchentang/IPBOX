package com.hcis.ipr.train.course.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Repository("courseDao")
public class CourseDao extends MyBatisDao{
	
	//所有课程
	public List<Map<String,Object>> listAll(SearchParam searchParam){
		return this.selectForList(namespace+".listAll", searchParam);
	}
	
	//待办任务
	public List<Map<String,Object>> listTodo(SearchParam searchParam){
		return this.selectForList(namespace+".listTodo", searchParam);
	}
	
	//已办任务
	public List<Map<String,Object>> listDone(SearchParam searchParam){
		return this.selectForList(namespace+".listDone", searchParam);
	}
	//可以选分配教师的课程列表
	public List<Map<String,Object>> listCourse(SearchParam searchParam){
		return this.selectForList(namespace+".listCourse", searchParam);
	}
	
	//已经分配了培训班，且可以选分配教师的课程列表
	public List<Map<String, Object>> listTrainCoure(SearchParam searchParam){
		return this.selectForList(namespace+".listTrainCoure", searchParam);
	}
	
	//前端报名课程培训班列表
	public List<Map<String,Object>> selectCourseTrain(SearchParam searchParam){
		return this.selectForList(namespace+".selectCourseTrain", searchParam);
	}
	//前端课程列表
	public List<Map<String,Object>> selectTalentCourse(SearchParam searchParam){
		return this.selectForList(namespace+".selectTalentCourse", searchParam);
	}
	//前端人才培训系统栏目首页培训基地选项 
	public List<Map<String,Object>> selectTrain(SearchParam searchParam){
		return this.selectForList(namespace+".selectTrain", searchParam);
	}
	//首页4条课程数据
	public List<Map<String,Object>> selectCourseHome(SearchParam searchParam){
		return this.selectForList(namespace+".selectCourseHome", searchParam);
	}
}