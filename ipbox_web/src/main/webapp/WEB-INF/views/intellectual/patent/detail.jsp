<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/18
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>

<div id="viewUserTopPanel" class="easyui-panel" data-options="" style="height:98%">
    <div id="viewUserTopListTabs" class="easyui-tabs" fit="true" data-options="tabPosition:'left',height:getHeight('viewUserTopPanel',100),cache:false">
        <div id="viewUserBaseTopTab" data-options="href:'${ctx}/intellectual/patent/detailInfo.do?id=${id}'" title="案件/专利基本信息" ></div>
        <c:if test="${not empty id}">
            <div id="viewUserRewardTopTab" data-options="href:'${ctx}/intellectual/patent/detailAgency.do?id=${id}'" title="代理基本信息" ></div>
            <div id="viewUserRewardTopTab" data-options="href:'${ctx}/intellectual/patent/detailCustomer.do?id=${id}'" title="客户基本信息" ></div>
            <div id="viewUserRewardTopTab" data-options="href:'${ctx}/intellectual/patent/detailAttachment.do?id=${id}'" title="附件信息" ></div>
        </c:if>
    </div>
</div>
