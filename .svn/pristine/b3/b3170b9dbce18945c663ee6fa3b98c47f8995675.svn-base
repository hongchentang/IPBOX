<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/18
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>

<div id="patentCostTabPanel" class="easyui-panel" data-options="" style="height:98%">
    <div id="patentCostTab" class="easyui-tabs" fit="true" data-options="tabPosition:'left',height:getHeight('patentCostTabPanel',100),cache:false">
        <div id="patentCostInfo" data-options="href:'${ctx}/intellectual/patent/cost/detailInfo.do?id=${id}'" title="基本信息" ></div>
        <c:if test="${not empty id}">
            <div id="invoiceFileInfo" data-options="href:'${ctx}/intellectual/patent/cost/attachment.do?id=${id}'" title="缴费发票" ></div>
        </c:if>
    </div>
</div>
