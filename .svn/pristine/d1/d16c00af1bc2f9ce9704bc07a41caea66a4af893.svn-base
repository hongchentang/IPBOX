<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>

<c:set var="suffix" value="${param.tabId}"/><!-- 用于jsp被多次同时用到时区分各个DIV 的ID和函数名称 -->
<c:set var="formId" value="userQuestion_list_form${suffix}"/>
<c:set var="searchId" value="userQuestion_search${suffix}"/>
<c:set var="tableId" value="userQuestion_list${suffix}"/>







<%-- <form id="${formId}" action="${ctx}/cms/userQuestion/listAllUserQuestion.do" method="post"> --%>
<form id="${formId}" action="${ctx}/cms/userQuestion/listAllUserQuestion.do?tabId=${param.tabId}" method="post">
	
	<input type="hidden" id="action" name="paramMap[action]" value=""/><!-- 区别查询还是导出，导出为：expoert -->
	<input type="hidden" id="userQuestionIds" name="paramMap[userQuestionIds]" value=""/>
	
	
	
	<div class="easyui-panel" title="" data-options="" style="width: 100%;">
		<div class="datagrid-toolbar" float:right>
			<table cellspacin="0" cellpadding="3px">
				<tr>
					<td style="width: 80px">用户姓名：</td>
					<td><input type='text' name="paramMap[name]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.name }"></td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxPostFormForPanel('listUserQuestionForm','${param['tabId']}')" iconCls="fa fa-search">查询</a>
					</td>
				</tr>	
			</table> 
			<table cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<!-- <td><a onclick="editUserQuestion()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-edit">回复</i></a></td>
							<td><a onclick="deleteUserQuestion()" href="javascript:void(0)" class="easyui-linkbutton delete-btn"><i class="fa fa-minus">删除</i></a></td>		
							<td><a onclick="sendUserQuestion()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-minus">发送回复</i></a></td> -->
							
							<td><button type="button" id="editUserQuestion" class="easyui-linkbutton" ><i class="fa fa-plus"></i>回复</button></td>
							<td><button type="button" id="sendUserQuestion" class="easyui-linkbutton" ><i class="fa fa-plus"></i>发送回复</button></td>
							<td><button type="button" id="exportUserQuestion" class="easyui-linkbutton" ><i class="fa fa-plus"></i>导出</button></td>
							<td><button type="button" id="deleteUserQuestion" class="easyui-linkbutton" ><i class="fa fa-plus"></i>删除</button></td>
							
							
						</tr>
					</tbody>
			</table> 
		</div>
		<div>
			<table id="${tableId}" title=""	toolbar="#${param.tabId } #${searchId}" 
				rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="false" nowrap="true" singleSelect="false">
				<thead>
					<tr>
						<th width="30" data-options="field:'id',checkbox:true"></th>
						<th width="100" data-options="field:'name'">姓名</th>
						<th width="200" data-options="field:'email'">邮箱</th>
						<th width="100" data-options="field:'phone'">手机号码</th>
						<th width="100" data-options="field:'question'">问题描述</th>
						<th width="100" data-options="field:'createTime'">提交时间</th>
						<th width="90" data-options="field:'status'">状态</th> 
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listUserQuestion}" var="userQuestion">
						<tr>
							<td>${userQuestion.id }</td>
							<td>${userQuestion.name }</td>
							<td>${userQuestion.email }</td>
							<td>${userQuestion.phone }</td>
							<td>${userQuestion.question }</td>
							<td><fmt:formatDate value="${userQuestion.createTime}" pattern="yyyy-MM-dd" /></td>
<%-- 							<td>${userQuestion.createTime }</td> --%>
							<td>
								<c:choose>
									<c:when test="${userQuestion.status=='0'}">
										<span style="color: red;">未处理</span>
									</c:when>
									<c:when test="${userQuestion.status=='1'}">
										已回复
									</c:when>
									<c:when test="${userQuestion.status=='2'}">
										已发送
									</c:when>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="/jsp/common/include/manage_page_table.jsp">
				<jsp:param value="${formId}" name="pageForm" />
				<jsp:param value="${tableId}" name="tableId" />
				<jsp:param value="easyui" name="type" />
				<jsp:param value="${param['tabId']}" name="divId" />
			</jsp:include>
		</div>
	</div>
