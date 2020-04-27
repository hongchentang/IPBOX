<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div style="margin: 5px;margin-bottom: 27px">
	<!-- <div class="easyui-panel" title="课程管理" style="width: 100%;" > -->
		<form id="listCourseFlowForm" action="${ctx}/train/courseFlow/listCourseFlow.do" method="post">
			<input type="hidden" name="paramMap[courseId]" value="${searchParam.paramMap.courseId }">
			<div class="easyui-panel" title="" data-options="" style="width: 100%;">
				<div class="datagrid-toolbar" float:right>
				</div>
				<div>
					<table id="listCourseFlowTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
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
							<c:forEach items="${listCourseFlow }" var="courseFlow">
								<tr>
									<td>${courseFlow.taskName }</td>
									<td>${courseFlow.realName }</td>
									<td>${courseFlow.deptName }</td>
									<td><fmt:formatDate value="${courseFlow.auditTime }" type="date" pattern="yyyy-MM-dd hh:mm:ss"/></td>
									<td>${dict:getEntryName('FLOW_AUDIT_RESULT',courseFlow.auditResult) }</td>
									<td>${courseFlow.auditContent }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<jsp:include page="/jsp/common/include/manage_page_table.jsp">
						<jsp:param value="listCourseFlowForm" name="pageForm" />
						<jsp:param value="listCourseFlowTable" name="tableId" />
						<jsp:param value="easyui" name="type" />
						<jsp:param value="showLogsTab" name="divId" />
					</jsp:include>
				</div>
			</div>
		</form>
	</div>

<script>

</script>