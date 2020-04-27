<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<h3>当前站点: ${cmsSite.name}</h3>
<div class="easyui-panel" title="首页静态化" id="indexHtmlList">
	<form action="${ctx}/cms/html/htmlIndex.do" id="indexForm" method="post">
		<input type="hidden" value="${cmsSite.id}" name="siteId"/>
		<div style="text-align:center;padding:5px;">
			<button type="button" onclick="htmlIndex()" class="easyui-linkbutton" >生成首页</button>
		</div>
	</form>
</div>
<script type="text/javascript">
function htmlIndex(){
	$.messager.confirm('提示',"确认静态化当前站点首页?",function(result){    
		if (result){ 
			 getCurrentTab().find('#indexForm').form('submit', {   
				    success: function(data){    
						var json=jQuery.parseJSON(data);
						if(json){
							var message = json.message;
							var statusCode = json.statusCode;
							if(parseInt(statusCode)==300){
								jQuery.messager.alert("提示信息",message,function(){});
							}else if(parseInt(statusCode)==200){
								jQuery.messager.alert("提示信息",message,function(){});
								//easyuiUtils.ajaxClearFormForPanel(getCurrentTabId());
								//closeWindow('siteWindow');
								getCurrentTab().find("#htmlIndex").panel('refresh');
							}
						}else{
							console.error("json is null");
						}
					}    
				}); 
		}else{
			 jQuery.messager.alert("提示信息","静态化首页失败",function(){});
		}
	});
}
</script>