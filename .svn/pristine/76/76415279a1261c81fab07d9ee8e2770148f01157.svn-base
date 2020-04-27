<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp" %>
<c:if test="${searchParam.paramMap.notice_manage == 0}">
	<c:set var="action" value="listNotice"/>
	<c:set var="title" value="通知公告"/>
	<c:set var="divId" value="listNoticeContent"/>
</c:if>
<c:if test="${searchParam.paramMap.notice_manage == 1}">
	<c:set var="action" value="listNoticeManage"/>
	<c:set var="title" value="通知公告管理"/>
	<c:set var="divId" value="listNoticeManageContent"/>
</c:if>
<div id="${divId}" style="margin:5px;margin-bottom: 27px;">
<form id="listNoticeForm" action="${ctx}/common/notice/${action}.do?tabId=${param.tabId}" method="post">
<div id="listNoticeTableSearch">
	<div>
        <span>标题:</span>
        <input type="text" id="requestParam.noticeTitle" style="width:100px" name="paramMap[noticeTitle]" value="${searchParam.paramMap.noticeTitle}" />
        <span>发布时间:</span>
        <input id="requestParam_startTime" type="text" style="width:100px" class="Wdate" name="paramMap[startTime]" value="${searchParam.paramMap.startTime}"
        onFocus="var startTime=$dp.$('#requestParam_startTime');WdatePicker({onpicked:function(){requestParam_endTime.focus();},maxDate:'#F{$dp.$D(\'requestParam_startTime\')}'})" readonly/>
        至
        <input id="requestParam_endTime" type="text" style="width:100px" class="Wdate" name="paramMap[endTime]" value="${searchParam.paramMap.endTime}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'requestParam_startTime\')}'})" readonly/>
    <a href="#" class="easyui-linkbutton main-btn" onclick="easyuiUtils.ajaxPostFormForPanel(getCurrentTabComId('listNoticeForm'),getCurrentTabId())"><i class="fa fa-search"></i> 查询</a>
   <a href="#" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel(getCurrentTabId())"><i class="fa fa-eraser"></i> 重置</a>
    </div>
    <c:if test="${searchParam.paramMap.notice_manage == 1}">
        <div>
            <a href="#" class="easyui-linkbutton" plain="false" onclick="openListNoticeAddWindow();"><i class="fa fa-plus"></i> 新增</a>
            <a href="#" class="easyui-linkbutton" plain="false" onclick="openListNoticeEditWindow();"><i class="fa fa-pencil"></i> 修改</a>
            <a href="#" class="easyui-linkbutton delete-btn" plain="false" onclick="openListNoticeDelWindow();"><i class="fa fa-minus"></i> 删除</a>
        </div>
    </c:if>
</div>
<table id="listNoticeTable" style="width:98%;height:auto; min-height:400px"
        toolbar="#${param['tabId']} #listNoticeTableSearch"
        title="${title}" iconCls="fa fa-bell-o"
        rownumbers="true" pagination="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true" nowrap="false" fitColumns="true">
      <thead>
        <tr>
         <th width="30" data-options="field:'id',checkbox:true"></th>
         <th width="200" data-options="field:'noticeTitle'">标题</th>
         <%--<th width="50">类型</th>--%>
         <th width="80" data-options="field:'startTime'">发布时间</th>
         <th width="80" data-options="field:'endTime'">结束时间</th>
         <th width="50" data-options="field:'noticeStatus'">发布</th>
         <th width="50" data-options="field:'noticeTop'">置顶</th>
        </tr>
    </thead>
           <c:if test="${not empty resultList}">
        <tbody>
            <c:forEach var="list" items="${resultList }" varStatus="status">
            <tr>
                <td>${list.id}</td>
                <td>
<a href="#" onclick="openWindow('readNoticeWindow','通知公告',800,500,'${ctx}/common/notice/readNotice.do?notice.id=${list.id}',true);">
${list.noticeTitle}
</a>
                </td>
                <%--<td>${dict:getEntryName("NOTICE_TYPE",list.noticeType)}</td>  --%>
                <td>${list.startTime}</td>
                <td>${list.endTime}</td>
                <td>${dict:getEntryName("NOTICE_STATUS",list.noticeStatus)}</td>   
                <td>
                	<span style="color:${list.noticeTop eq '1'?'red':''}">
                	${dict:getEntryName("NOTICE_TOP",list.noticeTop)}
                	</span>
                </td>
            </tr>
            </c:forEach>
    </tbody>
        </c:if>
</table>
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
   <jsp:param name="pageForm" value="listNoticeForm" />
   <jsp:param name="tableId" value="listNoticeTable" />
   <jsp:param name="type" value="easyui" />
   <jsp:param name="divId" value="${param['tabId']}" />
</jsp:include>
<script type="text/javascript">
	function openListNoticeAddWindow(){
		var url='${ctx}/common/notice/goEditNotice.do';
		openWindow('listNoticeWindow','新增通知公告',800,500,url,true);
	}
	function openListNoticeEditWindow(){
		var selectedId=getCurrentTab().find('#listNoticeTable').datagrid('getSelected');
		if(selectedId!=null){
			var url='${ctx}/common/notice/goEditNotice.do?notice.id='+selectedId.id;
			openWindow('listNoticeWindow','修改通知公告',800,500,url,true);
		}
		else{
			$.messager.alert('提示','请选择要修改的公告！');
		}
	}
	function openListNoticeDelWindow(){
		var selectedId=getCurrentTab().find('#listNoticeTable').datagrid('getSelected');
		if(selectedId!=null){
			var url='${ctx}/common/notice/deleteNotice.do?notice.id='+selectedId.id;
			easyuiUtils.confirmPostUrl('确认删除吗？',url);
		}
		else{
			$.messager.alert('提示','请选择要删除的公告！');
		}
	}
	
</script>
</form>
</div>