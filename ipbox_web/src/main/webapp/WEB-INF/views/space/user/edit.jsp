<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/include/taglib.jsp" %>
<%--
用户编辑说明：
1，只要是type为教师或者学员，那么教师学员独有的内容都会放出来，但是不一定必填；
2，只有请求类型为注册成为教师或者学员时，教师学员独有的内容才会要求必填；
3，当用于除于审核流程中时，包括学员和教师审核，都不允许改变用户的信息
 --%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>
<%--教师独有的内容是否需要必填标识 --%>
<c:set var="teacherRequired" value="false"/>
<c:set var="teacherRequiredSpan" value=""/>
<c:if test="${param.registerType eq 'teacher' or user.teacherStatus eq '03'}">
	<c:set var="teacherRequired" value="required"/>
	<c:set var="teacherRequiredSpan" value="<span style='color:red'>*</span>"/>
</c:if>
<form id="userForm" name="userForm" action="${ctx}/space/user/save.do" method="post" enctype="multipart/form-data">
		<div class="easyui-panel" title="基础信息" collapsible="true" >
			<input type="hidden" name="id" id="id" value="${user.id}"/>
			<input type="hidden" name="userId" id="userId" value="${user.id}"/>
			<input type="hidden" name="roleCode" id="roleCode" value="${user.roleCode}"/>
			<input type="hidden" name="type" id="type" value="${user.type}"/>
			<%--标记用户当前保存的注册类型，空为不注册，只是保存，student为学员，teacher为教师 --%>
			<input type="hidden" name="registerType" id="registerType" value="${param.registerType}"/>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="alter-table-v-space">
			<tbody>
				<tr>
					<td><span style="color:red">*</span>用户名/账号/学号</td>
					<td>
						${user.userName}
					</td>
					<td rowspan="4">个人头像</td>
					<td rowspan="4" style="vertical-align: bottom;">
						<c:choose>
							<c:when test="${not empty user.avatar}">
								<c:set value="${ipanthercore:getJSONMap(user.avatar)}" var="map" />
								<img id="ImgPr" src="${ctx}${map.downloadUrl}" border="0" style="max-width:120px;max-height:120px">
								<textarea  name="logo" style="display:none;">${user.avatar}</textarea>
							</c:when>
							<c:otherwise>
								<img id="ImgPr" src="${ctx}/themes/easyui/themes/tms/images/default.jpg" border="0" style="max-width:120px;max-height:120px">
							</c:otherwise>
						</c:choose>
						<div>
							<input id="up" type="file" name="upload" content="<span style='color:red'>尺寸在120px*160px之内<br/>允许上传的文件类型：${fileTypes}<br/>允许上传文件的大小：${fileMaxSize}KB</span>" class="easyui-tooltip" position="left"/>
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
					<td><span style="color:red">*</span>学员类别</td>
					<td colspan="3">
						<select name="userType" id="userType" class="easyui-validatebox"  style="width:300px;" required></select>
	                             <script type="text/javascript">
	                             jQuery("#userForm").find("#userType").combotree({
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
											  jQuery("#userForm").find("#userType").combotree('setValues',value);
										}
									 }
								}); 
	                            </script>
					</td>
				</tr>
				<tr class="isVirtual_1">
					<td><span style="color:red">*</span>所属单位名称</td>
					<td colspan="3">
						<input type="hidden" id="deptId" name="deptId" value="${sessionScope.loginUser.deptId}"/>
						<input type="text" class="easyui-textbox" id="belongDeptName" name="belongDeptName" required value="${user.belongDeptName}" style="width:300px;"/>
					</td>
				</tr>
				<tr>
					<td>人才状态</td>
					<td>
						<c:choose>
							<c:when test="${empty user.studentStatus}">
								<span style="color:gray;">未申请</span>
							</c:when>
							<c:otherwise>
								<span style="color:${user.studentStatus eq '03'?'green':'red'}">
									${dict:getEntryName('STUDENT_STATUS',user.studentStatus)}
								</span>
							</c:otherwise>
						</c:choose>
					</td>
					<td>教师状态</td>
					<td>
						<c:choose>
							<c:when test="${empty user.teacherStatus}">
								<span style="color:gray;">未申请</span>
							</c:when>
							<c:otherwise>
								<span style="color:${user.teacherStatus eq '03'?'green':'red'}">
									${dict:getEntryName('TEACHER_STATUS',user.teacherStatus)}
								</span>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td width="20%"><span style="color:red">*</span>出生日期</td>
					<td width="30%">
						<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM'})"class="easyui-validatebox Wdate" name="bornDate" value="${user.bornDate}" required/>
					</td>
					<td width="20%"><%--<span style="color:red">*</span>密码--%></td>
					<td width="30%">
						<%--<input type="password" class="easyui-textbox" name="passwordPlain" required value="${user.passwordPlain}" />--%>
					</td>
				</tr>
				<tr>
					<td><span style="color:red">*</span>密码提示问题</td>
					<td>
						<input type="text" class="easyui-textbox" name="passwordAsk" value="${user.passwordAsk}" required/>
					</td>
					<td><span style="color:red">*</span>密码提示问题答案</td>
					<td>
						<input type="text" class="easyui-textbox" name="passwordAnswer" value="${user.passwordAnswer}" required/>
					</td>
				</tr>
				<tr>
					<td><span style="color:red">*</span>性别</td>
					<td>
						<select name="sex" class="easyui-combobox" style="width: 158px;" editable="false" required>
							<option value=""></option>
							${dict:getEntryOptionsSelected('SEX_TYPE',user.sex) }
						</select>
					</td>
					<td><span style="color:red">*</span>政治面貌</td>
					<td>
						<select id="politicsRole" name="politicsRole" class="easyui-combobox" style="width: 158px;" editable="false" required>
							<option value=""></option>
							${dict:getEntryOptionsSelected('ZZMM',user.politicsRole) }
						</select>
					</td>
				</tr>
				<tr>
					<td><span style="color:red">*</span>国籍/地区</td>
					<td>
						<select id="country" name="country" class="easyui-combobox" style="width: 158px;" editable="false" required>
							<option value=""></option>
							${dict:getEntryOptionsSelected('GJDQ',user.country) }
						</select>
					</td>
					<td><span style="color:red">*</span>籍贯</td>
					<td>
						<select id="hometownProvince" name="hometownProvince" style="width:80px" editable="false" required></select>
						<select id="hometownCity" name="hometownCity" style="width:80px" editable="false" required></select>
						<script type="text/javascript">
								jQuery('#userForm').find('#hometownCity').combobox({
								    url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${user.hometownProvince}',
								    valueField:'id',
								    textField:'text'
								});
								jQuery('#userForm').find('#hometownProvince').combobox({
									url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${ipanthercommon:getChinaRegionsCode()}',
								    valueField:'id',
								    textField:'text',
								    parentField:'pid',
								    panelWidth:200,
								    onLoadSuccess:function(){
								    	if('${user.hometownProvince}'!=''){
								    		jQuery('#userForm').find('#hometownProvince').combobox('setValue','${user.hometownProvince}');
										}
								    	if('${user.hometownCity}'!=''){
								    		jQuery('#userForm').find('#hometownCity').combobox('setValue','${user.hometownCity}');
								    	}
								    },
								    onChange:function(newValue, oldValue) {
								    	if(newValue!=undefined&&""!=newValue) {
								    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
									    	jQuery('#userForm').find('#hometownCity').combobox("reload",url);
									    	jQuery('#userForm').find('#hometownCity').combobox('setValue','');
								    	}
								    }
									
								}); 
						</script>
					</td>
				</tr>
				<tr>
					<td><span style="color:red">*</span>目前所在地区</td>
					<td colspan="3">
						<select name="currentProvince" id="currentProvince" class="easyui-validatebox"  style="width: 80px;" editable="false" required></select>
						<select name="currentCity" id="currentCity" class="easyui-validatebox"  style="width: 80px;" editable="false" required></select>
						<select name="currentCounties" id="currentCounties" class="easyui-validatebox"  style="width: 80px;" editable="false" required></select>
						<input type="text" class="easyui-textbox" name="currentStreet" id="currentStreet" value="${user.currentStreet}" style="width:220px" required/>
	                             <script type="text/javascript">
	                             jQuery('#userForm').find('#currentCounties').combobox({
	  							    url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${user.currentCity}',
	  							    valueField:'id',
	  							    textField:'text'
	  							 });
	                              jQuery('#userForm').find('#currentCity').combobox({
	  								url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${user.currentProvince}',
	  							    valueField:'id',
	  							    textField:'text',
	  							    parentField:'pid',
	  							    onChange:function(newValue, oldValue) {
	  							    	if(newValue!=undefined&&""!=newValue) {
	  							    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
	  								    	jQuery('#userForm').find('#currentCounties').combobox("reload",url);
	  								    	jQuery('#userForm').find('#currentCounties').combobox('setValue','');
	  							    	}
	  							    }
	  							 });
	                              jQuery('#userForm').find('#currentProvince').combobox({
	  								url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${ipanthercommon:getChinaRegionsCode()}',
	  							    valueField:'id',
	  							    textField:'text',
	  							    parentField:'pid',
	  							    panelWidth:200,
	  							    onLoadSuccess:function(){
	  							    	if('${user.currentProvince}'!=''){
	  							    		jQuery('#userForm').find('#currentProvince').combobox('setValue','${user.currentProvince}');
	  									}
	  							    	if('${user.currentCity}'!=''){
	  							    		jQuery('#userForm').find('#currentCity').combobox('setValue','${user.currentCity}');
	  									}
	  							    	if('${user.currentCounties}'!=''){
	  							    		jQuery('#userForm').find('#currentCounties').combobox('setValue','${user.currentCounties}');
	  									}
	  							    },
	  							    onChange:function(newValue, oldValue) {
	  							    	if(newValue!=undefined&&""!=newValue) {
	  							    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
	  								    	jQuery('#userForm').find('#currentCity').combobox("reload",url);
	  								    	jQuery('#userForm').find('#currentCity').combobox('setValue','');
	  								    	jQuery('#userForm').find('#currentCounties').combobox('setValue','');
	  							    	}
	  							    }
	  							}); 
	                            </script>
					</td>
				</tr>
				<tr>
					<td><span style="color:red">*</span>参加工作时间</td>
					<td>
						<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="easyui-validatebox Wdate" name="workDate" value="<fmt:formatDate value="${user.workDate}" pattern="yyyy-MM-dd"/>" required/>
					</td>
					<td><span style="color:red">*</span>职务</td>
					<td>
						<input type="text" class="easyui-textbox" name="job" value="${user.job}" required/>
					</td>
				</tr>
				
				<%--类型包括教师或者注册成为教师--%>
				<c:if test="${isTeacherType or param.registerType eq 'teacher'}">
				<tr>
					<td><span style="color:red">*</span>专家类别</td>
					<td>
						<select id="expertType" name="expertType" class="easyui-combotree" checkbox="true" style="width: 158px;" editable="false" required>
						</select>
								<script type="text/javascript">
	                             jQuery("#userForm").find("#expertType").combotree({
									data: ${dict:getEntryListJsonOpen("EXPERT_TYPE","true")}, 
									checkbox:true,
									multiple:true,
									onLoadSuccess:function(){
										var value='${userStaff.expertType}';
										value = value.replace(/\"/g,'').replace(/\[/g,'').replace(/\]/g,'');
										if(value!=''){
											  jQuery("#userForm").find("#expertType").combotree('setValues',value);
										}
									 }
								}); 
	                            </script>
					</td>
					<td><span style="color:red">*</span>专家级别</td>
					<td>
						<select id="expertLevel" name="expertLevel" class="easyui-combotree" style="width: 158px;" editable="false" required>
						</select>
								<script type="text/javascript">
	                             jQuery("#userForm").find("#expertLevel").combotree({
									data: ${dict:getEntryListJsonOpen("EXPERT_LEVEL","true")}, 
									checkbox:true,
									multiple:true,
									onLoadSuccess:function(){
										var value='${userStaff.expertLevel}';
										value = value.replace(/\"/g,'').replace(/\[/g,'').replace(/\]/g,'');
										if(value!=''){
											  jQuery("#userForm").find("#expertLevel").combotree('setValues',value);
										}
									 }
								}); 
	                            </script>
					</td>
				</tr>
				
				<tr>
					<td><span style="color:red">*</span>研究领域</td>
					<td>
						<input type="text" class="easyui-textbox" name="researchField" value="${userStaff.researchField}" required/>
					</td>
					<td><span style="color:red">*</span>研究专长</td>
					<td>
						<input type="text" class="easyui-textbox" name="researchSpecial" value="${userStaff.researchSpecial}" required/>
					</td>
				</tr>
				</c:if>
				
				<tr>
					<td ><span style="color:red">*</span>办公电话</td>
					<td >
						<c:set var="officePhoneArr" value="${fn:split(user.officePhone, '-') }"/>
						<input type="text" class="easyui-textbox" id="officePhoneHead" style="width:40px" value="${officePhoneArr[0]}" required/>
						-
						<input type="text" class="easyui-textbox" id="officePhoneTail" style="width:80px"  value="${officePhoneArr[1]}" required/>
						<input type="hidden" id="officePhone" name="officePhone" value="${user.officePhone}"/>
					</td>
					<td ><span style="color:red">*</span>移动电话</td>
					<td >
						<input type="text" class="easyui-textbox" id="mobilePhone" name="mobilePhone" value="${user.mobilePhone}" validType="mobilePhone" required/>
					</td>
				</tr>
				<tr>
					
					<c:choose>
						<%--类型包括教师或者注册成为教师--%>
						<c:when test="${isTeacherType or param.registerType eq 'teacher'}">
							<td><span style="color:red">*</span>电子信箱</td>
							<td >
								<input type="text" class="easyui-textbox" id="email" name="email" value="${user.email}" validType="email" required/>
							</td>
							<td>${teacherRequiredSpan}职称</td>
							<td>
								<input type="text" class="easyui-textbox" name="title" value="${userStaff.title}" ${teacherRequired}/>
							</td>
						</c:when>
						<c:otherwise>
							<td><span style="color:red">*</span>电子信箱</td>
							<td colspan="3">
								<input type="text" class="easyui-textbox" name="email" id="email" value="${user.email}" validType="email" required/>
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
				<td><span style="color:red">*</span>个人简介</td>
				<td colspan="3">
					<textarea class="easyui-validatebox" validType="length[0,500]" required name="introduce" rows="5" style="width: 98%">${user.introduce}</textarea>
				</td>
			</tr>
			</tbody>
		</table>
		</div>
		<div class="easyui-panel" title="学历信息" collapsible="true">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="alter-table-v-space">
			<tbody>
				<tr>
					<td width="20%"><span style="color:red">*</span>学历</td>
					<td width="30%">
						<select id="expertLevel" name="highDiploma" class="easyui-combobox" style="width: 158px;" editable="false" required>
							<option value=""></option>
							${dict:getEntryOptionsSelected('DIPLOMA_TYPE',userStaff.highDiploma) }
						</select>
					</td>
					<td width="20%"><span style="color:red">*</span>所学专业</td>
					<td width="30%">
						<input type="text" class="easyui-textbox" name="highSubject" value="${userStaff.highSubject}" required/>
					</td>
				</tr>
				<tr>
					<td><span style="color:red">*</span>学位</td>
					<td>
						<select id="highDegree" name="highDegree" class="easyui-combobox" style="width: 158px;" editable="false" required>
							<option value=""></option>
							${dict:getEntryOptionsSelected('DEGREE_TYPE',userStaff.highDegree) }
						</select>
					</td>
					<td><span style="color:red">*</span>授予学位单位名称</td>
					<td>
						<input type="text" class="easyui-textbox" name="highDegreeUnit" value="${userStaff.highDegreeUnit}" required/>
					</td>
				</tr>
				<tr>
					<td><span style="color:red">*</span>获学位日期</td>
					<td>
						<input type="text" class="easyui-validatebox Wdate" name="highDegreeDt" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value='<fmt:formatDate value="${userStaff.highDegreeDt}" pattern="yyyy-MM-dd"/>' required/>
					</td>
					<td><span style="color:red">*</span>毕业时间</td>
					<td>
						<input type="text" class="easyui-validatebox Wdate" name="highDt" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<fmt:formatDate value="${userStaff.highDt}" pattern="yyyy-MM-dd"/>" required/>
					</td>
				</tr>
				<tr>
					<td><span style="color:red">*</span>毕肄业学校或单位</td>
					<td colspan="3">
						<input type="text" class="easyui-textbox" name="highCollege" value="${userStaff.highCollege}" required/>
					</td>
				</tr>
			</tbody>
			</table>
		</div>

		<div class="easyui-panel" title="任职资格信息" collapsible="true">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="alter-table-v-space">
			<tbody>
				<tr>
					<td width="20%"><span style="color:red">*</span>外国语种</td>
					<td width="30%">
						<select id="foreignLanguages" name="foreignLanguages" class="easyui-combobox" style="width: 158px;" editable="false" required>
							<option value=""></option>
							${dict:getEntryOptionsSelected('YZMC',userStaff.foreignLanguages) }
						</select>
					</td>
					<td width="20%"><span style="color:red">*</span>外国语种熟练程度</td>
					<td width="30%">
						<select id="foreignDegree" name="foreignDegree" class="easyui-combobox" style="width: 158px;" editable="false" required>
							<option value=""></option>
							${dict:getEntryOptionsSelected('WGYZSLCD',userStaff.foreignDegree) }
						</select>
					</td>
				</tr>
				<tr>
					<td><span style="color:red">*</span>计算机水平</td>
					<td>
						<select id="computerLevel" name="computerLevel" class="easyui-combobox" style="width: 158px;" editable="false" required>
							<option value=""></option>
							${dict:getEntryOptionsSelected('COMPUTER_LEVEL',userStaff.computerLevel) }
						</select>
					</td>
					<td><span style="color:red">*</span>最高专业技术资格名称</td>
					<td>
						<input type="text" class="easyui-textbox" name="proName" value="${userStaff.proName}" required/>
					</td>
				</tr>
				<tr>
					<td>评定日期</td>
					<td>
						<input type="text" class="easyui-validatebox Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="proDt" value="<fmt:formatDate value="${userStaff.proDt}" pattern="yyyy-MM-dd"/>" />
					</td>
					<td><span style="color:red">*</span>现聘专业技术职务</td>
					<td>
						<input type="text" class="easyui-textbox" name="proJob" value="${userStaff.proJob}" required/>
					</td>
				</tr>
				</tbody>
			</table>
		</div>
		
	<%--类型包括教师或者注册成为教师--%>
	<c:if test="${isTeacherType or param.registerType eq 'teacher'}">
		<div class="easyui-panel" title="师资信息" collapsible="true">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="alter-table-v-space">
				<tbody>
					<tr>
						<td width="20%">${teacherRequiredSpan}培训领域</td>
						<td width="30%">
							<input type="text" class="easyui-textbox" name="trainDomain" value="${userStaff.trainDomain}" ${teacherRequired}/>
						</td>
						<td width="20%">${teacherRequiredSpan}培训方向</td>
						<td width="30%">
							<input type="text" class="easyui-textbox" name="trainDirection" value="${userStaff.trainDirection}" ${teacherRequired}/>
						</td>
					</tr>
					
					<tr>
						<td>${teacherRequiredSpan}主要学术著作及论文</td>
						<td>
							<input type="text" class="easyui-textbox" name="mainThesis" value="${userStaff.mainThesis}" ${teacherRequired}/>
						</td>
						<td>${teacherRequiredSpan}收款账号</td>
						<td>
							<input type="text" class="easyui-textbox" name="bankAccount" value="${userStaff.bankAccount}" ${teacherRequired}/>
						</td>
					</tr>
					<tr>
						<td>${teacherRequiredSpan}是否有知识产权教学或实务工作经验</td>
						<td colspan="3">
							<select id="haveExp" name="haveExp" class="easyui-combobox" style="width: 158px;" editable="false" data-options="onSelect:changeHaveExp" ${teacherRequired}>
								<option value=""></option>
								${dict:getEntryOptionsSelected('HAVE_EXP',userStaff.haveExp) }
							</select>
						</td>
					</tr>
					<tr>
						<td><span style="color:red" id="expDesc_span">*</span>从事知识产权教学或授课的相关经历</td>
						<td colspan="3">
							<textarea rows="5" style="width: 98%" name="expDesc" id="expDesc" class="easyui-validatebox">${userStaff.expDesc}</textarea>
						</td>
					</tr>
				</tbody>
				</table>
			</div>
	</c:if>
	
	<div style="text-align: center">
		<%--不在走流程才可以编辑 --%>
		<c:if test="${user.teacherStatus ne '02' and user.studentStatus ne '02' }">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="alter-table-v-space">
				<tbody>
					<tr>
                    	<td width="20%"><span style="color:red" id="expDesc_span">*</span>验证方式</td>
                    	<td width="80%">
                        	
                               		   <select id="checkType" name="checkType" class="easyui-combobox" style="width:80px;" required editable="false">
												<option value="sms">手机验证</option> 
												<option value="email">邮箱验证</option>   
										</select>  
										
										
						  <input id="code" name="code" class="easyui-textbox" type="text"  required <%-- validType="myRemote['${ctx}/common/validate/getValidate.do','userForm','code','checkType','email','mobilePhone']" --%>/>
						  <a id="btnCode" style="font-size: 12px;text-decoration: underline;" href="javascript:void(0)" onClick="getCheckCode()">点击获取验证码</a>
												<script type="text/javascript">
													
													//获取验证码的倒数时间
													var count;
													function getCheckCode(){
														
														var checkType = $("#checkType").combobox("getValue");
														var email = $("input[name='email']").val();
														var phone = $("input[name='mobilePhone']").val();
														
														if('email'==checkType){
															if(!$("#email").textbox('isValid')){
																$.messager.alert('警告','请填写正确的邮箱信息');
																return false;
															}
														}
														else if('sms'==checkType){
															if(!$("#mobilePhone").textbox('isValid')){
																$.messager.alert('警告','请填写正确的手机信息');
																return false;
															}
														}
														
														//设置倒数时间
														count = 60;
														GetNumber();
														var userId = $("#id").val();
														//先校验一下填写的邮箱或者手机号是否已经注册过
														$.ajax({
															url:"${ctx}/common/validate/checkEmailOrPhone.do?checkType="+checkType+"&email="+email+"&phone="+phone+"&userId="+userId,
															type:"post",
															dataType:"json",
															async:true,
															success:function(data){
																if(data.flag){//当flag为true，即可发送验证码
																	$.ajax({ 
																		url:"${ctx}/common/validate/saveValidate.do?checkType="+checkType+"&email="+email+"&phone="+phone+"&userId="+userId, 
																		type:'post',
																		dataType:'json',
																		async:true,
																		success: function(data){
																				if(data.count>0){
																					$.messager.alert('提示','正在发送');
																				}
																				else{
																					$.messager.alert('提示','发送失败');
																				}
																		}
																	 });
																}else{
																	$.messager.alert('警告','填写的邮箱或手机已被注册');
																}
															}
														});
													}
													
													//var count = 5;
													function GetNumber(){
														
														//将a标签置为失效
														$("#btnCode").removeAttr("href");
														$("#btnCode").removeAttr("onclick");
														
														
														$("#btnCode").text(count + "秒之后点击获取");
														count--;
														if(count>0){
															setTimeout(GetNumber,1000);
														}else{
															$("#btnCode").text("点击获取验证码");
															$("#btnCode").attr("href","javascript:void(0)");
															$("#btnCode").attr("onclick","getCheckCode()");
														}
													}
												
												
												</script> 
                        </td>
                    </tr>
				  </tr>
                </tbody>
            </table>
            <c:choose>
                <c:when test="${param.registerType eq 'student' and user.studentStatus ne '03'}">
                    <%-- 注册类型是学员，且当前不是学员 --%>
                    <button type="button" onclick="saveUser('student')" class="easyui-linkbutton" > <i class="fa fa-plus"></i>注册成为学员</button>
                </c:when>
                <c:when test="${param.registerType eq 'teacher' and user.teacherStatus ne '03'}">
                    <%-- 注册类型是教师，且当前不是教师 --%>
                    <button type="button" onclick="saveUser('teacher')" class="easyui-linkbutton" > <i class="fa fa-plus"></i>注册成为教师</button>
                </c:when>
                <c:otherwise>
                    <button type="button" onclick="saveUser('')" class="easyui-linkbutton" > <i class="fa fa-save"></i>保 存 </button>
                </c:otherwise>
            </c:choose>
		</c:if>
	</div>
</form>
<script type="text/javascript">
tableVBg('.alter-table-v-space');
$(document).ready(function (){
	$("#up").uploadPreview({ Img: "ImgPr", Width: 120, Height: 160 });
});
$.parser.onComplete=function(){
	changePaperworkType();
	changeHaveExp();
	$.parser.onComplete=function(){};
};
//证件格式校验
function changePaperworkType() {
	var paperworkType = $("#paperworkType","#userForm").combobox("getValue");
	if("01"==paperworkType) {//居民身份证
		$("#paperworkNo","#userForm").textbox({
			validType:'idcard'
		});
	} else {
		$("#paperworkNo","#userForm").textbox({
			validType:''
		});
	}
}
//是否有知识产权教学或实务工作经验
function changeHaveExp() {
	<c:if test="${isTeacherType or param.registerType eq 'teacher'}">
		var haveExp = $("#haveExp","#userForm").combobox("getValue");
		if('1'==haveExp) {
			$("#expDesc","#userForm").validatebox({required: true});
			$("#expDesc_span","#userForm").show();
		} else {
			$("#expDesc","#userForm").validatebox({required: false});
			$("#expDesc_span","#userForm").hide();
		}
	</c:if>
}

function saveUser(registerType) {
	var msg = '确定保存？';
	if(''!=registerType) {
		msg = '注册后，在审核结束前，不允许更改用户信息！确认注册？';
	}
	$.messager.confirm('提示', msg, function(r){
		if(r) {
			
			//处理数据
			//处理办公电话
			var officePhoneHead = jQuery("#officePhoneHead").val();
			var officePhoneTail = jQuery("#officePhoneTail").val();
			if(""!=officePhoneHead&&""!=officePhoneTail) {
				jQuery("#officePhone","#userForm").val(officePhoneHead+"-"+officePhoneTail);
			}
			jQuery('#userForm').form('submit',{
				onSubmit: function(){
					 return $('#userForm').form('validate');
				},
			    success: function(data){
			    	var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							jQuery.messager.alert("提示信息",message,"error");
						} else if(parseInt(statusCode)==200){
							
							jQuery.messager.alert("提示信息","操作成功","info",function() {
								window.location.reload();
							});
						}
					}
			    }
			}); 
		}
		
	});
}
</script>