</form>
<script>
	//回复
	/* function editUserQuestion(){
		
		var objects = getCurrentTab().find('#${tableId}').datagrid('getSelections');
		
		if(objects.length==0){
			$.messager.alert('消息','请选择一条数据');
			return false;
		}
		if(objects.length>1){
			$.messager.alert('消息','不能同时编辑多条数据');
			return false;
		}
		
		var id = objects[0].id;
		openWindow("regUserQuestion","修改在线留言",750,450,"${ctx}/cms/userQuestion/goEditUserQuestion.do?paramMap[id]="+id,true);
	} */
	//回复
	getCurrentTab().find("#${param.tabId}").find('#editUserQuestion').linkbutton({onClick: function(){
		
		var objects = getCurrentTab().find('#${tableId}').datagrid('getSelections');
		
		if(objects.length==0){
			$.messager.alert('消息','请选择一条数据');
			return false;
		}
		if(objects.length>1){
			$.messager.alert('消息','不能同时编辑多条数据');
			return false;
		}
		
		var id = objects[0].id;
		openWindow("regUserQuestion","修改在线留言",750,450,"${ctx}/cms/userQuestion/goEditUserQuestion.do?paramMap[id]="+id,true);
	}});
	
	/* function sendUserQuestion(){
		var objects = getCurrentTab().find('#${tableId}').datagrid('getSelections');
		
		if(objects.length==0){
			$.messager.alert('消息','请选择一条数据');
			return false;
		}
		
		if(objects.length>1){
			$.messager.alert('消息','不能同时编辑多条数据');
			return false;
		}
		
		var status = objects[0].status;
		//去除所有的空格和换行		
		var s1 = status.replace(/^\s+|\s+$/g,"");
		var id = objects[0].id;
		if("已回复"==s1||"已发送"==s1){
			$.messager.confirm("确认","确定要发送选中的记录吗?",function(flag){
				$.ajax({
					url:"${ctx}/cms/userQuestion/sendUserQuestion.do?id="+id,
					type:"post",
					dataType:"json",
					async:true,
					success:function(data){
						if(data.flag){
							$.messager.alert('消息','发送成功');
							getCurrentTab().panel('refresh');
						}else{
							$.messager.alert('消息','发送失败');
						}
					}
				});
			});
		}else{
			$.messager.alert('消息','请先处理回复所选中的记录');
			return false;
		}
	} */
	
	//发送回复
	getCurrentTab().find("#${param.tabId}").find('#sendUserQuestion').linkbutton({onClick: function(){
		
		var objects = getCurrentTab().find('#${tableId}').datagrid('getSelections');
		
		if(objects.length==0){
			$.messager.alert('消息','请选择一条数据');
			return false;
		}
		if(objects.length>1){
			$.messager.alert('消息','不能同时处理多条数据');
			return false;
		}
		
		var status = objects[0].status;
		//去除所有的空格和换行		
		var s1 = status.replace(/^\s+|\s+$/g,"");
		var id = objects[0].id;
		if("已回复"==s1||"已发送"==s1){
			$.messager.confirm("确认","确定要发送选中的记录吗?",function(flag){
				$.ajax({
					url:"${ctx}/cms/userQuestion/sendUserQuestion.do?id="+id,
					type:"post",
					dataType:"json",
					async:true,
					success:function(data){
						if(data.flag){
							$.messager.alert('消息','发送成功');
							getCurrentTab().panel('refresh');
						}else{
							$.messager.alert('消息','发送失败');
						}
					}
				});
			});
		}else{
			$.messager.alert('消息','请先处理回复所选中的记录');
			return false;
		}
	}});
	
	
	//导出
	getCurrentTab().find("#${param.tabId}").find('#exportUserQuestion').linkbutton({onClick: function(){
		var userQuestionIds = "";
		var objects = getCurrentTab().find('#${tableId}').datagrid('getSelections');
		
		 if(objects.length==0){
			$.messager.alert('消息','请选择要导出的数据数据');
			return false;
		}
		
		for(var i=0;i<objects.length;i++){
			userQuestionIds+="\'"+objects[i].id+"\'"+','
		}
		
		jQuery('#${formId}').find("#userQuestionIds").val(userQuestionIds.substr(0,userQuestionIds.length-1));
		$.messager.show({
			title:'提示',
			msg:'系统正在导出，请稍后',
			timeout:2000,
			showType:'slide'
		});
		jQuery('#${formId}').find(":hidden[id='action']").val("export");
		jQuery('#${formId}').form('submit',{
		    success: function(data){}
		}); 
		jQuery('#${formId}').find("#userQuestionIds").val('');
		jQuery('#${formId}').find(":hidden[id='action']").val(""); 
		
		
	}});
	
	
	
	
	
	
	
	
	//删除
	/* function deleteUserQuestion(){
		var objects = getCurrentTab().find('#${tableId}').datagrid('getSelections');
		
		if(objects.length==0){
			$.messager.alert('消息','请选择一条数据');
			return false;
		}
		if(objects.length>1){
			$.messager.alert('消息','不能同时编辑多条数据');
			return false;
		}
		
		var id = objects[0].id;
		$.messager.confirm("确认","确定要删除选中的记录吗?",function(flag){
			$.ajax({
				url:"${ctx}/cms/userQuestion/deleteUserQuestion.do?id="+id,
				type:"post",
				dataType:'json',
				async:true,
				success:function(data){
					if(data.flag){
						$.messager.alert('提示','操作成功！');
						getCurrentTab().panel('refresh');
					}else{
						$.messager.alert('提示','操作失败！');
					}
				}
			});
		});		

	} */
	//删除
	getCurrentTab().find("#${param.tabId}").find('#deleteUserQuestion').linkbutton({onClick: function(){
		
		var objects = getCurrentTab().find('#${tableId}').datagrid('getSelections');
		
		if(objects.length==0){
			$.messager.alert('消息','请选择一条数据');
			return false;
		}
		if(objects.length>1){
			$.messager.alert('消息','不能同时编辑多条数据');
			return false;
		}
		
		var id = objects[0].id;
		$.messager.confirm("确认","确定要删除选中的记录吗?",function(flag){
			$.ajax({
				url:"${ctx}/cms/userQuestion/deleteUserQuestion.do?id="+id,
				type:"post",
				dataType:'json',
				async:true,
				success:function(data){
					if(data.flag){
						$.messager.alert('提示','操作成功！');
						getCurrentTab().panel('refresh');
					}else{
						$.messager.alert('提示','操作失败！');
					}
				}
			});
		});		
	}});
	
</script>