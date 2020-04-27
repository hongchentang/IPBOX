<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.hcis.ipanther.common.login.vo.LoginUser" %>
<%@ include file="/jsp/common/include/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>${ipanthercore:getAppName()}</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="">
    <meta http-equiv="description" content="">
    <%@ include file="/themes/skin/ipr/inc.jsp" %>

</head>

<input type="hidden" id="ctx" value="${pageContext.request.contextPath}">
<%
    //如果模块是TEACHER 则转到教师个人中心
    String moduleId = session.getAttribute("moduleId") == null ? "" : session.getAttribute("moduleId").toString();
    if ("TEACHER".equals(moduleId)) {
        //response.sendRedirect("/tms/teacher/index.do");
    } else {
        //如果是教师身份，则返回选择角色页面
        LoginUser loginUser = LoginUser.loginUser(request);
        //if(loginUser.getRoleCode().intValue()==2){
        //	//response.sendRedirect("/entrance.do");
        //}
    }
%>
<c:set value="<%=moduleId%>" var="moduleId"/>
<!-- BEGIN BODY -->
<body id="index" class="easyui-layout" style="width: 100%;height: 100%">
    <%--div data-options="region:'east',split:true" title="East" style="width:100px;"></div>--%>
    <%--头部--%>
    <div data-options="region:'north'" style="width: 100%; height:66px; border: 0;">
        <jsp:include page="/themes/skin/ipr/header.jsp"/>
    </div>
    <%--center 左侧Tab栏--%>
    <div data-options="region:'west',split:false" title="" class="side-wrap" style="width: 200px;height: 100%;border-left: 0;text-align: center">
        <jsp:include page="/themes/skin/ipr/menu_left.jsp"/>
    </div>
    <%--底部--%>
    <div data-options="region:'south',split:false" class="footer-wrap"
         style="width: 100%;height: 25px; overflow: hidden; text-align:center">
        <jsp:include page="/themes/skin/ipr/footer.jsp"/>
    </div>
    <%--center 右侧内容显示--%>
    <div data-options="region:'center',title:''" class="center-wrap" style="width: 85%;overflow: hidden;">
        <jsp:include page="/themes/skin/ipr/center.jsp"/>
    </div>
<script>
    $(document).ready(function (e) {
        $('#layout_center_tabs').tabs('getTab', '系统首页').panel('refresh', '${ctx}/index_content.do');
    });


    function chooseSite(valueId, valueName) {
        var url = "${ctx}/cms/site/selectSite.do?paramMap[contentId]=index&paramMap[dialogId]=chooseSiteWindow&paramMap[entityId]="
            + valueId + "&paramMap[entityName]=" + valueName + "&paramMap[type]=text";
        openWindow('chooseSiteWindow', '选择站点', 500, 700, url, true);
    }

</script>

</body>
</html>
