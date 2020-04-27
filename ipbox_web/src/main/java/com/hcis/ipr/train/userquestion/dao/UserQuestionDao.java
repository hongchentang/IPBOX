package com.hcis.ipr.train.userquestion.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipr.train.userquestion.entity.UserQuestion;

@Repository
public class UserQuestionDao extends MyBatisDao{
	
	/**
	 * 根据某个字段查找出符合的所有在线留言记录
	 * @param map
	 * @return
	 */
	public List<UserQuestion> selectByMap(Map<String,Object> map){
		return this.getSqlSession().selectList(namespace+".selectByMap", map);
	}
	
	/**
	 * 计算出不同类型的数据数量
	 * @param map
	 * @return
	 */
	public int selectCount(Map<String,Object> map){
		return this.getSqlSession().selectOne(namespace+".selectCount", map);
	}
}
