<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<input type="hidden" name="tabId" value="${param.divId}">
<input id="currentPage" type="hidden" name="pagination.currentPage" value="${searchParam.pagination.currentPage }"/>
<input id="pageSize" type="hidden" name="pagination.pageSize" value="${searchParam.pagination.pageSize }"/>
<c:set var="tableId" value="#${param.divId} #${param.tableId}" />
<c:set var="formId" value="#${param.divId} #${param.pageForm}" />
<script>
	$(function() {
		$('${tableId}').datagrid({
			pageSize : parseInt('${searchParam.pagination.pageSize}'),
			pageNumber : parseInt('${searchParam.pagination.currentPage}')
		});
        var tab = $('${tableId}');
		var pagenation = tab.datagrid('getPager');
		$(pagenation).pagination({
			total : parseInt('${searchParam.pagination.rowCount}'),
			pageSize : parseInt('${searchParam.pagination.pageSize}'),
			pageNumber : parseInt('${searchParam.pagination.currentPage}'),
			onSelectPage:function(pageNumber, pageSize){
				$('${formId} #currentPage').val(pageNumber);
				$('${formId} #pageSize').val(pageSize);
				if('${param.type}' != null && '${param.type}' == 'ajax'){
					$.ajaxQuery('${formId}', '${param.divId}');
				}else if('${param.type}' != null && '${param.type}' == 'easyui'){
					/* $('#${param.divId}').panel({
						href:$('${formId}').attr('action'),
						queryParams:serializeObject($('${formId}')),
						method:'post'
					});
					$('#${param.divId}').panel('refresh'); */
					$('#${param.divId}').panel('options').queryParams=serializeObject($('${formId}'));
					$('#${param.divId}').panel('options').method='post';
					$('#${param.divId}').panel('refresh',$('${formId}').attr('action'));
				}else{
					document.getElementById('${formId}').submit();
				}
			}
		});
	});
</script>

