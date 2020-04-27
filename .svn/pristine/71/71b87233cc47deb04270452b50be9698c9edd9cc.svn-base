<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<form id="selectStudentForm" action="${ctx}/train/register/selectStudent.do?paramMap[trainId]=${searchParam.paramMap.trainId }&tabId=${param['tabId']}" method="post">
	<div class="easyui-panel" title="" data-options="" style="width: 100%;">
		<div class="datagrid-toolbar" float:right>
			<table cellspacin="0" cellpadding="3px">
				<tr>
					<td style="width: 80px">学员姓名：</td>
					<td><input name="paramMap[realName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.realName }"></td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:easyuiQuery('selectStudentForm','selectStudentWindow');" iconCls="fa fa-search">查询</a>
					</td>
				</tr>	
			</table>
			<table cellspacing="0" cellpadding="0">
							<tbody>
								<tr>
									<td><div class="datagrid-btn-separator"></div></td>
									<td><a onclick="register()" href="javascript:void(0)" class="easyui-linkbutton">报名</a></td>
								</tr>
							</tbody>
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
<form id="saveRegisterFrom" action="${ctx}/train/register/saveTrainRegister.do" method="post" >
	<input type="hidden" name="paramMap[trainId]" value="${searchParam.paramMap.trainId }">
	<input id="studentIds" type="hidden" name="paramMap[studentIds]" value="">
</form>
<script>

function register(){
	var student_ids='';
	var student=$('#selectStudentTable').datagrid('getSelections');
	if(student.length==0){
		$.messager.alert('提示','请勾选报名学员！','warning');
	}else{
		$.messager.confirm('确认','是否确认给予勾选中的学员报名？',function(result){    
			if (result){  
				for(var i=0;i<student.length;i++){
					if(i==student.length-1){
						student_ids+=student[i].teacherId;
					}else{
						student_ids+=(student[i].teacherId+",");
					}
				}
				$("#studentIds").val(student_ids);
				
				$('#saveRegisterFrom').form('submit',{
					success: function(data){
						var json = eval('(' + data + ')');  
						if(!jQuery.isEmptyObject(json)){
							var message = json.message;
							var statusCode = json.statusCode;
							if(parseInt(statusCode)==300){
								$.messager.alert('提示',message);
							}else if(parseInt(statusCode)==200){
								closeWindow("selectStudentWindow");
								$('#regUserWindow').panel('refresh');
								$('#${param["tabId"]}').panel('refresh');
								$.messager.alert('提示',message);
							}
						}else{
							$.messager.alert('提示','json is null');
						}
					}
				});
			}
		});
	}
}

</script>