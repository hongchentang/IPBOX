<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="suffix" value="${param.tabId}"/><!-- 用于jsp被多次同时用到时区分各个DIV 的ID和函数名称 -->
<c:set var="formId" value="deptAdmin_list_form${suffix}"/>
<c:set var="searchId" value="deptAdmin_search${suffix}"/>
<c:set var="tableId" value="deptAdmin_list${suffix}"/>
<form id="${formId}" action="${ctx }/common/user/listAdmin.do?tabId=${param.tabId }&paramMap[deptId]=${searchParam.paramMap.deptId}" method="post">
<div id="${searchId}">
	<div style="margin: 10px">
		姓名:
		<input class="easyui-textbox" type="text" name="paramMap[realName]" value="${searchParam.paramMap.realName}" />
		<button type="button" class="easyui-linkbutton main-btn" onclick="javascript:easyuiQuery('${formId}','${param.tabId}');"><i class="fa fa-search"></i> 查询</button>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel(getCurrentTabId())" ><i class="fa fa-eraser"></i> 重置</a>
	</div>
	<div style="margin: 10px">
	  	<button type="button" id="addButton" class="easyui-linkbutton" ><i class="fa fa-plus"></i> 新增</button>
	  	<button type="button" id="editButton" class="easyui-linkbutton" ><i class="fa fa-pencil"></i> 修改</button>
		<button type="button" id="delButton" class="easyui-linkbutton delete-btn" ><i class="fa fa-minus"></i> 注销</button>
		<button type="button" id="addDefaultButton" class="easyui-linkbutton"><i class="fa fa-plus"></i> 添加默认区域管理员</button>
	</div>	
</div>
 <table id="${tableId}" title="管理员列表"	toolbar="#${param.tabId } #${searchId}" pagination="true"
 	rownumbers="true" fitColumns="true"  singleSelect="true" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
                	<th data-options="field:'id',checkbox:true"></th>
					<th field="userName" width="25">用户名</th>
					<th field="realName" width="25">姓名</th>
					<th field="sex" width="8" align="center">性别</th>
					<th field="regionsName" width="15">区域</th>
					<th field="deptName" width="40">单位</th>
					<th field="mobilePhone" width="25">联系电话</th>
					<th field="email" width="25">邮箱</th>
					<th field="status" width="25">状态</th>
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
           <td>
           		${dict:getEntryName('SEX_TYPE',user.sex) }
           </td>
           <td>${user.regionsName}</td>
           <td>${user.deptName }</td>
           <td>${user.mobilePhone }</td> 
            <td>${user.email }</td> 
            <td>${dict:getEntryName('USER_STATUS',user.status) }
            </td> 
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
<script type="text/javascript">
$(document).ready(function(e) {
	//添加
	getCurrentTab().find('#addButton').linkbutton({onClick: function(){
		easyuiUtils.openWindow('showAddDialog','新增管理员',900,420,'${ctx}/common/user/edit.do?deptId=${searchParam.paramMap.deptId}&isAdmin=true',true);	
	}});
	//编辑
	getCurrentTab().find('#editButton').linkbutton({onClick: function(){
		var selectedData = easyuiUtils.getSelectedData('${tableId}');
		if(null!=selectedData) {
			var id = selectedData.id;
			easyuiUtils.openWindow('showAddDialog','修改管理员信息',900,420,'${ctx}/common/user/edit.do?id='+id+"&isAdmin=true",true);
		}
	}});
	//注销
	getCurrentTab().find('#delButton').linkbutton({onClick: function(){
		var selectedData = easyuiUtils.getSelectedData('${tableId}');
		if(null!=selectedData) {
			$.messager.confirm('提示', '是否确定注销‘'+selectedData.userName+'’管理员账户?', function(r){
				if(r) {
					var data=$.ajaxSubmitValue('${ctx }/common/user/delete.do?user.id='+selectedData.id);
					var json=jQuery.parseJSON(data);
					var responseCode = json.responseCode;
					if(parseInt(responseCode)==0){
						jQuery.messager.alert("提示信息","注销成功","info");
						easyuiQuery('${formId}','${param.tabId}');
					} else {
						jQuery.messager.alert("提示信息","注销失败","error");
					}
				}
			});
		}
	}});
	
	getCurrentTab().find('#addDefaultButton').linkbutton({onClick: function(){
		$.messager.confirm('提示', '确定添加默认区域管理员吗?', function(r){
			if(r) {
				var deptId='${searchParam.paramMap.deptId}';
				$.ajax({
					   type: "POST",
					   url: "${ctx }/common/user/addDefaultAdmin.do",
					   data: "deptId="+deptId,
					   asynchronous:false,
					   success: function(data){
						   if("200"==data.statusCode) {
							   $.messager.alert("提示信息","新增成功");
							   jQuery('#'+getCurrentTabId()).panel('refresh');
						   } else {
							   $.messager.alert("提示信息","新增失败");
						   }
					   }
				});
			}
		});
	}});
});
</script>