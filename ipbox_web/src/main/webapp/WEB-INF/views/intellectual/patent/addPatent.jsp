<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes')}" var="fileTypes2"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize2"/>
<div id="layout1" style="padding-top: 10px;padding-left:0px;" class="easyui-panle" data-options="region:'center',title:''">
    <form name="patentForm" method="post" id="patentForm" action="${ctx}/intellectual/patent/add.do" enctype="multipart/form-data">
        <%--放修改时候的ID--%>
        <input type="text" id="id" name="id" style="display: none;" value='' />
        <input type="text" id="deleteAttachmentIds" name="deleteAttachmentIds" style="display: none;" value='' />
        <input type="text" id="mongoPatentId" name="mongoPatentId" style="display: none;" value='' />
        <div class="easyui-panel" title="案件/专利基本信息" collapsible="true">
            <table  cellpadding="4" cellspacing="4" border="0" class="alter-table-h" style="font-size: 12px;width: 90%;">
                <tr>
                    <td>专利号/申请号*</td>
                    <td class="td_long">
                        <input id="appNumber" name="appNumber" class="easyui-textbox" type="text" title="专利号/申请号" data-options="required:true"/>
                    </td>
                    <td>
                        <i id="appNumberIcon" class="fa fa-link" aria-hidden="true" title="点击加载知识产权库专利数据" onclick="pullOutPatent()"></i>
                    </td>
                </tr>
                <tr>
                    <td>案件/专利名称*</td>
                    <td colspan="3">
                        <input id="patentName" name="patentName" class="easyui-textbox" type="text" title="案件/专利名称" data-options="required:true, multiline:true" style="width: 92%"/>
                    </td>
                </tr>
                <tr>
                    <td class="td_short">英文名称</td>
                    <td colspan="3" class="">
                        <input id="patentEnglishName" name="patentEnglishName" class="easyui-textbox" type="text" title="英文名称" data-options="required:false, multiline:true" style="width: 92%;"/>
                    </td>
                </tr>
                <tr>
                    <td class="td_short">专利类型*</td>
                    <td class="td_long"><input id="patentType" name="patentType" class="easyui-textbox" type="text" title="专利类型" data-options="required:true"/></td>
                    <td>申请日*</td>
                    <td>
                        <input id='appDate' name='appDate' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="required:true" title="申请日"  value='' autocomplete="off"/>
                    </td>
                </tr>
                <tr>
                    <td>申请人/专利权人*</td>
                    <td><input id="applyer" name="applyer" class="easyui-textbox" type="text" title="申请人/专利权人" data-options="required:true"/></td>
                    <td>申请人文号</td>
                    <td><input id="applyerNumber" name="applyerNumber" class="easyui-textbox" type="text" title="申请人文号" data-options="required:false"/></td>
                </tr>
                <tr>
                    <td>申请人地址</td>
                    <td colspan="3">
                        <input id="applyerAddress" name="applyerAddress" class="easyui-textbox" type="text" title="申请人地址" data-options="required:false" style="width: 92%; height: 25px;"/>
                    </td> 
                </tr>
                <tr>
                    <td>申请国家</td>
                    <td><input id="authorCountry" name="authorCountry" class="easyui-textbox" type="text" title="申请国家" data-options="required:false"/></td>
                    <td>法律状态</td>
                    <td><input id="legalStatus" name="legalStatus" class="easyui-textbox" type="text" title="法律状态" data-options="required:false"/></td>
                </tr>
                <tr>
                    <td>发明人*</td>
                    <td><input id="inventor" name="inventor" class="easyui-textbox" type="text" title="发明人" data-options="required:true"/></td>
                    <td>第一发明人*</td>
                    <td><input id="firstInventor" name="firstInventor" class="easyui-textbox" type="text" title="第一发明人" data-options="required:true"/></td>
                </tr>
                <tr>
                    <td>公开号</td>
                    <td><input id="publicationNumber" name="publicationNumber" class="easyui-textbox" type="text" title="公开号" data-options="required:false"/></td>
                    <td>公开日</td>
                    <td>
                        <input id='publicationDate' name='publicationDate' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="申请日"  value='' autocomplete="off"/>
                    </td>
                </tr>
                <tr>
                    <td>公告号</td>
                    <td><input id="announcementNumber" name="announcementNumber" class="easyui-textbox" type="text" title="公告号" data-options="required:false"/></td>
                    <td>公告日</td>
                    <td>
                        <input id='announcementDate' name='announcementDate' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="公告日"  value='' autocomplete="off"/>
                    </td>
                </tr>
                <tr>
                    <td>是否授权发文</td>
                    <td>
                        <input id="isAuthorize" name="isAuthorize" class="easyui-textbox" type="text" title="是否授权" data-options="required:true"/>
                    </td>
                    <td>授权发文日</td>
                    <td>
                        <input id='authorizeDate' name='authorizeDate' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="授权日"  value='' autocomplete="off" />
                    </td>
                </tr>
                <tr>
                    <td>证书号</td>
                    <td><input id="certificateNumber" name="certificateNumber" class="easyui-textbox" type="text" title="证书号" data-options="required:false"/></td>
                    <td>届满日</td>
                    <td>
                        <input id='expirationDate' name='expirationDate' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="届满日"  value='' autocomplete="off"/>
                    </td>
                </tr>
                <tr>
                    <td>进入实审日</td>
                    <td>
                        <%--<input id="startSubstantiveExaminationDate" name="startSubstantiveExaminationDate" class="easyui-textbox" type="text" title="进入实审日" data-options="required:false"/>--%>
                        <input id='sseDate' name='sseDate' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="进入实审日"  value='' autocomplete="off"/>
                    </td>
                    <td>开卷日</td>
                    <td>
                        <input id='openBookDate' name='openBookDate' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="开卷日"  value='' autocomplete="off"/>
                    </td>
                </tr>
                <tr>
                    <td>优先权号</td>
                    <td><input id="priorityNumber" name="priorityNumber" class="easyui-textbox" type="text" title="优先权号" data-options="required:false"/></td>
                    <td>优先权日</td>
                    <td>
                        <input id='priorityDate' name='priorityDate' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="优先权日"  value='' autocomplete="off"/>
                    </td>
                </tr>
                <tr>
                    <td>PCT申请号</td>
                    <td><input id="pctApplyNumber" name="pctApplyNumber" class="easyui-textbox" type="text" title="PCT申请号" data-options="required:false"/></td>
                    <td>PCT公布号</td>
                    <td><input id="pctAnnouncementNumber" name="pctAnnouncementNumber" class="easyui-textbox" type="text" title="PCT公布号" data-options="required:false"/></td>
                </tr>
                <tr>
                    <td>PCT公布日</td>
                    <td>
                        <input id='pctAnnouncementDate' name='pctAnnouncementDate' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="PCT公布日"  value='' autocomplete="off"/>
                    </td>
                </tr>
            </table>
        </div>
        <div class="easyui-panel" title="代理基本信息" collapsible="true">
            <table cellpadding="4" cellspacing="4" border="0" class="alter-table-h" style="font-size: 12px;width: 90%;">
                <tr>
                    <td class="td_short">委案日期</td>
                    <td class="td_long">
                        <input id=entrustDate' name='entrustDate' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                               class="easyui-validatebox Wdate" data-options="" title="委案日期"  value='' autocomplete="off"/>
                    </td>
                    <td class="td_short">起始阶段</td>
                    <td class="td_long">
                        <input id='entrustStage' name='entrustStage' type="select" title="起始阶段"   value='' />
                    </td>
                </tr>
                <tr>
                    <td>案件承办部门</td>
                    <td>
                        <input id="depId" name="depId" class="easyui-textbox" type="text" title="案件承办部门" data-options="required:true"/>
                    </td>
                    <td>代理人</td>
                    <td>
                        <input id="agency" name="agency" class="easyui-textbox" type="text" title="代理人" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>案件处理人</td>
                    <td>
                        <input id="entrustHandler" name="entrustHandler" class="easyui-textbox" type="text" title="案件处理人" data-options="required:false"/>
                    </td>
                    <td>业务助理</td>
                    <td>
                        <input id="assistantHandler" name="assistantHandler" class="easyui-textbox" type="text" title="案件处理人" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td colspan="3">
                        <input id="agencyRemark" name="agencyRemark" class="easyui-textbox" type="text" title="案件处理人" data-options="required:false" style="width: 92%"/>
                    </td>
                </tr>
            </table>
        </div>
        <div class="easyui-panel" title=" 客户基本信息" collapsible="true">
            <table cellpadding="4" cellspacing="4" border="0" class="alter-table-h" style="font-size: 12px;width: 90%;">
                <tr>
                    <td class="td_short">客户名称</td>
                    <td class="td_long">
                        <input id="customerName" name="customerName" class="easyui-textbox" type="text" title="客户名称" data-options="required:false"/>
                    </td>
                    <td class="td_short">客户代码</td>
                    <td class="td_long">
                        <input id="customerCode" name="customerCode" class="easyui-textbox" type="text" title="客户代码" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>客户联系人</td>
                    <td>
                        <input id="customerContacts" name="customerContacts" class="easyui-textbox" type="text" title="客户联系人" data-options="required:false"/>
                    </td>
                    <td>客户来源</td>
                    <td>
                        <input id="customerSource" name="customerSource" class="easyui-textbox" type="text" title="客户来源" data-options="required:false"/>
                    </td>
                </tr>
                <tr>
                    <td>客户地址</td>
                    <td colspan="3">
                        <input id="customerAddress" name="customerAddress" class="easyui-textbox" type="text" title="客户地址" data-options="required:false" style="width: 92%"/>
                    </td>
                </tr>
            </table>
        </div>
        <div class="easyui-panel" title="文件信息" collapsible="true">
            <c:if test="${not empty patent.id}">
                <c:set var="height" value="200px"/>
            </c:if>
            <c:if test="${empty patent.id}">
                <c:set var="height" value="120px"/>
            </c:if>
            <div class="easyui-panel" title="<center style='width: 790px;font-size: 10px;'>申请原文件</center>" data-options="collapsible: true,collapsed:true">
                <div class="panel-body easyui-layout" style="height: ${height};">
                    <div data-options="region:'north'" style="height:30px;">
                        <input name="sourceFile" type="file" id="sourceFile" multiple="multiple" onchange="fileSelect(this)" style="width:100%;"/>
                    </div>
                    <div data-options="region:'east'" class="tip_d">
                        <label>允许上传的文件类型：${fileTypes2}</label><br/>
                        <label>允许上传文件的大小：${fileMaxSize}KB</label><br/>
                        <label>提示：按住 Ctrl 可多选文件</label><br/>
                        <label>提示：不能上传空文档</label><br/>
                        <c:if test="${not empty patent.id}">
                            <label>提示：点击- <a href="javascript: void(0)"><i class="fa fa-times"></i></a> -可以删除已保存的文件</label><br/>
                        </c:if>       
                    </div>      
                    <div data-options="region:'west'" style="width: 60%;padding-inline-start: 20px; overflow: auto;">
                        <div class="file_d">已选择的文件：</div>
                        <fieldset class="under_border">
                            <ul id="sourceFileSelectUl" class="file_ul">

                            </ul>
                        </fieldset>
                        <c:if test="${not empty patent.id}">
                            <div class="file_d">已保存的文件：</div>
                            <fieldset class="under_border">
                                <ul id="sourceFileSaveUl" class="file_ul">

                                </ul>
                            </fieldset>
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="easyui-panel" title="<center style='width: 790px;font-size: 10px;'>受理通知书</center>" data-options="collapsible: true,collapsed:true">
                <div class="panel-body easyui-layout" style="height: ${height};">
                    <div data-options="region:'north'" style="height:30px;">
                        <input name="authorizationFile" type="file" id="authorizationFile" multiple="multiple" onchange="fileSelect(this)" style="width:100%;"/>
                    </div>
                    <div data-options="region:'east'" class="tip_d">
                        <label>允许上传的文件类型：${fileTypes2}</label><br/>
                        <label>允许上传文件的大小：${fileMaxSize}KB</label><br/>
                        <label>提示：按住 Ctrl 可多选文件</label><br/>
                        <label>提示：不能上传空文档</label><br/>
                        <c:if test="${not empty patent.id}">
                            <label>提示：点击- <a href="javascript: void(0)"><i class="fa fa-times"></i></a> -可以删除已保存的文件</label><br/>
                        </c:if>
                    </div>
                    <div data-options="region:'west'" style="width: 60%;padding-inline-start: 20px; overflow: auto;">
                        <div class="file_d">已选择的文件：</div>
                        <fieldset class="under_border">
                            <ul id="authorizationFileSelectUl" class="file_ul">

                            </ul>
                        </fieldset>

                        <c:if test="${not empty patent.id}">
                            <div  class="file_d">已保存的文件：</div>
                            <fieldset class="under_border">
                                <ul id="authorizationFileSaveUl" class="file_ul">

                                </ul>
                            </fieldset>
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="easyui-panel" title="<center style='width: 790px;font-size: 10px;'>专利证书</center>" data-options="collapsible: true,collapsed:true">
                <div class="panel-body easyui-layout" style="height: ${height};">
                    <div data-options="region:'north'" style="height:30px;">
                        <input name="patentFile" type="file" id="patentFile" multiple="multiple" onchange="fileSelect(this)" style="width:100%;"/>
                    </div>
                    <div data-options="region:'east'" class="tip_d">
                        <label>允许上传的文件类型：${fileTypes2}</label><br/>
                        <label>允许上传文件的大小：${fileMaxSize}KB</label><br/>
                        <label>提示：按住 Ctrl 可多选文件</label><br/>
                        <label>提示：不能上传空文档</label><br/>
                        <c:if test="${not empty patent.id}">
                            <label>提示：点击- <a href="javascript: void(0)"><i class="fa fa-times"></i></a> -可以删除已保存的文件</label><br/>
                        </c:if>
                    </div>
                    <div data-options="region:'west'" style="width: 60%;padding-inline-start: 20px; overflow: auto;">
                        <div class="file_d">已选择的文件：</div>
                        <fieldset class="under_border">
                            <ul id="patentFileSelectUl" class="file_ul">

                            </ul>
                        </fieldset>

                        <c:if test="${not empty patent.id}">
                            <div>已保存的文件：</div>
                            <fieldset class="under_border">
                                <ul id="patentFileSaveUl" class="file_ul">

                                </ul>
                            </fieldset>
                        </c:if>

                    </div>
                </div>
            </div>

            <div class="easyui-panel" title="<center style='width: 790px;font-size: 10px;'>检索报告</center>" data-options="collapsible: true,collapsed:true">
                <div class="panel-body easyui-layout" style="height: ${height};">
                    <div data-options="region:'north'" style="height:30px;">
                        <input name="searchFile" type="file" id="searchFile" multiple="multiple" onchange="fileSelect(this)" style="width:100%;"/>
                    </div>
                    <div data-options="region:'east'" class="tip_d">
                        <label>允许上传的文件类型：${fileTypes2}</label><br/>
                        <label>允许上传文件的大小：${fileMaxSize}KB</label><br/>
                        <label>提示：按住 Ctrl 可多选文件</label><br/>
                        <label>提示：不能上传空文档</label><br/>
                        <c:if test="${not empty patent.id}">
                            <label>提示：点击- <a href="javascript: void(0)"><i class="fa fa-times"></i></a> -可以删除已保存的文件</label><br/>
                        </c:if>
                    </div>
                    <div data-options="region:'west'" style="width: 60%;padding-inline-start: 20px; overflow: auto;">
                        <div class="file_d">已选择的文件：</div>
                        <fieldset class="under_border">
                            <ul id="searchFileSelectUl" class="file_ul">

                            </ul>
                        </fieldset>

                        <c:if test="${not empty patent.id}">
                            <div class="file_d">已保存的文件：</div>
                            <fieldset class="under_border">
                                <ul id="searchFileSaveUl" class="file_ul">

                                </ul>
                            </fieldset>
                        </c:if>

                    </div>
                </div>
            </div>

            <div class="easyui-panel" title="<center style='width: 790px;font-size: 10px;'>代理合同</center>" data-options="collapsible: true,collapsed:true">
                <div class="panel-body easyui-layout" style="height: ${height};">
                    <div data-options="region:'north'" style="height:30px;">
                        <input name="agencyFile" type="file" id="agencyFile" multiple="multiple" onchange="fileSelect(this)" style="width:100%;"/>
                    </div>
                    <div data-options="region:'east'" class="tip_d">
                        <label>允许上传的文件类型：${fileTypes2}</label><br/>
                        <label>允许上传文件的大小：${fileMaxSize}KB</label><br/>
                        <label>提示：按住 Ctrl 可多选文件</label><br/>
                        <label>提示：不能上传空文档</label><br/>
                        <c:if test="${not empty patent.id}">
                            <label>提示：点击- <a href="javascript: void(0)"><i class="fa fa-times"></i></a> -可以删除已保存的文件</label><br/>
                        </c:if>
                    </div>
                    <div data-options="region:'west'" style="width: 60%;padding-inline-start: 20px; overflow: auto;">
                        <div class="file_d">已选择的文件：</div>
                        <fieldset class="under_border">
                            <ul id="agencyFileSelectUl" class="file_ul">

                            </ul>
                        </fieldset>
                        <c:if test="${not empty patent.id}">
                            <div class="file_d">已保存的文件：</div>
                            <fieldset class="under_border">
                                <ul id="agencyFileSaveUl" class="file_ul">

                                </ul>
                            </fieldset>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div style="text-align: center;padding-left: 40px;padding-top: 10px; padding-bottom: 5px;">
            <button type="button" value="确认" id="=subBut" onclick="submitForm()" class="easyui-linkbutton l-btn" style="margin-right: 20px;">确认</button>
            <button type="reset"  value="关闭" id="subClose" class="easyui-linkbutton l-btn" onclick="doClose()" >关闭</button>
        </div>
    </form>
