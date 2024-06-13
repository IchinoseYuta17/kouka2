<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>

<style>
    h2 {
        text-align: left;
        padding: 10px 20px;
        background-color: #f2f2f2;
        color: black;
    }

    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
    }

    .main-container {
        margin: 20px auto;
        width: 80%;
        max-width: 1200px;
        text-align: left;
    }

    table {
        border-collapse: collapse;
        width: 100%;
    }

    th, td {
        border-bottom: 1px solid #ddd;
        padding: 10px;
        text-align: left;
    }

    tr:hover {
        background-color: #f0f0f0;
    }

    .new-registration-container {
        margin-bottom: 10px;
        text-align: right;
    }




</style>

<div class="main-container">
    <h2>科目管理</h2>
    <div class="new-registration-container">
        <a href="SubjectCreate.action" class="new-registration">新規登録</a>
    </div>

    <table>
        <tr>
            <th>科目コード</th>
            <th>科目名</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var="subject" items="${subjectList}">
            <tr>
                <td>${subject.cd}</td>
                <td>${subject.name}</td>
                <td><a href="SubjectUpdate.action?subject_cd=${subject.cd}">変更</a></td>
                <td><a href="SubjectDelete.action?subject_cd=${subject.cd}">削除</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@include file="../footer.html" %>
