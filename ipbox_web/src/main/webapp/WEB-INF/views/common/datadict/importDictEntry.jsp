<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp" %>
  <form id="importDictEntryForm"  action="${ctx}/common/datadict/importDictEntry.do" method="post">
          	<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
				<tbody>
					<tr></tr>
					<tr>
						<td  width="25%">
				          	<label>文件路径</label>  
				        </td>
				        <td  width="75%">
				        	<input name="upload" class="easyui-filebox" 
							data-options="required:true,missingMessage:'请选择文件路径',buttonText:'选择文件'">
				        </td>
				     </tr>
				     <tr>
				        <td colspan="2">
				          	<label>温馨提示：为防止重复导入会覆盖修改过的信息，重复导入会提示成功，但是不会更新数据，修改教师信息请用修改功能。</label>  
				        </td>
		    		</tr>
		    		<tr></tr>
		     	</tbody>
		    </table>
      </form>
      <div id="importDictEntryResult">
      </div>
       <div style="text-align:center;padding:5px">
      	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitDictTypeForm()">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('importDictEntryWindow');">取消</a>
       </div>

<script type="text/javascript">
tableVBg('.alter-table-v');
 
function submitDictTypeForm(){
	jQuery.messager.confirm("提示信息","确定提交?",function(isReturn){
	   if(isReturn){
		 jQuery('#importDictEntryWindow').find('#importDictEntryForm').form('submit',{
			url:jQuery('#importDictEntryWindow').find('#importDictEntryForm').attr("action"),
			onSubmit: function(){
				 return true;
			},
		    success: function(data){    
				//var json=jQuery.parseJSON(data);
				if(data){
					jQuery("#importDictEntryResult").html(data);
					
// 					var message = json.message;
// 					var statusCode = json.statusCode;
// 					if(parseInt(statusCode)==300){
// 						jQuery.messager.alert("提示信息",message,function(){});
// 					}else if(parseInt(statusCode)==200){
// 						jQuery.messager.alert("提示信息",message,function(){});
// 						jQuery('#'+getCurrentTabId()).panel('refresh');
// 						closeWindow('importDictEntryWindow');
// 					}
				}else{
					console.error("json is null");
				}
			}
		}); 
	   }
	}); 
} 
</script>
<%--
  <script language="javascript">
// function importDictEntryCallback(response){
// 	$("#importResult").html(response).initUI();
// 	navTab.getCurrentPanel().find("[layoutH]").layoutH();
// }
</script> 
 <div class="pageHeader">
	<div class="searchBar">
		<p>导入数据字典</p>
	</div>
</div>
		<div class="pageContent">
        	<form id="importDictEntryForm"  method="post"  action="${pageContext.request.contextPath}/common/datadict/importDictEntry.do" class="pageForm required-validate" onsubmit="return iframeCallback(this,importDictEntryCallback);" enctype="multipart/form-data">
		<div class="pageFormContent">
			<p class="nowrap">
				<label class="control-label">文件路径:</label>
				<input type="file" name="upload" class="required" msg="请选择文件路径">
			</p>
			<p class="nowrap">
				<span class="info">温馨提示：为防止重复导入会覆盖修改过的信息，重复导入会提示成功，但是不会更新数据，修改教师信息请用修改功能。</span>
			</p>
			<div class="divider"></div>
			
		    <p>
		    <div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div> 
			<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
			</p>
		</div>
            </form>
        </div>
    <div id="importResult" layoutH="100">
	</div> --%>