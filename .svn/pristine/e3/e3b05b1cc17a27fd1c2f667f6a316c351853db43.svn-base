<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
 <%@include file="/jsp/common/include/taglib.jsp" %>
 <form id="loginLogForm" action="${ctx}/common/log/loginLog.do?tabId=${param.tabId}" method="post">
<input type="hidden" name="paramMap[siteId]" value="${searchParam.paramMap.siteId}" />
<div id="loginLogTableSearch" style="margin-left: 5px;margin-top: 10px;">
	<div>
        <span>用户名:</span>
        <input type="text" style="width:200px" class="easyui-textbox" name="paramMap[realName]" value="${searchParam.paramMap.realName}" />

	    <a href="javascript:void(0)" class="easyui-linkbutton main-btn" onclick="easyuiUtils.ajaxPostFormForPanel(getCurrentTabComId('listLogForm'),'${param['tabId']}')"><i class="fa fa-search"></i> 查询</a>
	   	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('${param['tabId']}')"><i class="fa fa-eraser"></i> 重置</a>
    </div>
    <br/>
    <div style="margin-bottom: 5px;">
    </div>
</div>
<table id="loginLogTable" style="width:99%;height:auto; min-height:400px"
        toolbar="#${param['tabId']} #loginLogTableSearch"
        title="${title}" iconCls="fa fa-bell-o"
        rownumbers="true" pagination="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true" nowrap="false" fitColumns="true">
      <thead>
        <tr>
         <th width="30"  data-options="field:'id',checkbox:true"></th>   
         <th width="100" data-options="field:'accountId'">用户名</th>
		 <th width="100" data-options="field:'ipAddr'">客户端IP</th>		 
		 <th width="100" data-options="field:'sessionId'">sessionId</th>
		 <th width="100" data-options="field:'loginTime'">登录时间</th>
		 <th width="100" data-options="field:'endMachine'">登出时间备</th>		 
		 <th width="100" data-options="field:'creator'">终端设备</th>

        </tr>
    </thead>
      <c:if test="${not empty resultList}">
        <tbody>
            <c:forEach var="list" items="${resultList }" varStatus="status">
            <tr>
                <td>${list.id}</td>
                <td>${ipanthercommon:getUserRealName(list.accountId)}</td> 
                <td>${list.ipAddr}</td> 
                <td>${list.sessionId}</td>
                <td><fmt:formatDate value="${list.loginTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td> 
                <td><fmt:formatDate value="${list.logoutTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></td>
    			<td>${list.userAgent}</td>
            </tr>
            </c:forEach>
    	</tbody>
       </c:if>
</table>
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
   <jsp:param name="pageForm" value="loginLogForm" />
   <jsp:param name="tableId" value="loginLogTable" />
   <jsp:param name="type" value="easyui" />
   <jsp:param name="divId" value="${param['tabId']}" />
</jsp:include>
<script type="text/javascript">
	function addLinkWindow(url,type){
		if(type=="edit"){
			var selectedId=getCurrentTab().find('#listLogTable').datagrid('getSelections');
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
		var selectedId=getCurrentTab().find('#listLogTable').datagrid('getSelections');
		if(selectedId.length>0){
			easyuiUtils.confirmPostUrl('确认删除吗？',url+selectedId[0].id);
		}else{
			$.messager.alert('提示','请选择要删除的链接！');
		}
	}
</script>
</form>