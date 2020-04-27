<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%> 
<form id="user_saveFrom" name="user_saveFrom" action="${ctx}/common/user/add.do" method="post">
	<table>

		<tr style="height: 30px" ></tr>
	</table>
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
		
			<tr>
				<td width="20%"><span style="color:red">*</span>姓名</td>
				<td width="30%">
					<input type="text" class="easyui-textbox" name="user.userName"  data-options="width: 200, height: 30, required:true" />
				</td>
			
			</tr>	
				<tr>
		
				<td width="20%"><span style="color:red">*</span>邮箱</td>
				<td width="30%">
					<input type="text" class="easyui-textbox" name="user.userEmail" 
					  data-options="width: 200, height: 30, validType: 'email', required:true"/>
				</td>
			</tr>
				<tr>
				<td width="20%"><span style="color:red">*</span>所属公司</td>
				<td width="30%">
					<input type="text" class="easyui-textbox" name="user.companyName"  data-options="width: 200, height: 30, required:true" />
				</td>
			
			</tr>
				<tr>
				<td width="20%"><span style="color:red">*</span>联系方式</td>
				<td width="30%">
					<input type="text" class="easyui-textbox" name="user.mobilePhoe"  data-options="width: 200, height: 30, required:true" />
				</td>
			
			</tr>
		

		</tbody>
	</table>
		<!-- 空格效果
	 -->
	<table>

		<tr style="height: 30px" ></tr>
	</table>
	
	
	<div style="text-align: center;top:20%">
		<button  type="button" onclick="user_editFun()" class="easyui-linkbutton" > <i class="fa fa-save"></i>保 存 </button>
		<button type="reset"  class="easyui-linkbutton" class="easyui-linkbutton"> 重 置 </button>
	</div>
</form>
<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
	
});
<c:if test="${not empty user.id}">
$.parser.onComplete=changePaperworkType;
</c:if>

//证件格式校验
function changePaperworkType() {
/* 	var paperworkType = $("#paperworkType","#user_saveFrom").combobox("getValue");
	if("01"==paperworkType) {//居民身份证
		$("#paperworkNo","#user_saveFrom").textbox({
			validType:'idcard'
		});
	} else {
		$("#paperworkNo","#user_saveFrom").textbox({
			validType:''
		});
	} */
	$.parser.onComplete=function(){};
}
	
function user_editFun(){
	if($('#user_saveFrom').form('validate')){

		
		$.messager.confirm('提示', '确定保存？', function(r){
			if(r) {
				jQuery('#user_saveFrom').form('submit',{
					onSubmit: function(){
						 return $('#user_saveFrom').form('validate');
					},
				    success: function(data){
				    	var json=jQuery.parseJSON(data);
						if(!jQuery.isEmptyObject(json)){
							var message = json.message;
							var statusCode = json.statusCode;
							if(parseInt(statusCode)==300){
								jQuery.messager.alert("提示信息",message,"error");
							} else if(parseInt(statusCode)==200){
								jQuery.messager.alert("提示信息","保存成功","info");
								jQuery('#'+getCurrentTabId()).panel('refresh');
								closeWindow("showAddDialog");
							}
						}
				    }
				}); 
			}
		});
	}
	else{
		$.messager.alert('提示','请填写必填项！','error');
	}
}
</script>