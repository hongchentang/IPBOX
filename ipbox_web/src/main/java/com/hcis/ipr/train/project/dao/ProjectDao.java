package com.hcis.ipr.train.project.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.project.entity.Project;

@Repository("projectDao")
public class ProjectDao extends MyBatisDao{
	//待办任务
	public List<Map<String,Object>> listTodo(SearchParam searchParam){
		return this.selectForList(namespace+".listTodo", searchParam);
	}
	
	//已办任务
	public List<Map<String,Object>> listDone(SearchParam searchParam){
		return this.selectForList(namespace+".listDone", searchParam);
	}
	
	public int deleteAll(Project pro){
		return this.getSqlSession().update(namespace+".deleteAll", pro);
	}
	
	//所有项目
	public List<Map<String,Object>> listAll(SearchParam searchParam){
		return this.selectForList(namespace+".listAll", searchParam);
	}
}