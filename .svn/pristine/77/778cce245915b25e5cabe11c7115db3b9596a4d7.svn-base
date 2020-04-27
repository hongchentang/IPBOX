<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>


<!-- echarts图表 -->
	<div  style="height:90%;width: 90%;"   id="statisticsCountRegions">
		
	</div>
	<script type="text/javascript">
	 
	var myChartRegions =echarts.init($("#statisticsCountRegions").get(0), 'macarons');                   
	 var years= new Array();
	  	  years=eval('(${years})');
	 var yearList= new Array();
		 yearList=eval('(${yearList})');  
	  var dictEntryList=new Array();
	  	  dictEntryList=eval('(${dictEntryList})');
	  var dataMap=eval('(${dataMap})');
	  var yearsOne=yearList[0];
	  var yearsTwo=yearList[1];
	  var yearsThe=yearList[2];
	  var yearsTrr=yearList[3];
	  var yearsFir=yearList[4];
	  var regionList=new Array();
	  regionList=eval('(${regionList})');
	  for(var i=0;i<regionList.length;i++){
			if(i%2==0){
				regionList[i]="\n"+regionList[i];
			}
	  }
	  
	var	option = {
 
				    timeline:{
				        data:years,
				        label : {
				            formatter : function(s) {
				                return s.slice(0, 4);
				            }
				        },
				        autoPlay : true,
				        playInterval : 1000
				    },
				    options:[
				        {
				            title : {
				                'text':yearsOne+'选课人次的行业分布情况',
				                'subtext':'区域分布情况',
				                'x':'center',
				            },
				            tooltip : {'trigger':'axis'},
				            legend : {
				                x:'center',
				                y:'50px',
				                'data':dictEntryList,
				            },
				            toolbox : {
				                'show':true, 
				                orient : 'vertical',
				                x: 'right', 
				                y: 'center',
				                'feature':{
				                    'dataView':{'show':true,'readOnly':false},
				                    'magicType':{'show':true,'type':['line','bar']},
				                    'restore':{'show':true},
				                    'saveAsImage':{'show':true}
				                }  
				            },
				            calculable : true,
				            grid : {'y':80,'y2':100},
				            xAxis : [{
				                'type':'category',
				                'axisLabel':{'interval':0},
				                'data':regionList
				            }],
				            yAxis : [
				                {
				                    'type':'value',
				                },
				                {
				                    'type':'value',
				                }
				            ],
				            series : [
									{
									    'name':dictEntryList[0],
									    'type':'bar', 
									    'data': dataMap.dataPublic[yearsOne]
									},
									{
									    'name':dictEntryList[1],'yAxisIndex':1,'type':'bar',
									    'data': dataMap.dataCadre[yearsOne]
									},
									{
									    'name':dictEntryList[2],'yAxisIndex':1,'type':'bar',
									    'data': dataMap.dataTeacher[yearsOne]
									},
									{
									    'name':dictEntryList[3],'yAxisIndex':1,'type':'bar',
									    'data': dataMap.dataServe[yearsOne]
									},
									{
									    'name':dictEntryList[4],'yAxisIndex':1,'type':'bar',
									    'data': dataMap.dataCareer[yearsOne]
									},
									{
									    'name':dictEntryList[5],'yAxisIndex':1,'type':'bar',
									    'data': dataMap.dataManage[yearsOne]
									}				                 
				            ]
				        },
		        {
		            title : {'text':yearsTwo+'选课人次的行业分布情况'},
		            series : [
		                {'data': dataMap.dataPublic[yearsTwo]},
		                {'data': dataMap.dataCadre[yearsTwo]},
		                {'data': dataMap.dataTeacher[yearsTwo]},
		                {'data': dataMap.dataServe[yearsTwo]},
		                {'data': dataMap.dataCareer[yearsTwo]},
		                {'data': dataMap.dataManage[yearsTwo]}
		            ]
		        },
		        {
		        	 title : {'text':yearsThe+'选课人次的行业分布情况'},
			            series : [
			                {'data': dataMap.dataPublic[yearsThe]},
			                {'data': dataMap.dataCadre[yearsThe]},
			                {'data': dataMap.dataTeacher[yearsThe]},
			                {'data': dataMap.dataServe[yearsThe]},
			                {'data': dataMap.dataCareer[yearsThe]},
			                {'data': dataMap.dataManage[yearsThe]}
			            ]
		        },
		        {
		        	title : {'text':yearsTrr+'选课人次的行业分布情况'},
		            series : [
		                {'data': dataMap.dataPublic[yearsTrr]},
		                {'data': dataMap.dataCadre[yearsTrr]},
		                {'data': dataMap.dataTeacher[yearsTrr]},
		                {'data': dataMap.dataServe[yearsTrr]},
		                {'data': dataMap.dataCareer[yearsTrr]},
		                {'data': dataMap.dataManage[yearsTrr]}
		            ]
		        },
		        {
		        	title : {'text':yearsFir+'选课人次的行业分布情况'},
		            series : [
		                {'data': dataMap.dataPublic[yearsFir]},
		                {'data': dataMap.dataCadre[yearsFir]},
		                {'data': dataMap.dataTeacher[yearsFir]},
		                {'data': dataMap.dataServe[yearsFir]},
		                {'data': dataMap.dataCareer[yearsFir]},
		                {'data': dataMap.dataManage[yearsFir]}
		            ]
		        },
		    ]
		};
		                    
		myChartRegions.setOption(option);
</script>
	 



