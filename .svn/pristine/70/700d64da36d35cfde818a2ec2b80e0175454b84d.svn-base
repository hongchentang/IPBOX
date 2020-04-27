<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="tabId" value="viewUserRewardTopTab"/>
<div class="notice nc-mod">
	<div class="tit clearfix">
		<h3>奖励信息列表</h3>
	</div>
	<div class="con">
		<div id="${tabId}">
			<form id="userReward_list_form" action="${ctx }/common/user/reward/list.do?paramMap[userId]=${searchParam.paramMap.userId}&tabId=${tabId}&action=${param.action}" method="post">
			<div id="userReward_search">
				<div style="margin: 10px">
					
				</div>
				<div style="margin: 10px">
					<button type="button" class="easyui-linkbutton" onclick="viewUserReward()"><i class="fa fa-search"></i> 查看</button>
					<c:if test="${param.action eq 'edit'}">
					  	<button type="button" class="easyui-linkbutton" onclick="addUserReward()"><i class="fa fa-plus"></i> 新增</button>
					  	<button type="button" class="easyui-linkbutton" onclick="editUserReward()"><i class="fa fa-pencil"></i> 修改</button>
						<button type="button" class="easyui-linkbutton delete-btn" onclick="deleteUserReward()"><i class="fa fa-minus"></i> 删除</button>
					</c:if>
				</div>	
			</div>
			 <table id="userReward_list" title=""	toolbar="#${tabId} #userReward_search" pagination="true"
			 	rownumbers="true" fitColumns="true"  singleSelect="true" checkOnSelect="true" selectOnCheck="true">
						<thead>
							<tr>
			                	<th data-options="field:'id',checkbox:true"></th>
								<th field="awardProject" width="25">获奖项目</th>
								<th field="awardLevel" width="15">奖励级别</th>
								<th field="awardDrade" width="15">奖励等级</th>
								<th field="awardDept" width="25">颁奖单位</th>
								<th field="awardDate" width="15">获奖日期</th>
							</tr>
						</thead>
						<tbody>   
			       	<c:forEach items="${rewards }" var="reward">
			        <tr>   
			           <td>${reward.id }</td>
			           <td>${reward.awardProject }</td>
			           <td>${dict:getEntryName('AWARD_LEVEL',reward.awardLevel) }</td>
			           <td>${dict:getEntryName('AWARD_DRADE',reward.awardDrade) }</td>
			           <td>${reward.awardDept}</td>
			           <td><fmt:formatDate value="${reward.awardDate }" pattern="yyyy-MM-dd"/></td>
			        </tr>   
			       </c:forEach>
			    </tbody>   
			</table> 
			<jsp:include page="/jsp/common/include/manage_page_table.jsp">
				<jsp:param name="pageForm" value="userReward_list_form" />
			    <jsp:param name="tableId" value="userReward_list"/>
				<jsp:param name="type" value="easyui" />
				<jsp:param name="divId" value="${tabId}" />
			</jsp:include>
			 </form>
			</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(e) {
	
});
var userId = '${searchParam.paramMap.userId}';
var selectedData;
function isSelectRewardOne() {
	selectedData = jQuery('#userReward_list').datagrid('getSelections');
	if(selectedData.length != 1){
		$.messager.alert('消息','请选择一条记录','warning');
		return false;
	} else {
		return true;
	}
}

function viewUserReward() {
	if(isSelectRewardOne()) {
		var id = selectedData[0].id;
		easyuiUtils.openWindow('showAddRewardDialog','查看奖励信息',700,230,'${ctx}/common/user/reward/view.do?id='+id,true);
	}
}

function addUserReward() {
	easyuiUtils.openWindow('showAddRewardDialog','新建奖励',700,230,'${ctx}/common/user/reward/edit.do?userId='+userId,true);
}


function editUserReward() {
	if(isSelectRewardOne()) {
		var id = selectedData[0].id;
		easyuiUtils.openWindow('showAddRewardDialog','修改奖励信息',700,230,'${ctx}/common/user/reward/edit.do?id='+id,true);
	}
}

function deleteUserReward() {
	if(isSelectRewardOne()) {
		$.messager.confirm('提示', '确定删除？', function(r){
			if(r) {
				var data=$.ajaxSubmitValue('${ctx }/common/user/reward/delete.do?id='+selectedData[0].id);
				var json=jQuery.parseJSON(data);
				var responseMsg = json.responseMsg;
				var responseCode = json.responseCode;
				if(responseCode=='01'){
					jQuery.messager.alert("提示信息",responseMsg,"error");
				} else if(responseCode=='00'){
					jQuery.messager.alert("提示信息",responseMsg,"info");
					jQuery('#${tabId}').panel('refresh');
				}
			}
		});
	}
}
</script>