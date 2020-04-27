<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>
<form id="deptForm" name="deptForm" action="${ctx}/common/dept/save.do" method="post" enctype="multipart/form-data">
	<div class="easyui-panel" title="基础信息" collapsible="true">
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<input type="hidden" name="id" id="id" value="${dept.id}"/>
		<tbody>
			<tr>
				<td>所在区域</td>
				<td>
					${regions.regionsName }
					<input type="hidden" name="regionsCode" value="${regions.regionsCode}"/>
				</td>
				<td rowspan="5">单位LOGO</td>
				<td rowspan="5" style="vertical-align: bottom;">
					<c:choose>
						<c:when test="${not empty dept.logo}">
							<c:set value="${ipanthercore:getJSONMap(dept.logo)}" var="map" />
							<img src="${ctx}${map.downloadUrl}" border="0" style="max-width:94px;max-height:84px">
							<textarea  name="logo" style="display:none;">${dept.logo}</textarea>
						</c:when>
						<c:otherwise>
							<img src="${ctx}/themes/easyui/themes/tms/images/default.jpg" border="0">
						</c:otherwise>
					</c:choose>
					<div>
						<input type="file" name="upload" content="<span style='color:red'>尺寸在94px*84px之内<br/>允许上传的文件类型：${fileTypes}<br/>允许上传文件的大小：${fileMaxSize}KB</span>" class="easyui-tooltip" position="left"/>
					</div>
				</td>
			</tr>
			
			
			<tr>
				<td><span style='color:red'>*</span>单位类型</td>
				<td>
					<select id="deptType" name="deptType" class="easyui-combobox" required style="width: 158px;" editable="false" data-options="onSelect:changeDeptType">
								<option value=""></option>
								${dict:getEntryOptionsSelected('DEPT_TYPE',dept.deptType)}
					</select>
				</td>
			</tr>
			<tr>
				<td ><span style='color:red'>*</span>单位类别</td>
				<td >
					<select id="deptCategory" name="deptCategory" class="easyui-combobox" required style="width: 158px;" editable="false">
								<option value=""></option>
								${dict:getEntryOptionsSelected('DEPT_CATEGORY',dept.deptCategory)}
					</select>
				</td>
			</tr>
			<tr>
				<td><span style='color:red'>*</span>单位性质</td>
				<td>
					<select id="deptNature" name="deptNature" class="easyui-combobox" required style="width: 158px;" editable="false">
								<option value=""></option>
								${dict:getEntryOptionsSelected('DEPT_NATURE_TYPE',dept.deptNature)}
					</select>
				</td>
			</tr>
			<tr>
				<td ><span style='color:red'>*</span>单位名称</td>
				<td >
					<input type="text" class="easyui-textbox" name="deptName" required value="${dept.deptName}"/>
				</td>
			</tr>
			<tr>
				<td>行业分类</td>
				<td colspan="3">
				<select id="industryType" name="industryType" class="easyui-combobox" required style="width: 60%px;" editable="false">
								<option value=""></option>
								${dict:getEntryOptionsSelected('IPR_INDUSTRY_TYPE',dept.industryType)}
					</select>
				</td>
			</tr>
			<tr>
				<td>单位地址</td>
				<td colspan="3">
					<input type="text" class="easyui-textbox" name="deptAddress" style="width: 60%" value="${dept.deptAddress}"/>
				</td>
			</tr>
			<tr>
				<td>组织机构码</td>
				<td>
					<input type="text" class="easyui-textbox" name="deptCode" value="${dept.deptCode}"/>
				</td>
				<td>单位邮政编码</td>
				<td>
					<input type="text" class="easyui-textbox" name="postCode" value="${dept.postCode}"/>
				</td>
			</tr>
			<tr>
				<td>联系电话</td>
				<td>
					<input type="text" class="easyui-textbox" name="linkPhone" value="${dept.linkPhone}" />
				</td>
				<td>传真电话</td>
				<td>
					<input type="text" class="easyui-textbox" name="linkFax" value="${dept.linkFax}"/>
				</td>
			</tr>
			<tr>
				<td width="20%">电子信箱</td>
				<td width="30%">
					<input type="text" class="easyui-textbox" name="linkEmail" value="${dept.linkEmail}" validType="email"/>
				</td>
				<td width="20%">单位主页</td>
				<td width="30%">
					<input type="text" class="easyui-textbox" name="homePage" value="${dept.homePage}"/>
				</td>
			</tr>
			
			<tr class="deptType_12">
				<td><span style='color:red'>*</span>基地级别</td>
				<td>
					<select id="trainOrgLevel" name="trainOrgLevel" class="easyui-combobox" required style="width: 158px;" editable="false">
								<option value=""></option>
								${dict:getEntryOptionsSelected('TRAIN_ORG_LEVEL',dept.trainOrgLevel)}
					</select>
				</td>
				<td>基地特色</td>
				<td>
					<input type="text" class="easyui-textbox" id="trainOrgFeature" name="trainOrgFeature" value="${dept.trainOrgFeature}"/>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
	
	<div class="easyui-panel" title="法定代表人信息" collapsible="true">
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="20%">姓名</td>
				<td width="30%">
					<input type="text" class="easyui-textbox" name="legalName" value="${dept.legalName}"/>
				</td>
				
				<td width="20%">移动电话</td>
				<td width="30%">
					<input type="text" class="easyui-textbox" name="legalMobile" value="${dept.legalMobile}" validType="mobilePhone"/>
				</td>
			</tr>
			<tr>
				<td >办公电话</td>
				<td >
					<c:set var="legalPhoneArr" value="${fn:split(dept.legalPhone, '-') }"/>
					<input type="text" class="easyui-textbox" id="legalPhoneHead" style="width:40px" value="${legalPhoneArr[0]}"/>
					-
					<input type="text" class="easyui-textbox" id="legalPhoneTail" style="width:80px"  value="${legalPhoneArr[1]}"/>
					<input type="hidden" id="legalPhone" name="legalPhone" value="${dept.legalPhone}"/>
				</td>
				<td>传真电话</td>
				<td>
					<input type="text" class="easyui-textbox" name="legalFax" value="${dept.legalFax}"/>
				</td>
			</tr>
			
			<tr>
				<td>电子信箱</td>
				<td colspan="3">
					<input type="text" class="easyui-textbox" name="legalEmail" value="${dept.legalEmail}" validType="email"/>
				</td>
			</tr>
			
		</tbody>
		</table>
	</div>
	
	<div class="easyui-panel" title="单位管理员信息" collapsible="true">
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				
				<td width="20%">姓名</td>
				<td width="30%">
					<input type="text" class="easyui-textbox" name="adminName" value="${dept.adminName}"/>
				</td>
				<td width="20%">移动电话</td>
				<td width="30%">
					<input type="text" class="easyui-textbox" name="adminMobile" value="${dept.adminMobile}" validType="mobilePhone"/>
				</td>
			</tr>
			<tr>
				<td>办公电话</td>
				<td>
					<c:set var="adminPhoneArr" value="${fn:split(dept.adminPhone, '-') }"/>
					<input type="text" class="easyui-textbox" id="adminPhoneHead" style="width:40px" value="${adminPhoneArr[0]}"/>
					-
					<input type="text" class="easyui-textbox" id="adminPhoneTail" style="width:80px"  value="${adminPhoneArr[1]}"/>
					<input type="hidden" id="adminPhone" name="adminPhone" value="${dept.adminPhone}"/>
				</td>
				<td>传真电话</td>
				<td>
					<input type="text" class="easyui-textbox" name="adminFax" value="${dept.adminFax}"/>
				</td>
			</tr>
			<tr>
				<td>电子信箱</td>
				<td colspan="3">
					<input type="text" class="easyui-textbox" name="adminEmail" value="${dept.adminEmail}" validType="email"/>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
	<div style="text-align: center">
		<button type="button" onclick="save()" class="easyui-linkbutton" > <i class="fa fa-save"></i>保 存 </button>
		<button type="reset"  class="easyui-linkbutton" class="easyui-linkbutton"> 重 置 </button>
	</div>
