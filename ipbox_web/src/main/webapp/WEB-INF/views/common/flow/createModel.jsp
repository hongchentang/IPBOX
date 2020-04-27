<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="activitiExplorerUrl" value="${ipanthercore:getProperty('activiti.explorer.url')}"/>
<form id="modelFrom" name="modelFrom" action="${ctx}/common/flow/createModelDo.do" method="post">
	 <table align="center" style="line-height: 40px;">
          <tr>
            <td style="text-align:right;">名称：</td>
            <td >
				<input type="text" class="easyui-textbox"  style="width: 300px" value="" data-options="required:true" id="name" name="name"/>
			</td>
          </tr>
          
          <tr>
            <td style="text-align:right;">代码：</td>
            <td >
            	<input type="text" class="easyui-textbox"  style="width: 300px" value="" data-options="required:true" id="key" name="key"/>
			</td>
          </tr>
          
           <tr>
            <td style="text-align:right;">描述：</td>
            <td >
				<textarea rows="2" cols="40" name="description"></textarea>
			</td>
          </tr>
          <tr style="text-align: center;line-height: 20px">
            <td colspan="2" >
            <button type="button" onclick="create();" style="float: center; margin-right: 20px" class="easyui-linkbutton" data-options="iconCls:'fa fa-floppy-o'">创建</button>
            </td>
          </tr>
        </table>
</form>

<script type="text/javascript">
$(document).ready(function(){
	
});

//保存序列
function create(formId){
	if(!$('#modelFrom').form('validate')) {
		return false;
	}
	$.messager.confirm('提示', '确定创建？', function(r){
		if(r) {
			jQuery('#modelFrom').form('submit',{
				 onSubmit: function(){
					 return $('#modelFrom').form('validate');
				},
			    success: function(data){
			    	var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							jQuery.messager.alert("提示信息",message,function(){});
							closeWindow('showAddDialog');
						} else if(parseInt(statusCode)==200){
							jQuery('#modelListTopTab').panel('refresh');
							var data = $.ajaxSubmitValue('${ctx }/common/flow/getActivitiExplorerURL.do?modelId='+message);
							var _json=jQuery.parseJSON(data);
							var json = jQuery.parseJSON(_json);
							var url = json.url;
							var encry = json.encry;
							openPostWindow(url,"编辑模型","encry",encry);
							closeWindow('showAddDialog');
						} else {
							jQuery.messager.alert("提示信息","系统出错！",function(){});
							closeWindow('showAddDialog');
						}
					}else{
						console.error("json is null");
					}
			    }
			}); 
		}
		
	});
}

//window.open 用post提交
function openPostWindow(url,windowName,argName,argValue) {
    var tempForm = document.createElement("form");  
    tempForm.id="tempForm";
    tempForm.method="post";
    tempForm.action=url;
    tempForm.target=windowName;
    
    var hideInput = document.createElement("input");
    hideInput.type="hidden";
    hideInput.name= argName;
    hideInput.value= argValue;
    tempForm.appendChild(hideInput);
    
    if (tempForm.addEventListener) {
    	tempForm.addEventListener("onsubmit", function(){ window.open('about:blank',windowName); }, false);
	}
	else if (tempForm.attachEvent) {
		tempForm.attachEvent("ononsubmit", function(){ window.open('about:blank',windowName); });
	}
    
    document.body.appendChild(tempForm);
    tempForm.submit();
    document.body.removeChild(tempForm);
}
</script>
