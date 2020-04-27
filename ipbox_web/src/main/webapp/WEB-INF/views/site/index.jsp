<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<!--start #index 首页 -->
<div id="index">
	<!--start g-box 大模块-->
	<div class="g-box g-box1">
		<div class="g-boxc">
			<div class="g-box-dt">
				<div class="g-bd-h f-cb">
					<!--start .m-sld 轮播图-->
					<div class="m-sld">
						<ul class="c-img-list">
							<c:forEach items="${scrollNews}" var="scrollNew">
								<li>
									<c:set value="${ipanthercore:getJSONMap(scrollNew.img)}" var="img" />
									<a href="${ctx}/site${globalRegions}/inform/detail.do?id=${scrollNew.id}" class="img"><img src="${img.fileId}" alt="" style="width: 420px;height:306px"></a>
									<div class="txt"><a href="javascript:void(0);">${StringUtils:abbreviate(scrollNew.title,22)}</a></div>
								</li>
							</c:forEach>
						</ul>
						<div class="m-focus">
							<c:forEach items="${scrollNews}" var="scrollNew">
								<a href="javascript:void(0);"></a>
							</c:forEach>
						</div>
					</div>
					<!--start .m-sld 轮播图-->
					<!--start .g-txtLst 文字列表-->
					<div class="g-txtLst" style="margin: 0 10px;">
						<div class="m-tt1">
							<h2>通知要闻</h2>
							<div class="u-more">
								<a href="${ctx}/site${globalRegions}/inform/list.do">查看更多<i class="ico"></i></a>
							</div>
						</div>
						<ul class="m-t-lst">
							<c:forEach items="${informs}" var="inform">
								<li>
									<i class="u-dot"></i>
									<a href="${ctx}/site${globalRegions}/inform/detail.do?id=${inform.id}">${StringUtils:abbreviate(inform.title,22)}</a>
								</li>
							</c:forEach>
							
						</ul>
					</div>
					<!--start .g-txtLst 文字列表-->
					<!--start .g-txtLst 文字列表-->
					<div class="g-txtLst">
						<div class="m-tt1">
							<h2>培训动态</h2>
							<div class="u-more">
								<a href="${ctx}/site${globalRegions}/dynamic/list.do">查看更多<i class="ico"></i></a>
							</div>
						</div>
						<ul class="m-t-lst">
							<c:forEach items="${dynamics}" var="dynamic">
								<li>
									<i class="u-dot"></i>
									<a href="${ctx}/site${globalRegions}/dynamic/detail.do?id=${dynamic.id}">${StringUtils:abbreviate(dynamic.title,22)}</a>
								</li>
							</c:forEach>
						</ul>
					</div>
					<!--start .g-txtLst 文字列表-->
				</div><!--end .g-bd-h-->
			</div><!--end .g-box-dt-->
		</div><!--end .g-boxc-->
	</div>
	<!--end g-box 大模块-->
	
	<div class="g-box">
		<div class="g-boxc">
			<div class="g-box-dt">
				<!--start .g-figure-lst 人才培训系统-->
				<div class="g-figure-lst">
					<div class="m-tt">
						<h2 class="u-tt1">
							人才培训系统(培训课程)
						</h2>
						<div class="u-more">
							<a href="${ctx}/site${globalRegions}/train/list.do">查看更多<i class="ico"></i></a>
						</div>
					</div>
					<div class="m-mod-dt" id="index_talents">
						
					</div>
				</div><!--end .g-figure-lst 人才培训系统-->
			</div><!--end .g-box-dt-->
		</div><!--end .g-boxc-->
	</div>
	
	
	<div class="g-box g-box2">
		<div class="g-boxc">
			<div class="g-box-dt">
				<!--start .g-user-cards 培训师资系统-->
				<div class="g-user-cards">
					<div class="m-tt">
						<h2 class="u-tt2">
							培训师资系统(师资库)
						</h2>
						<div class="u-more">
							<a href="${ctx}/site${globalRegions}/teacher/list.do">查看更多<i class="ico"></i></a>
						</div>
					</div>
					<div class="m-mod-dt" id="index_teacher">
						
					</div>
				</div><!--end .g-user-cards 培训师资系统-->
			</div><!--end .g-box-dt-->
		</div><!--end .g-boxc-->
	</div>
	
	<div class="g-box g-box1">
		<div class="g-boxc">
			<div class="g-box-dt">
				<!--start .g-chart 人才评估系统-->
				<div class="g-chart">
					<div class="m-tt">
						<h2 class="u-tt3">
						人才评估系统(数据管理)
						</h2>
						<div class="u-more">
							<a href="${ctx}/site${globalRegions}/stat.do">查看更多<i class="ico"></i></a>
						</div>
					</div>
					<div class="m-mod-dt" id="show_student_content">
						
					</div>
				</div><!--end .g-chart 人才评估系统-->
			</div><!--end .g-box-dt-->
		</div><!--end .g-boxc-->
	</div>
	
	<!--start g-box 大模块-->
	<div class="g-box">
		<div class="g-boxc">
			<div class="g-box-dt">
				<!--start .g-blogroll 友情链接-->
				<div class="g-blogroll">
					<div class="m-tt">
						<h2 class="u-tt4">友情链接</h2>
					</div>
					<div class="m-mod-dt">
						<div class="m-blogroll">
							<strong>国内机构：</strong>
							<c:forEach items="${inLinks}" var="inLink">
								<a href="${not empty inLink.url?inLink.url:'javascript:void(0);'}" target="_blank">${inLink.name }</a>
							</c:forEach>
						</div><!--end .m-blogroll -->
						<div class="m-blogroll">
							<strong>国际机构：</strong>
							<c:forEach items="${interLinks}" var="interLink">
								<a href="${not empty interLink.url?interLink.url:'javascript:void(0);'}" target="_blank">${interLink.name }</a>
							</c:forEach>
						</div><!--end .m-blogroll -->
						<div class="m-blogroll">
							<strong>服务网站：</strong>
							<c:forEach items="${serviceLinks}" var="serviceLink">
								<a href="${not empty serviceLink.url?serviceLink.url:'javascript:void(0);'}" target="_blank">${serviceLink.name }</a>
							</c:forEach>
						</div><!--end .m-blogroll -->
					</div>
				</div><!--end .g-blogroll 友情链接-->
			</div><!--end .g-box-dt-->
		</div><!--end .g-boxc-->
	</div>
	<!--end g-box 大模块-->
