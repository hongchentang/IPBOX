<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/jsp/common/include/taglib.jsp" %>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<html style="height: 100%">
<head>
    <meta charset="utf-8">
</head>
<body style="height: 100%; margin: 0">
<div id="container" style="height: 100%"></div>
<script type="text/javascript" src="/js/echarts/echarts.min.js"></script>
<script type="text/javascript">
    var xdata = [];
    var invent = [];
    var appearance = [];
    var utility = [];
    $.ajax({
        url: "${ctx }/intellectual/call/patentType.do",
        type: 'post',
        async: false,
//			data:"${formId}".serialize(),
        success: function (data) {

            var json = data;

            if (json != null && json.length > 0) {
                for (var i = 0; i < json.length; i++) {
                    invent.push((json[i].invent));        //挨个取出温度、湿度、压强等值并填入前面声明的温度、湿度、压强等数组

                    appearance.push((json[i].appearance));
                    utility.push((json[i].utility));
                    xdata.push([(json[i].year)]);


                }

            }
        }
    });

    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;


    app.title = '坐标轴刻度与标签对齐';

    option = {
        title: {
            /*   text: '专利统计', */

        },
        legend: {

            itemWidth: 10,// 标志图形的长度
            itemHeight: 10,// 标志图形的宽度

            x: 'left',
            y: '10px',

            data: ['发明', '实用新型', '外观设计']
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // åæ è½´æç¤ºå¨ï¼åæ è½´è§¦åææ
                type: 'shadow'        // é»è®¤ä¸ºç´çº¿ï¼å¯éä¸ºï¼'line' | 'shadow'
            }
        },
        toolbox: {

            feature: {
                magicType: {show: true, type: ['line', 'bar', 'stack']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        grid: {
            left: '3%',
            right: '0%',
            bottom: '3%',
            containLabel: true
        },

        xAxis:
            {
                type: 'category',
                data: [],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ,

        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [

            {
                name: '发明',
                type: 'bar',
                barWidth: '20%',
                color: ["#ADD6F2"],
                data: [10, 52, 200, 334, 390, 330, 220]
            },
            {
                name: '实用新型',
                type: 'bar',
                color: ["#FE7B4D"],
                barWidth: '20%',
                data: [10, 52, 200, 334, 390, 330, 220]
            }, {
                name: '外观设计',
                type: 'bar',
                color: ["#FDAD4E"],
                barWidth: '20%',
                data: [10, 52, 200, 334, 390, 330, 220]
            }
        ]
    };

    if (option && typeof option === "object") {

        option.xAxis.data = xdata;
        option.series[0].data = invent;
        option.series[1].data = appearance;
        option.series[2].data = utility;
        myChart.setOption(option, true);
    }
</script>
</body>
</html>