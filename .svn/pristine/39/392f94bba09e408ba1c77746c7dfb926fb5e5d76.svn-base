<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>
<c:set value="0" var="size"/>
<c:set value="5" var="maxSize"/>
<div class="easyui-panel" id="patentFileAddPanel" data-options="region:'center',title:''">
             <form id="importPatsFrom"  action="${ctx}/warehouse/patenthouse/import.do" method="post" enctype="multipart/form-data">
            <table border="0" cellspacing="1" cellpadding="1"  class="alter-table-v">
				<tbody>
                    <tr>
						<td width="20%">
				          	导入模板
				        </td>
				        <td width="80%">
				        	<a style="text-decoration: underline;" href="${ctx}/template/专利数据上传模板.zip">
				        		点击下载导入模板
				        	</a>
				        </td>	
           			</tr>                    
                    <tr>
						 <td >
				          	<label>导入数据</label>  
				         </td>
				         <td> 
                            <select id ="fileType" name="fileType">
                            	<option value="1">专利基本信息</option>
                            	<option value="2">专利权力要求</option>
                            	<option value="3">专利历史信息</option>
                            	<option value="4">专利全文附图信息</option>
                            	<option value="5">专利法律状态</option>
                            	<option value="6">专利说明</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
						<td>
				          	选择文件
				        </td>
				        <td>
				        	<input type="file" name="uploadFile"  title="支持文件格式：json,txt" data-options="required:true" />
				        	<div style="color:red">提示：支持文件格式：json,txt</div>
				        </td>	
           			</tr>
		     	</tbody>
		    </table>
      </form>
       <div style="text-align:center;padding:5px;">
      	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormEdit()">保存</a>
       	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="returnList()">关闭</a>
       </div>
</div>
<script type="text/javascript">

 tableVBg('.alter-table-v');  

 function submitFormEdit(){
	jQuery.messager.confirm("提示信息","确定导入信息?",function(isReturn){
	   if(isReturn){
		   $("#patentInfoMsgWindow").find('#importPatsFrom').form('submit', { 
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
							closeWindow('patentInfoMsgWindow');
							easyuiUtils.ajaxClearFormForPanel(getCurrentTabId());
						}
					}else{
						console.error("json is null");
					}
				}else{
						console.error("data is null");
				}
			}
		}); 
	  }
	}); 
} 

 function returnList(){
	 closeWindow('patentInfoMsgWindow');
 }

</script>
