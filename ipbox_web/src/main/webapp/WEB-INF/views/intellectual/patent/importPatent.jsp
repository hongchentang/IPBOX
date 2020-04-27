<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes')}" var="fileTypes2"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize2"/>

<div id="layout1" style="padding-top: 10px;padding-left:0px;" class="easyui-panle" data-options="region:'center',title:''">
    <form name="patentForm" method="post" id="patentForm" action="${ctx}/intellectual/patent/ipmort.do" enctype="multipart/form-data">
        <%--放修改时候的ID--%>
        <input type="text" id="id" name="id" style="display: none;" value='' />
        <input type="text" id="deleteAttachmentIds" name="deleteAttachmentIds" style="display: none;" value='' />
        <input type="text" id="mongoPatentId" name="mongoPatentId" style="display: none;" value='' />

        <div class="easyui-panel" title="文件信息" collapsible="true">
            <c:if test="${not empty patent.id}">
                <c:set var="height" value="200px"/>
            </c:if>
            <c:if test="${empty patent.id}">
                <c:set var="height" value="120px"/>
            </c:if>
            <div class="easyui-panel" title="<center style='width: 790px;font-size: 10px;'>专利文件</center>" data-options="collapsible: true,collapsed:false">
                <div class="panel-body easyui-layout" style="height: ${height};">
                    <div data-options="region:'north'" style="height:30px;">
                        <input name="sourceFile" type="file" id="sourceFile" multiple="multiple" onchange="fileSelect(this)" style="width:100%;"/>
                    </div>
          <%--           <div data-options="region:'east'" class="tip_d">
                        <label>允许上传的文件类型：${fileTypes2}</label><br/>
                        <label>允许上传文件的大小：${fileMaxSize}KB</label><br/>
                        <label>提示：按住 Ctrl 可多选文件</label><br/>
                        <c:if test="${not empty patent.id}">
                            <label>提示：点击- <a href="javascript: void(0)"><i class="fa fa-times"></i></a> -可以删除已保存的文件</label><br/>
                        </c:if>       
                    </div>   --%>    
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
  
        $("#importPatentWindow").find('#patentForm').form('submit', {
            success: function (data) {
                data = JSON.parse(data);
                if (data.code === 200) {
               
                    /*刷新列表*/
                    refreshPatent();
                    /*关闭窗口*/
                    closeWindow("importPatentWindow");
                    $.messager.alert('提示','导入成功');
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
                    debugger
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
                    $('#authorizeDate').val(dealPullPatentDate(legal.patent_legal_status.authorization_date + ''));
                    //是否授权
                    if(legalStatus == 29){
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
                    debugger
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
                    console.log("找不到专利：" + data.msg);
                    icon.removeClass('fa fa-refresh fa-spin fa-fw');
                    icon.addClass('fa fa-link');
                    icon.attr('title','点击加载知识产权库专利数据');
                }
            },
            error: function(data, status, e) {

            }
        });
    }

    function dealPullPatentDate(dateStr) {
        if(dateStr != null && dateStr != '' && dateStr != '0'){
            dateStr = dateStr.substr(0, 4) + '-' + dateStr.substr(4,2) + '-' + dateStr.substr(6,2);
            return dateStr;
        }
    }
</script>
