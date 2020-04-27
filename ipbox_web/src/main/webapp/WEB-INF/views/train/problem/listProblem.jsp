<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp" %>
<form id="listProblemForm" action="${ctx}/train/problem/listProblem.do?tabId=${param.tabId }" method="post">
<div id="listProblemSearch">

	<div style="margin: 10px">    
		名称:<input class="easyui-textbox" type="text" name="paramMap[problemName]" value="${searchParam.paramMap.problemName}" />
		编码:<input class="easyui-textbox" type="text" name="paramMap[problemCode]" value="${searchParam.paramMap.problemCode}" />
		<button type="button" class="easyui-linkbutton main-btn" onclick="easyuiQuery('listProblemForm',getCurrentTabId());"><i class="fa fa-search"></i> 查询</button>
		<button type="button" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('${param['tabId']}')"><i class="fa fa-eraser"></i> 重置</button> 
	</div>
	<div style="margin: 10px">
		<button type="button" id="listProblemAddBtn"  class="easyui-linkbutton" ><i class="fa fa-plus"></i> 新增</button>
		<button type="button" id="listProblemEditBtn" class="easyui-linkbutton"><i class="fa fa-pencil"></i> 修改</button>
		<button type="button" id="listProblemDelBtn"  class="easyui-linkbutton delete-btn"><i class="fa fa-minus"></i> 删除</button>	
	</div>
</div>
 <table id="listProblemList" title="常见问题列表"	toolbar="#${param.tabId } #listProblemSearch" pagination="true"
 	rownumbers="true" fitColumns="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
					<th width="30" data-options="field:'id',checkbox:true"></th>
                    <th width="30" field="problemCode">问题编码</th>
		 			<th width="30" field="problemName" >问题名称</th>
		 			<th width="30" field="problemContent" >问题内容</th>
				</tr>
			</thead>
			<tbody>   
       			<c:if test="${not empty resultList}">
				  <c:forEach items="${resultList}"  var="list" varStatus="i">
			         <tr>
			         	 <td>${list.id }</td>
	                     <td>
	                     	${list.problemCode }
	                     </td>
	                     <td>${list.problemName }</td>
	                     <td>${list.problemContent }</td>
	                  </tr>
                </c:forEach>  
                </c:if>    
    </tbody>   
</table> 
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
	<jsp:param name="pageForm" value="listProblemForm" />
    <jsp:param name="tableId" value="listProblemList"/>
	<jsp:param name="type" value="easyui" />
	<jsp:param name="divId" value="${param['tabId']}"/>
</jsp:include>
 </form>
<script type="text/javascript">
$(document).ready(function(e) {
	getCurrentTab().find('#listProblemAddBtn').linkbutton({    
		onClick:function(){
			easyuiUtils.openWindow('editProblemWindow','新增常见问题',400,250, '${ctx}/train/problem/goAddProblem.do',true);
		}
	}); 
 
	getCurrentTab().find('#listProblemEditBtn').linkbutton({    
		onClick:function(){
			var row=getCurrentTab().find('#listProblemList').datagrid('getSelected');
			if(row==null){
				$.messager.alert('提示','请选择一行数据进行操作！','warning');
			}else{
				easyuiUtils.openWindow('editProblemWindow','编辑常见问题',400,250,'/train/problem/goEditProblem.do?problem.id='+row.id,true);
			}
		}
	}); 
	getCurrentTab().find('#listProblemDelBtn').linkbutton({    
		onClick:function(){
			var row=getCurrentTab().find('#listProblemList').datagrid('getSelected');
			if(row==null){
				$.messager.alert('提示','请选择一行数据进行操作！','warning');
			}else{
				easyuiUtils.confirmPostUrl('是否确定常见问题【'+row.problemName+'】?','${ctx}/train/problem/deleteProblem.do?problem.id='+row.id);
			}
		}
	}); 
});
</script>