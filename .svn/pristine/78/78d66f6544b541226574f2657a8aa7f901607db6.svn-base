<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
 <div class="easyui-panel" id="addFlowPanel" data-options="region:'center',title:''">
       <form id="flowDeployFrom"  action="${ctx}/common/flow/deployDo.do" method="post" enctype="multipart/form-data">
          	<table  cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
				<tbody>
					<tr>
						<td>
				          	<label >选择文件</label>  
				        </td>
				        <td>
				        	<input type="file" name="file"  title="支持文件格式：zip、bar、bpmn、bpmn20.xml" data-options="required:true" />
				        </td>	
           			</tr>
		     	</tbody>
		    </table>
      </form>
       <div style="text-align:left; padding:5px;margin-left: 25%;">
      	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="deploy()">部署</a>
       	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('showAddDialog');">关闭</a>
       </div>
       <div style="color: red">
       提示：支持文件格式：zip、bar、bpmn、bpmn20.xml
       </div>
</div>
<script type="text/javascript">

 tableVBg('.alter-table-v');
 
 //发布流程
 function deploy(){
	 if(""==jQuery(":file[name='file']").val()) {
		 jQuery.messager.alert("提示信息","请选择文件");
		 return false;
	 }
	 $.messager.confirm('提示', '确定发布？', function(r){
		 if(r) {
			 jQuery('#flowDeployFrom').form('submit',{
				 onSubmit: function(){
					 return $('#flowDeployFrom').form('validate'); 
				},
			    success: function(data){
			    	var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							jQuery.messager.alert("提示信息",message,function(){});
						}else if(parseInt(statusCode)==200){
							jQuery.messager.alert("提示信息",message,function(){});
							jQuery('#flowListTopTab').panel('refresh');
							closeWindow('showAddDialog');
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