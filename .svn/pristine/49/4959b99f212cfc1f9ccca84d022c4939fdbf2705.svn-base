<%--
  User: zhw
  Date: 2019/9/2
  Time: 9:05
  --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>

<div title="" class="panel-body panel-body-noheader panel-body-noborder" id="" style="width: 100%; height: 508px;">
    <form id="listAgencyForm" action="${ctx}/intellectual/agency/list.do?tabId=${param.tabId}" method="post">
        <div class="easyui-panel" title="" data-options="" style="width: 100%; padding-top: 5px;">
            <div class="datagrid-toolbar" float:right>
                <table cellspacing="0" cellpadding="3px" class="model_interval">
                    <tbody>
                    <tr>
                        <td>机构名称：</td>
                        <td>
                            <input type='text' name="paramMap[patentName]" class="easyui-validatebox"  value="${searchParam.paramMap.patentName}">
                        </td>
                        <td colspan="">
                            <div style="float: right;">
                                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="refreshAgency()" iconCls="fa fa-search">查询</a>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <table cellspacing="0" cellpadding="0" class="">
                    <tbody>
                    <tr>
                        <td><a onclick="agencyDetail()" href="javascript:void(0)" class="easyui-linkbutton"> 查看</a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                        <td><a onclick="addAgency()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-plus"> 新增</i></a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                        <td><a onclick="updateAgency()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil"> 修改</i></a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                        <td><a onclick="deleteAgency()" href="javascript:void(0)" class="easyui-linkbutton delete-btn"><i class="fa fa-minus"> 删除</i></a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div>
                <table id="listAgencyTable" rowsnumber="true"  pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
                    <thead>
                        <tr>
                            <th width="30" data-options="field:'id',checkbox:true"></th>
                            <th width="150" data-options="field:'agencyName'">机构名称</th>
                            <th width="150" data-options="field:'agencyCode'">机构编号</th>
                            <th width="200" data-options="field:'agencyAddress'">机构地址</th>
                            <th width="150" data-options="field:'agencyMOBILE'">机构电话</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="agency">
                            <tr>
                                <td>${agency.id}</td>
                                <td>${agency.agencyName}</td>
                                <td>${agency.agencyCode}</td>
                                <td>${agency.agencyAddress}</td>
                                <td>${agency.agencyMobile}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <jsp:include page="/jsp/common/include/manage_page_table.jsp">
                    <jsp:param value="listAgencyForm" name="pageForm" />
                    <jsp:param value="listAgencyTable" name="tableId" />
                    <jsp:param value="easyui" name="type" />
                    <jsp:param value='${param.tabId}' name="divId" />
                </jsp:include>
            </div>
        </div>
    </form>
</div>

<script>
    function addAgency() {

        openWindow('addAgencyWindow','新增代理机构',800,300,'${ctx}/intellectual/agency/goAddAgency.do?', true);
    }

    function updateAgency() {

        var objects = $("#listAgencyTable").datagrid('getSelections');

        if(objects.length == 0){
            $.messager.alert('消息','请选择一条数据');
            return false;
        }
        if(objects.length > 1){
            $.messager.alert('消息','不能同时编辑多条数据');
            return false;
        }
        var id = objects[0].id;
        openWindow('editAgencyWindow','查看代理机构',800,300,'${ctx}/intellectual/agency/goAddAgency.do?id=' + id ,true);
    }

    function agencyDetail() {

        var objects = $("#listAgencyTable").datagrid('getSelections');

        if(objects.length == 0){
            $.messager.alert('消息','请选择一条数据');
            return false;
        }
        if(objects.length > 1){
            $.messager.alert('消息','不能同时查看多条数据');
            return false;
        }

        var id = objects[0].id;
        openWindow('agencyDetailWindow','查看代理机构',800,300,'${ctx}/intellectual/agency/detail.do?id=' + id + '',true);
    }

    function deleteAgency() {
        var objects = $('#listAgencyTable').datagrid('getSelections');
        if(objects.length == 0){
            $.messager.alert('消息','请选择要删除的数据');
            return false;
        }
        var id = objects[0].id;
        $.messager.confirm('确认','确定想要删除选中的记录吗？', function(flag){
            if(flag){
                $.ajax({
                    url:'${ctx}/intellectual/agency/delete.do?id='+id,
                    type:'post',
                    success:function(){
                        $.messager.alert('提示','操作成功！');
                        jQuery('#'+getCurrentTabId()).panel('refresh');
                    }
                });
            }
        });
    }

    function doCloseAgencyWindow() {
        closeWindow("addAgencyWindow");
        closeWindow("agencyDetailWindow");
        closeWindow("editAgencyWindow");
    }

    function refreshAgency() {
        easyuiUtils.ajaxPostFormForPanel('listAgencyForm' , '${param.tabId}')
    }
</script>