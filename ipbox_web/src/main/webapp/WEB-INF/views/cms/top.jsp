<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/jsp/common/include/taglib.jsp"%>
 	<div class="g-tpc clearfix">
			<div class="m-welcomes">欢迎来到广东省知识产权人才信息化管理门户！</div>
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
					<a href="${ctx}/site/icms/register/index.html" class="reg1" id="u-reg-btn1">注册</a>
				</shiro:guest>
			</div>
	</div>
 
 