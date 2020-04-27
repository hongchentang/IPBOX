<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp" %>
  <form id="addDictEntryForm"  action="${ctx}/common/datadict/${StringUtils:isNotEmpty(dictEntry.dictTypeCode)?'editDictEntry':'addDictEntry'}.do" method="post">
          	<input type="hidden" name="paramMap[dictTypeCode]" value="${StringUtils:isNotEmpty(dictEntry.dictTypeCode)?dictEntry.dictTypeCode:searchParam.paramMap.dictTypeCode}"/>
	    	<input name="dictEntry.dictTypeCode" id="dictEntry.dictTypeCode" type="hidden"   value="${StringUtils:isNotEmpty(dictEntry.dictTypeCode)?dictEntry.dictTypeCode:searchParam.paramMap.dictTypeCode}"/>
          	<input name="dictEntry.id" type="hidden"   value="${dictEntry.id}"/>
          	<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
				<tbody>
					<tr>
						<td>
				          	<label>数据类型编码</label>  
				        </td>
				        <td>
				        	 <input class="easyui-validatebox" 
								disabled="disabled" style="width: 250px" type="text" value="${StringUtils:isNotEmpty(dictEntry.dictTypeCode)?dictEntry.dictTypeCode:searchParam.paramMap.dictTypeCode}"/>
				        </td>
			    	</tr>
			    	<tr>
				        <td>
				          	<label>数据项名称</label>  
				        </td>
				        <td>
				        	 <input name="dictEntry.dictName" class="easyui-validatebox" 
							data-options="required:true" style="width: 250px"  type="text" value="${dictEntry.dictName}"/>
				        </td>	
		    		</tr>
		    		<tr>
						<td>
				          	<label>数据项值</label>  
				        </td>
				        <td>
				        	 <input class="easyui-validatebox"  data-options="required:true" name="dictEntry.dictValue"
				        	 style="width: 250px" type="text" value="${dictEntry.dictValue}"/>
				        </td>
				    </tr>
			    	<tr>
				        <td>
				          	<label>排序号</label>  
				        </td>
				        <td>
				        	 <input name="dictEntry.sortNo" class="easyui-numberbox" 
				        	 data-options="required:true,min:0,precision:0,missingMessage:'只能输入整数'"
				        	 style="width: 255px"  type="text" value="${dictEntry.sortNo}"/>
				        </td>	
		    		</tr>
		    		<tr>
						<td>
				          	<label>父数据项名称</label>  
				        </td>
				        <td>
				        	 <input class="easyui-validatebox" name="dictEntry.parentName"
							 style="width: 250px" type="text" value="${dictEntry.parentName}"/>
				        </td>
				    </tr>
			    	<tr>
				        <td>
				          	<label>父数据项值</label>  
				        </td>
				        <td>
				        	 <input name="dictEntry.parentValue" class="easyui-validatebox" 
							 style="width: 250px"  type="text" value="${dictEntry.parentValue}"/>
				        </td>	
		    		</tr>
		    		<tr>
			    		<td>级别</td>
			    		<td>
				        	<input name="dictEntry.rank" class="easyui-validatebox" 
							 style="width: 250px"  type="text" value="${dictEntry.rank}"/>
					    </td>	
		    		</tr>
		     	</tbody>
		    </table>
      </form>
       <div style="text-align:center;padding:5px">
      	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitDictTypeForm()">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('editDictEntryWindow');">取消</a>
       </div>
<script type="text/javascript">
var isXK=false;
tableVBg('.alter-table-v');
 
