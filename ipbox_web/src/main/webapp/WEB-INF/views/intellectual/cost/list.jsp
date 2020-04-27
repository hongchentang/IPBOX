<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>

<c:set var="formId" value="PCF${param.tabId}" />
<c:set var="tableId" value="PCT${param.tabId}" />
<c:set var="tabId" value="${param.tabId}" />
<c:set var="type" value="${searchParam.paramMap.type}" />
<c:set var="status" value="${searchParam.paramMap.status}" />

<form id="${formId}" action="${ctx}/intellectual/patent/cost/list.do?tabId=${tabId}" method="post">
    <div class="easyui-panel" title="" data-options="" style="width: 100%; padding-top: 5px;">
        <div class="datagrid-toolbar" float:right>
            <%-- 隐藏的费用类型筛选 --%>
            <input type="text" id="paramMap[type]" name="paramMap[type]" style="display: none" value="${searchParam.paramMap.type}">
            <%-- 隐藏的缴费状态筛选 --%>
            <input type="text" id="paramMap[status]" name="paramMap[status]" style="display: none" value="${searchParam.paramMap.status}">

            <table cellspacing="0" cellpadding="3px" class="model_interval">
                <tbody>
                <tr>
                    <td>专利名称：</td>
                    <td>
                        <input type='text' name="paramMap[patentName]" class="easyui-validatebox"  value="${searchParam.paramMap.patentName}">
                    </td>
                    <td>专利号/申请号：</td>
                    <td>
                        <input type='text' name="paramMap[appNumber]" class="easyui-validatebox"  value="${searchParam.paramMap.appNumber}">
                    </td>
                    <td>专利权人：</td>
                    <td>
                        <input type='text' name="paramMap[applyer]" class="easyui-validatebox"  value="${searchParam.paramMap.applyer}">
                    </td>
                    <td>
                        <div style="float: right;">
                            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="refreshPatentCost()" iconCls="fa fa-search">查询</a>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
            <table cellspacing="0" cellpadding="0" class="">
                <tbody>
                <tr>
                    <td><a onclick="patentCostDetail()" href="javascript:void(0)" class="easyui-linkbutton"> 查看</a></td>
                    <td><div class="datagrid-btn-separator"></div></td>
                    <td><a onclick="addPatentCost()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-plus"> 新增</i></a></td>
                    <td><div class="datagrid-btn-separator"></div></td>
                    <td><a onclick="updatePatentCost()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil"> 修改</i></a></td>
                    <td><div class="datagrid-btn-separator"></div></td>
                    <td><a onclick="deletePatentCost()" href="javascript:void(0)" class="easyui-linkbutton delete-btn"><i class="fa fa-minus"> 删除</i></a></td>
                    <td><div class="datagrid-btn-separator"></div></td>
                    <c:if test="${status eq 0}" >
                        <td><a onclick="payable()" href="javascript:void(0)" class="easyui-linkbutton"> 确认缴费</a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                        <td><a onclick="gosedEmailpage()" href="javascript:void(0)" class="easyui-linkbutton delete-btn">邮件提醒</a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                            <td><a onclick="retun()" href="javascript:void(0)" class="easyui-linkbutton delete-btn">退款</a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                    <!--    <td><a onclick="alipay()" href="javascript:void(0)" class="easyui-linkbutton delete-btn">支付宝缴费</a></td>
                        <td><div class="datagrid-btn-separator"></div></td>  -->
                    </c:if>
                    <%--<td><a onclick="test()" href="javascript:void(0)" class="easyui-linkbutton delete-btn"><i class="fa fa-minus"> test</i></a></td>
                    <td><div class="datagrid-btn-separator"></div></td>--%>
                </tr>
                </tbody>
            </table>
        </div>
        <div>
            <table id="${tableId}" rowsnumber="true"  pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true" fitColumns="false">
                <thead>
                <tr>
                    <c:if test="${type eq 0}">
                        <%-- 代理服务费 --%>
                        <th width="30" data-options="field:'id',checkbox:true"></th>
                        <th width="200" data-options="field:'typeName', align:'center'">费用类型</th>
                        <th width="100" data-options="field:'costStatus', align:'center'">缴费状态</th>
                        <th width="150" data-options="field:'costName', align:'center'">费用名称</th>
                        <th width="300" data-options="field:'patentName', align:'center'">专利名称</th>
                        <th width="200" data-options="field:'appNumber', align:'center'">专利号</th>
                        <th width="200" data-options="field:'agencyer', align:'center'">代理人</th>
                        <th width="100" data-options="field:'feePayable'">应缴金额（元）</th>
                        <th width="100" data-options="field:'feePayableDate', align:'center'">应缴日期</th>
                        <th width="100" data-options="field:'payment', align:'center'">实缴金额（元）</th>
                        <th width="100" data-options="field:'paymentDate', align:'center'">实缴日期</th>
                        <th width="200" data-options="field:'payor', align:'center'">缴费人</th>
                        <th width="200" data-options="field:'paymenter', align:'center'">费用填报人</th>
                    </c:if>
                    <c:if test="${type eq 1}">
                        <%-- 专利年费 --%>
                        <th width="30" data-options="field:'id',checkbox:true"></th>
                        <c:if test="${status eq 0}">
                            <th width="100" data-options="field:'dueDate', align:'center'">距离期限天数</th>
                        </c:if>
                        <th width="150" data-options="field:'costName', align:'center'">费用名称</th>
                        <th width="200" data-options="field:'paymenter', align:'center'">费用填报人</th>
                        <th width="200" data-options="field:'patentName', align:'center'">专利名称</th>
                        <th width="200" data-options="field:'applyer', align:'center'">专利权人</th>
                        <th width="200" data-options="field:'inventor', align:'center'">发明人</th>
                        <th width="100" data-options="field:'costStatus', align:'center'">缴费状态</th>
                        <th width="100" data-options="field:'feePayableDate', align:'center'">应缴日期</th>
                        <th width="100" data-options="field:'standardDate', align:'center'">缴费绝限日期</th>
                        <th width="100" data-options="field:'standardAmount', align:'center'">标准金额（元）</th>
                        <th width="100" data-options="field:'mitigationRatio', align:'center'">缓减比例（%）</th>
                        <th width="100" data-options="field:'feePayable', align:'center'">应缴金额（元）</th>
                        <c:if test="${status eq 1 or status eq -1}">
                            <th width="100" data-options="field:'payment', align:'center'">实缴金额（元）</th>
                            <th width="200" data-options="field:'payor', align:'center'">缴费人</th>
                            <th width="100" data-options="field:'paymentDate', align:'center'">实缴日期</th>
                        </c:if>

                    </c:if>
                    <c:if test="${type eq 2}">
                        <%-- 专利官费 --%>
                        <th width="30" data-options="field:'id',checkbox:true"></th>
                        <th width="150" data-options="field:'costName', align:'center'">费用名称</th>
                        <th width="300" data-options="field:'patentName', align:'center'">专利名称</th>
                        <th width="300" data-options="field:'appNumber', align:'center'">申请号</th>
                        <th width="300" data-options="field:'applyer', align:'center'">专利权人</th>
                        <th width="300" data-options="field:'inventor', align:'center'">发明人</th>
                        <th width="100" data-options="field:'costStatus', align:'center'">缴费状态</th>
                        <th width="100" data-options="field:'feePayableDate', align:'center'">应缴日期</th>
                        <th width="100" data-options="field:'standardDate', align:'center'">缴费绝限日期</th>
                        <th width="100" data-options="field:'standardAmount', align:'center'">标准金额（元）</th>
                        <th width="100" data-options="field:'mitigationRatio', align:'center'">缓减比例（%）</th>
                        <th width="100" data-options="field:'feePayable', align:'center'">应缴金额（元）</th>
                        <th width="200" data-options="field:'payor', align:'center'">缴费人</th>
                        <th width="100" data-options="field:'paymentDate', align:'center'">实缴日期</th>
                    </c:if>
                    <c:if test="${type eq 3}">
                        <%-- 其他费用 --%>
                        <th width="30" data-options="field:'id',checkbox:true"></th>
                        <th width="150" data-options="field:'costName', align:'center'">费用名称</th>
                        <th width="200" data-options="field:'patentName', align:'center'">专利名称</th>
                        <th width="150" data-options="field:'appNumber', align:'center'">专利号</th>
                        <th width="200" data-options="field:'applyer', align:'center'">专利权人</th>
                        <th width="200" data-options="field:'inventor', align:'center'">发明人</th>
                        <th width="80" data-options="field:'costStatus', align:'center'">缴费状态</th>
                        <th width="90" data-options="field:'standardAmount', align:'center'">标准金额（元）</th>
                        <th width="90" data-options="field:'mitigationRatio', align:'center'">缓减比例（%）</th>
                        <th width="90" data-options="field:'feePayable', align:'center'">应缴金额（元）</th>
                        <th width="80" data-options="field:'feePayableDate', align:'center'">应缴日期</th>
                        <c:if test="${status eq 1 or status eq -1}">
                            <th width="80" data-options="field:'payment', align:'center'">实缴金额（元）</th>
                            <th width="80" data-options="field:'paymentDate', align:'center'">实缴日期</th>
                            <th width="150" data-options="field:'payor', align:'center'">缴费人</th>
                            <th width="150" data-options="field:'paymenter', align:'center'">费用填报人</th>
                        </c:if>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:if test="${type eq 0}">
                    <%-- 代理服务费 --%>
                    <c:forEach items="${patentCostList}" var="patentCost" varStatus="status">
                        <tr>
                            <td>${patentCost.id}</td>
                            <td>${patentCost.typeName}</td>
                            <td>${patentCost.costStatus}</td>
                            <td>${patentCost.costName}</td>
                            <td>${patentCost.patentName}</td>
                            <td>${patentCost.appNumber}</td>
                            <td>${patentCost.agencyer}</td>
                            <td>${patentCost.feePayable}</td>
                            <td><fmt:formatDate value="${patentCost.feePayableDate}"  pattern="yyyy-MM-dd"/></td>
                            <td>${patentCost.payment}</td>
                            <td><fmt:formatDate value="${patentCost.paymentDate}" pattern="yyyy-MM-dd"/></td>
                            <td>${patentCost.payor}</td>
                            <td>${patentCost.paymenter}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${type eq 1}">
                    <%-- 专利年费 --%>
                    <c:forEach items="${patentCostList}" var="patentCost">
                        <tr>
                            <td>${patentCost.id}</td>
                            <c:if test="${status eq 0}">
                                <td>
                                    <c:if test="${patentCost.type eq 1}">
                                        <c:if test="${patentCost.dueDate > 30}">
                                            <div class='badge badge-green'>${patentCost.dueDate}</div>
                                        </c:if>
                                        <c:if test="${patentCost.dueDate <= 30 and patentCost.dueDate > -30}">
                                            <div class='badge badge-red'>${patentCost.dueDate}</div>
                                        </c:if>
                                        <c:if test="${patentCost.dueDate <= -30 and patentCost.dueDate > -180}">
                                            <div class='badge badge-yellow'>${patentCost.dueDate}</div>
                                        </c:if>
                                        <c:if test="${patentCost.dueDate <= -180 and patentCost.dueDate > -300}">
                                            <div class='badge'>${patentCost.dueDate}</div>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${patentCost.type eq 2}">
                                        <c:if test="${patentCost.dueDate > 0}">
                                            <div class='badge badge-red'>${patentCost.dueDate}</div>
                                        </c:if>
                                        <c:if test="${patentCost.dueDate <= 0}">
                                            <div class='badge'>${patentCost.dueDate}</div>
                                        </c:if>
                                    </c:if>
                                </td>
                            </c:if>
                            <td>${patentCost.costName}</td>
                            <td>${patentCost.paymenter}</td>
                            <td>${patentCost.patentName}</td>
                            <td>${patentCost.applyer}</td>
                            <td>${patentCost.inventor}</td>
                            <td>${patentCost.costStatus}</td>
                            <td><fmt:formatDate value="${patentCost.feePayableDate}" pattern="yyyy-MM-dd"/></td>
                            <td><fmt:formatDate value="${patentCost.standardDate}" pattern="yyyy-MM-dd"/></td>
                            <td>${patentCost.standardAmount}</td>
                            <td>${patentCost.mitigationRatio}</td>
                            <td>${patentCost.feePayable}</td>
                            <c:if test="${status eq 1 or status eq -1}">
                                <td>${patentCost.payment}</td>
                                <td>${patentCost.payor}</td>
                                <td><fmt:formatDate value="${patentCost.paymentDate}" pattern="yyyy-MM-dd"/></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${type eq 2}">
                    <%-- 专利官费 --%>
                    <c:forEach items="${patentCostList}" var="patentCost">
                        <tr>
                            <td>${patentCost.id}</td>
                            <td>${patentCost.costName}</td>
                            <td>${patentCost.patentName}</td>
                            <td>${patentCost.appNumber}</td>
                            <td>${patentCost.applyer}</td>
                            <td>${patentCost.inventor}</td>
                            <td>${patentCost.costStatus}</td>
                            <td><fmt:formatDate value="${patentCost.feePayableDate}" pattern="yyyy-MM-dd"/></td>
                            <td><fmt:formatDate value="${patentCost.standardDate}" pattern="yyyy-MM-dd"/></td>
                            <td>${patentCost.standardAmount}</td>
                            <td>${patentCost.mitigationRatio}</td>
                            <td>${patentCost.feePayable}</td>
                            <td>${patentCost.payor}</td>
                            <td><fmt:formatDate value="${patentCost.paymentDate}" pattern="yyyy-MM-dd"/></td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${type eq 3}">
                    <%-- 其他费用 --%>
                    <c:forEach items="${patentCostList}" var="patentCost">
                        <tr>
                            <td>${patentCost.id}</td>
                            <td>${patentCost.costName}</td>
                            <td>${patentCost.patentName}</td>
                            <td>${patentCost.appNumber}</td>
                            <td>${patentCost.applyer}</td>
                            <td>${patentCost.inventor}</td>
                            <td>${patentCost.costStatus}</td>
                            <td>${patentCost.standardAmount}</td>
                            <td>${patentCost.mitigationRatio}</td>
                            <td>${patentCost.feePayable}</td>
                            <td><fmt:formatDate value="${patentCost.feePayableDate}" pattern="yyyy-MM-dd"/></td>
                            <c:if test="${status eq 1 or status eq -1}">
                                <td>${patentCost.payment}</td>
                                <td><fmt:formatDate value="${patentCost.paymentDate}" pattern="yyyy-MM-dd"/></td>
                                <td>${patentCost.payor}</td>
                                <td>${patentCost.paymenter}</td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <jsp:include page="/jsp/common/include/manage_page_table.jsp">
                <jsp:param value="${formId}" name="pageForm" />
                <jsp:param value="${tableId}" name="tableId" />
                <jsp:param value="easyui" name="type" />
                <jsp:param value='${tabId}' name="divId" />
            </jsp:include>
        </div>
    </div>
