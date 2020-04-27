<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp" %>
<div class="easyui-panel" style="width:100%">
<form id="listSiteForm" action="${ctx}/cms/site/listSite.do?tabId=${param.tabId}" method="post">
<div id="listSiteTableSearch" style="margin-left: 5px;margin-top: 10px;">
	<div>
        <span>名称:</span>
        <input type="text" style="width:200px" class="easyui-textbox" name="paramMap[name]" value="${searchParam.paramMap.noticeTitle}" />
    <a href="#" class="easyui-linkbutton main-btn" onclick="easyuiUtils.ajaxPostFormForPanel(getCurrentTabComId('listSiteForm'),getCurrentTabId())"><i class="fa fa-search"></i> 查询</a>
   <a href="#" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel(getCurrentTabId())"><i class="fa fa-eraser"></i> 重置</a>
    </div>
    <br/>
    <div style="margin-bottom: 5px;">
       <a href="#" class="easyui-linkbutton" plain="false" onclick="addSiteWindow('${ctx}/cms/site/addSite.do?','add');"><i class="fa fa-plus"></i> 新增</a>
       <a href="#" class="easyui-linkbutton" plain="false" onclick="addSiteWindow('${ctx}/cms/site/addSite.do?cmsSite.id=','edit');"><i class="fa fa-pencil"></i> 修改</a>
       <a href="#" class="easyui-linkbutton delete-btn" plain="false" onclick="delSiteWindow('${ctx}/cms/site/deleteSite.do?cmsSite.id=');"><i class="fa fa-minus"></i> 删除</a>
    </div>
</div>
<table id="listSiteTable" style="width:99%;height:auto; min-height:400px"
        toolbar="#${param['tabId']} #listSiteTableSearch"
        title="${title}" iconCls="fa fa-bell-o"
        rownumbers="true" pagination="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true" nowrap="false" fitColumns="true">
      <thead>
        <tr>
         <th width="30"  data-options="field:'id',checkbox:true"></th>
         <th width="100" data-options="field:'name'">名称</th>
         <%--<th width="50">类型</th>--%>
         <th width="80"  data-options="field:'sourcePath'">源文件路径</th>
         <th width="80"  data-options="field:'siteDoMain'">域名</th>
         <th width="30"  data-options="field:'orderNum'">排序</th>
         <th width="30"  data-options="field:'isValid'">是否有效</th>
         <th width="50"  data-options="field:'yulan'">预览</th>
        </tr>
    </thead>
           <c:if test="${not empty resultList}">
        <tbody>
            <c:forEach var="list" items="${resultList }" varStatus="status">
            <tr>
                <td>${list.id}</td>
                <td>${list.name}</td>
                <td>${list.sourcePath}</td>
                <td>${list.siteDoMain}</td>
                <td>${list.orderNum}</td>
                <td>${list.isValid eq '01'?'是':'否'}</td>
                <td>
                	<a href="javascript:void(0);" class="easyui-linkbutton"><i class="fa fa-search"></i> 查询</a>
                </td>
            </tr>
            </c:forEach>
    </tbody>
        </c:if>
</table>
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
   <jsp:param name="pageForm" value="listSiteForm" />
   <jsp:param name="tableId" value="listSiteTable" />
   <jsp:param name="type" value="easyui" />
   <jsp:param name="divId" value="${param['tabId']}" />
</jsp:include>
<script type="text/javascript">
	function addSiteWindow(url,type){
		if(type=="edit"){
			var selectedId=getCurrentTab().find('#listSiteTable').datagrid('getSelections');
			if(selectedId.length>0){
				openWindow('siteWindow','新增站点',800,500,url+selectedId[0].id,true);	
			}else{
				$.messager.alert('提示','请选择要修改的站点！');
				return false;
			}
		}else{
			openWindow('siteWindow','新增站点',800,500,url,true);	
		}
	}
 
	function delSiteWindow(url){
		var selectedId=getCurrentTab().find('#listSiteTable').datagrid('getSelections');
		if(selectedId.length>0){
			easyuiUtils.confirmPostUrl('确认删除吗？',url+selectedId[0].id);
		}else{
			$.messager.alert('提示','请选择要删除的站点！');
		}
	}
	
</script>
</form>
</div>
</div>