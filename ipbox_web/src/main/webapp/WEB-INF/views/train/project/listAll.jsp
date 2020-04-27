<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div style="margin: 5px;margin-bottom: 27px">
	<!-- <div class="easyui-panel" title="课程管理" style="width: 100%;" > -->
		<form id="listAllForm" action="${ctx}/train/project/listAll.do" method="post">
			<div class="easyui-panel" title="" data-options="" style="width: 100%;">
				<div class="datagrid-toolbar" float:right>
					<table cellspacin="0" cellpadding="3px">
						<tr>
							<td>项目名称</td>
							<td><input name="paramMap[projectName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.projectName }"></td>
							<td>
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxPostFormForPanel('listAllForm','viewProjectListAll')" iconCls="fa fa-search">查询</a>
							</td>
						</tr>	
					</table>
					<table cellspacing="0" cellpadding="0">
							<tbody>
								<tr>
									<td><a onclick="showDetail_pass()" href="javascript:void(0)" class="easyui-linkbutton">查看详细</a>	</td>
<!-- 									<td>&nbsp;<a onclick="showDetail_pass_del()" href="javascript:void(0)" class="easyui-linkbutton">删除</a>	</td> -->
								</tr>
							</tbody>
						</table>
				</div>
				<div>
					<table id="listAllTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
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
								<th width="100" data-options="field:'applyCount'">已申请次数</th>
								<th width="100" data-options="field:'maxApplyCount'">最大申请次数</th>
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
									<td>${project.applyCount }</td>
									<td>${project.maxApplyCount }</td>
									<td>${dict:getEntryName('PROJ_STATUS',project.status) }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<jsp:include page="/jsp/common/include/manage_page_table.jsp">
						<jsp:param value="listAllForm" name="pageForm" />
						<jsp:param value="listAllTable" name="tableId" />
						<jsp:param value="easyui" name="type" />
						<jsp:param value="viewProjectListAll" name="divId" />
					</jsp:include>
				</div>
			</div>
		</form>
	</div>

<script>
function showDetail_pass(){
	var objects = $('#listAllTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时查看多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('showDetailWindow','查看详细',0,600,'${ctx}/train/project/showDetail.do?id='+id,true); 
}
function showDetail_pass_del(){
	var objects = $('#listAllTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时删除多条数据');
		return false;
	}
	var id = objects[0].id;
	$.messager.confirm('确认','确定想要删除此项目及其对应的培训班记录吗？', function(flag){
		if(flag){
			$.ajax({
				url:'${ctx}/train/project/deleteProject.do?id='+id,
				type:'post',
				/* //data:$('#listJSJBDYAllForm').serialize(), */
				success:function(data){
					var json = jQuery.parseJSON(data);
					var responseCode = json.statusCode;
					if (parseInt(responseCode) == 200) {
						jQuery.messager.alert("提示信息","删除成功","info");
						jQuery("#viewProjectListAll").panel('refresh');
					} else {
						jQuery.messager.alert("提示信息","删除失败","error");
					}
				}
			});
		}
	});
}
</script>