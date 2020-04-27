<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="suffix" value="${param.tabId}"/>
<c:set var="formId" value="employees_list_form${suffix}"/>
<c:set var="searchId" value="employees_search${suffix}"/>
<c:set var="tableId" value="employees_list${suffix}"/>

 <c:set var="currentDeptId" value="${sessionScope.loginUser.firstDeptId}"/>
<%--${ipanthercommon:isAdminCompetent(sessionScope.loginUser.deptType)} --%>
<c:set var="isAdminCompetent" value="${ipanthercommon:isAdminCompetent(sessionScope.loginUser.deptType)}"/>
<form id="${formId}" action="${ctx }/common/user/employees/list.do?tabId=${param.tabId }" method="post">
<input id="action" type="hidden" name="paramMap[action]" value="" />
<input id="ids" type="hidden" name="paramMap[ids]" value="" />
<input type="hidden" id="deptId" name="paramMap[deptId]" value="${searchParam.paramMap.deptId }"/>
<div id="${searchId}" style="margin: 5px">
	<div style="margin: 3px">
		姓名:
		<input class="easyui-textbox" type="text" name="paramMap[realName]" value="${searchParam.paramMap.realName}" />
		
		行业分类:
		<select class="easyui-combobox" name="paramMap[industryType]" style="width: 158px;" editable="false">
				<option value=""></option>
				${dict:getEntryOptionsSelected('IPR_INDUSTRY_TYPE',searchParam.paramMap.industryType)}
		</select>

		<button type="button" class="easyui-linkbutton main-btn" onclick="javascript:easyuiQuery('${formId}','user_list_div');"><i class="fa fa-search"></i> 查询</button>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel(getCurrentTabId())" ><i class="fa fa-eraser"></i> 重置</a>
	</div>
	<div style="margin: 3px">
		<button type="button" id="viewButton" class="easyui-linkbutton" ><i class="fa fa-search"></i>查看</button>
	  	<button type="button" id="addButton" class="easyui-linkbutton" ><i class="fa fa-plus"></i> 新增</button>
	  	<button type="button" id="editButton" class="easyui-linkbutton" ><i class="fa fa-pencil"></i> 修改</button>
		<button type="button" id="delButton" class="easyui-linkbutton delete-btn"><i class="fa fa-minus"></i> 注销</button>
		<button type="button" id="exportButton" class="easyui-linkbutton"><i class="fa "></i>导出结果</button>
		<button type="button" id="importButton" class="easyui-linkbutton" ><i class="fa "></i>导入</button>
		<button type="button" id="printButton" class="easyui-linkbutton" ><i class="fa fa-print"></i>打印</button>
	</div>	
</div>
 <table id="${tableId}" title="人员信息列表"	toolbar="#${param.tabId } #user_list_div #${searchId}" pagination="true"
 	rownumbers="true" fitColumns="true"  singleSelect="false" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
                	<th data-options="field:'id',checkbox:true"></th>
					<th field="userName" width="25">用户名</th>
					<th field="realName" width="25">姓名</th>
					<th field="deptName" width="40">单位</th>
					<th field="mobilePhone" width="25">联系电话</th>
					<th field="email" width="25">邮箱</th>
					<th field="roleName" width="25">角色权限</th>
					<th field="studentStatusName" width="25">状态</th>
					<th field="studentStatus" data-options="hidden:true">studentStatus</th>
					<th field="teacherStatus" data-options="hidden:true">teacherStatus</th>
					<th field="regionsCode" data-options="hidden:true">区域编码</th>
					<th field="deptId" data-options="hidden:true">deptId</th>
					<th field="isPersonValue" data-options="hidden:true" ></th>
				</tr>
			</thead>
			<tbody>   
       	<c:forEach items="${users }" var="user">
        <tr>   
           <td>${user.id }</td>
           <td>${user.userName }</td>
           <td>${user.realName }</td>
           <td>${user.deptName }</td>
           <td>${user.mobilePhone }</td> 
           <td>${user.email }</td> 
           <td>${user.roleName }</td>
           <td>
			   ${dict:getEntryName('USER_STATUS',user.status) }
		   </td>
           <td>${user.studentStatus }</td>
           <td>${user.teacherStatus }</td>
           <td>${user.regionsCode }</td>
           <td>${user.deptId }</td>
           <td>${user.isPerson}</td>
        </tr>   
       </c:forEach>
    </tbody>   
