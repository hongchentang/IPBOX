<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:choose>
	<c:when test="${ipanthercommon:isSpace(sessionScope.loginUser.roleCode)}">
		<c:set var="tableClass" value="alter-table-v-space"/>
	</c:when>
	<c:otherwise>
		<c:set var="tableClass" value="alter-table-v"/>
	</c:otherwise>
</c:choose>
	<div class="easyui-panel" title="基础信息" collapsible="true">
		<input type="hidden" name="user.id" id="id" value="${user.id}"/>
		<input type="hidden" name="userStaff.userId" id="userId" value="${user.id}"/>
		<table cellspacing="0" cellpadding="0" border="0" class="${tableClass}">
		<tbody>
			<tr>
				<td>用户名/账号/学号</td>
				<td>
					${user.userName}
				</td>
				<td rowspan="4">个人头像</td>
				<td rowspan="4" style="vertical-align: bottom;">
					<c:choose>
						<c:when test="${not empty user.avatar}">
							<c:set value="${ipanthercore:getJSONMap(user.avatar)}" var="map" />
							<img src="${ctx}${map.downloadUrl}" border="0" style="max-width: 120px;max-height:160px">
						</c:when>
						<c:otherwise>
							<img src="${ctx}/themes/easyui/themes/tms/images/default.jpg" border="0">
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>姓名</td>
				<td>
					${user.realName}
				</td>
			</tr>
			<tr>
				<td>证件类型</td>
				<td>
					${dict:getEntryName('PAPERWORK_TYPE',user.paperworkType) }
				</td>
			</tr>
			<tr>
				<td>证件号</td>
				<td>
					${user.paperworkNo}
				</td>
			</tr>
			<tr>
				<td>学员类别</td>
				<td colspan="3">
					${dict:getEntryNameByJson('USER_TYPE',userStaff.userType)}
				</td>
			</tr>
			<tr>
				<td width="20%">所在地区</td>
				<td width="30%">
					${user.regionsName}
				</td>
				<td width="20%">所在单位</td>
				<td width="30%">
					${user.deptName }
				</td>
			</tr>
			<c:if test="${not empty user.belongDeptName }">
				<tr>
					<td>所属单位名称</td>
					<td colspan="3">
						${user.belongDeptName}
					</td>
				</tr>
				<tr>
					<td>所属单位地址</td>
					<td colspan="3">
						${user.corrAddress}
					</td>
				</tr>
			</c:if>
			<tr>
				<td>出生日期</td>
				<td>
					${user.bornDate}
				</td>
				<td>密码提示问题</td>
				<td>
					${user.passwordAsk}
				</td>
			</tr>
			<tr>
				<td>性别</td>
				<td>
					${dict:getEntryName('SEX_TYPE',user.sex) }
				</td>
				<td>政治面貌</td>
				<td>
					${dict:getEntryName('ZZMM',user.politicsRole) }
				</td>
			</tr>
			<tr>
				<td>国籍/地区</td>
				<td>
					${dict:getEntryName('GJDQ',user.country) }
				</td>
				<td>籍贯</td>
				<td>
					${ipanthercommon:getRegionsName(user.hometownProvince)}
					${ipanthercommon:getRegionsName(user.hometownCity)}
				</td>
			</tr>
			<tr>
				<td>目前所在地</td>
				<td colspan="3">
					${ipanthercommon:getRegionsName(user.currentProvince)}
					${ipanthercommon:getRegionsName(user.currentCity)}
					${ipanthercommon:getRegionsName(user.currentCounties)}
					${user.currentStreet }
				</td>
			</tr>
			<tr>
				<td>参加工作时间</td>
				<td>
					<fmt:formatDate value="${user.workDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>职务</td>
				<td>
					${user.job}
				</td>
			</tr>
			<tr>
				<td>专家类别</td>
				<td>
					${dict:getEntryNameByJson('EXPERT_TYPE',userStaff.expertType)}
				</td>
				<td>专家级别</td>
				<td>
					${dict:getEntryNameByJson('EXPERT_LEVEL',userStaff.expertLevel)}
				</td>
			</tr>
			<tr>
				<td>研究领域</td>
				<td>
					${userStaff.researchField}
				</td>
				<td>研究专长</td>
				<td>
					${userStaff.researchSpecial}
				</td>
			</tr>
			<tr>
				<td>办公电话</td>
				<td>
					${user.officePhone}
				</td>
				<td>移动电话</td>
				<td>
					${user.mobilePhone}
				</td>
			</tr>
			<tr>
				<td>电子信箱</td>
				<td colspan="3">
					${user.email}
				</td>
			</tr>
			<tr>
				<td>传真</td>
				<td colspan="3">
					${user.faxes}
				</td>
			</tr>
			<tr>
				<td>个人简介</td>
				<td colspan="3">
					<textarea readonly="readonly" rows="2" style="width: 98%">${user.introduce}</textarea>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
	
	<div class="easyui-panel" title="学历信息" collapsible="true">
		<table cellspacing="0" cellpadding="0" border="0" class="${tableClass }">
		<tbody>
			<tr>
				<td width="20%">学历</td>
				<td width="30%">
					${dict:getEntryName('DIPLOMA_TYPE',userStaff.highDiploma) }
				</td>
				<td width="20%">所学专业</td>
				<td width="30%">
					${userStaff.highSubject}
				</td>
			</tr>
			<tr>
				<td>学位</td>
				<td>
					
						${dict:getEntryName('DEGREE_TYPE',userStaff.highDegree) }
				</td>
				<td>授予学位单位名称</td>
				<td>
					${userStaff.highDegreeUnit}
				</td>
			</tr>
			<tr>
				<td>获学位日期</td>
				<td>
					<fmt:formatDate value="${userStaff.highDegreeDt}" pattern="yyyy-MM-dd"/>
				</td>
				<td>毕业时间</td>
				<td>
					<fmt:formatDate value="${userStaff.highDt}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<td>毕肄业学校或单位</td>
				<td colspan="3">
					${userStaff.highCollege}
				</td>
			</tr>
		</tbody>
		</table>
	</div>
	<div class="easyui-panel" title="任职资格信息" collapsible="true">
		<table cellspacing="0" cellpadding="0" border="0" class="${tableClass }">
		<tbody>
			<tr>
				<td width="20%">外国语种</td>
				<td width="30%">
					${dict:getEntryName('YZMC',userStaff.foreignLanguages) }
				</td>
				<td width="20%">外国语种熟练程度</td>
				<td width="30%">
					${dict:getEntryName('WGYZSLCD',userStaff.foreignDegree) }
				</td>
			</tr>
			<tr>
				<td>计算机水平</td>
				<td>
					${dict:getEntryName('COMPUTER_LEVEL',userStaff.computerLevel) }
				</td>
				<td>最高专业技术资格名称</td>
				<td>
					${userStaff.proName}
				</td>
			</tr>
			<tr>
				<td>评定日期</td>
				<td>
					<fmt:formatDate value="${userStaff.proDt}" pattern="yyyy-MM-dd"/>
				</td>
				<td>现聘专业技术职务</td>
				<td>
					${userStaff.proJob}
				</td>
			</tr>
			</tbody>
		</table>
	</div>
<script type="text/javascript">
tableVBg('.${tableClass}');
$(document).ready(function (){
	
});
</script>
