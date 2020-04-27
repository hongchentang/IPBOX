<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<form id="divideTeacherForm" action="${ctx}/train/courseTeacher/selectTeacher.do" method="post">
<input type="hidden" name="paramMap[devideType]" value="${searchParam.paramMap.devideType }">
<input type="hidden" name="paramMap[teacherIds]" value="${searchParam.paramMap.teacherIds }">
	<div class="easyui-panel" title="" data-options="" style="width: 100%;">
		<div class="datagrid-toolbar" float:right>
			<table cellspacin="0" cellpadding="3px">
				<tr>
					<td style="width: 80px">教师选择：</td>
					<td><input name="paramMap[realName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.realName }"></td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxPostFormForPanel('divideTeacherForm','divideTeacherWindow')" iconCls="fa fa-search">查询</a>
						<a onclick="addTeacher()" href="javascript:void(0)" class="easyui-linkbutton">选择</a>
					</td>
				</tr>	
			</table>
		</div>
		<div>
			<table id="divideTeacherTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
				<thead>
					<tr>
						<th width="30" data-options="field:'teacherId',checkbox:true"></th>
						<th width="100" data-options="field:'userName'">用户名</th>
						<th width="100" data-options="field:'realName'">姓名</th>
						<th width="100" data-options="field:'deptName'">部门</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listTeacher }" var="t">
						<tr>
							<td>${t.teacherId }</td>
							<td>${t.userName }</td>
							<td>${t.realName }</td>
							<td>${t.deptName }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="/jsp/common/include/manage_page_table.jsp">
				<jsp:param value="divideTeacherForm" name="pageForm" />
				<jsp:param value="divideTeacherTable" name="tableId" />
				<jsp:param value="easyui" name="type" />
				<jsp:param value="divideTeacherWindow" name="divId" />
			</jsp:include>
		</div>
	</div>
</form>
<script>

function addTeacher(){
	var teacher=$('#divideTeacherTable').datagrid('getSelections');
	if(teacher.length==0){
		$.messager.alert('提示','请勾选所选教师！','warning');
	}else{
		$('#realName').textbox('setText',teacher[0].realName);
		$('#teacherUnit').textbox('setValue',teacher[0].deptName);
		$("#teacherName").val(teacher[0].teacherId);
		closeWindow("divideTeacherWindow");
	}
}

</script>