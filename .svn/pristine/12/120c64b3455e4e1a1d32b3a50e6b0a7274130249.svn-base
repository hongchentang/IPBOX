<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp" %>
<form id="listDictEntryForm" action="${ctx}/common/datadict/listDictEntry.do?tabId=listDictEntryWindow&paramMap[dictTypeCode]=${searchParam.paramMap.dictTypeCode}" method="post">
<div id="listDictEntrySearch">

	<div style="margin: 10px">    
		数据项名称:
		<input class="easyui-textbox" type="text" name="paramMap[dictName]" value="${searchParam.paramMap.dictName}" />
		<button type="button" class="easyui-linkbutton main-btn" onclick="easyuiQuery('listDictEntryForm','listDictEntryWindow');"><i class="fa fa-search"></i> 查询</button>
		<button type="button" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('listDictEntryWindow')"><i class="fa fa-eraser"></i> 重置</button> 
	</div>
	<div style="margin: 10px">
		<button type="button" id="listDictEntryAddBtn"  class="easyui-linkbutton" ><i class="fa fa-plus"></i> 新增</button>
		<button type="button" id="listDictEntryEditBtn" class="easyui-linkbutton"><i class="fa fa-pencil"></i> 修改</button>
		<button type="button" id="listDictEntryDelBtn"  class="easyui-linkbutton delete-btn"><i class="fa fa-minus"></i> 删除</button>
	</div>
</div>
 <table id="listDictEntryList" title="数据字典项列表"	toolbar="#listDictEntryWindow #listDictEntrySearch" pagination="true"
 	rownumbers="true" fitColumns="true" style="height: 540px" singleSelect="true" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
					<th width="30" data-options="field:'id',checkbox:true"></th>
                    <th width="50" field="dictTypeCode">数据类型编码</th>
                    <th width="30" field="dictName">数据项名称</th>
           			<th width="30" field="dictValue">数据项值</th>
           			<th width="15" field="sortNo">排序号</th>
           			<th width="30" field="parentName">父数据项名称</th>
           			<th width="30" field="parentValue">父数据项值</th>
				</tr>
			</thead>
			<tbody>   
       			<c:if test="${not empty resultList}">
				  <c:forEach items="${resultList}"  var="list" varStatus="i">
                     <tr>
                     	<td><c:out value="${list.id}"/></td>
                        <td><c:out value="${list.dictTypeCode}"/></td>
                        <td><c:out value="${list.dictName}"/></td>
                        <td><c:out value="${list.dictValue}"/></td>
                        <td><c:out value="${list.sortNo}"/></td>
                        <td><c:out value="${list.parentName}"/></td>
                        <td><c:out value="${list.parentValue}"/></td>
                     </tr>
                   </c:forEach>  
                </c:if>    
    		</tbody>   
</table> 
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
	<jsp:param name="pageForm" value="listDictEntryForm" />
    <jsp:param name="tableId" value="listDictEntryList"/>
	<jsp:param name="type" value="easyui" />
	<jsp:param name="divId" value="listDictEntryWindow"/>
</jsp:include>
 </form>
<script type="text/javascript">
$(document).ready(function(e) {
	jQuery('#listDictEntryWindow').find('#listDictEntryAddBtn').linkbutton({    
		onClick:function(){
			easyuiUtils.openWindow('editDictEntryWindow','新增数据字典项',500,350, '${ctx}/common/datadict/goAddDictEntry.do?paramMap[dictTypeCode]=${searchParam.paramMap.dictTypeCode}',true);
		}
	}); 
 
	jQuery('#listDictEntryWindow').find('#listDictEntryEditBtn').linkbutton({    
		onClick:function(){
			var row=jQuery('#listDictEntryWindow').find('#listDictEntryList').datagrid('getSelected');
			if(row==null){
				$.messager.alert('提示','请选择一行数据进行操作！','warning');
			}else{
				easyuiUtils.openWindow('editDictEntryWindow','编辑数据字典项',500,350,'${ctx}/common/datadict/goEditDictEntry.do?paramMap[dictTypeCode]=${searchParam.paramMap.dictTypeCode}&dictEntry.id='+row.id,true);
			}
		}
	}); 
	jQuery('#listDictEntryWindow').find('#listDictEntryDelBtn').linkbutton({    
		onClick:function(){
			var row=jQuery('#listDictEntryWindow').find('#listDictEntryList').datagrid('getSelected');
			if(row==null){
				$.messager.alert('提示','请选择一行数据进行操作！','warning');
			}else{
				easyuiUtils.confirmPostUrl('是否确定删除数据字典 项‘'+row.dictName+'’?','${ctx}/common/datadict/deleteDictEntry.do?dictEntry.id='+row.id,'listDictEntryWindow','${ctx}/common/datadict/listDictEntry.do?paramMap[dictTypeCode]=${searchParam.paramMap.dictTypeCode}&tabId=listDictEntryWindow');
			}
		}
	}); 
});
</script>

 