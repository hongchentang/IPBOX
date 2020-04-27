<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp" %>
<div class="easyui-panel" style="width:100%">
<form id="listDirectiveForm" action="${ctx}/cms/directive/listDirective.do?tabId=${param.tabId}" method="post">
<div id="listDirectiveTableSearch" style="margin-left: 5px;margin-top: 10px;">
	<div>
        <span>名称:</span>
        <input type="text" style="width:200px" class="easyui-textbox" name="paramMap[name]" value="${searchParam.paramMap.name}" />
    <a href="javascript:void(0)" class="easyui-linkbutton main-btn" onclick="easyuiUtils.ajaxPostFormForPanel(getCurrentTabComId('listDirectiveForm'),'${param['tabId']}')"><i class="fa fa-search"></i> 查询</a>
   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('${param['tabId']}')"><i class="fa fa-eraser"></i> 重置</a>
    </div>
    <br/>
    <div style="margin-bottom: 5px;">
       <a href="javascript:void(0)" class="easyui-linkbutton" plain="false" onclick="addDirectiveWindow('${ctx}/cms/directive/addDirective.do','add');"><i class="fa fa-plus"></i> 新增</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" plain="false" onclick="addDirectiveWindow('${ctx}/cms/directive/addDirective.do?cmsDirective.id=','edit');"><i class="fa fa-pencil"></i> 修改</a>
       <a href="javascript:void(0)" class="easyui-linkbutton delete-btn" plain="false" onclick="delDirectiveWindow('${ctx}/cms/directive/deleteDirective.do?cmsDirective.id=');"><i class="fa fa-minus"></i> 删除</a>
    </div>
</div>
<table id="listDirectiveTable" style="width:99%;height:auto; min-height:400px"
        toolbar="#${param['tabId']} #listDirectiveTableSearch"
        title="标签列表" iconCls="fa fa-bell-o"
        rownumbers="true" pagination="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true" nowrap="false" fitColumns="true">
      <thead>
        <tr>
         <th width="30"  data-options="field:'id',checkbox:true"></th>
         <th width="100" data-options="field:'name'">名称</th>
         <%--<th width="50">类型</th>--%>
         <th width="50"  data-options="field:'orderNum'">排序号</th>
        </tr>
    </thead>
      <c:if test="${not empty resultList}">
        <tbody>
            <c:forEach var="list" items="${resultList }" varStatus="status">
            <tr>
                <td>${list.id}</td>
                <td><a href="javascript:void(0)" onclick="addDirectiveWindow('${ctx}/cms/directive/readDirective.do?cmsDirective.id=${list.id}','add')">${list.name}</a></td>
                <td>${list.orderNum}</td>
            </tr>
            </c:forEach>
    	</tbody>
       </c:if>
</table>
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
   <jsp:param name="pageForm" value="listDirectiveForm" />
   <jsp:param name="tableId" value="listDirectiveTable" />
   <jsp:param name="type" value="easyui" />
   <jsp:param name="divId" value="${param['tabId']}" />
</jsp:include>
<script type="text/javascript">
	function addDirectiveWindow(url,type){
		if(type=="edit"){
			var selectedId=getCurrentTab().find('#listDirectiveTable').datagrid('getSelections');
			if(selectedId.length>0){
				//getCurrentTab().find("#infoEditTree").panel('refresh',url+selectedId[0].id);
				openWindow('addDirective','编辑标签',1000,800,url+selectedId[0].id,true);	
			}else{
				$.messager.alert('提示','请选择要修改的数据！');
				return false;
			}
		}else{
			openWindow('addDirective','新增标签',1000,800,url,true);	
		}
	}
	
	function delDirectiveWindow(url){
		var selectedId=getCurrentTab().find('#listDirectiveTable').datagrid('getSelections');
		if(selectedId.length>0){
			easyuiUtils.confirmPostUrl('确认删除吗？',url+selectedId[0].id);
		}else{
			$.messager.alert('提示','请选择要删除的数据！');
		}
	}
</script>
</form>
</div>
