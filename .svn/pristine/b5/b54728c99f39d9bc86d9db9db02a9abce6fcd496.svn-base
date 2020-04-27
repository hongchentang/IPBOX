package com.hcis.ipanther.common.flow.service;

import java.util.List;

import com.hcis.ipanther.common.flow.entity.FlowLog;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * 通用流程日志服务类
 * @author wuwentao
 * @date 2015年3月30日
 */
public interface IFlowLogService extends IBaseService<FlowLog> {
	
	/**
	 * 保存流程流转日志
	 * @param tableName 对应业务表的表名
	 * @param businessColumnName 业务表中业务主键的字段名
	 * @param businessId 业务ID
	 * @param taskId 对应ACT_RU_TASK.ID_
	 * @param taskName 对应ACT_RU_TASK.NAME_
	 * @param auditResult 审核结果 字典：FLOW_AUDIT_RESULT  FlowConstants.AUDIT_RESULT_*
	 * @param auditContent 审核的内容、备注、说明
	 * @return
	 */
	public int save(String tableName,String businessColumnName,String businessId,
			String taskId,String taskName,String auditResult,String auditContent);

	/**
	 * 查询流程的日志
	 * @param procInstId 流程实例ID
	 * @param businessId 业务ID
	 * @return
	 */
	public List<FlowLog> listByBusinessId(SearchParam searchParam,String procInstId,String businessId);

	/**
	 * 根据业务ID找到最后一次的日志信息
	 * @param tableName 对应业务表的表名
	 * @param businessColumnName 业务表中业务主键的字段名
	 * @param businessId 业务ID
	 * @return
	 */
	public FlowLog getLastFlowLog(String tableName, String businessColumnName,String businessId);
	
}
