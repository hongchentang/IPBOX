<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
<div class="container">
	<div class="col-md-11">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					 人员管理<small></small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="index.do">首页</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<a href="#" onclick="javascript:jQuery('#content').load('${ctx}/common/user/list.do');">人员管理</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							角色配置
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
<div class="col-md-11">
 <div class="portlet box blue">
    <div class="portlet-title">
        <div class="caption"><i class="fa fa-user"></i>
 				当前配置用户:${user.realName }
		</div>
		<div class="tools">
			<div class="btn-group btn-group-xs btn-group-solid">
				<a href="#" onclick="javascript:jQuery('#content').load('${ctx}/common/user/list.do');" class="btn blue"><i class="fa fa-mail-reply"></i>返回</a><br>
			</div>
		</div>
    </div>
    <div class="portlet-body">
    	<form id="editFrom" name="editFrom" action="/common/security/role/userRoleSave.do" method="post">
    	<div class="table-responsive">
    		<table class="table table-bordered table-striped table-condensed flip-content">
    			<thead>
						<tr>
							<th>--</th>
							<th>角色名</th>
							<th>描述</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${not empty roles}">
					<c:forEach items="${roles }" var="list">
								<tr class="success">
									<td class="center">
										<input type="checkbox" id="roleId" name="roleId" value="${list.id }" title="${list.id }" onclick="javascript:check(this);">
									</td>
									<td>${list.name }</td>
									<td>${list.description }</td>
								</tr>
					</c:forEach>
					</c:if>
					</tbody>
    		</table>
    	</div>
	    <div class="form-actions fluid">
	    							<input type="hidden" id="roleIds" name="roleIds" value="${roleIds}">
	    							<input type="hidden" id="userId" name="userId" value="${user.id }">
									<div class="col-md-offset-4 col-md-8">
										<button class="btn green" type="button" onclick="javascript:$.ajaxPostForm('editFrom','content','${ctx}/common/user/list.do');"><i class="fa fa-save"></i>保存</button>
										<button class="btn default" type="reset"><i class="fa fa-rotate-right"></i>重置</button>
									</div>
		</div>
		</form>
    </div>
  </div>  
</div>
	<!--/span--> 
</div>
</div>
<hr>

<script type="text/javascript">

var roleIds='';
	
function check(obj){
	if(obj.checked){
		addCheck(obj);
	}else{
		removeCheck(obj);
	}
}
function removeCheck(obj){
	var id=("|"+obj.value.toString()+"|").toString();
	var reg=new RegExp(id,"g");
	roleIds=roleIds.replace(id,"");
	$("#roleIds").val(roleIds);
}	
function addCheck(obj){
	var id=("|"+obj.value.toString()+"|").toString();
	var reg=new RegExp(id,"g");
	if(roleIds.indexOf(id)==-1){
		roleIds+=id;
	}
	$("#roleIds").val(roleIds);
}
 
$(document).ready(function(){
	 roleIds=$("#roleIds").val();
	 var checks= $("input:checkbox");
	 for(var i=0;i<checks.length;i++){
		 var flg=roleIds.indexOf(checks[i].value);
		 if(flg!=-1){
			 checks[i].checked=true;
		 }
	 }
 })
</script>