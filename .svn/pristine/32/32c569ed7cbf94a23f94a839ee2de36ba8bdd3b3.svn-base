package com.hcis.ipanther.common.flow.listener;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.flow.service.IFlowLogService;
import com.hcis.ipanther.common.flow.utils.FlowConstants;

/**
 * 通用流程日志监听类
 * @author wuwentao
 * @date 2015年3月31日
 */
@Service("flowLogListener")
public class FlowLogListener implements TaskListener {

	private static final long serialVersionUID = -9179577998173405435L;

	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private IFlowLogService flowLogService;
	
	@Override
    public void notify(DelegateTask delegateTask) {
		String procInstId = delegateTask.getProcessInstanceId();
		ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
		String businessId = instance.getBusinessKey();
		/**
		 * 在流程图中需定义
		 */
		String executionId = delegateTask.getExecutionId();
    	String tableName = (String) runtimeService.getVariable(executionId, FlowConstants.FLOW_TABLE_NAME);//表名
    	String businessColumnName = (String) runtimeService.getVariable(executionId, FlowConstants.FLOW_BUSINESS_COLUMN_NAME);//字段名
    	/**
    	 * task.complete前，需要runtimeService.setVariable
    	 */
    	String auditResult = (String) runtimeService.getVariable(delegateTask.getExecutionId(),FlowConstants.FLOW_AUDIT_RESULT);//审核结果
    	String auditContent = (String) runtimeService.getVariable(delegateTask.getExecutionId(),FlowConstants.FLOW_AUDIT_CONTENT);//审核备注
    	
    	flowLogService.save(tableName,businessColumnName,businessId, delegateTask.getId(), delegateTask.getName(), auditResult,auditContent);
    	
    }

}
