<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="easyui-panel" title="师资信息" collapsible="true" style="height: 435px">
<%@include file="tabs.jsp" %>
</div>
<form id="auditTeacherForm" name="auditTeacherForm" action="${ctx}/common/user/teacher/audit.do" method="post" >
	<div class="easyui-panel" title="审核信息" collapsible="true">
		<input type="hidden" name="userId" id="userId" value="${user.id}"/>
		<input type="hidden" name="registerId" id="registerId" value="${register.id}"/>
		<input type="hidden" name="taskId" id="taskId" value="${task.id}"/>
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
			<tbody>
				<tr>
					<td width="20%"><span style="color: red">*</span>审核结果</td>
					<td colspan="3">
						<select id="auditResult" name="auditResult" class="easyui-combobox" onSelect="changeAuditResult" required style="width: 158px;" editable="false" data-options="onSelect:changeResult">
							<option value=""></option>
							<option value="01">通过</option>
							<option value="03">不通过</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><span id="auditContent_required" style="color: red;display: none">*</span>审核内容</td>
					<td colspan="3">
						<textarea rows="2" style="width: 98%" name="auditContent" id="auditContent" class="easyui-validatebox"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div style="text-align: center">
		<button type="button" onclick="auditTeacher()" class="easyui-linkbutton" > <i class="fa fa-save"></i>保 存 </button>
		<button type="reset"  class="easyui-linkbutton" class="easyui-linkbutton"> 重 置 </button>
	</div>
</form>
<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
	
});

//改变审批结果时触发
function changeResult() {
	var result = $("#auditResult").combobox("getValue");
	if("03"==result) {
		$("#auditContent_required").show();
		$("#auditContent","#auditTeacherForm").validatebox({
			required:true
		});
		$("#auditContent","#auditTeacherForm").validatebox("enableValidation");
	} else {
		$("#auditContent_required").hide();
		$("#auditContent","#auditTeacherForm").validatebox("disableValidation");
	}
}

function auditTeacher() {
	$.messager.confirm('提示', '确定审核？', function(r){
		if(r) {
			jQuery('#auditTeacherForm').form('submit',{
				onSubmit: function(){
					 return $('#auditTeacherForm').form('validate');
				},
			    success: function(data){
			    	var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							jQuery.messager.alert("提示信息",message,"error");
						} else if(parseInt(statusCode)==200){
							jQuery.messager.alert("提示信息","审核成功","info");
							jQuery('#viewTeacherTodoBaseTopTab').panel('refresh');
							closeWindow("showAddDialog");
						}
					}
			    }
			}); 
		}
		
	});
}
</script>
