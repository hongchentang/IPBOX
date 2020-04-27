<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:choose>
	<c:when test="${ipanthercommon:isSpace(sessionScope.loginUser.roleCode)}">
		<c:set var="tableClass" value="alter-table-v-space"/>
	</c:when>
	<c:otherwise>
		<c:set var="tableClass" value="alter-table-v"/>
	</c:otherwise>
</c:choose>
		<input type="hidden" name="userQuestion.id" id="id" value="${userQuestion.id}"/>
		<input type="hidden" name="userQuestion.userId" id="userId" value="${userQuestion.userId}"/>
		<table cellspacing="0" cellpadding="0" border="0" class="${tableClass}">
		<tbody>
			<tr>
				<td>姓名</td>
				<td>
					${userQuestion.name}
				</td>
			</tr>
			<tr>
				<td>邮箱</td>
				<td>
					${userQuestion.email}
				</td>
			</tr>
			<tr>
				<td>电话</td>
				<td>
					${userQuestion.phone}
				</td>
			</tr>
			<tr>
				<td>问题描述</td>
				<td>
					<textarea readonly="readonly" rows="4" cols="4" style="width:100%;">${userQuestion.question}</textarea>
				</td>
			</tr>
			<tr>
				<td>问题回复</td>
				<td>
					<textarea readonly="readonly" rows="4" cols="4" style="width:100%;">${userQuestion.answer}</textarea>
				</td>
			</tr>
			
			
		</tbody>
	</table>
	<div style="text-align: center">
		<button type="button"  class="easyui-linkbutton" class="easyui-linkbutton" onclick="closeWindow('viewUserQuestionDialog')"> 关闭 </button>
	</div>
<script type="text/javascript">
tableVBg('.${tableClass}');
$(document).ready(function (){
	
});

</script>
