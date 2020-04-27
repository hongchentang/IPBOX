<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			用户授权管理<small></small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li>
				<i class="fa fa-home"></i>
				<a href="index.do">首页</a> 
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
			<a href="javascript:void(0);">用户授权管理</a> 
				<i class="fa fa-angle-right"></i>
			</li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-user"></i>用户
				</div>
				<%-- <div class="actions clearfix">
					<a class="btn  btn-warning" href="javascript:void(0);" onclick="javascript:jQuery('#content').load('${ctx}/common/user/goAdd.do');"  title="新增用户"><span><i class="fa fa-plus"></i>新增</span></a> 
				</div> --%>
			</div>
			<div class="portlet-body">
			<ul class="nav nav-tabs">
			</ul>
			<div class="tab-content" id="tab-content">
				<div id="tab_1_1" class="tab-pane active">
				<form id="searchForm" name="searchForm" class="form-inline"
					action="/common/user/roleConfigList.do" method="post">
					<input type="hidden" name="paramMap[dataPosition]" value="${paramMap.dataPosition}"/>
					<div class="form-group">
						&nbsp;&nbsp;
						<label>姓名</label> 
						<input name="paramMap[userName]"
							title="输入查询姓名" class="form-control input-small"
							value="${paramMap.userName}"/> 
						&nbsp;&nbsp;&nbsp;&nbsp;
						<label>单位</label>
						<input name="paramMap[deptName]" title="输入查询部门名称"
							class="form-control input-small" value="${paramMap.deptName}"/>
						&nbsp;&nbsp;
						<div class="btn-group btn-group-sm">
							<button class="btn btn-default" type="button"
								onclick="javascript:jQuery.search('searchForm','content')">
								<i class="fa fa-search"></i>查询
							</button>
						</div>
						&nbsp;&nbsp;
						<div class="btn-group btn-group-sm">
                        <input type="hidden" id="userIds" name="paramMap[userIds]" value="${paramMap.userIds}"/>
							<button class="btn blue" type="button"
								onclick="javascript:showBatchRole();">
								<i class="fa fa-unlock-alt"></i>批量角色配置
							</button>
						</div>
					</div>
                 <br/>
                 <br/>
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th style="width: 50px; text-align:center">勾选</th>
							<th>用户名</th>
							<th>姓名</th>
							<th>单位</th>
							<th>区域</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty listUser}">
							<c:forEach items="${listUser}" var="list">
								<tr>
									<td><input type="checkbox" value="${list.id }" onclick="check(this)"></td>
									<td>${list.userName}</td>
									<td>${list.realName}</td>
									<td>${list.deptName}</td>
									<td>
									   <c:if test="${(not empty list.province)||(not empty list.province)||(not empty list.province)}">
										${ipanthercommon:getRegionsName(list.province)}
										${ipanthercommon:getRegionsName(list.city)}
										${ipanthercommon:getRegionsName(list.counties)} 
									   </c:if>
									   <c:if test="${(empty list.province)&&(empty list.province)&&(empty list.province)}">
										${ipanthercommon:getRegionsName(ipanthercommon:readDepartment(list.deptId).province)}
										${ipanthercommon:getRegionsName(ipanthercommon:readDepartment(list.deptId).city)}
										${ipanthercommon:getRegionsName(ipanthercommon:readDepartment(list.deptId).counties)} 
									   </c:if>
									</td>
									<td class="center">
											<%-- <a class="btn btn-success" href="javascript:void(0);"
												onclick="javascript:dialogShow('${ctx}/common/user/view.do?userView.id=${list.id }');">
												<i class="fa fa-eye"></i>查看
											</a> --%>
											<c:if test="${list.dataPosition!='00'}">
												<%-- <a class="btn btn-info" href="javascript:void(0);"
													onclick="javascript:$('#content').load('${ctx}/common/user/goEdit.do?userView.id=${list.id }&user.id=${list.id }');">
													<i class="fa fa-pencil-square-o"></i>编辑
												</a> <a class="btn btn-danger" href="javascript:void(0);"
													onclick="javascript:jQuery.confirmPost('是否确认删除?','${ctx}/common/user/delete.do?userView.id=${list.id }','content','${ctx}/common/user/list.do');">
													<i class="fa fa-trash-o"></i>删除
												</a> --%>
												<a class="btn blue" onclick="javascript:showRole('/common/user/roleConfig.do?user.id=${list.id }');" href="#">
												<i class="fa fa-unlock-alt"></i>
												角色配置
												</a>
											</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty listUser}">
						  <tr class="center" style="text-align: center;">
						  	<td class="center" colspan="6">没有查询到数据</td>
						  </tr>
						</c:if>
					</tbody>
				</table>
				<jsp:include page="${ctx}/jsp/common/include/manage_page.jsp">
					<jsp:param value="searchForm" name="pageForm" />
					<jsp:param value="ajax" name="type" />
					<jsp:param value="content" name="divId" />
				</jsp:include>
					</form>
			</div>
		</div>
	</div>
  </div>
 </div>
</div>
<div>
<form id="showBatchRoleForm" action="/common/user/batchRoleConfig.do">
<input type="hidden" id="hiddenId" name="paramMap[hiddenId]" value="${paramMap.hiddenId}"/>
</form>
</div>
<div id="myModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">角色配置</h4>
      </div>
       <div class="modal-body" id="viewOpen">
        
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
function check(obj){
	if(obj.checked){
		addCheck(obj);
	}else{
		removeCheck(obj);
	}
}
function removeCheck(obj){
	var userIds=$("#userIds").val();
	var id=("#"+obj.value.toString()+"#").toString();
	var reg=new RegExp(id,"g");
	userIds=userIds.replace(id,"");
	$("#userIds").val(userIds);
}	
function addCheck(obj){
	var userIds=$("#userIds").val();
	var id=("#"+obj.value.toString()+"#").toString();
	var reg=new RegExp(id,"g");
	if(userIds.indexOf(id)==-1){
		userIds+=id;
	}
	$("#userIds").val(userIds);
}


$(document).ready(function(){
	var userIds=$("#userIds").val();
	 var checks= $("input:checkbox",'#searchForm');
	 for(var i=0;i<checks.length;i++){
		 var flg=userIds.indexOf(checks[i].value);
		 if(flg!=-1){
			 checks[i].checked=true;
		 }
	 }
})


/*  function dialogShow(postUrl){
	 var data= jQuery.ajaxSubmitValue(postUrl);
	 bootbox.dialog({ message: data,
       title: "查看",
       buttons: {
	           success: {
	             label: "关闭",
	             className: "blue"
	           }
         }
	 
		 });
}  */
function showRole(postUrl){
	 var data= jQuery.ajaxSubmitValue(postUrl);
	  $("#viewOpen").html(data);
	  $('#myModal').modal() 
}
function showBatchRole(){
	var ids=$("#userIds").val();
	if(ids==''){
		bootbox.alert('请勾选需要添加角色的用户！');
		return false;
	}
	$("#hiddenId").val(ids);
	var data=$.ajaxSubmit("showBatchRoleForm");	
 	$("#viewOpen").html(data);
	$('#myModal').modal() 
}
</script>