</form>

<style type="text/css">
    .test{
        border-radius: 9px;
    }

    .selected-tabs-bc{

    }
</style>

<script type="text/javascript">

    $(document).ready(function () {

        var tabId = $("#layout_center_tabs").tabs('getSelected').attr('id');
        var costSmallType = $("#" + tabId).find('input[id^="costSmallType"]');
        var type = '${type}';

        if (type == 1) {
            costSmallType.combobox({
                url: '${ctx}/common/datadict/getByTypeCode.do?typeCode=IPBOX_ANNUAL_FEE',
                emptyText: '',
                valueField: 'dictValue',
                textField: 'dictName',
                slide: true,
                valueFieldID: 'authorCountry',
                limitToList: true,
                panelHeight: '200'
            });
        } else if (type == 2) {
            costSmallType.combobox({
                url: '${ctx}/common/datadict/getByTypeCode.do?typeCode=IPBOX_GOVERNMENT_FEE',
                emptyText: '',
                valueField: 'dictValue',
                textField: 'dictName',
                slide: true,
                valueFieldID: 'authorCountry',
                limitToList: true,
                panelHeight: '200'
            });
        }

        //创建
        createCostListTooltip();
    });

    function addPatentCost() {
        //确定费用类型
     
        var tabSelectType = getType();
        var typeName = getTypeName();

        var windowHeight = 500;
        if(tabSelectType == 1){
            windowHeight = 550;
        }else if(tabSelectType == 2){
            windowHeight = 450;
        }
        if(tabSelectType == 2){
            tabSelectType = 1;
        }

        openWindow('addPatentCostWindow','新增'+ typeName + '单', 800,parseInt(windowHeight),'${ctx}/intellectual/patent/cost/goAddPatentCost.do?type=' + tabSelectType,true);
    }

    function updatePatentCost() {

        //确定费用类型
        var table = $('#' + getTableId());
        var typeName = getTypeName();
        var tabSelectType = getType();
        var objects = table.datagrid('getSelections');

        if(objects.length == 0){
            $.messager.alert('消息','请选择一条数据');
            return false;
        }
        if(objects.length > 1){
            $.messager.alert('消息','不能同时编辑多条数据');
            return false;
        }

        var id = objects[0].id;
        if(tabSelectType == 2){
            tabSelectType = 1;
        }
        openWindow('editPatentCostWindow','修改' + typeName + '单',800,500,'${ctx}/intellectual/patent/cost/goAddPatentCost.do?id=' + id + '&type=' + tabSelectType,true);
    }

    function patentCostDetail() {

        //确定费用类型
        var table = $('#' + getTableId());
        var typeName = getTypeName();
        var objects = table.datagrid('getSelections');

        if(objects.length == 0){
            $.messager.alert('消息','请选择一条数据');
            return false;
        }
        if(objects.length > 1){
            $.messager.alert('消息','不能同时查看多条数据');
            return false;
        }

        var id = objects[0].id;
        openWindow('patentCostDetailWindow','查看' + typeName + '单',800,500,'${ctx}/intellectual/patent/cost/detail.do?id=' + id + '',true);
    }

    function deletePatentCost() {
        //确定费用类型
        var table = $('#' + getTableId());
        var objects = table.datagrid('getSelections');

        if(objects.length == 0){
            $.messager.alert('消息','请选择要删除的数据');
            return false;
        }
        var id = objects[0].id;
        $.messager.confirm('确认','确定想要删除选中的记录吗？', function(flag){
            if(flag){
                $.ajax({
                    url:'${ctx}/intellectual/patent/cost/deletePatentCost.do?id='+id,
                    type:'post',
                    success:function(){
                        $.messager.alert('提示','操作成功！');
                        jQuery('#'+getCurrentTabId()).panel('refresh');
                    }
                });
            }
        });
    }
    
    
    function gosedEmailpage() {
        var table = $('#' + getTableId());
        var objects = table.datagrid('getSelections');

        if(objects.length == 0){
            $.messager.alert('消息','请选择数据');
            return false;
        }
        var id = objects[0].id;
     var vue="";
                    
        openWindow('patentCostPayableWindows','选择邮箱', 700,500,'${ctx}/intellectual/patent/cost/gosendEmailpage.do?id=' + id+'&name='+ vue + '',true);
    }
    
    

    function sendEmail() {
        //确定费用类型
        var table = $('#' + getTableId());
        var objects = table.datagrid('getSelections');

        if(objects.length == 0){
            $.messager.alert('消息','请选择数据');
            return false;
        }
        var id = objects[0].id;
        $.ajax({
            url:'${ctx}/intellectual/patent/cost/sendEmail.do?id='+id,
            type:'GET',
            dataType:'Json',
            success:function(data){
                if(data.code == 200){
                    $.messager.alert('成功', '邮件已发送');
                }else {
                    $.messager.alert('错误', data.msg);
                }
            }
        });
    }

    function payable(){
        //确定费用类型
        var table = $('#' + getTableId());
        var typeName = getTypeName();
        var objects = table.datagrid('getSelections');

        if(objects.length == 0){
            $.messager.alert('消息','请选择一条数据');
            return false;
        }
        if(objects.length > 1){
            $.messager.alert('消息','不能同时查看多条数据');
            return false;
        }

        var id = objects[0].id;

        openWindow('patentCostPayableWindow','缴纳' + typeName,330,230,'${ctx}/intellectual/patent/cost/goPayable.do?id=' + id + '',true);
    }

    /*关闭子窗口*/
    function doCloseCostWin(){
        closeWindow("addPatentCostWindow");
        closeWindow("editPatentCostWindow");
        closeWindow("patentCostDetailWindow");
    }

    /*刷新*/
    function refreshPatentCost() {
        console.log(">>>>费用刷新！");

        var formPanel = getFormId();
        var tabId = getThisSelectTabId();

        easyuiUtils.ajaxPostFormForPanel(formPanel , tabId)
    }

    
    function createCostListTooltip() {
        var tableId = getTableId();
        var tst = getThisSelectTabId();
        var t = $('#' + tst + ' #' + tableId);
        var url = '${ctx}/intellectual/patent/cost/detailInfo.do';
        //createTooltip(t, url, 800, 500);
    }

    function test() {
        /*var tableId = getTableId();
        var tst = getThisSelectTabId();
        var t = $('#' + tst + ' #' + tableId);
        createTooltip(t);*/
        var secType = getCurrentTab();
        var tabs = secType.find('div[id^="viewPatentCostTopTabs"]');
        var ul = tabs.find('ul[class^="tabs"]');
        var children = ul.children();
        children.each(function () {
            var x = $(this);
            x.find('a[class^="tabs-inner"]').addClass('test');
        });
        tabs.tabs({
            onSelect: function (title, index) {
                children.each(function (idx) {
                    var x = $(this);
                    if(index == idx){
                        x.find('a[class^="tabs-inner"]').css('background-color','cornflowerblue');
                    }else {
                        x.find('a[class^="tabs-inner"]').css('background-color','#fff');
                    }
                });
            }
        });
    }
    
    function alipay() {
        //确定费用类型
        var table = $('#' + getTableId());
        var typeName = getTypeName();
        var objects = table.datagrid('getSelections');

        if(objects.length == 0){
            $.messager.alert('消息','请选择一条数据');
            return false;
        }
        if(objects.length > 1){
            $.messager.alert('消息','不能同时查看多条数据');
            return false;
        }

        var id = objects[0].id;

        window.open("${ctx}/alipay/pay.do?id=" + id);
    }
    function retun() {
        //确定费用类型
        var table = $('#' + getTableId());
        var typeName = getTypeName();
        var objects = table.datagrid('getSelections');

        if(objects.length == 0){
            $.messager.alert('消息','请选择一条数据');
            return false;
        }
        if(objects.length > 1){
            $.messager.alert('消息','不能同时查看多条数据');
            return false;
        }

        var id = objects[0].id;
        $.ajax({
            url:'${ctx}/alipay/retu.do?id='+id,
            type:'GET',
            success:function(data){
                debugger
                if(data.code == 200){
                    $.messager.alert('成功', '退款成功');
                }else {
                    $.messager.alert('退款失败', data.msg);
                }
            }
        });

       /* window.open("${ctx}/alipay/retu.do?id=" + id);*/
    }

</script>