<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<form id="listTrainForm" action="${ctx}/train/train/listTrain.do" method="post">
<input type="hidden" name="paramMap[projectId]" value="${searchParam.paramMap.projectId }">
	<div class="easyui-panel" title="" data-options="" style="width: 100%;">
		<div class="datagrid-toolbar" float:right>
			<table cellspacin="0" cellpadding="3px">
				<tr>
					<td style="width: 80px">培训班名称：</td>
					<td><input type='text' name="paramMap[trainName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.trainName }"></td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxPostFormForPanel('listTrainForm','trainClassWindow')" iconCls="fa fa-search">查询</a>
					</td>
				</tr>	
			</table>
				<table cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td><a onclick="viewTrain()" href="javascript:void(0)" class="easyui-linkbutton">查看</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="addTrain()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-plus"> 新增</i></a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="editTrain()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil"> 修改</i></a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="deleteTrain()" href="javascript:void(0)" class="easyui-linkbutton delete-btn"><i class="fa fa-minus"> 删除</i></a></td>
							<td><div class="datagrid-btn-separator"></div></td>
						</tr>
					</tbody>
				</table>
		</div>
		<div>
			<table id="listTrainTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
				<thead>
					<tr>
						<th width="30" data-options="field:'id',checkbox:true"></th>
						<th width="100" data-options="field:'trainCode'">培训班编码</th>
						<th width="100" data-options="field:'trainName'">培训班名称</th>
						<th width="100" data-options="field:'startTime'">选课起始日期</th>
						<th width="100" data-options="field:'endTime'">选课结束日期</th>
						<th width="100" data-options="field:'cashStartTime'">缴费起始日期</th>
						<th width="100" data-options="field:'cashEndTime'">缴费结束日期</th>
						<th width="100" data-options="field:'studyStartTime'">学习起始日期</th>
						<th width="100" data-options="field:'studyEndTime'">学习结束日期</th>
						<th width="100" data-options="field:'scoreTime'">成绩登记日期</th>
						<th width="100" data-options="field:'roomStartTime'">住宿开始日期</th>
						<th width="100" data-options="field:'roomEndTime'">住宿结束日期</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listTrain }" var="train">
						<tr>
							<td>${train.id }</td>
							<td>${train.trainCode }</td>
							<td>${train.trainName }</td>
							<td><fmt:formatDate value="${train.startTime }"  pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${train.endTime }"  pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${train.cashStartTime }"  pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${train.cashEndTime }"  pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${train.studyStartTime }"  pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${train.studyEndTime }"  pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${train.scoreTime }"  pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${train.roomStartTime }"  pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${train.roomEndTime }"  pattern="yyyy-MM-dd"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="/jsp/common/include/manage_page_table.jsp">
				<jsp:param value="listTrainForm" name="pageForm" />
				<jsp:param value="listTrainTable" name="tableId" />
				<jsp:param value="easyui" name="type" />
				<jsp:param value="trainClassWindow" name="divId" />
			</jsp:include>
		</div>
	</div>
</form>
<script>

function addTrain(){
	openWindow('addTrainWindow','新增培训班',0,600,'${ctx}/train/train/goAddTrain.do?projectId=${searchParam.paramMap.projectId }',true);
}

function editTrain(){
	var objects = $('#listTrainTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('addTrainWindow','编辑培训班',0,600,'${ctx}/train/train/goAddTrain.do?id='+id+'&projectId=${searchParam.paramMap.projectId }',true);
}

function viewTrain(){
	var objects = $('#listTrainTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('viewTrainWindow','查看培训班信息',0,600,'${ctx}/train/train/view.do?id='+id+'&projectId=${searchParam.paramMap.projectId }',true);
}

function deleteTrain(){
	var objects = $('#listTrainTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择要删除的数据');
		return false;
	}
	
	var id = objects[0].id;
	$.messager.confirm('确认','确定想要删除选中的记录吗？', function(flag){
		if(flag){
			$.ajax({
				url:'${ctx}/train/train/deleteTrain.do?id='+id,
				type:'post',
				/* //data:$('#listJSJBDYAllForm').serialize(), */
				success:function(){
					$.messager.alert('提示','操作成功！');
					$("#trainClassWindow").panel('refresh');
				}
			});
		}
	});
}

</script>