<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="suffix" value="${param.tabId}"/><!-- 用于jsp被多次同时用到时区分各个DIV 的ID和函数名称 -->
<c:set var="formId" value="teacher_list_form${suffix}"/>
<c:set var="searchId" value="teacher_search${suffix}"/>
<c:set var="tableId" value="teacher_list${suffix}"/>

<c:set var="currentDeptId" value="${sessionScope.loginUser.deptId}"/>
<c:set var="isAdminCompetent" value="${ipanthercommon:isAdminCompetent(sessionScope.loginUser.deptType)}"/>
<form id="${formId}" action="${ctx }/common/user/teacher/${request}.do?tabId=${param.tabId }" method="post">
<input type="hidden" id="action" name="paramMap[action]" value=""/><!-- 区别查询还是导出，导出为：expoert -->
<input type="hidden" id="userIds" name="paramMap[userIds]" value=""/>
<div id="${searchId}" style="margin: 5px">
	<div style="margin: 3px;">
		姓名:
		<input class="easyui-textbox" type="text" name="paramMap[realName]" style="width:100px"  value="${searchParam.paramMap.realName}" />
		
		<c:if test="${isAdminCompetent}"><%--主管单位才放出来 --%>
			区域:
			<select id="regionsCode" name="paramMap[regionsCodes]" style="width:100px"></select>
			<script type="text/javascript">
				$(function(){
					getCurrentTab().find('#${searchId}').find('#regionsCode').combotree({    
					    data: jQuery.parseJSON('${ipanthercommon:getRegionsJson(true)}'),    
					    valueField:'id',    
					    textField:'text',
					    parentField:'pid',
					    panelWidth:200,
					    multiple:true,
					    cascadeCheck:false,
					    onLoadSuccess:function(){
					    	if('${searchParam.paramMap.regionsCodes}'!=''){
					    		var value='${searchParam.paramMap.regionsCodes}';
					    		value = value.replace(/\"/g,'').replace(/\[/g,'').replace(/\]/g,'');
					    		getCurrentTab().find('#${searchId}').find('#regionsCode').combotree('setValues',value);
							}
					    }
					}); 
				});
			</script>
		</c:if>
		单位:
		<input class="easyui-textbox" type="text" name="paramMap[deptName]" style="width:100px;" value="${searchParam.paramMap.deptName}" />
		专家类别:
		<select name="paramMap[expertType]" class="easyui-combobox">
		<option value="">-请选择-</option>
		${dict:getEntryOptionsSelected('EXPERT_TYPE',searchParam.paramMap.expertType) }
		</select>
		年龄:
		<input type="text" class="easyui-numberbox" name="paramMap[ageMin]" value="${searchParam.paramMap.ageMin}" style="width:30px"/>
		-
		<input type="text" class="easyui-numberbox" name="paramMap[ageMax]" value="${searchParam.paramMap.ageMax}" style="width:30px"/>
		职称:
		<input class="easyui-textbox" type="text" name="paramMap[title]" value="${searchParam.paramMap.title}" style="width:100px;"/>
		研究领域:<input type="text" class="ipt" name="paramMap[researchField]" value="${searchParam.paramMap.researchField}" style="width:100px;">
			<button type="button" class="easyui-linkbutton main-btn" onclick="javascript:easyuiQuery('${formId}','${param.tabId}');"><i class="fa fa-search"></i> 查询</button>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('${param.tabId}')" ><i class="fa fa-eraser"></i> 重置</a>
	</div>
	<div style="margin: 3px">
		<button type="button" id="viewButton" class="easyui-linkbutton" ><i class="fa fa-search"></i>查看</button>
		<c:choose>
			<c:when test="${type eq 'Edit' }">
				<button type="button" id="editButton" class="easyui-linkbutton" ><i class="fa fa-pencil"></i> 修改</button>
				<button type="button" id="registerButton" class="easyui-linkbutton" ><i class="fa fa-pencil"></i>注册</button>
			</c:when>
			<c:otherwise>
				<button type="button" id="addButton" class="easyui-linkbutton" ><i class="fa fa-plus"></i> 新增</button>
				<button type="button" id="chooseButton" class="easyui-linkbutton" ><i class="fa fa-plus"></i> 选择新增</button>
			  	<button type="button" id="editButton" class="easyui-linkbutton" ><i class="fa fa-pencil"></i> 修改</button>
				<button type="button" id="delButton" class="easyui-linkbutton delete-btn"><i class="fa fa-minus"></i> 注销</button>
				<button type="button" id="exportButton" class="easyui-linkbutton"><i class="fa "></i>导出结果</button>
				<button type="button" id="importButton" class="easyui-linkbutton" ><i class="fa "></i>导入</button>
				<button type="button" id="printButton" class="easyui-linkbutton" ><i class="fa fa-print"></i>打印</button>
			</c:otherwise>
		</c:choose>
		
	</div>	
</div>
 <table id="${tableId}" title=""	toolbar="#${param.tabId } #${searchId}" pagination="true"
 	rownumbers="true" fitColumns="true"  singleSelect="false" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
                	<th data-options="field:'id',checkbox:true"></th>
					<th field="userName" width="10">用户名</th>
					<th field="realName" width="10">姓名</th>
					<th field="regionsName" width="15">区域</th>
					<th field="deptName" width="15">单位</th>
					<th field="job" width="15">职务</th>
					<th field="title" width="15">职称</th>
					<th field="expertType" width="10">专家类别</th>
					<th field="researchField" width="15">研究领域</th>
					<th field="mobilePhone" width="10">联系电话</th>
					<th field="email" width="10">邮箱</th>
					<th field="teacherStatusName" width="5">状态</th>
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
           <td>${user.job }</td>
           <td>${user.title }</td>
           <td>${dict:getEntryNameByJson('EXPERT_TYPE',user.expertType)}</td>
<%--            <td>${user.expertType }</td> --%>
           <td>${user.researchField }</td>
           <td>${user.mobilePhone }</td> 
           <td>${user.email }</td> 
           <td>${dict:getEntryName('TEACHER_STATUS',user.teacherStatus) }</td> 
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
	<jsp:param name="divId" value="${param['tabId']}" />
</jsp:include>
 </form>
<form id="teacherPrintForm" action="${ctx}/common/user/teacher/print.do" target="_blank" method="post">
	<input id="printTeacherUserIds" type="hidden" name="paramMap[userIds]">
</form >
<script type="text/javascript">
$(document).ready(function(e) {
	//新增
	getCurrentTab().find("#${param.tabId}").find('#addButton').linkbutton({onClick: function(){
		easyuiUtils.openWindow('showAddDialog','新增师资',900,630,'${ctx}/common/user/teacher/editTabs.do?parentTabId=${param.tabId}',true);
	}});
	//选择
	getCurrentTab().find("#${param.tabId}").find('#chooseButton').linkbutton({onClick: function(){
		easyuiUtils.openWindow('showChooseDialog','选择用户成为师资',900,530,'${ctx}/common/user/teacher/chooseUser.do',true);
	}});
	//修改
	getCurrentTab().find("#${param.tabId}").find('#editButton').linkbutton({onClick: function(){
		//var selectedData = easyuiUtils.getSelectedData('${tableId}');
		 var selectedRow=getCurrentTab().find('#${tableId}').datagrid('getSelections');
		 if(selectedRow.length>1){
			 $.messager.alert('消息','只能选择一条记录进行操作','warning');
			 return false;
		 }else if(selectedRow.length==0){
			 $.messager.alert('消息','请至少选择一条记录进行操作','warning');
			 return false;
		 }
		 var selectedData=selectedRow[0];
		if(null!=selectedData) {
			var studentStatus = selectedData.studentStatus;
			var teacherStatus = selectedData.teacherStatus;
			if(studentStatus=='02') {
				$.messager.alert("提示","该用户正在进行学员注册审核，不能修改","error");
				return false;
			}
			if(teacherStatus=='02') {
				$.messager.alert("提示","该用户正在进行教师注册审核，不能修改","error");
				return false;
			}
			if('${isAdminCompetent}'=='false'&&'${currentDeptId}' != selectedData.deptId) {
				$.messager.alert("提示","不能修改本单位外的用户","error");
				return false;
			}
			var id = selectedData.id;
			easyuiUtils.openWindow('showAddDialog','修改师资信息',900,630,'${ctx}/common/user/teacher/editTabs.do?parentTabId=${param.tabId}&id='+id,true);		
		}
	}});
	//查看
	getCurrentTab().find("#${param.tabId}").find('#viewButton').linkbutton({onClick: function(){
		//var selectedData = easyuiUtils.getSelectedData('${tableId}');
		 var selectedRow=getCurrentTab().find('#${tableId}').datagrid('getSelections');
		 if(selectedRow.length>1){
			 $.messager.alert('消息','只能选择一条记录进行操作','warning');
			 return false;
		 }else if(selectedRow.length==0){
			 $.messager.alert('消息','请至少选择一条记录进行操作','warning');
			 return false;
		 }
		 var selectedData=selectedRow[0];
		var id = selectedData.id;
		easyuiUtils.openWindow('showAddDialog','查看师资信息',900,630,'${ctx}/common/user/teacher/viewTabs.do?id='+id,true);
		
	}});
	//注销
	getCurrentTab().find("#${param.tabId}").find('#delButton').linkbutton({onClick: function(){
		//var selectedData = easyuiUtils.getSelectedData('${tableId}');
		 var selectedRow=getCurrentTab().find('#${tableId}').datagrid('getSelections');
		 if(selectedRow.length>1){
			 $.messager.alert('消息','只能选择一条记录进行操作','warning');
			 return false;
		 }else if(selectedRow.length==0){
			 $.messager.alert('消息','请至少选择一条记录进行操作','warning');
			 return false;
		 }
		 var selectedData=selectedRow[0];
		if('${isAdminCompetent}'=='false'&&'${currentDeptId}' != selectedData.deptId) {
			$.messager.alert("提示","不能注销本单位外的用户","error");
			return false;
		}
		$.messager.confirm('提示', '是否确定注销‘'+selectedData.userName+'’用户?', function(r){
			if(r) {
				var data=$.ajaxSubmitValue('${ctx }/common/user/delete.do?user.id='+selectedData.id);
				var json=jQuery.parseJSON(data);
				var responseCode = json.responseCode;
				if(parseInt(responseCode)==0){
					jQuery.messager.alert("提示信息","注销成功","info");
					jQuery('#'+getCurrentTabId()).panel('refresh');
				} else {
					jQuery.messager.alert("提示信息","注销失败","error");
				}
			}
		});
	}});
	//导出
	getCurrentTab().find("#${param.tabId}").find('#exportButton').linkbutton({onClick: function(){
		var userId="";
		var selectedData=getCurrentTab().find('#${tableId}').datagrid('getSelections');
		if(selectedData.length==0){
			 $.messager.alert('消息','请选择导出记录','warning');
			 return false;
		 }
		for(var i=0;i<selectedData.length;i++){
			userId+="\'"+selectedData[i].id+"\'"+','
		}
		jQuery('#${formId}').find("#userIds").val(userId.substr(0,userId.length-1));
		$.messager.show({
			title:'提示',
			msg:'系统正在导出，请稍后',
			timeout:2000,
			showType:'slide'
		});
		jQuery('#${formId}').find(":hidden[id='action']").val("export");
		jQuery('#${formId}').form('submit',{
		    success: function(data){}
		}); 
		jQuery('#${formId}').find("#userIds").val('');
		jQuery('#${formId}').find(":hidden[id='action']").val("");
	}});
	//导入
	getCurrentTab().find("#${param.tabId}").find('#importButton').linkbutton({onClick: function(){
		easyuiUtils.openWindow('showAddDialog','批量导入师资',500,170,'${ctx}/common/user/teacher/goImport.do',true);
	}});
	//打印
	getCurrentTab().find("#${param.tabId}").find('#printButton').linkbutton({onClick: function(){
		/* var selectedData = easyuiUtils.getSelectedData('${tableId}');
		if(null!=selectedData) {
			var id = selectedData.id;
			window.open('${ctx}/common/user/teacher/print.do?id='+id);
		} */
		var userId="";
		var selectedData=getCurrentTab().find('#${tableId}').datagrid('getSelections');
		if(selectedData.length==0){
			 $.messager.alert('消息','请选择导出记录','warning');
			 return false;
		 }
		for(var i=0;i<selectedData.length;i++){
			userId+="\'"+selectedData[i].id+"\'"+','
		}
		jQuery('#printTeacherUserIds').val(userId.substr(0,userId.length-1));
		$("#teacherPrintForm").submit();
	}});
	
	//注册
	getCurrentTab().find("#${param.tabId}").find('#registerButton').linkbutton({onClick: function(){
		//var selectedData = easyuiUtils.getSelectedData('${tableId}');
		 var selectedRow=getCurrentTab().find('#${tableId}').datagrid('getSelections');
		 if(selectedRow.length>1){
			 $.messager.alert('消息','只能选择一条记录进行操作','warning');
			 return false;
		 }else if(selectedRow.length==0){
			 $.messager.alert('消息','请至少选择一条记录进行操作','warning');
			 return false;
		 }
		 var selectedData=selectedRow[0];
		$.messager.confirm('提示', '确定注册？', function(r){
			if(r) {
				var data=$.ajaxSubmitValue('${ctx }/common/user/teacher/registTeacher.do?id='+selectedData.id);
				var json=jQuery.parseJSON(data);
				var message = json.message;
				jQuery.messager.alert("提示信息",message,"info");
				jQuery('#${param.tabId}').panel('refresh');
			}
		});
	}});
});

</script>