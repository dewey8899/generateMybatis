<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/4 0004
  Time: 上午 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<h1>欢迎登录</h1>
<form action="/springboot/loginUser" method="post">
    <input type="text" name="username"><br>
    <input type="password" name="password"><br>
    <input type="submit" value="提交">
</form>

</body>
</html>
