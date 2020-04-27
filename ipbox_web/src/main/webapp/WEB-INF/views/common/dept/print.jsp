<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<%@ include file="/themes/skin/ipr/inc.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>机构信息打印预览</title>
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
	<div style="margin-top: 20px;">
		基础信息：
	</div>
	<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td>所在区域</td>
				<td>
					${regions.regionsName }
					<input type="hidden" name="dept.regionsCode" value="${regions.regionsCode}"/>
				</td>
				<td rowspan="5">单位LOGO</td>
				<td rowspan="5" style="vertical-align: bottom;">
					<c:choose>
						<c:when test="${not empty dept.logo}">
							<c:set value="${ipanthercore:getJSONMap(dept.logo)}" var="map" />
							<img src="${ctx}${map.downloadUrl}" border="0" style="max-width:94px;max-height:84px">
						</c:when>
						<c:otherwise>
							<img src="${ctx}/themes/easyui/themes/tms/images/default.jpg" border="0">
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>单位类型</td>
				<td>
					${dict:getEntryName('DEPT_TYPE',dept.deptType)}
				</td>
			</tr>
			<tr>
				<td >单位类别</td>
				<td >
					${dict:getEntryName('DEPT_CATEGORY',dept.deptCategory)}
				</td>
			</tr>
			<tr>
				<td>单位性质</td>
				<td>
					${dict:getEntryName('DEPT_NATURE_TYPE',dept.deptNature)}
				</td>
			</tr>
			<tr>
				<td >单位名称</td>
				<td >
					${dept.deptName}
				</td>
			</tr>
			<tr>
				<td>单位地址</td>
				<td colspan="3">
					${dept.deptAddress}
				</td>
			</tr>
			<tr>
				<td>组织机构码</td>
				<td>
					${dept.deptCode}
				</td>
				<td>单位邮政编码</td>
				<td>
					${dept.postCode}
				</td>
			</tr>
			<tr>
				<td>联系电话</td>
				<td>
					${dept.linkPhone}
				</td>
				<td>传真电话</td>
				<td>
					${dept.linkFax}
				</td>
			</tr>
			
			<tr>
				<td>电子信箱</td>
				<td>
					${dept.linkEmail}
				</td>
				<td>单位主页</td>
				<td>
					${dept.homePage}
				</td>
			</tr>
			<c:if test="${dept.deptType eq '12' }">
				<tr class="deptType_12">
					<td>基地级别</td>
					<td>
						${dict:getEntryName('TRAIN_ORG_LEVEL',dept.trainOrgLevel)}
					</td>
					<td>基地特色</td>
					<td>
						${dept.trainOrgFeature}
					</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	
	<div style="margin-top: 20px;">
		法定代表人信息：
	</div>
	<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="20%">姓名</td>
				<td width="30%">
					${dept.legalName}
				</td>
				<td width="20%">移动电话</td>
				<td width="30%">
					${dept.legalMobile}
				</td>
				
			</tr>
			
			<tr>
				<td>办公电话</td>
				<td>
					${dept.legalPhone}
				</td>
				<td>传真电话</td>
				<td>
					${dept.legalFax}
				</td>
			</tr>
			
			<tr>
				<td>电子信箱</td>
				<td colspan="3">
					${dept.legalEmail}
				</td>
			</tr>
		</tbody>
	</table>
	<div style="margin-top: 20px;">
		单位管理员信息：
	</div>
	<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="20%">姓名</td>
				<td width="30%">
					${dept.adminName}
				</td>
				<td width="20%">移动电话</td>
				<td width="30%">
					${dept.adminMobile}
				</td>
			</tr>
			<tr>
				
				<td >办公电话</td>
				<td >
					${dept.adminPhone}
				</td>
				<td>传真电话</td>
				<td>
					${dept.adminFax}
				</td>
			</tr>
			<tr>
				<td>电子信箱</td>
				<td colspan="3">
					${dept.adminEmail}
				</td>
			</tr>
		</tbody>		
	</table>
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

