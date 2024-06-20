<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>

<h2>成績参照検索画面</h2>

<form action="TestList.action" method="post">
     <div>
        <label for="entYear">入学年度:</label>
        <input type="text" id="entYear" name="entYear">
    </div>


   <div>
        <label for="classNum">クラス:</label>
        <input type="text" id="classNum" name="classNum">
    </div>

     <div>
        <label for="subjectCd">科目:</label>
        <input type="text" id="subjectCd" name="subjectCd">
    </div>

    <div>
        <label for="studentNo">学生番号:</label>
        <input type="text" id="studentNo" name="studentNo">
    </div>

    <input type="submit" value="検索">

    <c:if test="${not empty errorMsg}">
        <p style="color:red;">${errorMsg}</p>
    </c:if>
</form>

<%@ include file="../footer.html" %>
