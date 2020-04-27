<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<!-- echarts图表 -->
	<div  style="height:90%;height: 90%"  id="statisticsTrainYear">
		
	</div>
	<script type="text/javascript">
	var myChart2 =echarts.init($("#statisticsTrainYear").get(0), 'macarons');
	var option={
			title:{text:"专利总体费用统计",
				   x: "center"
				  },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		    	x: 'left',
		        data:['专利年费','专利其他官费','其他费'],
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
		            data : eval('(${years})')
		        }                   
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
					name:'其他费',
					type:'line',
					stack: '总量',
					data:eval('(${otherfee})')

		
		        },
		        {
		            name:'专利其他官费',
		            type:'line',
		            stack: '总量',
		            data:eval('(${officialfees})')
		        },

		        {
					name:'专利年费',
					type:'line',
					stack: '总量',
					data:eval('(${agency})')
		        },
		    ]
		};
	myChart2.setOption(option);
</script>
	 



