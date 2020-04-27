<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="dept" style="height:90%;width: 90%" ></div>


<script type="text/javascript">
var myChart = echarts.init(document.getElementById('dept'), 'macarons');
var option = ${option};
myChart.setOption(option);
</script>