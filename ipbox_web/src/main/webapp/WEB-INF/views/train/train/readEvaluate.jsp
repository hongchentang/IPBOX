<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<style>
table {
	border: 1px  solid #CCCCCC;
	font-size: 13px;
}
table thead th {
	line-height:50px;
	font-weight: bolder;
	font-size: 14px;
	text-align: center
}
</style>
<div class="easyui-panel" title="教学评价表" data-options="" style="width: 98%;">
  <table cellspacing="0" cellpadding="0" border="1">
    <thead>
			<tr align="center" >
				<th width="15%" >评价项目</th>
	 			<th width="42%" colspan="5" class="project">教学内容</th>
	 			<th width="43%" colspan="6" class="project">教师表现</th>
			</tr>
		 </thead>
		 <tbody>
		 	<tr align="center" >
			   <th class="project">评分标准</th>
			   <td>教学内容与实际工作需要联系紧密</td>
			   <td>教学内容准确，重点突出，条理清晰</td>
			   <td>案例生动，使用与企业领域相关或通俗易懂的案例，使课程深入浅出</td>
			   <td>理论与案例结合恰到好处，学员印象深刻</td>
			   <td>培训课件清楚，有利于理解课程内容</td>
		 	   <td>教师对所讲授内容非常熟悉，思路清晰，授课过程顺畅</td>
		 	   <td>课堂氛围活跃，与学员有互动</td>
		 	   <td>时间和进度把握得当</td>
		 	   <td>能较好的利用目光、手势、走动等身体语言</td>
		 	   <td>声音清楚，语速、音调变化适当，有吸引力</td>
		 	   <td>总分</td>
		 	</tr>
		 	<c:forEach items="${listUser}" var="listUser" varStatus="i">
		 	<tr align="center" style="line-height: 30px;">
					<td align="center"> ${listUser.userName}</td>
					<td align="center">
						${teachingEvalua[listUser.teacherId].inseparable}
					</td>
					<td align="center">
						${teachingEvalua[listUser.teacherId].accuracy}
					</td>
					<td align="center">
						${teachingEvalua[listUser.teacherId].lively}
					</td>
					<td align="center">
						${teachingEvalua[listUser.teacherId].deepGoing}
					</td>
					<td align="center">
						${teachingEvalua[listUser.teacherId].clear}
					</td>
					<td align="center">
						${teachingEvalua[listUser.teacherId].insideDopester}
					</td>
					<td align="center">
						${teachingEvalua[listUser.teacherId].properly}
					</td>
					<td align="center">
						${teachingEvalua[listUser.teacherId].bodyLanguage}
					</td>
					<td align="center">
						${teachingEvalua[listUser.teacherId].appropriate}
					</td>
					<td align="center">
						${teachingEvalua[listUser.teacherId].active}
					</td>
					<td align="center">
						${teachingEvalua[listUser.teacherId].totalNum}
					</td>
		 	</tr>
		    </c:forEach>
		 </tbody>
  </table>
