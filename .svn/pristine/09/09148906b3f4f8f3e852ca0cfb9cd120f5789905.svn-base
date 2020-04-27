<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<form id="scoreInfoForm" action="${ctx}/space/archive/noskin/listScoreRegister.do" method="post">
	<div class="easyui-panel" title="" data-options="" style="width: 100%;">
		<div>
			<table id="scoreInfoTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
				<thead>
					<tr>
						<th width="20%" data-options="field:'id',hidden:true"></th>
						<th width="10%" data-options="field:'userId',hidden:true">用户ID</th>
						<th width="20%" data-options="field:'trainName'">培训班</th>
						<th width="10%" data-options="field:'realName'">姓名</th>
						<th width="20%" data-options="field:'deptName'">单位</th>
						<th width="40%" data-options="field:'showField'">课程成绩</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listScoreInfo }" var="t">
						<tr>
							<td>${t.id }</td>
							<td>${t.userId }</td>
							<td>${t.trainName }</td>
							<td>${t.realName }</td>
							<td>${t.deptName }</td>
							<td><font style="font-weight:bold;font-size: 14px;">${t.showField }</font></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="/jsp/common/include/manage_page_table.jsp">
				<jsp:param value="scoreInfoForm" name="pageForm" />
				<jsp:param value="scoreInfoTable" name="tableId" />
				<jsp:param value="easyui" name="type" />
				<jsp:param value="scoreTab" name="divId" />
			</jsp:include>
		</div>
	</div>
</form>
<script>


</script>