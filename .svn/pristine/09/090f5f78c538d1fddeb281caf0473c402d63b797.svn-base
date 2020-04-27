<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<div id="g-bd">
	<!--start #teacher 培训师资系统 -->
	<div id="teacher">
		<div class="g-page-wrap">
			<div class="g-page-box">
				<div class="m-crm">
					<span class="c-txt">您的位置：</span> <a
						href="${ctx}/site${globalRegions}/index.do">首页</a> <span class="line">&gt;</span>
					<span class="z-crt"> 师资培训系统 </span>
				</div>
				<form id="teacherForm"
					action="${ctx}/site${globalRegions}/teacher/list.do" method="post" >
					<div class="m-multi-srh">
						<label class="block"> <span>区域:</span> <select
							id="province" name="paramMap[province]" style="width: 200px"
							onchange="getOnlyChildRegions('city','province','${searchParam.paramMap.city }')">
								<option value="">-请选择省-</option>
								<option value="440000"
									<c:if test="${searchParam.paramMap.province eq 440000}">selected</c:if>>广东省</option>
						</select>
						</label> <label class="block"> <span></span> <select id="city"
							name="paramMap[city]" style="width: 200px"
							onchange="getOnlyChildRegions('counties','city','${searchParam.paramMap.counties }')">
								<option value="">-请选择市-</option>
						</select>
						</label> <label class="block"> <span></span> <select id="counties"
							name="paramMap[counties]" style="width: 200px">
								<option value="">-请选择区-</option>
						</select>
						</label>
					</div>
					<br>
					<div class="m-multi-srh">
						<label class="block"> <span>专家级别:</span> <select
							name="paramMap[expertLevel]">
								<option value="">-请选择-</option>
								${dict:getEntryOptionsSelected('EXPERT_LEVEL',searchParam.paramMap.expertLevel) }
						</select>
						</label> <label class="block"> <span>专家类别:</span> <select
							name="paramMap[expertType]">
								<option value="">-请选择-</option>
								${dict:getEntryOptionsSelected('EXPERT_TYPE',searchParam.paramMap.expertType) }
						</select>
						</label> <label class="block"> <span>专业:</span> <input
							type="text" class="ipt" name="paramMap[highCollege]"
							value="${searchParam.paramMap.highCollege}">
						</label> <label class="block"> <span>研究领域:</span> <input
							type="text" class="ipt" name="paramMap[researchSpecial]"
							value="${searchParam.paramMap.researchSpecial}">
						</label> <label class="block"> <span>姓名:</span> <input type="text"
							class="ipt" name="paramMap[realName]"
							value="${searchParam.paramMap.realName}">
						</label>
						<button type="submit" class="u-sch-btn">
							<i class="ico"></i>
						</button>
					</div>
					<ul class="m-techer-lst">
						<c:forEach items="${teachers}" var="teacher">
							<li class="c-item"><c:choose>
									<c:when test="${not empty teacher.avatar}">
										<c:set value="${ipanthercore:getJSONMap(teacher.avatar)}"
											var="map" />
										<span class="img"> <a href="javascript:void(0)"
											onclick="loadDetail('${teacher.id}')"> <img
												src="${ctx}${map.downloadUrl}" alt="" onerror="javascript:this.src='${ctx}/themes/easyui/themes/tms/images/default.jpg';"
												style="max-width: 120px; max-height: 160px">
										</a>
										</span>
									</c:when>
									<c:otherwise>
										<span class="img"> <a href="javascript:void(0)"
											onclick="loadDetail('${teacher.id}')"> <img
												src="${ctx}/themes/easyui/themes/tms/images/default.jpg"
												alt="">
										</a>
										</span>
									</c:otherwise>
								</c:choose> <strong class="name"> <a href="javascript:void(0);"
									onclick="loadDetail('${teacher.id}')"> ${teacher.realName}
								</a>
							</strong>
								<p class="c-txt">
								<table class="m-table" width="100%" cellspacing="1"
									cellpadding="1" border="0">
									<tr>
										<th width="10%">性别</th>
										<td width="15%" style="text-align: left">${dict:getEntryName('SEX_TYPE',teacher.sex) }</td>
										<th width="10%">专业</th>
										<td width="15%" style="text-align: left">${teacher.highCollege}</td>
										<th width="10%">学员类别</th>
										<td width="25%" style="text-align: left">${dict:getEntryNameByJson('USER_TYPE',teacher.userType)}</td>
									</tr>
									<tr>
										<th>职称</th>
										<td style="text-align: left">${teacher.title}</td>
										<th>职务</th>
										<td style="text-align: left">${teacher.job}</td>
										<th>研究领域</th>
										<td style="text-align: left">${teacher.researchField}</td>
									</tr>
									<tr>
										<th>工作单位</th>
										<td style="text-align: left">${teacher.belongDeptName}</td>
										<th>所在地区</th>
										<td colspan="5" style="text-align: left">
											${ipanthercommon:getRegionsName(teacher.currentProvince)}
											${ipanthercommon:getRegionsName(teacher.currentCity)}
											${ipanthercommon:getRegionsName(teacher.currentCounties)}
											${teacher.currentStreet }</td>
									</tr>
									<tr>
										<th>个人简介</th>
										<td colspan="5" style="text-align: left">
											${teacher.abbreviateIntroduce}</td>
									</tr>
								</table>
								</p></li>
						</c:forEach>
					</ul>
					<jsp:include page="${ctx}/jsp/common/include/manager_page_cms.jsp">
						<jsp:param name="pageForm" value="teacherForm" />
					</jsp:include>
				</form>
			</div>
		</div>
	</div>
</div>
<!--end #teacher 培训师资系统 -->
<script type="text/javascript">
$(function(){
	setMenuSelect("teacher");
})
readyLoad();
function getOnlyChildRegions(id,regionsCodeId,selecRegionsCode){
	var regionsCode=$("#"+regionsCodeId).val();
	if(regionsCode!=''){
		$.ajax({
			url:"/common/regions/getOnlyChildRegions.do?selecRegionsCode="+selecRegionsCode+"&regionsCode="+regionsCode,
			type:"post",
			async:false,
			success:function(data){
				$("#"+id).html("");
				$("#"+id).append(data);
			}
		})
	}
}
function readyLoad(){
	var province='${searchParam.paramMap.province}';
	var city='${searchParam.paramMap.city}';
	var counties='${searchParam.paramMap.counties}';
	if(province!=""&&$("#province").val()!=""){
		 getOnlyChildRegions("city","province",city);
	}
	if(city!=""&&($("#city").val()!="")){
		 getOnlyChildRegions("counties","city",counties);
	}
}

function loadDetail(valueId){
	document.write("<form action='${ctx}/site${globalRegions}/teacher/detail.do?id="+valueId+"' method='post' name='form2' style='display:none'>");  
	document.write("</form>"); 
	document.form2.submit();
// 	window.location.href="${ctx}/site${globalRegions}/teacher/detail.do?id="+valueId;
}
</script>