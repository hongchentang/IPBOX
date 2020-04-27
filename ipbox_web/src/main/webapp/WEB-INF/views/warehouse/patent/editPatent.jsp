<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<form id="patentMForm" name="patentMForm" >
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="20%">专利编号</td>
				<td colspan="3">
					${patentM._id}
				</td>
			</tr>
			<c:forEach var="list" items="${patentM.agents}" varStatus="status">
		    <tr>
				<td width="20%">专利代理人编号</td>
				<td width="30%" >
					${list._id}
				</td>
				<td width="20%">专利代理人名称</td>
				<td width="30%" >
					${list.name}
				</td>
			</tr>
		    </c:forEach>
		    <tr>
				<td width="20%">专利代理机构名称</td>
				<td colspan="3" >
					${patentM.agencies[0]}
				</td>

			</tr>
			<tr>
				<td width="20%">标题</td>
				<td colspan="3" style="text-align:left;font-size:16px;">
					${patentM.title.original}
				</td>

			</tr>
			<tr>
				<td width="20%">摘要</td>
				<td colspan="3" style="text-align:left;font-size:16px;">
					${patentM.Abstract.original}
				</td>

			</tr>
			<tr>
				<td width="20%">第一申请人姓名</td>
				<td width="30%" >
					${patentM.applicants[0].name.original}
				</td>
				<td width="20%">第一申请人地址</td>
				<td width="30%" >
					${patentM.applicants[0].address.original}
				</td>
			</tr>
			<tr>
				<td width="20%">第一申请人编码</td>
				<td width="30%" >
					${patentM.applicants[0].id}
				</td>
				<td width="20%">第一申请人地区</td>
				<td width="30%" >
					${patentM.applicants[0].countries[0]}
				</td>
			</tr>
			<c:forEach var="list" items="${patentM.inventors}" varStatus="status">
		    <tr>
				<td width="20%">发明人编号</td>
				<td width="30%" >
					${list._id}
				</td>
				<td width="20%">发明人名称</td>
				<td width="30%" >
					${list.name.original}
				</td>
			</tr>
		    </c:forEach>
		    <c:forEach var="list" items="${patentM.priorities}" varStatus="status">
		    <tr>
				<td width="20%">优先权号</td>
				<td width="30%" >
					${list.priority_number}
				</td>
				<td width="20%">优先权日</td>
				<td width="30%" >
					${list.proority_date}
				</td>
			</tr>
		    </c:forEach>
		</tbody>
		<c:if test="${not empty patentM.cited_patents }">
		<tbody>
			<tr>
				<td colspan="4" style="text-align:left"><span style="color:red">该专利引用的专利</span></td>				
			</tr>
		    <c:forEach var="list" items="${patentM.cited_patents}" varStatus="status">
		    <tr>
				<td width="20%">引用专利第一申请人编码</td>
				<td width="30%" >
					${list.applicants[0].id}
				</td>
				<td width="20%">引用专利第一申请人</td>
				<td width="30%" >
					${list.applicants[0].name}
				</td>
			</tr>
			<tr>
				<td width="20%">引用专利主键编码</td>
				<td width="30%" >
					${list.id}
				</td>
				<td width="20%">引用专利公开号</td>
				<td width="30%" >
					${list.publication_number}
				</td>
			</tr>
			<tr>
				<td width="20%">引用类型</td>
				<td width="30%" >
					<c:if test="${list.type==1}">
						自己引用
					</c:if>
					<c:if test="${list.type==2}">
						同族引用
					</c:if>
				</td>
				<td width="20%">引用信息修改版本</td>
				<td width="30%" >
					${list.version}
				</td>
			</tr>
			<tr>
				<td width="20%">引用信息修改版本</td>
				<td colspan="3" >
					${list.remark}
				</td>
			</tr>
		    </c:forEach>			
		</tbody>
		</c:if>
		<c:if test="${not empty patentM.citing_patents }">
		<tbody>
			<tr>
				<td colspan="4" style="text-align:left"><span style="color:red">引用该专利的专利</span></td>				
			</tr>
		    <c:forEach var="list" items="${patentM.citing_patents}" varStatus="status">
		    <tr>
				<td width="20%">引用专利第一申请人编码</td>
				<td width="30%" >
					${list.applicants[0].id}
				</td>
				<td width="20%">引用专利第一申请人</td>
				<td width="30%" >
					${list.applicants[0].name}
				</td>
			</tr>
			<tr>
				<td width="20%">引用专利主键编码</td>
				<td width="30%" >
					${list.id}
				</td>
				<td width="20%">引用专利公开号</td>
				<td width="30%" >
					${list.publication_number}
				</td>
			</tr>
			<tr>
				<td width="20%">引用类型</td>
				<td width="30%" >
					<c:if test="${list.type==1}">
						自己引用
					</c:if>
					<c:if test="${list.type==2}">
						同族引用
					</c:if>
				</td>
				<td width="20%">引用信息修改版本</td>
				<td width="30%" >
					${list.version}
				</td>
			</tr>
			<tr>
				<td width="20%">引用信息修改版本</td>
				<td colspan="3">
					${list.remark}
				</td>
			</tr>
		    </c:forEach>			
		</tbody>
		</c:if>
		<tbody>
			<c:forEach var="list" items="${patentM.cited_literatures}" varStatus="status">
				<tr>
					<td>引用的科技文献</td>
					<td colspan="3">
						${list.original}
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tbody>
			<tr>
				<td colspan="4" style="text-align:left"><span style="color:red">IPC主分类</span></td>				
			</tr>
		    <tr>
				<td width="20%">IPC分类</td>
				<td width="30%" >
					${patentM.main_ipc.ipc}
				</td>
				<td width="20%">IPc分类版本</td>
				<td width="30%" >
					${patentM.main_ipc.version}
				</td>
			</tr>
			<tr>
				<td width="20%">IPC分类详情</td>
				<td width="30%" >
					${patentM.main_ipc.ipcr}
				</td>
				<td width="20%">IPc分类版本</td>
				<td width="30%" >
					${patentM.main_ipc.type}
				</td>
			</tr>
			<tr>
				<td width="20%">审查员</td>
				<td width="30%">${patentM.examiner[0].name}</td>
				<td width="20%">专利类型</td>
				<td width="30%">
					<c:if test="${patentM.type==1}">
						发明
					</c:if>
					<c:if test="${patentM.type==2}">
						实用新型
					</c:if>
					<c:if test="${patentM.type==3}">
						外观设计
					</c:if>
				</td>
			</tr>
			<tr>
				<td width="20%">申请号</td>
				<td width="30%">${patentM.application_number}</td>
				<td width="20%">申请日</td>
				<td width="30%">${patentM.application_date}</td>
			</tr>
			<tr>
				<td width="20%">公开号</td>
				<td width="30%">${patentM.publication_number}</td>
				<td width="20%">最早公开日</td>
				<td width="30%">${patentM.earliest_publication_date}</td>
			</tr>
			<tr>
				<td width="20%">主权利要求</td>
				<td colspan="3" style="text-align:left">${patentM.main_claim}</td>
			</tr>
			
		</tbody>
	</table>
	<div style="text-align: center">
		<button type="button" onclick="save()" class="easyui-linkbutton" > <i class="fa fa-save"></i>保 存 </button>
		<button type="reset"  class="easyui-linkbutton" class="easyui-linkbutton"> 重 置 </button>
	</div>
</form>
<script type="text/javascript">
 tableVBg('alter-table-v');
$(document).ready(function (){	
// 	var str ="{'id':'ssss','name':'zonggaojin'}";
// 	alert(${patentM});
// 	var obj = ${patentM};
//  	alert(obj._id);
//  	var newstr=JSON.stringify(obj); //返回一个新字符串
//  	console.log(newstr);
});

</script>
