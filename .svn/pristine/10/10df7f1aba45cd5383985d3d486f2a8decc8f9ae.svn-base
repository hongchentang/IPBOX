<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<form id="saveImportFrom" name="saveImportFrom" action="${ctx}/train/courseRegister/saveImport.do" method="post" enctype="multipart/form-data">
	 <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">
          <tr>
            <td width="50">学员姓名</td>
            <td >
			  <input id="saveImportFile" type="file" name="file"  title="支持文件格式：xls" data-options="required:true" />
			</td>
          </tr>
          
       	  <tr>
            <td colspan="4" width="600px" >
           		 <div style="width: 100%;text-align: center;">
           			<a onclick="saveImport()" href="javascript:void(0)" class="easyui-linkbutton">导入</a>
           			<a onclick="closeWindow('importWindow')" href="javascript:void(0)" class="easyui-linkbutton">关闭</a>
				</div>
			</td>
          </tr> 
        </table>
</form>
<font color="red">(模板以导出的评分模板为准，支持文件格式：xls)</font>

<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
});
function saveImport(){
	$.messager.confirm('确认','是否确认导导入？',function(result){
		if(result){
			$('#saveImportFrom').form('submit',{
				onSubmit: function(){
					if($("#saveImportFile").val()==''){
						$.messager.alert('提示',"请选择成绩导入模板!");
						return false;
					}
					return true;
				},
				success: function(data){
					var json = eval('(' + data + ')');  
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							$.messager.alert('提示',message);
						}else if(parseInt(statusCode)==200){
							jQuery("#importWindow").window({
								width:750,
							    height:500,
							    title:'查看导入结果'
							});
							jQuery("#importWindow").window("hcenter");
							jQuery("#importWindow").window("refresh","${ctx}/train/courseRegister/importResult.do?paramMap[batchId]="+message);
						}
					}else{
						$.messager.alert('提示','json is null');
					}
				}
			});
			
		}
	});
	
}

</script>
