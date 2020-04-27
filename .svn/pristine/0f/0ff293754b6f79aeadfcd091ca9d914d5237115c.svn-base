<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>
<form id="teacherForm" name="teacherForm" action="${ctx}/common/user/teacher/save.do" method="post" enctype="multipart/form-data">
	<div class="easyui-panel" title="基础信息" collapsible="true">
		<input type="hidden" name="id" id="id" value="${user.id}"/>
		<input type="hidden" name="userId" id="userId" value="${user.id}"/>
		<input type="hidden" name="roleCode" id="roleCode" value="${user.roleCode}"/>
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td><span style="color:red">*</span>用户名/账号/学号</td>
				<td>
					<input type="text" class="easyui-textbox" name="userName" required value="${user.userName}" validType="username"/>
				</td>
				<td rowspan="4">个人头像</td>
				<td rowspan="4" style="vertical-align: bottom;">
					<c:choose>
						<c:when test="${not empty user.avatar}">
							<c:set value="${ipanthercore:getJSONMap(user.avatar)}" var="map" />
							<img src="${ctx}${map.downloadUrl}" border="0" style="max-width: 120px;max-height:160px">
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
					<input type="text" class="easyui-textbox" name="realName" required value="${user.realName}"/>
				</td>
			</tr>
			<tr>
				<td><span style="color:red">*</span>证件类型</td>
				<td>
					<select id="paperworkType" name="paperworkType" class="easyui-combobox" required style="width: 158px;" editable="false" data-options="onSelect:changePaperworkType">
						<option value=""></option>
						${dict:getEntryOptionsSelected('PAPERWORK_TYPE',user.paperworkType) }
					</select>
				</td>
			</tr>
			<tr>
				<td><span style="color:red">*</span>证件号</td>
				<td>
					<input type="text" class="easyui-textbox" name="paperworkNo" id="paperworkNo" value="${user.paperworkNo}" data-options="required:true,missingMessage:'请输入证证件号'"/>
				</td>
			</tr>
			<tr>
				<td>学员类别</td>
				<td colspan="3">
					<select name="userType" id="userType" class="easyui-validatebox"  style="width:300px;" required></select>
                             <script type="text/javascript">
                             jQuery("#teacherForm").find("#userType").combotree({
								data: ${dict:getEntryListJsonOpen("USER_TYPE","true")}, 
								id:'dictValue',
								textField:'dictName',
								parentField:'pid',
								panelHeight:'200',
								checkbox:true,
								onlyLeafCheck:true,
								multiple:true,
								onLoadSuccess:function(){
									var value='${userStaff.userType}';
									value = value.replace(/\"/g,'').replace(/\[/g,'').replace(/\]/g,'');
									if(value!=''){
										  jQuery("#teacherForm").find("#userType").combotree('setValues',value);
									}
								 }
							}); 
                            </script>
				</td>
			</tr>
			<%--非主管部门时不必显示所在单位 --%>
			<c:choose>
				<c:when test="${!ipanthercommon:isAdminCompetent(sessionScope.loginUser.deptType)}">
					<c:set var="deptId" value="${sessionScope.loginUser.deptId}"/>
					<input type="hidden" id="deptId" name="deptId" value="${deptId}"/>
					
					<%--个人机构，需展示用户自己填写所属机构 --%>
					<c:if test="${ipanthercommon:isVirtualOrg(deptId)}">
						<tr class="isVirtual_1">
							<td><span style="color:red">*</span>所属单位名称</td>
							<td colspan="3">
								<input type="text" class="easyui-textbox" id="belongDeptName" name="belongDeptName" required value="${user.belongDeptName}"/>
							</td>
						</tr>
					</c:if>
				</c:when>
				<c:otherwise>
					<tr>
						<td><span style="color:red">*</span>所在单位</td>
						<td colspan="3">
							<select id="regionsCode" name="regionsCode" style="width:95px" required></select> 
							<select id="deptId" name="deptId" style="width:200px" required editable="false" ></select>
							<script type="text/javascript">
								jQuery('#teacherForm').find('#deptId').combobox({    
								    url:'${ctx}/common/dept/getDeptByRegionsCode.do?regionsCode=${user.regionsCode}',
								    valueField:'id',
								    textField:'deptName',
								    onChange:function(newValue, oldValue) {
								    	//判断是否需要输入所属单位名称
								    	if(newValue!=undefined&&""!=newValue) {
								    		var deptJson=$.ajaxSubmitValue('${ctx }/common/dept/getDeptByDeptId.do?id='+newValue);
								    		var dept=jQuery.parseJSON(deptJson);
								    		var isVirtual = dept.isVirtual;
								    		if(isVirtual=="1") {//其它机构，展示单位名称让用户填写
								    			$("#belongDeptName","#teacherForm").textbox();
								    			$("#belongDeptName","#teacherForm").textbox("enableValidation");
								    			$(".isVirtual_1").show();
								    		} else {
								    			$("#belongDeptName","#teacherForm").textbox();
								    			$("#belongDeptName","#teacherForm").textbox("setValue","");
								    			$("#belongDeptName","#teacherForm").textbox("disableValidation");
								    			$(".isVirtual_1").hide();
								    		}
								    	}
								    }
								});
								
								jQuery('#teacherForm').find('#regionsCode').combotree({
								    data: jQuery.parseJSON('${ipanthercommon:getRegionsJson(true)}'),
								    valueField:'id',    
								    textField:'text',
								    parentField:'pid',
								    panelWidth:200,
								    onLoadSuccess:function(){
								    	if('${user.regionsCode}'!=''){
								    		jQuery('#teacherForm').find('#regionsCode').combotree('setValue','${user.regionsCode}');
								    		if('${user.deptId}'!=''){
									    		jQuery('#teacherForm').find('#deptId').combobox('setValue','${user.deptId}');
									    	}
										}
								    },
								    onChange:function(newValue, oldValue) {
								    	if(newValue!=undefined&&""!=newValue) {
								    		var url = '${ctx}/common/dept/getDeptByRegionsCode.do?regionsCode='+newValue;
									    	jQuery('#teacherForm').find('#deptId').combobox("reload",url);
									    	jQuery('#teacherForm').find('#deptId').combobox('setValue','');
								    	}
								    }
								}); 
							</script>
						</td>
					</tr>
					<tr class="isVirtual_1">
						<td><span style="color:red">*</span>所属单位名称</td>
						<td colspan="3">
							<input type="text" class="easyui-textbox" id="belongDeptName" name="belongDeptName" required value="${user.belongDeptName}"/>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
			<tr>
				<td width="20%">出生日期</td>
				<td width="30%">
					<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM'})"class="easyui-validatebox Wdate" name="bornDate" value="${user.bornDate}"/>
				</td>
				<td width="20%"><span style="color:red">*</span>密码</td>
				<td width="30%">
					<input type="password" class="easyui-textbox" name="passwordPlain" required value="${user.passwordPlain}" />
				</td>
			</tr>
			<tr>
				<td>密码提示问题</td>
				<td>
					<input type="text" class="easyui-textbox" name="passwordAsk" value="${user.passwordAsk}"/>
				</td>
				<td>密码答案</td>
				<td>
					<input type="text" class="easyui-textbox" name="passwordAnswer" value="${user.passwordAnswer}"/>
				</td>
			</tr>
			<tr>
				<td>性别</td>
				<td>
					<select name="sex" class="easyui-combobox" style="width: 158px;" editable="false">
						<option value=""></option>
						${dict:getEntryOptionsSelected('SEX_TYPE',user.sex) }
					</select>
				</td>
				<td>政治面貌</td>
				<td>
					<select id="politicsRole" name="politicsRole" class="easyui-combobox" style="width: 158px;" editable="false">
						<option value=""></option>
						${dict:getEntryOptionsSelected('ZZMM',user.politicsRole) }
					</select>
				</td>
			</tr>
			<tr>
				<td>国籍/地区</td>
				<td>
					<select id="country" name="country" class="easyui-combobox" style="width: 158px;" editable="false">
						<option value=""></option>
						${dict:getEntryOptionsSelected('GJDQ',user.country) }
					</select>
				</td>
				<td>籍贯</td>
				<td>
					<select id="hometownProvince" name="hometownProvince" style="width:80px" editable="false"></select>
					<select id="hometownCity" name="hometownCity" style="width:80px" editable="false"></select>
					<script type="text/javascript">
							jQuery('#teacherForm').find('#hometownCity').combobox({
							    url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${user.hometownProvince}',
							    valueField:'id',
							    textField:'text'
							});
							jQuery('#teacherForm').find('#hometownProvince').combobox({
								url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${ipanthercommon:getChinaRegionsCode()}',
							    valueField:'id',
							    textField:'text',
							    parentField:'pid',
							    panelWidth:200,
							    onLoadSuccess:function(){
							    	if('${user.hometownProvince}'!=''){
							    		jQuery('#teacherForm').find('#hometownProvince').combobox('setValue','${user.hometownProvince}');
									}
							    	if('${user.hometownCity}'!=''){
							    		jQuery('#teacherForm').find('#hometownCity').combobox('setValue','${user.hometownCity}');
							    	}
							    },
							    onChange:function(newValue, oldValue) {
							    	if(newValue!=undefined&&""!=newValue) {
							    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
								    	jQuery('#teacherForm').find('#hometownCity').combobox("reload",url);
								    	jQuery('#teacherForm').find('#hometownCity').combobox('setValue','');
							    	}
							    }
								
							}); 
					</script>
				</td>
			</tr>
			<tr>
				<td>目前所在地区</td>
				<td colspan="3">
					<select name="currentProvince" id="currentProvince" class="easyui-validatebox"  style="width: 80px;" editable="false"></select>
					<select name="currentCity" id="currentCity" class="easyui-validatebox"  style="width: 80px;" editable="false"></select>
					<select name="currentCounties" id="currentCounties" class="easyui-validatebox"  style="width: 80px;" editable="false"></select>
					<input type="text" class="easyui-textbox" name="currentStreet" id="currentStreet" value="${user.currentStreet}" style="width:220px"/>
                             <script type="text/javascript">
                             jQuery('#teacherForm').find('#currentCounties').combobox({
  							    url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${user.currentCity}',
  							    valueField:'id',
  							    textField:'text'
  							 });
                              jQuery('#teacherForm').find('#currentCity').combobox({
  								url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${user.currentProvince}',
  							    valueField:'id',
  							    textField:'text',
  							    parentField:'pid',
  							    onChange:function(newValue, oldValue) {
  							    	if(newValue!=undefined&&""!=newValue) {
  							    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
  								    	jQuery('#teacherForm').find('#currentCounties').combobox("reload",url);
  								    	jQuery('#teacherForm').find('#currentCounties').combobox('setValue','');
  							    	}
  							    }
  							 });
                              jQuery('#teacherForm').find('#currentProvince').combobox({
  								url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${ipanthercommon:getChinaRegionsCode()}',
  							    valueField:'id',
  							    textField:'text',
  							    parentField:'pid',
  							    panelWidth:200,
  							    onLoadSuccess:function(){
  							    	if('${user.currentProvince}'!=''){
  							    		jQuery('#teacherForm').find('#currentProvince').combobox('setValue','${user.currentProvince}');
  									}
  							    	if('${user.currentCity}'!=''){
  							    		jQuery('#teacherForm').find('#currentCity').combobox('setValue','${user.currentCity}');
  									}
  							    	if('${user.currentCounties}'!=''){
  							    		jQuery('#teacherForm').find('#currentCounties').combobox('setValue','${user.currentCounties}');
  									}
  							    },
  							    onChange:function(newValue, oldValue) {
  							    	if(newValue!=undefined&&""!=newValue) {
  							    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
  								    	jQuery('#teacherForm').find('#currentCity').combobox("reload",url);
  								    	jQuery('#teacherForm').find('#currentCity').combobox('setValue','');
  								    	jQuery('#teacherForm').find('#currentCounties').combobox('setValue','');
  							    	}
  							    }
  							}); 
                            </script>
				</td>
			</tr>
			<tr>
				<td>参加工作时间</td>
				<td>
					<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="easyui-validatebox Wdate" name="workDate" value="<fmt:formatDate value="${user.workDate}" pattern="yyyy-MM-dd"/>"/>
				</td>
				<td>职务</td>
				<td>
					<input type="text" class="easyui-textbox" name="job" value="${user.job}"/>
				</td>
			</tr>
			<tr>
				<td>专家类别</td>
				<td>
					<select id="expertType" name="expertType" class="easyui-combotree" checkbox="true" style="width: 158px;" editable="false">
					</select>
							<script type="text/javascript">
                             jQuery("#teacherForm").find("#expertType").combotree({
								data: ${dict:getEntryListJsonOpen("EXPERT_TYPE","true")}, 
								checkbox:true,
								multiple:true,
								onLoadSuccess:function(){
									var value='${userStaff.expertType}';
									value = value.replace(/\"/g,'').replace(/\[/g,'').replace(/\]/g,'');
									if(value!=''){
										  jQuery("#teacherForm").find("#expertType").combotree('setValues',value);
									}
								 }
							}); 
                            </script>
				</td>
				<td>专家级别</td>
				<td>
					<select id="expertLevel" name="expertLevel" class="easyui-combotree" style="width: 158px;" editable="false">
					</select>
							<script type="text/javascript">
                             jQuery("#teacherForm").find("#expertLevel").combotree({
								data: ${dict:getEntryListJsonOpen("EXPERT_LEVEL","true")}, 
								checkbox:true,
								multiple:true,
								onLoadSuccess:function(){
									var value='${userStaff.expertLevel}';
									value = value.replace(/\"/g,'').replace(/\[/g,'').replace(/\]/g,'');
									if(value!=''){
										  jQuery("#teacherForm").find("#expertLevel").combotree('setValues',value);
									}
								 }
							}); 
                            </script>
				</td>
			</tr>
			
			<tr>
				<td>研究领域</td>
				<td>
					<input type="text" class="easyui-textbox" name="researchField" value="${userStaff.researchField}"/>
				</td>
				<td>研究专长</td>
				<td>
					<input type="text" class="easyui-textbox" name="researchSpecial" value="${userStaff.researchSpecial}"/>
				</td>
			</tr>
			
			<tr>
				<td>办公电话</td>
				<td>
					<c:set var="officePhoneArr" value="${fn:split(user.officePhone, '-') }"/>
					<input type="text" class="easyui-textbox" id="officePhoneHead" style="width:40px" value="${officePhoneArr[0]}"/>
					-
					<input type="text" class="easyui-textbox" id="officePhoneTail" style="width:80px"  value="${officePhoneArr[1]}"/>
					<input type="hidden" id="officePhone" name="officePhone" value="${user.officePhone}"/>
				</td>
				<td>职称</td>
				<td>
					<input type="text" class="easyui-textbox" name="title" value="${userStaff.title}"/>
				</td>
			</tr>
			<tr>
				<td>移动电话</td>
				<td>
					<input type="text" class="easyui-textbox" name="mobilePhone" value="${user.mobilePhone}" validType="mobilePhone"/>
				</td>
				<td>电子信箱</td>
				<td>
					<input type="text" class="easyui-textbox" name="email" value="${user.email}" validType="email"/>
				</td>
			</tr>
			<tr>
				<td>个人简介</td>
				<td colspan="3">
					<textarea class="easyui-validatebox" validType="length[0,500]" name="introduce" rows="2" style="width: 98%">${user.introduce}</textarea>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
	
	<div class="easyui-panel" title="学历信息" collapsible="true">
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="20%">学历</td>
				<td width="30%">
					<select id="expertLevel" name="highDiploma" class="easyui-combobox" style="width: 158px;" editable="false">
						<option value=""></option>
						${dict:getEntryOptionsSelected('DIPLOMA_TYPE',userStaff.highDiploma) }
					</select>
				</td>
				<td width="20%">所学专业</td>
				<td width="30%">
					<input type="text" class="easyui-textbox" name="highSubject" value="${userStaff.highSubject}"/>
				</td>
			</tr>
			<tr>
				<td>学位</td>
				<td>
					<select id="highDegree" name="highDegree" class="easyui-combobox" style="width: 158px;" editable="false">
						<option value=""></option>
						${dict:getEntryOptionsSelected('DEGREE_TYPE',userStaff.highDegree) }
					</select>
				</td>
				<td>授予学位单位名称</td>
				<td>
					<input type="text" class="easyui-textbox" name="highDegreeUnit" value="${userStaff.highDegreeUnit}"/>
				</td>
			</tr>
			<tr>
				<td>获学位日期</td>
				<td>
					<input type="text" class="easyui-validatebox Wdate" name="highDegreeDt" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value='<fmt:formatDate value="${userStaff.highDegreeDt}" pattern="yyyy-MM-dd"/>'/>
				</td>
				<td>毕业时间</td>
				<td>
					<input type="text" class="easyui-validatebox Wdate" name="highDt" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<fmt:formatDate value="${userStaff.highDt}" pattern="yyyy-MM-dd"/>"/>
				</td>
			</tr>
			<tr>
				<td>毕肄业学校或单位</td>
				<td colspan="3">
					<input type="text" class="easyui-textbox" name="highCollege" value="${userStaff.highCollege}"/>
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	<div class="easyui-panel" title="任职资格信息" collapsible="true">
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="20%">外国语种</td>
				<td width="30%">
					<select id="foreignLanguages" name="foreignLanguages" class="easyui-combobox" style="width: 158px;" editable="false">
						<option value=""></option>
						${dict:getEntryOptionsSelected('YZMC',userStaff.foreignLanguages) }
					</select>
				</td>
				<td width="20%">外国语种熟练程度</td>
				<td width="30%">
					<select id="foreignDegree" name="foreignDegree" class="easyui-combobox" style="width: 158px;" editable="false">
						<option value=""></option>
						${dict:getEntryOptionsSelected('WGYZSLCD',userStaff.foreignDegree) }
					</select>
				</td>
			</tr>
			<tr>
				<td>计算机水平</td>
				<td>
					<select id="computerLevel" name="computerLevel" class="easyui-combobox" style="width: 158px;" editable="false">
						<option value=""></option>
						${dict:getEntryOptionsSelected('COMPUTER_LEVEL',userStaff.computerLevel) }
					</select>
				</td>
				<td>最高专业技术资格名称</td>
				<td>
					<input type="text" class="easyui-textbox" name="proName" value="${userStaff.proName}"/>
				</td>
			</tr>
			<tr>
				<td>评定日期</td>
				<td>
					<input type="text" class="easyui-validatebox Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="proDt" value="<fmt:formatDate value="${userStaff.proDt}" pattern="yyyy-MM-dd"/>"/>
				</td>
				<td>现聘专业技术职务</td>
				<td>
					<input type="text" class="easyui-textbox" name="proJob" value="${userStaff.proJob}"/>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
	
	<div class="easyui-panel" title="师资信息" collapsible="true">
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="20%">培训领域</td>
				<td width="30%">
					<input type="text" class="easyui-textbox" name="trainDomain" value="${userStaff.trainDomain}"/>
				</td>
				<td width="20%">培训方向</td>
				<td width="30%">
					<input type="text" class="easyui-textbox" name="trainDirection" value="${userStaff.trainDirection}"/>
				</td>
			</tr>
			
			<tr>
				<td>主要学术著作及论文</td>
				<td>
					<input type="text" class="easyui-textbox" name="mainThesis" value="${userStaff.mainThesis}"/>
				</td>
				<td>收款账号</td>
				<td>
					<input type="text" class="easyui-textbox" name="bankAccount" value="${userStaff.bankAccount}"/>
				</td>
			</tr>
			<tr>
				<td>是否有知识产权教学或实务工作经验</td>
				<td colspan="3">
					<select id="haveExp" name="haveExp" class="easyui-combobox" style="width: 158px;" editable="false">
						<option value=""></option>
						${dict:getEntryOptionsSelected('HAVE_EXP',userStaff.haveExp) }
					</select>
				</td>
			</tr>
			<tr>
				<td>从事知识产权教学或授课的相关经历</td>
				<td colspan="3">
					<textarea rows="2" style="width: 98%" name="expDesc" id="expDesc">${userStaff.expDesc}</textarea>
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	<div style="text-align: center">
		<button type="button" onclick="saveUser()" class="easyui-linkbutton"  > <i class="fa fa-save"></i>保 存 </button>
		<button type="reset"  class="easyui-linkbutton" class="easyui-linkbutton"> 重 置 </button>
	</div>
</form>
<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
	
});
<c:if test="${not empty user.id}">
$.parser.onComplete=changePaperworkType;
$.parser.onComplete=changeDept;
</c:if>
//证件格式校验
function changePaperworkType() {
	var paperworkType = $("#paperworkType","#teacherForm").combobox("getValue");
	if("01"==paperworkType) {//居民身份证
		$("#paperworkNo","#teacherForm").textbox({
			validType:'idcard'
		});
	} else {
		$("#paperworkNo","#teacherForm").textbox({
			validType:''
		});
	}
}

function saveUser() {
	if(!$('#teacherForm').form('validate')) {
		return false;
	}
	$.messager.confirm('提示', '确定保存？', function(r){
		if(r) {
			//处理数据
			//处理办公电话
			var officePhoneHead = jQuery("#officePhoneHead").val();
			var officePhoneTail = jQuery("#officePhoneTail").val();
			if(""!=officePhoneHead&&""!=officePhoneTail) {
				jQuery("#officePhone","#teacherForm").val(officePhoneHead+"-"+officePhoneTail);
			}
			$("#teacherForm").ajaxSubmit({ 
				type: 'post',  
				success: function(json){
					if(!$.isEmptyObject(json)){
						var responseMsg = json.message;
						var responseCode = json.statusCode;
						if("200"==responseCode) {//成功
							jQuery.messager.alert("提示信息","保存成功","info",function() {
								jQuery('#${param.parentTabId}').panel('refresh');
								$('#showAddDialog').window({
									  title:'修改师资信息',
									  href:'${ctx}/common/user/teacher/editTabs.do?parentTabId=${param.parentTabId}&id='+responseMsg
								});
							});
						} else {//错误
							jQuery.messager.alert("提示信息",responseMsg,"error");
						}
					}
				}
			});
		}
		
	});
}

function changeDept() {
	var deptJson=$.ajaxSubmitValue('${ctx }/common/dept/getDeptByDeptId.do?id=${user.deptId}');
	var dept=jQuery.parseJSON(deptJson);
	var isVirtual = dept.isVirtual;
	if(isVirtual=="1") {//其它机构，展示单位名称让用户填写
		$("#belongDeptName","#teacherForm").textbox();
		$("#belongDeptName","#teacherForm").textbox("enableValidation");
		$(".isVirtual_1").show();
		//console.log("load show");
	} else {
		$("#belongDeptName","#teacherForm").textbox();
		$("#belongDeptName","#teacherForm").textbox("setValue","");
		$("#belongDeptName","#teacherForm").textbox("disableValidation");
		$(".isVirtual_1").hide();
		//console.log("load hide");
	}
	$.parser.onComplete=function(){};
}
</script>
