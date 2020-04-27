<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
       <form id="privilegeEditFrom"  action="${ctx}/common/security/privilege/save.do" method="post">
			<input type="hidden" value="" id="privilegePid" name="privilege.pid"/>
          	<input type="hidden" value="" id="privilege_module" name="privilege.module"/>
          	<table  cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
				<tbody>
					<tr>
						<td>
				          	<label for="privilege.name">所属上级菜单</label>  
				        </td>
				        <td>
				         	<select id="privilege_pid" style="width:200px;">
				         	</select>  
				         	<span style="color:gray;">默认为当前模块的一级菜单</span>
				        </td>	
           			</tr>
					<tr>
						<td>
				          	<label for="privilege.name">功能名称</label>  
				        </td>
				        <td>
				       		 <input type="hidden"   name="privilege.id"  value="${privilege.id}" />
				        	<input class="easyui-textbox" type="text"  data-options="required:true,width: '250px'"   name="privilege.name"  value="${privilege.name}" />
				        </td>	
           			</tr>
		    		<tr>
		    			<td><label for="privilege.url">URL</label></td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="privilege.url"  data-options="width: '250px'"  value="${privilege.url}"/>
		  				</td>
		    		</tr>
		    		<tr>
		    			<td><label for="privilege.target">target类型</label></td>
		    			<td>
		    			  <select name="privilege.target"  class="easyui-combobox">
		    			  	<option value="">默认选择</option>
		    			    ${dict:getEntryOptionsSelected('TARGET_TYPE',privilege.target)}
		    			  </select>
		  				</td>
		    		</tr>
		    		<tr>
		    			<td><label for="privilege.type">在菜单中显示</label> </td>
		    			<td>
		    				<label>
		    			 	<span class="checked">
							    <input type="radio" value="Y" ${privilege.isDisplay eq 'Y'?'checked="true"':''} name="privilege.isDisplay"></input>
							</span>
						   		 是
						    </label>
						    <label class="radio-inline">
						     <span class="checked">
							    <input  type="radio" value="N" ${privilege.isDisplay eq 'N'?'checked="true"':''} name="privilege.isDisplay"></input>
							</span>
						   		 否
						    </label>
		    			</td>
		    		</tr>
		    		<tr>
		     			<td><label for="privilege.description">排列号</label></td>
		     			<td> 
		     				<input class="easyui-numberbox" type="text" name="privilege.orderNo"  data-options="required:true,width: '250px'"  value="${privilege.orderNo}"/>
		     			</td>
		     		</tr>
		     		<tr>
		     			<td><label for="privilege.description">描述</label></td>
		     			<td>
		     				<textarea class="easyui-validatebox" name="privilege.description"  rows="3">${privilege.description}</textarea>
		     			</td>
		     		</tr>
		     	</tbody>
		    </table>
      </form>
       <div style="text-align:center;padding:5px">
      	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEditFrom()">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:getCurrentTab().find('#privilegeEditFrom').form('reset')">重置</a>
       </div>
<script type="text/javascript">
tableVBg('.alter-table-v');

getCurrentTab().find("#privilege_pid").combotree({    
    method:'post',
    url:'${ctx}/common/security/privilege/priByModule.do?module.id='+getCurrentTab().find('#moduleSelect').combobox('getValue'),  
    id:'id',
    text:'text',
    parentField:'pid',
    onLoadSuccess:function(){
   	 	//var node=getCurrentTab().find('#privilege_pid').combotree('tree').tree('find','${privilege.pid}');
   	 	getCurrentTab().find('#privilege_pid').combotree('setValue','${privilege.pid}');
    }
}); 



 function submitEditFrom(){
 	 var root= getCurrentTab().find('#privilege_pid').combotree('tree').tree('getSelected');
	 if(root){
		 getCurrentTab().find("#privilegePid").val(root.id);
	 }else{
		 getCurrentTab().find("#privilegePid").val("0");
     } 
	 var module= getCurrentTab().find('#moduleSelect').combobox("getValue");
	 if(module){
		 getCurrentTab().find("#privilege_module").val(module);
	 }
	 getCurrentTab().find('#privilegeEditFrom').form('submit', {   
	    success: function(data){    
			var json=jQuery.parseJSON(data);
			if(!jQuery.isEmptyObject(json)){
				var message = json.message;
				var statusCode = json.statusCode;
				if(parseInt(statusCode)==300){
					jQuery.messager.alert("提示信息",message,function(){});
				}else if(parseInt(statusCode)==200){
					jQuery.messager.alert("提示信息",message,function(){});
					getCurrentTab().find('#listTree').tree('reload');
 					getCurrentTab().find('#editTree').html('<h1 style="margin-top: 100px;margin-left: 40%">请点击左边的树进行操作.</h1>');
				}
			}else{
				console.error("json is null");
			}
		}    
	}); 
} 
</script>