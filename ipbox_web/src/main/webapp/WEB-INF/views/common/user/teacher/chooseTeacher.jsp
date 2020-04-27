<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="tabId" value="chooseTeacherWindow"/>
<c:set var="formId" value="choose_teacher_list_form"/>
<c:set var="searchId" value="choose_teacher_search"/>
<c:set var="tableId" value="choose_teacher_list"/>
<c:set var="isAdminCompetent" value="${ipanthercommon:isAdminCompetent(sessionScope.loginUser.deptType)}"/>
<form id="${formId}" action="${ctx }/common/user/teacher/chooseTeacher.do?paramMap[notIds]=${searchParam.paramMap.notIds}" method="post">
<div id="${searchId}" style="margin: 5px">
	<div style="margin: 3px">
		姓名:
		<input class="easyui-textbox" type="text" name="paramMap[realName]" value="${searchParam.paramMap.realName}" />
		区域:
		<select id="regionsCode" name="paramMap[regionsCode]" style="width:100px"></select>
		<script type="text/javascript">
			$(document).find('#${searchId}').find('#regionsCode').combotree({
			    data: jQuery.parseJSON('${ipanthercommon:getRegionsJson(true)}'),    
			    valueField:'id',    
			    textField:'text',
			    parentField:'pid',
			    panelWidth:200,
			    onLoadSuccess:function(){
			    	if('${searchParam.paramMap.regionsCode}'!=''){
			    		$(document).find('#${searchId}').find('#regionsCode').combotree('setValue','${searchParam.paramMap.regionsCode}');
					}
			    }
			}); 
		</script>
		单位:
		<input class="easyui-textbox" type="text" name="paramMap[deptName]" value="${searchParam.paramMap.deptName}" />
		
		<button type="button" class="easyui-linkbutton main-btn" onclick="javascript:easyuiQuery('${formId}','${tabId}');"><i class="fa fa-search"></i> 查询</button>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('${tabId}')" ><i class="fa fa-eraser"></i> 重置</a>
	</div>
	<div style="margin: 3px">
		<button type="button" onclick="confirmTeacher()" class="easyui-linkbutton main-btn" ><i class="fa fa-plus"></i>选择</button>
	</div>	
</div>
 <table id="${tableId}" title=""	toolbar="#${tabId} #${searchId}" pagination="true"
 	rownumbers="true" fitColumns="true"  singleSelect="false" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
                	<th data-options="field:'id',checkbox:true"></th>
					<th field="userName" width="10">用户名</th>
					<th field="realName" width="10">姓名</th>
					<th field="regionsName" width="15">区域</th>
					<th field="deptName" width="15">单位</th>
					<th field="researchField" width="15">研究领域</th>
					<th field="mobilePhone" width="10">联系电话</th>
					<th field="email" width="10">邮箱</th>
					<th field="teacherStatus" data-options="hidden:true">teacherStatus</th>
					<th field="regionsCode" data-options="hidden:true">区域编码</th>
					<th field="deptId" data-options="hidden:true">deptId</th>
				</tr>
			</thead>
			<tbody>   
       	<c:forEach items="${users }" var="user">
        <tr>   
           <td>${user.id }</td>
           <td>${user.userName }</td>
           <td>${user.realName }</td>
           <td>${ipanthercommon:getRegionsNameFull(user.regionsCode)}</td>
           <td>${user.deptName }</td>
           <td>${user.researchField }</td>
           <td>${user.mobilePhone }</td> 
           <td>${user.email }</td> 
           <td>${user.teacherStatus }</td>
           <td>${user.regionsCode }</td>
           <td>${user.deptId }</td>
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
	
});

//确认选择，回调
function confirmTeacher() {
	var selectedData = easyuiUtils.getSelectedData('${tableId}',true,'${tabId}');
	if(null!=selectedData) {
		var result = "";
		for(var i =0;i<selectedData.length;i++) {
			var id = selectedData[i].id;
			var realName = selectedData[i].realName;
			result += "★"+id+"☆"+realName;
		}
		if(""!=result) {
			addTeacher(result.substr(1));
		}
		closeWindow('${tabId}');
	}
}

</script>