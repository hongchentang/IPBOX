<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes')}" var="fileTypes2"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize2"/>

<div id="layout1" style="padding-top: 10px;padding-left:0px;" class="easyui-panle" data-options="region:'center',title:''">
    <form name="oauthPatentForm" method="post" id="oauthPatentForm" action="${ctx}/oauth/patent/list.do?tabId=21313" >
        <%--放修改时候的ID--%>
        <div class="easyui-panel" title="检索条件1" collapsible="true">
            <table  cellpadding="4" cellspacing="4" border="0" class="alter-table-h" style="font-size: 12px;width: 90%;">
                <tr>
                    <td>专利名称</td>
                    <td>
                        <input id="patentName" name="paramMap[patentName]" class="easyui-textbox" type="text" title="专利名称" data-options=""/>
                    </td>
                    <td>摘要</td>
                    <td class="td_long">
                        <input id="digest" name="paramMap[digest]" class="easyui-textbox" type="text" title="摘要" data-options=""/>
                    </td>
                </tr>
                <tr>
                    <td class="td_short">专利类型</td>
                    <td>
                        <input id="patentType" name="paramMap[patentType]" class="easyui-textbox" type="text" title="专利类型" data-options=""/>
                    </td>
                    <td class="td_short">申请号</td>
                    <td>
                        <input id="appNumber" name="paramMap[appNumber]" class="easyui-textbox" type="text" title="申请号" data-options=""/>
                    </td>
                </tr>
                <tr>
                    <td class="td_short">申请日开始</td>
                    <td class="td_long">
                        <input id='appDateStart' name='paramMap[appDateStart]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="申请日开始"  value='' autocomplete="off"/>
                    </td>
                    <td>申请日结束</td>
                    <td>
                        <input id='appDateEnd' name='paramMap[appDateEnd]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="申请日结束"  value='' autocomplete="off"/>
                    </td>
                </tr>
                <tr>
                    <td>公开（公告）号</td>
                    <td><input id="publicationNumber" name="paramMap[publicationNumber]" class="easyui-textbox" type="text" title="申请人/专利权人" data-options=""/></td>
                    <td>申请（专利权）人</td>
                    <td><input id="patentee" name="patentee" class="easyui-textbox" type="text" title="申请（专利权）人" data-options=""/></td>
                </tr>
                <tr>
                    <td>公开（公告）日开始</td>
                    <td>
                        <input id='publicationDateStart' name='paramMap[publicationDateStart]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="公开（公告）日开始"  value='' autocomplete="off"/>
                    </td>
                    <td>公开（公告）日结束</td>
                    <td>
                        <input id='publicationDateEnd' name='paramMap[publicationDateEnd]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="公开（公告）日结束"  value='' autocomplete="off"/>
                    </td>
                </tr>
                <tr>
                    <td>发明（设计）人</td>
                    <td><input id="inventor" name="paramMap[inventor]" class="easyui-textbox" type="text" title="发明（设计）人" data-options="required:false"/></td>
                    <td>主分类号</td>
                    <td><input id="mainCategory" name="paramMap[mainCategory]" class="easyui-textbox" type="text" title="主分类号" data-options="required:false"/></td>
                </tr>
                <tr>
                    <td>分类号</td>
                    <td><input id="category" name="paramMap[category]" class="easyui-textbox" type="text" title="分类号" data-options="required:false"/></td>
                    <td>专利代理机构</td>
                    <td><input id="agency" name="paramMap[agency]" class="easyui-textbox" type="text" title="专利代理机构" data-options="required:false"/></td>
                </tr>
                <tr>
                    <td>代理人</td>
                    <td><input id="agents" name="paramMap[agents]" class="easyui-textbox" type="text" title="代理人" data-options="required:false"/></td>
                    <td>优先权</td>
                    <td>
                        <input id="priority" name="paramMap[priority]" class="easyui-textbox" type="text" title="优先权" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>权利要求书</td>
                    <td><input id="rightClaimingDoc" name="paramMap[rightClaimingDoc]" class="easyui-textbox" type="text" title="权利要求书" data-options="required:false"/></td>
                    <td>主权项</td>
                    <td>
                        <input id="principal" name="paramMap[principal]" class="easyui-textbox" type="text" title="主权项" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>独权限</td>
                    <td>
                        <input id="soleAuthority" name="paramMap[soleAuthority]" class="easyui-textbox" type="text" title="独权限" data-options="required:false"/>
                    </td>
                    <td>说明书</td>
                    <td>
                        <input id="instructions" name="paramMap[instructions]" class="easyui-textbox" type="text" title="说明书" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>地址</td>
                    <td><input id="address" name="paramMap[address]" class="easyui-textbox" type="text" title="地址" data-options="required:false"/></td>
                    <td>省</td>
                    <td>
                        <input id="province" name="paramMap[province]" class="easyui-textbox" type="text" title="省" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>市</td>
                    <td>
                        <input id="city" name="paramMap[city]" class="easyui-textbox" type="text" title="市" data-options="required:false"/>
                    </td>
                    <td>区</td>
                    <td>
                        <input id="area" name="paramMap[area]" class="easyui-textbox" type="text" title="区" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>申请国代码</td>
                    <td><input id="applyCountryCode" name="paramMap[applyCountryCode]" class="easyui-textbox" type="text" title="申请国代码" data-options="required:false"/></td>
                    <td>公开国代码</td>
                    <td>
                        <input id="publicCountryCode" name="paramMap[publicCountryCode]" class="easyui-textbox" type="text" title="公开国代码" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>国省代码</td>
                    <td><input id="countryProvinceCode" name="paramMap[countryProvinceCode]" class="easyui-textbox" type="text" title="国省代码" data-options="required:false"/></td>
                    <td>关键词</td>
                    <td><input id="keyword" name="paramMap[keyword]" class="easyui-textbox" type="text" title="关键词" data-options="required:false"/></td>
                </tr>
                <tr>
                    <td>名称关键词</td>
                    <td>
                        <input id="nameKeyword" name="paramMap[nameKeyword]" class="easyui-textbox" type="text" title="关键词" data-options="required:false"/>
                    </td>
                    <td>独权关键词</td>
                    <td>
                        <input id="soleAuthorityKeyword" name="paramMap[soleAuthorityKeyword]" class="easyui-textbox" type="text" title="独权关键词" data-options="required:false"/>
                    </td>
                </tr>
                <tr style="display: none;">
                    <td><input type="text" id="pagination.currentPage" name="pagination.currentPage" value="1"/>/td>
                    <td><input type="text" id="pagination.pageSize" name="pagination.pageSize" value="10" />/td>
                </tr>
            </table>
        </div>
        <div class="easyui-panel" title="检索条件2" collapsible="true">
            <table cellpadding="4" cellspacing="4" border="0" class="alter-table-h" style="font-size: 12px;width: 90%;">
                <tr>
                    <td class="td_short">背景关键词</td>
                    <td class="td_long">
                        <input id='backgroundKeyword' name='paramMap[backgroundKeyword]' title="背景关键词"   value='' />
                    </td>
                    <td class="td_short">同日申请</td>
                    <td class="td_long">
                        <input id='applicationOnTheSameDay' name='paramMap[applicationOnTheSameDay]' title="同日申请"   value='' />
                    </td>
                </tr>
                <tr>
                    <td>审查员</td>
                    <td>
                        <input id="examiner" name="paramMap[examiner]" class="easyui-textbox" type="text" title="审查员" data-options="required:false"/>
                    </td>
                    <td>申请来源</td>
                    <td>
                        <input id="sourceOfApplication" name="paramMap[sourceOfApplication]" class="easyui-textbox" type="text" title="申请来源" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>国际申请</td>
                    <td>
                        <input id="internationalApplication" name="paramMap[internationalApplication]" class="easyui-textbox" type="text" title="国际申请" data-options="required:false"/>
                    </td>
                    <td>国际公布</td>
                    <td>
                        <input id="internationalPublication" name="paramMap[internationalPublication]" class="easyui-textbox" type="text" title="国际公布" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>进入国家日开始</td>
                    <td>
                        <input id='enterCountryDateStart' name='paramMap[enterCountryDateStart]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="进入国家日开始"  value='' autocomplete="off"/>
                    </td>
                    <td>进入国家日结束</td>
                    <td>
                        <input id='enterCountryDateEnd' name='paramMap[enterCountryDateEnd]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="进入国家日结束"  value='' autocomplete="off"/>
                    </td>
                </tr>
                <tr>
                    <td>法律状态</td>
                    <td>
                        <input id="legalStatus" name="paramMap[legalStatus]" class="easyui-textbox" type="text" title="法律状态" data-options="required:false"/>
                    </td>
                    <td>专利权状态</td>
                    <td>
                        <input id="patentRightStatus" name="paramMap[patentRightStatus]" class="easyui-textbox" type="text" title="专利权状态" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                <td>专利权状态待代码</td>
                <td>
                    <input id="legalStatusCode" name="paramMap[legalStatusCode]" class="easyui-textbox" type="text" title="专利权状态待代码" data-options="required:false"/>
                </td>
                <td>PCT指定国家</td>
                <td>
                    <input id="pctAppointCountry" name="paramMap[pctAppointCountry]" class="easyui-textbox" type="text" title="PCT指定国家" data-options="required:false"/>
                </td>
            </tr>
                <tr>
                    <td>专利数据库</td>
                    <td>
                        <input id="dbName" name="paramMap[dbName]" class="easyui-textbox" type="text" title="专利数据库" data-options="required:false"/>
                    </td>
                    <td>国名经济分类</td>
                    <td>
                        <input id="classificationOfNationalEconomy" name="paramMap[classificationOfNationalEconomy]" class="easyui-textbox" type="text" title="国名经济分类" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>专利权人</td>
                    <td>
                        <input id="applyer" name="paramMap[applyer]" class="easyui-textbox" type="text" title="专利权人" data-options="required:false"/>
                    </td>
                    <td>历史专利权人</td>
                    <td>
                        <input id="historyApplyer" name="paramMap[historyApplyer]" class="easyui-textbox" type="text" title="历史专利权人" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>授权日开始</td>
                    <td>
                        <input id='authorizationDateStart' name='paramMap[authorizationDateStart]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="进入国家日开始"  value='' autocomplete="off"/>
                    </td>
                    <td>授权日结束</td>
                    <td>
                        <input id='authorizationDateEnd' name='paramMap[authorizationDateEnd]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="进入国家日结束"  value='' autocomplete="off"/>
                    </td>
                </tr>
                <tr>
                    <td>失效日开始</td>
                    <td>
                        <input id='expirationDateStart' name='paramMap[expirationDateStart]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="失效日开始"  value='' autocomplete="off"/>
                    </td>
                    <td>失效日结束</td>
                    <td>
                        <input id='expirationDateEnd' name='paramMap[expirationDateEnd]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="失效日结束"  value='' autocomplete="off"/>
                    </td>
                </tr>
                <tr>
                    <td>申请人类型</td>
                    <td>
                        <input id="applyerType" name="paramMap[applyerType]" class="easyui-textbox" type="text" title="申请人类型" data-options="required:false"/>
                    </td>
                    <td>权利要求书类型</td>
                    <td>
                        <input id="rightClaimingDocType" name="paramMap[rightClaimingDocType]" class="easyui-textbox" type="text" title="权利要求书类型" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>优先权号</td>
                    <td>
                        <input id="priorityNumber" name="paramMap[priorityNumber]" class="easyui-textbox" type="text" title="优先权号" data-options="required:false"/>
                    </td>
                    <td>权利要求书类型</td>
                    <td>
                        <input id="firstPriority" name="paramMap[firstPriority]" class="easyui-textbox" type="text" title="权利要求书类型" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>优先权国家</td>
                    <td>
                        <input id="priorityCountry" name="paramMap[priorityCountry]" class="easyui-textbox" type="text" title="优先权国家" data-options="required:false"/>
                    </td>
                    <td>说明书页数</td>
                    <td>
                        <input id="instructionsPages" name="paramMap[instructionsPages]" class="easyui-textbox" type="text" title="说明书页数" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>授权时长</td>
                    <td>
                        <input id="authorizationDuration" name="paramMap[authorizationDuration]" class="easyui-textbox" type="text" title="授权时长" data-options="required:false"/>
                    </td>
                    <td>是否提前公开</td>
                    <td>
                        <input id="isAdvanceAuthorization" name="paramMap[isAdvanceAuthorization]" class="easyui-textbox" type="text" title="是否提前公开" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>解密公告日开始</td>
                    <td>
                        <input id='declassificationAnnouncementDateStart' name='paramMap[declassificationAnnouncementDateStart]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="失效日开始"  value='' autocomplete="off"/>
                    </td>
                    <td>解密公告日结束</td>
                    <td>
                        <input id='declassificationAnnouncementDateEnd' name='paramMap[declassificationAnnouncementDateEnd]' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="失效日开始"  value='' autocomplete="off"/>
                    </td>
                </tr>
                <tr>
                    <td>分类号小类数</td>
                    <td>
                        <input id="categoryNumber" name="paramMap[categoryNumber]" class="easyui-textbox" type="text" title="分类号小类数" data-options="required:false"/>
                    </td>
                    <td>发明人数</td>
                    <td>
                        <input id="inventorNumber" name="paramMap[inventorNumber]" class="easyui-textbox" type="text" title="发明人数" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>权利要求数</td>
                    <td>
                        <input id="rightNumber" name="paramMap[rightNumber]" class="easyui-textbox" type="text" title="权利要求数" data-options="required:false"/>
                    </td>
                    <td>独权数</td>
                    <td>
                        <input id="soleAuthorizationNumber" name="paramMap[soleAuthorizationNumber]" class="easyui-textbox" type="text" title="独权数" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>从权数</td>
                    <td>
                        <input id="expedientNumber" name="paramMap[expedientNumber]" class="easyui-textbox" type="text" title="从权数" data-options="required:false"/>
                    </td>
                    <td>专利权转移次数</td>
                    <td>
                        <input id="transferNumber" name="paramMap[transferNumber]" class="easyui-textbox" type="text" title="专利权转移次数" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>申请权转移次数</td>
                    <td>
                        <input id="applicationTransferNumber" name="paramMap[applicationTransferNumber]" class="easyui-textbox" type="text" title="申请权转移次数" data-options="required:false"/>
                    </td>
                    <td>许可次数</td>
                    <td>
                        <input id="permitNumber" name="paramMap[permitNumber]" class="easyui-textbox" type="text" title="许可次数" data-options="required:false"/>
                    </td>
                </tr>

            </table>
        </div>
        <div class="easyui-panel" title="检索条件3" collapsible="true">
            <table cellpadding="4" cellspacing="4" border="0" class="alter-table-h" style="font-size: 12px;width: 90%;">
                <tr>
                    <td>质押次数</td>
                    <td>
                        <input id="pledgeNumber" name="paramMap[pledgeNumber]" class="easyui-textbox" type="text" title="质押次数" data-options="required:false"/>
                    </td>
                    <td>保全次数</td>
                    <td>
                        <input id="preservationNumber" name="paramMap[preservationNumber]" class="easyui-textbox" type="text" title="保全次数" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>引证总次数</td>
                    <td>
                        <input id="citationsNumber" name="paramMap[citationsNumber]" class="easyui-textbox" type="text" title="引证总次数" data-options="required:false"/>
                    </td>
                    <td>专利引证次数</td>
                    <td>
                        <input id="patentCitationsNumber" name="paramMap[patentCitationsNumber]" class="easyui-textbox" type="text" title="专利引证次数" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>非专利引证次数</td>
                    <td>
                        <input id="excludePatentCitationNumber" name="paramMap[excludePatentCitationNumber]" class="easyui-textbox" type="text" title="非专利引证次数" data-options="required:false"/>
                    </td>
                    <td>被引证次数</td>
                    <td>
                        <input id="areQuoteNumber" name="paramMap[areQuoteNumber]" class="easyui-textbox" type="text" title="被引证次数" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>复审次数</td>
                    <td>
                        <input id="reviewNumber" name="paramMap[reviewNumber]" class="easyui-textbox" type="text" title="复审次数" data-options="required:false"/>
                    </td>
                    <td>无效次数</td>
                    <td>
                        <input id="invalidNumber" name="paramMap[invalidNumber]" class="easyui-textbox" type="text" title="无效次数" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>判决次数</td>
                    <td>
                        <input id="sentenceNumber" name="paramMap[sentenceNumber]" class="easyui-textbox" type="text" title="判决次数" data-options="required:false"/>
                    </td>
                    <td>国家代码</td>
                    <td>
                        <input id="countryCode" name="paramMap[countryCode]" class="easyui-textbox" type="text" title="国家代码" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>国家名</td>
                    <td>
                        <input id="countryName" name="paramMap[countryName]" class="easyui-textbox" type="text" title="国家名" data-options="required:false"/>
                    </td>
                    <td>省名</td>
                    <td>
                        <input id="provinceName" name="paramMap[provinceName]" class="easyui-textbox" type="text" title="省名" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>引证号</td>
                    <td>
                        <input id="citationsCode" name="paramMap[citationsCode]" class="easyui-textbox" type="text" title="引证号" data-options="required:false"/>
                    </td>
                    <td>引证分类号</td>
                    <td>
                        <input id="citationsCategoryCode" name="paramMap[citationsCategoryCode]" class="easyui-textbox" type="text" title="引证分类号" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>引证申请人</td>
                    <td>
                        <input id="citationsApplyer" name="paramMap[citationsApplyer]" class="easyui-textbox" type="text" title="引证申请人" data-options="required:false"/>
                    </td>
                    <td>引证国家</td>
                    <td>
                        <input id="citationsCountry" name="paramMap[citationsCountry]" class="easyui-textbox" type="text" title="引证国家" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>非专利引证</td>
                    <td>
                        <input id="nonPatentCitation" name="paramMap[nonPatentCitation]" class="easyui-textbox" type="text" title="非专利引证" data-options="required:false"/>
                    </td>
                    <td>被引证号</td>
                    <td>
                        <input id="quitedNumber" name="paramMap[quitedNumber]" class="easyui-textbox" type="text" title="被引证号" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>被引证国家</td>
                    <td>
                        <input id="citedCountry" name="paramMap[citedCountry]" class="easyui-textbox" type="text" title="被引证国家" data-options="required:false"/>
                    </td>
                    <td>分类号部</td>
                    <td>
                        <input id="classificationDepartment" name="paramMap[classificationDepartment]" class="easyui-textbox" type="text" title="分类号部" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>分类号大部</td>
                    <td>
                        <input id="classificationBigDepartment" name="paramMap[classificationBigDepartment]" class="easyui-textbox" type="text" title="分类号大部" data-options="required:false"/>
                    </td>
                    <td>分类号小部</td>
                    <td>
                        <input id="classificationSmallDepartment" name="paramMap[classificationSmallDepartment]" class="easyui-textbox" type="text" title="分类号小部" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>分类号大组</td>
                    <td>
                        <input id="classificationBigGroup" name="paramMap[classificationBigGroup]" class="easyui-textbox" type="text" title="分类号大组" data-options="required:false"/>
                    </td>
                    <td>分类号小组</td>
                    <td>
                        <input id="classificationSmallGroup" name="paramMap[classificationSmallGroup]" class="easyui-textbox" type="text" title="分类号小组" data-options="required:false"/>
                    </td>
                </tr>
            </table>
        </div>
        <div style="text-align: center;padding-left: 40px;padding-top: 10px; padding-bottom: 5px;">
            <button type="button" value="确认" id="=subBut" onclick="submitBestSearchForm()" class="easyui-linkbutton l-btn" style="margin-right: 20px;">确认</button>
            <button type="reset"  value="关闭" id="subClose" class="easyui-linkbutton l-btn" onclick="closeWindow('bestSearchWin');" >关闭</button>
        </div>
    </form>
</div>
<style type="text/css">

</style>
<script type="text/javascript">
    $(document).ready(function () {
        /*加载专利类型下拉数据*/
        $("#patentType").combobox({
            url: '${ctx}/common/datadict/getByTypeCode.do?typeCode=IPR_PATENT_TYPE',
            emptyText: '',
            valueField: 'dictValue',
            textField: 'dictName',
            slide: true,
            valueFieldID: 'patentType',
            limitToList: true,
            panelHeight: 'auto'
        });
    });
</script>
