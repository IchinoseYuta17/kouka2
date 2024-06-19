<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>

<div id="container">
<%@ include file="menu.jsp" %>


    <div id="main-contents" class = "form-container">
        <h2 class="form-title">学生情報登録</h2>

        <form action="StudentCreateExecute.action" method="post">
            <div class="form-group">
                <label for="admissionYear">入学年度:</label>
                <select id="admissionYear" name="admissionYear">
					<option value="">--------</option>
                	<c:forEach var="entYear" items="${entYearSet}">
                    	<option value="${entYear}">${entYear}年</option>
					</c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="studentNumber">学生番号:</label>
                <input type="text" name="studentNumber" placeholder="学生番号を入力してください">
            </div>

            <div class="form-group">
                <label for="name">氏名:</label>
                <input type="text" name="name" placeholder="氏名を入力してください">
            </div>

            <div class="form-group">
                <label for="class">クラス:</label>
                <select name="class">
                	<c:forEach var="classNum" items="${classList}">
                    	<option value="${classNum.num}">${classNum.num}</option>
					</c:forEach>
                </select>
            </div>

            <div class="form-buttons">
                <input type="submit" value="登録して終了">

            </div>
            <a href="StudentList.action">戻る</a>
        </form>

    </div>
</div>
    <%@ include file="../footer.html" %>


