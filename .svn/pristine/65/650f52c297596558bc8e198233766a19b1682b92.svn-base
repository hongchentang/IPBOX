<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<%@ include file="/themes/skin/ipr/inc.jsp" %>
<style type="text/css">
body{
	background-color: white;
}
.td_right{
width: 20%;
text-align: right
}
.td_left{
width:80%;
text-align: left
}
.tip{
font-size: 12px;
color:#666666
}
</style>
<!--start #g-bd 中间 -->
<div id="g-bd">
	<!--start #register 注册-->
	<div >
		<div class="g-page-wrap">
			<div class="g-page-box">
				<div class="m-tt2">
					<div class="m-crm">
						<span class="c-txt">您的位置：</span>
						<a href="${ctx}/site${globalRegions}/index.do">首页</a>
						<span class="line">></span>
						<span class="z-crt">
						注册
						</span>
					</div>
				</div><!--end .m-tt2-->	
				<table style="width: 95%">
					<tr>
						<td height="30" class="td_right"><span style="color: red">*</span>注册类型: </td>
						   <td class="td_left">
						         <label><input type ="radio" name="registerType" onclick="registerFrom.style.display='';orgRegisterFrom.style.display='none';" checked value=""/>普通用户</label>
						         <label><input type ="radio" name="registerType" onclick="registerFrom.style.display='none';orgRegisterFrom.style.display='';" value=""/>机构用户</label>
						   </td>
					</tr>
				</table>
				<form id="registerFrom" name="registerFrom" action="${ctx}/site/regist.do" method="post">
                	<input type="hidden" name="user.roleCode" value="2"/>
                	<input type="hidden" name="user.type" value="2"/>
                	<input type="hidden" name="user.studentStatus" value="03"/>
                	<input type="hidden" name="user.teacherStatus" value="01"/>
					<div style="width:95%;text-align: center;margin: 0 auto;">
						<!-- <div id="userRegisterPanel" class="easyui-panel" title="用户注册" > -->
							
							 <table style="width: 100%">
						          <tr>
						            <td height="30" class="td_right"><span style="color: red">*</span>用户名:</td>
						            <td class="td_left">
						            	<input type="text" class="easyui-textbox" name="user.userName" style="width: 158px;" required value="${user.userName}" data-options="validType:'username'"/>
						            	<span class="tip">注册后不可修改，英文字母、数字、下划线等组成，第一位必须为字母，6-16字符</span>
						            </td>
						          </tr>
						          <tr>
						            <td height="30" class="td_right"><span style="color: red">*</span>设置密码:</td>
						            <td class="td_left">
						            	<input type="password" class="easyui-textbox" id="password" style="width: 158px;" name="user.passwordPlain" required/>
						            	<span class="tip">登录时使用的密码</span>
						            </td>
						          </tr>
						          <tr>
						            <td height="30" class="td_right"><span style="color: red">*</span>确认密码:</td>
						            <td class="td_left">
						            	<input type="password" class="easyui-textbox" id="rePassword" style="width: 158px;" required <%--validType="equalTo['password']"--%>/>
						            	<span class="tip">请重复输入密码</span>
						            </td>
						          </tr>
						          <%-- <tr>
						            <td class="td_right"><span style="color: red">*</span>密码提示问题:</td>
						            <td class="td_left">
						            	<input type="text" class="easyui-textbox" name="user.passwordAsk" value="${user.passwordAsk}" required/>
						            </td>
						          </tr>
						          <tr>
						            <td class="td_right"><span style="color: red">*</span>密码答案:</td>
						            <td class="td_left">
						            	<input type="text" class="easyui-textbox" name="user.passwordAnswer" value="${user.passwordAnswer}" required/>
						            </td>
						          </tr>--%>
                                  
						          <%-- <tr>
						            <td height="30" class="td_right"><span style="color: red">*</span>手机号码:</td>
						            <td class="td_left">
						            	<input type="text" id="mobilePhone" class="easyui-textbox" name="user.mobilePhone" style="width: 158px;" required data-options="validType:'mobilePhone'"/>
						            	<span class="tip">请输入手机号码，用于接收验证短信验证码</span>
						            </td>
						          </tr>
						          <tr>
						            <td height="30" class="td_right"><span style="color: red">*</span>电子邮箱:</td>
						            <td class="td_left">
						            	<input type="text" id="email" class="easyui-textbox" name="user.email" style="width: 158px;" required data-options="validType:'email'"/>
						            	<span class="tip">请输入有效Email，用于接收验证邮件验证码</span>
						            </td>
						          </tr>
						          <tr>
						            <td height="30" class="td_right"><span style="color: red">*</span>传真:</td>
						            <td class="td_left">
						            	<input type="text" class="easyui-textbox" name="user.faxes" style="width: 158px;" required />
						            </td>
						          </tr> --%>
						          <tr>
						            <td height="30" class="td_right"><span style="color: red">*</span>真实姓名:</td>
						            <td class="td_left">
						            	<input type="text" class="easyui-textbox" name="user.realName" style="width: 158px;" required/>
						            	<span class="tip">请输入真实姓名</span>
						            </td>
						          </tr>
						          <tr>
						            <td height="30" class="td_right"><span style="color: red">*</span>性别:</td>
						            <td class="td_left">
						            	<select name="user.sex" class="easyui-combobox" style="width: 158px;" editable="false" required>
											<option value=""></option>
											${dict:getEntryOptionsSelected('SEX_TYPE',user.sex) }
										</select>
						            </td>
						          </tr>
						          
						          <tr>
						            <td height="30" class="td_right"><span style="color: red">*</span>所属地区:</td>
						            <td class="td_left">
						            	<input type="hidden" name="user.regionsCode" id="regionsCode" value=""/>
										<select id="city" class="easyui-validatebox"  style="width: 80px;" editable="false" required></select>
										<select id="country" class="easyui-validatebox"  style="width: 80px;" editable="false"></select>
										<select id="street" class="easyui-validatebox"  style="width: 80px;" editable="false"></select>
 										<span class="tip">单位所在的地区</span>
					                    <script type="text/javascript">
					                             jQuery('#registerFrom').find('#street').combobox({
					                             	valueField:'id',
					    							textField:'text'
					   							 });
					                             jQuery('#registerFrom').find('#country').combobox({
					                            	valueField:'id',
					   							    textField:'text',
					   							 	parentField:'pid',
												    panelWidth:200,
												    onChange:function(newValue, oldValue) {
												    	if(newValue!=undefined&&""!=newValue) {
												    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
													    	jQuery('#registerFrom').find('#street').combobox("reload",url);
													    	jQuery('#registerFrom').find('#street').combobox('setValue','');
												    	}
												    }
					  							 });
					                              jQuery('#registerFrom').find('#city').combobox({
					  								url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${ipanthercommon:getGDRegionsCode()}',
					  							    valueField:'id',
					  							    textField:'text',
					  							    parentField:'pid',
					  							    panelWidth:200,
					  							    onChange:function(newValue, oldValue) {
					  							    	if(newValue!=undefined&&""!=newValue) {
					  							    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
					  								    	jQuery('#registerFrom').find('#country').combobox("reload",url);
					  								    	jQuery('#registerFrom').find('#country').combobox('setValue','');
					  								    	jQuery('#registerFrom').find('#street').combobox('setValue','');
					  							    	}
					  							    }
					  							}); 
					                     </script>
						            </td>
						          </tr>
						          <tr>
						            <td height="30" class="td_right"><span style="color: red">*</span>所属单位名称:</td>
						            <td class="td_left">
						            	<input type="text" class="easyui-textbox" id="belongDeptName" name="user.belongDeptName" required value="${user.belongDeptName}" style="width: 260px;"/>
						            </td>
						          </tr>
						           <tr>
						            <td height="30" class="td_right"><span style="color: red">*</span>所属单位地址:</td>
						            <td class="td_left">
						            	<input type="text" class="easyui-textbox" id="corrAddress" name="user.corrAddress" required value="${user.corrAddress}" style="width: 260px"/>
						            	<span class="tip">详细地址</span>
						            </td>
						          </tr>
						          <tr>
						            <td height="30" class="td_right"><span style="color: red">*</span>职务:</td>
						            <td class="td_left">
						            	<input type="text" class="easyui-textbox" id="job" name="user.job" required value="${user.job}" style="width: 158px;"/>
						            </td>
						          </tr>
						        <%--  <tr>
						            <td height="30" class="td_right"><span style="color: red">*</span>证件类型:</td>
						            <td class="td_left">
						            	<select id="paperworkType" name="user.paperworkType" class="easyui-combobox" required editable="false" data-options="onSelect:changePaperworkType" style="width: 158px;">
											<option value=""></option>
											${dict:getEntryOptionsSelected('PAPERWORK_TYPE',user.paperworkType) }
										</select>
						            </td>
						          </tr>
						          <tr>
						            <td height="30" class="td_right"><span style="color: red">*</span>证件号:</td>
						            <td class="td_left">
						            	<input type="text" class="easyui-textbox" name="user.paperworkNo" id="paperworkNo" value="${user.paperworkNo}" data-options="required:true,missingMessage:'请输入证证件号'" style="width: 158px;"/>
						            </td>
						          </tr>
						          <tr>
						            <td height="30" class="td_right"><span style="color: red">*</span>验证方式:</td>
						            <td class="td_left">
                                    	
										<select id="checkType" name="checkType" class="easyui-combobox" style="width:80px;" required editable="false">
												<option value="sms">手机验证</option> 
												<option value="email">邮箱验证</option>   
										</select>
										
										
										
										<input name="code" id="code" class="easyui-textbox" type="text"  style="width:100px;" required/>
										
										
										<a id="btnCode" style="font-size: 12px;text-decoration: underline;" href="javascript:void(0)" onClick="getCheckCode()">点击获取验证码</a>
												<script type="text/javascript">
													
															//获取验证码的倒数时间
															var count;
															function getCheckCode(){
																
																
																var checkType = $("#checkType").combobox("getValue");
																var email = $("input[name='user.email']").val();
																var phone = $("input[name='user.mobilePhone']").val();
																
																if("email"==checkType){
																	if(!$("#email").textbox('isValid')){
																		$.messager.alert('警告','请填写正确的邮箱信息');    
																		return false;
																	}
																}
																else if("sms"==checkType){
																	if(!$("#mobilePhone").textbox('isValid')){
																		$.messager.alert('警告','请填写正确的手机信息');
																		return false;
																	}
																}
																
																
																//设置倒数时间
																count = 60;
																GetNumber();
																
																
																//先校验一下填写的邮箱或者手机号是否已经注册过
																 $.ajax({
																	url:"${ctx}/common/validate/checkEmailOrPhone.do?checkType="+checkType+"&email="+email+"&phone="+phone,
																	type:"post",
																	dataType:"json",
																	async:true,
																	success:function(data){
																		if(data.flag){//当flag为true，即可发送验证码
																			$.ajax({ 
																				url:"${ctx}/common/validate/saveValidate.do?checkType="+checkType+"&email="+email+"&phone="+phone, 
																				type:'post',
																				dataType:'json',
																				async:true,
																				success: function(data){
																						if(data.count>0){
																							$.messager.alert('警告','正在发送');
																							
																						}
																						else{
																							$.messager.alert('警告','发送失败');
																							
																						}
																				}
																			 });
																		}else{
																			$.messager.alert('警告','邮箱或手机已被注册');
																			
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
						          </tr> --%>
					           <tr >
						            <td height="30" class="td_right">
						            	<span style="color: red">*</span>验证码:
						            </td>
						            <td class="td_left">
						            	<input type="text" class="easyui-textbox" name="checkCode" id="checkCode" required style="width: 158px;"/>
						            	<a style="font-size: 12px;text-decoration: underline;" href="javascript:void(0)" onClick="changeCheckCode()">换一个</a>
						            </td>
						          </tr>
						          
						          <tr >
						            <td height="30" class="td_right">
						            	
						            </td>
						            <td class="td_left">
						            	<img id="checkCodeImg" src="${ctx}/common/captcha/generateImage.do" onClick="changeCheckCode()" style="width: 158px;">
						            </td>
						          </tr>
						     </table>
						</div>
						<div class="m-btn-block m-btn-big">
							<button class="u-confirm-btn u-btn" type="button" onclick="regist()">立即注册</button>
							<button class="u-cancel-btn u-btn" type="button" onclick="window.location.href='${ctx}/'">取消</button>
						</div>
				</form>
				<form id ="orgRegisterFrom" name="orgRegisterFrom" style="display:none;" action="${ctx}/site/unitRegist.do" method="post">
						<input type="hidden" name="user.roleCode" value="1"/>
                		<input type="hidden" name="user.country" value="156"/>
                		<input type="hidden" name="user.sex" value="01"/>
                		<input type="hidden" name="user.politicsRole" value="02"/>
					<div style="width:95%;text-align: center;margin: 0 auto;">
					<table style="width:100%" >
						<tbody>
							<tr>
						            <td height="30" class="td_right"><span style="color: red">*</span>用户名:</td>
						            <td class="td_left">
						            	<input type="text" class="easyui-textbox" name="user.userName" style="width: 256px;" required value="${user.userName}" data-options="validType:'username'"/>
						            	<span class="tip">注册后不可修改，英文字母、数字、下划线等组成，第一位必须为字母，6-16字符</span>
						            </td>
						     </tr>
						     <tr>
						            <td height="30" class="td_right"><span style="color: red">*</span>设置密码:</td>
						            <td class="td_left">
						            	<input type="password" class="easyui-textbox" id="password" style="width: 256px;" name="user.passwordPlain" required/>
						            	<span class="tip">登录时使用的密码</span>
						            </td>
						     </tr>
						     <tr>
						            <td height="30" class="td_right"><span style="color: red">*</span>确认密码:</td>
						            <td class="td_left">
						            	<input type="password" class="easyui-textbox" id="rePassword" style="width: 256px;" required <%--validType="equalTo['password']"--%>/>
						            	<span class="tip">请重复输入密码</span>
						            </td>
						    </tr>
							<tr>
								<td height="30" class="td_right"><span style="color: red">*</span>单位名称: </td>
								<td class="td_left">
									<input type="text" class="easyui-textbox" name="department.deptName" required value="${department.deptName}" style="width:256px"/>
									<span class="tip">中文名称不超过50个中文字，简称不超过6个中文字</span>
								</td>
							</tr>
							<tr>
								<td height="30" class="td_right"><span style="color: red">*</span>单位性质: </td>
								<td class="td_left">
									<select id="deptNature" name="department.deptNature" class="easyui-combobox" required style="width: 256px;" editable="false">
												<option value=""></option>
												${dict:getEntryOptionsSelected('DEPT_NATURE_TYPE',department.deptNature)}
									</select>
								</td>
							</tr>
							<tr>
								<td height="30" class="td_right"><span style="color: red">*</span>单位类别: </td>
								<td class="td_left">
									<select id="deptCategory" name="department.deptCategory" class="easyui-combobox" required style="width: 256px;" editable="false">
												<option value=""></option>
												${dict:getEntryOptionsSelected('DEPT_CATEGORY',department.deptCategory)}
									</select>
								</td>
							</tr>
							<tr>
								<td height="30" class="td_right"><span style="color: red">*</span>行业分类: </td>
								<td class="td_left" colspan="3">
								<select id="industryType" name="department.industryType" class="easyui-combobox" required style="width: 256px;" editable="false">
												<option value=""></option>
												${dict:getEntryOptionsSelected('IPR_INDUSTRY_TYPE',department.industryType)}
									</select>
								</td>
							</tr>
							<%--<tr>
								<td height="30" class="td_right"><span style="color: red">*</span>技术领域: </td>
								<td class="td_left" colspan="3">
								<select id="industryType" name="department.technologyType" class="easyui-combobox" required style="width: 256px;" editable="false">
												<option value=""></option>
												${dict:getEntryOptionsSelected('IPR_TECHNOLOGY_TYPE',department.technologyType)}
									</select>
								</td>
							</tr>--%>
							<tr class="deptType_12">
								<td height="30" class="td_right"><span style="color: red">*</span>单位级别: </td>
								<td class="td_left">
									<select id="trainOrgLevel" name="department.trainOrgLevel" class="easyui-combobox" required style="width: 256px;" editable="false">
												<option value=""></option>
												${dict:getEntryOptionsSelected('TRAIN_ORG_LEVEL',department.trainOrgLevel)}
									</select>
								</td>
							</tr>
							<tr>
								<td height="30" class="td_right"><span style="color: red">*</span>主管单位: </td>
								<td class="td_left">
									<input  type="text" id="deptLeader" name="department.deptLeader" value="${department.deptLeader}" class="easyui-textbox" required style="width:256px"/>
									<span class="tip">没有可填“无”，如果单位未提交过申报项目，可直接通过本功能修改主管部门</span>
								</td>
							</tr>
							<tr>
								<td height="30" class="td_right"><span style="color: red">*</span>注册地区:</td>
						            <td class="td_left">
						            	<input type="hidden" name="department.regionsCode" id="regionsCode" value=""/>
										<select id="province" name="department.province" class="easyui-combobox"  style="width: 100px;" editable="false" required></select>
										<select id="city" name="department.city" class="easyui-combobox"  style="width: 100px;" editable="false" required></select>
										<select id="country" name="department.counties" class="easyui-combobox"  style="width: 100px;" editable="false" required ></select>
										<select id="street" name="department.street" class="easyui-combobox"  style="width: 100px;" editable="false" ></select>
 										<span class="tip">单位所在的地区</span>
					                    <script type="text/javascript">
					                             jQuery('#orgRegisterFrom').find('#street').combobox({
					                             	valueField:'id',
					    							textField:'text'
					   							 });
					                             jQuery('#orgRegisterFrom').find('#country').combobox({
					                            	valueField:'id',
					   							    textField:'text',
					   							 	parentField:'pid',
												    panelWidth:200,
												    onChange:function(newValue, oldValue) {
												    	if(newValue!=undefined&&""!=newValue) {
												    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
													    	jQuery('#orgRegisterFrom').find('#street').combobox("reload",url);
													    	jQuery('#orgRegisterFrom').find('#street').combobox('setValue','');
												    	}
												    }
					  							 });
					                              jQuery('#orgRegisterFrom').find('#city').combobox({
					  								//url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${ipanthercommon:getGDRegionsCode()}',
					  							    valueField:'id',
					  							    textField:'text',
					  							    parentField:'pid',
					  							    panelWidth:200,
					  							    onChange:function(newValue, oldValue) {
					  							    	if(newValue!=undefined&&""!=newValue) {
					  							    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
					  								    	jQuery('#orgRegisterFrom').find('#country').combobox("reload",url);
					  								    	jQuery('#orgRegisterFrom').find('#country').combobox('setValue','');
					  								    	jQuery('#orgRegisterFrom').find('#street').combobox('setValue','');
					  							    	}
					  							    }
					  							}); 
					                             jQuery('#orgRegisterFrom').find('#province').combobox({
						  								url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${ipanthercommon:getChinaRegionsCode()}',
						  							    valueField:'id',
						  							    textField:'text',
						  							    parentField:'pid',
						  							    panelWidth:200,
						  							    onChange:function(newValue, oldValue) {
						  							    	if(newValue!=undefined&&""!=newValue) {
						  							    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
						  								    	jQuery('#orgRegisterFrom').find('#city').combobox("reload",url);
						  								    	jQuery('#orgRegisterFrom').find('#city').combobox("setValue",'');
						  								    	jQuery('#orgRegisterFrom').find('#country').combobox('setValue','');
						  								    	jQuery('#orgRegisterFrom').find('#street').combobox('setValue','');
						  							    	}
						  							    }
						  							});
					                     </script>
						            </td>
						    </tr>
							<tr>
								<td height="30" class="td_right"><span style="color: red">*</span>传真电话:</td>
								<td class="td_left">
									<input type="text" class="easyui-textbox" name="department.linkFax" value="${department.linkFax}" required style="width:256px"/>
								</td>
							</tr>
							<tr>
								<td height="30" class="td_right"><span style="color: red">*</span>电子信箱:</td>
								<td class="td_left">
									<input type="text" class="easyui-textbox" name="department.linkEmail" value="${department.linkEmail}" required style="width:256px" validType="email"/>
									<span class="tip">如xx@qq.com</span>
								</td>
							</tr>
							<tr>
								<td height="30" class="td_right"><span style="color: red">*</span>单位主页:</td>
								<td class="td_left">
									<input type="text" class="easyui-textbox" name="department.homePage" value="${department.homePage}" required style="width:256px"/>
								</td>
							</tr>
							<tr >
						        <td height="30" class="td_right">
						            <span style="color: red">*</span>验证码:
						        </td>
						        <td class="td_left">
						            <input type="text" class="easyui-textbox" name="orgCheckCode" id="orgCheckCode" required style="width: 256px;"/>
						            <a style="font-size: 12px;text-decoration: underline;" href="javascript:void(0)" onClick="changeOrgCheckCode()">换一个</a>
						        </td>
						    </tr>       
						    <tr>
						            <td height="30" class="td_right">
						            </td>
						            <td class="td_left">
						            	<img id="orgCheckCodeImg" src="${ctx}/common/captcha/generateImage.do" onClick="changeOrgCheckCode()" style="width: 158px;">
						            </td>
						     </tr>
						</tbody>
					</table>
					</div>
					<div class="m-btn-block m-btn-big">
						<button class="u-confirm-btn u-btn" type="button" onclick="unitRegist()">机构注册</button>
						<button class="u-cancel-btn u-btn" type="button" onclick="window.location.href='${ctx}/'">取消</button>
					</div>
				</form>
			</div><!--end .g-page-box -->
		</div>
	</div>
	<!--end #register 注册 -->
</div>
<!--end #g-bd 中间 -->
<script type="text/javascript">
$(document).ready(function(){
	$(".g-nvc").children().removeClass("z-crt");//移除菜单选中效果
});
<%--用户信息验证成功，跳转到首页--%>
<c:if test="${not empty param.confirmRegisterInfo}">
jQuery.messager.alert("提示信息","${param.confirmRegisterInfo}","info",function() {
	<%--跳转到个人端登录界面--%>
	window.location.href="${ctx}${ipanthercommon:getProperty('app','space.loginUrl')}";
});
</c:if>

//更新验证码
function changeCheckCode() {
	jQuery("#checkCodeImg").attr("src","${ctx}/common/captcha/generateImage.do?date="+new Date().getTime());
}

//证件格式校验
function changePaperworkType() {
	var paperworkType = $("#paperworkType").combobox("getValue");
	if("01"==paperworkType) {//居民身份证
		$("#paperworkNo","#registerFrom").textbox({
			validType:'idcard'
		});
	} else {
		$("#paperworkNo","#registerFrom").textbox({
			validType:''
		});
	}
}



function regist() {
	if(!$('#registerFrom').form('validate')) {
		return false;
	}
	$.messager.confirm('提示', '确定注册？', function(r){
		if(r) {
			
			
			
			//设置regionsCode到最小级别
			var regionsCode = jQuery('#registerFrom').find('#city').combobox('getValue');
			var country = jQuery('#registerFrom').find('#country').combobox('getValue');
			var street = jQuery('#registerFrom').find('#street').combobox('getValue');
			if(""!=street) {
				regionsCode = street;
			} else if(""!=country) {
				regionsCode = country;
			}
			jQuery("#regionsCode").val(regionsCode);
			
			
			
			
			$("#registerFrom").ajaxSubmit({ 
				type: 'post',  
				success: function(json){
					if(!$.isEmptyObject(json)){
						var responseMsg = json.message;
						var responseCode = json.statusCode;
						changeCheckCode();
						if("200"==responseCode) {//成功
							jQuery.messager.alert("提示信息","注册成功！","info",function() {
								window.location.href="${ctx}/site${globalRegions}/index.do";
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

function unitRegist(){
// 	window.location.href="${ctx}/site/unitRegist.do";
// 	easyuiUtils.openWindow('showAddDialog','修改人才信息',900,630,'${ctx}/site/unitRegist.do?id='+'bed44f811cab407999c54d8f5b615e33',true);		
 	if(!$('#orgRegisterFrom').form('validate')) {
		return false;
	}
	$.messager.confirm('提示', '确定注册？', function(r){
		if(r) {

			//设置regionsCode到最小级别
			var regionsCode = jQuery('#orgRegisterFrom').find('#city').combobox('getValue');
			var country = jQuery('#orgRegisterFrom').find('#country').combobox('getValue');
			var street = jQuery('#orgRegisterFrom').find('#street').combobox('getValue');
			if(""!=street) {
				regionsCode = street;
			} else if(""!=country) {
				regionsCode = country;
			}
			jQuery("#orgRegisterFrom").val(regionsCode);
			$("#orgRegisterFrom").ajaxSubmit({ 
				type: 'post',  
				success: function(json){
					if(!$.isEmptyObject(json)){
						var responseMsg = json.message;
						var responseCode = json.statusCode;
						changeCheckCode();
						if("200"==responseCode) {//成功
							jQuery.messager.alert("提示信息","注册成功！","info",function() {
								window.location.href="${ctx}/site${globalRegions}/index.do";
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

</script>