</div>
<div class="g-movebox" style="background-color: #dceef9; display: ${course ==null?'none':'block' }">
	<div class="cont" style="padding-top: 5px;">
		<p align="center">
			<font color="#188dd1"><strong>最新培训报名消息</strong></font>
		</p>
		<p align="center" style="padding-top: 5px;">
				<a href="${ctx }/site/train/detail.do?id=${course.id}"
					target="_blank"><strong>${course.courseName}</strong></a>
				<p align="right"></p>
				<p class="c-site" style="padding-top: 3px;">来源：${course.deptName }</p>
				<p style="padding-top: 3px;">
					<a href="javascript:void(0)" class="u-view"> ${not empty course.studyHours?course.studyHours:0 }学时
					</a>|<a href="javascript:void(0)" class="u-num">
						已报：${course.allTrainCount }人</a>
				</p>
				<p class="txt" style="padding-top: 3px;">
					<span>${course.teacherUnit }</span><span style="padding-left: 6px;"></span><strong
						class="name"><a href="#" style="color: #188dd1">${course.teacherName }老师</a></strong>
				</p>

		</p>
	</div>
	<div class="ico"></div>
	</div>
<!--end #index 首页 -->
<script type="text/javascript">
$(document).ready(function(){
	setMenuSelect("index");
	jQuery("#index_teacher").load("/cms/teacher/showIndex.do");//培训师资系统
	jQuery("#index_talents").load("/train/talentTrain/indexListCourse.do");//人才培训系统
	jQuery("#show_student_content").load("/cms/train/statisticsIndex.do");//人才评估系统	
});
$(function(){
	$('.m-sld').dsTab({
		itemEl        : '.c-img-list li',
		btnElName     : 'm-focus',
		btnItem       : 'a',
	    currentClass  : 'z-crt',
	    maxSize       : 5,
	    changeType    : 'fade',
	    change        : true,
	    changeTime    : 3000,
	    autoCreateTab : false
	});
	
	$('.g-user-cards').rollpic({
		prev : ".u-prev",
		next : ".u-next",
		cont : ".m-user-cards",
		li : "li",
        pause:3000,
        nspd:1000,
        uspd:500,
        vnum:5,
        snum:1,
        //start:0,
        isH:true,
        auto:true

 	});
	turns($(".m-figure-lst .c-item"),218,100);
	
	//移动框
	var boxWidth=200;
	var boxHeight=200;
	var innerWidth=parseInt($(window).width())-200;
	var innerHeight=parseInt($(window).height())-200;
	var offsetX=2;
	var offsetY=2;
	var timer=null;
	var ico=$('.g-movebox .ico');
	starMove();
	function starMove(){
		var timer=setInterval(function(){
			var left=parseInt($('.g-movebox').css("left"));
			var top=parseInt($('.g-movebox').css("top"));
			if(left>=innerWidth||left<=0){
				offsetX=-offsetX	;
			}
			if(top>=innerHeight||top<=0){
				offsetY=-offsetY;
			}
			var valLeft=left+offsetX;
			var valTop=top+offsetY;
			$('.g-movebox').css({
				"left":valLeft+"px",
				"top":valTop+"px"
			});			
		},40);

		$('.g-movebox').mouseover(function(){
				clearInterval(timer);
				ico.show();
				ico.click(function(){
					$('.g-movebox').remove();
					clearInterval(timer);
				});
		});
	}
	$('.g-movebox').mouseout(function(){
				starMove();
				ico.hide();
	});
})
</script>