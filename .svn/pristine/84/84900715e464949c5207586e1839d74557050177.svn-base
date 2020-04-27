<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/jsp/common/include/taglib.jsp" %>
 <form id="listLinkForm" action="${ctx}/cms/link/listLink.do?tabId=${param.tabId}" method="post">
<input type="hidden" name="paramMap[siteId]" value="${searchParam.paramMap.siteId}" />
<div id="listLinkTableSearch" style="margin-left: 5px;margin-top: 10px;">
	<div>
        <span>链接名:</span>
        <input type="text" style="width:200px" class="easyui-textbox" name="paramMap[name]" value="${searchParam.paramMap.name}" />
    	<span>链接类型:</span>
    	<select name="paramMap[type]" style="width: 155px;" data-options="panelWidth:'150px',panelHeight:'auto',required:true"  class="easyui-combobox" >
							<option value="">--请选择--</option>
							${dict:getEntryOptionsSelected('LINK_TYPE',searchParam.paramMap.type)}
		</select>
    <a href="javascript:void(0)" class="easyui-linkbutton main-btn" onclick="easyuiUtils.ajaxPostFormForPanel(getCurrentTabComId('listLinkForm'),'${param['tabId']}')"><i class="fa fa-search"></i> 查询</a>
   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('${param['tabId']}')"><i class="fa fa-eraser"></i> 重置</a>
    </div>
    <br/>
    <div style="margin-bottom: 5px;">
       <a href="javascript:void(0)" class="easyui-linkbutton" plain="false" onclick="addLinkWindow('${ctx}/cms/link/addLink.do?cmsLink.siteId=${searchParam.paramMap.siteId}','add');"><i class="fa fa-plus"></i> 新增</a>
       <a href="javascript:void(0)" class="easyui-linkbutton" plain="false" onclick="addLinkWindow('${ctx}/cms/link/addLink.do?cmsLink.siteId=${searchParam.paramMap.siteId}&cmsLink.id=','edit');"><i class="fa fa-pencil"></i> 修改</a>
       <a href="javascript:void(0)" class="easyui-linkbutton delete-btn" plain="false" onclick="delLinkWindow('${ctx}/cms/link/deleteLink.do?cmsLink.id=');"><i class="fa fa-minus"></i> 删除</a>
    </div>
</div>
<table id="listLinkTable" style="width:99%;height:auto; min-height:400px"
        toolbar="#${param['tabId']} #listLinkTableSearch"
        title="${title}" iconCls="fa fa-bell-o"
        rownumbers="true" pagination="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true" nowrap="false" fitColumns="true">
      <thead>
        <tr>
         <th width="30"  data-options="field:'id',checkbox:true"></th>
         <th width="100" data-options="field:'name'">链接名</th>
         <th width="100" data-options="field:'url'">链接路径</th>
         <th width="100" data-options="field:'type'">链接类型</th>
         <th width="100" data-options="field:'isOk'">是否启用</th>
         <th width="100" data-options="field:'orderNum'">排序号</th>
        </tr>
    </thead>
      <c:if test="${not empty resultList}">
        <tbody>
            <c:forEach var="list" items="${resultList }" varStatus="status">
            <tr>
                <td>${list.id}</td>
                <td>${list.name}</td>
                <td>${list.url}</td>
                <td>${dict:getEntryName('LINK_TYPE',list.type)}</td>
                <td>${list.isok eq '01'?'是':'否'}</td>
                <td>${list.orderNum}</td>
            </tr>
            </c:forEach>
    	</tbody>
       </c:if>
</table>
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
   <jsp:param name="pageForm" value="listLinkForm" />
   <jsp:param name="tableId" value="listLinkTable" />
   <jsp:param name="type" value="easyui" />
   <jsp:param name="divId" value="${param['tabId']}" />
</jsp:include>
<script type="text/javascript">
	function addLinkWindow(url,type){
		if(type=="edit"){
			var selectedId=getCurrentTab().find('#listLinkTable').datagrid('getSelections');
			if(selectedId.length>0){
				openWindow('linkWindow','新增链接',800,500,url+selectedId[0].id,true);	
			}else{
				$.messager.alert('提示','请选择需要修改的链接！');
				return false;
			}
		}else{
			openWindow('linkWindow','新增链接',800,500,url,true);	
		}
	}
	
	function delLinkWindow(url){
		var selectedId=getCurrentTab().find('#listLinkTable').datagrid('getSelections');
		if(selectedId.length>0){
			easyuiUtils.confirmPostUrl('确认删除吗？',url+selectedId[0].id);
		}else{
			$.messager.alert('提示','请选择要删除的链接！');
		}
	}
</script>
</form>