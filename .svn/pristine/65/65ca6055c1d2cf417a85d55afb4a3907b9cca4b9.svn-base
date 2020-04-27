<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ include file="/jsp/common/include/taglib.jsp"%>
<script type="text/javascript" charset="UTF-8" src="${ctx}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="UTF-8" src="${ctx}/js/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="UTF-8" src="${ctx}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
 
<div class="easyui-panel" id="directiveAddPanel" data-options="region:'center',title:''">
       <form id="directiveAddFrom"  action="${ctx}/cms/directive/${StringUtils:isNotEmpty(cmsDirective.id)?'editDirective':'saveDirective'}.do " method="post">
            <input type="hidden" value="${cmsDirective.id}" name="cmsDirective.id"/>
          	<table border="0" cellspacing="1" cellpadding="1"  class="alter-table-v">
				<tbody>
                    <tr>
						 <td >
				          	<label>标题</label>  
				        </td>
				         <td> 
                       		<input name="cmsDirective.name"  type="text" class="easyui-textbox" data-options="required:true" value="${cmsDirective.name}"/>
                        </td>
                    </tr>
 
                    <tr>
						 <td >
				          	<label>信息内容</label>  
				        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                        <script id="content" name="content" style="width:800px;height:450px;"></script>
                        <!--<textarea id="postContent.postContent" class="edit_input"></textarea>-->
                        <input type="hidden" id="ueditorContent" name="ueditorContent" value=""/>
                        </td>
                    </tr>
 					<tr>
                        <td>排序号</td>
                        <td>
                        <input type="text"  class="easyui-numberbox" data-options="min:0,precision:0" 
                        	   name="cmsDirective.orderNum" value="${cmsDirective.orderNum}"/>
                        </td>
                    </tr>
		     	</tbody>
		    </table>
		    <textarea rows="" cols="" id="cmsDirective_content" name="cmsDirective.content" style="display: none;">${cmsDirective.content}</textarea>
      </form>
       <div style="text-align:center;padding:5px;">
      	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormEdit()">保存</a>
       	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.closeWindow('addDirective')">取消</a>
       </div>
</div>
<div id="insertHtml" style="display: none;">
${cmsDirective.content}
</div>
<script type="text/javascript">

 tableVBg('.alter-table-v');
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用		UE.getEditor('editor')就能拿到相关的实例
	var editor=UE.getEditor('content');
	editor.addListener('contentchange',function(){
		this.sync();
	});
	  editor.ready( function( editor ) {
    var insertHtml=jQuery('#addDirective').find("#insertHtml").html();
    if(insertHtml!=""){
    	UE.getEditor('content').setContent(insertHtml,false);
    }else{ 
		console.log("insertHtml is null");
	} 
    });

 function submitFormEdit(){
	jQuery.messager.confirm("提示信息","确定${not empty cmsDirective.id?'编辑':'添加'}标签?",function(isReturn){
	   if(isReturn){
		   jQuery('#addDirective').find('#directiveAddFrom').form('submit', { 
		    success: function(data){
				if(data){    
					var json=jQuery.parseJSON(data);
					if(json){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							jQuery.messager.alert("提示信息",message,function(){});
						}else if(parseInt(statusCode)==200){
							jQuery.messager.alert("提示信息",message,function(){});
							easyuiUtils.ajaxClearFormForPanel(getCurrentTabId());
							easyuiUtils.closeWindow('addDirective');
						}
					}else{
						console.error("json is null");
					}
				}else{
						console.error("data is null");
				}
			},	 
			onSubmit:function(){  
				 if($(this).form('validate')){
					var editor=UE.getEditor('content');
						editor.sync();       //同步内容
						jQuery('#addDirective').find('#directiveAddFrom').find("#cmsDirective_content").html(UE.getEditor('content').getContent());
					}
				return $(this).form('validate');  
           }, 
			
				/*	*/
		}); 
	  }
	}); 
} 
 
 
 
 
 
</script>
 