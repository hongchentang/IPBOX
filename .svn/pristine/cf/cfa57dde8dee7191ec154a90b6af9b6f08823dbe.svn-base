<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
	<!--start #g-bd 中间 -->
	<!-- <div id="g-bd"> -->
		<!--start #talents 人才培训系统 -->
		<div id="talents">
			<div class="g-page-wrap">
				<div class="g-page-box">
					<div class="m-tt2">
						<div class="m-crm">
							<span class="c-txt">您的位置：</span>
							<a href="${ctx}/">首页</a>
							<span class="line">&gt;</span>
							<span class="z-crt">人才培训系统</span>
						</div>
					</div><!--end .m-tt2-->	
					<div class="m-page-dt">
						<div class="g-talents" style="padding-top: 0">
                        	<div class="m-select" style="width: 100%;height: 84px;padding: 8px 12px">
                        		<div style="float: left;text-align: center;padding-top: 30px;">
									<strong>培训基地：</strong>
								</div>
								<div class="sl-v-logos" id="trainId_">
									<ul class="v-fixed">
									    <c:forEach items="${trainList }" var="train">
											<li><a href="javascript:void(0);"  onclick="searchJ(this,'trainId','${train.declareDept }')">
											<c:if test="${not empty train.logo}">
												<c:set value="${ipanthercore:getJSONMap(train.logo)}" var="map" />
												 	<img src="${ctx}${map.downloadUrl}" title="${train.trainName }" width="94" height="84"/>
											</c:if>
											<c:if test="${empty train.logo}">
												<img src="${ctx}/themes/itlt-ppt/images/images1.png" title="${train.trainName }"/>
											</c:if>
											</a></li>
									    </c:forEach>
									</ul>
								</div>
							</div>
							<div class="m-select" id="trainType_" style="padding: 8px 12px">
								<strong>培训形式：</strong>
								<a href="javascript:void(0);" onclick="searchJ(this,'trainType','')" class="z-crt">全部</a>
								<c:forEach items="${dict:getEntryList('STUDY_TYPE') }" var="l">
								<a href="javascript:void(0);" onclick="searchJ(this,'trainType','${l.dictValue}')">${l.dictName}</a>
								</c:forEach>
							</div>
							<div class="m-select" id="hoursType_" style="padding: 8px 12px">
								<strong>学时类型：</strong>
								<a href="javascript:void(0);" onclick="searchJ(this,'hoursType','')" class="z-crt">全部</a>
								<c:forEach items="${dict:getEntryList('HOURS_TYPE') }" var="l">
								<a href="javascript:void(0);" onclick="searchJ(this,'hoursType','${l.dictValue}')">${l.dictName}</a>
								</c:forEach>
							</div>
							<div class="m-select" id="courseLevel_" style="padding: 8px 12px">
								<strong>课程级别：</strong>
								<a href="javascript:void(0);" onclick="searchJ(this,'courseLevel','')" class="z-crt">全部</a>
								<c:forEach items="${dict:getEntryList('IPR_COURSE_LEVEL') }" var="l">
								<a href="javascript:void(0);" onclick="searchJ(this,'courseLevel','${l.dictValue}')">${l.dictName}</a>
								</c:forEach>
							</div>
							<div class="m-select" id="applyJob_" style="padding: 8px 12px">
								<strong>适用岗位：</strong>
								<a href="javascript:void(0);" onclick="searchJ(this,'applyJob','')" class="z-crt">全部</a>
								<c:forEach items="${dict:getEntryList('IPR_APPLY_POSITION') }" var="l">
								<a href="javascript:void(0);" onclick="searchJ(this,'applyJob','${l.dictValue}')">${l.dictName}</a>
								</c:forEach>
							</div>
							<div class="m-select" id="courseStage_" style="padding: 8px 12px">
								<strong>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;段：</strong>
								<a href="javascript:void(0);" onclick="searchJ(this,'courseStage','')" class="z-crt">全部</a>
								<c:forEach items="${dict:getEntryList('IPR_COURSE_STAGE') }" var="l">
									<a href="javascript:void(0);" onclick="searchJ(this,'courseStage','${l.dictValue}')" >${l.dictName}</a>
								</c:forEach>
							</div>
							<div class="m-select" id="year_" style="padding: 8px 12px">
								<strong>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度：</strong>
								<a href="javascript:void(0);" onclick="searchJ(this,'year','')" class="z-crt">全部</a>
								<c:forEach items="${yearList }" var="y">
									<a href="javascript:void(0);" onclick="searchJ(this,'year','${y }')">${y }</a>
								</c:forEach>
							</div>
							<div class="g-select-wrap f-cb">
								<div class="g-select-side">
									<div class="g-txtLst1">
								<div class="m-tt1">
									<h2>培训动态</h2>
									<div class="u-more">
										<a href="${ctx}/site${globalRegions}/dynamic/list.do">查看更多<i class="ico"></i></a>
									</div>
								</div>
								<ul class="m-t-lst">
									<c:forEach items="${dynamics}" var="dynamic">
										<li>
											<a href="${ctx}/site${globalRegions}/dynamic/detail.do?id=${dynamic.id}" title="${dynamic.title}" target="_blank">${StringUtils:abbreviate(dynamic.title,17)}</a>
										</li>
									</c:forEach>
								</ul>
							</div>
								</div>
								<div class="g-select-main">
									<div class="m-tabs" style="padding-left: 12px;" id="itemsStatus">
										<!-- <a href="javascript:void(0);" onclick="itemsStatus(this,'all')" class="z-crt">全部</a> -->
										<a href="javascript:void(0);" onclick="itemsStatus(this,'willOpen')" class="z-crt">即将报名</a>
										<a href="javascript:void(0);" onclick="itemsStatus(this,'opening')" >报名进行中</a>
										<a href="javascript:void(0);" onclick="itemsStatus(this,'notOpen')">尚未开班</a>
										<a href="javascript:void(0);" onclick="itemsStatus(this,'close')">结束报名</a>
									</div>
										<div id="courseContent">
											努力加载中。。。
										</div>
								</div>
							</div>
						</div><!--end .g-talents-->
					</div><!--end .m-page-dt-->
				</div><!--end .g-page-box -->
			</div>
		</div>
		<!--end #talents 人才培训系统 -->
	<!-- </div> -->
	<!--end #g-bd 中间 -->
	<div class="m-blackbg"></div>
	
	<!--end .m-log-layer 登录弹出框-->
