<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<form id="listCourseRegisterTrain" action="${ctx}/train/courseRegister/listCourseRegisterTrain.do" method="post">
	<div class="easyui-panel" title="" data-options="" style="width: 100%;">
		<div class="datagrid-toolbar" float:right>
			<table cellspacin="0" cellpadding="3px">
				<tr>
					<td style="width: 80px">项目名称：</td>
					<td><input type='text' name="paramMap[projectName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.projectName }"></td>
					<td style="width: 80px">培训班名称：</td>
					<td><input type='text' name="paramMap[trainName]" class="easyui-validatebox" style="width:100px " value="${searchParam.paramMap.trainName }"></td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxPostFormForPanel('listCourseRegisterTrain','${param['tabId']}')" iconCls="fa fa-search">查询</a>
					</td>
				</tr>	
			</table>
				<table cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td><a onclick="viewTrain()" href="javascript:void(0)" class="easyui-linkbutton">培训班详细</a></td>	
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="listScoreInfo()" href="javascript:void(0)" class="easyui-linkbutton">查看学员成绩</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="exportTemplate()" href="javascript:void(0)" class="easyui-linkbutton">导出评分模板</a></td>	
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="seniorTemplateView()" href="javascript:void(0)" class="easyui-linkbutton">高级模板</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="goImport()" href="javascript:void(0)" class="easyui-linkbutton">成绩导入</a></td>
						</tr>
					</tbody>
				</table>
		</div>
		<div>
			<table id="listCourseRegisterTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
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
						<th width="100" data-options="field:'projectName'">所属项目</th>
						<th width="100" data-options="field:'trainCount'">报名人数</th>
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
							<td>${train.projectName }</td>
							<td>${train.registerCount }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="/jsp/common/include/manage_page_table.jsp">
				<jsp:param value="listCourseRegisterTrain" name="pageForm" />
				<jsp:param value="listCourseRegisterTable" name="tableId" />
				<jsp:param value="easyui" name="type" />
				<jsp:param value="${param['tabId']}" name="divId" />
			</jsp:include>
		</div>
	</div>
</form>
<form id="exportForm" action="${ctx}/train/courseRegister/exportTemplate.do" method="post">
<input id="trainId_" type="hidden" name="paramMap[trainId]" value="">
<input id="trainName_" type="hidden" name="paramMap[trainName]" value="">

</form>
<script>

function listScoreInfo(){
	var objects = $('#listCourseRegisterTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条培训班数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时查看多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('scoreInfoWindow',objects[0].trainName+'班学员成绩',0,500,'${ctx}/train/courseRegister/listScoreInfo.do?paramMap[trainId]='+id,true);
}
function viewTrain(){
	var objects = $('#listCourseRegisterTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条培训班数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时查看多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('viewTrainWindow','查看培训班信息',0,600,'${ctx}/train/train/view.do?id='+id,true);
}
function exportTemplate(){
	var objects = $('#listCourseRegisterTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条培训班数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var trainId = objects[0].id;
	var trainName=objects[0].trainName;
	$("#trainId_").val(trainId);
	$("#trainName_").val(trainName);
	$.messager.confirm('确认','是否确认导出'+trainName+'班下的学员评分导入模板？',function(result){
		if(result){
			 $("#exportForm").submit();
		}
	});
}
function seniorTemplateView(){
	var objects = $('#listCourseRegisterTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条培训班数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var trainId = objects[0].id;
	openWindow('seniorTemplateWindow',objects[0].trainName+'班高级模板',400,300,'${ctx}/train/courseRegister/seniorTemplate.do?paramMap[trainId]='+trainId,true);
}
function goImport(){
	openWindow('importWindow','成绩导入',500,200,'${ctx}/train/courseRegister/goImport.do',true);
}
</script>