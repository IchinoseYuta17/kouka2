<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>

<h2>成績参照検索画面</h2>

<form action="TestList.action" method="post">
    <label for="studentNo">学生番号:</label>
    <input type="text" id="studentNo" name="studentNo">
    <input type="submit" value="検索">
    <c:if test="${not empty errorMsg}">
        <p style="color:red;">${errorMsg}</p>
    </c:if>
</form>



