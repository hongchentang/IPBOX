<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp" %>
<form id="listDictTypeForm" action="${ctx}/common/datadict/listDictType.do?tabId=${param.tabId }" method="post">
<div id="listDictTypeSearch">

	<div style="margin: 10px">    
		名称:<input class="easyui-textbox" type="text" name="paramMap[dictTypeName]" value="${searchParam.paramMap.dictTypeName}" />
		编码:<input class="easyui-textbox" type="text" name="paramMap[dictTypeCode]" value="${searchParam.paramMap.dictTypeCode}" />
		<button type="button" class="easyui-linkbutton main-btn" onclick="easyuiQuery('listDictTypeForm',getCurrentTabId());"><i class="fa fa-search"></i> 查询</button>
		<button type="button" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('${param['tabId']}')"><i class="fa fa-eraser"></i> 重置</button> 
	</div>
	<div style="margin: 10px">
		<button type="button" id="listDictTypeAddBtn"  class="easyui-linkbutton" ><i class="fa fa-plus"></i> 新增</button>
		<button type="button" id="listDictTypeEditBtn" class="easyui-linkbutton"><i class="fa fa-pencil"></i> 修改</button>
		<button type="button" id="listDictTypeDelBtn"  class="easyui-linkbutton delete-btn"><i class="fa fa-minus"></i> 删除</button>
		<button type="button" id="listDictTypeEditSonBtn"  class="easyui-linkbutton"><i class="fa fa-pencil"></i> 编辑子项</button>
			
	</div>
</div>
 <table id="listDictTypeList" title="数据字典列表"	toolbar="#${param.tabId } #listDictTypeSearch" pagination="true"
 	rownumbers="true" fitColumns="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
					<th width="30" data-options="field:'id',checkbox:true"></th>
                    <th width="30" field="dictTypeCode">数据类型编码</th>
		 			<th width="30" field="dictTypeName" >数据类型名称</th>
				</tr>
			</thead>
			<tbody>   
       			<c:if test="${not empty resultList}">
				  <c:forEach items="${resultList}"  var="list" varStatus="i">
			         <tr>
			         	 <td>${list.dictTypeCode }</td>
	                     <td>
	                     	${list.dictTypeCode }
	                     </td>
	                     <td>${list.dictTypeName }</td>
	                  </tr>
                </c:forEach>  
                </c:if>    
    </tbody>   
</table> 
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
	<jsp:param name="pageForm" value="listDictTypeForm" />
    <jsp:param name="tableId" value="listDictTypeList"/>
	<jsp:param name="type" value="easyui" />
	<jsp:param name="divId" value="${param['tabId']}"/>
</jsp:include>
 </form>
<script type="text/javascript">
$(document).ready(function(e) {
	getCurrentTab().find('#listDictTypeAddBtn').linkbutton({    
		onClick:function(){
			easyuiUtils.openWindow('editDictTypeWindow','新增数据字典',400,170, '${ctx}/common/datadict/goAddDictType.do',true);
		}
	}); 
 
	getCurrentTab().find('#listDictTypeEditBtn').linkbutton({    
		onClick:function(){
			var row=getCurrentTab().find('#listDictTypeList').datagrid('getSelected');
			if(row==null){
				$.messager.alert('提示','请选择一行数据进行操作！','warning');
			}else{
				easyuiUtils.openWindow('editDictTypeWindow','编辑数据字典',400,170,'/common/datadict/goEditDictType.do?dictType.dictTypeCode='+row.id,true);
			}
		}
	}); 
	getCurrentTab().find('#listDictTypeDelBtn').linkbutton({    
		onClick:function(){
			var row=getCurrentTab().find('#listDictTypeList').datagrid('getSelected');
			if(row==null){
				$.messager.alert('提示','请选择一行数据进行操作！','warning');
			}else{
				easyuiUtils.confirmPostUrl('是否确定删除数据字典 ‘'+row.dictTypeName+'’?','${ctx}/common/datadict/deleteDictType.do?dictType.dictTypeCode='+row.id);
			}
		}
	}); 
	
	getCurrentTab().find('#listDictTypeEditSonBtn').linkbutton({    
		onClick:function(){
			editSon(); 
		}
	}); 
});

function editSon(dictTypeCode,title){
	if(dictTypeCode){
		if(dictTypeCode==""){
			$.messager.alert('提示','请选择一行数据进行操作！','warning');
		}else{
			easyuiUtils.openWindow('listDictEntryWindow',title,800,650,'${ctx}/common/datadict/listDictEntry.do?paramMap[dictTypeCode]='+dictTypeCode,true);
		}	
	}else{
		var row=getCurrentTab().find('#listDictTypeList').datagrid('getSelected');
		if(row==null){
			$.messager.alert('提示','请选择一行数据进行操作！','warning');
		}else{
			easyuiUtils.openWindow('listDictEntryWindow',row.dictTypeName,800,650,'${ctx}/common/datadict/listDictEntry.do?paramMap[dictTypeCode]='+row.id,true);
		}
	}
}

function importDictEntry(url){
	easyuiUtils.openWindow('importDictEntryWindow','导入数据字典',700,650,url,true);
}
</script>