<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div style="margin: 5px;margin-bottom: 27px">
		<form id="listProjectFlowForm" action="${ctx}/train/projectFlow/listProjectFlow.do" method="post">
			<input type="hidden" name="paramMap[projectId]" value="${searchParam.paramMap.projectId }">
			<div class="easyui-panel" title="" data-options="" style="width: 100%;">
				<div class="datagrid-toolbar" float:right>
				</div>
				<div>
					<table id="listProjectFlowTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
						<thead>
							<tr>
								<th width="100" data-options="field:'taskName'">审核环节</th>
								<th width="100" data-options="field:'realName'">审核人</th>
								<th width="100" data-options="field:'deptName'">所属部门</th>
								<th width="150" data-options="field:'auditTime'">审核时间</th>
								<th width="100" data-options="field:'auditResult'">审核结果</th>
								<th width="200" data-options="field:'auditContent'">审核意见</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listProFlow }" var="proFlow">
								<tr>
									<td>${proFlow.taskName }</td>
									<td>${proFlow.realName }</td>
									<td>${proFlow.deptName }</td>
									<td><fmt:formatDate value="${proFlow.auditTime }" type="date" pattern="yyyy-MM-dd hh:mm:ss"/></td>
									<td>${dict:getEntryName('FLOW_AUDIT_RESULT',proFlow.auditResult) }</td>
									<td>${proFlow.auditContent }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<jsp:include page="/jsp/common/include/manage_page_table.jsp">
						<jsp:param value="listProjectFlowForm" name="pageForm" />
						<jsp:param value="listProjectFlowTable" name="tableId" />
						<jsp:param value="easyui" name="type" />
						<jsp:param value="showProLogsTab" name="divId" />
					</jsp:include>
				</div>
			</div>
		</form>
	</div>

<script>

</script>