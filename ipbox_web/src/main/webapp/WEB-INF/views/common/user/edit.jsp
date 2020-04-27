<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%> 
<form id="user_saveFrom" name="user_saveFrom" action="${ctx}/common/user/save.do" method="post">
	 	<input type="hidden" name="user.id" id="id" value="${user.id}"/>
	 	<input type="hidden" name="isAdmin" id="isAdmin" value="${isAdmin}"/><!-- 是否是管理员 -->
		<input type="hidden" name="user.deptId" id="deptId" value="${user.deptId}"/>
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="20%"><span style="color:red">*</span>用户名</td>
				<td width="30%">
					<input type="text" class="easyui-textbox" name="user.userName" required value="${user.userName}" validType="username"/>
				</td>
				<td width="20%"><span style="color:red">*</span>姓名</td>
				<td width="30%">
					<input type="text" class="easyui-textbox" name="user.realName" required value="${user.realName}"/>
				</td>
			</tr>
			<tr>
				<td><span style="color:red">*</span>证件类型</td>
				<td>
					<select id="paperworkType" name="user.paperworkType" class="easyui-combobox" required style="width: 158px;" editable="false" data-options="onSelect:changePaperworkType">
						<option value=""></option>
						${dict:getEntryOptionsSelected('PAPERWORK_TYPE',user.paperworkType) }
					</select>
				</td>
				<td><span style="color:red">*</span>证件号</td>
				<td>
					<input type="text" class="easyui-textbox" name="user.paperworkNo" id="paperworkNo" value="${user.paperworkNo}" data-options="required:true,missingMessage:'请输入证证件号'"/>
				</td>
			</tr>
			<tr>
				<td>出生日期</td>
				<td>
					<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM'})"class="easyui-validatebox Wdate" name="user.bornDate" value="${user.bornDate}"/>
				</td>
				<td><span style="color:red">*</span>密码</td>
				<td>
					<input type="password" class="easyui-textbox" name="user.passwordPlain" required value="${user.passwordPlain}" />
				</td>
			</tr>
			<tr>
				<td>密码提示问题</td>
				<td>
					<input type="text" class="easyui-textbox" name="user.passwordAsk" value="${user.passwordAsk}"/>
				</td>
				<td>密码答案</td>
				<td>
					<input type="text" class="easyui-textbox" name="user.passwordAnswer" value="${user.passwordAnswer}"/>
				</td>
			</tr>
			<tr>
				<td>性别</td>
				<td>
					<select name="user.sex" class="easyui-combobox" style="width: 158px;" editable="false">
						<option value=""></option>
						${dict:getEntryOptionsSelected('SEX_TYPE',user.sex) }
					</select>
				</td>
				<td>政治面貌</td>
				<td>
					<select id="politicsRole" name="user.politicsRole" class="easyui-combobox" style="width: 158px;" editable="false">
						<option value=""></option>
						${dict:getEntryOptionsSelected('ZZMM',user.politicsRole) }
					</select>
				</td>
			</tr>
			<tr>
				<td>国籍/地区</td>
				<td>
					<select id="country" name="user.country" class="easyui-combobox" style="width: 158px;" editable="false">
						<option value=""></option>
						${dict:getEntryOptionsSelected('GJDQ',user.country) }
					</select>
				</td>
				<td>籍贯</td>
				<td>
					<select id="hometownProvince" name="user.hometownProvince" style="width:80px" editable="false"></select>
					<select id="hometownCity" name="user.hometownCity" style="width:80px" editable="false"></select>
					<script type="text/javascript">
							jQuery('#user_saveFrom').find('#hometownCity').combobox({
							    url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${user.hometownProvince}',
							    valueField:'id',
							    textField:'text'
							});
							jQuery('#user_saveFrom').find('#hometownProvince').combobox({
								url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${ipanthercommon:getChinaRegionsCode()}',
							    valueField:'id',
							    textField:'text',
							    parentField:'pid',
							    panelWidth:200,
							    onLoadSuccess:function(){
							    	if('${user.hometownProvince}'!=''){
							    		jQuery('#user_saveFrom').find('#hometownProvince').combobox('setValue','${user.hometownProvince}');
									}
							    	if('${user.hometownCity}'!=''){
							    		jQuery('#user_saveFrom').find('#hometownCity').combobox('setValue','${user.hometownCity}');
							    	}
							    },
							    onChange:function(newValue, oldValue) {
							    	if(newValue!=undefined&&""!=newValue) {
							    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
								    	jQuery('#user_saveFrom').find('#hometownCity').combobox("reload",url);
								    	jQuery('#user_saveFrom').find('#hometownCity').combobox('setValue','');
							    	}
							    }
								
							}); 
					</script>
				</td>
			</tr>
			<tr>
				<td>目前所在地区</td>
				<td colspan="3">
					<select name="user.currentProvince" id="currentProvince" class="easyui-validatebox"  style="width: 80px;" editable="false"></select>
					<select name="user.currentCity" id="currentCity" class="easyui-validatebox"  style="width: 80px;" editable="false"></select>
					<select name="user.currentCounties" id="currentCounties" class="easyui-validatebox"  style="width: 80px;" editable="false"></select>
					<input type="text" class="easyui-textbox" name="user.currentStreet" id="currentStreet" value="${user.currentStreet}" style="width:220px"/>
                             <script type="text/javascript">
                             jQuery('#user_saveFrom').find('#currentCounties').combobox({
  							    url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${user.currentCity}',
  							    valueField:'id',
  							    textField:'text'
  							 });
                              jQuery('#user_saveFrom').find('#currentCity').combobox({
  								url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${user.currentProvince}',
  							    valueField:'id',
  							    textField:'text',
  							    parentField:'pid',
  							    onChange:function(newValue, oldValue) {
  							    	if(newValue!=undefined&&""!=newValue) {
  							    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
  								    	jQuery('#user_saveFrom').find('#currentCounties').combobox("reload",url);
  								    	jQuery('#user_saveFrom').find('#currentCounties').combobox('setValue','');
  							    	}
  							    }
  							 });
                              jQuery('#user_saveFrom').find('#currentProvince').combobox({
  								url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${ipanthercommon:getChinaRegionsCode()}',
  							    valueField:'id',
  							    textField:'text',
  							    parentField:'pid',
  							    panelWidth:200,
  							    onLoadSuccess:function(){
  							    	if('${user.currentProvince}'!=''){
  							    		jQuery('#user_saveFrom').find('#currentProvince').combobox('setValue','${user.currentProvince}');
  									}
  							    	if('${user.currentCity}'!=''){
  							    		jQuery('#user_saveFrom').find('#currentCity').combobox('setValue','${user.currentCity}');
  									}
  							    	if('${user.currentCounties}'!=''){
  							    		jQuery('#user_saveFrom').find('#currentCounties').combobox('setValue','${user.currentCounties}');
  									}
  							    },
  							    onChange:function(newValue, oldValue) {
  							    	if(newValue!=undefined&&""!=newValue) {
  							    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
  								    	jQuery('#user_saveFrom').find('#currentCity').combobox("reload",url);
  								    	jQuery('#user_saveFrom').find('#currentCity').combobox('setValue','');
  								    	jQuery('#user_saveFrom').find('#currentCounties').combobox('setValue','');
  							    	}
  							    }
  							}); 
                            </script>
				</td>
			</tr>
			<tr>
				<td width="20%">办公电话</td>
				<td width="30%">
					<c:set var="officePhoneArr" value="${fn:split(user.officePhone, '-') }"/>
					<input type="text" class="easyui-textbox" id="officePhoneHead" style="width:40px" value="${officePhoneArr[0]}"/>
					-
					<input type="text" class="easyui-textbox" id="officePhoneTail" style="width:80px"  value="${officePhoneArr[1]}"/>
					<input type="hidden" id="officePhone" name="user.officePhone" value="${user.officePhone}"/>
				</td>
				<td width="20%">移动电话</td>
				<td width="30%">
					<input type="text" class="easyui-textbox" name="user.mobilePhone" value="${user.mobilePhone}" validType="mobilePhone"/>
				</td>
			</tr>
			<tr>
				<td>电子信箱</td>
				<td colspan="3">
					<input type="text" class="easyui-textbox" name="user.email" value="${user.email}" validType="email"/>
				</td>
			</tr>
			<c:if test="${isAdmin }">
				<tr>
		            <td style="text-align:right;" ><span style="color:red">*</span>角色类型</td>
		            <td colspan="3">
		                <select id="roleIds" name="roleIds" class="easyui-combotree" checkbox="true" required style="width: 200px;" editable="false">
						</select>
						<script type="text/javascript">
		                          jQuery("#user_saveFrom").find("#roleIds").combotree({
										data: eval("("+'${rolesJson}'+")"), 
										checkbox:true,
										multiple:true,
										onLoadSuccess:function(){
											var value='${roleIds}';
											if(value!=''){
												  jQuery("#user_saveFrom").find("#roleIds").combotree('setValues',value);
											}
										}
								}); 
		                </script>
		             </td>
	          </tr>
			</c:if>
		</tbody>
	</table>
	
	<div style="text-align: center">
		<button type="button" onclick="user_editFun()" class="easyui-linkbutton" > <i class="fa fa-save"></i>保 存 </button>
		<button type="reset"  class="easyui-linkbutton" class="easyui-linkbutton"> 重 置 </button>
	</div>
