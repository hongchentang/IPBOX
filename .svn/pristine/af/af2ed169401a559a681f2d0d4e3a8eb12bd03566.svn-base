<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp" %>
<div class="easyui-panel" style="width:100%">
<form id="listInfoForm" action="${ctx}/cms/info/listInfo.do?tabId=${param.tabId}" method="post">
<input type="hidden" name="paramMap[type]" value="${searchParam.paramMap.type}" />
<div id="listInfoTableSearch" style="margin-left: 5px;margin-top: 10px;">
	<div>
        <span>标题:</span>
        <input type="text" style="width:200px" class="easyui-textbox" name="paramMap[title]" value="${searchParam.paramMap.title}" />
    <a href="javascript:void(0)" class="easyui-linkbutton main-btn" onclick="easyuiUtils.ajaxPostFormForPanel('listInfoForm','${param['tabId']}')"><i class="fa fa-search"></i> 查询</a>
    </div>
    <br/>
    <div style="margin-bottom: 5px;">
       <a id="addButton" href="javascript:void(0)" class="easyui-linkbutton" plain="false" > 新增</a>
       <a id="editButton" href="javascript:void(0)" class="easyui-linkbutton" plain="false" > 修改</a>
       <a id="delButton" href="javascript:void(0)" class="easyui-linkbutton delete-btn" plain="false" >删除</a>
       <a id="publishButton" href="javascript:void(0)" class="easyui-linkbutton" plain="false" > 发布</a> 
       <a id="setTopButton01" href="javascript:void(0)" class="easyui-linkbutton" plain="false" > 置顶</a>
        <a  id="setTopButton00"href="javascript:void(0)" class="easyui-linkbutton" plain="false">取消置顶</a>
    </div>
</div>
<table id="listInfoTable" style="width:99%;height:auto; min-height:400px"
        toolbar="#${param['tabId']} #listInfoTableSearch"
        title="" iconCls="fa fa-bell-o"
        rownumbers="true" pagination="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true" nowrap="false" fitColumns="true">
      <thead>
        <tr>
         <th width="30"  data-options="field:'id',checkbox:true"></th>
         <th width="100" data-options="field:'title'">标题</th>
         <th width="40"  data-options="field:'status'">是否发布</th>
         <th width="40"  data-options="field:'isTop'">是否置顶</th>
         <th width="80"  data-options="field:'addTime'">创建时间</th>
        </tr>
    </thead>
      <c:if test="${not empty resultList}">
        <tbody>
          <c:forEach var="list" items="${resultList }" varStatus="status">
            <tr>
                <td>${list.id}</td>
                <td>${list.title}</td>
                <td>${dict:getEntryName("CMS_INFO_STATE  ",list.state)}</td>
                <td> ${dict:getEntryName("CMS_INFO_IS_TOP ",list.isTop)} </td>
                <td>
                <fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
            </tr>
            </c:forEach>
    	</tbody>
       </c:if>
</table>
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
   <jsp:param name="pageForm" value="listInfoForm" />
   <jsp:param name="tableId" value="listInfoTable" />
   <jsp:param name="type" value="easyui" />
   <jsp:param name="divId" value="${param['tabId']}" />
</jsp:include>
<script type="text/javascript">
getCurrentTab().find('#addButton').linkbutton({onClick: function(){
		openWindow('infoMsgWindow','新增信息',800,500,"${ctx}/cms/info/addInfo.do?paramMap[type]=${searchParam.paramMap.type}",true);	
	}
});
getCurrentTab().find('#editButton').linkbutton({onClick: function(){
		var data=$("#${param['tabId']}").find('#listInfoTable').datagrid('getSelections');
		if(data.length<1){
			$.messager.alert('提示','请选择要修改的信息！');
			return false;
		}
		openWindow('infoMsgWindow','修改信息',800,500,"${ctx}/cms/info/addInfo.do?paramMap[type]=${searchParam.paramMap.type}&cmsInfo.id="+data[0].id,true);	
		}
});

getCurrentTab().find('#delButton').linkbutton({onClick: function(){
	var selectedId=getCurrentTab().find('#listInfoTable').datagrid('getSelections');
	if(selectedId.length>0){
		easyuiUtils.confirmPostUrl("是否确认删除?","${ctx}/cms/info/setInfo.do?cmsInfo.isDeleted=Y&cmsInfo.id="+selectedId[0].id);
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
getCurrentTab().find('#publishButton').linkbutton({onClick: function(){
	var selectedId=getCurrentTab().find('#listInfoTable').datagrid('getSelections');
	if(selectedId.length>0){
		if(selectedId[0].status=="否"){
			easyuiUtils.confirmPostUrl("是否确认发布？","${ctx}/cms/info/setInfo.do?cmsInfo.state=01&cmsInfo.id="+selectedId[0].id);
		}else{
			$.messager.alert('提示','选择的信息已经发布！');
		}
	}else{
		$.messager.alert('提示','请选择要发布的信息！');
	}
	}
});
	
	/* function delInfoWindow(url){
		var selectedId=getCurrentTab().find('#listInfoTable').datagrid('getSelections');
		if(selectedId.length>0){
			easyuiUtils.confirmPostUrl('确认删除吗？',url+selectedId[0].id);
		}else{
			$.messager.alert('提示','请选择要删除的信息！');
		}
	}
	
	function recycleInfoWindow(url){
		var selectedId=getCurrentTab().find('#listInfoTable').datagrid('getSelections');
		if(selectedId.length>0){
			easyuiUtils.confirmPostUrl('确认放入回收站吗？',url+selectedId[0].id);
		}else{
			$.messager.alert('提示','请选择需要放入回收站的信息！');
		}
	}
 */
   /* function updateInfoWindow(){
	   var selectedId=getCurrentTab().find('#listInfoTable').datagrid('getSelections');
		if(selectedId.length>0){
			var channelId=getCurrentTab().find('#listInfoTable').find("#"+selectedId[0].id).val(); 
			//easyuiUtils.confirmPostUrl('确认删除吗？',url+selectedId[0].id);
				 var url="${ctx}/cms/info/changeChannel.do?paramMap[cmsInfoId]="+selectedId[0].id+"&paramMap[cmsChannelId]="+channelId+"&paramMap[InfoName]="+selectedId[0].title+"&paramMap[dialogId]=changeChannel&paramMap[contentId]="+getCurrentTabId()+"&cmsInfo.id="+selectedId[0].id;
			openWindow('changeChannel','更改信息所属栏目',800,500,url,true)
		}else{
			$.messager.alert('提示','请选择要变更的信息！');
		}
   } */
   
</script>
</form>
</div>
