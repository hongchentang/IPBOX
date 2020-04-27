<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@ include file="/jsp/common/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8" />
<%--地址栏的区域和session的不一致时，重新设置session的区域 --%>
<c:if test="${not empty regions and regions ne globalRegions}">
	<c:set var="globalRegions" value="/${regions}" scope="session"/>
</c:if>
<c:set var="regionsName" value="${ipanthercommon:getSiteName(globalRegions)}"/>
<%--不存在的站点，重定向到404页面 --%>
<c:if test="${empty regionsName }">
	<c:set var="globalRegions" value="" scope="session"/>
	<script type="text/javascript">
		window.location.href="${ctx}/jsp/common/404.jsp";
	</script>
</c:if>
<title>${regionsName}知识产权人才信息化管理</title>
<link rel="stylesheet" href="${ctx}/themes/itlt-ppt/css/reset.css">
<link rel="stylesheet" href="${ctx}/themes/itlt-ppt/css/style.css">
<script type="text/javascript" src="${ctx}/themes/itlt-ppt/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/themes/itlt-ppt/js/jquery.dsTab.js"></script>
<script type="text/javascript" src="${ctx}/themes/itlt-ppt/js/function.js"></script>
<script type="text/javascript" src="${ctx}/themes/itlt-ppt/js/scrollpic_jq.js"></script>
<!-- echarts图表 -->
<script src="${ctx}/js/echarts/echarts-all.js" type="text/javascript"></script>
<!-- 引入layerjs -->
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
</head>

<body style="background-color: white;">
<div id="g-tp">
<div class="g-tpc clearfix">
	
	
	<div class="m-welcomes">欢迎来到${regionsName}知识产权人才信息化管理门户！</div>
	<div class="m-slt-area">
		<a href="javascript:void(0);" class="show"><span>${regionsName}</span><i class="trg"></i></a>
		<div class="ly" id="addressLy">
					<i class="trg1"></i>
					<i class="trg2"></i>
					<div class="m-slt-region">
						<div >当前区域[<span style="font-weight: bolder;">${regionsName}</span>]</div>
						<ul>
							<c:forEach items="${ipanthercommon:getSiteList()}" var="site">
								<li style="display: inline;width: 40px;float: left;">
									<a style="color: #188DD1;text-decoration: underline;" href="${ctx}/site${globalRegions}/changeSite.do?site=${site.dictName}">${site.dictValue}</a>
<%-- 									<a style="color: #188DD1;text-decoration: underline;" href="${ctx}/site${globalRegions}/changeSite.do?site=${site.dictName}&url=${currentRequestUrl}">${site.dictValue}</a> --%>
								</li>
							</c:forEach>
						</ul>
					</div>
		</div>
	</div>
	<div class="m-lr">
		<shiro:user>
		    	<a href="${ctx}/index.do"><span>
		    		<strong>当前用户</strong>：<shiro:principal property="realName"/>
		    	</span>
		    	<span>/</span>
		    	<span>
		    		<%--<strong>机构</strong>：--%>
	                <c:set var="myRegionNameMap" value="${ipanthercommon:getRegionNameTreeMap(sessionScope.loginUser.regionsCode)}"/>
				    ${myRegionNameMap.province} ${myRegionNameMap.city} ${myRegionNameMap.counties} 
	                ${sessionScope.loginUser.deptName}        
		    	</span>		
		    	</a>	
		    	<%--有个人中心权限的用户才能进入个人中心 --%>
		    	<c:if test="${ipanthercommon:isSpace(sessionScope.loginUser.roleCode)}">
		    		<span>|</span>
		    		<a href="${ctx}/space/index.do" class="log1" id="u-log-btn1">个人中心</a>
		    	</c:if>
		    	<span>|</span>
		    	<a href="${ctx}/logout.do" class="log1" id="u-log-btn1">退出</a>
      		 </shiro:user>
		<shiro:guest>
			<a href="${ctx}/login.do" class="log1" id="u-log-btn1" >登录</a>	
			<span>|</span>
			<a href="${ctx}/site/register.do" class="reg1" id="u-reg-btn1">注册</a>
		</shiro:guest>
	</div>
