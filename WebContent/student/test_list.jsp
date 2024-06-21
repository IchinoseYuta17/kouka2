<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>

<div id="container">
<%@ include file="menu.jsp" %>

<div id="main-contents">
	<h2>成績参照検索画面</h2>
	<div>
		<form class="list-container" method="post" action="TestListSubjectExecute.action">
			<div class="list-elm">
				<label for="class">入学年度</label><br>
				<select name="entYear">
					<option value="none"  selected>---------</option>
					<c:forEach var="studentEntYear" items="${studentEntYearSet}">
						<option value="${studentEntYear}">${studentEntYear}</option>
					</c:forEach>
				</select>
			</div>

			<div class="list-elm">
				<label for="class">クラス</label><br>
				<select name="classNum">
					<option value="none"  selected>---------</option>
					<c:forEach var="classNum" items="${classNumSet}">
						<option value="${classNum.num}">${classNum.num}</option>
					</c:forEach>
				</select>
			</div>

			<div class="list-elm">
				<label for="class">科目</label><br>
				<select name="subjectCd">
					<option value="none"  selected>---------</option>
					<c:forEach var="subjectCd" items="${subjectSet}">
						<option value="${subjectCd.cd}">${subjectCd.name}</option>
					</c:forEach>
				</select>
			</div>

		    <input type="submit" value="検索">

		    <c:if test="${not empty errorMsg}">
		        <p style="color:red;">${errorMsg}</p>
		    </c:if>
		</form>
	</div>

	<form action="TestListStudentExecute.action" method="post">

	    <div>
	        <label for="studentNo">学生番号:</label>
	        <input type="text" id="studentNo" name="studentNo">
	    </div>

	    <input type="submit" value="検索">

	    <c:if test="${not empty errorMsg}">
	        <p style="color:red;">${errorMsg}</p>
	    </c:if>
	</form>
	</div>
</div>
<%@ include file="../footer.html" %>
