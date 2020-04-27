<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp" %>
<div class="easyui-panel" style="width:100%">
<form id="listPatsForm" action="${ctx}/knowledge/patentInfo/patentList.do?tabId=${param.tabId}" method="post">
<div id="listPatsTableSearch" style="margin-left: 5px;margin-top: 10px;">
	<div>
        <span>商品名称:</span>
        <input type="text" style="width:200px" class="easyui-textbox" name="paramMap[commodityName]" value="${searchParam.paramMap.commodityName}" />
        <span>商品品牌:</span>
        <input type="text" style="width:200px" class="easyui-textbox" name="paramMap[commodityNickname]" value="${searchParam.paramMap.commodityNickname}" />
    	<a href="javascript:void(0)" class="easyui-linkbutton main-btn" onclick="easyuiUtils.ajaxPostFormForPanel('listIndsForm','${param['tabId']}')"><i class="fa fa-search"></i> 查询</a>
    </div>
    <br/>
    <div style="margin-bottom: 5px;">
       <a id="addButton" href="javascript:void(0)" class="easyui-linkbutton" plain="false" > 导入</a>
       <a id="editButton" href="javascript:void(0)" class="easyui-linkbutton" plain="false" > 查看</a>
       <a id="delButton" href="javascript:void(0)" class="easyui-linkbutton delete-btn" plain="false" >删除</a>
    </div>
</div>
<table id="listPatsTable" style="width:99%;height:auto; min-height:400px"
        toolbar="#${param['tabId']} listPatsTableSearch"
        title="" iconCls="fa fa-bell-o"     rownumbers="true" pagination="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true" nowrap="false" fitColumns="true">
      <thead>
        <tr>
         <th width="30"  data-options="field:'id',checkbox:true"></th>
         <th width="100" data-options="field:'main_claim'">商品名称</th>
        </tr>
    </thead>
    <c:if test="${not empty patentList}">
        <tbody>
          <c:forEach var="list" items="${patentList}" varStatus="status">
            <tr>
                <td>${list.id}</td>
                <td>${list.main_claim}</td>
            </tr>
            </c:forEach>
    	</tbody>
       </c:if>
</table>
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
   <jsp:param name="pageForm" value="listPatsForm" />
   <jsp:param name="tableId" value="listPatsTable" />
   <jsp:param name="type" value="easyui" />
   <jsp:param name="divId" value="${param['tabId']}" />
</jsp:include>
<script type="text/javascript">
getCurrentTab().find('#addButton').linkbutton({onClick: function(){
		openWindow('indsMsgWindow','导入信息',800,500,"${ctx}/knowledge/inds/goimport.do",true);	
	}
});
getCurrentTab().find('#editButton').linkbutton({onClick: function(){
		var data=$("#${param['tabId']}").find('#listIndsTable').datagrid('getSelections');
		if(data.length<1){
			$.messager.alert('提示','请选择要修改的信息！');
			return false;
		}
		openWindow('indsMsgWindow','修改信息',800,500,"${ctx}/knowledge/inds/addInds.do?id="+data[0].id,true);	
		}
});

getCurrentTab().find('#delButton').linkbutton({onClick: function(){
	var selectedId=getCurrentTab().find('#listIndsTable').datagrid('getSelections');
	if(selectedId.length>0){
		easyuiUtils.confirmPostUrl("是否确认删除?","${ctx}/knowledge/inds/deleteInds.do?id="+selectedId[0].id);
	}else{
		$.messager.alert('提示','请选择要删除的信息！');
	}
	}
});
getCurrentTab().find('#setTopButton00').linkbutton({onClick: function(){
	var selectedId=getCurrentTab().find('#listInfoTable').datagrid('getSelections');
	if(selectedId.length>0){
		easyuiUtils.confirmPostUrl("是否确认取消置顶？","${ctx}/cms/info/setInfo.do?cmsInfo.isTop=00&cmsInfo.id="+selectedId[0].id);
	}else{
		$.messager.alert('提示','请选择要设置置顶的信息！');
	}
	}
});
getCurrentTab().find('#setTopButton01').linkbutton({onClick: function(){
	var selectedId=getCurrentTab().find('#listInfoTable').datagrid('getSelections');
	if(selectedId.length>0){
		easyuiUtils.confirmPostUrl("是否确认置顶？","${ctx}/cms/info/setInfo.do?cmsInfo.isTop=01&cmsInfo.id="+selectedId[0].id);
	}else{
		$.messager.alert('提示','请选择要设置置顶的信息！');
	}
	}
});
</script>
</form>
</div>
