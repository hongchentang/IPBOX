<%--
  User: zhw
  Date: 2019/8/22
  Time: 18:27
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="addPatentCost" style="padding:10px;" class="easyui-panel" data-options="region:'center'">
    <form id="addPatentCostForm" name="addPatentCostForm" method="post" action="${ctx}/intellectual/patent/cost/add.do" enctype="multipart/form-data">
     
       <div style="height:100px;padding-left:150px;padding-top:10px" class="easyui-panel" ><font size="3px">邮件发送成功！<br>
             您是否将邮箱完善到客户列表中？
       </font>
    </div>
       


        <div style="text-align: center;padding-left: 40px;padding-top: 10px; padding-bottom: 5px;">
            <button type="button" value="确认" id="=subBut" onclick="submitForm()" class="easyui-linkbutton" style="margin-right: 20px;">确认</button>
            <button type="reset"  value="关闭" id="subClose" class="easyui-linkbutton" onclick="doCloseCostWin()" >关闭</button>
            <%--<input type="button"  value="测试" id="xx" class="easyui-linkbutton" onclick="test()" />--%>
        </div>
    </form>
</div>

<script type="text/javascript">
function submitForm (){
	doCloseCostWin();
	easyuiUtils.openWindow('showAddDialog','新增客户', 900,400,'${ctx }/common/user/customeredit.do',true);

  
}
function doCloseCostWin(){
	 
    closeWindow("gocusomer");
}


    

</script>
