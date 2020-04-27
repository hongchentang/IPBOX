<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes')}" var="fileTypes2"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize2"/>
<form id="performanceTargetFrom" name="performanceTargetFrom" action="${ctx}/evalua/performanceTarget/saveFile.do" method="post" enctype="multipart/form-data">
	 	<input type="hidden" name="id" value="">
	 	<input type="hidden" name="projectId" value="${project.id }">
	 <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">
          <tr>
			<td >项目名</td>
            <td >
			   ${project.projectName }
			</td>
		</tr>
		<tr>
			<td>上传附件</td>
          		<td>
          			<input type="file"  name="uploadFile">
           			<div>
						     允许上传的文件类型：${fileTypes2}<br/>
						    允许上传文件的大小：${fileMaxSize2}KB 
					</div>
          		</td>
          </tr>
          <tr style="text-align: center;line-height: 20px">
            <td colspan="4">
            <div style="width: 100%;text-align: center;">
            <button type="button" onclick="javascript:submitFileForm();" style="float: center; margin-right: 20px" class="easyui-linkbutton">
            	保存
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


function submitFileForm(){
	$.messager.confirm('确认','确认保存吗？',function(result){    
		if (result){  
			$('#performanceTargetFrom').form('submit',{
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	
						$.messager.alert('提示','请填写必填项！');
					}
					return isValid;	
				},
				success: function(data){
					var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							$.messager.alert('提示',message);
						}else if(parseInt(statusCode)==200){
							closeWindow('addFileWindow');
							easyuiUtils.ajaxPostFormForPanel('listPreForm','${param.tabId}')
							$.messager.alert('提示',message);
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
