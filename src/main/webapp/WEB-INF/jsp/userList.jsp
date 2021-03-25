<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户列表</title>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/static/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript">
        function toAdd() {
            $("#userForm").attr("action",
                "${pageContext.request.contextPath }/toAdd.do");
            $("#userForm").submit();
        }

        function batchDelete() {
            var ids = [];//定义一个数组
            $('input[name="ids"]:checked').each(function () {
                ids.push($(this).val());
            });
            if (ids.length > 0) {
                if (confirm("确认删除吗?")) {
                    $("#userForm").attr("action",
                        "${pageContext.request.contextPath }/batchDelete.do");
                    $("#userForm").submit();
                }
            } else {
                alert("至少选中一列!!!");
            }
        }
    </script>
</head>
<body>
<form id="userForm" action="${pageContext.request.contextPath }/getAllUsers.do" method="post">
    <table width="800px" border=1 align="center">
        <caption>
            <font size="5">用户列表</font>
        </caption>
        <tr>
            <td width="50%">根据昵称查询：<input type="text" name="nickname"></td>
            <td><input type="submit" value="查询"/></td>
            <td><input type="button" value="添加" onclick="toAdd();"/></td>
            <td><input type="button" value="删除" onclick="batchDelete();"/></td>
        </tr>
    </table>
    <table width="800px" border='1' align="center">
        <tr>
            <th>选择</th>
            <th>头像</th>
            <th>用户名</th>
            <th>昵称</th>
            <th>生日</th>
            <th>电话</th>
            <th>性别</th>
            <th>地址</th>
            <th>查看我的狗</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pageBean.list}" var="user" varStatus="st">
            <tr align="center">
                <td><input type="checkbox" name="ids" value="${user.id }"></td>
                <td><img width="40px" height="40px"
                         src="${pageContext.request.contextPath}/${user.photo}"/></td>
                <td>${user.username}</td>
                <td>${user.nickname}</td>
                <td><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/></td>
                <td>${user.tel}</td>

                <td><c:if test="${user.sex=='1'}">
                    男
                </c:if> <c:if test="${user.sex=='2'}">
                    女
                </c:if></td>
                <td>${user.address}</td>
                <td><c:forEach items="${user.dogs}" var="dog">
                    <a href="${pageContext.request.contextPath }/getDog.do?cid=${dog.cid}">${dog.name}</a>
                </c:forEach></td>
                <td><a href="${pageContext.request.contextPath }/toUpdate.do?id=${user.id}">修改</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="10" align="center">第${pageBean.currPage }/${pageBean.totalPage }页&nbsp;&nbsp;&nbsp;&nbsp;
                总记录数:${pageBean.totalCount }&nbsp;&nbsp;每页显示记录数:${pageBean.pageSize }&nbsp;&nbsp;
                <c:if test="${pageBean.currPage !=1 }">
                    <a href="${pageContext.request.contextPath}/getAllUsers.do?currPage=1">首页</a>
                    <a href="${pageContext.request.contextPath}/getAllUsers.do?currPage=${pageBean.currPage-1}">上一页</a>
                </c:if> &nbsp;&nbsp; <c:forEach var="i" begin="1" end="${pageBean.totalPage }">
                    <c:if test="${pageBean.currPage!=i }">
                        <a href="${pageContext.request.contextPath}/getAllUsers.do?currPage=${i}">[${i }]</a>
                    </c:if>
                    <c:if test="${pageBean.currPage ==i }">
                        [${i }]
                    </c:if>
                </c:forEach> &nbsp;&nbsp; <c:if
                        test="${pageBean.currPage !=pageBean.totalPage&&pageBean.totalPage!=0 }">
                    <a href="${pageContext.request.contextPath}/getAllUsers.do?currPage=${pageBean.currPage+1}">下一页</a>
                    <a href="${pageContext.request.contextPath}/getAllUsers.do?currPage=${pageBean.totalPage}">尾页</a>
                </c:if>
            </td>
        </tr>
    </table>
</form>
</body>

</html>