</form>
<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
	
});
<c:if test="${not empty dept.id}">
$.parser.onComplete=changeDeptType;
</c:if>

function save() {
	$.messager.confirm('提示', '确定保存？', function(r){
		if(r) {
			//处理数据
			//处理管理员办公电话
			var adminPhoneHead = jQuery("#adminPhoneHead").val();
			var adminPhoneTail = jQuery("#adminPhoneTail").val();
			if(""!=adminPhoneHead&&""!=adminPhoneTail) {
				jQuery("#adminPhone","#deptForm").val(adminPhoneHead+"-"+adminPhoneTail);
			}
			//处理法人代表办公电话
			var legalPhoneHead = jQuery("#legalPhoneHead").val();
			var legalPhoneTail = jQuery("#legalPhoneTail").val();
			if(""!=legalPhoneHead&&""!=legalPhoneHead) {
				jQuery("#legalPhone","#deptForm").val(legalPhoneHead+"-"+legalPhoneTail);
			}
			jQuery('#deptForm').form('submit',{
				onSubmit: function(){
					 return $('#deptForm').form('validate');
				},
			    success: function(data){
			    	var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							jQuery.messager.alert("提示信息",message,"error");
						} else if(parseInt(statusCode)==200){
							jQuery.messager.alert("提示信息",message,"info");
							jQuery('#dept_list_div').panel('refresh');
							closeWindow("showAddDialog");
						}
					}
			    }
			}); 
		}
		
	});
}

//单位类型改变时触发
function changeDeptType() {
	var deptType = jQuery("#deptType","#deptForm").combobox("getValue");
	if("12"==deptType) {//培训机构：则展示基地信息，且必填
		$("#trainOrgLevel","#deptForm").combobox("enableValidation");
		$(".deptType_12").show();
	} else {
		$("#trainOrgFeature","#deptForm").textbox("setValue","");
		$("#trainOrgLevel","#deptForm").combobox("setValue","");
		$("#trainOrgLevel","#deptForm").combobox("disableValidation");
		$(".deptType_12").hide();
	}
	$.parser.onComplete=function(){};
}
</script>
