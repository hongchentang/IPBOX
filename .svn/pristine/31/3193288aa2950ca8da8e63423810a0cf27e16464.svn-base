<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
<div class="col-md-5 col-sm-15" style="text-align: left;float: left;">
<div class="dataTables_paginate paging_bootstrap">
			<ul class="pagination" style="visibility: visible;">
                        	<li>
                            	<a>每页
                            	<select name="pagination.pageSize" id="pageSize" style="margin-bottom: -4px; margin-top: -4px; width: 40px; height: 20px; " onchange="submitPage()">
									<option value="10" <c:if test="${searchParam.pagination.pageSize==10}">selected</c:if>>10</option>
									<option value="20" <c:if test="${searchParam.pagination.pageSize==20}">selected</c:if>>20</option>
									<option value="50" <c:if test="${searchParam.pagination.pageSize==50}">selected</c:if>>50</option>
								</select>条
                                </a>
                            </li>
                            <li class="disabled">
                            <a>                                                                                       
	                            <c:choose>
	                            	<c:when test="${not empty searchParam.pagination.currentPage and searchParam.pagination.currentPage > 1}">
	                            		   第${(searchParam.pagination.currentPage-1)*searchParam.pagination.pageSize}
	                            		 <c:choose>
	                            		 			<c:when test="${searchParam.pagination.currentPage*searchParam.pagination.pageSize>searchParam.pagination.rowCount}">
	                            		 				-${searchParam.pagination.rowCount}条记录
	                            		 			</c:when>
	                            		 			<c:otherwise>
	                            		 				-${searchParam.pagination.currentPage*searchParam.pagination.pageSize}条记录
	                            		 			</c:otherwise>
	                            		 </c:choose> 
	                            	</c:when>
	                            	<c:otherwise>
	                            		 <c:choose>
	                            		 	<c:when test="${searchParam.pagination.rowCount == 0 }">
	                            		 		   第 0 条记录
	                            		 	</c:when>
	                            		 	<c:otherwise>
	                            		 		<c:choose>
	                            		 			<c:when test="${searchParam.pagination.currentPage*searchParam.pagination.pageSize>searchParam.pagination.rowCount}">
	                            		 				 第 1-${searchParam.pagination.rowCount}条记录
	                            		 			</c:when>
	                            		 			<c:otherwise>
	                            		 				 第 1-${searchParam.pagination.currentPage*searchParam.pagination.pageSize}条记录
	                            		 			</c:otherwise>
	                            		 		</c:choose>
	                            		 	</c:otherwise>
	                            		 </c:choose>
	                            	</c:otherwise>
	                            </c:choose>
                            	/ 共${searchParam.pagination.rowCount}条
                            </a>
                            </li>
                        </ul>
          </div>              
</div>
<div class="col-md-7 col-sm-16 " style="text-align: right;float: right;">
       <div class="dataTables_paginate paging_bootstrap" style="text-align: right;float: right;">
			<ul class="pagination" style="visibility: visible;" >

				<!-- 跳转 -->
				<li ><a id="skip" href="#" onclick="skipPage();">转到</a></li>
				<li><a >第 <input  style="width: 23px;height: 17px;" type="text" id="current_page"
						value="${searchParam.pagination.currentPage}"/>
						页
				</a></li>
				<!-- 分页首页按钮 -->
				<li
					<c:if test="${searchParam.pagination.currentPage==1}">class="prev disabled"</c:if>>
					<a
					<c:if test="${searchParam.pagination.currentPage!=1}">onclick="indexPage();" style="cursor:pointer;"</c:if>>
						<span>首页</span>
				</a>
				</li>
				<li <c:if test="${!searchParam.pagination.previous}">class="disabled"</c:if>>
					<a
					<c:if test="${searchParam.pagination.previous}">onclick="previous();" style="cursor:pointer;"</c:if>>
						前一页 </a>
				</li>
				<!-- 下一页按钮 -->
				<li
					class='next <c:if test="${!searchParam.pagination.next}">disabled</c:if>'>
					<a
					<c:if test="${searchParam.pagination.next}">onclick="next();" style="cursor:pointer;"</c:if>>
						下一页 </a>
				</li>
				<li
					<c:if test="${searchParam.pagination.currentPage>=searchParam.pagination.pageCount}">class="disabled"</c:if>>
					<a
					<c:if test="${searchParam.pagination.currentPage<searchParam.pagination.pageCount}">onclick="lastPage()" style="cursor:pointer;"</c:if>>
						尾页 </a>
				</li>
			</ul>
		</div>
</div>
</div>
<input type="hidden" id="currentPage" name="pagination.currentPage" value="<c:out value="${searchParam.pagination.currentPage }"/>">
<div class="modal hide fade">
    <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h3>对话框标题</h3>
    </div>
    <div class="modal-body">
    <p>One fine body…</p>
    </div>
    <div class="modal-footer">
    <a href="#" class="btn">关闭</a>
    <a href="#" class="btn btn-primary">Save changes</a>
    </div>
</div>
<script>
	function indexPage(){
		$("#${param.pageForm} #currentPage").val(1);
		submitPage();
	}
	
	function lastPage(){
		$("#${param.pageForm} #currentPage").val('${searchParam.pagination.pageCount }');
		submitPage();
	}
	
	function previous(){
		var currentPage = $("#${param.pageForm} #currentPage");
		if(parseInt($(currentPage).val())>1){
			$(currentPage).val(parseInt($(currentPage).val())-1);
			submitPage();
		}else{
			bootbox.alert("已经是第一页");
		}
	}
	
	function next(){
		var currentPage = $("#${param.pageForm} #currentPage");
		if(parseInt($(currentPage).val())<parseInt('${searchParam.pagination.pageCount}')){	
			$(currentPage).val(parseInt($(currentPage).val())+1);
			submitPage();
		}else{
			bootbox.alert("已经是最后一页");
		}
	}
	
	function skipPage(){
		var goPage = $('#${param.pageForm} #current_page');
		if(parseInt($(goPage).val()) <= parseInt('${searchParam.pagination.pageCount}')&&parseInt($(goPage).val())>=1){
			$("#${param.pageForm} #currentPage").val($(goPage).val());
			submitPage();
		}else{
			bootbox.alert("输入的跳转页不在页面范围内");
		}
	}
	
	function submitPage(){
		<c:choose>
		<c:when test="${not empty param.callback}">
		var fn = ${param.callback};
		var args= ${param.args};
		if(args==null){
			args=[''];
		}
		fn.apply(this,args);  
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${not empty param.type and param.type eq 'ajax'}">
					$.ajaxQuery("${param.pageForm}","${param.divId}");
				</c:when>
				<c:otherwise>
					document.getElementById("${param.pageForm}").submit();
				</c:otherwise>
			</c:choose>
		</c:otherwise>
		</c:choose> 
	}
</script>