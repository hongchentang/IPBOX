<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<script type="text/javascript" src="/js/echarts/echarts.min.js"></script>


<!-- echarts图表 -->
<!-- 	<div style="text-align: center;">
		<b>统计纬度:</b>
		<select id="cur_year" onchange="selectType(this.value)">
		<option value="">--请选择--</option>
		<option value="01" selected="selected">年度</option>
		<option value="02">人才类型</option>
		<option value="03">行业分类</option>

		</select>
	</div> -->

	<div  style="height:90%"  id="statisticsTrainType02">
	
	</div>
	<script type="text/javascript">
 	echarts.init($("#statisticsTrainType02").get(0),'macarons').setOption(${optionPerson});
 	function selectType(a){
		if(a=='01'){
			echarts.init($("#statisticsTrainType").get(0),'macarons').setOption(${optionYear});
		}
		if(a=='02'){
			echarts.init($("#statisticsTrainType").get(0),'macarons').setOption(${optionPerson});
		}
		if(a=='03'){
			echarts.init($("#statisticsTrainType").get(0),'macarons').setOption(${optionIndustry});
		}
		
	}  
	
</script>

