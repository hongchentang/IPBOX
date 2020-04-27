<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="easyui-panel" title="项目信息" collapsible="true">
	<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v-space">
	<tbody>
		<tr>
			<td width="20%">项目编码</td>
			<td width="30%">
				${project.projectCode }
			</td>
			<td width="20%">项目名称</td>
			<td width="30%">
				${project.projectName }
			</td>
		</tr>
		<tr>
            <td >首席专家</td>
            <td >
            	${project.expertId }
			</td>
			<td >项目实施年度</td>
            <td >
            	${project.year }
			</td>
          </tr>
          
           <tr>
            <td >部门负责人</td>
            <td >
            	${project.headId }
			</td>
			<td >项目执行部门</td>
            <td >
			   ${project.headUnit }
			</td>
          </tr>
          
          <tr>
            <td >学科（领域）</td>
            <td >
            	${project.projectSubject }
			</td>
			<td >产出指标</td>
            <td >
			   ${project.indexOut }
			</td>
          </tr>
          
          <tr>
            <td >效益指标</td>
            <td >
           		${project.indexBenefit }
			</td>
			<td >参训对象满意度指标</td>
            <td >
			  	${project.indexSatisfy }
			</td>
          </tr>
          
          <tr>
            <td >项目总经费</td>
            <td >
            	${project.cash }
			</td>
			<td >经费来源</td>
            <td >
			   ${project.cashFrom }
			</td>
          </tr>
          <tr>
            <td >项目绩效评价表自评结果</td>
            <td >
            	${project.evalPerform }
			</td>
			<td >项目自评得分</td>
            <td >
			   ${project.evalScore }
			</td>
          </tr>
          <tr>
			<td >附件</td>
            <td colspan="3">
			   <c:if test="${not empty project.attachment}">
			      <c:set value="${ipanthercore:getJSONMap(project.attachment)}" var="map" />
				      <div >
					  	<span><a href="${ctx}${map.downloadUrl}" target="download">${map.attachmentName}(点击下载)</a></span> 
					  </div>
			    </c:if>
			</td>
          </tr>
          <tr>
            <td >项目自评结论</td>
            <td colspan="3">
            	<textarea rows="2" style="width: 95%" readonly="readonly">${project.evalResult }</textarea>
			</td>
          </tr>
          <tr>
            <td >项目自评报告</td>
            <td colspan="3">
            	<textarea rows="2" style="width: 95%" readonly="readonly">${project.evalResult }</textarea>
			</td>
          </tr>
	</tbody>
	</table>
</div>
<script type="text/javascript">
tableVBg('.alter-table-v-space');

$(document).ready(function (){
	
});
</script>