<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/18
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>

<div id="viewOauthPatentPanel" class="easyui-panel" data-options="" style="height:98%">
    <div id="viewOauthPatentListTabs" class="easyui-tabs" fit="true" data-options="tabPosition:'left',height:getHeight('viewOauthPatentPanel',100),cache:false">
        <div id="viewUserBaseTopTab" data-options="href:'${ctx}/oauth/patent/basicDetail.do?appNumber=${appNumber}'" title="专利基本信息" ></div>
        <c:if test="${not empty appNumber}">
            <div id="view1" data-options="href:'${ctx}/oauth/patent/basicDetail.do?appNumber=${appNumber}&type=1'" title="摘要附图" ></div>
            <div id="view2" data-options="href:'${ctx}/oauth/patent/basicDetail.do?appNumber=${appNumber}&type=2'" title="说明书附图" ></div>
            <div id="view3" data-options="href:'${ctx}/oauth/patent/appearance.do?id=${pid}'" title="外观图片" ></div>
            <div id="view4" data-options="href:'${ctx}/oauth/patent/PDF.do?id=${pid}'" title="PDF全文" ></div>
        </c:if>
    </div>
</div>
