<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>
<form id="userForm" name="userForm" action="${ctx}/common/user/employees/save.do" method="post" enctype="multipart/form-data">
	<div class="easyui-panel" title="基础信息" collapsible="true">
		<input type="hidden" name="id" id="id" />
		<input type="hidden" name="userId" id="userId" value=""/>
		<input type="hidden" name="deptId" id="temp" value=""/>

		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td><span style="color:red">*</span>用户名</td>
				<td>
					<input type="text" class="easyui-textbox" name="userName" required value="" validType="username" autocomplete="off"/>
				</td>
				<td rowspan="4">个人头像</td>
				<td rowspan="4" style="vertical-align: bottom;">
					<c:choose>
						<c:when test="${not empty user.avatar}">
							<c:set value="${ipanthercore:getJSONMap(user.avatar)}" var="map" />
							<img src="${ctx}${map.downloadUrl}" border="0" style="max-width: 120px;max-height:160px" >
							<textarea  name="logo" style="display:none;">${user.avatar}</textarea>
						</c:when>
						<c:otherwise>
							<img src="${ctx}/themes/easyui/themes/tms/images/default.jpg" border="0">
						</c:otherwise>
					</c:choose>
					<div>
						<input type="file" name="upload" content="<span style='color:red'>尺寸在120px*160px之内<br/>允许上传的文件类型：${fileTypes}<br/>允许上传文件的大小：${fileMaxSize}KB</span>" class="easyui-tooltip" position="left"/>
					</div>
				</td>
			</tr>

			<tr>
				<td><span style="color:red">*</span>姓名</td>
				<td>
					<input type="text" class="easyui-textbox" name="realName" required value=""/>
				</td>
			</tr>

			<tr>
				<td>性别</td>
				<td>
					<%--<select name="sex" class="easyui-combobox" style="width: 158px;" editable="false">
						<option value=""></option>
						${dict:getEntryOptionsSelected('SEX_TYPE',user.sex) }
					</select>--%>
					<input id="sex" name="sex" class="easyui-textbox" type="text" title="性别" data-options="required:true"/>
				</td>
			</tr>

			<tr>
				<td width="20%"><span style="color:red">*</span>密码</td>
				<td width="30%">
					<input type="password" class="easyui-textbox" name="passwordPlain" required value="" />
				</td>
			</tr>

			<tr>
				<td >办公电话</td>
				<td >
					<c:set var="officePhoneArr" value="${fn:split(user.officePhone, '-') }"/>
					<input type="text" class="easyui-textbox" id="officePhoneHead" data-options="width: 60" value="${officePhoneArr[0]}"/>
					-
					<input type="text" class="easyui-textbox" id="officePhoneTail" data-options="width: 100" value="${officePhoneArr[1]}"/>
					<input type="hidden" id="officePhone" name="officePhone" value="${user.officePhone}"/>
				</td>
				<td >移动电话</td>
				<td >
					<input type="text" class="easyui-textbox" name="mobilePhone" value="" validType="mobilePhone"/>
				</td>
			</tr>

			<tr>
				<td>电子信箱</td>
				<td>
					<input type="text" class="easyui-textbox" name="email" value="" validType="email"/>
				</td>
				<td>传真</td>
				<td>
					<input type="text" class="easyui-textbox" name="faxes" value="" />
				</td>
			</tr>

			<tr>
				<td>用户角色</td>
				<td>
					<input type="text" id="roleCode" name="roleCode" class="easyui-texbox" data-options="">
				</td>
				<td>
					管理部门
				</td>
				<td>
					<input type="text" id="concatDept" name="concatDept" class="easyui-combobox" data-options="disabled: true, multiline: true">
					<input type="hidden" id="deptIds" name="deptIds">
				</td>
			</tr>
		</tbody>
	</table>
	</div>
	
	<div style="text-align: center; margin-top: 10px;">
		<button type="button" onclick="saveUser()" class="easyui-linkbutton" > <i class="fa fa-save"></i>保 存 </button>
		<button type="reset"  class="easyui-linkbutton" class="easyui-linkbutton"> 重置 </button>
	</div>
