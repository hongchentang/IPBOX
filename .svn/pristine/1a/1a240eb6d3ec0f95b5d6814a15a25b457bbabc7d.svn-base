<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<%-- ueditor插件库 --%>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/ueditor.all.min.js"></script>
<%-- 
建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败
这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文
 --%>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
<c:set var="uuid" value="${ipanthercore:uuid()}"/>
<form id="saveNoticeForm" name="saveNoticeForm" method="post" action="${ctx}/common/notice/saveNotice.do">
	<input id="basepath" value="${ctx}" type="hidden"/>
    <input type="hidden" name="notice.id" value="${notice.id}" />
    <table width="100%" border="0" cellspacing="1" cellpadding="1">
      <tr>
        <td width="10%" align="right">标题：</td>
        <td width="90%"><input type="text" id="notice.noticeTitle" name="notice.noticeTitle" class="easyui-textbox easyui-validatebox" data-options="required:true" value="${notice.noticeTitle}" style="width:20%"/></td>
      </tr>
      <tr>
        <td align="right">发布：</td>
        <td> <select name="notice.noticeStatus" class="easyui-validatebox" data-options="required:true,editable:false" style="width:20%">
                    <!--<option value="">请选择</option>-->
                    ${dict:getEntryOptionsSelected("NOTICE_STATUS",notice.noticeStatus) }
                </select>
        </td>
      </tr>
      <tr>
        <td align="right">置顶：</td>
        <td><select name="notice.noticeTop" class="easyui-validatebox" data-options="required:true,editable:false" style="width:20%">
                    <!--<option value="">请选择</option>-->
                    ${dict:getEntryOptionsSelected("NOTICE_TOP",notice.noticeTop) }
                </select>
        </td>
      </tr>
      <tr>
        <td align="right">有效时间：</td>
        <td>开始:     <input class="Wdate" id="requestParam_startTime" name="notice.startTime" 
                        onFocus="var startTime=$dp.$('#requestParam_startTime');WdatePicker({onpicked:function(){requestParam_endTime.focus();},maxDate:'#F{$dp.$D(\'requestParam_startTime\')}'})" 
                        value="${notice.startTime}"  type="text" style="width:20%"/>
                        </button>
            结束:
                    <input class="Wdate" id="requestParam_endTime" type="text" name="notice.endTime"
                     onFocus="WdatePicker({minDate:'#F{$dp.$D(\'requestParam_startTime\')}'})" 
                     value="${notice.endTime}" style="width:20%"/></td>
      </tr>
      <tr>
        <td align="right">内容：</td>
        <td>
            <script id="notice_noticeContent" name="notice.noticeContent" style="width:100%"></script>
        </td>
      </tr>
      <tr>
        <td colspan="2" align="center">
        	<button type="button" class="easyui-linkbutton" onclick="submitThisForm_notice()">确定</button>
       	  	<button type="button" class="easyui-linkbutton" onclick="closeThisWindow()">关闭</button>
        </td>
      </tr>
    </table>
</form>
<script type="text/javascript">
	
	$(document).ready(function(e) {
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		var editor=UE.getEditor('notice_noticeContent',{
			 initialFrameHeight:180,
			 initialContent:'${notice.noticeContent==null?"请输入内容":notice.noticeContent}',
			  toolbars:window.UEDITOR_CONFIG.toolbars_notice,
		});
		editor.addListener('contentchange',function(){
			this.sync();
		});
    });
	
	function submitThisForm_notice(){
		$.messager.confirm('确认','确认保存吗？',function(result){    
			if (result){  
				$('#listNoticeWindow #saveNoticeForm').form('submit',{
					onSubmit: function(){
						var isValid = $(this).form('validate');
						if (!isValid){
							$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
							$.messager.alert('提示','请填写必填项！');
						}
						return isValid;	// 返回false终止表单提交  
					},
					success: function(data){
						var json=jQuery.parseJSON(data);
						if(!jQuery.isEmptyObject(json)){
							var message = json.message;
							var statusCode = json.statusCode;
							if(parseInt(statusCode)==300){
								$.messager.alert('提示',message);
							}else if(parseInt(statusCode)==200){
								closeWindow('listNoticeWindow');
								getCurrentTab().panel('refresh');
								$.messager.alert('提示',message);
							}
						}else{
							$.messager.alert('提示','json is null');
						}
					}
				});
			}
		});
	}
	
	function closeThisWindow(){
		closeWindow('listNoticeWindow');
	}
</script>