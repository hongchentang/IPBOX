<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<!-- echarts图表 -->
	<div style="text-align: center;">
		<b>当前年份:</b>
		<select id="cur_year" onchange="selectYear(this.value)">
		<option value="">--请选择--</option>
		<c:forEach var="year" items="${years}" varStatus="i">
			<option value="${year}" <c:if test="${currentYear==year}"> selected="selected"</c:if>>${year}年</option>
		</c:forEach>
		</select>
	</div>
	<div  style="height:400px"  id="statisticsTrainMonth">
	</div>
	<script type="text/javascript">
	var myChartMonth =echarts.init($("#statisticsTrainMonth").get(0), 'macarons');
	var option={
			title:{text:"${currentYear}年月度面授培训、远程培训、总培训人数统计",
				   x: "center"
				  },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		    	x: 'left',
		        data:['面授课程','远程课程','总培训人数'],
		    	//selectedMode:false
		    },
		    toolbox: {
		        show : true,
		        feature : {
		           // mark : {show: true},
		            //dataView : {show: false, readOnly: true},
		            restore : {show: true},
		            magicType : {show: true, type: ['line', 'bar']},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : eval('(${month})')
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        { 
		            name:'面授课程',
		            type:'line',
		            stack: '总量',
		            //data:eval('(${ftftList})'),
		            data:[34, 16, 44, 13, 52, 13, 17,45,66,56,87,96],
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            }
		        },
		        {
		            name:'远程课程',
		            type:'line',
		            stack: '总量',
		            //data:eval('(${rlist})'),
		            data:[78, 16, 15, 56, 12, 78, 10,43,66,76,87,36],
		             markPoint : {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            }
		        },
		        {
		            name:'总培训人数',
		            type:'line',
		            stack: '总量',
		           // data:eval('(${sumlist})'),
		           data:[15, 16, 15, 13, 12, 13, 10,45,66,76,87,36],
		             markPoint : {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            }
		        },
		    ]
		};
	myChartMonth.setOption(option);
	function selectYear(values){
		if(values!=""){
	 		jQuery("#content_train_m").load("${ctx}/cms/train/statisticsTrainMonth.do?paramMap[year]="+values);
	 		try{
				jQuery('#'+getCurrentTabId()).panel('refresh','${ctx}/cms/train/statisticsTrainMonth.do?paramMap[year]='+values);
			} catch(e){}
		}
	}
</script>
	 



