package com.hcis.ipanther.common.flow.dao;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.flow.entity.FlowLog;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
/**
 * 通用流程日志DAO
 * @author wuwentao
 * @date 2015年3月30日
 */
@Repository
public class FlowLogDao extends MyBatisDao {
	
	
	public FlowLog getLastFlowLog(SearchParam searchParam) {
		return (FlowLog) this.selectForOneVO(namespace+".getLastFlowLog", searchParam);
	}
	
}
