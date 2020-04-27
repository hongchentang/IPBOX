<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div class="easyui-panel" id="addPrivilegePanel" data-options="region:'center',title:''" style="overflow:hidden;">
          	<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
				<tbody>
					<tr>
						<td>
				          	<label>角色名称:</label>  
				        </td>
				        <td>
				        	${role.name }
				        </td>	
           			</tr>
		     		<tr>
		     			<td><label>描述:</label></td>
		     			<td>
		     				<textarea class="easyui-validatebox"   rows="3" disabled="disabled">${role.description }</textarea>
		     			</td>
		     		</tr>
		     		<tr>
		     			<td><label> 角色标示:</label></td>
		     			<td>${role.roleCode }</td>
		     		</tr>
		     	</tbody>
		    </table>
	       <div style="text-align:center;padding:5px">
	        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('showDialog');">关闭</a>
	       </div>
</div>
<script type="text/javascript">
 tableVBg('.alter-table-v');
</script>