<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:choose>
	<c:when test="${ipanthercommon:isSpace(sessionScope.loginUser.roleCode)}">
		<c:set var="tableClass" value="alter-table-v-space"/>
	</c:when>
	<c:otherwise>
		<c:set var="tableClass" value="alter-table-v"/>
	</c:otherwise>
</c:choose>
<form id="userHistoryForm" name="userHistoryForm" action="${ctx}/common/user/parttime/save.do" method="post" >
		<input type="hidden" name="userParttime.id" id="id" value="${userParttime.id}"/>
		<input type="hidden" name="userParttime.userId" id="userId" value="${userParttime.userId}"/>
		<table cellspacing="0" cellpadding="0" border="0" class="${tableClass}">
		<tbody>
			<tr>
				<td width="20%"><span style="color:red">*</span>兼职名称</td>
				<td width="30%">
					<input type="text" class="easyui-textbox" name="userParttime.name" required value="${userParttime.name}"/>
				</td>
				<td width="20%"><span style="color:red">*</span>兼职类型</td>
				<td width="30%">
					<select name="userParttime.type" id="type" class="easyui-combobox" required style="width: 158px;" editable="false" data-options="onSelect:changeParttimeType">
						<option value=""></option>
						${dict:getEntryOptionsSelected('PARTTIME_TYPE',userParttime.type) }
					</select>
				</td>
			</tr>
			<tr class="parttime_term">
				<td>学术团体名称</td>
				<td>
					<input type="text" class="easyui-textbox" name="userParttime.termName"  value="${userParttime.termName}"/>
				</td>
				<td>学术团体级别</td>
				<td>
					<input type="text" class="easyui-textbox" name="userParttime.termLevel"  value="${userParttime.termLevel}"/>
				</td>
			</tr>
			<tr class="parttime_term">
				<td>学术团体隶属或主管单位</td>
				<td>
					<input type="text" class="easyui-textbox" name="userParttime.termDept"  value="${userParttime.termDept}"/>
				</td>
				<td>学术兼职职务</td>
				<td>
					<input type="text" class="easyui-textbox" name="userParttime.job"  value="${userParttime.job}"/>
				</td>
			</tr>
		</tbody>
	</table>
	<div style="text-align: center">
		<button type="button" onclick="save()" class="easyui-linkbutton" > <i class="fa fa-save"></i>保 存 </button>
		<button type="reset"  class="easyui-linkbutton" class="easyui-linkbutton"> 重 置 </button>
	</div>
</form>
<script type="text/javascript">
tableVBg('.${tableClass}');
$(document).ready(function (){
	
});
<c:if test="${not empty userParttime.id}">
$.parser.onComplete=changeParttimeType;
</c:if>
function changeParttimeType() {
	var type = $("#type","#userHistoryForm").combobox("getValue");
	if("01"==type) {//社会兼职
		$(".parttime_term").hide();
		$(".parttime_term").find(".easyui-textbox").textbox("setValue","");
	} else{//学术团体兼职
		$(".parttime_term").show();
	}
	$.parser.onComplete=function(){};
}

function save() {
	$.messager.confirm('提示', '确定保存？', function(r){
		if(r) {
			jQuery('#userHistoryForm').form('submit',{
				onSubmit: function(){
					 return $('#userHistoryForm').form('validate');
				},
			    success: function(data){
			    	var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						console.log(message);
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							jQuery.messager.alert("提示信息","保存失败","error");
						} else if(parseInt(statusCode)==200){
							jQuery.messager.alert("提示信息","保存成功","info");
							jQuery('#viewUserParttimeTopTab').panel('refresh');
							closeWindow("showAddParttimeDialog");
						}
					}
			    }
			}); 
		}
		
	});
}

</script>