</form>
<script type="text/javascript">
 //tableVBg('.alter-table-v');
$(document).ready(function (){

	$("#sex").combobox({
		url: '${ctx}/common/datadict/getByTypeCode.do?typeCode=SEX_TYPE',
		emptyText: '',
		valueField: 'dictValue',
		textField: 'dictName',
		slide: true,
		valueFieldID: 'sex',
		limitToList: true,
		panelHeight: 'auto'
	});

	$("#roleCode").combobox({
		url: '${ctx}/common/security/role/jsonList.do',
		emptyText: '',
		valueField: 'roleCode',
		textField: 'name',
		slide: true,
		valueFieldID: 'roleCode',
		limitToList: true,
		panelHeight: 'auto',
		onSelect: function (record) {
			$('#deptIds').val('');
			if(record.roleCode == 'deptAdmin'){
				$("#concatDept").combobox({disabled: false});
			}else {
				$("#concatDept").combobox({disabled: true});
			}
		}
	});

	var unitId = $("#deptTree",getCurrentTab()).tree("getRoot").id;
	$("#concatDept").combobox({
		url: '${ctx}/common/dept/getDepartmentList.do?unitId=' + unitId,
		emptyText: '',
		valueField: 'id',
		multiple: true,
		textField: 'deptName',
		slide: true,
		valueFieldID: 'concatDept',
		limitToList: true,
		panelHeight: 'auto',
		onSelect: function (record) {
			console.log('---->' + record.deptName);
			var dept = $('#deptIds');
			var ids = dept.val();
			ids = ids + ',' + record.id;
			dept.val(ids);
		},
		onUnselect: function (record) {
			console.log('<----' + record.deptName);
			var dept = $('#deptIds');
			var ids = dept.val();
			ids = ids.replace((',' + record.id), '');
			dept.val(ids);
		},
		onLoadSuccess : function (none) {
			var arr = '${user.deptIds}'.split(',');
			$('#concatDept').combobox('setValues', arr);
		}
	});

	//加载数据
	if ('${user.id}' !== '') {
		$('#userForm').form('load', {
			id: '${user.id}',
			userId: '${user.id}',
			deptId: '${user.deptId}',
			userName: '${user.userName}',
			realName: '${user.realName}',
			sex: '${user.sex}',
			passwordPlain: '${user.passwordPlain}',
			mobilePhone: '${user.mobilePhone}',
			email: '${user.email}',
			roleCode: '${user.roleCode}'
		});
	}
});

function saveUser() {

	$.messager.confirm('提示', '确定保存？', function(r){
		if(r) {
			//处理数据
			//处理办公电话
			var officePhoneHead = jQuery("#officePhoneHead").val();
			var officePhoneTail = jQuery("#officePhoneTail").val();
			if(""!=officePhoneHead&&""!=officePhoneTail) {
				jQuery("#officePhone","#studentForm").val(officePhoneHead+"-"+officePhoneTail);
			}
			//处理部门信息
			var selectData =  $("#deptTree",getCurrentTab()).tree('getSelected');
			$("#temp").val(selectData.id);
			$("#userForm").ajaxSubmit({
				type: 'post',  
				success: function(json){
					if(!$.isEmptyObject(json)){
						var responseMsg = json.message;
						var responseCode = json.statusCode;
						if("200"==responseCode) {
							//成功
							jQuery.messager.alert("提示信息","保存成功","info",function() {
								jQuery('#'+getCurrentTabId()).panel('refresh');
								closeWindow('showAddDialog');
							});
						} else {
							//错误
							jQuery.messager.alert("提示信息",responseMsg,"error");
						}
					}
				}
			});
		}
		
	});
}

function test() {

}
</script>
