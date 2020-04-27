<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="patentDetail">
    <c:set value="${patentDetail}" var="patent"/>
    <table style="font-size: 12px;width: 90%;" class="alter-table-v-space" cellspacing="0">
        <tr>
            <td width="25%">客户名称：</td>
            <td width="25%">${patent.customerName}</td>
            <td width="25%">客户代码：</td>
            <td width="25%">${patent.customerCode}</td>
        </tr>
        <tr>
            <td> 客户联系人：</td>
            <td>${patent.customerContacts}</td>
            <td>代理机构地址：</td>
            <td>${patent.customerSource}</td>
        </tr>

        <tr>
            <td> 客户地址：</td>
            <td>${patent.customerAddress}</td>
        </tr>
    </table>
</div>