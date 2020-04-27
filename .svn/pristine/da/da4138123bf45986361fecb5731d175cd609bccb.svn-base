<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>

<table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td>专利标题</td>
		<td colspan="3">${usePatent.patentName}</td>
	</tr>
	<tr>
		<td>专利申请号</td>
		<td>${usePatent.patentPublicNumber}</td>
		<td>使用类型</td>
		<td>
			${dict:getEntryName('IPBOX_APPLICATIN_TYPE',usePatent.useType)}
		</td>
	</tr>
	<tr>
		<td>转让类型</td>
		<td>
			${dict:getEntryName('IPBOX_PATENT_TRANSFER',usePatent.apType)}

		</td>
		<td>转让日期</td>
		<td>
			<fmt:formatDate value="${usePatent.apDate}" type="date" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	<tr>
		<td>转让方</td>
		<td>${usePatent.apFromMan }</td>
		<td>转让方代表</td>
		<td>${usePatent.apFromName }</td>
	</tr>
	<tr>
		<td>转让方地址</td>
		<td colspan="3">
			${usePatent.apFromAddress }
		</td>
	</tr>
	<tr>
		<td>受让方</td>
		<td>${usePatent.apToMan }</td>
		<td>受让方代表</td>
		<td>${usePatent.apToName}</td>
	</tr>
	<tr>
		<td>受让方地址</td>
		<td colspan="3">
			${usePatent.apToAddress }
		</td>
	</tr>
	<tr>
		<td>转让金额</td>
		<td colspan="3">
			${usePatent.apIncome }
		</td>
	</tr>
	<tr>
		<td>转让条件</td>
		<td colspan="3">
			${usePatent.apCondition }
		</td>
	</tr>
	<tr>
		<td >联系人电话</td>
		<td >
			${usePatent.contractNumber }
		</td>
		<td >所属公司</td>
		<td >
			${usePatent.orgDepId }
		</td>
	</tr>
	<tr>
		<td>
			上传合同附件
		</td>
		<td colspan="3">
			<c:if  test="${not empty usePatent.attachName}">
				<c:set value="${ipanthercore:getJSONMap(usePatent.attachName)}" var="map" />
				<div id="fileSpanAttachment">
					<span id="attachmentName">${map.attachmentName}</span>
				</div>
			</c:if>
		</td>
	</tr>
	<tr>
		<td>备注</td>
		<td colspan="3">${usePatent.remark }</td>
	</tr>
</table>

<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
});
</script>
