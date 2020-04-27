<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
 <div class="easyui-panel" id="importUserPanel" data-options="region:'center',title:''">
       <form id="importUserFrom"  action="${ctx}/common/user/${userType}/import.do" method="post" enctype="multipart/form-data">
          	<table  cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
				<tbody>
					<tr>
						<td width="20%">
				          	导入模板
				        </td>
				        <td width="80%">
				        	<a style="text-decoration: underline;" href="${ctx}/template/${userType}ImportTemplate.xls">
				        		点击下载导入模板
				        	</a>
				        </td>	
           			</tr>
					<tr>
						<td>
				          	选择文件
				        </td>
				        <td>
				        	<input type="file" name="file"  title="支持文件格式：xls" data-options="required:true" />
				        	<div style="color:red">提示：支持文件格式：xls</div>
				        </td>	
           			</tr>
		     	</tbody>
		    </table>
      </form>
       <div style="text-align:left; padding:5px;margin-left: 25%;">
      	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="importUser()">导入</a>
       	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('showAddDialog');"><i class="fa fa-times"></i>关闭</a>
       </div>
</div>
<script type="text/javascript">

tableVBg('.alter-table-v');
 
 //导入
 function importUser(){
	 if(""==jQuery(":file[name='file']").val()) {
		 jQuery.messager.alert("提示信息","请选择文件","error");
		 return false;
	 }
	 $.messager.confirm('提示', '确定导入？', function(r){
		 if(r) {
			 jQuery('#importUserFrom').form('submit',{
				 onSubmit: function(){
					 return $('#importUserFrom').form('validate'); 
				},
			    success: function(data){
			    	var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							jQuery.messager.alert("提示信息","导入发生错误，请检查导入excel是否正确！",function(){});
						}else if(parseInt(statusCode)==200){
							//跳转到导入结果页面
							jQuery("#showAddDialog").window({
								width:750,
							    height:500,
							    title:'查看导入结果',
							    href:'${ctx}/common/user/${userType}/importResult.do?batchId='+message
							});
							jQuery("#showAddDialog").window("hcenter");
						}
					}else{
						console.error("json is null");
					}
			    },
			});  
		 }
	 });
	}
</script>