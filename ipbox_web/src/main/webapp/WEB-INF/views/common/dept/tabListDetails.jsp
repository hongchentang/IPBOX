<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="viewDeptTopPanel" class="easyui-panel" data-options="" style="height:98%">
	<div id="viewDeptTopListTabs" class="easyui-tabs" fit="true" data-options="tabPosition:'left',height:getHeight('viewDeptTopPanel',100),cache:false">
		<div id="viewDeptBaseTopTab" data-options="href:'${ctx}/common/dept/listDept.do?id=${department.id}'" title="机构基本信息" ></div>
<%-- 		<div id="viewDeptFinancialTopTab" data-options="href:'${ctx}/common/dept/listFinancial.do?paramMap[departmentId]=${department.id}'" title="奖励信息" ></div> --%>
		<div id="viewDeptManagersTopTab" data-options="href:'${ctx}/common/dept/listManagers.do?paramMap[departmentId]=${department.id}'" title="管理人员信息" ></div>
		<div id="viewDeptFundsTopTab" data-options="href:'${ctx}/common/dept/listFunds.do?paramMap[departmentId]=${department.id}'" title="受资助情况" ></div>
		<div id="viewDeptPropertiesTopTab" data-options="href:'${ctx}/common/dept/listProperties.do?paramMap[departmentId]=${department.id}'" title="机构资质" ></div>
<%-- 		<div id="viewDeptEmployeeTopTab" data-options="href:'${ctx}/common/dept/listEmployee.do?paramMap[departmentId]=${department.id}'" title="机构员工信息" ></div> --%>
		<div id="viewDeptIntellectualTopTab" data-options="href:'${ctx}/common/dept/listIntellectual.do?paramMap[departmentId]=${department.id}'" title="知识产权状况" ></div>
	</div>
</div>