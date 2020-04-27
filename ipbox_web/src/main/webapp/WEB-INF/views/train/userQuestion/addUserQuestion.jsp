<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<%@ include file="/themes/skin/ipr/inc.jsp" %>
<style type="text/css">
body{
	background-color: white;
}
.td_right{
width: 20%;
font-size:12px;
text-align: right
}
.td_left{
width:80%;
text-align: left
}
.tip{
font-size: 12px;
color:#666666
}

</style>

					
				
	<form id="addUserQuestion"  action="${ctx}/cms/userQuestion/saveUserQuestion.do" method="post">
		<!-- <div id="userRegisterPanel" class="easyui-panel" title="用户注册" > -->
							
		<table style="line-height: 40px;width: 100%">
			<tr>
				<td class="td_right"><span style="color: red">*</span>姓名:</td>
				<td>
					<input type="text" name="name" value="${user.realName}" class="easyui-textbox"  style="width:50%;" required />
				</td>
			</tr>
			<tr>
				<td class="td_right"><span style="color: red">*</span>邮箱:</td>
				<td>
					<input type="text" name="email" value="${user.email}" class="easyui-textbox" style="width:50%;" required data-options="validType:'email'"/>
				</td>
			</tr>
			<tr>
				<td class="td_right"><span style="color: red">*</span>手机号码:</td>
				<td>
					<input type="text" name="phone" value="${user.mobilePhone}" class="easyui-textbox" style="width:50%;" required data-options="validType:'mobilePhone'"/>
				</td>
			</tr>
			<tr>
				<td class="td_right"><span style="color: red">*</span>提出问题:</td>
				<td>
					<textarea name="question" class="easyui-textbox"  style="width:100%;height:100px" required data-options="multiline:true"></textarea>
				</td>
			</tr>
						          
						          
						          
		</table>
						
		<div>
				<center><button  type="button" onclick="userQuestionSubmit()">提交</button></center>
				
		</div>
	</form>
	
	<script type="text/javascript">
		function userQuestionSubmit(){
			if(!$("#addUserQuestion").form("validate")){
				return false;
			}
			
			$.messager.confirm("提示","确定要提交?",function(r){
				if(r){
					$("#addUserQuestion").ajaxSubmit({
						type:"post",
						success:function(data){
							if(data.count>0){
								//alert("发送成功");
								/* $.messager.show({
										title:'我的消息',
										msg:'发送成功',
										showType:'show',
										style:{
											right:'',
											top:document.body.scrollTop+document.documentElement.scrollTop,
											bottom:''
										}
									}); */
								
								parent.window.location.reload();
								var index = parent.layer.getFrameIndex(window.name);
								parent.layer.close(index);
							}else{
								$.messager.show({
									title:'我的消息',
									msg:'发送失败',
									showType:'show',
									style:{
										right:'',
										top:document.body.scrollTop+document.documentElement.scrollTop,
										bottom:''
									}
								});
							}
							
							//关闭layer窗口
							//window.location.href="${ctx}/site/index.do"; 
							
							
							
							
						}
					});
				}
			});
		}
	</script>
	
