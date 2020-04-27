<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<form id="siteTempletForm"  action="${ctx}/cms/templet/upload.do" method="post" enctype="multipart/form-data">
<input name="cmsSite.id"  type="hidden" value="${cmsSite.id}"/>
<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v" width="90%">
	<tbody>
		<tr>
			<td><label>当前站点名</label></td>
			<td>
				<h3>${cmsSite.name}</h3>
			</td>
		</tr>
		<tr>
			<td><label>文件路径</label></td>
			<td>
			<input id="path" name="paramMap[path]"   type="hidden" value=""/>
			<input id="sourcePath" name="paramMap[sourcePath]" readonly="readonly" type="text" value="/"/>
			<button type="button"  class="easyui-linkbutton" onclick="selectDirectory();">选择</button>
			(默认存放当前站点根目录)
			</td>
		</tr>
		<tr>
			<td><label>文件</label></td>
			<td>
			<input name="paramMap[fileTypes]" type="hidden" value="ftl,html,js,css,txt"/>
			<input name="upload" type="file"  class="easyui-validatebox" data-options="required:true"/>
			<div>
			 <br/>     
			   允许上传的文件类型：<b>.ftl,.html,.js,.css,.txt</b>
			 <br/> 
			 </div>
			</td>
		</tr>
	</tbody>
</table>
<div style="text-align: center; padding: 5px">
	<button type="button"  class="easyui-linkbutton" onclick="saveSiteTemplet();">保存</button>
	<button type="button"  class="easyui-linkbutton" onclick="easyuiUtils.closeWindow('editTempletDialog')">取消</button>
</div>
</form>
<script type="text/javascript">
// jQuery("#editTempletDialog").find('#siteTempletForm').find("#upload").filebox({  
// 	buttonText:'选择文件',
// 	buttonAlign:'right',
// 	required:true,
// })

 tableVBg('.alter-table-v');
 function saveSiteTemplet(){
	 jQuery("#editTempletDialog").find('#siteTempletForm').form('submit', {   
		    success: function(data){    
				var json=jQuery.parseJSON(data);
				if(json){
					var message = json.message;
					var statusCode = json.statusCode;
					if(parseInt(statusCode)==300){
						jQuery.messager.alert("提示信息",message,function(){});
					}else if(parseInt(statusCode)==200){
						jQuery.messager.alert("提示信息",message,function(){});
						easyuiUtils.ajaxClearFormForPanel(getCurrentTabId());
						easyuiUtils.closeWindow('editTempletDialog');
					}
				}else{
					console.error("json is null");
				}
			}    
		}); 
 }
 
 function selectDirectory(){
	 var url="${ctx}/cms/templet/selectDirectory.do?paramMap[siteId]=${cmsSite.id}&paramMap[entityId]=path&paramMap[entityName]=sourcePath&paramMap[dialogId]=selectDirectoryDialog&paramMap[contentId]=editTempletDialog";
	 easyuiUtils.openWindow('selectDirectoryDialog',"选择文件夹",300,360,url,true); 
 }
</script>
