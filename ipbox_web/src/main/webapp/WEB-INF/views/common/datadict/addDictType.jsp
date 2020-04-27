<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp" %>
  <form id="addDictTypeForm"  action="${ctx}/common/datadict/${StringUtils:isNotEmpty(dictType.dictTypeCode)?'editDictType':'addDictType'}.do" method="post">
          	<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
				<tbody>
					<tr>
						<td  width="25%">
				          	<label>数据类型编码</label>
				        </td>
				        <td  width="75%">
				        	<c:choose>
				        		<c:when test="${not empty dictType.dictTypeCode}">
				        			<input type="hidden" name="dictType.version" value="${dictType.version }"/>
				        			${dictType.dictTypeCode}
				        			<input id="dictType.dictTypeCode" type="hidden" name="dictType.dictTypeCode" value="${dictType.dictTypeCode}"/>
				        		</c:when>
				        		<c:otherwise>
				        			<input id="dictType.dictTypeCode" name="dictType.dictTypeCode" class="easyui-validatebox" 
									data-options="required:true" type="text" value="${dictType.dictTypeCode}"/>
				        		</c:otherwise>
				        	</c:choose>
				        </td>
				     </tr>
				     <tr>
				        <td>
				          	<label>数据类型名称</label>  
				        </td>
				        <td>
				        	 <input id="dictType.dictTypeName" name="dictType.dictTypeName" class="easyui-validatebox" 
							data-options="required:true" type="text" value="${dictType.dictTypeName}"/>
				        </td>	
		    		</tr>
		     	</tbody>
		    </table>
      </form>
       <div style="text-align:center;padding:5px">
      	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitDictTypeForm()">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('editDictTypeWindow');">取消</a>
       </div>
<script type="text/javascript">
var isXK=false;
tableVBg('.alter-table-v');
 
function submitDictTypeForm(){
	jQuery.messager.confirm("提示信息","确定${StringUtils:isEmpty(dictType.dictTypeCode)?'添加':'编辑'}数据字典?",function(isReturn){
	   if(isReturn){
		 jQuery('#editDictTypeWindow').find('#addDictTypeForm').form('submit',{
			url:jQuery('#editDictTypeWindow').find('#addDictTypeForm').attr("action"),
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
						closeWindow('editDictTypeWindow');
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