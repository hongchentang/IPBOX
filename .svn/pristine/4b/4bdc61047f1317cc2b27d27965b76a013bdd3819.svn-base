<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="/jsp/common/include/taglib.jsp"%>
<%-- <div class="pageContent">
	<div class="tabs" currentIndex="0" eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>导入成功的数据列表</span></a></li>
					<li><a href="javascript:;"><span>导入失败的数据列表</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent" style="height:230px;">
			<div>
		<table  class="table" width="1200" layoutH="260">
					<thead align="left">
						<tr align="center">
							<th width="30">列数</th>
							<th width="200">数据类型名称</th>
							<th width="200">数据类型编码</th>
							<th width="90">数据项名称</th>
							<th width="90">数据项值</th>
							<th width="60">排序号</th>
							<th width="220">父数据项名称</th>
							<th width="90">父数据项值</th>
						</tr>
					</thead>
					<tbody  align="left">
						<c:if test="${not empty  resultMap.successList}">
							<c:forEach var="vo" items="${resultMap.successList}">
								<tr align="left">
								    <td>${vo.rownum+1}</td>
									<td align="center"><c:out value="${vo.dictTypeName }" /></td>
									<td align="center"><c:out value="${vo.dictTypeCode }" /></td>
									<td align="center"><c:out value="${vo.dictName }" /></td>
									<td align="center"><c:out value="${vo.dictValue }" /></td>
									<td align="center"><c:out value="${vo.sortNo }" /></td>
									<td align="center"><c:out value="${vo.dictParentName }" /></td>
									<td align="center"><c:out value="${vo.dictParentValue }" /></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<div>
	 			<table class="table" width="95%">
		<thead>
			<tr>
				<th width="100">行号</th>
				<th>失败原因</th>
			</tr>
		</thead>
		<tbody>
	<c:if test="${not empty  resultMap.failList}">
			<c:forEach var="vo" items="${resultMap.failList}">
				<tr>
					<td align="center"><c:out value="${vo.rownum+1}" /></td>
					<td align="center"><c:out value="${vo.failMessage}" /></td>
				</tr>
			</c:forEach>
	</c:if>
		</tbody>
	</table>
			</div>
		</div>
		<div class="tabsFooter" style="height:25px;">
			<div class="tabsFooterContent" style="height:25px;">
				<a class="buttonActive" href="${pageContext.request.contextPath }${resultMap.filePath}" target="downloadDictEntryResult" title="下载导入结果"><span>下载导入结果</span></a>
			
			</div>
		</div>
	</div>
	</div> --%>