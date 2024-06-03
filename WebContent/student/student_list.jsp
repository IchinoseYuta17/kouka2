<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>

<!-- display:flexでmenu.jspとcontentを横並びにする -->
<div class="container" style="display:flex; height:100vh;">
	<%@include file="menu.jsp" %>
	<div class="content" style="flex:0 0 85%; padding-left:20px">
		<!-- サーブレットから受け取ったstudentListをjstlのforEach文を使用して表示する -->
        <h2>生徒一覧</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>学生番号</th>
                    <th>学生名</th>
                    <th>コース名</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${studentList}">
                    <tr>
                        <td>${student.student_id}</td>
                        <td>${student.student_name}</td>
                        <!-- getCourse()でコースビーンを取得し、コースビーンのgetCourse_name()でコース名を取得する -->
                        <td>${student.getCourse().getCourse_name()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="/kouka2">Topページへ</a>
	</div>
</div>

<%@include file="../footer.html" %>