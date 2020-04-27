<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="tabId" value="viewUserHisTopTab"/>
<c:set var="formId" value="student_his_list_form"/>
<c:set var="searchId" value="student_his_search"/>
<c:set var="tableId" value="student_his_list"/>

<form id="${formId}" action="${ctx }/common/user/student/listHis.do?paramMap[userId]=${searchParam.paramMap.userId}" method="post">
<div id="${searchId}" style="margin: 5px">
	<div style="margin: 3px">
		修改时间:
		<input type="text" class="easyui-validatebox Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="paramMap[updateTimeMin]" value="${searchParam.paramMap.updateTimeMin }"/>
		-
		<input type="text" class="easyui-validatebox Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="paramMap[updateTimeMax]" value="${searchParam.paramMap.updateTimeMax }"/>
		<button type="button" class="easyui-linkbutton main-btn" onclick="javascript:easyuiQuery('${formId}','${tabId}');"><i class="fa fa-search"></i> 查询</button>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('viewUserHisTopTab')" ><i class="fa fa-eraser"></i> 重置</a>
	</div>
	<div style="margin: 3px">
		<button type="button" id="viewButton" class="easyui-linkbutton" ><i class="fa fa-search"></i>查看</button>
	</div>	
</div>
 <table id="${tableId}" title="修改历史列表"	toolbar="#${tabId} #${searchId}" pagination="true"
 	rownumbers="true" fitColumns="true"  singleSelect="true" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
                	<th data-options="field:'id',checkbox:true"></th>
					<th field="realName" width="25">姓名</th>
					<th field="mobilePhone" width="25">联系电话</th>
					<th field="email" width="25">邮箱</th>
					<th field="updateTime" width="25">修改时间</th>
					<th field="updateby" width="25">修改人</th>
				</tr>
			</thead>
			<tbody>   
       	<c:forEach items="${users }" var="user">
        <tr>   
           <td>${user.id }</td>
           <td>${user.realName }</td>
           <td>${user.mobilePhone }</td> 
           <td>${user.email }</td>
           <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${user.updateTime}"/></td>
           <td>${ipanthercommon:getUserRealName(user.updatedby)}</td>
        </tr>   
       </c:forEach>
    </tbody>   
</table> 
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
	<jsp:param name="pageForm" value="${formId}" />
    <jsp:param name="tableId" value="${tableId}"/>
	<jsp:param name="type" value="easyui" />
	<jsp:param name="divId" value="${tabId}" />
</jsp:include>
 </form>

<script type="text/javascript">
$(document).ready(function(e) {
	//查看
	$('#viewUserHisTopTab').find('#student_his_search').find('#viewButton').linkbutton({onClick: function(){
		var selectedData = easyuiUtils.getSelectedData('${tableId}',false,'viewUserHisTopTab');
		if(null!=selectedData) {
			var id = selectedData.id;
			easyuiUtils.openWindow('showHisDialog','查看人才历史信息',900,630,'${ctx}/common/user//student/view.do?hisId='+id,true);
		}
	}});
});

</script>