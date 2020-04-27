<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hcis.ipanther.common.security.utils.SecurityConstants" %>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<c:set value="<%=SecurityConstants.MODULE_TYPE_MASTER %>" var="master"/>
<c:set value="<%=SecurityConstants.MODULE_TYPE_SLAVE %>" var="slave"/>
<div class="easyui-panel" id="editDialogPanel" data-options="region:'center',title:''">
       <form id="moduleDialogEditFrom"  action="${ctx}/common/security/module/edit.do" method="post">
          	<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
				<tbody>
					<tr>
						<td>
				          	<label for="module.id">模块标示</label>  
				        </td>
				        <td>
				        	<input class="easyui-textbox" type="text"  data-options="required:true" id="module.id" name="module.id"  value="${module.id}" />
				        </td>	
           			</tr>
		    		<tr>
		    			<td><label for="module.name">模块名称</label></td>
		    			<td>
		    				<input class="easyui-textbox" type="text"  id="id" name="module.name"  data-options="required:true"  value="${module.name}"/>
		  				</td>
		    		</tr>
		    		<tr>
		    			<td><label for="module.type">模块类型</label> </td>
		    			<td>
			    			<select id="type" name="module.type" class="easyui-combobox easyui-validatebox" >
		    					<option value="${master}" ${module.type eq master?'selected="selected"':''} >${master}</option>
						    	<option value="${slave}" ${module.type eq slave?'selected="selected"':''}>${slave}</option>
						    </select>
		    			</td>
		    		</tr>
		     		<tr>
		     			<td><label for="module.description">描述:</label></td>
		     			<td>
		     				<textarea class="easyui-validatebox" name="module.description" data-options="required:true" rows="3">${module.description}</textarea>
		     			</td>
		     		</tr>
		     	</tbody>
		    </table>
      </form>
       <div style="text-align:center;padding:5px">
      	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormEdit()">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('editDialog')">取消</a>
       </div>
</div>
<script type="text/javascript">

 tableVBg('.alter-table-v');

 function submitFormEdit(){
	 jQuery("#editDialog").find('#moduleDialogEditFrom').form('submit', {   
	    success: function(data){    
			var json=jQuery.parseJSON(data);
			if(!jQuery.isEmptyObject(json)){
				var message = json.message;
				var statusCode = json.statusCode;
				if(parseInt(statusCode)==300){
					jQuery.messager.alert("提示信息",message,function(){});
				}else if(parseInt(statusCode)==200){
					jQuery.messager.alert("提示信息",message,function(){});
					getCurrentTab().find('#moduleSelect').combobox('reload','${ctx}/common/security/privilege/listJson.do');
					closeWindow('editDialog');
				}
			}else{
				console.error("json is null");
			}
		}    
	}); 
} 
</script>
