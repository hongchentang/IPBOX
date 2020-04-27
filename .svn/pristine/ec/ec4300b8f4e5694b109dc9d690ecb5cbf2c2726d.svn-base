package com.hcis.ipanther.common.user.dao;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.user.entity.UserRegister;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
/**
 * 
 * @author wuwentao
 * @date 2015年3月30日
 */
@Repository
public class UserRegisterDao extends MyBatisDao {
	
	/**
	 * 根据用户ID取最新一条记录
	 * @param userId
	 * @return
	 */
	public UserRegister getLatestByUserId(String userId) {
		return (UserRegister) this.selectOne(namespace+".getLatestByUserId", userId);
	}
	
}
