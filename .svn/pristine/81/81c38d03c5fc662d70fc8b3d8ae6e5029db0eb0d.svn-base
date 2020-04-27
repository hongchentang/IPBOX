<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ include file="/jsp/common/include/taglib.jsp"%>
 <script type="text/javascript" charset="UTF-8" src="${ctx}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="UTF-8" src="${ctx}/js/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="UTF-8" src="${ctx}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
 
<div class="easyui-panel" id="directiveAddPanel" data-options="region:'center',title:''">
          	<table border="0" cellspacing="1" cellpadding="1" class="alter-table-v"> 
				<tbody>
                    <tr>
						 <td >
				          	<label>标题</label>  
				        </td>
				         <td> 
                       		${cmsDirective.name}
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
                        </td>
                    </tr>
 					<tr>
                        <td>排序号</td>
                        <td>
                        	${cmsDirective.orderNum}
                        </td>
                    </tr>
		     	</tbody>
		    </table>
       <div style="text-align:center;padding:5px;">
       	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.closeWindow('addDirective')">取消</a>
       </div>
</div>
<div id="insertHtml" style="display: none;">
${cmsDirective.content}
</div>
<script type="text/javascript">

 tableVBg('.alter-table-v');
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用		UE.getEditor('editor')就能拿到相关的实例
	var editor=UE.getEditor('content',{readonly:true,toolbars:[],wordCount:false,elementPathEnabled:false,});
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
</script>  
 