<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<%@ include file="/themes/skin/ipr/inc.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>人才信息打印预览</title>
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
		基础信息：
	</div>
	<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
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
					${dict:getEntryNameByJson('USER_TYPE',user.userType)}
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
					${dict:getEntryNameByJson('EXPERT_TYPE',user.expertType)}
				</td>
				<td>专家级别</td>
				<td>
					${dict:getEntryNameByJson('EXPERT_LEVEL',user.expertLevel)}
				</td>
			</tr>
			<tr>
				<td>研究领域</td>
				<td>
					${user.researchField}
				</td>
				<td>研究专长</td>
				<td>
					${user.researchSpecial}
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
				<td>个人简介</td>
				<td colspan="3">
					${user.introduce}
				</td>
			</tr>
		</tbody>
	</table>
	
	<div style="margin-top: 10px;">
		学历信息：
	</div>
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="20%">学历</td>
				<td width="30%">
					${dict:getEntryName('DIPLOMA_TYPE',user.highDiploma) }
				</td>
				<td width="20%">所学专业</td>
				<td width="30%">
					${user.highSubject}
				</td>
			</tr>
			<tr>
				<td>学位</td>
				<td>
					
						${dict:getEntryName('DEGREE_TYPE',user.highDegree) }
				</td>
				<td>授予学位单位名称</td>
				<td>
					${user.highDegreeUnit}
				</td>
			</tr>
			<tr>
				<td>获学位日期</td>
				<td>
					<fmt:formatDate value="${user.highDegreeDt}" pattern="yyyy-MM-dd"/>
				</td>
				<td>毕业时间</td>
				<td>
					<fmt:formatDate value="${user.highDt}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<td>毕肄业学校或单位</td>
				<td colspan="3">
					${user.highCollege}
				</td>
			</tr>
		</tbody>
		</table>
	
	<div style="margin-top: 10px;">
		任职资格信息：
	</div>
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="20%">外国语种</td>
				<td width="30%">
					${dict:getEntryName('YZMC',user.foreignLanguages) }
				</td>
				<td width="20%">外国语种熟练程度</td>
				<td width="30%">
					${dict:getEntryName('WGYZSLCD',user.foreignDegree) }
				</td>
			</tr>
			<tr>
				<td>计算机水平</td>
				<td>
					${dict:getEntryName('COMPUTER_LEVEL',user.computerLevel) }
				</td>
				<td>最高专业技术资格名称</td>
				<td>
					${user.proName}
				</td>
			</tr>
			<tr>
				<td>评定日期</td>
				<td>
					<fmt:formatDate value="${user.proDt}" pattern="yyyy-MM-dd"/>
				</td>
				<td>现聘专业技术职务</td>
				<td>
					${user.proJob}
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

