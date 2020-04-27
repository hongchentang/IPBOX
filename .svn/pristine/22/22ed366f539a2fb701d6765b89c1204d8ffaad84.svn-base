<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>

<form id="deptForm" name="deptForm" action="${ctx}/common/dept/save.do" method="post" enctype="multipart/form-data">
    <div class="easyui-panel" title="" collapsible="true" style="margin-top: 20px;">
        <input type="text" id="parentId" name="parentId" style="display: none;">
        <input type="text" id="id" name="id" style="display: none;" value="${dept.id}">
        <input type="text" id="deptLevel" name="deptLevel" style="display: none;" value="">
        <table  cellpadding="4" cellspacing="4" border="0" class="alter-table-h" style="font-size: 12px;width: 90%;">
            <tr>
                <td style="">
                   名称
                </td>
                <td style="">
                    <input type="text" id="deptName" name="deptName" class="easyui-textbox" data-options="width: 300"/>
                </td>
                <c:if test="${dept != null and dept.id eq dept.unitId}">
                    <td rowspan="5">企业logo</td>
                    <td rowspan="5">
                        <c:choose>
                            <c:when test="${not empty dept.logo}">
                                <c:set value="${ipanthercore:getJSONMap(dept.logo)}" var="map" />
                                <img src="${ctx}${map.downloadUrl}" border="0" style="max-width:94px;max-height:84px">
                                <textarea  name="logo" style="display:none;">${dept.logo}</textarea>
                            </c:when>
                            <c:otherwise>
                                <img src="${ctx}/themes/easyui/themes/tms/images/default.jpg" border="0">
                            </c:otherwise>
                        </c:choose>
                        <div>
                            <input type="file" name="upload" content="<span style='color:red'>尺寸在94px*84px之内<br/>允许上传的文件类型：${fileTypes}<br/>允许上传文件的大小：${fileMaxSize}KB</span>" class="easyui-tooltip" position="left"/>
                        </div>
                    </td>
                </c:if>
            </tr>
            <tr>
                <td style="">
                    邮箱
                </td>
                <td style="">
                    <input type="text" id="linkEmail" name="linkEmail" class="easyui-textbox" data-options="width: 300"/>
                </td>
            </tr>
            <tr>
                <td style="">
                    地址
                </td>
                <td style="">
                    <input type="text" id="deptAddress" name="deptAddress" class="easyui-textbox" data-options="width: 300"/>
                </td>
            </tr>
            <tr>
                <td style="">
                    联系电话
                </td>
                <td style="">
                    <input type="text" id="legalPhone" name="legalPhone" class="easyui-textbox" data-options="width: 300"/>
                </td>
            </tr>

            <c:if test="${dept.id != null and dept.id eq dept.unitId}">
                <tr>
                    <td>
                        统一社会信用代码
                    </td>
                    <td>
                        <input type="text" id="deptCode" name="deptCode" class="easyui-textbox" data-options="width: 300"/>
                    </td>
                </tr>

            </c:if>
        </table>
    </div>

    <div style="text-align: center;padding-left: 40px;padding-top: 10px; padding-bottom: 5px;">
        <button type="button" value="确认" id="=subBut" onclick="save()" class="easyui-linkbutton l-btn" style="margin-right: 20px;">确认</button>
        <button type="reset"  value="关闭" id="subClose" class="easyui-linkbutton l-btn" onclick="closeWindow('showAddDialog')" >关闭</button>
    </div>
</form>

<script type="text/javascript">

    $(document).ready(function (){});

    if ('${dept.id}' !== '') {
        $('#deptForm').form('load', {
            deptName: '${dept.deptName}',
            linkEmail: '${dept.linkEmail}',
            deptAddress: '${dept.deptAddress}',
            legalPhone: '${dept.legalPhone}',
            deptCode: '${dept.deptCode}'
        });
    }

    function save() {
        $.messager.confirm('提示', '确定保存？', function(r){
            if(r) {
                var selectedData = $("#deptTree",getCurrentTab()).tree('getSelected');
                var level = selectedData.level + 1;
                $('#parentId').val(selectedData.id);
                $('#deptLevel').val(level);
                debugger
                jQuery('#deptForm').form('submit', {
                    success: function (data) {
                        var json = jQuery.parseJSON(data);
                        if (!jQuery.isEmptyObject(json)) {
                            var message = json.message;
                            var statusCode = json.statusCode;
                            if (parseInt(statusCode) == 300) {
                                jQuery.messager.alert("提示信息", message, "error");
                            } else if (parseInt(statusCode) == 200) {
                                jQuery.messager.alert("提示信息", message, "info");
                                getCurrentTab().panel('refresh');
                                closeWindow("showAddDialog");
                            }
                        }
                    }
                });
            }

        });
    }
</script>
