<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<!-- echarts图表 -->
	<div  style="height:400px"  id="statisticsCountRegister">
		
	</div>
	<script type="text/javascript">
	var myChartRegister =echarts.init($("#statisticsCountRegister").get(0), 'macarons');
	var option={
			 //backgroundColor:"#188DD2",
			 title:{text:"${currentYear}年月度注册用户数据、选课人次变化",
				   x: "center"
				  },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		    	x: 'left',
		        data:['注册用户','选课人次'],
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
		            name:'注册用户',
		            type:'line',
		            stack: '总量',
		            //data:eval('(${userList})'),
		            data:[34, 16, 44, 93, 52, 78, 17,45,66,56,105,96],
		            markPoint : {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            }
		        },
		        {
		            name:'选课人次',
		            type:'line',
		            stack: '总量',
		           // data:eval('(${countlist})'),
		           data:[74, 10, 44, 93, 42, 78, 17,65,66,56,105,120],
		             markPoint : {
		                data : [
		                    {type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            }
		        },
		    ]
		};
	myChartRegister.setOption(option);
</script>
	 



