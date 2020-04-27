<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/include/taglib.jsp" %>
<style>
    /*初始化*/
    * {margin: 0;padding: 0;list-style: none;}
    /*HI*/
    .top-hello {width: 100%;padding-top: 15px;}
    .top-helloIn {display: flex;justify-content: stretch;align-items: center;width: 90%;height: 150px;border: #dcdfe6 solid 1px;border-radius: 10px;background: #fff;margin: 0 auto 15px}
    .top-helloIn > img {display: block;width: 60px;height: 60px;margin: 5% 1%;}
    .top-helloIn > p {font-size: 16px;color: #909399;}

    /*常用模块*/
    .center-common-h3 {width: 100%;margin-left: 4%;}
    .center-common-ul {width: 100%;height: 15%;display: flex;justify-items: center;align-items: center;margin: 0 5%;}
    .center-common-ul > li {width: 15%;height: 68px;border-radius: 10px;background: #ffffff;cursor: pointer;margin: 0 1%;display: flex;justify-content: center;align-items: center;font-size: 14px;color: #606266;border: #dcdfe6 solid 1px;}
    .center-common-ul li > img {margin-right: 5%;width: 50px;height: 50px}
    .center-common-ul li > span {font-weight: bold;}
    @media screen and (max-width: 800px) {
        .center-common-ul li > span {
            display: none;
        }
    }
    /*统计图*/
    .btn-canva{width: 100%;height: 60%;display: flex;padding: 0 auto;background: #f2f6fc}
    .btn-canva-lr{width: 50%;height: 100%;margin-left: 4%;}
    .btn-canva-lr >h3{margin: 0 0 20px 0;}

    .btn-canvaIn{height:80%;width:88%;border:1px solid #dcdfe6;border-radius:10px;background:#ffffff}

    @media screen and (max-width: 1000px) {
        .btn-canva{
            flex-direction: column;
        }
        .btn-canva-lr{width: 100%;height: 100%;margin-left: 4%;}
    }
</style>
<div style="width: 100%;height: 100%;background: #f2f6fc">
    <%--HI--%>
    <div class="top-hello">
        <div class="top-helloIn">
            <img src="/themes/easyui/themes/icons/hi.png" alt="您好!欢迎使用IPbox知识产权管理系统">
            <p>您好：  ${sessionScope.loginUser.userName} <br>欢迎使用IPbox知识产权管理系统!</p>
        </div>
    </div>
    <%--<div style="padding:5px;height: 15%;background: #f2f6fc;">
        <div style="width:100%">
            <div style="margin-top: 10px;margin-left: 60px; margin-right: 120px ;padding-left: 20px;padding-right: 10px ;background: #ffffff;      border: 1px solid #dcdfe6;   width: 87.2%;height:150px;   float: left;border-radius: 10px;">
                <div style=" padding-top: 20px; height: 50px">
                    <img style="width: 60px;height: 60px;margin-top:15px; " src="/themes/easyui/themes/icons/hi.png">
                    <div style="position: relative; font-size: 16px;color: #909399;left: 70px;top: -60px;">您好：
                        <shiro:principal property="realName"/></div>
                    <div style="position: relative; font-size: 16px;color: #909399;left: 70px;top: -50px">
                        欢迎使用IPbox知识产权管理系统
                    </div>
                </div>
            </div>
        </div>
    </div>--%>
    <%--常用功能模块--%>
    <h3 class="center-common-h3">常用功能</h3>
    <ul class="center-common-ul">
        <li title="专利库管理" class="common-ul-left"
            onclick="patentList('/intellectual/patent/list.do?tabId=6a0d518daeb611e9a9e100ff45a3ab56','专利列表','6a0d518daeb611e9a9e100ff45a3ab56')">
            <img src="/themes/easyui/themes/icons/patent.png" alt="专利库管理"><span>专利库管理</span>
        </li>
        <li title="商标管理"
            onclick="patentList('/intellectual/tradeMark/list.do?tabId=1d1cb281aeb511e9a9e100ff33a3ab56','商标管理','1d1cb281aeb511e9a9e100ff33a3ab56')">
            <img src="/themes/easyui/themes/icons/brand.png" alt="商标管理"><span>商标管理</span>
        </li>
     
        <li title="官费管理"
            onclick="patentList('/intellectual/patent/cost/tabs.do?type=1','专利官费管理','5d567378895b4db9b3610253394c0dbf')">
            <img src="/themes/easyui/themes/icons/guan_cost.png" alt="官费管理"><span>官费管理</span>
        </li>
           <li title="其他费用管理"
            onclick="patentList('/intellectual/patent/cost/tabs.do?type=3','其他费用管理','5d567378895b4db9b3610253394c0dbc')">
            <img src="/themes/easyui/themes/icons/cost.png" alt="年费管理"><span>其他费用管理</span>
        </li>
        <li title="用户修改" class="common-ul-right" onclick="changeUserInfo()">
            <img src="/themes/easyui/themes/icons/upuser.png" alt="用户修改"><span>用户修改</span>
        </li>
    </ul>
    <%--    <div style="padding:0px;height: 20%;background: #f2f6fc;">
        <div style="width:100%;height: 100%;background: #f2f6fc;">
            <div style="position: relative; margin-left:4%;top:25px; font-size: 14px;"><b>常用功能</b></div>
            <div style=" margin-top: 50px;margin-left: 60px; margin-right: 0px ;padding-left: 0px;padding-right: 10px ;      border: 1px solid #dcdfe6;   width: 15.4%;    float: left; border-radius: 10px;background: #ffffff;">
                <div onclick="patentList('/intellectual/patent/list.do?tabId=6a0d518daeb611e9a9e100ff45a3ab56','专利列表','6a0d518daeb611e9a9e100ff45a3ab56')"
                     style="cursor:pointer;padding: 10px; height: 50px;">
                    <img style="width: 50px;height:50px; " src="/themes/easyui/themes/icons/patent.png">
                    <div style="position: relative; font-size: 14px;color: #909399;margin-left: 60px;top: -40px;">
                        <b>专利库管理</b></div>
                    <!--      <div style="position: relative; font-size: 18px;color: #606266;left: 70px;top: -40px"  >  100   </div> -->
                </div>
            </div>
            <div style=" margin-top: 50px;margin-left: 2%; margin-right: 0px ;padding-left: 0px;padding-right: 10px ;      border: 1px solid #dcdfe6;   width: 15.4%;    float: left;border-radius: 10px;background: #ffffff;">
                <div onclick="patentList('/intellectual/tradeMark/list.do?tabId=1d1cb281aeb511e9a9e100ff33a3ab56','商标管理','1d1cb281aeb511e9a9e100ff33a3ab56')"
                     style="cursor:pointer;padding: 10px; height: 50px">
                    <img style="width: 50px;height: 50px; " src="/themes/easyui/themes/icons/brand.png">
                    <div style="position: relative; font-size: 14px;color: #909399;left: 60px;top: -40px;"><b>商标管理</b>
                    </div>
                    <!--      <div style="position: relative; font-size: 18px;color: #606266;left: 70px;top: -40px"  >  100   </div> -->
                </div>
            </div>
            <div style=" margin-top:50px;margin-left: 2%; margin-right: 0px ;padding-left: 0px;padding-right: 10px ;      border: 1px solid #dcdfe6;   width: 15.4%;    float: left;border-radius: 10px;background: #ffffff;">
                <div onclick="patentList('/intellectual/patent/cost/list.do?type=1&tabId=5d567378895b4db9b3610253394c0dbc','专利年费管理','5d567378895b4db9b3610253394c0dbc')"
                     style="cursor:pointer;padding: 10px; height: 50px">
                    <img style="width: 50px;height: 50px; " src="/themes/easyui/themes/icons/cost.png">
                    <div style="position: relative; font-size: 14px;color: #909399;left: 60px;top: -40px;"><b>年费管理</b>
                    </div>
                    <!--      <div style="position: relative; font-size: 18px;color: #606266;left: 70px;top: -40px"  >  100   </div> -->
                </div>
            </div>
            <div style=" margin-top: 50px;margin-left: 2%; margin-right: 0px ;padding-left: 0px;padding-right: 10px ;      border: 1px solid #dcdfe6;   width: 15.4%;    float: left;border-radius: 10px;background: #ffffff;">
                <div onclick="patentList('/intellectual/patent/cost/list.do?type=2&tabId=5d567378895b4db9b3610253394c0dbf','专利官费管理','5d567378895b4db9b3610253394c0dbf')"
                     style="cursor:pointer;padding: 10px; height: 50px">
                    <img style="width: 50px;height: 50px; " src="/themes/easyui/themes/icons/guan_cost.png">
                    <div style="position: relative; font-size: 14px;color: #909399;left: 60px;top: -40px;"><b>官费管理</b>
                    </div>
                    <!--      <div style="position: relative; font-size: 18px;color: #606266;left: 70px;top: -40px"  >  100   </div> -->
                </div>
            </div>
            <div style=" margin-top: 50px;margin-left: 2%; margin-right: 0px ;padding-left: 0px;padding-right: 10px ;      border: 1px solid #dcdfe6;   width: 15.4%;    float: left;border-radius: 10px;background: #ffffff;">
                <div onclick="changeUserInfo()" style="cursor:pointer;padding: 10px; height: 50px;">
                    <img style="width: 50px;height: 50px; " src="/themes/easyui/themes/icons/upuser.png">
                    <div style="position: relative; font-size: 14px;color: #909399;left: 60px;top: -40px;"><b>用户修改</b>
                    </div>
                    <!--   <div style="position: relative; font-size: 18px;color: #606266;left: 70px;top: -40px ;"  >  100   </div> -->
                </div>
            </div>
            <!--       <div style="margin-top: 40px;margin-left: 120px; margin-right: 20px    padding-left: 10px;padding-right: 10px ;   width: 15%;    float: left;">
                14
                 <div style=" padding: 15px 20px;background: #f2f6fc;font-weight: 700;    padding-left: 10px;padding-right: 10px" > 改水电费 </div>
                 <div style="padding: 20px;font-size: 18px"  >  100   </div>
                 </div> -->
        </div>
         
    </div>--%>
    <%--统计图模块--%>
    <div class="btn-canva">
        <div class="btn-canva-lr btn-canva-left">
            <h3>专利统计</h3>
            <div class="btn-canvaIn" style="">
                <iframe class="rightDIV1" src="/themes/skin/ipr/patents.jsp" frameborder="0" height="100%;" width="100%" marginheight="0" marginwidth="0" scrolling="no"></iframe>
            </div>
        </div>

        <div class="btn-canva-lr btn-canva-right">
            <h3>专利费用统计</h3>
            <div class="btn-canvaIn" style="">
                <iframe class="rightDIV1" src="/themes/skin/ipr/pie.jsp" frameborder="0" height="100%;" width="100%" marginheight="0" marginwidth="0" scrolling="no"></iframe>
            </div>
        </div>
    </div>

    <%--
    <div style="height: 60%; width:100%;background: #f2f6fc;display: flex;margin: 0 auto">
        <div class="btn-canvas" style="width:47%; height: 90%;">
            <div style="position: relative;  top:25px;margin-left: 8%; font-size: 14px;"><b>专利统计</b></div>
            <div id="left" style="height:80%; margin-top: 50px;margin-left: 60px; border: 1px solid #dcdfe6;width: 88%;border-radius: 10px;background: #ffffff">
                <iframe class="rightDIV1" src="/themes/skin/ipr/patents.jsp" frameborder="0" height="90%;" width="90%" marginheight="0" marginwidth="0" scrolling="no"></iframe>
            </div>
        </div>

        <div class="btn-canvas" style="margin-right:2%; width:46%; height: 90%;">
            <div style="position: relative; top:25px; margin-left: 7%;font-size: 14px;"><b>专利费用统计</b></div>
            <div id="rigth" style="height:80%; margin-top: 50px;margin-left: 8%; margin-right: 10px ;padding-left: 10px;padding-right: 1%;border: 1px solid #dcdfe6;width: 88%; border-radius: 10px;background:#ffffff">
                <iframe class="rightDIV1" src="/themes/skin/ipr/pie.jsp" frameborder="0" height="90%;" width="90%" marginheight="0" marginwidth="0" scrolling="no"></iframe>
            </div>
        </div>
    </div>--%>
    <%--  <div style="padding:0px;height: 65%; width:100%;background: #f2f6fc;">
          <div class="btn-canvas" style=" float:left;width:47%; height: 90%; background: #f2f6fc; ">
              <div style="position: relative;  top:25px;margin-left: 9%; font-size: 14px;"><b>专利统计</b></div>
              <div id="left"
                   style="height:80%; margin-top: 50px;margin-left: 60px; margin-right: 10px ;padding-left: 0px;padding-right: 10px ;border: 1px solid #dcdfe6;width: 88%;float: left; border-radius: 10px;background: #ffffff">
                  <iframe class="rightDIV1" src="/themes/skin/ipr/patents.jsp" frameborder="0" height="90%;" width="90%" marginheight="0" marginwidth="0" scrolling="no"></iframe>
              </div>
          </div>
          <div class="btn-canvas" style="float:left;margin-left:2%; width:46%; height: 90%; background: #f2f6fc;">
              <div style="position: relative; top:25px; margin-left: 7%;font-size: 14px;"><b>专利费用统计</b></div>
              <div id="rigth" style="height:80%; margin-top: 50px;margin-left: 6%; margin-right: 10px ;padding-left: 10px;padding-right: 1%;      border: 1px solid #dcdfe6;   width: 88%;    float: left; border-radius: 10px;background: #ffffff">
                  <iframe class="rightDIV1" src="/themes/skin/ipr/pie.jsp" frameborder="0" height="90%;" width="90%" marginheight="0" marginwidth="0" scrolling="no"></iframe>
              </div>
          </div>
      </div>--%>

</div>

<script type="text/javascript">
    function openListNoticeTab() {
        layout_center_addTabFun({
            id: 'listNoticeIndex',
            title: '通知公告',
            href: '${ctx}/common/notice/listNotice.do?tabId=listNoticeIndex',
            closable: true
        });
    }

   
    function patentList(url, title, id) {
        layout_center_addTabFun({
            id: id,
            title: title,
            closable: true,
            iconCls: "",
            //cache : false,
            href: url,
            queryParams: {},
            //content: '<iframe src="' + url + '" width="100%" height="100%" frameborder="0"></iframe>',
        });
    }

    //跳转到统计数字对应的页面
    function goStatistics(id, title, url) {
        title = title.trim();
        layout_center_addTabFun({
            id: id,
            title: title,
            href: url,
            closable: true,
            queryParams: {},
        });
    }
   
    function changeUserInfo() {
    	
    	
        openWindow('editUserInfoWindow', '个人信息修改', 900, 370, '${ctx }/common/user/goChangeUserInfo.do', true);
    }
</script>


