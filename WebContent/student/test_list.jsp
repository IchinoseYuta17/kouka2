<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>

<div id="container">
<%@ include file="menu.jsp" %>

<div id="main-contents">
	<h2>成績参照</h2>
	<div class="container-frame">
		<form class="list-arrange" method="post" action="TestListSubjectExecute.action" style="border-bottom: 1px solid #ddd;">
		<div class="list-elm">
				<p>科目情報</p>
			</div>
			<div class="list-elm" >
				<label for="class">入学年度</label><br>
				<select name="entYear" style="width:100px">
					<option value="none"  selected>---------</option>
					<c:forEach var="studentEntYear" items="${studentEntYearSet}">
						<option value="${studentEntYear}">${studentEntYear}</option>
					</c:forEach>
				</select>
			</div>

			<div class="list-elm">
				<label for="class">クラス</label><br>
				<select name="classNum" style="width:100px">
					<option value="none"  selected>---------</option>
					<c:forEach var="classNum" items="${classNumSet}">
						<option value="${classNum.num}">${classNum.num}</option>
					</c:forEach>
				</select>
			</div>

			<div class="list-elm">
				<label for="class">科目</label><br>
				<select name="subjectCd" style="width:180px">
					<option value="none"  selected>---------</option>
					<c:forEach var="subjectCd" items="${subjectSet}">
						<option value="${subjectCd.cd}">${subjectCd.name}</option>
					</c:forEach>
				</select>
			</div>
		<div class="list-elm">
		    <div class="glay-buttons">
		    	<input type="submit" value="検索">
			</div>
		</div>
		    <c:if test="${not empty errorMsg}">
		        <p style="color:red;">${errorMsg}</p>
		    </c:if>
		</form>


	<form class="list-arrange" action="TestListStudentExecute.action" method="post" style="width:66%">
			<div class="list-elm">
				<p>学生情報</p>
			</div>

	    <div class="list-elm" >
	        <label for="studentNo">学生番号</label><br>
	        <input type="text" id="studentNo" name="studentNo" style="width:260px; border-width:thin;" placeholder="学生番号を入力してください">
	    </div>

		<div class="list-elm">
		    <div class="glay-buttons">
		    	<input type="submit" value="検索">
			</div>
		</div>

	    <c:if test="${not empty errorMsg}">
	        <p style="color:red;">${errorMsg}</p>
	    </c:if>
	</form>
	</div>
		<p style="color: #00bfff;">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
	</div>
</div>
<%@ include file="../footer.html" %>
