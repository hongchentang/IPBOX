<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>

  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
      <h4 class="modal-title">选择机构</h4>
    </div>
    <div class="modal-body">
        	<form id="selectForm" name="selectForm" class="form-inline"	action="${ctx}/common/dept/selectDept.do" method="post">
					<div class="form-group">
						&nbsp;&nbsp;
						<label>机构名称</label>
						<input name="paramMap[deptName]" title="输入查询部门名称"
							class="form-control input-small" value="${paramMap.deptName}"/>
						&nbsp;&nbsp;
						<div class="btn-group btn-group-sm">
							<button class="btn btn-default" type="button"
								onclick="javascript:jQuery.search('selectForm','deptDialogContent')">
								<i class="fa fa-search"></i>查询
							</button>
						</div>
					</div>
                 <br/>
                 <br/>
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>机构名称</th>
							<th>区域</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty deptList}">
							<c:forEach items="${deptList}" var="list">
								<tr id="${list.id}">
									<td>${list.deptName}</td>
									<td>
										${ipanthercommon:getRegionsName(list.province)}
										${ipanthercommon:getRegionsName(list.city)}
										${ipanthercommon:getRegionsName(list.counties)} 
									</td>
									<td class="center">
										<a href="javascript:void(0);" class="btn btn-default" onclick="chooes('${list.id}','${list.deptName}')">选择</a>	 
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty deptList}">
						  <tr class="center" style="text-align: center;">
						  	<td class="center" colspan="6">没有查询到数据</td>
						  </tr>
						</c:if>
					</tbody>
				</table>
				<jsp:include page="${ctx}/jsp/common/include/manage_page_select.jsp">
					<jsp:param value="selectForm" name="pageForm" />
					<jsp:param value="ajax" name="type" />
					<jsp:param value="deptDialogContent" name="divId" />
				</jsp:include>
					</form>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn default" data-dismiss="modal">关闭</button>
    </div>
  </div>
  <!-- /.modal-content --> 
<!-- /.modal-dialog --> 
<script type="text/javascript">
 function chooes(deptId,deptName){
	if(!jQuery.isEmptyObject(deptId)){
       	jQuery("#deptDiv").html("<span>"
       		  	+"<input type='hidden' id='deptId' name='userView.deptId' value='"+deptId+"'/>"
       		 	+"<input type='hidden' id='deptName' name='userView.deptName' value='"+deptName+"'/>"
       		    +"<label class='control-label'>"+deptName+"</label></span>");
	}
	$("#deptDialogContent").modal("hide");
	
}
</script>