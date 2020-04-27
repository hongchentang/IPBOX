<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<div class="easyui-panel" title="" collapsible="true">

    <div class="datagrid-toolbar" float:right>
        <table cellspacing="0" cellpadding="0" class="">
            <tbody>
            <c:if test="${dept.id eq dept.unitId}">
                <tr>
                    <td><a onclick="addDept()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-plus"> 新建下级部门</i></a></td>
                    <td><div class="datagrid-btn-separator"></div></td>
                    <td><a onclick="editDept()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil"> 修改公司信息</i></a></td>
                    <td><div class="datagrid-btn-separator"></div></td>
                </tr>
            </c:if>
            <c:if test="${dept.id != dept.unitId}">
                <tr>
                    <td><a onclick="addDept()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-plus"> 新建部门</i></a></td>
                    <td><div class="datagrid-btn-separator"></div></td>
                    <td><a onclick="editDept()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil"> 修改部门</i></a></td>
                    <td><div class="datagrid-btn-separator"></div></td>
                    <td><a onclick="deleteDept()" href="javascript:void(0)" class="easyui-linkbutton delete-btn"><i class="fa fa-minus"> 删除部门</i></a></td>
                    <td><div class="datagrid-btn-separator"></div></td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>

    <table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
        <tbody>
            <tr>
                <td style="width: 15%;">
                    名称
                </td>
                <td style="width: 30%;">
                    ${dept.deptName}
                </td>
                <c:if test="${dept.id eq dept.unitId}">
                    <td rowspan="5" style="width: 10%;">企业logo</td>
                    <td rowspan="5">
                        <c:choose>
                            <c:when test="${not empty dept.logo}">
                                <c:set value="${ipanthercore:getJSONMap(dept.logo)}" var="map" />
                                <img src="${ctx}${map.downloadUrl}" border="0" style="max-width:188px;max-height:168px">
                                <textarea  name="logo" style="display:none;">${dept.logo}</textarea>
                            </c:when>
                            <c:otherwise>
                                <img src="${ctx}/themes/easyui/themes/tms/images/default.jpg" border="0">
                            </c:otherwise>
                        </c:choose>
                    </td>
                </c:if>
            </tr>
            <tr>
                <td style="">
                    邮箱
                </td>
                <td style="">
                    ${dept.linkEmail}
                </td>
            </tr>
            <tr>
                <td style="">
                    地址
                </td>
                <td style="">
                    ${dept.deptAddress}
                </td>
            </tr>
            <tr>
                <td style="">
                    联系电话
                </td>
                <td style="">
                    ${dept.legalPhone}
                </td>
            </tr>
            <c:if test="${dept.id eq dept.unitId}">
                <tr>
                    <td>统一社会信用代码：</td>
                    <td>${dept.deptCode}</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>