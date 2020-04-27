<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<input type="hidden" id="currentPage" name="pagination.currentPage" value="${searchParam.pagination.currentPage}">
<c:set var="pageList" value="${ipanthercommon:getPageList(searchParam.pagination.pageCount) }"></c:set>
<div class="m-jumppage">
<c:if test="${searchParam.pagination.pageCount>0 }">
	<c:if test="${searchParam.pagination.previous eq true}">
	<a href="javascript:void(0);" onclick="previous()" class="prev">&lt;</a>
	</c:if>
	<!--前半部分页数  -->
	
	<c:if test="${searchParam.pagination.currentPage -4<0}">
	<c:forEach items="${pageList }"  begin="0" end="${searchParam.pagination.currentPage-1}" varStatus="i">
		<a href="javascript:void(0);" onclick="goPage('${i.index+1}')" id="${i.index+1}">${i.index+1}</a>
	</c:forEach>
	</c:if>
	<c:if test="${searchParam.pagination.currentPage -4>=0}">
	<c:forEach items="${pageList }"  begin="0" end="3" varStatus="i">
		<a href="javascript:void(0);" onclick="goPage('${i.index+1+searchParam.pagination.currentPage-4}')" id="${i.index+1+searchParam.pagination.currentPage-4}">${i.index+1+searchParam.pagination.currentPage-4}</a>
	</c:forEach>
	</c:if>
	<!-- 后半部分页数 -->
	<!-- 当前页数加4大于总页数，且不等于总页数 -->
	<c:if test="${searchParam.pagination.currentPage +4 >searchParam.pagination.pageCount  and searchParam.pagination.currentPage !=searchParam.pagination.pageCount and searchParam.pagination.currentPage -1>=0}">
	<c:forEach items="${pageList }"  begin="0" end="${searchParam.pagination.pageCount-searchParam.pagination.currentPage-1}" varStatus="i">
		<a href="javascript:void(0);" onclick="goPage('${i.index+1+searchParam.pagination.currentPage}')" id="${i.index+1+searchParam.pagination.currentPage }">${i.index+1+searchParam.pagination.currentPage}</a>
	</c:forEach>
	</c:if>
	<!-- 当前页数加4小于总页数 -->
	<c:if test="${searchParam.pagination.currentPage +4<=searchParam.pagination.pageCount}">
	<c:forEach items="${pageList }"  begin="0" end="2" varStatus="i">
		<a href="javascript:void(0);" onclick="goPage('${i.index+1+searchParam.pagination.currentPage }')" id="${i.index+1+searchParam.pagination.currentPage }">${i.index+1+searchParam.pagination.currentPage}</a>
	</c:forEach>
	</c:if>
	<c:if test="${searchParam.pagination.next eq true}">
	<a href="javascript:void(0);" onclick="next()" class="next">&gt;</a>
	</c:if>
</c:if>
</div>
<!-- <div class="m-jumppage">
	<a href="javascript:void(0);" class="prev">&lt;</a>
	<a href="javascript:void(0);" class="z-crt">1</a>
	<a href="javascript:void(0);">2</a>
	<a href="javascript:void(0);">3</a>
	<a href="javascript:void(0);">4</a>
	<a href="javascript:void(0);">5</a>
	<a href="javascript:void(0);">6</a>
	<a href="javascript:void(0);">7</a>
	<a href="javascript:void(0);">8</a>
	<a href="javascript:void(0);">9</a>
	<a href="javascript:void(0);">10</a>
	<a href="javascript:void(0);" class="next">&gt;</a>
</div>
 -->
<script type="text/javascript">
var currentPage="${searchParam.pagination.currentPage }";
var pageCount="${searchParam.pagination.pageCount }";
var pageForm="${param.pageForm}";
var divId="${param.divId}";
$(function(){
	$("#"+currentPage,"#"+pageForm).addClass("z-crt");
})
function previous(){
	var nextNum=parseInt(currentPage);
	if(nextNum==1){
		goPage(nextNum);
	}else{
		goPage(nextNum-1);
	}
	
}
function next(){
	var nextNum=parseInt(currentPage);
	var maxNum=parseInt(pageCount);
	if(nextNum==maxNum){
		goPage(maxNum);
	}else{
		goPage(nextNum+1);
	}
}

function goPage(num){
	$("#currentPage","#"+pageForm).val(num);
	if(""!=divId) {//刷新div
		var rData = $.ajax({
			url : $("#"+pageForm).attr("action"),
			data : $("#"+pageForm).serialize(),
			type : "post",
			async : false,
			success : function(data) {
				$("#"+divId).html(data);
			}
		});
	} else {//提交整个form
		$("#"+pageForm).submit();
	}
	
}
</script>