<%-- <script type="text/javascript" src="${ctx}/themes/itlt-ppt/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/themes/itlt-ppt/js/function.js"></script>  --%>
<script type="text/javascript">
$(function(){
	setMenuSelect("train");
	turns($(".m-figure-lst1 .c-item"),180,100);
	$("#courseContent").load("${ctx}/train/talentTrain/listCourse.do?paramMap[openStatus]=willOpen")
})
function searchJ(obj,textId,value){
	$('a',"#"+textId+"_").removeClass('z-crt');
	$(obj).addClass('z-crt');
	$("#"+textId).val(value);
	$("#currentPage","#courseForm").val("1");
var rData = $.ajax({
			url : $("#courseForm").attr("action"),
			data : $("#courseForm").serialize(),
			type : "post",
			async : false,
			success : function(data) {
			},
			cache : false,
			ifModified : true
		}).responseText;
jQuery("#courseContent").html(rData);	
}

function itemsStatus(obj,status){
	$('a',"#itemsStatus").removeClass('z-crt');
	$(obj).addClass('z-crt');
	$("#currentPage","#courseForm").val("1");
	$("#openStatus","#courseForm").val(status);
	var rData = $.ajax({
				url : $("#courseForm").attr("action"),
				data : $("#courseForm").serialize(),
				type : "post",
				async : false,
				success : function(data) {
				},
				cache : false,
				ifModified : true
			}).responseText;
	jQuery("#courseContent").html(rData);
}
</script>
