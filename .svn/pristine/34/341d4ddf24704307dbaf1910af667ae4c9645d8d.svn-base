<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<form id="auditUserForm" action="${ctx}/train/audit/listAuditUser.do" method="post">
	<input type="hidden" name="paramMap[auditUserWindow]" value="${searchParam.paramMap.auditUserWindow }">
	<input type="hidden" name="paramMap[status]" value="${searchParam.paramMap.status }">
	<div class="easyui-panel" title="" data-options="" style="width: 100%;">
		<div class="datagrid-toolbar" float:right>
			学员姓名：
			<input name="paramMap[realName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.realName }">
			培训班名称：
			<input name="paramMap[trainName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.trainName }">
			是否住宿：
			<input id="isRoomY" name="paramMap[isRoom]" type="radio" class="easyui-validatebox" ${searchParam.paramMap.isRoom eq '01' ?'checked="checked"':''} value="01"/>是
            <input id="isRoomN" name="paramMap[isRoom]" type="radio" class="easyui-validatebox" ${searchParam.paramMap.isRoom eq '00' or  empty searchParam.paramMap.isRoom ?'checked="checked"':''} value="00"/>否&nbsp;&nbsp;&nbsp;
			<c:if test="${searchParam.paramMap.status eq '00' }">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="goAuditUser()">审核</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="viewInfo('00')">查看学员信息</a>
<!-- 				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="goBatchAuditUser()">批量审核</a> -->
				<input type="hidden" name="action" id="action" value=""/><%--导出的标识 --%>
				<button type="button" id="exportButton" class="easyui-linkbutton" onclick="exportUserTodo()"><i class="fa "></i>导出结果</button>
			</c:if>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:easyuiQuery('auditUserForm','${searchParam.paramMap.auditUserWindow }')" >查询</a>
			<c:if test="${searchParam.paramMap.status eq '01' }">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="viewInfo('01')">查看学员信息</a>
				<input type="hidden" name="action" id="action" value=""/><%--导出的标识 --%>
				<button type="button" id="exportButton" class="easyui-linkbutton" onclick="exportUser()"><i class="fa "></i>导出结果</button>
			</c:if>
			<c:if test="${searchParam.paramMap.status eq '02'}">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="viewInfo('02')">查看学员信息</a>
				<input type="hidden" name="action" id="action" value=""/><%--导出的标识 --%>
				<button type="button" id="exportButton" class="easyui-linkbutton" onclick="exportUser_()"><i class="fa "></i>导出结果</button>
			</c:if>
		</div>
		<div>
			<table id="auditUserTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="false">
				<thead>
					<tr>
						<th width="30" data-options="field:'id',checkbox:true"></th>
						<th width="100" data-options="field:'trainId',hidden:'true'">培训班编码</th>
						<th width="100" data-options="field:'userId',hidden:'true'">用户编码</th>
						<!-- <th width="100" data-options="field:'userName'">用户名</th> -->
						<th width="100" data-options="field:'projectName'">项目</th>
						<th width="100" data-options="field:'trainName'">培训班</th>
						<th width="100" data-options="field:'realName'">姓名</th>
						<th width="100" data-options="field:'isRoom'">是否住宿</th>
						<th width="200" data-options="field:'roomStartTime'">住宿开始时间</th>
						<th width="200" data-options="field:'roomEndTime'">住宿结束时间</th>
						<th width="100" data-options="field:'paperWorkNo'">身份证</th>
						<th width="200" data-options="field:'deptName'">单位</th>
						<th width="200" data-options="field:'mobilePhone'">联系方式</th>
						<th width="200" data-options="field:'email'">邮箱</th>
						<th width="200" data-options="field:'createTime'">报名时间</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="t">
						<tr>
							<td>${t.id }</td>
							<td>${t.trainId }</td>
							<td>${t.userId }</td>
<%-- 							<td>${t.userName }</td> --%>
							<td>${t.projectName }</td>
							<td>${t.trainName }</td>
							<td>${t.realName }</td>
							<td>${t.isRoom eq '01' ?'是':'否'}</td>
							<td><fmt:formatDate value="${t.roomStartTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${t.roomEndTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${t.paperWorkNo }</td>
							<td>${empty t.belongDeptName?t.deptName:t.belongDeptName }</td>
							<td>${t.mobilePhone }</td>
							<td>${t.email}</td>
							<td><fmt:formatDate value="${t.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="/jsp/common/include/manage_page_table.jsp">
				<jsp:param value="auditUserForm" name="pageForm" />
				<jsp:param value="auditUserTable" name="tableId" />
				<jsp:param value="easyui" name="type" />
				<jsp:param value="${searchParam.paramMap.auditUserWindow }" name="divId" />
			</jsp:include>
		</div>
	</div>
</form>

<script>
// function goBatchAuditUser(){
// 	var objects = $('#auditUserTable',"#auditUserForm").datagrid('getSelections');
// 	if(objects.length == 0){
// 		$.messager.alert('消息','请选择一条数据');
// 		return false;
// 	}
// 	openWindow('auditUserWindow','批量审核',650,250,'${ctx}/train/audit/goBatchAudit.do?paramMap[count]='+objects.length,true);
	
// }

function goAuditUser(){
	var objects = $('#auditUserTable',"#auditUserForm").datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	}
	openWindow('auditUserWindow','审核',650,250,'${ctx}/train/audit/goAudit.do?paramMap[count]='+objects.length,true);
	
}

function viewInfo(status){
	if(status == "00"){
		var objects = jQuery("#viewAuditTodo").find('#auditUserForm').find("#auditUserTable").datagrid('getSelections');
	}else if(status == "01"){
		var objects = jQuery("#viewAuditY").find('#auditUserForm').find("#auditUserTable").datagrid('getSelections');
	}else{
		var objects = jQuery("#viewAuditN").find('#auditUserForm').find("#auditUserTable").datagrid('getSelections');
	}
	
	if(objects.length == 0||objects.length >1){
		$.messager.alert('消息','请选择一条数据');
		return false;
	}
	openWindow('userView','学员信息',650,650,'${ctx}/common/user/student/view.do?id='+objects[0].userId,true);
}
function exportUserTodo() {
	$.messager.show({
		title:'提示',
		msg:'系统正在导出，请稍后',
		timeout:2000,
		showType:'slide'
	});
	jQuery("#viewAuditTodo").find('#auditUserForm').find(":hidden[id='action']").val("export");
	jQuery("#viewAuditTodo").find('#auditUserForm').form('submit',{
	    success: function(data){}
	}); 
	jQuery("#viewAuditTodo").find('#auditUserForm').find(":hidden[id='action']").val(""); 
}
function exportUser() {
	$.messager.show({
		title:'提示',
		msg:'系统正在导出，请稍后',
		timeout:2000,
		showType:'slide'
	});
	jQuery("#viewAuditY").find('#auditUserForm').find(":hidden[id='action']").val("export");
	jQuery("#viewAuditY").find('#auditUserForm').form('submit',{
	    success: function(data){}
	}); 
	jQuery("#viewAuditY").find('#auditUserForm').find(":hidden[id='action']").val(""); 
}
function exportUser_() {
	$.messager.show({
		title:'提示',
		msg:'系统正在导出，请稍后',
		timeout:2000,
		showType:'slide'
	});
	jQuery("#viewAuditN").find('#auditUserForm').find(":hidden[id='action']").val("export");
	jQuery("#viewAuditN").find('#auditUserForm').form('submit',{
	    success: function(data){}
	}); 
	jQuery("#viewAuditN").find('#auditUserForm').find(":hidden[id='action']").val(""); 
}
</script>