<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
       <form id="aadPrivilegeFrom"  action="${ctx}/common/security/privilege/aadPrivilege.do" method="post">
          	<input type="hidden" value="" id="privilege_module" name="privilege.module"/>
          	<input type="hidden" value="" id="privilegePid" name="privilege.pid"/>
          	
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
				         	<input type="hidden"   name="privilege.id"  value="" />
				        	<input class="easyui-textbox" type="text"  data-options="required:true,width: '250px'"   name="privilege.name"  value="" />
				        </td>	
           			</tr>
		    		<tr>
		    			<td><label for="privilege.url">URL</label></td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="privilege.url"  data-options="width: '250px'"  value=""/>
		  				</td>
		    		</tr>
		    		<tr>
		    			<td><label for="privilege.target">target类型</label></td>
		    			<td>
		    			  <select name="privilege.target"  class="easyui-combobox">
		    			  	<option value="">默认选择</option>
		    			    ${dict:getEntryOptions('TARGET_TYPE')}
		    			  </select>
		  				</td>
		    		</tr>
		    		<tr>
		    			<td><label for="privilege.isDisplay">在菜单中显示</label> </td>
		    			<td>
		    				<label>
		    			 	<span class="checked">
							    <input type="radio" value="Y" checked="checked" name="privilege.isDisplay"></input>
							</span>
						   		 是
						    </label>
						    <label class="radio-inline">
						     <span class="checked">
							    <input  type="radio" value="N"  name="privilege.isDisplay"></input>
							</span>
						   		 否
						    </label>
		    			</td>
		    		</tr>
		    		<tr>
		     			<td><label for="privilege.orderNo">排列号</label></td>
		     			<td> 
		     				<input class="easyui-numberbox" type="text" name="privilege.orderNo"  data-options="required:true,width: '250px'"  value=""/>
		     			</td>
		     		</tr>
		     		<tr>
		     			<td><label for="privilege.description">描述</label></td>
		     			<td>
		     				<textarea class="easyui-validatebox" name="privilege.description"  rows="3"></textarea>
		     			</td>
		     		</tr>
		     	</tbody>
		    </table>
	       <div style="text-align:center;padding:5px">
	      	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitPrivilegeFrom()">确定</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:getCurrentTab().find('#aadPrivilegeFrom').form('reset')">重置</a>
	       </div>
      </form>
<script type="text/javascript">
tableVBg('.alter-table-v');

getCurrentTab().find("#privilege_pid").combotree({    
    method:'post',
    url:'${ctx}/common/security/privilege/priByModule.do?module.id='+getCurrentTab().find('#moduleSelect').combobox('getValue'),  
    id:'id',
    text:'text',
    parentField:'pid',
});  
function submitPrivilegeFrom(){
/*  	 var root= getCurrentTab().find('#privilege_pid').combotree('tree').tree('getSelected');
 	 alert(root.id); */
 	 var getValue= getCurrentTab().find('#privilege_pid').combotree('getValue');
	 if(getValue){
		 getCurrentTab().find("#privilegePid").val(getValue);
	 }else{
		 getCurrentTab().find("#privilegePid").val("0");
     } 
	 var module= getCurrentTab().find('#moduleSelect').combobox("getValue");
	 if(module){
		 getCurrentTab().find("#privilege_module").val(module);
	 } 
	 getCurrentTab().find('#aadPrivilegeFrom').form('submit', {   
	    success: function(data){  
		    if(data){  
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
		}else{
			jQuery.messager.alert("提示信息","参数错误!",function(){});
		} 
	  }  
	}); 
} 
</script>