<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>

<c:set value="${data.results}" var="list" />

<form id="listOauthPatentForm" action="${ctx}/oauth/patent/list.do?tabId=${param.tabId}" method="post">
<%--    <div>哈哈哈：${list}</div>--%>
    <div class="easyui-panel" title="" data-options="" style="width: 100%">
        <div class="datagrid-toolbar" float:right>
            <table cellspacin="0" cellpadding="3px" style="padding-top: 5px; padding-bottom: 5px;">
                <tr>
                    <td style="width: auto;">专利名称：</td>
                    <td><input type='text' name="paramMap[patentName]" class="easyui-validatebox"  value="${searchParam.paramMap.patentName}"></td>
                    <td style="width: auto;">专利号/申请号：</td>
                    <td><input type='text' name="paramMap[appNumber]" class="easyui-validatebox"  value="${searchParam.paramMap.appNumber}"></td>
                    <td style="width: auto;">专利权人：</td>
                    <td><input type='text' name="paramMap[applyer]" class="easyui-validatebox"  value="${searchParam.paramMap.applyer}"></td>
                    <%--<td><input type='text' style="display: initial;" id="pagination.isChange" name="pagination.isChange" value="false"></td>--%>
                    <td>
                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="refreshOauthPatent()" iconCls="fa fa-search">查询</a>
                    </td>
                </tr>
            </table>
            <div id="bestSearch"  >
                <div class="easyui-panel" id="bestSearchParam1" onclick="closePanel(this)" title="高级检索条件1" collapsible="true" collapsed="true">
                    <table  cellpadding="4" cellspacing="4" border="0" class="alter-table-h" style="font-size: 12px;width: 90%;">
                        <tr>
                            <td class="td_short">专利类型</td>
                            <td>
                                <input id="patentType" name="paramMap[patentType]" class="easyui-textbox" type="text" value="${searchParam.paramMap.patentType}" title="专利类型" data-options=""/>
                            </td>
                            <td>摘要</td>
                            <td class="td_long">
                                <input id="digest" name="paramMap[digest]" class="easyui-textbox" value="${searchParam.paramMap.digest}" type="text" title="摘要" data-options=""/>
                            </td>
                        </tr>
                        <tr>
                            <td class="td_short">申请日开始</td>
                            <td class="td_long">
                                <input id='appDateStart' name='paramMap[appDateStart]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                                       class="easyui-validatebox Wdate" data-options="" title="申请日开始"  value='${searchParam.paramMap.appDateStart}' autocomplete="off"/>
                            </td>
                            <td>申请日结束</td>
                            <td>
                                <input id='appDateEnd' name='paramMap[appDateEnd]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                                       class="easyui-validatebox Wdate" data-options="" title="申请日结束"  value='${searchParam.paramMap.appDateEnd}' autocomplete="off"/>
                            </td>
                        </tr>
                        <tr>
                            <td>公开（公告）号</td>
                            <td><input id="publicationNumber" name="paramMap[publicationNumber]" class="easyui-textbox" type="text"  value="${searchParam.paramMap.publicationNumber}" title="申请人/专利权人" data-options=""/></td>
                            <td>申请（专利权）人</td>
                            <td><input id="patentee" name="patentee" class="easyui-textbox" type="text" value="${searchParam.paramMap.patentee}" title="申请（专利权）人" data-options=""/></td>
                        </tr>
                        <tr>
                            <td>公开（公告）日开始</td>
                            <td>
                                <input id='publicationDateStart' name='paramMap[publicationDateStart]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                                       class="easyui-validatebox Wdate" data-options="" title="公开（公告）日开始"  value='${searchParam.paramMap.publicationDateStart}' autocomplete="off"/>
                            </td>
                            <td>公开（公告）日结束</td>
                            <td>
                                <input id='publicationDateEnd' name='paramMap[publicationDateEnd]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                                       class="easyui-validatebox Wdate" data-options="" title="公开（公告）日结束"  value='${searchParam.paramMap.publicationDateEnd}' autocomplete="off"/>
                            </td>
                        </tr>
                        <tr>
                            <td>发明（设计）人</td>
                            <td><input id="inventor" name="paramMap[inventor]" class="easyui-textbox" type="text" value="${searchParam.paramMap.inventor}" title="发明（设计）人" data-options="required:false"/></td>
                            <td>主分类号</td>
                            <td><input id="mainCategory" name="paramMap[mainCategory]" class="easyui-textbox" type="text" value="${searchParam.paramMap.mainCategory}" title="主分类号" data-options="required:false"/></td>
                        </tr>
                        <tr>
                            <td>分类号</td>
                            <td><input id="category" name="paramMap[category]" class="easyui-textbox" type="text" value="${searchParam.paramMap.category}" title="分类号" data-options="required:false"/></td>
                            <td>专利代理机构</td>
                            <td><input id="agency" name="paramMap[agency]" class="easyui-textbox" type="text" value="${searchParam.paramMap.agency}" title="专利代理机构" data-options="required:false"/></td>
                        </tr>
                        <tr>
                            <td>代理人</td>
                            <td><input id="agents" name="paramMap[agents]" class="easyui-textbox" type="text" title="代理人" value="${searchParam.paramMap.agents}" data-options="required:false"/></td>
                            <td>优先权</td>
                            <td>
                                <input id="priority" name="paramMap[priority]" class="easyui-textbox" type="text" value="${searchParam.paramMap.priority}" title="优先权" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>权利要求书</td>
                            <td><input id="rightClaimingDoc" name="paramMap[rightClaimingDoc]" class="easyui-textbox" value="${searchParam.paramMap.rightClaimingDoc}" type="text" title="权利要求书" data-options="required:false"/></td>
                            <td>主权项</td>
                            <td>
                                <input id="principal" name="paramMap[principal]" class="easyui-textbox" type="text" value="${searchParam.paramMap.principal}" title="主权项" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>独权限</td>
                            <td>
                                <input id="soleAuthority" name="paramMap[soleAuthority]" class="easyui-textbox" type="text" value="${searchParam.paramMap.soleAuthority}" title="独权限" data-options="required:false"/>
                            </td>
                            <td>说明书</td>
                            <td>
                                <input id="instructions" name="paramMap[instructions]" class="easyui-textbox" type="text" value="${searchParam.paramMap.instructions}" title="说明书" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>地址</td>
                            <td><input id="address" name="paramMap[address]" class="easyui-textbox" type="text" value="${searchParam.paramMap.address}" title="地址" data-options="required:false"/></td>
                            <td>省</td>
                            <td>
                                <input id="province" name="paramMap[province]" class="easyui-textbox" type="text" value="${searchParam.paramMap.province}" title="省" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>市</td>
                            <td>
                                <input id="city" name="paramMap[city]" class="easyui-textbox" type="text" value="${searchParam.paramMap.city}" title="市" data-options="required:false"/>
                            </td>
                            <td>区</td>
                            <td>
                                <input id="area" name="paramMap[area]" class="easyui-textbox" type="text" value="${searchParam.paramMap.area}" title="区" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>申请国代码</td>
                            <td><input id="applyCountryCode" name="paramMap[applyCountryCode]" class="easyui-textbox" type="text" value="${searchParam.paramMap.applyCountryCode}" title="申请国代码" data-options="required:false"/></td>
                            <td>公开国代码</td>
                            <td>
                                <input id="publicCountryCode" name="paramMap[publicCountryCode]" class="easyui-textbox" type="text" value="${searchParam.paramMap.publicCountryCode}" title="公开国代码" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>国省代码</td>
                            <td><input id="countryProvinceCode" name="paramMap[countryProvinceCode]" class="easyui-textbox" type="text" value="${searchParam.paramMap.countryProvinceCode}" title="国省代码" data-options="required:false"/></td>
                            <td>关键词</td>
                            <td><input id="keyword" name="paramMap[keyword]" class="easyui-textbox" type="text" value="${searchParam.paramMap.keyword}" title="关键词" data-options="required:false"/></td>
                        </tr>
                        <tr>
                            <td>名称关键词</td>
                            <td>
                                <input id="nameKeyword" name="paramMap[nameKeyword]" class="easyui-textbox" type="text" value="${searchParam.paramMap.nameKeyword}" title="关键词" data-options="required:false"/>
                            </td>
                            <td>独权关键词</td>
                            <td>
                                <input id="soleAuthorityKeyword" name="paramMap[soleAuthorityKeyword]" class="easyui-textbox" type="text" value="${searchParam.paramMap.soleAuthorityKeyword}" title="独权关键词" data-options="required:false"/>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="easyui-panel" id="bestSearchParam2" title="高级检索条件2" collapsible="true" collapsed="true">
                    <table cellpadding="4" cellspacing="4" border="0" class="alter-table-h" style="font-size: 12px;width: 90%;">
                        <tr>
                            <td class="td_short">背景关键词</td>
                            <td class="td_long">
                                <input id='backgroundKeyword' name='paramMap[backgroundKeyword]' title="背景关键词"   value='${searchParam.paramMap.backgroundKeyword}' />
                            </td>
                            <td class="td_short">同日申请</td>
                            <td class="td_long">
                                <input id='applicationOnTheSameDay' name='paramMap[applicationOnTheSameDay]' title="同日申请"   value='${searchParam.paramMap.applicationOnTheSameDay}' />
                            </td>
                        </tr>
                        <tr>
                            <td>审查员</td>
                            <td>
                                <input id="examiner" name="paramMap[examiner]" class="easyui-textbox" type="text" value="${searchParam.paramMap.examiner}" title="审查员" data-options="required:false"/>
                            </td>
                            <td>申请来源</td>
                            <td>
                                <input id="sourceOfApplication" name="paramMap[sourceOfApplication]" class="easyui-textbox" type="text" value="${searchParam.paramMap.sourceOfApplication}" title="申请来源" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>国际申请</td>
                            <td>
                                <input id="internationalApplication" name="paramMap[internationalApplication]" class="easyui-textbox" type="text" value="${searchParam.paramMap.internationalApplication}" title="国际申请" data-options="required:false"/>
                            </td>
                            <td>国际公布</td>
                            <td>
                                <input id="internationalPublication" name="paramMap[internationalPublication]" class="easyui-textbox" type="text" value="${searchParam.paramMap.internationalPublication}" title="国际公布" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>进入国家日开始</td>
                            <td>
                                <input id='enterCountryDateStart' name='paramMap[enterCountryDateStart]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                                       class="easyui-validatebox Wdate" data-options="" title="进入国家日开始"  value='${searchParam.paramMap.enterCountryDateStart}' autocomplete="off"/>
                            </td>
                            <td>进入国家日结束</td>
                            <td>
                                <input id='enterCountryDateEnd' name='paramMap[enterCountryDateEnd]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                                       class="easyui-validatebox Wdate" data-options="" title="进入国家日结束"  value='${searchParam.paramMap.enterCountryDateEnd}' autocomplete="off"/>
                            </td>
                        </tr>
                        <tr>
                            <td>法律状态</td>
                            <td>
                                <input id="legalStatus" name="paramMap[legalStatus]" class="easyui-textbox" type="text" value="${searchParam.paramMap.legalStatus}" title="法律状态" data-options="required:false"/>
                            </td>
                            <td>专利权状态</td>
                            <td>
                                <input id="patentRightStatus" name="paramMap[patentRightStatus]" class="easyui-textbox" type="text" value="${searchParam.paramMap.patentRightStatus}" title="专利权状态" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>专利权状态待代码</td>
                            <td>
                                <input id="legalStatusCode" name="paramMap[legalStatusCode]" class="easyui-textbox" type="text" value="${searchParam.paramMap.legalStatusCode}" title="专利权状态待代码" data-options="required:false"/>
                            </td>
                            <td>PCT指定国家</td>
                            <td>
                                <input id="pctAppointCountry" name="paramMap[pctAppointCountry]" class="easyui-textbox" type="text" value="${searchParam.paramMap.pctAppointCountry}" title="PCT指定国家" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>专利数据库</td>
                            <td>
                                <input id="dbName" name="paramMap[dbName]" class="easyui-textbox" type="text" value="${searchParam.paramMap.dbName}" title="专利数据库" data-options="required:false"/>
                            </td>
                            <td>国名经济分类</td>
                            <td>
                                <input id="classificationOfNationalEconomy" name="paramMap[classificationOfNationalEconomy]" class="easyui-textbox" type="text" value="${searchParam.paramMap.classificationOfNationalEconomy}" title="国名经济分类" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <%--<td>专利权人</td>
                            <td>
                                <input id="applyer" name="paramMap[applyer]" class="easyui-textbox" type="text" title="专利权人" data-options="required:false"/>
                            </td>--%>
                            <td>历史专利权人</td>
                            <td>
                                <input id="historyApplyer" name="paramMap[historyApplyer]" class="easyui-textbox" type="text" value="${searchParam.paramMap.historyApplyer}" title="历史专利权人" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>授权日开始</td>
                            <td>
                                <input id='authorizationDateStart' name='paramMap[authorizationDateStart]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                                       class="easyui-validatebox Wdate" data-options="" title="进入国家日开始"  value='${searchParam.paramMap.authorizationDateStart}' autocomplete="off"/>
                            </td>
                            <td>授权日结束</td>
                            <td>
                                <input id='authorizationDateEnd' name='paramMap[authorizationDateEnd]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                                       class="easyui-validatebox Wdate" data-options="" title="进入国家日结束"  value='${searchParam.paramMap.authorizationDateEnd}' autocomplete="off"/>
                            </td>
                        </tr>
                        <tr>
                            <td>失效日开始</td>
                            <td>
                                <input id='expirationDateStart' name='paramMap[expirationDateStart]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                                       class="easyui-validatebox Wdate" data-options="" title="失效日开始"  value='${searchParam.paramMap.expirationDateStart}' autocomplete="off"/>
                            </td>
                            <td>失效日结束</td>
                            <td>
                                <input id='expirationDateEnd' name='paramMap[expirationDateEnd]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                                       class="easyui-validatebox Wdate" data-options="" title="失效日结束"  value='${searchParam.paramMap.expirationDateEnd}' autocomplete="off"/>
                            </td>
                        </tr>
                        <tr>
                            <td>申请人类型</td>
                            <td>
                                <input id="applyerType" name="paramMap[applyerType]" class="easyui-textbox" type="text" value="${searchParam.paramMap.applyerType}" title="申请人类型" data-options="required:false"/>
                            </td>
                            <td>权利要求书类型</td>
                            <td>
                                <input id="rightClaimingDocType" name="paramMap[rightClaimingDocType]" class="easyui-textbox" type="text" value="${searchParam.paramMap.rightClaimingDocType}" title="权利要求书类型" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>优先权号</td>
                            <td>
                                <input id="priorityNumber" name="paramMap[priorityNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.priorityNumber}" title="优先权号" data-options="required:false"/>
                            </td>
                            <td>权利要求书类型</td>
                            <td>
                                <input id="firstPriority" name="paramMap[firstPriority]" class="easyui-textbox" type="text" value="${searchParam.paramMap.firstPriority}" title="权利要求书类型" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>优先权国家</td>
                            <td>
                                <input id="priorityCountry" name="paramMap[priorityCountry]" class="easyui-textbox" type="text" value="${searchParam.paramMap.priorityCountry}" title="优先权国家" data-options="required:false"/>
                            </td>
                            <td>说明书页数</td>
                            <td>
                                <input id="instructionsPages" name="paramMap[instructionsPages]" class="easyui-textbox" type="text" value="${searchParam.paramMap.instructionsPages}" title="说明书页数" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>授权时长</td>
                            <td>
                                <input id="authorizationDuration" name="paramMap[authorizationDuration]" class="easyui-textbox" type="text" value="${searchParam.paramMap.authorizationDuration}" title="授权时长" data-options="required:false"/>
                            </td>
                            <td>是否提前公开</td>
                            <td>
                                <input id="isAdvanceAuthorization" name="paramMap[isAdvanceAuthorization]" class="easyui-textbox" type="text" value="${searchParam.paramMap.isAdvanceAuthorization}" title="是否提前公开" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>解密公告日开始</td>
                            <td>
                                <input id='declassificationAnnouncementDateStart' name='paramMap[declassificationAnnouncementDateStart]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                                       class="easyui-validatebox Wdate" data-options="" title="失效日开始"  value='${searchParam.paramMap.declassificationAnnouncementDateStart}' autocomplete="off"/>
                            </td>
                            <td>解密公告日结束</td>
                            <td>
                                <input id='declassificationAnnouncementDateEnd' name='paramMap[declassificationAnnouncementDateEnd]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                                       class="easyui-validatebox Wdate" data-options="" title="失效日开始"  value='${searchParam.paramMap.declassificationAnnouncementDateEnd}' autocomplete="off"/>
                            </td>
                        </tr>
                        <tr>
                            <td>分类号小类数</td>
                            <td>
                                <input id="categoryNumber" name="paramMap[categoryNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.categoryNumber}" title="分类号小类数" data-options="required:false"/>
                            </td>
                            <td>发明人数</td>
                            <td>
                                <input id="inventorNumber" name="paramMap[inventorNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.inventorNumber}" title="发明人数" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>权利要求数</td>
                            <td>
                                <input id="rightNumber" name="paramMap[rightNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.rightNumber}" title="权利要求数" data-options="required:false"/>
                            </td>
                            <td>独权数</td>
                            <td>
                                <input id="soleAuthorizationNumber" name="paramMap[soleAuthorizationNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.soleAuthorizationNumber}" title="独权数" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>从权数</td>
                            <td>
                                <input id="expedientNumber" name="paramMap[expedientNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.expedientNumber}" title="从权数" data-options="required:false"/>
                            </td>
                            <td>专利权转移次数</td>
                            <td>
                                <input id="transferNumber" name="paramMap[transferNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.transferNumber}" title="专利权转移次数" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>申请权转移次数</td>
                            <td>
                                <input id="applicationTransferNumber" name="paramMap[applicationTransferNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.applicationTransferNumber}" title="申请权转移次数" data-options="required:false"/>
                            </td>
                            <td>许可次数</td>
                            <td>
                                <input id="permitNumber" name="paramMap[permitNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.permitNumber}" title="许可次数" data-options="required:false"/>
                            </td>
                        </tr>

                    </table>
                </div>
                <div class="easyui-panel" id="bestSearchParam3" title="高级检索条件3" collapsible="true" collapsed="true">
                    <table cellpadding="4" cellspacing="4" border="0" class="alter-table-h" style="font-size: 12px;width: 90%;">
                        <tr>
                            <td>质押次数</td>
                            <td>
                                <input id="pledgeNumber" name="paramMap[pledgeNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.pledgeNumber}" title="质押次数" data-options="required:false"/>
                            </td>
                            <td>保全次数</td>
                            <td>
                                <input id="preservationNumber" name="paramMap[preservationNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.preservationNumber}" title="保全次数" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>引证总次数</td>
                            <td>
                                <input id="citationsNumber" name="paramMap[citationsNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.citationsNumber}" title="引证总次数" data-options="required:false"/>
                            </td>
                            <td>专利引证次数</td>
                            <td>
                                <input id="patentCitationsNumber" name="paramMap[patentCitationsNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.patentCitationsNumber}" title="专利引证次数" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>非专利引证次数</td>
                            <td>
                                <input id="excludePatentCitationNumber" name="paramMap[excludePatentCitationNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.excludePatentCitationNumber}" title="非专利引证次数" data-options="required:false"/>
                            </td>
                            <td>被引证次数</td>
                            <td>
                                <input id="areQuoteNumber" name="paramMap[areQuoteNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.areQuoteNumber}" title="被引证次数" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>复审次数</td>
                            <td>
                                <input id="reviewNumber" name="paramMap[reviewNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.reviewNumber}" title="复审次数" data-options="required:false"/>
                            </td>
                            <td>无效次数</td>
                            <td>
                                <input id="invalidNumber" name="paramMap[invalidNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.invaildNumber}" title="无效次数" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>判决次数</td>
                            <td>
                                <input id="sentenceNumber" name="paramMap[sentenceNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.sentenceNumber}" title="判决次数" data-options="required:false"/>
                            </td>
                            <td>国家代码</td>
                            <td>
                                <input id="countryCode" name="paramMap[countryCode]" class="easyui-textbox" type="text" value="${searchParam.paramMap.countryCode}" title="国家代码" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>国家名</td>
                            <td>
                                <input id="countryName" name="paramMap[countryName]" class="easyui-textbox" type="text" value="${searchParam.paramMap.countryName}" title="国家名" data-options="required:false"/>
                            </td>
                            <td>省名</td>
                            <td>
                                <input id="provinceName" name="paramMap[provinceName]" class="easyui-textbox" type="text" value="${searchParam.paramMap.privinceName}" title="省名" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>引证号</td>
                            <td>
                                <input id="citationsCode" name="paramMap[citationsCode]" class="easyui-textbox" type="text" value="${searchParam.paramMap.citationsCode}" title="引证号" data-options="required:false"/>
                            </td>
                            <td>引证分类号</td>
                            <td>
                                <input id="citationsCategoryCode" name="paramMap[citationsCategoryCode]" class="easyui-textbox" type="text" value="${searchParam.paramMap.citationsCategoryCode}" title="引证分类号" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>引证申请人</td>
                            <td>
                                <input id="citationsApplyer" name="paramMap[citationsApplyer]" class="easyui-textbox" type="text" value="${searchParam.paramMap.citationsApplyer}" title="引证申请人" data-options="required:false"/>
                            </td>
                            <td>引证国家</td>
                            <td>
                                <input id="citationsCountry" name="paramMap[citationsCountry]" class="easyui-textbox" type="text" value="${searchParam.paramMap.citationsCountry}" title="引证国家" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>非专利引证</td>
                            <td>
                                <input id="nonPatentCitation" name="paramMap[nonPatentCitation]" class="easyui-textbox" type="text" value="${searchParam.paramMap.nonPatentCitation}" title="非专利引证" data-options="required:false"/>
                            </td>
                            <td>被引证号</td>
                            <td>
                                <input id="quitedNumber" name="paramMap[quitedNumber]" class="easyui-textbox" type="text" value="${searchParam.paramMap.quitedNumber}" title="被引证号" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>被引证国家</td>
                            <td>
                                <input id="citedCountry" name="paramMap[citedCountry]" class="easyui-textbox" type="text" value="${searchParam.paramMap.citedCountry}" title="被引证国家" data-options="required:false"/>
                            </td>
                            <td>分类号部</td>
                            <td>
                                <input id="classificationDepartment" name="paramMap[classificationDepartment]" class="easyui-textbox" type="text" value="${searchParam.paramMap.classificationDepartment}" title="分类号部" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>分类号大部</td>
                            <td>
                                <input id="classificationBigDepartment" name="paramMap[classificationBigDepartment]" class="easyui-textbox" type="text" value="${searchParam.paramMap.classificationBigDepartment}" title="分类号大部" data-options="required:false"/>
                            </td>
                            <td>分类号小部</td>
                            <td>
                                <input id="classificationSmallDepartment" name="paramMap[classificationSmallDepartment]" class="easyui-textbox" type="text" value="${searchParam.paramMap.classificationSmallDepartment}" title="分类号小部" data-options="required:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td>分类号大组</td>
                            <td>
                                <input id="classificationBigGroup" name="paramMap[classificationBigGroup]" class="easyui-textbox" type="text" value="${searchParam.paramMap.classificationBigGroup}" title="分类号大组" data-options="required:false"/>
                            </td>
                            <td>分类号小组</td>
                            <td>
                                <input id="classificationSmallGroup" name="paramMap[classificationSmallGroup]" class="easyui-textbox" type="text" value="${searchParam.paramMap.classificationSmallGroup}" title="分类号小组" data-options="required:false"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <table cellspacing="0" cellpadding="0" class="">
                <tbody>
                    <tr>
                        <td><a onclick="showOauthPatentDetail()" href="javascript:void(0)" class="easyui-linkbutton">查看</a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                        <%--<td><a onclick="batchInsert()" href="javascript:void(0)" class="easyui-linkbutton">批量导入</a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                        <td><a onclick="refreshRole()" href="javascript: void(0);" class="easyui-linkbutton">更新权限</a></td>
                        <td><div class="datagrid-btn-separator"></div></td>--%>
                    </tr>
                </tbody>
            </table>
        </div>

        <div>
            <table id="listOauthPatentTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="false" fitColumns="false" >
                <thead>
                <tr>
                    <th width="30" data-options="field:'id',checkbox:true"></th>
                    <th width="300" data-options="field:'patentName', align:'center'">专利名称</th>
                    <th width="150" data-options="field:'appNumber', align:'center'">专利号/申请号</th>
                    <th width="100" data-options="field:'appDate', align:'center'">申请日期</th>
                    <th width="200" data-options="field:'applyer', align:'center'">申请人/专利权人</th>
                    <th width="200" data-options="field:'inventor', align:'center'">发明人</th>
                    <th width="100" data-options="field:'patentType', align:'center'">专利类型</th>
                    <th width="100" data-options="field:'authorCountry', align:'center'">申请国家</th>
                    <th width="100" data-options="field:'legalStatus', align:'center'">法律状态</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="patents">
                    <tr>
                        <td>${patents.pid }</td>
                        <td >${patents.title}</td>
                        <td>${patents.appNumber[0]}</td>
                        <td>${patents.appDate}</td>
                        <td>${patents.applicantName[0] }</td>
                        <td>${patents.inventorName[0] }</td>
                        <td>${dict:getEntryName('IPBOX_IPR_PATENT_TYPE',patents.patType)}</td>
                        <td>中国</td>
                        <td>${patents.lprs}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <jsp:include page="/jsp/common/include/manage_page_table.jsp">
                <jsp:param value="listOauthPatentForm" name="pageForm" />
                <jsp:param value="listOauthPatentTable" name="tableId" />
                <jsp:param value="easyui" name="type" />
                <jsp:param value='${param.tabId}' name="divId" />
            </jsp:include>
        </div>

    </div>
