<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp" %>
<div class="easyui-panel" style="width:100%">
<form id="listRecycleBinForm" action="${ctx}/cms/info/listRecycleBin.do?tabId=${param.tabId}" method="post">
<input type="hidden" name="paramMap[channelId]" value="${searchParam.paramMap.channelId}" />
<div id="listRecycleBinTableSearch" style="margin-left: 5px;margin-top: 10px;">
	<div>
        <span>名称:</span>
        <input type="text" style="width:200px" class="easyui-textbox" name="paramMap[title]" value="${searchParam.paramMap.title}" />
    <a href="javascript:void(0)" class="easyui-linkbutton main-btn" onclick="easyuiUtils.ajaxPostFormForPanel(getCurrentTabComId('listRecycleBinForm'),'${param['tabId']} #infoRecycleTree')"><i class="fa fa-search"></i> 查询</a>
   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('${param['tabId']} #infoRecycleTree')"><i class="fa fa-eraser"></i> 重置</a>
    </div>
    <br/>
    <div style="margin-bottom: 5px;">
       <a href="javascript:void(0)" class="easyui-linkbutton delete-btn" plain="false" onclick="delInfoWindow('${ctx}/cms/info/deleteInfo.do?cmsInfo.id=');"><i class="fa fa-minus"></i> 删除</a>
       <a href="javascript:void(0)" class="easyui-linkbutton delete-btn" plain="false" onclick="restoreInfoWindow('${ctx}/cms/info/restoreInfo.do?cmsInfo.isDeleted=N&cmsInfo.id=');"><i class="fa fa-reply"></i> 还原</a>
    </div>
</div>
<table id="listRecycleBinTable" style="width:99%;height:auto; min-height:400px"
        toolbar="#${param['tabId']} #listRecycleBinTableSearch"
        title="${title}" iconCls="fa fa-bell-o"
        rownumbers="true" pagination="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true" nowrap="false" fitColumns="true">
      <thead>
        <tr>
         <th width="30"  data-options="field:'id',checkbox:true"></th>
         <th width="100" data-options="field:'title'">标题</th>
         <%--<th width="50">类型</th>--%>
         <th width="80"  data-options="field:'addTime'">添加时间</th>
        </tr>
    </thead>
      <c:if test="${not empty resultList}">
        <tbody>
            <c:forEach var="list" items="${resultList }" varStatus="status">
            <tr>
                <td>${list.id}</td>
                <td>${list.title}</td>
                <td><fmt:formatDate value="${list.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                <input type="hidden" value="${list.channelId}" id="${list.id}"/>
                </td>
            </tr>
            </c:forEach>
    	</tbody>
       </c:if>
</table>
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
   <jsp:param name="pageForm" value="listRecycleBinForm" />
   <jsp:param name="tableId" value="listRecycleBinTable" />
   <jsp:param name="type" value="easyui" />
   <jsp:param name="divId" value="${param['tabId']} #infoRecycleTree" />
</jsp:include>
<script type="text/javascript">
 
	
	function delInfoWindow(url){
		var selectedId=getCurrentTab().find('#listRecycleBinTable').datagrid('getSelections');
		if(selectedId.length>0){
			easyuiUtils.confirmPostUrl('确认删除吗？',url+selectedId[0].id);
		}else{
			$.messager.alert('提示','请选择要删除的信息！');
		}
	}
	
	function restoreInfoWindow(url){
		var selectedId=getCurrentTab().find('#listRecycleBinTable').datagrid('getSelections');
		if(selectedId.length>0){
			easyuiUtils.confirmPostUrl('确认还原吗？',url+selectedId[0].id);
		}else{
			$.messager.alert('提示','请选择要还原的信息！');
		}
	}
 
</script>
</form>
</div>
