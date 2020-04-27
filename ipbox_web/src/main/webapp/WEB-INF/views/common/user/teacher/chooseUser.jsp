<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="formId" value="choose_user_list_form"/>
<c:set var="searchId" value="choose_user_search"/>
<c:set var="tableId" value="choose_user_list"/>
<c:set var="isAdminCompetent" value="${ipanthercommon:isAdminCompetent(sessionScope.loginUser.deptType)}"/>
<form id="${formId}" action="${ctx }/common/user/teacher/chooseUser.do" method="post">
<div id="${searchId}" style="margin: 5px">
	<div style="margin: 3px">
		姓名:
		<input class="easyui-textbox" type="text" name="paramMap[realName]" value="${searchParam.paramMap.realName}" />
		<c:if test="${isAdminCompetent}">
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
		</c:if>
		单位:
		<input class="easyui-textbox" type="text" name="paramMap[deptName]" value="${searchParam.paramMap.deptName}" />
		
		<button type="button" class="easyui-linkbutton main-btn" onclick="javascript:easyuiQuery('${formId}','showChooseDialog');"><i class="fa fa-search"></i> 查询</button>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('showChooseDialog')" ><i class="fa fa-eraser"></i> 重置</a>
	</div>
	<div style="margin: 3px">
		<button type="button" id="chooseUserButton" class="easyui-linkbutton main-btn" ><i class="fa fa-plus"></i>选择</button>
	</div>	
</div>
 <table id="${tableId}" title=""	toolbar="#showChooseDialog #${searchId}" pagination="true"
 	rownumbers="true" fitColumns="true"  singleSelect="true" checkOnSelect="true" selectOnCheck="true">
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
	<jsp:param name="divId" value="showChooseDialog" />
</jsp:include>
 </form>

<script type="text/javascript">
$(document).ready(function(e) {
	//选择用户
	$(document).find('#${searchId}').find('#chooseUserButton').linkbutton({onClick: function(){
		var selectedData = easyuiUtils.getSelectedData('${tableId}',false,'showChooseDialog');
		if(null!=selectedData) {
			$.messager.confirm('提示', '确定选择？', function(r){
				if(r) {
					var id = selectedData.id;
					var data=$.ajaxSubmitValue('${ctx }/common/user/teacher/chooseUserDo.do?id='+id);
					var json=jQuery.parseJSON(data);
					var responseCode = json.responseCode;
					if(parseInt(responseCode)==0){
						$.messager.alert('提示','操作成功，点击确定继续完善教师信息','info',function() {
							jQuery('#viewTeacherAllBaseTopTab').panel('refresh');//刷新父列表页面
							//跳转到教师编辑页面
							easyuiUtils.openWindow('showAddDialog','修改师资信息',900,630,'${ctx}/common/user/teacher/editTabs.do?parentTabId=viewTeacherAllBaseTopTab&id='+id,true);
							closeWindow('showChooseDialog');//关闭当前页面
							
						});
					} else {
						$.messager.alert('提示','操作失败','error');
					}
				}
			});
		}
	}});
});

</script>