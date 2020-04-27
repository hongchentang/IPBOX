<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp" %>
<div class="easyui-panel" style="width:100%">

<div id="listPatsTableSearch" style="margin-left: 5px;margin-top: 10px;">
	<div>
        <span>标题名:</span>
        <input type="text" style="width:200px" id="title" class="easyui-textbox" name="paramMap[title]" value="${searchParam.paramMap.title}" />
        <span>申请号:</span>
        <input type="text" style="width:200px" id="application_number" class="easyui-textbox" name="paramMap[application_number]" value="${searchParam.paramMap.application_number}" />
    	<button id="refreshbtn"  class="easyui-linkbutton main-btn" onclick="showdatagrid()"><i class="fa fa-search"></i> 查询</button>
    </div>
    <br/>
    <div style="margin-bottom: 5px;">
        <c:if test="${roleCode eq 0}">
            <a id="addButton" href="javascript:void(0)" class="easyui-linkbutton" plain="false" > 导入</a>
            <a id="delButton" href="javascript:void(0)" class="easyui-linkbutton delete-btn" plain="false" >删除</a>
        </c:if>
       <a id="editButton" href="javascript:void(0)" class="easyui-linkbutton" plain="false" > 查看</a>
    </div>
</div>
<form id="listPatsForm" action="${ctx}/warehouse/patenthouse/patentList.do?tabId=${param.tabId}" method="post">

<table id="listPatsTable" style="width:99%;height:auto; min-height:400px"   toolbar="#${param['tabId']} listPatsTableSearch" iconCls="fa fa-bell-o" >
</table>
<script type="text/javascript">
$(document).ready(function(){
	fLoadTable();
// 	show();
});

function fLoadTable(){
    $('#listPatsTable').datagrid({
        title: '专利基本信息列表',
        fitColumns: true,
//      pageSize : parseInt('${searchParam.pagination.pageSize}'),
// 		pageNumber : parseInt('${searchParam.pagination.currentPage}'),
// 		total : parseInt('${searchParam.pagination.rowCount}'),
// 		total:120,
//      pageNumber: 1,
		singleSelect: true,
        remoteSort: true, //定义从服务器对数据进行排序。
        pagination: true, //在DataGrid控件底部显示分页工具栏。
        pageSize: 10,
	    pageList: [10,15,20],
        method: 'post',
        rownumbers: true,
        checkbox: true,
        emptyMsg: '列表为空',
        selectOnCheck: true,
        checkOnSelect: true,
        nowrap:false,
        url:"${ctx}/warehouse/patenthouse/patentListData.do",
        queryParams: {title:"",application_number:""},
        onLoadSuccess: function(data){
        },
//对应json数据中的每一列
       columns : [ [ { 
             field : '_id', //每一列的名字
             width : '100', 
             title:'ID',
             checkbox:true 
         },{ 
             field : 'title', //每一列的名字
             width : '250', 
             title:'标题名',
             formatter:function(value, row, index) {
					if (row.title) {
						return row.title.original;
					} else {
						return value;
					}
             }
         },{ 
             field : 'application_number', //每一列的名字
             width : '150',
             align : 'center' ,
             title : '专利申请号'
         },{ 
           field : 'application_date', 
           title : '申请日期',
           align : 'center' , 
           width : '100'
        },{ 
            field : 'type', 
            title : '专利类型',
            align : 'center' ,
            width : '50',
            formatter:function(value, row, index) {
				if (row.type == 1) {
					return "发明";
				} else if(row.type == 2){
					return "新型";
				}else{
					return "外观设计";
				}
         	}
         },{ 
             field : 'main_patent', 
             title : '是否为主专利',
             align : 'center' ,
             width : '100',
             formatter:function(value, row, index) {
 				if (row.main_patent == 0) {
 					return "非主专利";
 				}else{
 					return "主专利";
 				}
          	}
          },{ 
              field : 'applicants', 
              title : '第一申请人',
              align : 'center' ,
              width : '200',
              formatter:function(value, row, index) {
  				if (row.applicants) {
  					return row.applicants[0].name.original;
  				}else{
  					return "";
  				}
           	}
           },{ 
               field : 'inventors', 
               title : '发明人',
               align : 'center' ,
               width : '100',
               formatter:function(value, row, index) {
   				if (row.inventors) {
   					return row.inventors[0].name.original;
   				}else{
   					return "";
   				}
            	}
            },{ 
                field : 'kind', 
                title : '专利种类码',
                align : 'center' ,
                width : '60'
             },{ 
                 field : 'publication_number', 
                 title : '公开号',
                 align : 'center' ,
                 width : '50'
              }       
           
            
    ] ],
 idField:'_id', 
 loadMsg:'Processing, please wait …' 
});
}
function show(){
	$.ajax({
		url:"${ctx}/warehouse/patenthouse/patentListData.do",
		type:"get",
		dataType:"json",
		params:{'paramMap[title]':'ssss','paramMap[application_number]':'sss'},
		success:function(data){
			$('#listPatsTable').datagrid('loadData',data);
		},
		error:function(){
				alert("请检查数据是否正常");
			}
		})		
}

// $('#refreshbtn').click(function() { 
// 	var queryParams = $('#listPatsTable').datagrid('options').queryParams;
// 	queryParams.paramMap[title] = $('#title').val();
// 	queryParams.paramMap[application_number] = $('#application_number').val();
// 	$('#listPatsTable').datagrid('reload');

// 		$("#listPatsTable").datagrid("reload",{'paramMap[title]':'sss','paramMap[application_number]':'ssz'}); 

// 		}); 

function showdatagrid() { 
	var queryParams = $('#listPatsTable').datagrid('options').queryParams;
	alert($('#application_number').val());
	alert($('#title').val());
	queryParams.title = $('#title').val();
	
// 	queryParams.paramMap[application_number] = $('#application_number').val();
	$('#listPatsTable').datagrid('reload',{title:$('#title').val(),application_number:$('#application_number').val()});
// 	alert("ssss")
// 		$("#listPatsTable").datagrid("reload",{'paramMap[title]':'sss','paramMap[application_number]':'ssz'}); 

		} 

	getCurrentTab().find('#editButton').linkbutton({onClick: function(){
		var data=$("#${param['tabId']}").find('#listPatsTable').datagrid('getSelections');
		if(data.length<1){
			$.messager.alert('提示','请选择一条数据！');
			return false;
		}
		openWindow('patentInfoMsgWindow','查看信息','100%','100%',"${ctx}/warehouse/patenthouse/listTabs.do?id="+data[0]._id,true);	
		}
	});

	getCurrentTab().find('#addButton').linkbutton({onClick: function(){
		openWindow('patentInfoMsgWindow','导入文件',800,300,"${ctx}/warehouse/patenthouse/goImport.do",true);	
	}
});

</script>
</form>
</div>
