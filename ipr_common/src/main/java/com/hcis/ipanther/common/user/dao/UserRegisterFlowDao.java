package com.hcis.ipanther.common.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.user.entity.UserRegisterFlow;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
/**
 * 
 * @author wuwentao
 * @date 2015年3月24日
 */
@Repository
public class UserRegisterFlowDao extends MyBatisDao {

	/**
	 * 根据用户ID取最新一条记录
	 * @param userId
	 * @return
	 */
	public UserRegisterFlow getLatestByUserId(String userId) {
		return (UserRegisterFlow) this.selectOne(namespace+".getLatestByUserId", userId);
	}
	
	/**
	 * 取出用户的注册流程，包括人才注册和教师注册
	 * @param userId
	 * @return
	 */
	public List<UserRegisterFlow> getFlowsByUserId(SearchParam searchParam) {
		return this.selectForList(namespace+".getFlowsByUserId", searchParam);
	}
}
