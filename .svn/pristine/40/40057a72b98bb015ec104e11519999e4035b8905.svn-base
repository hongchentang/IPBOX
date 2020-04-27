<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<form id="patentPhotoForm" name="patentPhotoForm" >
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="20%">专利附图编号</td>
				<td colspan="3" style="text-align:left;font-size:16px;">
					${patentPhoto.publication_id}
				</td>
			</tr>
			<tr>
				<td width="20%">专利公开号</td>
				<td colspan="3" style="text-align:left;font-size:16px;">
					${patentPhoto.publication_number}
				</td>
			</tr>
			<tr>
				<td width="20%">专利附图压缩文件</td>
				<td colspan="3" style="text-align:left;font-size:16px;">
					${patentPhoto.figures_path}（点击此处下载）
				</td>
			</tr>				    	
    	    <tr>
				<td width="20%">专利附图</td>
				<td colspan="3" style="text-align:left;font-size:16px;">
					<c:forEach var="list" items="${patentPhoto.figures_filename}" varStatus="status">							
						<img src="${list}" width="175" height="47" alt="" />${list}
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
	var obj = ${patentPhoto};
 	alert(obj._id);
 	var newstr=JSON.stringify(obj); //返回一个新字符串
 	console.log(newstr);
});

</script>
