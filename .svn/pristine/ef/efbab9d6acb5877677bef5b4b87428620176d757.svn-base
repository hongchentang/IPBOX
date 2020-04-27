package com.hcis.ipanther.common.validate.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.validate.entity.Validate;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;

@Repository
public class ValidateDao extends MyBatisDao{

	/**
	 * 根据传入的email或phone查出 时间最新的验证码数据
	 * @param map
	 * @return
	 */
	public List<Validate> selectByMap(Map map){
		return this.selectForList(namespace+".selectByMap", map);
	}
	
	
	
}
