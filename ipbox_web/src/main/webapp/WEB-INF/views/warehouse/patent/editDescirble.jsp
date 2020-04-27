<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<form id="patentMDesForm" name="patentMDesForm" >
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="20%">编号</td>
				<td colspan="3" style="text-align:left;font-size:16px;">
					${patentMDes._id}
				</td>
			</tr>		    	
    	    <tr>
				<td width="20%">专利说明</td>
				<td colspan="3" style="text-align:left;font-size:16px;">
					<c:forEach var="list" items="${patentMDes.description}" varStatus="status">
						<c:forEach var="contextList" items="${list.original}" varStatus="status">
							<br/> [${contextList.id}] ${contextList.text} <br/>
						</c:forEach>
					</c:forEach>
				</td>
			</tr>		    
		</tbody>
	</table>
	<div style="text-align: center">
		<button type="button" onclick="save()" class="easyui-linkbutton" > <i class="fa fa-save"></i>保 存 </button>
		<button type="reset"  class="easyui-linkbutton" class="easyui-linkbutton"> 重 置 </button>
	</div>
</form>
<script type="text/javascript">
 tableVBg('alter-table-v');
$(document).ready(function (){	

// 	alert(${patentM});
// 	var obj = ${patentMDes};
//  	alert(obj._id);
//  	var newstr=JSON.stringify(obj); //返回一个新字符串
//  	console.log(newstr);
});

</script>
