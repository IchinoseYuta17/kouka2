<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>

<h2>科目別成績一覧画面ページ</h2>

<table class="tbl">
    <thead>
        <tr>
            <th>科目名</th>
            <th>科目コード</th>
            <th>テスト番号</th>
            <th>得点</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="test" items="${testList}">
            <tr>
                <td>${test.subjectName}</td>
                <td>${test.subjectCd}</td>
                <td>${test.no}</td>
                <td>${test.point}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="../footer.html" %>
