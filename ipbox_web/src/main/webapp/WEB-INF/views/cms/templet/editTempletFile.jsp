<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<form id="editTempletForm"  action="${ctx}/cms/templet/saveTemplet.do" method="post" >
 <input name="paramMap[isEdit]" id="isEdit" value="01" type="hidden"/>
 <input name="paramMap[root]" value="${root}" type="hidden"/>
 <input name="paramMap[oldFileExt]" value="${fileExt}" type="hidden"/>
 <input name="paramMap[oldFileName]" value="${fileName}" type="hidden"/>
<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v" width="90%">
	<tbody>
		<tr>
				<td width="6%">
					文件名
				</td>
				<td width="94%">
					<input name="paramMap[fileName]" value="${fileName}" class="easyui-textbox"/>
					<select name="paramMap[fileExt]" class="easyui-combobox" data-options="onSelect:function(rec){editExt(rec.value,'${fileExt}')}">
						<option value="ftl" ${fileExt eq 'ftl'?'selected="selected"':''}>.ftl</option>
						<option value="html" ${fileExt eq 'html'?'selected="selected"':''}>.html</option>
						<option value="js" ${fileExt eq 'js'?'selected="selected"':''}>.js</option>
						<option value="css" ${fileExt eq 'css'?'selected="selected"':''}>.css</option>
						<option value="text" ${fileExt eq 'text'?'selected="selected"':''}>.text</option>
					</select>
				</td>
		</tr>
		<tr>
				<td>
					文件内容
				</td>
				<td>
					<textarea rows="" cols="" name="paramMap[content]" 
						style="width:99%;height:400px">${content}</textarea>
				</td>
		</tr>

 
 </tbody>
 </table>
<div style="text-align: center; padding: 5px">
	<button type="button"  class="easyui-linkbutton" onclick="saveSiteTemplet();">保存</button>
	<button type="button"  class="easyui-linkbutton" onclick="easyuiUtils.refreshPanel(getCurrentTabId());">取消</button>
	<button type="button"  class="easyui-linkbutton" onclick="deleteTemplet();">删除</button>
</div>
</form>
<script type="text/javascript">
var isEdit=false;
 tableVBg('.alter-table-v');
 function saveSiteTemplet(){
	 if(isEdit){
		$.messager.confirm('提示',"当前文件会被覆盖是否确认保存？",function(result){    
			if (result){  
				submitTempletForm();
				return true;
			}
		});
		return false;
	 }
	 submitTempletForm();
 }
 
 function submitTempletForm(){
	 getCurrentTab().find('#editTempletForm').form('submit', {   
		    success: function(data){    
				var json=jQuery.parseJSON(data);
				if(json){
					var message = json.message;
					var statusCode = json.statusCode;
					if(parseInt(statusCode)==300){
						jQuery.messager.alert("提示信息",message,function(){});
					}else if(parseInt(statusCode)==200){
						jQuery.messager.alert("提示信息",message,function(){});
						easyuiUtils.refreshPanel(getCurrentTabId());
						//closeWindow('siteWindow');
					}
				}else{
					console.error("json is null");
				}
			}    
		}); 
 }
 
 function editExt(elemtValue,fileExt){
	 if(elemtValue!=fileExt){
		 getCurrentTab().find('#editTempletForm').find("#isEdit").val("02");
		 isEdit=true;
	 }else{
		 getCurrentTab().find('#editTempletForm').find("#isEdit").val("01");
		 isEdit=false;
	 }
 }
 
 function deleteTemplet(){
	 $.messager.confirm('提示',"是否确认删除当前文件？",function(result){    
			if (result){  
				 var dataArray=jQuery.ajaxSubmitValue('${ctx}/cms/templet/deleteTempletFile.do?paramMap[root]=${root}');
				 var json=eval("("+dataArray+")");
				 if(json){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							jQuery.messager.alert("提示信息",message,function(){});
						}else if(parseInt(statusCode)==200){
							jQuery.messager.alert("提示信息",message,function(){});
							easyuiUtils.refreshPanel(getCurrentTabId());
							//closeWindow('siteWindow');
						}
					}else{
						console.error("json is null");
					}
			}
		});
	
 }
</script>