</form>


<style type="text/css">
    .model_interval{
        padding-top: 5px;
        padding-bottom: 5px;
    }
    .xu_interval{
        padding-right: 5px;
    }
    .textCenter{
        text-align: center;
    }
</style>

<script>

    $(document).ready(function () {
        /*加载专利类型下拉数据*/
        $("#patentType").combobox({
            url: '${ctx}/common/datadict/getByTypeCode.do?typeCode=IPBOX_IPR_PATENT_TYPE',
            emptyText: '',
            valueField: 'dictValue',
            textField: 'dictName',
            slide: true,
            valueFieldID: 'patentType',
            limitToList: true,
            panelHeight: 'auto'
        });
    });

    $('input').change(function () {
        var currentTab = getCurrentTab();
        var isChange = currentTab.find('#pagination.isChange');
        isChange.val("true");
    });
    
    function refreshOauthPatent() {
        console.log(">>>>专利列表刷新V1！");
        var tabId = $("#layout_center_tabs").tabs('getSelected').attr('id');
        debugger
        easyuiUtils.ajaxPostFormForPanel('listOauthPatentForm', tabId);
    }
    
    function bestSearch2() {
        //openWindow("bestSearchWin", '高级检索', '100%', '100%', '${ctx}/oauth/patent/goBestSearch.do', true);
        debugger
        openWindow("bestSearchWin", '高级检索', 1000, 800, '${ctx}/oauth/patent/goBestSearch.do', true);

    }
    
    function viewOauthPatent() {
        easyuiUtils.openWindow("")
    }

    /*提交高级检索表单*/
    function submitBestSearchForm() {

        var arrParam = $("#bestSearchWin").find('#oauthPatentForm').serializeArray();
        var arrParamStr = "";
        $.each(arrParam, function (idx, obj) {
            if(arrParamStr == ""){
                arrParamStr += obj.name + "=" + obj.value;
            }else {
                arrParamStr += "&" + obj.name + "=" + obj.value;
            }
        });

        $.ajax({
            url: '${ctx}/oauth/patent/list4Json.do?tabId=${param.tabId}',
            method: 'post',
            dataType: 'json',
            data: arrParamStr,
            success: function (data) {
                var list = data.data.results;
                var listData = [];
                $.each(list, function (idx, obj) {
                    var type = obj.patType;
                    if(type == 1){
                        type = "发明专利";
                    }else if(type == 2){
                        type = "实用新型";
                    }else if(type == 3){
                        type = "外观设计";
                    }
                    var a = {
                        'id': obj.pid,
                        'patentName': obj.title,
                        'appNumber': obj.appNumber[0],
                        'appDate': obj.appDate,
                        'applyer': obj.applicantName[0],
                        'inventor': obj.inventorName[0],
                        'patentType': type,
                        'authorCountry': '中国',
                        'legalStatus': obj.lprs
                    };

                    listData.push(a);
                });


                var tab = $('#${param.tabId} #listOauthPatentTable');
                tab.datagrid('loadData', listData);
                debugger

                tab.datagrid('getPager').pagination({
                    total : parseInt(data.data.total),
                    pageSize : parseInt('${searchParam.pagination.pageSize}'),
                    pageNumber : parseInt('${searchParam.pagination.currentPage}')
                });
                debugger
                closeWindow('bestSearchWin');

            },
            error: function (data) {
                debugger
            }
        })
    }

    function closePanel(obj) {

        /*var id = obj.getAttribute('id');
        var closeable = obj.getAttribute('collapsed');
    debugger
        var panel = $('#' + id);
        if (closeable) {
        debugger
            panel.panel({collapsed: false});
            panel.val('collapsed', 'false');
        } else {
        debugger
            panel.panel({collapsed: true});
            panel.val('collapsed', 'true');
        }
    debugger*/
    }

    function showOauthPatentDetail() {
        var objects = $('#listOauthPatentTable').datagrid('getSelections');

        if(objects.length == 0){
            $.messager.alert('消息','请选择一条数据');
            return false;
        }
        if(objects.length > 1){
            $.messager.alert('消息','不能同时编辑多条数据');
            return false;
        }

        var appNumber = objects[0].appNumber;
        var pid = objects[0].id;
        debugger
        openWindow('oauthPatentDetailWin', '专利信息', '100%', '100%', '${ctx}/oauth/patent/goDetail.do?appNumber=' + appNumber + '&pid=' + pid, true);
    }

    function refreshRole() {
        $.ajax({
            url: '${ctx}/oauth/refreshToken.do',
            method: 'get',
            success: function(data){
                alert("权限已更新");
            },
            error: function () {
                alert("更新失败");
            }
        });
    }

    function batchInsert() {
        var param = $("#listOauthPatentForm").serialize();
        debugger
        $.ajax({
            url: '${ctx}/oauth/patent/batchInsert.do',
            type: 'post',
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            data: param,
            dataType: "json",
            success: function (data) {

            },
            error: function (data) {

            }
        });
    }
</script>