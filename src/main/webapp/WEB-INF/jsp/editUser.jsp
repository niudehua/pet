<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改用户</title>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/static/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/static/js/jquery.form.min.js"></script>
    <script type="text/javascript">
        function uploadImg() {
            //图片上传的操作
            var opts = {
                url: "${pageContext.request.contextPath}/uploadImg.do",
                type: "post",
                dataType: "json",
                success: function (data) {
                    //返回图片存储在服务器的路径来进行回显
                    $("#allimg").attr("src", data.path);
                    //把路径存在把隐藏域里面保存到数据库
                    $("#himg").val(data.path);
                }
            };
            //提交表单
            $("#addForm").ajaxSubmit(opts);
        }
    </script>
</head>
<body>
<form id="addForm" action="${pageContext.request.contextPath }/updateUser.do" method="post"
      enctype="multipart/form-data">
    <input type="hidden" name="id" value="${user.id }"/>
    <table width="500px" border=1 align="center">
        <caption>
            <font size="5">添加用户</font>
        </caption>
        <tr>
            <td>头像</td>
            <td><img width="100" height="100" id="allimg"
                     src="${pageContext.request.contextPath}/${user.photo}"/>
                <input type="file" name="picfile" onchange="uploadImg();"/>
                <input type="hidden" name="photo" id="himg"/> <!-- 回显图片的路径 --></td>
        </tr>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username" value="${user.username}"/></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password" value="${user.password}"/></td>
        </tr>
        <tr>
            <td>昵称</td>
            <td><input type="text" name="nickname" value="${user.nickname}"/></td>
        </tr>
        <tr>
            <td>生日</td>
            <td><input type="text" name="birthday"
                       value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" />"/>
            </td>
        </tr>
        <tr>
            <td>电话</td>
            <td><input type="text" name="tel" value="${user.tel}"/></td>
        </tr>
        <tr>
            <td>性别</td>
            <td><select name="sex">
                <option value="1" <c:if test="${user.sex=='1'}">selected="selected"</c:if>>男
                </option>
                <option value="2" <c:if test="${user.sex=='2'}">selected="selected"</c:if>>女
                </option>
            </select></td>
        </tr>
        <tr>
            <td>地址</td>
            <td><input type="text" name="address" value="${user.address} "/></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="提交"/></td>
        </tr>
    </table>
</form>
</body>

</html>