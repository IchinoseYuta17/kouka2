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
                <select id="admissionYear" name="admissionYear">
                    <option value="">--------</option>
					<option value="2014">2014年</option>
					<option value="2015">2015年</option>
					<option value="2016">2016年</option>
					<option value="2017">2017年</option>
					<option value="2018">2018年</option>
					<option value="2019">2019年</option>
					<option value="2020">2020年</option>
					<option value="2021">2021年</option>
					<option value="2022">2022年</option>
					<option value="2023">2023年</option>
					<option value="2024">2024年</option>
					<option value="2025">2025年</option>
					<option value="2026">2026年</option>
					<option value="2027">2027年</option>
					<option value="2028">2028年</option>
					<option value="2029">2029年</option>
					<option value="2030">2030年</option>
					<option value="2031">2031年</option>
					<option value="2032">2032年</option>
					<option value="2033">2033年</option>
					<option value="2034">2034年</option>


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
                    <option value="101">101</option>

                </select>
            </div>

            <div class="form-buttons">
                <input type="submit" value="登録して終了">

            </div>
            <a href="student_list.jsp">戻る</a>
        </form>
    </div>
</div>

<%@ include file="../footer.html" %>
