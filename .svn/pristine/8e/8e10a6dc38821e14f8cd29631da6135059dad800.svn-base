<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<div class="easyui-panel" title="功能列表" id="moduleList">
    <div class="datagrid-toolbar">
		<table cellspacing="0"  cellpadding="0">
			<tbody>
				<tr> 
					<%--<td>&nbsp;&nbsp;&nbsp;&nbsp;模块:&nbsp;&nbsp;</td>--%>
					<td style="display: none;">
						<select id="moduleSelect" style="width: 100px;">
					 	</select>
					</td>
					<td><div class="datagrid-btn-separator"></div></td>
					<%--<td><a onclick="editDialogShow('${ctx}/common/security/module/add.do','新增模块','')" href="javascript:void(0)" class="easyui-linkbutton"><span class="fa fa-plus"></span> 新增模块</a></td>--%>
					<td><a onclick="javascript:getCurrentTab().find('#editTree').panel('refresh','${ctx}/common/security/privilege/add.do');" class="easyui-linkbutton"><span class="fa fa-plus"></span> 新增功能</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
<%--					<td><a onclick="editDialogShow('${ctx}/common/security/module/goEdit.do?module.id=','编辑模块','edit')" href="javascript:void(0)" class="easyui-linkbutton"><span class="fa fa-pencil"></span> 编辑模块</a></td>--%>
<%--					<td><div class="datagrid-btn-separator"></div></td>--%>
					<%--<td><a onclick="javascript:deleteModule()" href="javascript:void(0)" class="easyui-linkbutton delete-btn"><span class="fa fa-times"></span> 删除</a></td>--%>
					<td><a onclick="javascript:deleteTrre()" class="easyui-linkbutton delete-btn"><span class="fa fa-times"></span> 删除功能</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
				</tr>
			</tbody>
		</table>
	</div>

	<div id="listPage" class="easyui-layout" data-options="fit:true"  style="width:100%;height:393px;overflow:hidden;margin:0;padding:0">   
            <div id="listTreeDiv_toolbar">
			<a href="javascript:void(0)" class="easyui-menubutton"
        	data-options="menu:'#menuDiv',height:'15',width:'30'" style="margin-right: 10px; width: 100%; height: 100%;"><i class="fa fa-chevron-circle-down fa-1x" style="margin-left: 5px;"></i></a>
			<div id="menuDiv" style="width:150px;">   
				<div title="功能新增" iconCls="fa fa-plus" onclick="javascript:getCurrentTab().find('#editTree').panel('refresh','${ctx}/common/security/privilege/add.do');" > 功能新增</div>
				<div title="功能删除" iconCls="fa fa-times" onclick="javascript:deleteTrre()">功能删除</div>
			</div> 
		    </div>
            <div class="easyui-panel" data-options="region:'west',title:'功能树',collapsible:false,tools:'#listTreeDiv_toolbar'" style="width:25%;height:auto;"  id="listTreeDiv">
                <ul id="listTree" style="height:auto">
            	</ul>	
            </div>
            <div  class="asyui-panel" data-options="region:'center',title:'功能管理'" style="width:20%;height:330px;"  id="editTree">
            	<h1 style="margin-top: 100px;margin-left: 40%">请点击左边的树进行操作.</h1>
            </div>
	</div>
</div>
<!-- <div id="editDialog"  title="新增模块" style="width:400px;height:200px;"  data-options="iconCls:'icon-save',resizable:true,modal:true">   
</div> -->

