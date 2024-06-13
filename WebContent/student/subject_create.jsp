<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
<h2>科目登録画面ページ</h2>

    <div class="form-container">
        <form action="SubjectCreateExecute.action" method="post">

            <div class="form-group">
                <label for="name">科目コード:</label>
                <input type="text" id="name" name="subject_cd" placeholder="科目コードを入力してください">
            </div>

            <div class="form-group">
                <label for="class">科目名:</label>
                <input type="text" id="name" name="subject_name" placeholder="科目名を入力してください">
            </div>

            <div class="form-buttons">
                <input type="submit" value="登録">

            </div>
            <a href="subject_list.jsp">戻る</a>
        </form>
    </div>

<%@include file="../footer.html" %>