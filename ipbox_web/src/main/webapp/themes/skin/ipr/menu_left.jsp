<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<span class="left-drag"></span>
<div class="easyui-accordion easyui-left-tree easyui-relative" data-options="fit:true,border:false">
	<div data-options="border:false" style="width: 100%; min-height:100%">
		<ul id="layout_west_tree" class="ztree" style="padding: 5px 0 0"></ul>
		<%--<div title="树形系统菜单" data-options="isonCls:'icon-save',tools : [ {
					iconCls : 'icon-reload',
					handler : function() {
						$('#layout_west_tree').tree('reload');
					}
				}, {
					iconCls : 'icon-redo',
					handler : function() {
						var node = $('#layout_west_tree').tree('getSelected');
						if (node) {
							$('#layout_west_tree').tree('expandAll', node.target);
						} else {
							$('#layout_west_tree').tree('expandAll');
						}
					}
				}, {
					iconCls : 'icon-undo',
					handler : function() {
						var node = $('#layout_west_tree').tree('getSelected');
						if (node) {
							$('#layout_west_tree').tree('collapseAll', node.target);
						} else {
							$('#layout_west_tree').tree('collapseAll');
						}
					}
				} ]">
		</div>
		<div title="普通系统菜单" data-options="iconCls:'icon-reload'">

		</div>--%>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(e) {
	createMenuTree("IPBOX-ALL");
});

