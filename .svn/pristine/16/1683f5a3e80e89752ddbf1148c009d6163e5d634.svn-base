<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>  
	 <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">
          <tr>
            <td colspan="7" >项目绩效目标申报表（培训类）--${project.projectName}</td>
          </tr>
          <tr>
            <td rowspan="7" width="25px">
           		 项目绩效指标
            </td>
            <td height="30px"  width="165px"><b>一级指标</b> </td>
            <td height="30px"  width="165px"><b>二级指标</b> </td>
            <td height="30px"  width="165px"><b>指标内容</b> </td>
            <td height="30px"><b>指标值</b> </td>
            <td height="30px"><b>实际指标值</b> </td>
            <td height="30px"><b>备注</b> </td>
          </tr>
          <tr>
            <td rowspan="5" width="25px">
           		 1.项目产出
            </td>
            <td height="30px">1.参训人数完成率
				参训人数完成率=（实际参训人数/项目计划参训人数）
			 </td>
            <td height="30px">实际参训人数占项目计划参训人数 </td>
            <td height="30px">不低于90%<br/>
            </td>
            <td height="30px">
                 <c:if test="${dataMap.trainNum!=0 and not empty dataMap.trainNum}">
                 	${dataMap.cxrs/dataMap.trainNum*100}
                 </c:if>
                 <c:if test="${dataMap.trainNum==0 or empty dataMap.trainNum}">
                   0
                 </c:if>
            	%
            <td height="30px">计划参训人数为 ${dataMap.trainNum==null?0:dataMap.trainNum}人<br/>
          </tr>
          <tr>
            <td height="30px">2.培训学时完成率
				培训学时完成率=（实际完成培训学时数/年度项目计划培训学时数）
			 </td>
            <td height="30px">实际完成培训学时占年度项目计划培训学时的比例 </td>
            <td height="30px">
            	 100%</td>
            <td>
            <c:if test="${dataMap.planHours!=0 and not empty dataMap.planHours}">
                 	${dataMap.wcxss/dataMap.planHours*100}
                 </c:if>
                 <c:if test="${dataMap.planHours==0 or empty dataMap.planHours}">
                   0
                 </c:if>%</td>
            <td height="30px">年度计划培训学时为 ${dataMap.planHours==null?0:dataMap.planHours}<br/>
          </tr>
          <tr>
            <td height="30px">3.培训作品（心得体会、论文等）提交率
				培训作品提交率=（作品提交人数/项目参训人数）
			</td>
            <td height="30px">作品提交人数占项目参训人数的比例</td>
            <td height="30px">
            		100%</td>
            <td height="30px">
           <c:if test="${dataMap.cxrs!=0 and not empty dataMap.cxrs}">
            ${dataMap.zptjrs/dataMap.cxrs*100}
            </c:if>
            <c:if test="${dataMap.cxrs==0 or empty dataMap.cxrs}">
            0
            </c:if>
            %</td>
            <td height="30px">参训学员每人提交一份培训心得体会</td>
          </tr>
          <tr>
            <td height="30px">4.培训学员整体出勤率=(实际参训人数/报名人数)</td>
            <td height="30px">实际参训学员人数占报名学员人数的比例</td>
            <td height="30px">不低于90%</td>
            <td height="30px">
            	<c:if test="${dataMap.cxrs!=0 and not empty dataMap.cxrs}">
                 	${dataMap.sjcxrs/dataMap.cxrs*100}
                 </c:if>
                 <c:if test="${dataMap.cxrs==0 or empty dataMap.cxrs}">
                   0
                 </c:if>
            
            %</td>
            <td height="30px">比如，实际参训人数为${dataMap.sjcxrs==null?0:dataMap.sjcxrs}，报名人数为${dataMap.cxrs==null?0:dataMap.cxrs}</td>
          </tr>
          <tr>
            <td height="30px">5.培训学员考试通过达标率=(通过考试人数/实际参加考试人数)</td>
            <td height="30px">通过考试人数占实际参加考试人数的比例</td>
            <td height="30px">依据具体情况进行设定</td>
            <td height="30px">
            <c:if test="${dataMap.cxrs!=0 and not empty dataMap.cxrs}">
                 	${dataMap.tgrs/dataMap.cxrs*100}
                 </c:if>
                 <c:if test="${dataMap.cxrs==0 or empty dataMap.cxrs}">
                   0
                 </c:if>%</td>
            <td height="30px">非必填 比如：通过率设置为不超过30%</td>
          </tr>
          <tr>
          	<td height="30px" rowspan="1">2.项目效益</td>
            <td height="30px">6.区域参训平衡发展情况=实际参训人员地域数/辖内行政地域数</td>
            <td height="30px">辖区内参训人员地域分布总体情况</td>
            <td height="30px">依据具体情况进行设定</td>
            <td height="30px">
             <c:if test="${not empty dataList}">
             <c:forEach items="${dataList}" var="list">
                  <c:if test="${list.regionsLevel==1}">
                  	 覆盖 ${list.regionsName}
                  	 <fmt:formatNumber value="${(list.sumNum/list.countNum*100)}" pattern="##.##" minFractionDigits="2"/>
                  	 % 地市
                  </c:if>
            	  <c:if test="${list.regionsLevel==2}">
                  	 覆盖 ${list.regionsName} 
					 <fmt:formatNumber value="${(list.sumNum/list.countNum*100)}" pattern="##.##" minFractionDigits="2"/>
					% 区县
                  </c:if>
                  <c:if test="${list.regionsLevel==3}">
                  	 覆盖 ${list.regionsName} 
                  	 
                  	 <fmt:formatNumber value="${(list.sumNum/list.countNum*100)}" pattern="##.##" minFractionDigits="2"/>
                  	 % 街道/镇
                  </c:if>
                  <br/>
             </c:forEach>
             </c:if>
             <c:if test="${empty dataList}">
             	暂无区域覆盖率数据
             </c:if>
             </td>
            <td height="30px">比如：覆盖广东90%地市；清远覆盖100%区县</td>
          </tr>
          <tr>
            <td height="30px">本项目以前年度绩效评价情况</td>
            <td  colspan="6">暂无以前年度绩效评价情况</td>
          </tr>
          <tr>
            <td height="30px">自评报告</td>
            <td  colspan="6">
            	 <c:if test="${not empty dataMap.attachment}">
			      	<c:set value="${ipanthercore:getJSONMap(dataMap.attachment)}" var="map" />
				      <div id="fileSpanAttachment">
					  	<span id="attachmentName"><a href="${ctx}${map.downloadUrl}" target="download">${map.attachmentName}(点击下载)</a></span> 
					  </div>
            	 </c:if>	
            	 <c:if test="${empty dataMap.attachment}">
            	 	暂无自评报告附件请在列表页上传附件
            	 </c:if>	
            
            </td>
         </tr>
        </table>