</table> 
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
	<jsp:param name="pageForm" value="${formId}" />
    <jsp:param name="tableId" value="${tableId}"/>
	<jsp:param name="type" value="easyui" />
	<jsp:param name="divId" value="${param['tabId']} #user_list_div" />
</jsp:include>
 </form>
<form id="printForm" action="${ctx}/common/user/employees/print.do" target="_blank" method="post">
	<input id="printUserIds" type="hidden" name="paramMap[userIds]" />
</form >
<script type="text/javascript">
$(document).ready(function(e) {
	//新增
	getCurrentTab().find('#addButton').linkbutton({onClick: function(){
		easyuiUtils.openWindow('showAddDialog','新增员工',900,450,'${ctx}/common/user/employees/editTabs.do',true);
	}});
	//修改
	getCurrentTab().find('#editButton').linkbutton({onClick: function(){
		//var selectedData = easyuiUtils.getSelectedData('${tableId}');
		 var selectedData=getCurrentTab().find('#${tableId}').datagrid('getSelections');
		 if(selectedData.length>1){
			 $.messager.alert('消息','只能选择一条记录进行操作','warning');
			 return false;
		 }else if(selectedData.length==0){
			 $.messager.alert('消息','请至少选择一条记录进行操作','warning');
			 return false;
		 }
			var studentStatus = selectedData[0].studentStatus;
			var teacherStatus = selectedData[0].teacherStatus;
			if(studentStatus=='02') {
				$.messager.alert("提示","该用户正在进行学员注册审核，不能修改","error");
				return false;
			}
			if(teacherStatus=='02') {
				$.messager.alert("提示","该用户正在进行教师注册审核，不能修改","error");
				return false;
			}

			/*if(''=='false'&&''!= selectedData[0].deptId) {
				$.messager.alert("提示","不能修改本单位外的用户","error");
				return false;
			}*/
			var id = selectedData[0].id;
			easyuiUtils.openWindow('showAddDialog','修改员工信息',900,450,'${ctx}/common/user/employees/editTabs.do?id='+id,true);
	}});
	//查看
	getCurrentTab().find('#viewButton').linkbutton({onClick: function(){
		//var selectedData = easyuiUtils.getSelectedData('${tableId}');
		 var selectedData=getCurrentTab().find('#${tableId}').datagrid('getSelections');
		 if(selectedData.length>1){
			 $.messager.alert('消息','只能选择一条记录进行操作','warning');
			 return false;
		 }else if(selectedData.length==0){
			 $.messager.alert('消息','请至少选择一条记录进行操作','warning');
			 return false;
		 }
			var id = selectedData[0].id;
			easyuiUtils.openWindow('showAddDialog','查看员工信息',900,450,'${ctx}/common/user/employees/viewTabs.do?id='+id,true);
	}});
	//注销
	getCurrentTab().find('#delButton').linkbutton({onClick: function(){
		//var selectedData = easyuiUtils.getSelectedData('${tableId}');
		 var selectedDatas=getCurrentTab().find('#${tableId}').datagrid('getSelections');
		 if(selectedDatas.length>1){
			 $.messager.alert('消息','只能选择一条记录进行操作','warning');
			 return false;
		 }else if(selectedDatas.length==0){
			 $.messager.alert('消息','请至少选择一条记录进行操作','warning');
			 return false;
		 }
		 var selectedData=selectedDatas[0];
		if(null!=selectedData) {
			$.messager.confirm('提示', '是否确定注销‘'+selectedData.userName+'’用户?', function(r){
				if(r) {
					var data=$.ajaxSubmitValue('${ctx }/common/user/delete.do?user.id='+selectedData.id);
					debugger
					var json=jQuery.parseJSON(data);
					var responseCode = json.responseCode;
					var msg = json.responseMsg;
					if(parseInt(responseCode)==0){
						jQuery.messager.alert("提示信息","注销成功","info");
						jQuery('#'+getCurrentTabId()).panel('refresh');
					} else {
						jQuery.messager.alert("提示信息",msg,"error");
					}
				}
			});
		}
	}});
	//导出
	getCurrentTab().find('#exportButton').linkbutton({onClick: function(){
		var selectIds='';
		var selectedData=getCurrentTab().find('#${tableId}').datagrid('getSelections');
		if(selectedData.length==0){
			 $.messager.alert('消息','请选择导出记录','warning');
			 return false;
		 }
		for(var i=0;i<selectedData.length;i++){
			if(selectIds == ''){
				selectIds +=  selectedData[i].id;
			}else {
				selectIds += ',' +  selectedData[i].id;
			}
		}
		$.messager.show({
			title:'提示',
			msg:'系统正在导出，请稍后',
			timeout:2000,
			showType:'slide'
		});
		jQuery('#${formId}').find(":hidden[id='action']").val("export");
		jQuery('#${formId}').find(":hidden[id='ids']").val(selectIds);
		debugger
		jQuery('#${formId}').form('submit',{
		    success: function(data){}
		}); 
		jQuery('#${formId}').find("#userIds").val('');
		jQuery('#${formId}').find(":hidden[id='action']").val(""); 
	}});
	//导入
	getCurrentTab().find('#importButton').linkbutton({onClick: function(){
		easyuiUtils.openWindow('showAddDialog','批量导入员工',500,170,'${ctx}/common/user/employees/goImport.do',true);
	}});
	//打印
	getCurrentTab().find('#printButton').linkbutton({onClick: function(){
		/* var selectedData = easyuiUtils.getSelectedData('${tableId}');
		if(null!=selectedData) {
			var id = selectedData.id;
			window.open('${ctx}/common/user/student/print.do?id='+id);
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
		jQuery('#printUserIds').val(userId.substr(0,userId.length-1));
		$("#printForm").submit();
	}});
	//设置人才
	getCurrentTab().find('#setPerson01').linkbutton({onClick: function(){
		var selectedId=getCurrentTab().find('#${tableId}').datagrid('getSelections');
		var userIds="";//设置用户id多个
		var isSet=true;
		for(var i=0;i<selectedId.length;i++){
			userIds+=','+selectedId[i].id;
		}
		if(selectedId.length>0){
			for(var j=0;j<selectedId.length;j++){
				/*if(selectedId[j].isPersonValue!=''){
					$.messager.alert('提示','选择的信息已存在一条已经设为人才！');
					isSet=false;
					break;
				}*/
			  }
			if(isSet){
				//$.messager.confirm('提示','是否确认设置人才?',function(result){
				easyuiUtils.openWindow('showAddDialog','选择员工类型',450,150,'${ctx}/common/user/employees/choosePersonType.do?paramMap[userIds]='+userIds,false);
						
			//});
			}
		}else{
			$.messager.alert('提示','请选择要设为员工的信息！');
		}
		}
	});
	
	//取消人才
	getCurrentTab().find('#setPerson02').linkbutton({onClick: function(){
		var selectedId=getCurrentTab().find('#${tableId}').datagrid('getSelections');
		 var userIds="";//设置用户id多个
		 var isCancel=true;
		for(var i=0;i<selectedId.length;i++){
			userIds+=','+selectedId[i].id;
		}
		if(selectedId.length>0){
		for(var j=0;j<selectedId.length;j++){
		/*	if(selectedId[j].isPersonValue==''){
				$.messager.alert('提示','选择的信息已存在一条已经取消人才！');
				isCancel=false;
				break;
			   }*/
		    }
		    if(isCancel){
		    	var url="${ctx}/common/user/student/setPerson.do?paramMap[isPerson]=&paramMap[userIds]="+userIds;
		    	var data= jQuery.ajaxSubmitValue(url);
				if(data){
					var json=jQuery.parseJSON(data);
					if(json){
						var msg = json.message;
						var statusCode = json.statusCode;
						//Response转为AjaxReturnObject
						if(json.responseCode!=undefined){
							msg=json.responseMsg;
							statusCode=(json.responseCode=='00'?200:300);
						}
						if(parseInt(statusCode)==300){
							$.messager.alert('提示',msg,'error');
						}
						else if(parseInt(statusCode)==200){
							$.messager.alert('提示',msg,'info',function(){
								//closeWindow("${paramMap.dialogId}");
								getCurrentTab().panel('refresh');
							});
						}
					}else{
							$.messager.alert('提示','返回数据解析错误！','error');
					}
			 }
			 else{
				$.messager.alert('提示','返回数据错误！','error');
			}
			//easyuiUtils.confirmPostUrl("是否确认取消人才？","${ctx}/common/user/student/setPerson.do?paramMap[isPerson]=&paramMap[userIds]="+userIds);
		   /* $.messager.confirm('提示','是否确认取消人才?',function(result){
					if(result){
				easyuiUtils.openWindow('showAddDialog','选择人才类型',450,150,'${ctx}/common/user/student/choosePersonType.do?paramMap[userIds]='+userIds,false);
						
					}
			});*/
			}
		}else{
			$.messager.alert('提示','请选择要取消员工的信息！');
		}
		}
	});
	
});
</script>