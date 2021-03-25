<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>猫的信息</title>
</head>
<body>
<table width="400px" border=1 align="center">
    <caption>
        <font size="5">我的猫</font>
    </caption>
    <tr>
        <th>名称</th>
        <th>颜色</th>
        <th>类型</th>
    </tr>
    <tr>
        <td>${dog.name }</td>
        <td>${dog.color }</td>
        <td>${dog.type }</td>
    </tr>
</table>
<div style="text-align: center;"><a
        href="${pageContext.request.contextPath }/getAllUsers.do">返回用户列表</a></div>
</body>
</html>