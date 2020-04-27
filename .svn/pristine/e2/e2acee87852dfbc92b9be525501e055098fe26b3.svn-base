<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
 <div class="easyui-panel" style="height: 400px" data-options="region:'center',title:'错误信息'">
	          	 <table class="easyui-datagrid" nowrap="false" fitColumns="true" >
						<thead> 
							<tr>
			                	<th field="rownum"  width="5%" >行数</th>
		                		<th field="failMessage"  width="95%" >导入错误信息</th>
							</tr>
						</thead>
						<tbody>   
					       	<c:forEach items="${results.failList}" var="model">
					        	<tr>
						           <td>${model.rownum }</td>
						           <td>${model.failMessage }</td>
						        </tr>
					       </c:forEach>
				    	</tbody>   
				</table> 
</div>
<div>
	<a style="text-decoration: underline;" href="${ctx}${results.filePath}">下载导入结果</a>
</div>
<div style="text-align:center; padding:5px;">
       	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindowAndRefresh();"><i class="fa fa-times"></i>关闭</a>
</div>
<script type="text/javascript">
	
	jQuery(document).ready(function(){
		<c:if test="${not empty results.message}">
			jQuery.messager.alert("提示信息",'${results.message}',function(){});
		</c:if>
	});
	
	function closeWindowAndRefresh() {
		closeWindow('showAddDialog');
		jQuery('#'+getCurrentTabId()).panel('refresh');
	}
</script>