<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="viewAuditPanel" class="easyui-panel" data-options="" style="height: 95%">
	<div id="viewAuditTabs" class="easyui-tabs" data-options="tabPosition:'top',height:getHeight('viewAuditPanel',100),cache:false">
		<div id="viewAuditTodo" data-options="href:'${ctx}/train/audit/listAuditUser.do?paramMap[auditUserWindow]=viewAuditTodo&paramMap[status]=00'" title="待审核" ></div>
		<div id="viewAuditY" data-options="href:'${ctx}/train/audit/listAuditUser.do?paramMap[auditUserWindow]=viewAuditY&paramMap[status]=01'" title="已通过" ></div>
		<div id="viewAuditN" data-options="href:'${ctx}/train/audit/listAuditUser.do?paramMap[auditUserWindow]=viewAuditN&paramMap[status]=02'" title="未通过" ></div>
	</div>
</div>