<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<%@ include file="/themes/skin/ipr/inc.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>员工信息打印预览</title>
<style type="text/css">
table{
border-collapse:collapse;
width: 80%;
background-color: white;
} 
table td{border: 1px solid black} 

@media print {
.noprint { display: none;color:green }
} 
</style>
</head>
<body style="background-color: white">
<c:forEach items="${list}" var="user">
<div style="page-break-after:always;"> 
	<div style="margin-top: 10px;">
		员工信息：
	</div>
	<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td style="width: 18%;">用户名</td>
				<td style="width: 32%;">
					${user.userName}
				</td>
				<td rowspan="4" style="width: 15%;">个人头像</td>
				<td rowspan="4" style="vertical-align: bottom; width: 35%;">
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
				<td>公司名称</td>
				<td>
					${user.companyName}
				</td>
			</tr>
			<tr>
				<td>所属部门</td>
				<td>
					${user.deptNames}
				</td>
				<td>员工角色</td>
				<td>
					${user.roleCode}
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

	<br>
</div>

</c:forEach>
	<div style="text-align: center" class="noprint">
		<input type="button" value="打印" onclick="window.print()"/>
		<input type="button" value="关闭" onclick="window.close()"/>
	</div>
</body>
<script type="text/javascript">
	jQuery("table tr").each(function() {
		$(this).children("td").each(function(i) {
			if(i%2==0) {
				jQuery(this).css("text-align","right");
			} else {
				jQuery(this).css("text-align","left");
			}
			//给无任何信息的单元格加上空格
			if(""==jQuery.trim(jQuery(this).html())) {
				jQuery(this).html("&nbsp;");
			}
		});
	});
</script>
</html>

