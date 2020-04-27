<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<form id="scoreInfoForm" action="${ctx}/train/courseRegister/listScoreInfo.do?paramMap[trainId]=${searchParam.paramMap.trainId }" method="post">
	<div class="easyui-panel" title="" data-options="" style="width: 100%;">
		<div class="datagrid-toolbar" float:right>
			<table cellspacin="0" cellpadding="3px">
				<tr>
					<td style="width: 80px">学员姓名：</td>
					<td><input name="paramMap[realName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.realName }"></td>
					<td >部门：</td>
					<td><input name="paramMap[deptName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.deptName }"></td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:easyuiQuery('scoreInfoForm','scoreInfoWindow');" iconCls="fa fa-search">查询</a>
					</td>
				</tr>	
			</table>
			
		</div>
		<div>
			<table id="scoreInfoTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
				<thead>
					<tr>
						<th width="30" data-options="field:'id',checkbox:true"></th>
						<th width="120" data-options="field:'userId',hidden:true">用户ID</th>
						<th width="120" data-options="field:'realName'">姓名</th>
						<th width="200" data-options="field:'deptName'">部门</th>
						<th width="600" data-options="field:'showField'">课程成绩</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listScoreInfo }" var="t">
						<tr>
							<td>${t.id }</td>
							<td>${t.userId }</td>
							<td>${t.realName }</td>
							<td>${t.deptName }</td>
							<td><font style="font-weight:bold;font-size: 12px;">${t.showField }</font></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="/jsp/common/include/manage_page_table.jsp">
				<jsp:param value="scoreInfoForm" name="pageForm" />
				<jsp:param value="scoreInfoTable" name="tableId" />
				<jsp:param value="easyui" name="type" />
				<jsp:param value="scoreInfoWindow" name="divId" />
			</jsp:include>
		</div>
	</div>
</form>
<script>


</script>