<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%> 
<form id="dept_saveFrom" name="dept_saveFrom" action="${ctx}/common/dept/departmentSave.do" method="post">
	 	<div class="easyui-panel" title="基础信息" collapsible="true">
		<input type="hidden" name="id" id="id" value="${department.id}"/>
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
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
						<c:when test="${not empty department.logo}">
							<c:set value="${ipanthercore:getJSONMap(department.logo)}" var="map" />
							<img src="${ctx}${map.downloadUrl}" border="0" style="max-width:94px;max-height:84px">
							<textarea  name="logo" style="display:none;">${department.logo}</textarea>
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
				<td ><span style="color:red">*</span>单位名称</td>
				<td>
					<input type="text" class="easyui-textbox" name="deptName" required value="${department.deptName}" validType="deptName"/>
				</td>
			</tr>
			
			<tr>
				<td ><span style="color:red">*</span>单位类型</td>
				<td>
<!-- 					<select id="deptType" name="deptType" class="easyui-combobox" required style="width: 158px;" editable="false" data-options="onSelect:changeDeptType"> -->
					<select id="deptType" name="deptType" class="easyui-combobox" required style="width: 158px;" editable="false" >
								<option value=""></option>
								${dict:getEntryOptionsSelected('DEPT_TYPE',department.deptType)}
					</select>
				</td>
			</tr>
			<tr>
				<td ><span style="color:red">*</span>单位类别</td>
				<td>
					<select id="deptCategory" name="deptCategory" class="easyui-combobox" required style="width: 158px;" editable="false">
								<option value=""></option>
								${dict:getEntryOptionsSelected('DEPT_CATEGORY',department.deptCategory)}
					</select>
				</td>
			</tr>
			<tr>
				<td ><span style="color:red">*</span>单位性质</td>
				<td>
					<select id="deptNature" name="deptNature" class="easyui-combobox" required style="width: 158px;" editable="false">
								<option value=""></option>
								${dict:getEntryOptionsSelected('DEPT_NATURE_TYPE',department.deptNature)}
					</select>
				</td>
			</tr>
			<tr>
				<td ><span style="color:red">*</span>行业分类</td>
				<td >
					<select id="industryType" name="industryType" class="easyui-combobox" required style="width: 158px;" editable="false">
								<option value=""></option>
								${dict:getEntryOptionsSelected('IPR_INDUSTRY_TYPE',department.industryType)}
					</select>
				</td>
				<td><span style="color:red">*</span>主管单位</td>
				<td >
					<input type="text" class="easyui-textbox" name="deptLeader" required value="${department.deptLeader}"/>
				</td>
				
			</tr>
			<tr>
				<td><span style="color:red">*</span>单位地址</td>
				<td colspan="3">
					<input type="text" class="easyui-textbox" name="deptAddress" required style="width: 80%" value="${department.deptAddress}"/>
				</td>
			</tr>
			<tr>
				<td><span style="color:red">*</span>技术领域</td>
				<td>
					<select id="industryType" name="technologyType" class="easyui-combobox" required style="width: 158px;" editable="false">
												<option value=""></option>
												${dict:getEntryOptionsSelected('IPR_TECHNOLOGY_TYPE',department.technologyType)}
					</select>
				</td>
				<td ><span style="color:red">*</span>单位级别</td>
				<td >
					<select id="deptLevel" name="deptLevel" class="easyui-combobox" required style="width: 158px;" editable="false">
												<option value=""></option>
												${dict:getEntryOptionsSelected('TRAIN_ORG_LEVEL',department.deptLevel)}
					</select>	
				</td>
			</tr>
			<tr>
				<td><span style="color:red">*</span>组织机构码</td>
				<td>
					<input type="text" class="easyui-textbox" name="deptCode" required value="${department.deptCode}"/>
				</td>
				<td ><span style="color:red">*</span>单位法人证书号</td>
				<td >
					<input type="text" class="easyui-textbox" name="deptLegalNo"  required value="${department.deptLegalNo}"/>
				</td>
			</tr>
			<tr>
				<td><span style="color:red">*</span>注册资金</td>
				<td colspan="3">
					<input type="text" class="easyui-textbox" name="deptRegisteredFund" required value="${department.deptRegisteredFund}"/>
					万元
					<select id="deptRegisteredFundUnit" name="deptRegisteredFundUnit" class="easyui-combobox" required style="width: 58px;" editable="false">
												<option value=""></option>
												${dict:getEntryOptionsSelected('IPR_MONEY_CODE',department.deptRegisteredFundUnit)}
					</select>
					币别
				</td>
			</tr>
			<tr>
				<td><span style="color:red">*</span>注册地区</td>
				<td colspan="3">
					<select name="province" id="province" class="easyui-validatebox"  style="width: 80px;" editable="false" required></select>
					<select name="city" id="city" class="easyui-validatebox"  style="width: 80px;" editable="false" required></select>
					<select name="counties" id="counties" class="easyui-validatebox"  style="width: 80px;" editable="false" required></select>
					<input type="text" class="easyui-textbox" name="street" id="street" value="${department.street}" style="width:220px"/>
                             <script type="text/javascript">
                             jQuery('#dept_saveFrom').find('#counties').combobox({
  							    url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${department.city}',
  							    valueField:'id',
  							    textField:'text'
  							 });
                              jQuery('#dept_saveFrom').find('#city').combobox({
  								url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${department.province}',
  							    valueField:'id',
  							    textField:'text',
  							    parentField:'pid',
  							    onChange:function(newValue, oldValue) {
  							    	if(newValue!=undefined&&""!=newValue) {
  							    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
  								    	jQuery('#dept_saveFrom').find('#counties').combobox("reload",url);
  								    	jQuery('#dept_saveFrom').find('#counties').combobox('setValue','');
  							    	}
  							    }
  							 });
                              jQuery('#dept_saveFrom').find('#province').combobox({
  								url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${ipanthercommon:getChinaRegionsCode()}',
  							    valueField:'id',
  							    textField:'text',
  							    parentField:'pid',
  							    panelWidth:200,
  							    onLoadSuccess:function(){
  							    	if('${department.province}'!=''){
  							    		jQuery('#dept_saveFrom').find('#province').combobox('setValue','${department.province}');
  									}
  							    	if('${department.city}'!=''){
  							    		jQuery('#dept_saveFrom').find('#city').combobox('setValue','${department.city}');
  									}
  							    	if('${department.counties}'!=''){
  							    		jQuery('#dept_saveFrom').find('#counties').combobox('setValue','${department.counties}');
  									}
  							    },
  							    onChange:function(newValue, oldValue) {
  							    	if(newValue!=undefined&&""!=newValue) {
  							    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
  								    	jQuery('#dept_saveFrom').find('#city').combobox("reload",url);
  								    	jQuery('#dept_saveFrom').find('#city').combobox('setValue','');
  								    	jQuery('#dept_saveFrom').find('#counties').combobox('setValue','');
  							    	}
  							    }
  							}); 
                            </script>
				</td>
			</tr>
			<tr>			
			<!-- 单位名称   单位性质  机构类型 国民经济行业  技术领域 单位级别 主管单位 组织机构代码  单位法人证书号  注册资金（单位：万元） 注册时间 注册地区  单位地址 -->
				<td ><span style="color:red">*</span>注册时间</td>
				<td>
					<input id="deptRegisteredDate" type="text"  class="Wdate" name="deptRegisteredDate" value="<fmt:formatDate value="${department.deptRegisteredDate }" type="date" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker()" required readonly/>
				</td>
				<td ><span style="color:red">*</span>单位预算编号</td>
				<td >
					<input type="text" class="easyui-textbox" name="deptNo" required value="${department.deptNo}"/>
				</td>
			</tr>
			<tr>
				<td><span style="color:red">*</span>税务登记号（国税）</td>
				<td>
					<input type="text" class="easyui-textbox" name="nationTaxNo" value="${department.nationTaxNo}" required />
				</td>
				<td><span style="color:red">*</span>税务登记号（地税）</td>
				<td>
					<input type="text" class="easyui-textbox" name="cityTaxNo" value="${department.cityTaxNo}" required />
				</td>
			</tr>
			<tr>
				<td><span style="color:red">*</span>联系电话</td>
				<td>
					<input type="text" class="easyui-textbox" name="linkPhone" required value="${department.linkPhone}" />
				</td>
				<td><span style="color:red">*</span>传真电话</td>
				<td>
					<input type="text" class="easyui-textbox" name="linkFax" required value="${department.linkFax}"/>
				</td>
			</tr>
			<tr>
				<td><span style="color:red">*</span>电子信箱</td>
				<td>
					<input type="text" class="easyui-textbox" name="linkEmail" required value="${department.linkEmail}" validType="email"/>
				</td>
				<td><span style="color:red">*</span>单位主页</td>
				<td>
					<input type="text" class="easyui-textbox" name="homePage" required value="${department.homePage}"/>
				</td>
			</tr>
			
			<tr class="deptType_12">
				<td><span style='color:red'>*</span>基地级别</td>
				<td>
					<select id="trainOrgLevel" name="trainOrgLevel" class="easyui-combobox" required style="width: 158px;" editable="false">
								<option value=""></option>
								${dict:getEntryOptionsSelected('TRAIN_ORG_LEVEL',department.trainOrgLevel)}
					</select>
				</td>
				<td>基地特色</td>
				<td>
					<input type="text" class="easyui-textbox" id="trainOrgFeature" name="trainOrgFeature" value="${department.trainOrgFeature}"/>
				</td>
			</tr>
		</tbody>
	</table>
	<div style="text-align: center">
		<button type="button" onclick="user_editSave()" class="easyui-linkbutton" > <i class="fa fa-save"></i>保 存 </button>
		<button type="reset"  class="easyui-linkbutton" class="easyui-linkbutton"> 重 置 </button>
	</div>
</form>
<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
	
});


// //证件格式校验
// function changePaperworkType() {
// 	var paperworkType = $("#paperworkType","#dept_saveFrom").combobox("getValue");
// 	if("01"==paperworkType) {//居民身份证
// 		$("#paperworkNo","#dept_saveFrom").textbox({
// 			validType:'idcard'
// 		});
// 	} else {
// 		$("#paperworkNo","#dept_saveFrom").textbox({
// 			validType:''
// 		});
// 	}
// 	$.parser.onComplete=function(){};
// }
	
function user_editSave(){
	if($('#dept_saveFrom').form('validate')){
		//处理数据
		//处理办公电话	
		$.messager.confirm('提示', '确定保存？', function(r){
			if(r) {
				jQuery('#dept_saveFrom').form('submit',{
					onSubmit: function(){
						 return $('#dept_saveFrom').form('validate');
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
								//closeWindow("showAddDialog");
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