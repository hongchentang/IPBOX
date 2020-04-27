package com.hcis.ipanther.common.flow.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.flow.dao.FlowLogDao;
import com.hcis.ipanther.common.flow.entity.FlowLog;
import com.hcis.ipanther.common.flow.service.IFlowLogService;
import com.hcis.ipanther.common.flow.utils.FlowConstants;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Service
public class FlowLogServiceImpl extends BaseServiceImpl<FlowLog> implements IFlowLogService {
	
	private final static Log log=LogFactory.getLog(FlowLogServiceImpl.class);
	
	@Autowired
	private FlowLogDao flowLogDao;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private HistoryService historyService;
	
	@Override
	public MyBatisDao getBaseDao() {
		return flowLogDao;
	}
	
	@Override
	public int save(String tableName,String businessColumnName,String businessId,
			String taskId,String taskName,String auditResult,String auditContent) {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		Date now = new Date();
		String userId = loginUser.getId();
		
		FlowLog flowLog = new FlowLog();
		flowLog.setTableName(tableName);
		flowLog.setBusinessColumnName(businessColumnName);
		flowLog.setBusinessId(businessId);
		
		flowLog.setTaskId(taskId);
		flowLog.setTaskName(taskName);
		flowLog.setAuditResult(auditResult);
		flowLog.setAuditContent(auditContent);
		
		flowLog.setId(Identities.uuid2());
		flowLog.setAuditDept(loginUser.getFirstDeptId());
		flowLog.setAuditUser(userId);
		flowLog.setAuditTime(now);
		flowLog.setCreator(userId);
		flowLog.setCreateTime(now);
		flowLog.setStatus(FlowConstants.FLOW_STATUS_DONE);
		flowLog.setDefaultValue();
		return this.create(flowLog, loginUser.getId());
	}

	@Override
	public List<FlowLog> listByBusinessId(SearchParam searchParam,String procInstId,String businessId) {
		HistoricProcessInstance instance = historyService.createHistoricProcessInstanceQuery().processInstanceId(procInstId).singleResult();
		ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(instance.getProcessDefinitionId());
		Map<String,Object> variables = processDefinitionEntity.getVariables();
		String tableName = (String) variables.get(FlowConstants.FLOW_TABLE_NAME);
		String businessColumnName = (String) variables.get(FlowConstants.FLOW_BUSINESS_COLUMN_NAME);
		Map<String,Object> paramMap = searchParam.getParamMap();
		paramMap.put("tableName", tableName);
		paramMap.put("businessColumnName", businessColumnName);
		paramMap.put("businessId", businessId);
		List<FlowLog> flowLogs = flowLogDao.selectBySearchParam(searchParam);
		return flowLogs;
	}
	
	@Override
	public FlowLog getLastFlowLog(String tableName,String businessColumnName, String businessId) {
		SearchParam searchParam = new SearchParam();
		Map<String,Object> paramMap = searchParam.getParamMap();
		paramMap.put("tableName", tableName);
		paramMap.put("businessColumnName", businessColumnName);
		paramMap.put("businessId", businessId);
		return flowLogDao.getLastFlowLog(searchParam);
	}
}
