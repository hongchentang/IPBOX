<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>


<div id="patentCodeDetail">
    <%-- 代理服务费 --%>
    <c:if test="${cost.costType eq 0}">
        <table  cellspacing="0" class="alter-table-v" style="font-size: 12px;width: 90%;">
            <tr>
                <td>费用填报人*</td>
                <td>
                        ${cost.paymenter}
                </td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>专利名称*</td>
                <td>
                        ${cost.patentName}
                </td>
                <td>专利号*</td>
                <td>
                        ${cost.appNumber}
                </td>
            </tr>
            <tr>
                <td>缴费状态*</td>
                <td>
                        <c:if test=" ${cost.costStatus eq 0}">
                            已缴费
                        </c:if>
                       <c:if test=" ${cost.costStatus eq 1}">
                           未缴费
                       </c:if>
                </td>
                <td>缴费区域*</td>
                <td>
                        ${dict:getEntryName('IPR_COUNTRY',cost.costArea)}
                </td>
            </tr>
            <tr>
                <td>应缴金额（元）*</td>
                <td>
                        ${cost.feePayable}
                </td>
                <td>应缴费日期*</td>
                <td>
                    <fmt:formatDate value="${cost.feePayableDate}" pattern="yyyy-MM-dd"/>
                </td>
            </tr>
            <tr>
                <td>费用说明</td>
                <td colspan="3" style="text-align: initial; ">
                        ${cost.costRemark}
                </td>
            </tr>
            <tr>
                <td>代理机构</td>
                <td>
                        ${cost.agencyName}
                </td>
                <td>实缴金额（元）</td>
                <td>
                        ${cost.paymentAmount}
                </td>
            </tr>
            <tr>
                <td>实缴日期</td>
                <td>
                    <fmt:formatDate value="${cost.paymentDate}" pattern="yyyy-MM-dd"/>
                </td>
                <td>缴费人</td>
                <td>
                        ${cost.payor}
                </td>
            </tr>
            <tr>
                <td>官费回执编号</td>
                <td>
                        ${cost.receiptNumber}
                </td>
                <td>官方回执日期</td>
                <td>
                    <fmt:formatDate value="${cost.receiptDate}" pattern="yyyy-MM-dd"/>
                </td>
            </tr>
        </table>
    </c:if>

    <%-- 专利年费 --%>
    <c:if test="${cost.costType eq 1}">
        <table  cellspacing="0" class="alter-table-v" style="font-size: 12px;width: 90%;">
            <tr>
                <td>费用名称</td>
                <td>${cost.costName}</td>
                <td>费用填报人</td>
                <td>${cost.paymenter}</td>
            </tr>
            <tr>
                <td>专利名称</td>
                <td>${cost.patentName}</td>
                <td>专利号</td>
                <td>${cost.appNumber}</td>
            </tr>
            <tr>
                <td>缴费状态</td>
                <td>
                    <c:if test="${cost.costStatus eq 0}">
                        已缴费
                    </c:if>
                    <c:if test="${cost.costStatus eq 1}">
                        未缴费
                    </c:if>
                </td>
                <td>缴费区域</td>
                <td>
                        ${dict:getEntryName('IPR_COUNTRY',cost.costArea)}
                </td>
            </tr>
            <tr>
                <td>标准缴费金额（元）</td>
                <td>${cost.standardAmount}</td>
                <td>应缴费日期</td>
                <td>
                    <fmt:formatDate value="${cost.feePayableDate}" pattern="yyyy-MM-dd"/>
                </td>
            </tr>
            <tr>
                <td>专利权人</td>
                <td>${cost.applyer}</td>
                <td>发明人</td>
                <td>${cost.inventor}</td>
            </tr>
            <tr>
                <td>费用说明</td>
                <td colspan="3">
                        ${cost.costRemark}
                </td>
            </tr>
            <tr>
                <td>费用缓减比例（%）</td>
                <td>${cost.mitigationRatio}</td>
                <td>实缴金额（元）</td>
                <td>${cost.paymentAmount}</td>
            </tr>
            <tr>
                <td>实缴日期</td>
                <td>
                    <fmt:formatDate value="${cost.paymentDate}" pattern="yyyy-MM-dd"/>
                </td>
                <td>缴费人</td>
                <td>${cost.payor}</td>
            </tr>
            <tr>
                <td>官方回执编号</td>
                <td>${cost.receiptNumber}</td>
                <td>官方回执日期</td>
                <td>
                    <fmt:formatDate value="${cost.receiptDate}" pattern="yyyy-MM-dd"/>
                </td>
            </tr>
        </table>
    </c:if>

    <%-- 专利官费 --%>
    <c:if test="${cost.costType eq 2}">
        <table  cellspacing="0" class="alter-table-v" style="font-size: 12px;width: 90%;">
            <tr>
                <td>费用名称</td>
                <td>${cost.costName}</td>
                <td>费用填报人</td>
                <td>${cost.paymenter}</td>
            </tr>
            <tr>
                <td>专利名称</td>
                <td>${cost.patentName}</td>
                <td>专利号</td>
                <td>${cost.appNumber}</td>
            </tr>
            <tr>
                <td>缴费状态</td>
                <td>
                    <c:if test=" ${cost.costStatus eq 0}">
                        已缴费
                    </c:if>
                    <c:if test=" ${cost.costStatus eq 1}">
                        未缴费
                    </c:if>
                </td>
                <td>标准缴费金额（元）</td>
                <td>${cost.standardAmount}</td>
            </tr>
            <tr>
                <td>实缴金额（元）</td>
                <td>${cost.paymentAmount}</td>
                <td>应缴日期</td>
                <td><fmt:formatDate value="${cost.feePayableDate}" pattern="yyyy-MM-dd"/></td>
            </tr>
            <tr>
                <td>费用说明</td>
                <td colspan="3">
                        ${cost.costRemark}
                </td>
            </tr>
            <tr>
                <td>实缴日期</td>
                <td><fmt:formatDate value="${cost.paymentDate}" pattern="yyyy-MM-dd"/></td>
                <td>缴费人</td>
                <td>${cost.payor}</td>
            </tr>
        </table>
    </c:if>

    <%-- 其他费用 --%>
    <c:if test="${cost.costType eq 3}">
        <table cellspacing="0" class="alter-table-v" style="font-size: 12px;width: 90%;">
            <tr>
                <td>费用名称</td>
                <td>${cost.costName}</td>
                <td>费用填报人</td>
                <td>${cost.paymenter}</td>
            </tr>
            <tr>
                <td>专利名称</td>
                <td>${cost.patentName}</td>
                <td>专利号</td>
                <td>${cost.appNumber}</td>
            </tr>
            <tr>
                <td>缴费状态</td>
                <td>
                    <c:if test=" ${cost.costStatus eq 0}">
                        已缴费
                    </c:if>
                    <c:if test=" ${cost.costStatus eq 1}">
                        未缴费
                    </c:if>
                </td>
                <td>缴费区域</td>
                <td>
                        ${dict:getEntryName('IPR_COUNTRY',cost.costArea)}
                </td>
            </tr>
            <tr>
                <td>应缴金额（元）</td>
                <td>${cost.feePayable}</td>
                <td>应缴日期</td>
                <td><fmt:formatDate value="${cost.feePayableDate}" pattern="yyyy-MM-dd"/></td>
            </tr>
            <tr>
                <td>费用说明</td>
                <td colspan="3">
                    ${cost.costRemark}
                </td>
            </tr>
            <tr>
                <td>代理机构</td>
                <td>${cost.agencyName}</td>
                <td>实缴金额（元）</td>
                <td>${cost.paymentAmount}</td>
            </tr>
            <tr>
                <td>实缴日期</td>
                <td><fmt:formatDate value="${cost.paymentDate}" pattern="yyyy-MM-dd"/></td>
                <td>缴费人</td>
                <td>${cost.payor}</td>
            </tr>
            <tr>
                <td>官方回执编号</td>
                <td>${cost.receiptNumber}</td>
                <td>官方回执日期</td>
                <td><fmt:formatDate value="${cost.receiptDate}" pattern="yyyy-MM-dd"/></td>
            </tr>
        </table>
    </c:if>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $.fn.jPicker.defaults.images.clientPath='${ctx}/js/jquery/plugins/jpicker/images/';
    debugger
    })
</script>