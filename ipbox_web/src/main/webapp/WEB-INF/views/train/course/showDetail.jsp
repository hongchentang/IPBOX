<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="viewDetailPanel" class="easyui-panel" data-options="" style="height: 95%">
	<div id="viewDetailTabs" class="easyui-tabs" fit='true' data-options="tabPosition:'left',height:getHeight('viewDetailPanel',100),cache:false">
	
		<div id="viewCourseInfo" data-options="href:'${ctx}/train/course/view.do?paramMap[courseId]=${course.id}'" title="课程信息" ></div>
		
		<div id="showLogsTab" data-options="href:'${ctx}/train/courseFlow/listCourseFlow.do?paramMap[courseId]=${course.id}'" title="流程处理日志" ></div>
		
		<div id="showFlowImg" data-options="href:'${ctx}/common/flow/process/goTrace.do?procInstId=${course.procInstId}'" title="流程图" ></div>
		
	</div>
</div>
