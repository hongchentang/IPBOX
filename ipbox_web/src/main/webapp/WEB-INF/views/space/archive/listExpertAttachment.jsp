<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.download.url')}" var="downloadUrl"/>
<div style="margin: 5px;margin-bottom: 27px">
		<form id="listAttachmentForm" action="${ctx}/space/archive/noskin/listExpertAttachment.do" method="post">
			<input id="courseId" type="hidden" name="paramMap[courseId]" value="${searchParam.paramMap.courseId }">
			<div class="easyui-panel" title="" data-options="" style="width: 100%;">
				<div class="datagrid-toolbar" float:right>
					 <table cellspacin="0" cellpadding="3px">
						<tr>
							<td width="55px">资源名称</td>
							<td width="130px" ><input name="paramMap[attachmentName]" class="easyui-textbox"  value="${searchParam.paramMap.attachmentName }"></td>
							<td width="60px">
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:easyuiQuery('listAttachmentForm','fileTab');" >查询</a>
							</td>
							<td width="85px">
								<a id="uploadBtn" href="javascript:void(0)" class="easyui-linkbutton" onclick="" >上传资源</a>
							</td>
							<!-- <td width="60px">
								<a id="dowloadBtn" href="javascript:void(0)" class="easyui-linkbutton" onclick="" >下载</a>
							</td>
							<td width="60px">
								<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton" onclick="" >删除</a>
							</td> -->
						</tr>	
					</table>
				</div>
				<div>
					<table id="listAttachmentTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
						<thead>
							<tr>
								<th width="30" data-options="field:'id',checkbox:true"></th>
								<th width="250" data-options="field:'fileName'">资源名称</th>
								<th width="100" data-options="field:'fileSize'">大小(KB)</th>
								<th width="100" data-options="field:'createTime'">上传时间</th>
								<th width="40" data-options="field:'handel'"></th>
								<th width="40" data-options="field:'handel2'"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listAttachment }" var="atta">
								<tr>
									<td>${atta.id }</td>
									<td>${(empty atta.fileName)?atta.tempFileName:atta.fileName }</td>
									<td>${atta.fileSize/1024 }</td>
									<td><fmt:formatDate value="${atta.createTime }"/></td>
									<td>
									<a href="${ctx}${downloadUrl }?attachment.id=${atta.id }" onclick="">下载</a>
									</td>
									<td>
									<a href="javascript:void(0)" onclick="delExpertFile('${atta.id }','${(empty atta.fileName)?atta.tempFileName:atta.fileName }')">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<jsp:include page="/jsp/common/include/manage_page_table.jsp">
						<jsp:param value="listAttachmentForm" name="pageForm" />
						<jsp:param value="listAttachmentTable" name="tableId" />
						<jsp:param value="easyui" name="type" />
						<jsp:param value="fileTab" name="divId" />
					</jsp:include>
				</div>
			</div>
		</form>
	</div>

<script>
$(document).ready(function(){
	$("#uploadBtn","#listAttachmentForm").bind("click",function(){
		openWindow('uploadWindow','上传附件',500,300,'${ctx}/train/course/goUploadFile.do?paramMap[courseId]=${searchParam.paramMap.courseId }',true);
	})
})
function delExpertFile(attachmentId,attachmentName){
	$.messager.confirm('确认','确定想要删除'+attachmentName+'吗？', function(flag){
		if(flag){
			$.ajax({
				url:'${ctx}/train/course/deleteExpertFile.do?paramMap[courseId]='+$("#courseId").val()+"&paramMap[attachmentId]="+attachmentId,
				type:'post',
				success:function(){
					$.messager.alert('提示','操作成功！');
					$("#fileTab").panel('refresh');
				}
			});
		}
	});
}
</script>