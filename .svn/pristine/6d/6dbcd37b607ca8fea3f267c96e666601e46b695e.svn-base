<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<form id="regUserForm" action="${ctx}/train/register/listRegTrainUser.do?paramMap[trainId]=${searchParam.paramMap.trainId }&tabId=${param['tabId']}" method="post">
	<input type="hidden" name="paramMap[showType]" value="${searchParam.paramMap.showType }">
	<div class="easyui-panel" title="" data-options="" style="width: 100%;">
		<div class="datagrid-toolbar" float:right>
			<table cellspacin="0" cellpadding="3px">
				<tr>
					<td style="width: 80px">学员姓名：</td>
					<td><input name="paramMap[realName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.realName }"></td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:easyuiQuery('regUserForm','regUserWindow');" iconCls="fa fa-search">查询</a>
					</td>
				</tr>	
			</table>
			<c:if test="${empty searchParam.paramMap.showType }">
			<table cellspacing="0" cellpadding="0">
							<tbody>
								<tr>
									<td><a onclick="selectStudent()" href="javascript:void(0)" class="easyui-linkbutton">新增报名</a></td>
									<td><div class="datagrid-btn-separator"></div></td>
									<td><a onclick="batchDel()" href="javascript:void(0)" class="easyui-linkbutton">删除报名</a></td>
								</tr>
							</tbody>
						</table>
				</c:if>
		</div>
		<div>
			<table id="regUserTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="false">
				<thead>
					<tr>
						<th width="30" data-options="field:'id',checkbox:true"></th>
						<th width="120" data-options="field:'userId',hidden:true">用户ID</th>
						<th width="120" data-options="field:'userName'">用户名</th>
						<th width="120" data-options="field:'realName'">姓名</th>
						<th width="200" data-options="field:'deptName'">部门</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listregTrainUser }" var="t">
						<tr>
							<td>${t.id }</td>
							<td>${t.userId }</td>
							<td>${t.userName }</td>
							<td>${t.realName }</td>
							<td>${t.deptName }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="/jsp/common/include/manage_page_table.jsp">
				<jsp:param value="regUserForm" name="pageForm" />
				<jsp:param value="regUserTable" name="tableId" />
				<jsp:param value="easyui" name="type" />
				<jsp:param value="regUserWindow" name="divId" />
			</jsp:include>
		</div>
	</div>
</form>
<form id="deleteRegisterFrom" action="${ctx}/train/register/deleteTrainUser.do" method="post" >
	<input id="registerIds" type="hidden" name="paramMap[registerIds]" value="">
	<input type="hidden" name="paramMap[trainId]" value="${searchParam.paramMap.trainId }">
</form>
<script>

function batchDel(){
	var registerIds='';
	var student=$('#regUserTable').datagrid('getSelections');
	if(student.length==0){
		$.messager.alert('提示','请勾需要删除的学员报名！','warning');
	}else{
		$.messager.confirm('确认','是否删除勾选中的学员报名？',function(result){    
			if (result){  
				for(var i=0;i<student.length;i++){
					if(i==student.length-1){
						registerIds+=student[i].userId;
					}else{
						registerIds+=(student[i].userId+",");
					}
				}
				$("#registerIds").val(registerIds);
				
				$('#deleteRegisterFrom').form('submit',{
					success: function(data){
						var json = eval('(' + data + ')');  
						if(!jQuery.isEmptyObject(json)){
							var message = json.message;
							var statusCode = json.statusCode;
							if(parseInt(statusCode)==300){
								$.messager.alert('提示',message);
							}else if(parseInt(statusCode)==200){
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

function selectStudent(){
	openWindow('selectStudentWindow','选择学员',550,500,'${ctx}/train/register/selectStudent.do?paramMap[trainId]=${searchParam.paramMap.trainId }&tabId=${param["tabId"]}',true);
}
</script>