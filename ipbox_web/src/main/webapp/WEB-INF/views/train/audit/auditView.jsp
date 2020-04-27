<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<form id="saveAuditUserForm" name="saveAuditUserForm" action="${ctx}/train/audit/saveAuditUser.do" method="post" >
	<input type="hidden" id="userTrainId" name="paramMap[userTrainId]">
	<input type="hidden" id="AuditId" name="paramMap[auditId]">
	<input type="hidden" id="auditStatus" name="paramMap[auditStatus]">
	<input type="hidden" id="toUserId" name="paramMap[toUserId]">
	 <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">
	 	  <tr>
	 	  	<td colspan="4"  height="60px" style="font-size: 20px;color: red; text-align: center;">
           		您此次共选择了${count}个报名信息进行批量审核
			</td>
		  </tr>
          <tr>
            <td style="width: 15%">审核意见</td>
            <td colspan="3"  height="80px">
           		<textarea id="auditContent" rows="4" cols="30" style="width: 100%" name="paramMap[auditContent]"></textarea>
			</td>
          </tr>
           
          <tr style="text-align: center;line-height: 20px">
            <td colspan="4">
            <div style="width: 100%;text-align: center;">
            <button type="button" onclick="javascript:auditUser('01',this);" style="float: center; margin-right: 20px" class="easyui-linkbutton">
            	通过
            </button>
            <button type="button" onclick="javascript:auditUser('02',this);" style="float: center; margin-right: 20px" class="easyui-linkbutton">
            	不通过
            </button>
            </div>
            </td>
          </tr>
        </table>
</form>

<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
});


function auditUser(status,obj){
	if($("#auditContent").val()=='' && status == "02"){
		$.messager.alert('提示','请填写审核意见！');
		return false;
	}
	var AuditId="";
	var userTrainId="";
	var toUserId="";
	var objects = $('#auditUserTable',"#auditUserForm").datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	}
	for(var i=0;i<objects.length;i++){
		if(i==objects.length-1){
			AuditId+=objects[i].id;
			userTrainId+=objects[i].userId+":"+objects[i].trainId;
			toUserId+=objects[i].userId;
		}else{
			AuditId+=objects[i].id+",";
			userTrainId+=objects[i].userId+":"+objects[i].trainId+",";
			toUserId+=objects[i].userId+",";
		}
	}
	$("#auditStatus","#saveAuditUserForm").val(status);
	$("#AuditId","#saveAuditUserForm").val(AuditId);
	$("#userTrainId","#saveAuditUserForm").val(userTrainId);
	$("#toUserId","#saveAuditUserForm").val(toUserId);
	$.messager.confirm('确认','是否确认审核'+obj.textContent+'？',function(result){
		if (result){ 
				$.ajax({
					url:$("#saveAuditUserForm").attr("action"),
					data:$("#saveAuditUserForm").serialize(),
					type:"post",
					success:function(data){
						var josn=eval(data);
						if(parseInt(josn.statusCode)==300){
							$.messager.alert('提示','操作失败！');
						}else if(parseInt(josn.statusCode)==200){
							closeWindow('auditUserWindow');
							$('#viewAuditTodo').panel('refresh');
							$.messager.alert('提示',josn.message);
						}
						
					}
				})
		}
	})
}
</script>