<script type="text/javascript">
	getCurrentTab().find('#listPage').layout();
	getCurrentTab().find('#moduleSelect').combobox({
    	valueField:'id',
    	textField:'name',
    	url:'${ctx}/common/security/privilege/listJson.do',
        onSelect: function(node){
        	getTrre(node.id);
    	},
    	onLoadSuccess:function(){
			var datas=$(this).combobox("getData");
			if(datas.length>0){
				var moduleId = datas[0].id;
				$(this).combobox("select",moduleId);
    			getTrre(moduleId);
			}
        }
    });
  	
	function editDialogShow(url,title,type){
		if(type=="edit"){
			url=url+getCurrentTab().find('#moduleSelect').combobox("getValue");
		}

		easyuiUtils.openWindow('editDialog',title,400,260,url,true);
	/* 	getCurrentTab().find('#editDialog').dialog({    
		    title: title,    
		    width: 400,    
		    height: 290,    
		    closed: false,    
		    cache: false,    
		    href: url,    
		    modal: true   
		});   */  
	} 

    function deleteModule(){
    	jQuery.messager.confirm("提示信息","确定删除当前模块?",function(isReturn){
        	if(isReturn){
        		var data=jQuery.ajaxSubmitValue("${ctx}/common/security/module/delete.do?module.id="+getCurrentTab().find('#moduleSelect').combobox("getValue"));
        		var json=jQuery.parseJSON(data);
    			if(json){
    				var message = json.message;
    				var statusCode = json.statusCode;
    				if(parseInt(statusCode)==300){
    					jQuery.messager.alert("提示信息",message,function(){});
    				}else if(parseInt(statusCode)==200){
    					jQuery.messager.alert("提示信息",message,function(){});
    					getCurrentTab().find('#moduleSelect').combobox('reload');
    					getCurrentTab().find('#moduleSelect').combobox('setValue',"请选择");
    				}
    			}else{
    				console.error("json is null");
    			}
            }
        });
    }

   function getTrre(moduleId){
	   getCurrentTab().find('#listTree').tree({
	         url:'${ctx}/common/security/privilege/priByModule.do?module.id='+moduleId ,
	         id:'id',
	         text:'text',
	         parentField:'pid',
	         onSelect:function(node){
	        	var module= jQuery('#moduleSelect').combobox("getValue");
	         	if(module){
	         		getCurrentTab().find("#editTree").panel('refresh','${ctx}/common/security/privilege/edit.do?privilege.id='+node.id);
		         }
		     }
	   });
   }

  function deleteTrre(){
	   var node = getCurrentTab().find('#listTree').tree('getSelected');
	   if(node){
			jQuery.messager.confirm("提示信息","确定删除  "+node.text+" ?",function(isReturn){
	        	if(isReturn){
	        		var data=jQuery.ajaxSubmitValue("${ctx}/common/security/privilege/delete.do?privilege.id="+node.id);
	        		var json=jQuery.parseJSON(data);
	    			if(!jQuery.isEmptyObject(json)){
	    				var message = json.message;
	    				var statusCode = json.statusCode;
	    				if(parseInt(statusCode)==300){
	    					jQuery.messager.alert("提示信息",message,function(){});
	    				}else if(parseInt(statusCode)==200){
	    					jQuery.messager.alert("提示信息",message,function(){});
	    					getCurrentTab().find('#listTree').tree('reload');
	    					getCurrentTab().find('#editTree').html('<h1 style="margin-top: 100px;margin-left: 40%">请点击左边的树进行操作.</h1>');
	    				}
	    			}else{
	    				console.error("json is null");
	    			}
	        	}
	         });
	   }else{
		   jQuery.messager.alert("提示信息","请先选择一条数据",function(){});
	   }
  }
   
</script>
<!--  $(document).ready(function (){ -->
<!-- /* 	$("#hiddenBnt").hide(); -->
<%-- 	if("${not empty module }"=="true"){ --%>
<!-- 		submodular(); -->
<!-- 	} */ -->
<!-- }); -->

<!--  /* //新增修改模块 -->
<!--  function showAddView(url,titleName){ -->
<!-- 	 $("#titleName").text(titleName); -->
<!-- 	 var id='?id='+$("#moduleSelect").val(); -->
<!-- 	 var data= jQuery.ajaxSubmitValue(url+=id); -->
<!-- 	  $("#viewOpen").html(data); -->
<!-- 	  $('#myModal').modal(); -->
<!-- } -->
<!--  //删除模块 -->
<!-- function deleteModule(){ -->
<!-- 	 var id='?id='+$("#moduleSelect").val(); -->
<%-- 	jQuery.confirmPost('是否确认删除?','${ctx}/common/security/module/delete.do'+id,'content','${ctx}/common/security/privilege/list.do'); --%>
<!-- } -->
<!--  //改变选择模块事件 -->
<!--  function changeModule(obj){ -->
<!-- 	 var module=$(obj); -->
<!-- 	 if(module.val()==''){ -->
<!-- 		$("#hiddenBnt").hide(); -->
<!-- 		var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo"); -->
<!-- 		zTreeObj.destroy(); -->
<!-- 		jQuery('#contentTree').html("<p class='lead text-info'>请选择模块,点击左边菜单树进行操作</p>"); -->
<!-- 	 }else{ -->
<!-- 		 $("#hiddenBnt").show() -->
<!-- 		changeTreeDemo(module); -->
<!-- 		 jQuery('#contentTree').html("<p class='lead text-info'>请选择模块,点击左边菜单树进行操作</p>"); -->
<!-- 	 } -->
<!--  } -->
<!--  //子模块显示的菜单 -->
<!--  function submodular(){ -->
<!-- 	 var module=$("#moduleSelect"); -->
<!-- 	 changeTreeDemo(module); -->
<!-- 	 jQuery('#contentTree').html("<p class='lead text-info'>请点击左边菜单树进行操作</p>"); -->
<!--  } -->
<!--  //根据模块ID改变tree -->
<!--  function changeTreeDemo(module){ -->
<!-- /* 	 $.ajax({ -->
<!-- 			url : '/common/security/privilege/priByModule.do?id='+module.val(), -->
<!-- 			data : '', -->
<!-- 			type : "post", -->
<!-- 			success : function(data) { -->
<!-- 				var json=eval(data); -->
<!-- 				$.fn.zTree.init($("#treeDemo"), setting, json); -->
<!-- 			} -->
<!-- 		}); */ -->
<!--  } -->
 
 
<!-- //提示框 -->
<!--  function myAlert(obj){ -->
<!-- 	 bootbox.dialog({ -->
<!-- 		 message: "<p class='text-warning'>"+obj+"</p>", -->
<!--             buttons: { -->
<!--               success: { -->
<!--                 label: "关闭", -->
<!--                 className: "red" -->
<!--               } -->
<!-- 	    } -->
<!-- 	 }) -->
<!--  } */ -->