</form>
<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
	
});
<c:if test="${not empty user.id}">
$.parser.onComplete=changePaperworkType;
</c:if>

//证件格式校验
function changePaperworkType() {
	var paperworkType = $("#paperworkType","#user_saveFrom").combobox("getValue");
	if("01"==paperworkType) {//居民身份证
		$("#paperworkNo","#user_saveFrom").textbox({
			validType:'idcard'
		});
	} else {
		$("#paperworkNo","#user_saveFrom").textbox({
			validType:''
		});
	}
	$.parser.onComplete=function(){};
}
	
function user_editFun(){
	if($('#user_saveFrom').form('validate')){
		//处理数据
		//处理办公电话
		var officePhoneHead = jQuery("#officePhoneHead").val();
		var officePhoneTail = jQuery("#officePhoneTail").val();
		
		if(""!=officePhoneHead&&""!=officePhoneTail) {
			jQuery("#officePhone","#user_saveFrom").val(officePhoneHead+"-"+officePhoneTail);
		}
		
		$.messager.confirm('提示', '确定保存？', function(r){
			if(r) {
				jQuery('#user_saveFrom').form('submit',{
					onSubmit: function(){
						 return $('#user_saveFrom').form('validate');
					},
				    success: function(data){
				    	var json=jQuery.parseJSON(data);
						if(!jQuery.isEmptyObject(json)){
							var message = json.message;
							var statusCode = json.statusCode;
							if(parseInt(statusCode)==300){
								jQuery.messager.alert("提示信息",message,"error");
							} else if(parseInt(statusCode)==200){
								jQuery.messager.alert("提示信息","保存成功","info");
								jQuery('#'+getCurrentTabId()).panel('refresh');
								closeWindow("showAddDialog");
							}
						}
				    }
				}); 
			}
		});
	}
	else{
		$.messager.alert('提示','请填写必填项！','error');
	}
}
</script>