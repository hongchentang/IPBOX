<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%> 

<form id="myInfoForm" name="myInfoForm" action="${ctx}/common/user/changeUserInfoSave.do" method="post">
	<input type="hidden" name="user.id" id="id" value="${user.id}"/>
	 <input type="hidden" name="isAdmin" id="isAdmin" value="false"/>
	<input type="hidden" name="user.deptId" id="deptId" value="${user.deptId}"/>
	 <table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td >用户名</td>
				<td colspan="3">
					${user.userName}
				</td>
			</tr>
			<tr>
				<td width="20%"><span style="color:red">*</span>姓名</td>
				<td width="30%">
					<input type="text" class="easyui-textbox" name="user.realName" required value="${user.realName}"/>
				</td>
				<td width="20%">出生日期</td>
				<td width="30%">
					<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM'})"class="easyui-validatebox Wdate" name="user.bornDate" value="${user.bornDate}"/>
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
						<td>电子信箱</td>
				<td colspan="3">
					<input type="text" class="easyui-textbox" name="user.email" value="${user.email}" validType="email"/>
				</td>

			</tr>
			<tr>
				<td>目前所在地址</td>
				<td colspan="3">
					<select name="user.currentProvince" id="currentProvince" class="easyui-validatebox"  style="width: 80px;" editable="false"></select>
					<select name="user.currentCity" id="currentCity" class="easyui-validatebox"  style="width: 80px;" editable="false"></select>
					<select name="user.currentCounties" id="currentCounties" class="easyui-validatebox"  style="width: 80px;" editable="false"></select>
					<input type="text" class="easyui-textbox" name="user.currentStreet" id="currentStreet" value="${user.currentStreet}" style="width:220px"/>
                             <script type="text/javascript">
                             jQuery('#myInfoForm').find('#currentCounties').combobox({
  							    url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${user.currentCity}',
  							    valueField:'id',
  							    textField:'text'
  							 });
                              jQuery('#myInfoForm').find('#currentCity').combobox({
  								url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${user.currentProvince}',
  							    valueField:'id',
  							    textField:'text',
  							    parentField:'pid',
  							    onChange:function(newValue, oldValue) {
  							    	if(newValue!=undefined&&""!=newValue) {
  							    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
  								    	jQuery('#myInfoForm').find('#currentCounties').combobox("reload",url);
  								    	jQuery('#myInfoForm').find('#currentCounties').combobox('setValue','');
  							    	}
  							    }
  							 });
                              jQuery('#myInfoForm').find('#currentProvince').combobox({
  								url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${ipanthercommon:getChinaRegionsCode()}',
  							    valueField:'id',
  							    textField:'text',
  							    parentField:'pid',
  							    panelWidth:200,
  							    onLoadSuccess:function(){
  							    	if('${user.currentProvince}'!=''){
  							    		jQuery('#myInfoForm').find('#currentProvince').combobox('setValue','${user.currentProvince}');
  									}
  							    	if('${user.currentCity}'!=''){
  							    		jQuery('#myInfoForm').find('#currentCity').combobox('setValue','${user.currentCity}');
  									}
  							    	if('${user.currentCounties}'!=''){
  							    		jQuery('#myInfoForm').find('#currentCounties').combobox('setValue','${user.currentCounties}');
  									}
  							    },
  							    onChange:function(newValue, oldValue) {
  							    	if(newValue!=undefined&&""!=newValue) {
  							    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
  								    	jQuery('#myInfoForm').find('#currentCity').combobox("reload",url);
  								    	jQuery('#myInfoForm').find('#currentCity').combobox('setValue','');
  								    	jQuery('#myInfoForm').find('#currentCounties').combobox('setValue','');
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
<%-- 			<tr>
				<td>电子信箱</td>
				<td colspan="3">
					<input type="text" class="easyui-textbox" name="user.email" value="${user.email}" validType="email"/>
				</td>
			</tr> --%>
		</tbody>
	</table>
	
	<div style="text-align: center; margin-top: 20px;">
		<button type="button" onclick="changeMyInfo()" class="easyui-linkbutton" > <i class="fa fa-save"></i>保 存 </button>
		<button class="easyui-linkbutton" type="button" onclick="javascript:closeWindow('editUserInfoWindow')"><i class="fa fa-times"></i>关闭</button>
	</div>
</form>
<script type="text/javascript">
tableVBg('.alter-table-v');

function changeMyInfo(){
	if($('#myInfoForm').form('validate')){
		//处理数据
		//处理办公电话
		var officePhoneHead = jQuery("#officePhoneHead").val();
		var officePhoneTail = jQuery("#officePhoneTail").val();
		
		if(""!=officePhoneHead&&""!=officePhoneTail) {
			jQuery("#officePhone","#myInfoForm").val(officePhoneHead+"-"+officePhoneTail);
		}
		$.messager.confirm('提示', '确定保存？', function(r){
			if(r) {
				jQuery('#myInfoForm').form('submit',{
					onSubmit: function(){
						 return $('#myInfoForm').form('validate');
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
								closeWindow("editUserInfoWindow");
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