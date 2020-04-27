<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="viewProDetailPanel" class="easyui-panel" data-options="" style="height: 95%">
	<div id="viewProDetailTabs" class="easyui-tabs" fit='true' data-options="tabPosition:'left',height:getHeight('viewProDetailPanel',100),cache:false">
	
		<div id="viewProInfo" data-options="href:'${ctx}/train/project/view.do?paramMap[projectId]=${project.id}'" title="项目信息" ></div>
		
		<div id="viewProTrain" data-options="href:'${ctx}/train/train/listTrainDetail.do?paramMap[projectId]=${project.id}'" title="培训班信息" ></div>
		
		<div id="showProLogsTab" data-options="href:'${ctx}/train/projectFlow/listProjectFlow.do?paramMap[projectId]=${project.id}'" title="流程处理日志" ></div>
		
		<div id="showProFlowImg" data-options="href:'${ctx}/common/flow/process/goTrace.do?procInstId=${project.procInstId}'" title="流程图" ></div>
		
	</div>
</div>
