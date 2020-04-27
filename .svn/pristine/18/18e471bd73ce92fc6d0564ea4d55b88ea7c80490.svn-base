<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/include/taglib.jsp" %>
<div class="mod">
	<div class="tit clearfix">
		<h3>抽查评估</h3>
	</div>
</div>
<c:set var="readonly" value=""/>
<c:if test="${checkResult.status eq '02'}">
	<c:set var="readonly" value="readonly='readonly'"/>
</c:if>

<form id="checkForm" name="checkForm" action="${ctx}/space/check/saveCheck.do" method="post">
	<input type="hidden" name="id" value="${checkResult.id}"/>
	<%@include file="include/project.jsp" %>
	<div class="easyui-panel" title="评估信息" collapsible="true">
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v-space">
			<tbody>
				<tr>
					<td width="20%">评估意见</td>
					<td width="80%">
						<textarea rows="2" style="width: 95%;" name="result" class="easyui-validatebox" required ${readonly} >${checkResult.result}</textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<c:if test="${checkResult.status eq '01'}">
		<div style="text-align: center">
			<button type="button" onclick="save()" class="easyui-linkbutton" > <i class="fa fa-save"></i>保 存 </button>
		</div>
	</c:if>
</form>
<script type="text/javascript">
tableVBg('.alter-table-v-space');
$(document).ready(function (){
	
});
function save() {
	$.messager.confirm('提示', '确定保存？', function(r){
		if(r) {
			jQuery('#checkForm').form('submit',{
				onSubmit: function(){
					 return $('#checkForm').form('validate');
				},
			    success: function(data){
			    	var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							jQuery.messager.alert("提示信息",message,"error");
						} else if(parseInt(statusCode)==200){
							jQuery.messager.alert("提示信息","评估成功","info",function() {
								window.location.href="${ctx}/space/check/listCheck.do";
							});
						}
					}
			    }
			}); 
		}
		
	});
}
</script>