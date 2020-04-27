<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div style="margin: 5px;margin-bottom: 27px">
		<form id="listProTodoForm" action="${ctx}/train/project/listTodo.do" method="post">
			<div class="easyui-panel" title="" data-options="" style="width: 100%;">
				<div class="datagrid-toolbar" float:right>
					<table cellspacin="0" cellpadding="3px">
						<tr>
							<td>项目名称</td>
							<td><input name="paramMap[projectName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.projectName }"></td>
							<td>
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:easyuiQuery(getCurrentTabComId('listProTodoForm',false),getCurrentTabComId('viewProjectListTodo',false));" iconCls="fa fa-search">查询</a>
							</td>
						</tr>	
					</table>
						<table cellspacing="0" cellpadding="0">
							<tbody>
								<tr>
									<td><a onclick="showDetailPro_todo()" href="javascript:void(0)" class="easyui-linkbutton">查看详细</a>	</td>
									<td><div class="datagrid-btn-separator"></div></td>
									<td><a onclick="editPro_todo()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil"> 修改</i></a></td>
									<td><div class="datagrid-btn-separator"></div></td>
								    <td><a onclick="deletePro_todo()" href="javascript:void(0)" class="easyui-linkbutton delete-btn"><i class="fa fa-minus"> 删除</i></a></td> 
									<td><div class="datagrid-btn-separator"></div></td>
									<td><a onclick="auditPro_todo()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil">办理</i></a>	</td>
									<td><div class="datagrid-btn-separator"></div></td>
									<td><a onclick="trainClass_todo()" href="javascript:void(0)" class="easyui-linkbutton">培训班管理</a></td>
									<td><div class="datagrid-btn-separator"></div></td>
									<td><a onclick="declarePro_todo()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil">申报</i></a></td>
								</tr>
							</tbody>
						</table>
				</div>
				<div>
					<table id="listProTodoTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
						<thead>
							<tr>
								<th width="30" data-options="field:'procInstId',hidden:true"></th>
								<th width="30" data-options="field:'id',checkbox:true"></th>
								<th width="100" data-options="field:'projectCode'">项目编号</th>
								<th width="100" data-options="field:'projectName'">项目名称</th>
								<th width="100" data-options="field:'year'">项目实施年度</th>
								<th width="100" data-options="field:'expertId'">首席专家</th>
								<th width="100" data-options="field:'headId'">部门负责人</th>
								<th width="100" data-options="field:'headUnit'">项目执行部门</th>
								<th width="100" data-options="field:'projectSubject'">学科（领域）</th>
								<th width="100" data-options="field:'cash'">项目总经费</th>
								<th width="100" data-options="field:'applyCount'">已申请次数</th>
								<th width="100" data-options="field:'maxApplyCount'">最大申请次数</th>
								<th width="100" data-options="field:'status'">状态</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listProject }" var="project">
								<tr>
								<td>${project.procInstId }</td>
									<td>${project.id }</td>
									<td>${project.projectCode }</td>
									<td>${project.projectName }</td>
									<td>${project.year }</td>
									<td>${project.expertId }</td>
									<td>${project.headId }</td>
									<td>${project.headUnit }</td>
									<td>${project.projectSubject }</td>
									<td>${project.cash }</td>
									<td>${project.applyCount }</td>
									<td>${project.maxApplyCount }</td>
									<td>${dict:getEntryName('PROJ_STATUS',project.status) }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<jsp:include page="/jsp/common/include/manage_page_table.jsp">
						<jsp:param value="listProTodoForm" name="pageForm" />
						<jsp:param value="listProTodoTable" name="tableId" />
						<jsp:param value="easyui" name="type" />
						<jsp:param value="viewProjectListTodo" name="divId" />
					</jsp:include>
				</div>
			</div>
		</form>
	</div>

<script>

function editPro_todo(){
	var objects = $('#listProTodoTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects[0].status!='已退回'){
		$.messager.alert('消息','不能进行修改操作！');
		return false;
	};
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('addProjectWindow','编辑项目',0,600,'${ctx}/train/project/goAddProject.do?id='+id+'&paramMap[rePanel]=viewProjectListTodo',true);
}

function deletePro_todo(){
	var objects = $('#listProTodoTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择要删除的数据');
		return false;
	}
	 if(objects[0].status!='已退回'){
		$.messager.alert('消息','该列表只能删除状态为已退回的项目!');
		return false;
	}; 
	var id = objects[0].id;
	$.messager.confirm('确认','确定想要删除选中的记录吗？', function(flag){
		if(flag){
			$.ajax({
				url:'${ctx}/train/project/deleteProject.do?id='+id+"&procInstId="+objects[0].procInstId,
				type:'post',
				/* //data:$('#listJSJBDYAllForm').serialize(), */
				success:function(data){
					var json = jQuery.parseJSON(data);
					var responseCode = json.statusCode;
					if (parseInt(responseCode) == 200) {
						jQuery.messager.alert("提示信息","删除成功","info");
						jQuery("#viewProjectListTodo").panel('refresh');
					} else {
						jQuery.messager.alert("提示信息","删除失败","error");
					}
				}
			});
		}
	});
}

function trainClass_todo(){
	var objects = $('#listProTodoTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据进行操作！');
		return false;
	} 
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	if(objects[0].status!='已退回'){
		$.messager.alert('消息','审核中的数据不能进行培训班管理操作！');
		return false;
	};
	var id = objects[0].id;
	openWindow('trainClassWindow','培训班管理(项目：'+objects[0].projectName+')',0,600,'${ctx}/train/train/listTrain.do?paramMap[projectId]='+id,true);
}


function declarePro_todo(){
	var objects = $('#listProTodoTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据进行操作！');
		return false;
	} 
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	if(objects[0].status!='已退回'){
		$.messager.alert('消息','该数据不能进行申报操作！');
		return false;
	};
	var id = objects[0].id;
	$.messager.confirm('确认','是否确定申报"'+objects[0].projectName+'"项目？', function(flag){
		if(flag){
			 $.ajax({
				url:'${ctx}/train/project/declareProject.do?id='+id,
				type:'post',
				 //data:$('#listJSJBDYAllForm').serialize(), 
				success:function(){
					$.messager.alert('提示','操作成功！');
					$("#viewProjectListTodo").panel('refresh');
				}
			}); 
		}
	});
} 
function auditPro_todo(){
	var objects = $('#listProTodoTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects[0].status!='待审核'){
		$.messager.alert('消息','该课程还不能审核');
		return false;
	};
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('auditProjectWindow','项目审核',0,600,'${ctx}/train/project/goAudit.do?id='+id,true);
}
function showDetailPro_todo(){
	var objects = $('#listProTodoTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('showDetailWindow','详细查看',0,600,'${ctx}/train/project/showDetail.do?id='+id,true);
}
</script>