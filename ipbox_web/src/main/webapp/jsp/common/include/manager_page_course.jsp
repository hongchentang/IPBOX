<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<input type="hidden" id="currentPage" name="pagination.currentPage" value="${searchParam.pagination.currentPage}">
<c:set var="pageList" value="${ipanthercommon:getPageList(searchParam.pagination.pageCount) }"></c:set>
<tfoot>
    <tr align="right">
      <td colspan="2">总记录条数：<em></em>${not empty searchParam.pagination.rowCount?searchParam.pagination.rowCount:0 }条</td>
      <td colspan="2">总页数：<em>${not empty searchParam.pagination.pageCount?searchParam.pagination.pageCount:0 }</em>页</td>
      <td colspan="2">
	      	<div class="m-jumpPage1">
				<c:if test="${searchParam.pagination.pageCount>0 }">
					<c:if test="${searchParam.pagination.previous eq true}">
					<a href="javascript:void(0);" onclick="previous()" >上一页</a>
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
						<a href="javascript:void(0);" onclick="next()">下一页</a>
						<a href="javascript:void(0);" onclick="goPage('${searchParam.pagination.pageCount}')" >末页</a>
					</c:if>
				</c:if>
			</div>
	      </td>
	      </tr>
	  </tfoot>
<!-- 

<tfoot>
	    <tr align="right">
	      <td colspan="2">总记录条数：<em>56</em>条</td>
	      <td colspan="2">总页数：<em>5</em>页</td>
	      <td colspan="2">
	      	<div class="m-jumpPage1">
	      		<a href="javascript:void(0);" class="z-crt">1</a>
	      		<a href="javascript:void(0);">2</a>
	      		<a href="javascript:void(0);">3</a>
	      		<a href="javascript:void(0);" class="z-crt">1</a>
	      		<a href="javascript:void(0);">2</a>
	      		<a href="javascript:void(0);">3</a>
	      		<a href="javascript:void(0);" class="z-crt">1</a>
	      		<a href="javascript:void(0);">2</a>
	      		<a href="javascript:void(0);">3</a>
	      		<span>…</span>
	      		<a href="javascript:void(0);">下一页</a>
	      		<a href="javascript:void(0);">末页</a>
	      	</div>
	      </td>
	      </tr>
	  </tfoot>
 -->
<script type="text/javascript">
$(function(){
	$("#${searchParam.pagination.currentPage }","#${param.pageForm}").addClass("z-crt");
})
function previous(){
	var nextNum=parseInt('${searchParam.pagination.currentPage }');
	if(nextNum==1){
		goPage(nextNum);
	}else{
		goPage(nextNum-1);
	}
	
}
function next(){
	var nextNum=parseInt('${searchParam.pagination.currentPage }');
	var maxNum=parseInt('${searchParam.pagination.pageCount }');
	if(nextNum==maxNum){
		goPage(maxNum);
	}else{
		goPage(nextNum+1);
	}
}

function goPage(num){
	$("#currentPage","#"+"${param.pageForm}").val(num);
	var rData = $.ajax({
		url : $("#"+"${param.pageForm}").attr("action"),
		data : $("#"+"${param.pageForm}").serialize(),
		type : "post",
		async : false,
		success : function(data) {
			$("#"+"${param.divId}").html(data);
		}
	})
}
</script>
