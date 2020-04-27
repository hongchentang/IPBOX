<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<form id="selectStudentForm" action="${ctx}/train/channel/selectStudent.do" method="post">
	<div class="easyui-panel" title="" data-options="" style="width: 100%;">
		<div class="datagrid-toolbar" float:right>
			<table cellspacin="0" cellpadding="3px">
				<tr>
					<td style="width: 80px">学员姓名：</td>
					<td><input name="paramMap[realName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.realName }"></td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:easyuiQuery('selectStudentForm','selectStudentWindow');" iconCls="fa fa-search">查询</a>
						<a onclick="addStudent()" href="javascript:void(0)" class="easyui-linkbutton">选择</a>
					</td>
				</tr>	
			</table>
		</div>
		<div>
			<table id="selectStudentTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="false">
				<thead>
					<tr>
						<th width="30" data-options="field:'teacherId',checkbox:true"></th>
						<th width="100" data-options="field:'userName'">用户名</th>
						<th width="100" data-options="field:'realName'">姓名</th>
						<th width="200" data-options="field:'deptName'">部门</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listStudent }" var="t">
						<tr>
							<td>${t.teacherId}</td>
							<td>${t.userName }</td>
							<td>${t.realName }</td>
							<td>${t.deptName }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="/jsp/common/include/manage_page_table.jsp">
				<jsp:param value="selectStudentForm" name="pageForm" />
				<jsp:param value="selectStudentTable" name="tableId" />
				<jsp:param value="easyui" name="type" />
				<jsp:param value="selectStudentWindow" name="divId" />
			</jsp:include>
		</div>
	</div>
</form>
<script>

function addStudent(){
	var student=$('#selectStudentTable').datagrid('getSelections');
	if(student.length==0){
		$.messager.alert('提示','请勾选学员！','warning');
	}else{
		for(var i=0;i<student.length;i++){
			$("#studentContent").append("<span id='student_id_"+student[i].teacherId+"'><input checked='checked' type='checkbox' name='studentId' value='"+student[i].teacherId+"'>"+student[i].realName+"<button type='button' class='easyui-linkbutton delete-btn' onclick=\"$('#student_id_"+student[i].teacherId+"').remove()\"> 删除</button> </span>")
		}
		$.parser.parse("#ask_saveFrom");
		closeWindow("selectStudentWindow");
	}
}

</script>