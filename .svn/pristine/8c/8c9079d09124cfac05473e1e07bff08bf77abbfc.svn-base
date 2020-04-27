<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<form id="personTypeForm" name="personTypeForm" action="${ctx}/common/user/employees/setPerson.do" method="post">
		<input type="hidden" name="paramMap[userIds]" id="userId" value="${searchParam.paramMap.userIds}"/>
<%-- 		<input type="hidden" name="roleCode" id="roleCode" value="${user.roleCode}"/> --%>
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			
			<tr>
				<td><span style="color:red">*</span>人才类型</td>
				<td>
					<select name="paramMap[isPerson]" class="easyui-combobox" required="required" style="width:60%;"
						id="isPerson">
							<option value=""></option>
							${dict:getEntryOptionsSelected('IS_PERSON',searchParam.paramMap.isPerson) }
					</select>
					</td>
			</tr>
			
			</tbody>
		</table>
	<div style="text-align: center">
		<button type="button" onclick="savePersonType()" class="easyui-linkbutton" > <i class="fa fa-save"></i>保 存 </button>
		<button type="reset"  class="easyui-linkbutton" class="easyui-linkbutton"> 重 置 </button>
	</div>
</form>
<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
	
});
function savePersonType() {
	if(!$('#studentForm').form('validate')) {
		return false;
	}
	$.messager.confirm('提示', '确定保存？', function(r){
		if(r) {
			
			$("#personTypeForm").ajaxSubmit({ 
				type: 'post',  
				success: function(json){
					if(!$.isEmptyObject(json)){
						var responseMsg = json.message;
						var responseCode = json.statusCode;
						if("200"==responseCode) {//成功
							jQuery.messager.alert("提示信息","保存成功","info",function() {
								closeWindow('showAddDialog');
								jQuery('#'+getCurrentTabId()).panel('refresh');
								
							});
						} else {//错误
							jQuery.messager.alert("提示信息",responseMsg,"error");
						}
					}
				}
			});
		}
		
	});
}

</script>