</div>
<div class="easyui-panel" title="" data-options="border:false" style="height: 50px;">
</div>
<div class="easyui-panel" title="培训班综合评价" data-options="" style="width:98%;">
  <table  cellspacing="0" cellpadding="0" border="1" style="width: 100%;"  style="line-height: 20px;">
    <thead>
			<tr align="center" >
				<th width="10%" >序号</th>
				<th width="40%" >评价内容</th>
				<th >评价分数</th>
			</tr>
		</thead>
		<tbody>
		<tr align="center"  style="line-height: 30px;">
			<td>1</td>
			<td>课程设置系统、完整</td>
			<td>
				<div>${courseEffectEvalua.complete}</div>
			</td>
		</tr>
		<tr align="center"  style="line-height: 30px;">
			<td class="number">2</td>
			<td>课时安排及信息量恰当</td>
			<td class="score2">
				<div>${courseEffectEvalua.appropriate}</div>
			</td>
		</tr>
		<tr align="center"  style="line-height: 30px;">
			<td class="number">3</td>
			<td>教学组织形式合适</td>
			<td class="score2">
				<div>${courseEffectEvalua.suitableForm}</div>
			</td>
		</tr>
		<tr align="center"  style="line-height: 30px;">
			<td class="number">4</td>
			<td>组织管理工作到位</td>
			<td class="score2">
				<div>${courseEffectEvalua.dovewhell}</div>
			</td>
		</tr>
		<tr align="center"  style="line-height: 30px;">
			<td class="number">5</td>
			<td>后勤服务保障工作满意</td>
			<td class="score2">
				<div>${courseEffectEvalua.logistics}
				</div>
			</td>
		</tr>
		<tr align="center"  style="line-height: 30px;"> 
			<td class="number">6</td>
			<td>对本次培训班的总体评价</td>
			<td class="score2">
				<div>
				${courseEffectEvalua.overall}</div>
			</td>
		</tr>
		<tr align="center" style="line-height: 30px;">
			<td colspan="2" align="left">
				<div class="questionTitle">&nbsp; 1、您认为本次的培训课程中哪些课程最贴近您的需求，最能对您的工作有帮助？</div>
			</td>
			<td>	
			<div>
					${courseEffectEvalua.help}
				</div>
			</td>
		</tr>
		<tr align="center" style="line-height: 30px;">
			<td colspan="2" align="left">
				<div class="questionTitle">&nbsp; 2、下次培训，您还希望开设什么样的课程，请列举？</div>
			</td>
			<td ><div>
					${courseEffectEvalua.enumerate}
				</div>
			</td>
		</tr>
		<tr align="center" style="line-height: 30px;">
			<td colspan="2" align="left" >
				<div class="questionTitle">&nbsp; 3、您在本期培训班的主要收获是什么？</div>
				
			</td>
			<td ><div>
					${courseEffectEvalua.results}</div>
		</tr>
		<tr align="center" style="line-height: 30px;">
			<td colspan="2" align="left" >
				<div class="questionTitle">&nbsp; 4、您对本次培训班还有哪些建议和意见？</div>
			</td>
			<td ><div>
					${courseEffectEvalua.suggestion}
				</div>
			</td>
		</tr>
		<tr align="center" style="line-height: 30px;">
			<td colspan="2" align="left">
				<div class="questionTitle">&nbsp; 5、培训总结</div>
			</td>
			<td >
			<div>
					<c:if test="${not empty courseEffectEvalua.trainingSummary}">
						      <c:set value="${ipanthercore:getJSONMap(courseEffectEvalua.trainingSummary)}" var="trainingSummary" />
						      <div id="fileSpan">
							  	&nbsp;&nbsp;&nbsp;
							  	<span id="attachmentName">
							  		<a href="${downloadUrl}?attachment.id=${trainingSummary.attachmentId}"  target="_blank">${trainingSummary.attachmentName}(点击下载)</a>
							  	</span> 
							  </div>
						    </c:if>
				</div>
			</td>
		</tr>
		<tr align="center" style="line-height: 30px;">
			<td colspan="2" align="left">
				<div class="questionTitle">&nbsp; 总分数</div>
			</td>
			<td>${courseEffectEvalua.allTotalNum}</td>
		</tr>
		<tr align="center" style="line-height: 30px;">
			<td colspan="2" align="left">
				<div class="questionTitle">&nbsp; 平均分</div>
			</td>
			<td>${courseEffectEvalua.allTotalNum/courseEffectEvalua.totalUserNum}</td>
		</tr>
		<tr align="center" style="line-height: 30px;">
			<td colspan="2" align="left">
				<div class="questionTitle">&nbsp; 总评价人次</div>
			</td>
			<td>${courseEffectEvalua.totalUserNum}</td>
		</tr>
		</tbody>
  </table>
</div>
<script type="text/javascript">
$(document).ready(function (){
});
</script>
