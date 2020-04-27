<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp" %>
<form id="bindWeChatForm" name="bindWeChatForm" class="form-horizontal" role="form" action="${ctx}/common/user/bindWeChat.do" method="post">
   <table align="center" class="alter-table-h" style="line-height: 40px;width: 90%">
       <tr>
           <td>微信名</td>
           <td>申请时间</td>
           <td>状态</td>
           <td>操作</td>
       </tr>
        <c:forEach var="wxUser" items="${list}">
            <c:if test="${ not empty wxUser.id}">
                <tr>
                    <td>${wxUser.nickName}</td>
                    <td>${wxUser.createDate}</td>
                    <td>
                        <c:if test="${wxUser.status eq 1}">
                            待审核
                        </c:if>
                        <c:if test="${wxUser.status eq 2}">
                            已通过
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${wxUser.status eq 1}">
                            <a style="color: blue" onclick="bindWx(this)" id="${wxUser.id}" href="javascript: void(0);">绑定</a>
                        </c:if>
                        <c:if test="${wxUser.status eq 2}">
                            <a style="color: blue" onclick="bindWx(this)" id="${wxUser.id}" href="javascript: void(0);">取消绑定</a>
                        </c:if>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
  </table>
</form>
<script type="text/javascript">
    function bindWx(ele) {
        var id = ele.getAttribute('id');
        $.ajax({
            url: '${ctx}/common/user/bindOrUnBind.do?id=' + id,
            method: 'get',
            success: function(data){
                var code = data.code;
                if(code == 200){
                    alert("成功");
                    $('#bindWeChat').window('refresh', '${ctx }/common/user/bindWeChat.do');
                }else {
                    alert("出错了：" + data.msg)
                }

            },
            error: function(data){
                alert("出错了,系统错误");
            }
        })
    }
</script>