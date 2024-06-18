<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<h2>学生別成績一覧画面ページ</h2>
<table class="tbl">
    <thead>
        <tr>
            <th>科目名</th>
            <th>科目コード</th>
            <th>学籍番号</th>
            <th>点数</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="test" items="${testListStudents}">
            <tr>
                <td>${test.subjectName}</td>
                <td>${test.subjectCd}</td>
                <td>${test.no}</td>
                <td>${test.point}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<h2>科目別成績一覧画面ページ</h2>
<table class="tbl">
    <thead>
        <tr>

            <th>入学年</th>
            <th>クラス番号</th>
            <th>学生番号</th>
            <th>学生名</th>
            <th>1回</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="testsubject" items="${testListSubjects}">
            <tr>
            	<td>${testsubject.entYear}</td>
                <td>${testsubject.classNum}</td>
                <td>${testsubject.studentNo}</td>
                <td>${testsubject.studentName}</td>
                <td>${testsubject.point}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<%@ include file="../footer.html" %>
