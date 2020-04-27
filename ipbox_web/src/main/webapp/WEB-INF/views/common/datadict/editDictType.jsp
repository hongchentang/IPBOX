<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ipanthercommon" uri="http://ipanther-common.hcis.com" %>
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
						<li><a href="#">数据字典修改</a></li>
					</ul>
				</div>
<div class="row-fluid sortable">
					<div class="box span12">
					<div class="box-header well">
                    <h2>数据字典修改</h2>
						<div class="pull-right">
								<a href="#"  onclick="javascript:$('#content').load('${ctx}/common/datadict/listDictType.do');"  class="btn btn-primary"> <i class="icon-arrow-left  icon-white"></i>返回</a> 
						</div>
					</div>
						<div class="box-content">
<form  class="form-horizontal" id="editDictTypeForm" name="editDictTypeForm" method="post" action="${ctx}/common/datadict/editDictType.do">
	<div class="modal-body">
	 <fieldset>
	                   <div class="control-group">
	 		              	       <label class="control-label">数据类型编码：</label>
							  	<div class="controls">
						           <input id="dictType.dictTypeCode" name="dictType.dictTypeCode"  required class="span6" type="text" value="${dictType.dictTypeCode}" />
								</div>
						</div>
						   <div class="control-group">
	 		              	        <label class="control-label">数据类型名称：</label>
							  	<div class="controls">
							  	    <input id="dictType.dictName" name="dictType.dictTypeName" required class="span6" type="text" value="${dictType.dictTypeName}" />
								</div>
						</div>
		<!-- 
			   <div class="formSep">
				<div class="row-fluid">
					<div class="span6">
				         <label class="control-label">级别：</label>
	                     <input id="dictType.rank" name="dictType.rank" class="number" type="text" value="${dictType.rank}" />
					</div>
					<div class="span6">
						 <label class="control-label">父类型编码：</label>
					     <select name="dictEntry.parentCode" class="combox">
						      <option value="">请选择</option>
					     </select>
             		</div>
				</div>
		</div>	
	        -->
	<div class="form-actions">
	    <button class="btn" type="reset" >重置</button>
		<button type="button" class="btn btn-primary" id="register" onclick="javascript:jQuery.ajaxPostForm('editDictTypeForm','content','${ctx}/common/datadict/listDictType.do')">确定</button>
	</div>
		 </fieldset>
	</div>
</form>
</div></div></div>
<script type="text/javascript">
$(document).ready(function() {
	$("#editDictTypeForm").validate();
	});
</script>
