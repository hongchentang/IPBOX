
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/js/common/easyui-common.js"></script>
<style>
    *{margin: 0;padding: 0;}
    .emailviewIn-l-top, .emailviewIn-l-liTop,.emailviewIn-l-li2{padding-left:10px;}
    .emailviewIn{display: flex;}
    .emailviewIn-l{width: 72%;height: 100%;display: flex;flex-direction: column;}
    .emailviewIn-l-top{height: 65px;border-bottom: #ccc solid 2px;line-height: 65px;}
    .emailviewIn-l-bottom{height: 260px;display: flex;flex-direction: column;overflow: hidden;}
    .emailviewIn-l-liTop{height: 45px;border-bottom: #ccc dashed 1px;line-height: 45px;}
    .emailviewIn-l-li2{width: 100%;height: 30px;border-bottom: #ccc dashed 1px;line-height: 30px;vertical-align:middle}
    .emailviewIn-r{width: 28%;border-left: #ccc solid 2px;overflow: hidden}
    .emailview-button{display: flex;justify-content: center;margin: 0 10px;width: auto;height: 25px;margin-top: 8px}
    .emailviewIn-checkbox{display: inline-block;vertical-align: middle;margin-right: 5px}
</style>
<div id="emailview" style="" class="easyui-panel" data-options="region:'center'" >
    <div class="emailviewIn">
        <div class="emailviewIn-l" style="">
            <div class="emailviewIn-l-top" style="">
                <span style="font-weight: bold;font-size: 14px">收件人邮箱地址:</span>
              
                <input  id="email"  name ="email"  value='' style="margin-left: 10px;width: 250px;height: 25px" />
            
            </div>
            <ul class="emailviewIn-l-bottom" style="">
              <form action="${ctx}/intellectual/patent/cost/gosendEmailpage" method="post">
                <li class="emailviewIn-l-liTop" style="font-weight: bold;font-size: 14px">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp主题:&nbsp&nbsp&nbsp<input style="height:20px ;font-size: 14px;"  id="theme" type="text" ><!-- <button style="margin-left:10px;width:60px;color: #999" onclick="gosedEmailpage()" >搜索</button> --></li>
               </form>
        
             
             <li class="emailviewIn-l-liTop" style="font-weight: bold;font-size: 14px">    正文：</li><br><label>  &nbsp&nbsp&nbsp&nbsp&nbsp <textarea id="text" style="resize:none;overflow-y:hidden;"  cols="60" rows="10">
                    
                    
                    </textarea></label>
              
 
            </ul>
        </div>
 
    



        <ul class="emailviewIn-r" style="">
          <li style="font-weight: bold;font-size: 14px;padding-left: 10px;width: 100%;height: 35px;line-height: 35px;border-bottom: #ccc solid 2px">客户通讯录:</li>
              <li style="font-weight: bold;font-size: 14px;padding-left: 10px;width: 100%;height: 35px;line-height: 35px;border-bottom: #ccc solid 2px"> <input style=" height:20px; width:150px"id='userName' name='userName' onclick="" type="text" title="客户名称" 
                    data-options="
                    prompt:'输入客户名称进行搜索',
                    loader: loadUserEmails,
                    mode: 'remote',
                    valueField: 'id',
                    textField: 'name',
                    labelPosition: 'top'
                
                    " value='' />
           
                     </li>
            <li style="font-weight: bold;font-size: 14px;padding-left: 10px;width: 100%;height: 35px;line-height: 35px;border-bottom: #ccc solid 2px">最近联系人:</li>
            <c:forEach items="${contacts}" var="contacts">
            <li class="emailviewIn-l-li2">
                <label><input id="d${contacts.id}" name='radio${contacts.id}' class="emailviewIn-checkbox" type="checkbox" value='${contacts.userEmail }'><span>${contacts.userName }</span></label>
            </li>
            </c:forEach>
  <li style="font-weight: bold;font-size: 14px;padding-left: 10px;width: 100%;height: 35px;line-height: 35px;border-bottom: #ccc solid 2px"><button id="btn" style="margin-left:10px;width:60px;color: #999"  >添加邮箱</button></li>
        </ul>
    </div>
</div>
<div class="emailview-button" style="text-align: center;">
    <input style="margin-right:10px;width: 60px" type="button" value="发送" id="=subBut" onclick="sendEmail()" />
    <input style="margin-left:10px;width: 60px" type="reset"  value="取消" id="subClose" onclick="doClosePayable()" />
</div>

<script type="text/javascript">
  

    $(document).ready(function() {

    	
 	   $("#btn").click(function () {
 	        var re = [];
 	        
 	        $("input[type='checkbox']:checked").each(function() {re.push($(this).val());
 	
 	      })
 	          re.splice(0,1);
 	     var  inemail=$("#email").val();
 	  var aaa="";
 	     for(var i=0;i<re.length;i++){
 	    	 var  aaa=aaa+re[i]+"、";
 	    	
 	     }
 		       $("#email").val(aaa);
 	    });
    	

        $.ajax({
            url: '${ctx}/intellectual/patent/cost/getcusData.do',
            dataType: 'json',
            success: function(data){
            	var datas=data.data;

                },
              
            
            error: function(){
            	alert("erro");
            }
        });

    });
  

function aaa(){

}
    

 

    /*提交表单*/
    function submitForm(message) {

        //进行id赋值
        var id = "${cost.id}";
        var type = getType();
        console.log("type = " + type);
        $("#id").val(id);
        $("#costType").val(type);
        $("#addPatentCostWindow,#editPatentCostWindow").find('#addPatentCostForm').form('submit', {
            success: function (data) {
                data = JSON.parse(data);
                if (data.code == 200) {
                    /*刷新列表*/
                    refreshPatentCost();
                    /*关闭窗口*/
                    doCloseCostWin("");
                } else {
                    $.messager.alert("错误","保存失败" + data.msg);
                }
            }
        });
    }
    

    function loadUserEmails(param,success,error) {
        var q = param.q;
        if(q === undefined){
            q = '';
        }
      
        $.ajax({
            url: '${ctx}/intellectual/patent/selectUserEmail.do?userName=' + q,
            dataType: 'json',
            success: function(data){
                data = data.data;
                var items = $.map(data, function(item){
         
                  return {
                    	
                  id:item.id,
                 
                  email:item.userEmail,
                  name:item.userName
              
                    };
                });
              
                success(items);
            },
            error: function(){
            }
        });
    }
    
    $("input[type='radio']").click(function(checkbox){
    
    	if( $("input[type='radio']").length>0){
    	
    		var va=$('input[name="radio"]:checked').val();
    		
    	$("#email").val(va);
    
    	
    		
    	
    	
    		
    	}else{
    		
    	}
    })

    function sendEmail() {
   
    	 var re=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    		var id="${id}";
 			var email=$("#email").val();
 			var text=$("#text").val();
 			var theme=$("#theme").val();
 
            if(email==""){
            	 $.messager.alert('提示', "邮件不能为空！");
            	 return false;
            }
    	 
        $.ajax({
            url:'${ctx}/intellectual/patent/cost/sendEmail.do?id='+id+'&email='+email+'&text='+text+'&theme='+theme,
            type:'GET',
            dataType:'Json',
            success:function(data){
            	
            	
                if(data.code == 200){
                doCloseCostWin();
                var data=data.data;
                var len=data.length;
        
                if(len==0){
                	 openWindow('gocusomer','', 700,400,'${ctx}/intellectual/patent/cost/gocusomer.do?id=' + id + '',true);              
                	 }else {
                		 $.messager.alert('成功', "邮件发送成功");

                }
                }else {
                    $.messager.alert('错误', data.msg);
                }
            }
        });
    }
    
    function gosedEmailpage() {
   
       var  vue=$("#name").val();
			var id="${id}";
		
        openWindow("find",'选择邮箱', 700,500,'${ctx}/intellectual/patent/cost/gosendEmailpage.do?id='+id+'&name='+ vue + '',true);
    	closeWindow("patentCostPayableWindows");
    	
    	
    
    }
    function doCloseCostWin(){
 
        closeWindow("patentCostPayableWindows");
    }

    
    $("#userName").combobox({
        onChange: function (newValue, oldValue) {
         
            var list = $("#userName").combobox('getData');
            var item = getSelectData(list, newValue);
            if (item != undefined) {
            	
                setPatentVal(item.email);
            }
    
  
        }
    });
    
    function getSelectData(list, id) {
        for (var i = 0; i < list.length; i++) {
            var item = list[i];
            var selectValId = item.id;
            if(selectValId == id){
                return item;
            }
        }
    }
    function setPatentVal(email) {
    	email=email+"、";
    	var vue=$("#email").val();
        var emails=vue+email;
       
        $("#email").val(emails);

    }

    $(".checkNumber").textbox({
        onChange:function (nVal, oVal) {
        	
            console.log("输入了" + nVal + "|" + oVal);
            if(isNaN(nVal)){
                    if(isNaN(oVal)){
                    $(this).textbox('setText', '');
                }else {
                    $(this).textbox('setText', oVal);
                }
            }
        }
    });
</script>
