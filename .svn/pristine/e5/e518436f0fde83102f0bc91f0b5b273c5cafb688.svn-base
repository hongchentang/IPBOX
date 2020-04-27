package com.hcis.ipr.train.train.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.train.entity.TrainCourse;

@Repository
public class TrainCourseDao extends MyBatisDao{
	
   public int deleteByTrainId(String trainId){
	   return this.update(namespace+".deleteByTrainId", trainId);
   }
   
  public List<Map<String,Object>> selectCourseByTrain(SearchParam searchParam){
	  return this.selectForList(namespace+".selectCourseByTrain", searchParam);
  }
  public List<Map<String,Object>> listTeacher(SearchParam searchParam){
	  return this.selectForList(namespace+".listTeacher", searchParam);
  }
}