<!-- 	/* 	var setting = { -->
<!-- 			data: { -->
<!-- 				key: { -->
<!-- 					title:"t" -->
<!-- 				}, -->
<!-- 				simpleData: { -->
<!-- 					enable: true -->
<!-- 				} -->
<!-- 			}, -->
<!-- 			callback: { -->
<!-- 				beforeClick: beforeClick, -->
<!-- 				onClick: onClick -->
<!-- 			} -->
<!-- 		}; -->

<%-- 		//var zNodes =${viewStr}; --%>

<!-- 		var log, className = "dark"; -->
<!-- 		function beforeClick(treeId, treeNode, clickFlag) { -->
			
<!-- 			return (treeNode.click != false); -->
<!-- 		} */ -->
<!-- 		/** -->
<!-- 			树的左击点击事件 -->
<!-- 		**/ -->
<!-- 		/* function onClick(event, treeId, treeNode, clickFlag) { -->
<!-- 			if(treeNode.id!='0'){ -->
<%-- 				jQuery('#contentTree').load('${ctx}/common/security/privilege/edit.do?id='+treeNode.id); --%>
<!-- 			} -->
<!-- 		}	 -->
<!-- 		/** -->
<!-- 		 节点编辑 -->
<!-- 		**/ -->
<!-- // 		function editNode(){ -->
<!-- // 			treeObj = $.fn.zTree.getZTreeObj("treeDemo"); -->
<!-- // 	    	var nodes = treeObj.getSelectedNodes(); -->
<!-- // 			if(nodes[0].id!='0'){ -->
<%-- // 				jQuery('#contentTree').load('${ctx}/common/security/privilege/edit.do?id='+nodes[0].id); --%>
<!-- // 			} -->
<!-- // 		}  -->
	
<!-- 		/** -->
<!-- 		创建菜单 -->
<!-- 	**/ -->
<!-- 		/* $(document).ready(function(){ -->
<!-- 			$.fn.zTree.init($("#treeDemo"), setting, zNodes); -->
<!-- 			zTree = $.fn.zTree.getZTreeObj("treeDemo"); -->
<!-- 			rMenu = $("#rMenu"); -->
<!-- 		}); */  -->
		
<!-- 		/** -->
<!-- 		新增菜单 -->
<!-- 	**/ -->
<!-- 		 function addTreeNode(){  -->
<!-- 		    	treeObj = $.fn.zTree.getZTreeObj("treeDemo"); -->
<!-- 		    	var nodes = treeObj.getSelectedNodes(); -->
<!-- 		    	if(nodes.length==0){ -->
<!-- 		    		myAlert("请点击功能模块树，选择新增功能的所属节点！"); -->
<!-- 		    	}else if(nodes.length>1){ -->
<!-- 		    		myAlert("只能选择一个节点新增所属功能！"); -->
<!-- 		    	}else{ -->
<%-- 		    		jQuery('#contentTree').load('${ctx}/common/security/privilege/add.do'); --%>
<!-- 		    	} -->
		    	
<!-- 		    } -->
