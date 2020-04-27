<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<form id="listRegTrainForm" action="${ctx}/train/register/listRegisterTrain.do" method="post">
	<div class="easyui-panel" title="" data-options="" style="width: 100%;">
		<div class="datagrid-toolbar" float:right>
			<table cellspacin="0" cellpadding="3px">
				<tr>
					<td style="width: 80px">培训班名称：</td>
					<td><input type='text' name="paramMap[trainName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.trainName }"></td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxPostFormForPanel('listRegTrainForm','${param['tabId']}')" iconCls="fa fa-search">查询</a>
					</td>
				</tr>	
			</table>
				<table cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td><a onclick="listRegTrainUser()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-plus"> 报名列表</i></a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="showDetail_regTrain()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil">查看报名情况</i></a></td>	
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a onclick="setStopApply()" href="javascript:void(0)" class="easyui-linkbutton">终止报名</a></td>			
						</tr>
					</tbody>
				</table>
		</div>
		<div>
			<table id="listRegTrainTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
				<thead>
					<tr>
						<th width="30" data-options="field:'id',checkbox:true"></th>
						<th width="100" data-options="field:'projectName'">项目</th>
						<th width="100" data-options="field:'trainName'">培训班名称</th>
						<th width="100" data-options="field:'trainCode'">培训班编码</th>
						<th width="90" data-options="field:'startTime'">选课起始日期</th>
						<th width="90" data-options="field:'endTime'">选课结束日期</th>
						<th width="90" data-options="field:'stopApply'">是否终止报名</th>
						<th width="90" data-options="field:'cashStartTime'">缴费起始日期</th>
						<th width="90" data-options="field:'cashEndTime'">缴费结束日期</th>
						<th width="90" data-options="field:'studyStartTime'">学习起始日期</th>
						<th width="90" data-options="field:'studyEndTime'">学习结束日期</th>
						<th width="90" data-options="field:'scoreTime'">成绩登记日期</th>
						<th width="90" data-options="field:'trainCount'">报名人数</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listTrain }" var="train">
						<tr>
							<td>${train.id }</td>
							<td>${train.projectName }</td>
							<td>${train.trainName }</td>
							<td>${train.trainCode }</td>
							<td><fmt:formatDate value="${train.startTime }"  pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${train.endTime }"  pattern="yyyy-MM-dd"/></td>
							<td >${train.isStopApply eq '1'?'<span style="color: red;">是</span>':'否' }</td>
							<td><fmt:formatDate value="${train.cashStartTime }"  pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${train.cashEndTime }"  pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${train.studyStartTime }"  pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${train.studyEndTime }"  pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${train.scoreTime }"  pattern="yyyy-MM-dd"/></td>
							<td>${train.registerCount }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="/jsp/common/include/manage_page_table.jsp">
				<jsp:param value="listRegTrainForm" name="pageForm" />
				<jsp:param value="listRegTrainTable" name="tableId" />
				<jsp:param value="easyui" name="type" />
				<jsp:param value="${param['tabId']}" name="divId" />
			</jsp:include>
		</div>
	</div>
</form>
<script>

function listRegTrainUser(){
	var objects = $('#listRegTrainTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('regUserWindow','报名列表',600,500,'${ctx}/train/register/listRegTrainUser.do?paramMap[trainId]='+id+'&tabId=${param["tabId"]}',true);
}
function showDetail_regTrain(){
	var objects = $('#listRegTrainTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('showDetailWindow','报名情况',0,600,'${ctx}/train/register/showTrainDetail.do?paramMap[trainId]='+id,true);
}
function setStopApply(){
	var objects = $('#listRegTrainTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var id = objects[0].id;
	objects[0].trainName
	$.messager.confirm('确认','是否终止"'+objects[0].trainName+'"报名！',function(result){    
		if (result){   
			$.ajax({
				url:'${ctx}/train/train/stopApply.do?id='+id,
				type:'post', 
				success:function(){
					$.messager.alert('提示','操作成功！');
					$("#${param['tabId']}").panel('refresh');
				}
			});
		}
	});
}
</script>