</div>
<style type="text/css">
    .under_border{
        height: auto!important;
        min-height: 30px;
        width: 90%;
        background-color: aliceblue;
        border: inset;
        border-top-width: 2px;
        border-right-width: 2px;
        border-bottom-width: 2px;
        border-left-width: 2px;
    }
    .file_d{
        padding-top: 5px;
        padding-bottom: 5px;
    }
    .file_ul{
        margin-block-start: 0em;
        padding-inline-start: 10px;
    }
    .td_short{
        width: 17%;
    }
    .td_long{
        width: 33%;
    }
    .tip_d{
        width: 40%;
        padding-top: 5px;
        padding-left: 15px;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {

        console.log('document ready!...${patent.patentName}');
        /* 加载修改的数据 */
        if ('${patent.id}' !== '') {
            $('#patentForm').form('load', {
                patentName: '${patent.patentName}',
                appNumber: '${patent.appNumber}',
                appDate: '<fmt:formatDate value="${patent.appDate }"  pattern="yyyy-MM-dd"/>',
                patentEnglishName: '${patent.patentEnglishName}',
                patentType: '${patent.patentType}',
                applyer: '${patent.applyer}',
                applyerNumber: '${patent.applyerNumber}',
                applyerAddress: '${patent.applyerAddress}',
                authorCountry: '${patent.authorCountry}',
                inventor: '${patent.inventor}',
                firstInventor: '${patent.firstInventor}',
                publicationNumber: '${patent.publicationNumber}',
                publicationDate: '<fmt:formatDate value="${patent.publicationDate}"  pattern="yyyy-MM-dd"/>',
                announcementNumber: '${patent.announcementNumber}',
                announcementDate: '<fmt:formatDate value="${patent.announcementDate}"  pattern="yyyy-MM-dd"/>',
                isAuthorize: '${patent.isAuthorize}',
                authorizeDate: '<fmt:formatDate value="${patent.authorizeDate}"  pattern="yyyy-MM-dd"/>',
                certificateNumber: '${patent.certificateNumber}',
                expirationDate: '<fmt:formatDate value="${patent.expirationDate}"  pattern="yyyy-MM-dd"/>',
                sseDate: '<fmt:formatDate value="${patent.sseDate}"  pattern="yyyy-MM-dd"/>',
                openBookDate: '<fmt:formatDate value="${patent.openBookDate}"  pattern="yyyy-MM-dd"/>',
                priorityNumber: '${patent.priorityNumber}',
                priorityDate: '<fmt:formatDate value="${patent.priorityDate}"  pattern="yyyy-MM-dd"/>',
                pctApplyNumber: '${patent.pctApplyNumber}',
                pctAnnouncementNumber: '${patent.pctAnnouncementNumber}',
                pctAnnouncementDate: '<fmt:formatDate value="${patent.pctAnnouncementDate}"  pattern="yyyy-MM-dd"/>',
                entrustDate: '<fmt:formatDate value="${patent.entrustDate}"  pattern="yyyy-MM-dd"/>',
                entrustStage: '${patent.entrustStage}',
                depId: '${patent.depId}',
                agency: '${patent.agency}',
                entrustHandler: '${patent.entrustHandler}',
                assistantHandler: '${patent.assistantHandler}',
                agencyRemark: '${patent.agencyRemark}',
                customerName: '${patent.customerName}',
                customerCode: '${patent.customerCode}',
                customerContacts: '${patent.customerContacts}',
                customerSource: '${patent.customerSource}',
                customerAddress: '${patent.customerAddress}',
                companyId: '${patent.companyId}',
                legalStatus: '${patent.legalStatus}'
            });

            //回写关联id
            $('#mongoPatentId').val('${patent.mongoPatentId}');
            
            //控制显示文件情况
            {
                //1.申请原文件
                <c:forEach items="${patent.attachments.sourceFile}" var="file">
                $('#sourceFileSaveUl').append(" <li class='' fileId='" + '${file.id}' + "'><span>" + '${file.fileName}' +
                    "&nbsp;</span><a href=\"javascript: void(0)\"><i onclick='deleteFile(this)' class='fa fa-times'/></a></li> ");
                </c:forEach>
                //2.受理通知书
                <c:forEach items="${patent.attachments.authorizationFile}" var="file">
                $('#authorizationFileSaveUl').append(" <li class='' fileId='" + '${file.id}' + "'><span>" + '${file.fileName}' +
                    "&nbsp;</span><a href=\"javascript: void(0)\"><i onclick='deleteFile(this)' class='fa fa-times'/></a></li> ");
                </c:forEach>
                //3.专利证书
                <c:forEach items="${patent.attachments.patentFile}" var="file">
                $('#patentFileSaveUl').append(" <li class='' fileId='" + '${file.id}' + "'><span>" + '${file.fileName}' +
                    "&nbsp;</span><a href=\"javascript: void(0)\"><i onclick='deleteFile(this)' class='fa fa-times'/></a></li> ");
                </c:forEach>
                //4.检索报告
                <c:forEach items="${patent.attachments.searchFile}" var="file">
                $('#searchFileSaveUl').append(" <li class='' fileId='" + '${file.id}' + "'><span>" + '${file.fileName}' +
                    "&nbsp;</span><a href=\"javascript: void(0)\"><i onclick='deleteFile(this)' class='fa fa-times'/></a></li> ");
                </c:forEach>
                //5.代理合同
                <c:forEach items="${patent.attachments.agencyFile}" var="file">
                $('#agencyFileSaveUl').append(" <li class='' fileId='" + '${file.id}' + "'><span>" + '${file.fileName}' +
                    "&nbsp;</span><a href=\"javascript: void(0)\"><i onclick='deleteFile(this)' class='fa fa-times'/></a></li> ");
                </c:forEach>
            }
        }

        /*加载法律状态下拉数据*/
        $("#legalStatus").combobox({
            url: '${ctx}/common/datadict/getByTypeCode.do?typeCode=IPBOX_LEGAL_STATUS',
            emptyText: '',
            valueField: 'dictValue',
            textField: 'dictName',
            slide: true,
            valueFieldID: 'legalStatus',
            limitToList: true,
            panelHeight: '200'
        });

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

        /*加载授权国家下拉数据*/
        $("#authorCountry").combobox({
            url: '${ctx}/common/datadict/getByTypeCode.do?typeCode=IPBOX_PATENT_AUTHOR_COUNTRY',
            emptyText: '',
            valueField: 'dictValue',
            textField: 'dictName',
            slide: true,
            valueFieldID: 'authorCountry',
            limitToList: true,
            panelHeight: 'auto'
        });

        //委案起始阶段
        $("#entrustStage").combobox({
            url: '${ctx}/common/datadict/getByTypeCode.do?typeCode=IPBOX_AGENCY_START_STAGE',
            emptyText: '',
            valueField: 'dictValue',
            textField: 'dictName',
            slide: true,
            valueFieldID: 'authorCountry',
            limitToList: true,
            panelHeight: 'auto'
        });

        //是否授权
        $("#isAuthorize").combobox({
            url: '${ctx}/common/datadict/getByTypeCode.do?typeCode=IPBOX_IS_AUTHORIZE',
            emptyText: '',
            valueField: 'dictValue',
            textField: 'dictName',
            slide: true,
            valueFieldID: 'isAuthorize',
            limitToList: true,
            panelHeight: 'auto'
        });

        //案件承办部门
        $("#depId").combobox({
            url: '${ctx}/common/dept/getDepartmentList.do?unitId=',
            emptyText: '',
            valueField: 'id',
            textField: 'deptName',
            slide: true,
            valueFieldID: 'depId',
            limitToList: true,
            panelHeight: 'auto',
            onLoadSuccess: function () {
                var selectId = '${patent.depId}';
                if(selectId == ''){
                    //取登录用户默认的部门
                    $("#depId").combobox('setValue', '${firstDeptId}');
                }
            }
        });

        //初始化时是否授权和授权日期的联动
        var isAuthorize = '${patent.isAuthorize}';
        debugger
        if (isAuthorize == "0") {
            $("#authorizeDate").validatebox({disabled: false, required: true});
        }



    });

    function displayFileDiv(el, fileName){
        if(fileName !== ""){
            $("#" + el + "Div").css("display", "none");
        }else {
            $("#" + el + "DeleteDiv").css("display", "none");
        }
    }

    /*是否授权和授权日期的联动*/
    $('#isAuthorize').combobox({
        onChange: function(){
            var cde = ($('#isAuthorize').combobox('getValue'));
            if(cde === "0"){
                //$("#authorizeDate").removeAttr('disabled','');
                $('#authorizeDate').validatebox({required: true});
            } else {
                //$("#authorizeDate").attr('disabled','');
                $('#authorizeDate').validatebox({required: false});
            }
        }
    });

    /*监听文件选择框，入股选择了之后就上传到服务器，返回文件信息*/
    /*$(":file").change(function () {
        var id = this.id;
        var file = $("#" + id);

        /!*上传文件*!/
        var formData = new FormData();
        // 给formData对象添加<input>标签,注意与input标签的ID一致
        formData.append('uploadFile',file[0].files[0]);
        $.ajax({
            url : '/intellectual/patent/upload.do',//这里写你的url
            type : 'POST',
            data : formData,
            contentType: false,// 当有文件要上传时，此项是必须的，否则后台无法识别文件流的起始位置
            processData: false,// 是否序列化data属性，默认true(注意：false时type必须是post)
            dataType: 'json',//这里是返回类型，一般是json,text等
            clearForm: true,//提交后是否清空表单数据
            success: function(data) {   //提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
                var reCode = data.code;
                if(reCode == 200){
                    //把文件名写到输入框中
                    console.log('上传成功：' + data.data.attachmentName);
                    $("#" + id + "Text").val(data.data.attachmentName);
                }else {
                    alert(data.msg);
                    cleanFile(file);
                }
            },
            error: function(data, status, e) {  //提交失败自动执行的处理函数。
                console.error(e);
            }
        });

    });*/

    function cleanFile(element) {
        element.val('');
    }

    /*上传文件*/
    function uploadFile(targetId){
        $("#" + targetId + "").trigger("click");
    }

    /*提交表单*/
    function submitForm() {

        //进行id赋值
        var id = "${patent.id}";
        console.log("ID = " + id);
        $("#id").val(id);
        $("#addPatentWindow,#editPatentWindow").find('#patentForm').form('submit', {
            success: function (data) {
                data = JSON.parse(data);
                if (data.code === 200) {
                    /*刷新列表*/
                    refreshPatent();
                    /*关闭窗口*/
                    doClose();
                } else {
                    $.messager.alert('错误','保存失败' + data.msg);
                }
            }
        });
    }

    function fileSelect(obj) {
        var files = obj.files;
        var fileId = obj.id;
        var fileUl = $('#' + fileId + 'SelectUl');
        fileUl.empty();
        $.each(files, function (idx, file) {
            addFileNameLi(fileUl, file.name, idx, fileId);
        });
    }

    function addFileNameLi(fileUl, fileName) {
        fileUl.append(" <li style=\"margin-right: 5px;\"><span>" + fileName + "&nbsp;</span></li> ");
    }

    /*删除已保存附件*/
    function deleteFile(obj) {
        //记录删除的id
        obj = $(obj).parent().parent();
        var ids = $("#deleteAttachmentIds");
        var fileId =  obj.attr('fileId');
        var deleteAttachmentIds = ids.val() + ',' + fileId;
        ids.val(deleteAttachmentIds);
        //页面去除这个元素
        obj.remove();
    }
    
    function pullOutPatent() {
        var appNumber = $('#appNumber').textbox('getValue');
        var icon = $('#appNumberIcon');
        icon.removeClass('fa fa-link');
        icon.addClass('fa fa-refresh fa-spin fa-fw');
        icon.attr('title','正在加载知识产权库专利数据');
        $.ajax({
            url : '${ctx}/warehouse/patenthouse/getPatent.do?appNumber=' + appNumber,
            type : 'GET',
            dataType: 'json',
            success: function(data) {
                if(data.code == 200){
                    var patent = data.data.patent;
                    var legal = data.data.legal;

                    //隐藏保存的关联MongoDB的数据
                    $('#mongoPatentId').val(patent._id);
                    //专利名称
                    $('#patentName').textbox('setValue', patent.title.original);
                    //专利英文名
                    $('#patentEnglishName').textbox('setValue', patent.title.en);
                    var appDate = patent.application_date + '';
                    //专利申请日
                    appDate = dealPullPatentDate(appDate);
                    $('#appDate').val(appDate);
                    //专利类型
                    $('#patentType').combobox('setValue', patent.type);
                    //申请人
                    $('#applyer').textbox('setValue', patent.applicants[0].name.original);
                    //申请人文号
                    $('#applyerNumber').textbox('setValue', patent.applicants[0]._id);
                    //申请人地址
                    $('#applyerAddress').textbox('setValue',patent.applicants[0].address.original);

                    //法律状态
                    var legalStatus = legal.patent_legal_status.legal_status;
                    if(legalStatus == 1){
                        //公开
                        legalStatus = 7;
                    }else if(legalStatus == 2){
                        //授权
                        legalStatus = 29
                    }else if(legalStatus == 3){
                        //失效
                        legalStatus = 26
                    }
                    $('#legalStatus').combobox('setValue', legalStatus);
                    //发明人
                    $('#inventor').textbox('setValue', patent.inventors[0].name.original);
                    //公开号
                    $('#publicationNumber').textbox('setValue', patent.publication_number);
                    //公开日
                    $('#publicationDate').val(dealPullPatentDate(patent.earliest_publication_date + ''));
                    //授权日期
                    var authorizationDate = legal.patent_legal_status.authorization_date;
                    $('#authorizeDate').val(dealPullPatentDate( authorizationDate + ''));
                    //是否授权
                    if(legalStatus == 29){
                        $('#isAuthorize').combobox('setValue', 0);
                    }
                    if(authorizationDate != null && authorizationDate != '' ){
                        $('#isAuthorize').combobox('setValue', 0);
                    }

                    //优先权号
                    //优先权日
                    var priorities =  patent.priorities;
                    if(priorities.length != 0){
                        var prority = priorities[0];
                        $('#priorityNumber').textbox('setValue', prority.priority_number);
                        $('#priorityDate').val(dealPullPatentDate(prority.priority_date + ''));
                    }
                    //pct申请号
                    $('#pctApplyNumber').textbox('setValue', patent.world_application_number);
                    //pct公布号
                    $('#pctAnnouncementNumber').textbox('setValue', patent.world_publication_number);
                    //pct公布日
                    $('#pctAnnouncementDate').val(dealPullPatentDate( patent.world_publication_date+ ''));
                    //申请国家
                    var countries = patent.countries;
                    if(countries.length != 0 && countries[0] == 'cn'){
                        $('#authorCountry').combobox('setValue', 0);
                    }
                    icon.removeClass('fa fa-refresh fa-spin fa-fw');
                    icon.addClass('fa fa-link');
                    icon.attr('title','点击加载知识产权库专利数据');
                }else {
                    $.messager.alert('错误', data.msg);
                    icon.removeClass('fa fa-refresh fa-spin fa-fw');
                    icon.addClass('fa fa-link');
                    icon.attr('title','点击加载知识产权库专利数据');
                }
            },
            error: function(data, status, e) {
                $.messager.alert('错误', data.msg);
            }
        });
    }

    function dealPullPatentDate(dateStr) {
        if(dateStr != null && dateStr != '' && dateStr != '0'){
            dateStr = dateStr.substr(0, 4) + '-' + dateStr.substr(4,2) + '-' + dateStr.substr(6,2);
            return dateStr;
        }
        $().form()
    }
</script>
