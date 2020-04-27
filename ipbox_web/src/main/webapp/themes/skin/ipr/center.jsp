<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/include/taglib.jsp" %>
<script type="text/javascript">
    $(function () {
        $('#layout_center_tabsMenu').menu({
            onClick: function (item) {
                var curTabTitle = $(this).data('tabTitle');
                var type = $(item.target).attr('type');

                if (type === 'refresh') {
                    layout_center_refreshTab(curTabTitle);
                    return;
                }

                if (type === 'close') {
                    var t = $('#layout_center_tabs').tabs('getTab', curTabTitle);
                    if (t.panel('options').closable) {
                        $('#layout_center_tabs').tabs('close', curTabTitle);
                    }
                    return;
                }

                var allTabs = $('#layout_center_tabs').tabs('tabs');
                var closeTabsTitle = [];

                $.each(allTabs, function () {
                    var opt = $(this).panel('options');
                    if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
                        closeTabsTitle.push(opt.title);
                    } else if (opt.closable && type === 'closeAll') {
                        closeTabsTitle.push(opt.title);
                    }
                });

                for (var i = 0; i < closeTabsTitle.length; i++) {
                    $('#layout_center_tabs').tabs('close', closeTabsTitle[i]);
                }
            }
        });

        $('#layout_center_tabs').tabs({
            fit: true,
            border: false,
            tabHeight: '32',
            onContextMenu: function (e, title) {
                e.preventDefault();
                $('#layout_center_tabsMenu').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                }).data('tabTitle', title);
            },
            //onSelect:function(title,index){alert(title+'  '+index+' is selected');},
            tools: [{
                iconCls: 'icon-reload',
                handler: function () {
                    var href = $('#layout_center_tabs').tabs('getSelected').panel('options').href;
                    if (href) {/*说明tab是以href方式引入的目标页面*/
                        var index = $('#layout_center_tabs').tabs('getTabIndex', $('#layout_center_tabs').tabs('getSelected'));
                        $('#layout_center_tabs').tabs('getTab', index).panel('refresh');
                    } else {/*说明tab是以content方式引入的目标页面*/
                        var panel = $('#layout_center_tabs').tabs('getSelected').panel('panel');
                        var frame = panel.find('iframe');
                        try {
                            if (frame.length > 0) {
                                for (var i = 0; i < frame.length; i++) {
                                    frame[i].contentWindow.document.write('');
                                    frame[i].contentWindow.close();
                                    frame[i].src = frame[i].src;
                                }
                                if ($.browser.msie) {
                                    CollectGarbage();
                                }
                            }
                        } catch (e) {
                        }
                    }
                }
            }, {
                iconCls: 'icon-cancel',
                handler: function () {
                    var index = $('#layout_center_tabs').tabs('getTabIndex', $('#layout_center_tabs').tabs('getSelected'));
                    var tab = $('#layout_center_tabs').tabs('getTab', index);
                    if (tab.panel('options').closable) {
                        $('#layout_center_tabs').tabs('close', index);
                    } else {
                        $.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被关闭', 'error');
                    }
                }
            }]
        });

    });

    function layout_center_refreshTab(title) {
        $('#layout_center_tabs').tabs('getTab', title).panel("options").queryParams = {};
        $('#layout_center_tabs').tabs('getTab', title).panel('refresh');
    }

    function layout_center_addTabFun(opts) {
        var t = $('#layout_center_tabs');
        var tabs = t.tabs('tabs');
        //最大标签20个，超过就提示
        if (tabs.length < 20) {
            //判断标题切页
            if (t.tabs('exists', opts.title)) {
                t.tabs('select', opts.title);
            } else {
                t.tabs('add', opts);
            }
            //判断ID切页
            /**
             var isExist=false;
             for(var i=0;i<tabs.length;i++){
				if(opts.id==$(tabs[i]).attr('id')){
					isExist=true;
					t.tabs('select', t.tabs('getTabIndex',tabs[i]));
					break;
				}
			}
             if(!isExist){
				t.tabs('add', opts);
			}
             */
        }
        else {
            $.messager.alert('提示', '已打开20个标签，请先关闭一些标签！');
        }
    }

    /**
     * 返回当前选择页tab的jquery对象
     */
    function getCurrentTab() {
        return $('#layout_center_tabs').tabs('getSelected');
    }

    /**
     * 返回当前选择页tab的Id
     */
    function getCurrentTabId() {
        return getCurrentTab().attr('id');
    }

    /**
     * 返回当前选择页tab中组件id的查询id
     * comId：组件ID（不带#）
     * isWithSharp：返回值是否前面带#，默认false=不带
     * 例如 getCurrentTabComId('#formId')='tabId #formId'
     */
    function getCurrentTabComId(comId, isWithSharp) {
        var tabId = getCurrentTabId();
        if (isWithSharp == true) {
            return '#' + tabId + ' #' + comId;
        }
        else {
            return tabId + ' #' + comId;
        }
    }

    /**
     * 返回当前选择页tabsId中组件id的查询id
     * comId：组件ID（不带#）
     * isWithSharp：返回值是否前面带#，默认false=不带
     * 例如 getCurrentTabComId('#formId')='tabId #formId'
     */
    function getTabsComId(tabsId, comId, isWithSharp) {
        if (isWithSharp == true) {
            return '#' + tabsId + ' #' + comId;
        }
        else {
            return tabsId + ' #' + comId;
        }
    }

    /*
        function datagridRowBg(){
            $('.datagrid-btable').datagrid({
                oddLine: function(index,row){
                    if (row.listprice>80){
                        return 'oddLine';    // rowStyle是一个已经定义了的ClassName(类名)
                    }
                }
            });

        }*/

</script>
<div id="layout_center_tabs" style="">
    <div id="layout_center_tabs_index" title="系统首页" data-options="" style="overflow-x: hidden;background: #f2f6fc"></div>
</div>

<div id="layout_center_tabsMenu" style="width: 120px;display:none;">
    <div type="refresh">刷新</div>
    <div class="menu-sep"></div>
    <div type="close">关闭</div>
    <div type="closeOther">关闭其他</div>
    <div type="closeAll">关闭所有</div>
</div>