function submitDictTypeForm(){
	jQuery.messager.confirm("提示信息","确定${StringUtils:isEmpty(dictEntry.id)?'添加':'编辑'}数据字典项?",function(isReturn){
	   if(isReturn){
		 jQuery('#editDictEntryWindow').find('#addDictEntryForm').form('submit',{
			url:jQuery('#editDictEntryWindow').find('#addDictEntryForm').attr("action"),
			onSubmit: function(){
				 return true;
			},
		    success: function(data){    
				var json=jQuery.parseJSON(data);
				if(json){
					var message = json.message;
					var statusCode = json.statusCode;
					if(parseInt(statusCode)==300){
						jQuery.messager.alert("提示信息",message,function(){});
					}else if(parseInt(statusCode)==200){
						jQuery.messager.alert("提示信息",message,function(){});
						jQuery('#listDictEntryWindow').panel('refresh');
						closeWindow('editDictEntryWindow');
					}
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
<style>
form.cmxform label.error,label.error {
	color: red;
	font-style: italic
}
</style>
 <!-- content starts -->
				<div>
					<ul class="breadcrumb">
					    <li><a href="#">首页</a><span class="divider">/</span></li>
						<li><a href="#">数据字典管理</a><span class="divider">/</span></li>
						<li><a href="#">数据字典项管理</a><span class="divider">/</span></li>
						<li><a href="#">数据字典项添加</a></li>
					</ul>
				</div>
<div class="row-fluid sortable">
					<div class="box span12">
					<div class="box-header well">
                    <h2>数据字典项添加</h2>
						<div class="pull-right">
								<a href="#"  onclick="javascript:$('#content').load('${ctx}/common/datadict/listDictEntry.do?requestParam.dictTypeCode=${requestParam.dictTypeCode[0]}');"  class="btn btn-primary"> <i class="icon-arrow-left  icon-white"></i>返回</a> 
						</div>
					</div>
						<div class="box-content">
<form  class="form-horizontal" id="addDictEntryForm" name="addDictEntryForm" method="post" action="${ctx}/common/datadict/addDictEntry.do">
        <input type="hidden" name="requestParam.dictTypeCode" value="${requestParam.dictTypeCode[0]}"/>
	    <input name="dictEntry.dictTypeCode" id="dictEntry.dictTypeCode" type="hidden"   value="${requestParam.dictTypeCode[0]}"/>
	<div class="modal-body">
	 <fieldset>
	                     <div class="control-group">
	 		                <label class="control-label">数据类型编码：</label>
							  	<div class="controls">
						   <input   readonly="readonly" class="span6"  required  value="${requestParam.dictTypeCode[0]}"/>
								</div>
						</div>
	                   <div class="control-group">
	 		                   <label class="control-label">数据项名称：</label>
							  	<div class="controls">
								 <input id="dictEntry.dictName" name="dictEntry.dictName" required class="span6" type="text" value="" />
								</div>
						</div>
	                   <div class="control-group">
	 		                   <label class="control-label">数据项值：</label>
							  	<div class="controls">
								 <input id="dictEntry.dictValue" name="dictEntry.dictValue" required class="span6" type="text" value="" />
								</div>
						</div>
						<div class="control-group">
	 		          	 <label class="control-label">排序号：</label>
							  	<div class="controls">
								 <input id="dictEntry.sortNo" name="dictEntry.sortNo" required class="span8" type="text" value="" />
								</div>
						</div>
			 
           	            <div class="control-group">
	 		          	            <label class="control-label">父数据项名称：</label>
							  	<div class="controls">
							        <input id="dictEntry.parentName"  name="dictEntry.parentName" class="span6" value="">
								</div>
						</div>
							<div class="control-group">
	 		                      <label class="control-label">父数据项值：</label>
							  	<div class="controls">
						          <input   id="dictEntry.parentValue"   name="dictEntry.parentValue"  class="span8" value="">
								</div>
						</div>
							<div class="control-group">
	 		          	  	        <label class="control-label">级别：</label>
							  	<div class="controls">
	       				             <input id="dictEntry.rank" name="dictEntry.rank" class="span8"  type="text" value="" />
								</div>
						</div>
	<div class="form-actions">
	    <button class="btn" type="reset" >重置</button>
		<button type="button" class="btn btn-primary" onclick="javascript:jQuery.ajaxPostForm('addDictEntryForm','content','${ctx}/common/datadict/listDictEntry.do?requestParam.dictTypeCode=${requestParam.dictTypeCode[0]}')">确定</button>
	</div>
		 </fieldset>
	</div>
</form>
</div></div></div>
<script type="text/javascript">
$(document).ready(function() {
	$("#addDictEntryForm").validate();
});
 
</script>	  --%>