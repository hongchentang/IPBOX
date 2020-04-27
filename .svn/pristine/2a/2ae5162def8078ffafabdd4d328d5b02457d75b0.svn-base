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
				<td>用户名</td>
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
				<td>性别</td>
				<td>
					${dict:getEntryName('SEX_TYPE',user.sex) }
				</td>
			</tr>

			<tr>
				<td>密码</td>
				<td>${user.passwordPlain}</td>
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
				<td>
					${user.email}
				</td>
				<td>传真</td>
				<td>
					${user.faxes}
				</td>
			</tr>
		</tbody>
	</table>
	</div>

<script type="text/javascript">
// tableVBg('.${tableClass}');
$(document).ready(function (){
	
});
</script>
