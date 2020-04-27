<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<form id="patentLegalForm" name="patentLegalForm" >
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="20%">专利编号</td>
				<td colspan="3" style="text-align:left;font-size:16px;">
					${patentLegal._id}
				</td>
			</tr>
			<tr>
				<td width="20%">最终法律状态</td>
				<td colspan="3" style="text-align:left;font-size:16px;">
					${patentLegal.patent_legal_status.legal_status}<span style="color:red">(1公开，2 授权，3失效)</span>
				</td>
			</tr>
			<tr>
				<td width="20%">失效日期</td>
				<td width="30%" style="text-align:left;font-size:16px;">
					${patentLegal.patent_legal_status.lapse_date}
				</td>
				<td width="20%">授权日期</td>
				<td width="30%" style="text-align:left;font-size:16px;">
					${patentLegal.patent_legal_status.authorization_date}
				</td>
			</tr>					    	
    	    <tr>
				<td width="20%">法律状态详情</td>
				<td colspan="3" style="text-align:left;font-size:16px;">
					<c:forEach var="list" items="${patentLegal.patent_legal_status.legal_status_history}" varStatus="status">							
						<label>法律状态公开日：</label>${list.publication_date} <br/>
						<label>状态代码：</label>${list.code} <br/>
						<label>法律状态详情描述：</label>${list.description} <br/>
						<label>法律状态详情类型：</label>${list.category} <br/><hr>
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
// 	var obj = ${patentLegal};
//  	alert(obj._id);
//  	var newstr=JSON.stringify(obj); //返回一个新字符串
//  	console.log(newstr);
});

</script>
