package com.hcis.ipanther.common.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * 
 * @author wuwentao
 * @date 2015年3月10日
 */
@Repository
public class UserStaffDao extends MyBatisDao {
	
	//打印数据列表
	public List<Map<String,Object>> userStaffInfoList(SearchParam searchParam){
		return this.selectForList(namespace+".userStaffInfoList", searchParam);
	}
	
}
