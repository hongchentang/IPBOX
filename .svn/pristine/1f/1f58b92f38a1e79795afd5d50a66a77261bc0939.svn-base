<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>


<div id="viewUserQuestionTopPanel" class="easyui-panel" data-options="" style="height:98%">
	<div id="viewUserQuestionTopListTabs" class="easyui-tabs" data-options="tabPosition:'top',height:getHeight('viewUserQuestionTopPanel',100),cache:false">
	
		<div id="viewUserQuestionAllBaseTopTab" data-options="href:'${ctx}/cms/userQuestion/listAllUserQuestion.do?tabId=viewUserQuestionAllBaseTopTab&status=all'" title="全部" ></div>
		<div id="viewUserQuestionEditBaseTopTab" data-options="href:'${ctx}/cms/userQuestion/listAllUserQuestion.do?tabId=viewUserQuestionEditBaseTopTab&status=0'" title="未处理<font color='red'>(${toDo})</font>" ></div>
	
		<div id="viewUserQuestionTodoBaseTopTab" data-options="href:'${ctx}/cms/userQuestion/listAllUserQuestion.do?tabId=viewUserQuestionTodoBaseTopTab&status=1'" title="待发送<font color='red'>(${toSend})</font>" ></div>
		<div id="viewUserQuestionDoneBaseTopTab" data-options="href:'${ctx}/cms/userQuestion/listAllUserQuestion.do?tabId=viewUserQuestionDoneBaseTopTab&status=2'" title="已发送" ></div>
	</div>
	
</div>