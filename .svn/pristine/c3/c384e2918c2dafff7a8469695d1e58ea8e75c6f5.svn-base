<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ipanthercommon" uri="http://ipanther-common.hcis.com" %>
<!-- content starts -->
<div>
	<ul class="breadcrumb">
		<li><a href="#">首页</a><span class="divider">/</span></li>
		<li><a href="#">数据字典管理</a><span class="divider">/</span></li>
		<li><a href="#">数据字典项管理</a><span class="divider">/</span></li>
		<li><a href="#">数据字典项修改</a></li>
	</ul>
</div>
<div class="row-fluid sortable">
	<div class="box span12">
		<div class="box-header well">
			<h2>数据字典项修改</h2>
			<div class="pull-right"> 
				<a href="#"  onclick="javascript:$('#content').load('${ctx}/common/datadict/listDictEntry.do?requestParam.dictTypeCode=${dictEntry.dictTypeCode}');"  class="btn btn-primary"> 
					<i class="icon-arrow-left  icon-white"></i>返回
				</a> 
			</div>
		</div>
		<div class="box-content">
			<form  class="form-horizontal" id="editDictEntryForm" name="editDictEntryForm" method="post" action="${ctx}/common/datadict/editDictEntry.do">
				<input type="hidden" name="requestParam.dictTypeCode" value="${dictEntry.dictTypeCode}"/>
				<input id="dictEntry.id" name="dictEntry.id"   type="hidden" value="${dictEntry.id}" />
					<fieldset>
						<div class="control-group">
							<label class="control-label">数据类型编码：</label>
							<div class="controls">
								<input   readonly="readonly" class="span6"  required  value="${dictEntry.dictTypeCode}"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">数据项名称：</label>
							<div class="controls">
								<input id="dictEntry.dictName" name="dictEntry.dictName" required class="span6" type="text" value="${dictEntry.dictName}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">数据项值：</label>
							<div class="controls">
								<input id="dictEntry.dictValue" name="dictEntry.dictValue" required class="span6" type="text" value="${dictEntry.dictValue}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">排序号：</label>
							<div class="controls">
								<input id="dictEntry.sortNo" name="dictEntry.sortNo" required class="span8" type="text" value="${dictEntry.sortNo}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">父数据项名称：</label>
							<div class="controls">
								<input id="dictEntry.parentName"  name="dictEntry.parentName" class="span6" value="${dictEntry.parentName}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">父数据项值：</label>
							<div class="controls">
								<input   id="dictEntry.parentValue"   name="dictEntry.parentValue"  class="span8" value="${dictEntry.parentValue}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">级别：</label>
							<div class="controls">
								<input id="dictEntry.rank" name="dictEntry.rank" class="span8"  type="text" value="${dictEntry.rank}" />
							</div>
						</div>
						<div class="form-actions">
							<button class="btn" type="reset" >重置</button>
							<button type="button" class="btn btn-primary"  onclick="javascript:jQuery.ajaxPostForm('editDictEntryForm','content','${ctx}/common/datadict/listDictEntry.do?requestParam.dictTypeCode=${dictEntry.dictTypeCode}')">确定</button>
						</div>
					</fieldset>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$("#editDictEntryForm").validate();
});
</script> 