function createMenuTree(moduleId){

	//ztree
	var setting = {
		async: {
			enable: true,
			url: '${ctx}/themes/skin/ipr/menu_tree_json.jsp?moduleId='+moduleId,
			dataFilter: zTreeAjaxDataFilter,
		},
		data: {
			key: {
				name: "text",
			},
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pid",
				rootPId: null
			}
		},
		callback: {
			onClick: zTreeOnClick,
			onExpand: zTreeOnExpand,
			onCollapse: zTreeOnCollapse,
			beforeExpand: zTreeBeforeExpand,
		},
		view: {
			dblClickExpand: false,
			showLine: false
		}
	};

	$.fn.zTree.init($("#layout_west_tree"), setting);

	var curExpandNode = null;

	function zTreeBeforeExpand(treeId, treeNode) {
		var pNode = curExpandNode ? curExpandNode.getParentNode():null;
		var treeNodeP = treeNode.parentTId ? treeNode.getParentNode():null;
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		for(var i=0, l=!treeNodeP ? 0:treeNodeP.children.length; i<l; i++ ) {
			if (treeNode !== treeNodeP.children[i]) {
				zTree.expandNode(treeNodeP.children[i], false);
				$('#'+treeNodeP.children[i].tId).removeClass('ztree-open');
			}
		}
		while (pNode) {
			if (pNode === treeNode) {
				break;
			}
			pNode = pNode.getParentNode();
		}
		if (!pNode) {
			zTreeSinglePath(treeId,treeNode);
		}
	}

	function zTreeSinglePath(treeId,newNode) {
		if (newNode === curExpandNode) return;

		var zTree = $.fn.zTree.getZTreeObj(treeId),
				rootNodes, tmpRoot, tmpTId, i, j, n;

		if (!curExpandNode) {
			tmpRoot = newNode;
			while (tmpRoot) {
				tmpTId = tmpRoot.tId;
				tmpRoot = tmpRoot.getParentNode();
			}
			rootNodes = zTree.getNodes();
			for (i=0, j=rootNodes.length; i<j; i++) {
				n = rootNodes[i];
				if (n.tId != tmpTId) {
					zTree.expandNode(n, false);
					$('#'+n.tId).removeClass('ztree-open');
				}
			}
		} else if (curExpandNode && curExpandNode.open) {
			if (newNode.parentTId === curExpandNode.parentTId) {
				zTree.expandNode(curExpandNode, false);
				$('#'+curExpandNode.tId).removeClass('ztree-open');
			} else {
				var newParents = [];
				while (newNode) {
					newNode = newNode.getParentNode();
					if (newNode === curExpandNode) {
						newParents = null;
						break;
					} else if (newNode) {
						newParents.push(newNode);
					}
				}
				if (newParents!=null) {
					var oldNode = curExpandNode;
					var oldParents = [];
					while (oldNode) {
						oldNode = oldNode.getParentNode();
						if (oldNode) {
							oldParents.push(oldNode);
						}
					}
					if (newParents.length>0) {
						zTree.expandNode(oldParents[Math.abs(oldParents.length-newParents.length)-1], false);
						$('#'+oldParents[Math.abs(oldParents.length-newParents.length)-1].tId).removeClass('ztree-open');
					} else {
						zTree.expandNode(oldParents[oldParents.length-1], false);
						$('#'+oldParents[oldParents.length-1].tId).removeClass('ztree-open');
					}
				}
			}
		}
		curExpandNode = newNode;
	}

	function zTreeOnExpand(event, treeId, treeNode) {
		$('#'+treeNode.tId).addClass('ztree-open');
		curExpandNode = treeNode;
	};

	function zTreeOnCollapse(event, treeId, treeNode) {
		$('#'+treeNode.tId).removeClass('ztree-open');
	};

	function zTreeOnClick(event, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.expandNode(treeNode, null, null, null, true);
		var url;
		var node=treeNode;
		if (node.attributes.url) {
			if(node.attributes.url.indexOf('?')>=0){
				url = '${ctx}' + node.attributes.url + '&tabId=' + node.id;
			}
			else{
				url = '${ctx}' + node.attributes.url + '?tabId=' + node.id;
			}
		} else {
			url = '';
			//url = '${ctx}/jsp/common/404.jsp';
		}
		if (url.indexOf('druidController') > -1) {/*要查看连接池监控页面*/
			layout_center_addTabFun({
				title : node.text,
				closable : true,
				iconCls : node.iconCls,
				//cache : false,
				content : '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99%;"></iframe>'
			});
		}
		else if (url==''){

		}
		else {
			layout_center_addTabFun({
				id : node.id,
				title : node.text,
				closable : true,
				iconCls : node.iconCls,
				//cache : false,
				href : url,
				queryParams:{},
				//content: '<iframe src="' + url + '" width="100%" height="100%" frameborder="0"></iframe>',
			});
		}
	};


	function zTreeAjaxDataFilter(treeId, parentNode, responseData) {
		if (responseData) {
		  for(var i =0; i < responseData.length; i++) {
			  //if(responseData[i].state=="open"){
			  	//responseData[i].open=true;
			  //}
			  //console.log(responseData[i].attributes.url);
		  }
		}
		return responseData;
	};


	//easyui tree
	<%--
	$('#layout_west_tree').tree({
		url : '${ctx}/themes/skin/ipr/menu_tree_json.jsp?moduleId='+moduleId,
		/*data : json.TMS,*/
		parentField : 'pid',
		lines : true,
		onClick : function(node) {
			var url;
			if (node.attributes.url) {
				if(node.attributes.url.indexOf('?')>=0){
					url = '${ctx}' + node.attributes.url + '&tabId=' + node.id;
				}
				else{
					url = '${ctx}' + node.attributes.url + '?tabId=' + node.id;
				}
			} else {
				url = '';
				//url = '${ctx}/jsp/common/404.jsp';
			}
			if (url.indexOf('druidController') > -1) {/*要查看连接池监控页面*/
				layout_center_addTabFun({
					title : node.text,
					closable : true,
					iconCls : node.iconCls,
					//cache : false,
					content : '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99%;"></iframe>'
				});
			}
			else if (url==''){

			}
			else {
				layout_center_addTabFun({
					id : node.id,
					title : node.text,
					closable : true,
					iconCls : node.iconCls,
					//cache : false,
					href : url,
					queryParams:{},
					//content: '<iframe src="' + url + '" width="100%" height="100%" frameborder="0"></iframe>',
				});
			}

		},
		onLoadSuccess:function(node, data){
			treeFirstStyle();
		}
	});
	--%>


}

function treeFirstStyle(){
	var nodes=$('#layout_west_tree').tree('getRoots');
	for(var i=0; i<nodes.length; i++){
	  $(nodes[i].target).addClass('tree-first-line');
	}
}
</script>