</div>
</div>
<div id="g-hd">
	<div class="g-hdc clearfix">
		<h1 id="logo"><a href="/">${regionsName}知识产权人才信息化管理</a></h1>
		<form name="searchForm" id="searchForm" action="${ctx}/site${globalRegions}/search.do" method="post" onsubmit="return searchSite()">
		<div class="m-multi-srh">
			<select style="float: right; margin-left: -7px; position: relative;" name="paramMap[type]">
				<option value="">全部</option>
				<option value="dynamic" ${searchParam.paramMap.type eq 'dynamic'?'selected':''}>培训动态</option>
				<option value="inform" ${searchParam.paramMap.type eq 'inform'?'selected':''}>通知要闻</option>
				<option value="course" ${searchParam.paramMap.type eq 'course'?'selected':''}>培训课程</option>
			</select>
			<label class="block" style="float: right;">
				<input type="text" id="keyword" name="paramMap[keyword]" value="${searchParam.paramMap.keyword}" placeholder="搜索" class="ipt" style="width:200px">
			</label>
			<button type="submit" class="u-sch-btn" >
				<i class="ico"></i>
			</button>
		</div>
		</form>
	</div>
</div>
<!--start #g-menu 导航 -->
<div id="g-menu">
	<div class="g-nvc clearfix">
		<a href="${ctx}/site${globalRegions}/index.do" class="index">首页</a>
		<a href="javascript:void(0);" class="inform" onclick="queryPost('${ctx}/site${globalRegions}/inform/list.do')">通知要闻</a>
		<a href="${ctx}/site${globalRegions}/dynamic/list.do" class="dynamic">培训动态</a>
		<a href="${ctx}/site${globalRegions}/train/list.do" class="train">人才培训系统(培训课程)</a>
		<a href="${ctx}/site${globalRegions}/teacher/list.do" class="teacher">培训师资系统(师资库)</a>
		<a href="${ctx}/site${globalRegions}/stat.do" class="stat">人才评估系统(数据管理)</a>
		<a href="${ctx}/site${globalRegions}/contactUs.do" class="contactUs">联系我们</a>
	</div>
</div>
<div id="g-bd">

	<decorator:body/>
	
</div>
<!--start #g-ft 底部 -->
<div id="g-ft">
	<div class="g-ftc">
		<p class="c-txt">主办单位：广州恒成智道信息科技有限&nbsp;|&nbsp;网站管理：广州恒成智道信息科技有限</p>
		<p class="c-txt">Copyright <em>©</em> 2019. All rights reserved</p>
	</div>
</div>


<!--start 侧边栏客服 -->
<div class="g-side-service">
    <a href="javascript:void(0);" onclick="OnlineMessage()">在线留言</a>
</div>
<form id="submitForm" action='' method='post' style="display: none;"> 
</form>
</body>
<script type="text/javascript">

//在线留言方法
function OnlineMessage(){
	
	layer.open({
		 type:2,
		 title:'在线留言',
		 maxmin:false,
		 shadeClose:false,
		 area:['550px','350px'],
		 content: '${ctx}/cms/userQuestion/goAddMessage.do'
	 });
}

//设置菜单选中
function setMenuSelect(_class) {
	$(".g-nvc").children().removeClass("z-crt");
	$(".g-nvc ."+_class).addClass("z-crt");
}

//首页查询
function searchSite() {
	var keyword = $("#keyword").val();
	if(""==$.trim(keyword)) {
		alert("请输入关键字");
		return false;
	}
	return true;
}
//转换post请求	
function queryPost(url) {
	
		if (url != ' ') {
			var url = url;
			//更改form的action  
			$("#submitForm").attr("action", url);
			//触发submit事件，提交表单   
			$("#submitForm").submit();
		}
	}
</script>
</html>