<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="viewPatentTopPanel" class="easyui-panel" data-options="" style="height:98%">
	<div id="viewPatentTopListTabs" class="easyui-tabs" fit="true" data-options="tabPosition:'left',height:getHeight('viewPatentTopPanel',100),cache:false">
		<div id="viewPatentBaseTopTab" data-options="href:'${ctx}/warehouse/patenthouse/getPatentById.do?id=${id}'" title="基本信息" ></div>
		<div id="viewPatentClaimTopTab" data-options="href:'${ctx}/warehouse/patenthouse/getClaimById.do?id=${id}'" title="权力要求" ></div>
		<div id="viewPatentLegalTopTab" data-options="href:'${ctx}/warehouse/patenthouse/getLegalById.do?id=${id}'" title="法律状态" ></div>
		<div id="viewPatentPhotoTopTab" data-options="href:'${ctx}/warehouse/patenthouse/getPhotoById.do?id=${id}'" title="专利附图" ></div>
		<div id="viewPatentDesTopTab" data-options="href:'${ctx}/warehouse/patenthouse/getDescribleById.do?id=${id}'" title="专利说明书" ></div>
		<div id="viewPatentPDFTopTab" data-options="href:'${ctx}/warehouse/patenthouse/getPDFById.do?id=${id}'" title="专利pdf全文" ></div>
<%-- 		<div id="viewPatentHisTopTab" data-options="href:'${ctx}/knowledge/patentInfo/getHistoryById.do?id=${id}'" title="历史信息" ></div>			 --%>
	</div>
</div>