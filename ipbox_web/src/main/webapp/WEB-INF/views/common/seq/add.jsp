<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set value="0" var="size"/>
<form id="seqFrom" name="seqFrom" action="${ctx}/common/seq/save.do" method="post">
	 <table align="center" style="line-height: 40px;">
          <tr>
            <td style="text-align:right;">名称：</td>
            <td >
				<input type="text" class="easyui-textbox"  style="width: 300px" value="${seq.seqName }" data-options="required:true" id="seqName" name="seqName"/>
			</td>
          </tr>
          
          <tr>
            <td style="text-align:right;">代码：</td>
            <td >
            	<c:choose>
            		<c:when test="${not empty seq.id }">
            			${seq.seqCode }		
            		</c:when>
            		<c:otherwise>
            			<input type="text" class="easyui-textbox"  style="width: 300px" value="${seq.seqCode }" data-options="required:true" id="seqCode" name="seqCode"/>
            		</c:otherwise>
            	</c:choose>
				
			</td>
          </tr>
          
           <tr>
            <td style="text-align:right;">步长：</td>
            <td >
				<input type="text" class="easyui-numberbox"  style="width: 300px" value="${seq.stepNum }" data-options="required:true"  id="stepNum" name="stepNum"/>
			</td>
          </tr>
          <c:if test="${not empty seq.id }">
	          <tr>
	            <td style="text-align:right;">当前数：</td>
	            <td >
					${seq.currentNum }
				</td>
	          </tr>
	          
	          <tr>
	            <td style="text-align:right;">下个数：</td>
	            <td >
					${seq.nextNum }
				</td>
	          </tr>
          </c:if>
          <tr style="text-align: center;line-height: 20px">
            <td colspan="2" >
            <input type="hidden" name="id" id="id" value="${seq.id }"/>
            <button type="button" onclick="javascript:save();" style="float: center; margin-right: 20px" class="easyui-linkbutton" data-options="iconCls:'fa fa-floppy-o'">保存</button>
            </td>
            
          </tr>
        </table>
</form>

<script type="text/javascript">
var typeName = '${typeName}';
$(document).ready(function(){

});

//保存序列
function save(formId){
	if(!$('#seqFrom').form('validate')) {
		return false;
	}
	$.messager.confirm('提示', '确定保存？', function(r){
		if(r) {
			jQuery('#seqFrom').form('submit',{
				 onSubmit: function(){
					 return $('#seqFrom').form('validate');
				},
			    success: function(data){
			    	var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							jQuery.messager.alert("提示信息",message,function(){});
							closeWindow('showAddDialog');
						} else if(parseInt(statusCode)==200){
							jQuery.messager.alert("提示信息",message,function(){});
							jQuery('#'+getCurrentTabId()).panel('refresh');
							closeWindow('showAddDialog');
						} else {
							jQuery.messager.alert("提示信息","系统出错！",function(){});
							closeWindow('showAddDialog');
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
