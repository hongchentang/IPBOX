<%--
  User: zhw
  Date: 2019/9/2
  Time: 9:05
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>

<div id="agencyDetail">
    <div class="easyui-panel" title="基础信息" collapsible="true">
        <table style="font-size: 12px;width: 90%;" cellpadding="4" cellspacing="4" border="0" class="alter-table-v-space" >
            <tr>
                <td>代理机构名称</td>
                <td>${agency.agencyName}</td>
                <td>代理机构编码</td>
                <td>${agency.agencyCode}</td>
            </tr>
            <tr>
                <td>代理机构地址</td>
                <td>${agency.agencyAddress}</td>
                <td>代理机构邮箱</td>
                <td>${agency.agencyEmail}</td>
            </tr>
            <tr>
                <td>代理机构联系方式</td>
                <td>${agency.agencyMobile}</td>
                <td>代理人</td>
                <td>${agency.agencyer}</td>
            </tr>
            <tr>
                <td>代理人联系方式</td>
                <td>${agency.agencyerMobile}</td>
            </tr>
        </table>
    </div>
</div>