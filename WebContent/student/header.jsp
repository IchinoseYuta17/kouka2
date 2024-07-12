<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="bean.Teacher" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Teacher teacher = (Teacher) session.getAttribute("teacher");
    request.setAttribute("teacher", teacher);
%>

<div id="header">
    <h1>得点管理システム</h1>
    <div class="head-elm">
        <c:if test="${not empty teacher}">
            <span>${teacher.name}様　</span>
            <a href="Logout.action">ログアウト</a>
        </c:if>
    </div>
</div>
<div class="container">
