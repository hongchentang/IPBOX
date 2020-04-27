<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<div id="selectSitePanel" class="easyui-panel" data-options="border:false"   >
<form id="selectSiteForm" action="${ctx}/cms/site/selectSite.do" method="post">
   <input name="paramMap[entityId]" type="hidden" value="${paramMap.entityId}"/>
   <input name="paramMap[entityName]" type="hidden" value="${paramMap.entityName}"/>
   <input name="paramMap[dialogId]" type="hidden" value="${paramMap.dialogId}"/>
   <input name="paramMap[contentId]" type="hidden" value="${paramMap.contentId}"/>
   <input name="paramMap[type]" type="hidden" value="${paramMap.type}"/>
<div id="selectSiteSearch">
	<div style="margin: 10px">    
		站点名称
		<input class="easyui-textbox" type="text" name="paramMap[name]" value="${paramMap.name}"></input>
		<button type="button" class="easyui-linkbutton" onclick="easyuiQuery('selectSiteForm', 'selectSitePanel')"><i class="fa fa-search"></i> 查询</button>
	</div>
</div>
 <table id="selectSiteList"
 	rownumbers="true" fitColumns="true" style="height: 620px" pagination="true" toolbar="#selectSiteSearch" 
    singleSelect="true" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
                	<th width="30" data-options="field:'id',checkbox:true"></th>
					<th field="name" width="30">名称</th>
					<th field="parentName" width="30">上级站点</th>
					<th field="xuanze" width="30">选择</th>
					<!-- <th field="areaName" width="50">所属区域</th> -->
				</tr>
			</thead>
			<tbody>   
       	<c:forEach items="${resultList }" var="list">
        <tr>   
           <td>${list.id }</td>
           <td>${list.name }</td>
           <td>${list.parentName }</td>
           <td>
           	<a onclick="getValues('${list.id}','${list.name}')" href="javascript:void(0)" class="easyui-linkbutton">
           	<i class="fa fa-plus"></i> 选择</a>
           </td> 
        </tr>   
       </c:forEach>
    </tbody>   
</table> 
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
	<jsp:param name="pageForm" value="selectSiteForm" />
    <jsp:param name="tableId" value="selectSiteList"/>
	<jsp:param name="type" value="easyui" />
	<jsp:param name="divId" value="selectSitePanel" />
</jsp:include>
 </form>
</div>
<script type="text/javascript">
function getValues(returnId,returnValue){
	  if(("${paramMap.entityId}"!="")&&("${paramMap.entityName}"!="")){
          jQuery("#${paramMap.contentId}").find("#${paramMap.entityId}").val(returnId);
          if("${paramMap.type}"=="text"){
          	jQuery("#${paramMap.contentId}").find("#${paramMap.entityName}").text(returnValue);
	  	  }else if("${paramMap.type}"=="html"){
	  		jQuery("#${paramMap.contentId}").find("#${paramMap.entityName}").html(returnValue);
	  	  }else{
	  		jQuery("#${paramMap.contentId}").find("#${paramMap.entityName}").val(returnValue);
	  	  }
          closeWindow("${paramMap.dialogId}");
	  }
}
</script>