<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="pagination"></div>
<input id="currentPage" type="hidden" name="pagination.currentPage" value="${searchParam.pagination.currentPage }"/>
<input id="pageSize" type="hidden" name="pagination.pageSize" value="${searchParam.pagination.pageSize }"/>
<script>
	$(function() {
		$('#${param.pageForm} #pagination').pagination({
			total : parseInt('${searchParam.pagination.rowCount}'),
			pageSize : parseInt('${searchParam.pagination.pageSize}'),
			pageNumber : parseInt('${searchParam.pagination.currentPage}'),
			onSelectPage:function(pageNumber, pageSize){
				$('#${param.pageForm} #currentPage').val(pageNumber);
				$('#${param.pageForm} #pageSize').val(pageSize);
				if('${param.type}' != null && '${param.type}' == 'ajax'){
					$.ajaxQuery('${param.pageForm}', '${param.divId}');
				}else if('${param.type}' != null && '${param.type}' == 'easyui'){
					$('#${param.divId}').panel({
						href:$('#${param.pageForm}').attr('action'),
						queryParams:serializeObject($('#${param.pageForm}')),
						method:'post'
					});
					$('#${param.divId}').panel('refresh');
				}else{
					document.getElementById('${param.pageForm}').submit();
				}
			}
		});
	});
</script>

