<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<!--start #g-bd 中间 -->
<div id="g-bd">
	<!--start #contactUs 联系我们 -->
	<div id="contactUs">
		<div class="g-page-wrap">
			<div class="g-page-box">
				<div class="m-tt2">
					<div class="m-crm">
						<span class="c-txt">您的位置：</span>
						<a href="${ctx}/site${globalRegions}/index.do">首页</a>
						<span class="line">></span>
						<span class="z-crt">
						 人才评估系统
						</span>
					</div>
				</div><!--end .m-tt2-->	
				<div class="m-page-dt">
				    <div class="m-site-img" id="content">
						
					
					</div>
					
					<div class="m-site-img" id="content_train">
						
					
					</div>
					<div class="m-site-img" id="content_train_m">
						
					
					</div>
					<div class="m-site-img" id="content_r_m">
						
					
					</div>
					<div class="m-site-img" id="content_region">
						
					
					</div>
					<div class="m-site-img" id="content_type">
						
					
					</div>
				</div>
			</div><!--end .g-page-box -->
		</div>
	</div>
	<!--end #contactUs 联系我们 -->
</div>
<!--end #g-bd 中间 -->
<script type="text/javascript">
$(document).ready(function(){
	setMenuSelect("stat");
  	jQuery("#content").load("${ctx}/train/talentTrain/registerStatistics.do");
  	jQuery("#content_train").load("${ctx}/cms/train/statisticsTrainYear.do");
  	jQuery("#content_train_m").load("${ctx}/cms/train/statisticsTrainMonth.do");
  	jQuery("#content_r_m").load("${ctx}/cms/train/statisticsRegisterCount.do");
  	jQuery("#content_region").load("${ctx}/cms/train/statisticsRegionCount.do");
  	jQuery("#content_type").load("${ctx}/cms/train/statisticsTrainType.do");
});
</script>