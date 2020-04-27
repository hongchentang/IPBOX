<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp" %>
  <form id="addProblemForm"  action="${ctx}/train/problem/${StringUtils:isNotEmpty(problem.problemCode)?'editProblem':'addProblem'}.do" method="post">
          	<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
				<tbody>
					<tr>
						<td  width="25%">
				          	<label>问题编码</label>
				        </td>
				        <td  width="75%">
				        	<c:choose>
				        		<c:when test="${not empty problem.problemCode}">
				        			<input type="hidden" name="problem.version" value="${problem.version }"/>
				        			<input type="hidden" name="problem.id" value="${problem.id }"/>
				        			${problem.problemCode}
				        			<input id="problem.problemCode" type="hidden" name="problem.problemCode" value="${problem.problemCode}"/>
				        		</c:when>
				        		<c:otherwise>
				        			<input id="problem.problemCode" name="problem.problemCode" class="easyui-validatebox" 
									data-options="required:true" type="text" value="${problem.problemCode}"/>
				        		</c:otherwise>
				        	</c:choose>
				        </td>
				     </tr>
				     <tr>
				        <td>
				          	<label>问题名称</label>  
				        </td>
				        <td>
				        	 <input id="problem.problemName" name="problem.problemName" class="easyui-validatebox" 
							data-options="required:true" type="text" value="${problem.problemName}"/>
				        </td>	
		    		</tr>
		    		<tr>
						<td>
							<label>问题内容</label>
						</td>
						<td colspan="3">
							<textarea class="easyui-validatebox" validType="length[0,500]" required name="problem.problemContent" rows="5" style="width: 98%">${problem.problemContent}</textarea>
						</td>
					</tr>
		     	</tbody>
		    </table>
      </form>
       <div style="text-align:center;padding:5px">
      	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitProblemForm()">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('editProblemWindow');">取消</a>
       </div>
<script type="text/javascript">
var isXK=false;
tableVBg('.alter-table-v');
 
function submitProblemForm(){
	jQuery.messager.confirm("提示信息","确定${StringUtils:isEmpty(problem.problemCode)?'添加':'编辑'}常见问题?",function(isReturn){
	   if(isReturn){
		 jQuery('#editProblemWindow').find('#addProblemForm').form('submit',{
			url:jQuery('#editProblemWindow').find('#addProblemForm').attr("action"),
			onSubmit: function(){
				 return true;
			},
		    success: function(data){    
				var json=jQuery.parseJSON(data);
				if(json){
					var message = json.message;
					var statusCode = json.statusCode;
					if(parseInt(statusCode)==300){
						jQuery.messager.alert("提示信息",message,function(){});
					}else if(parseInt(statusCode)==200){
						jQuery.messager.alert("提示信息",message,function(){});
						jQuery('#'+getCurrentTabId()).panel('refresh');
						closeWindow('editProblemWindow');
					}
				}else{
					console.error("json is null");
				}
			}
		}); 
	   }
	}); 
} 
</script>