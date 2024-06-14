<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>

<div id="container">
<%@ include file="menu.jsp" %>


    <div id="main-contents" class = "form-container">
        <h2 class="form-title">学生情報登録</h2>

        <form action="student_create.action" method="post">
			<div class="form-group">
			    <label for="admissionYear">入学年度:</label>
			    <input type="text" name="studentNumber" value="${student.ent_year}" readonly disabled style="border: none; background-color:#fff;"">
			</div>


			<div class="form-group">
			    <label for="studentNumber">学生番号:</label>
			    <input type="text" name="studentNumber" value="${student.no}" readonly disabled style="border: none; background-color:#fff;">
			</div>



            <div class="form-group">
                <label for="name">氏名:</label>
                <input type="text" name="name" placeholder="氏名を入力してください">
            </div>

            <div class="form-group">
                <label for="class">クラス:</label>
                <select name="class">
                    <option value="101">101</option>

                </select>
            </div>
			 <div>
			    <input type="checkbox" id="scales" name="scales" checked />
			    <label for="scales">在学中</label>
			  </div>

            <div class="form-buttons" style="margin-top:10px; margin-bottom:10px">
                <input type="submit" value="変更">

            </div>
            <a href="student_list.jsp">戻る</a>
        </form>
    </div>
</div>

<%@ include file="../footer.html" %>
