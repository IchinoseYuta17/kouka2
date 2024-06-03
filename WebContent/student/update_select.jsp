<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>

<!-- display:flexでmenu.jspとcontentを横並びにする -->
<div class="container" style="display:flex; height:100vh;">
	<%@include file="menu.jsp" %>
	<div class="content" style="flex:0 0 85%; padding-left:20px">
		<p>データを更新する学生を選んでください</p>
        <!-- selectボックスでサーブレットから受けっ取ったstudentListを
        JSTLのforEach文で表示する -->
        <form action="ToUpdateForm.action" method="post">
            <select name="student_id">
                <c:forEach var="student" items="${studentList}">
                    <option value="${student.student_id}">${student.student_name}</option>
                </c:forEach>
            </select>
            <input type="submit" value="送信">
        </form>
	</div>
</div>

<%@include file="../footer.html" %>
