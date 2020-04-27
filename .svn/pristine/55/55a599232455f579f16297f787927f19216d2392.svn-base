<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div style="margin: 5px;margin-bottom: 27px">
	<div class="easyui-panel" title="项目管理" style="width: 100%;" >
		<form id="listProjectForm" action="${ctx}/train/project/listProject.do?tabId=${param['tabId']}" method="post">
			<div class="easyui-panel" title="" data-options="" style="width: 100%;">
				<div class="datagrid-toolbar" float:right>
					<table cellspacin="0" cellpadding="3px">
						<tr>
							<td style="width: 60px">项目名称：</td>
							<td><input name="paramMap[projectName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.projectName }"></td>
							<td>
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxPostFormForPanel(getCurrentTabComId('listProjectForm'),getCurrentTabId())" iconCls="fa fa-search">查询</a>
							</td>
						</tr>	
					</table>
						<table cellspacing="0" cellpadding="0">
							<tbody>
								<tr>
									<td><a onclick="addProject()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-plus"> 新增</i></a></td>
									<td><div class="datagrid-btn-separator"></div></td>
									<td><a onclick="editProject()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil"> 修改</i></a></td>
									<td><div class="datagrid-btn-separator"></div></td>
									<td><a onclick="deleteProject()" href="javascript:void(0)" class="easyui-linkbutton delete-btn"><i class="fa fa-minus"> 删除</i></a></td>
									<td><div class="datagrid-btn-separator"></div></td>
									<td><a onclick="trainClass()" href="javascript:void(0)" class="easyui-linkbutton">培训班管理</a></td>
								</tr>
							</tbody>
						</table>
				</div>
				<div>
					<table id="listProjectTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
						<thead>
							<tr>
								<th width="30" data-options="field:'id',checkbox:true"></th>
								<th width="100" data-options="field:'projectCode'">项目编号</th>
								<th width="100" data-options="field:'projectName'">项目名称</th>
								<th width="100" data-options="field:'year'">项目实施年度</th>
								<th width="100" data-options="field:'expertId'">首席专家</th>
								<th width="100" data-options="field:'headId'">部门负责人</th>
								<th width="100" data-options="field:'headUnit'">项目执行部门</th>
								<th width="100" data-options="field:'projectSubject'">学科（领域）</th>
								<th width="100" data-options="field:'cash'">项目总经费</th>
								<th width="100" data-options="field:'status'">状态</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listProject }" var="project">
								<tr>
									<td>${project.id }</td>
									<td>${project.projectCode }</td>
									<td>${project.projectName }</td>
									<td>${project.year }</td>
									<td>${project.expertId }</td>
									<td>${project.headId }</td>
									<td>${project.headUnit }</td>
									<td>${project.projectSubject }</td>
									<td>${project.cash }</td>
									<td>${dict:getEntryName('PROJ_STATUS',project.status) }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<jsp:include page="/jsp/common/include/manage_page_table.jsp">
						<jsp:param value="listProjectForm" name="pageForm" />
						<jsp:param value="listProjectTable" name="tableId" />
						<jsp:param value="easyui" name="type" />
						<jsp:param value="${param['tabId']} " name="divId" />
					</jsp:include>
				</div>
			</div>
		</form>
	</div>
</div>
<script>

function addProject(){
	openWindow('addProjectWindow','新增项目',0,600,'${ctx}/train/project/goAddProject.do',true);
}

function editProject(){
	var objects = $('#listProjectTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects[0].status!='未申报'){
		$.messager.alert('消息','审核中或审核结束的课程不能进行修改、删除操作');
		return false;
	};
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('addProjectWindow','编辑项目',0,600,'${ctx}/train/project/goAddProject.do?id='+id,true);
}


function deleteProject(){
	var objects = $('#listProjectTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择要删除的数据');
		return false;
	}
	if(objects[0].status!='未申报'){
		$.messager.alert('消息','审核中或审核结束的课程不能进行修改、删除操作');
		return false;
	};
	var id = objects[0].id;
	$.messager.confirm('确认','确定想要删除选中的记录吗？', function(flag){
		if(flag){
			$.ajax({
				url:'${ctx}/train/project/deleteProject.do?id='+id,
				type:'post',
				/* //data:$('#listJSJBDYAllForm').serialize(), */
				success:function(){
					$.messager.alert('提示','操作成功！');
					getCurrentTab().panel('refresh');
				}
			});
		}
	});
}

function trainClass(){
	var objects = $('#listProjectTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据进行操作！');
		return false;
	} 
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('trainClassWindow','培训班管理(项目：'+objects[0].projectName+')',0,600,'${ctx}/train/train/listTrain.do?paramMap[projectId]='+id,true);
}
</script>