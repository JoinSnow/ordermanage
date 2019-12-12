<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/10 0010
  Time: 下午 4:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ajax</title>

</head>
<body>
用户名：<input type="text" name="username" id="username">
密码：<input type="password" name="password" id="password">
<input type="button" value="异步提交练习" id="btn">
</body>
</html>
<script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script>
    $("#btn").click(function () {
        var username=$("#username").val();
        var password=$("#password").val();
        // $.get("/member/test?username="+username+"&password="+password,function (data) {
        //     console.log(data);
        // })
        $.post("/member/test?username="+username+"&password="+password,function (data) {
            console.log(data);
        })
        // $.post("/member/test",{"username":username,"password":password},function (data) {
        //     console.log(data);
        // })
    })
</script>