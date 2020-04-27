<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input type="hidden" name='requestParam.pagination.currentPage' id="currentPage"
		value="<c:out value="${requestParam.pagination.currentPage }"/>">
<c:if test="${requestParam.pagination.navEnd>0}">
<div class="paging">	
	
	<a href="###" <c:if test="${requestParam.pagination.currentPage!=1}">onclick="goPage(1);"</c:if>>首页</a>
	
	
	<a href="###" <c:if test="${requestParam.pagination.previous}">onclick="goPage(<c:out value="${requestParam.pagination.currentPage-1}"/>);"</c:if>>上一页</a>
	
	<c:forEach var="pageNo" begin="${requestParam.pagination.navBegin}"
		end="${requestParam.pagination.navEnd}">
		<c:choose>
			<c:when test="${requestParam.pagination.currentPage==pageNo}">
				<a href="###" class="cur"><c:out value="${pageNo}" /></a>
			</c:when>
			<c:otherwise>
				<a href="###" onclick="goPage(<c:out value="${pageNo}"/>);"><c:out
						value="${pageNo}" /></a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if
		test="${requestParam.pagination.navEnd<requestParam.pagination.pageCount}">
		<span>...</span>
	</c:if>
	
	<a href="###" <c:if test="${requestParam.pagination.next}">onclick="goPage(<c:out value="${requestParam.pagination.currentPage+1}"/>);"</c:if>>下一页</a>
	
	
	<a href="###" <c:if	test="${requestParam.pagination.currentPage<requestParam.pagination.pageCount}">onclick="goPage(<c:out value="${requestParam.pagination.pageCount}"/>);"</c:if>>尾页</a>
	
</div>
<script>
	function goPage(currentPage){
		$("input[name='requestParam.pagination.currentPage']").remove();
		$("<input type='hidden' name='requestParam.pagination.currentPage' value='"+currentPage+"'/>").appendTo('#${param.pageForm}');
		var fn = ${param.callback};
		var args= ${param.args};
		if(args==null){
			args=[''];
		}
		fn.apply(this,args);  
	}
</script>
</c:if>