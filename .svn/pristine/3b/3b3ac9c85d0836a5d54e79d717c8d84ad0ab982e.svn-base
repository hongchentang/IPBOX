package com.hcis.ipanther.common.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.user.entity.UserRegions;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;

@Repository("userRegionsDao")
public class UserRegionsDao extends MyBatisDao {
	
	/**
	 * 逻辑删除userId的所有区域授权
	 * @param userRegions
	 * @return
	 */
	public int deleteByUserId(UserRegions userRegions){
		return this.update(this.namespace + ".deleteByUserId",userRegions);
	}
	
	/**
	 * 按UserId查询授权区域
	 * @param userId
	 * @return
	 */
	public List<UserRegions> selectByUserId(UserRegions userRegions){
		return this.selectForList(this.namespace+".selectByUserId", userRegions);
	}
	
}