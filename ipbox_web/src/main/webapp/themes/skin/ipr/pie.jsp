<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<html style="height: 100%">
   <head>
       <meta charset="utf-8">
   </head>
   <body style="height: 100%; margin: 0">
       <div id="container" style="height: 100%"></div>
<!-- <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.js"></script> -->

<!-- <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script> -->
<script type="text/javascript" src="/js/echarts/echarts.min.js"></script>

       <script type="text/javascript">
var zhu = ['10000','119','110'];
var officialfees="";
var yersfees="";
var elsefees="";
$.ajax({
    url: "${ctx }/intellectual/call/getindexCost.do",
    type: 'post',
    async: false,
//		data:"${formId}".serialize(),
    success: function (data) {

        var json = data;
        officialfees=json[1];
        yersfees=json[0];
        elsefees=json[2];
       
        

    }
});
var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
option = null; 
option = {
    title : {
   /*      text: '专利费用统计', */
        /*   subtext: '纯属虚构', */
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        x: 'left', 
        y: '10px', 
        data: ['官费','年费','其他费']
    },
 	toolbox: {
 	      
        feature : {
     
         
            saveAsImage : {show: true}
        }
    }, 
    series : [
        {
            name: '费用类型',
            type: 'pie',
            color:['#ADD6F2','#FE7B4D','#FDAD4E'],
            radius: ['50%', '80%'],
             hoverAnimation:true,
            center: ['50%', '59%'],
            data: (function(){
            	 
                var res = [];
                var len = 0;
                var vue="";
                while (len++ < 3) {
                var name="";
                if(len==1){
                	name="官费";
                	vue=officialfees;
                }else if(len==2){
                	name="年费";
                	vue=yersfees;
                }else{
                	name="其他费";
                	vue=elsefees;
                }
                res.push({
                name:name ,
                value: vue
                });
                }
                return res;
                })(),
            itemStyle: {
                emphasis: {     
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
  
    
}
       </script>
   </body>
</html>