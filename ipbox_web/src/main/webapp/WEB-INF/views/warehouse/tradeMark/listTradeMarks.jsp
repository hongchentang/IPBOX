<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp" %>
<div class="easyui-panel" style="width:100%">
<form id="listTMsForm" action="${ctx}/warehouse/tms/listTMS.do?tabId=${param.tabId}" method="post">
<div id="listTMsTableSearch" style="margin-left: 5px;margin-top: 10px;">
	<div>
        <span>注册号:</span>
        <input type="text" style="width:200px" class="easyui-textbox" name="paramMap[registeNumber]" value="${searchParam.paramMap.registeNumber}" />
    <a href="javascript:void(0)" class="easyui-linkbutton main-btn" onclick="easyuiUtils.ajaxPostFormForPanel('listTMsForm','${param['tabId']}')"><i class="fa fa-search"></i> 查询</a>
    </div>
    <br/>
    <div style="margin-bottom: 5px;">
       <a id="addButtonByOne" href="javascript:void(0)" class="easyui-linkbutton" plain="false" > 新增</a>
       <a id="editButton" href="javascript:void(0)" class="easyui-linkbutton" plain="false" > 查看</a>
       <%--<a id="delButton" href="javascript:void(0)" class="easyui-linkbutton delete-btn" plain="false" >删除</a>--%>
    </div>
</div>
<table id="listTMsTable" style="width:99%;height:auto; min-height:400px"
        toolbar="#${param['tabId']} listTMsTableSearch"
        title="" iconCls="fa fa-bell-o"
        rownumbers="true" pagination="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true" nowrap="false" fitColumns="true">
      <thead>
        <tr>
         <th width="30"  data-options="field:'id',checkbox:true"></th>
         <th width="100" data-options="field:'name'">商标</th>
         <th width="40"  data-options="field:'registeNumber'">注册号</th>
         <th width="40"  data-options="field:'registeType'">类型</th>
         <th width="40"  data-options="field:'classfiy'">类别</th>
         <th width="40"  data-options="field:'registePublicNumber'">公告期号</th>
         <th width="40"  data-options="field:'registePublicDate'">公告日期</th>
         <th width="40"  data-options="field:'image'">商标图样</th>
         <th width="40"  data-options="field:'registeAdress'">注册地址</th>
         <th width="40"  data-options="field:'provice'">注册地区</th>
        </tr>
    </thead>
    <c:if test="${not empty resultList}">
        <tbody>
          <c:forEach var="list" items="${resultList}" varStatus="status">
            <tr>
                <td>${list.id}</td>
                <td>${list.name}</td>
                <td>${list.registeNumber}</td>
                <td>${list.registeType}</td>
                <td>${list.classfiy}</td>
                <td>${list.registePublicNumber}</td>
                <td><fmt:formatDate value="${list.registePublicDate}" pattern="yyyy-MM-dd"/></td>
                <td>              	
					<c:if test="${not empty list.image}"> 
	      				<c:set value="${ipanthercore:getJSONMap(list.image)}" var="map" />
		      				<div id="fileSpanAttachment">
			  					<span id="attachmentName"><a href="${ctx}${map.downloadUrl}" target="download">${map.attachmentName}(点击下载)</a></span> 
			  				</div>
	    			</c:if>					    		
                </td>
                <td>${list.registeAdress}</td>
                <td>${list.provice}</td>
            </tr>
            </c:forEach>
    	</tbody>
       </c:if>
</table>
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
   <jsp:param name="pageForm" value="listTMsForm" />
   <jsp:param name="tableId" value="listTMsTable" />
   <jsp:param name="type" value="easyui" />
   <jsp:param name="divId" value="${param['tabId']}" />
</jsp:include>
<script type="text/javascript">

getCurrentTab().find('#editButton').linkbutton({onClick: function(){
		var data=$("#${param['tabId']}").find('#listTMsTable').datagrid('getSelections');
		if(data.length<1){
			$.messager.alert('提示','请选择一条数据！');
			return false;
		}
		openWindow('tmsMsgWindow','详细信息',800,500,"${ctx}/warehouse/tms/addTradeMarkHouse.do?id="+data[0].id,true);
		}
});

getCurrentTab().find('#delButton').linkbutton({onClick: function(){
	var selectedId=getCurrentTab().find('#listTMsTable').datagrid('getSelections');
	if(selectedId.length>0){
		easyuiUtils.confirmPostUrl("是否确认删除?","${ctx}/warehouse/tms/delTrademark.do?id="+selectedId[0].id);
	}else{
		$.messager.alert('提示','请选择要删除的信息！');
	}
	}
});

   
</script>
</form>
</div>
