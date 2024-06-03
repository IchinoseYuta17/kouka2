<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>

<div class="container" style="display:flex; height:100vh;">
	<%@include file="menu.jsp" %>
	<div class="content" style="flex:0 0 85%; padding-left:20px">
		<p>追加する学生の情報を入力してください。</p>
        <form action="StudentInsert.action" method="post">
            <table>
                <tr>
                    <td style="text-align:right">学生番号:</td>
                    <td><input type="text" name="student_id" required></td>
                </tr>
                <tr>
                    <td style="text-align:right">学生名:</td>
                    <td><input type="text" name="student_name" required></td>
                </tr>
                <tr>
                    <td style="text-align:right">コース名:</td>
                    <td>
                        <select name="course_id" required>
                            <c:forEach var="course" items="${courseList}">
                            	<!-- 表示はoptionタグで囲まれた文字列。実際に送るデータはvalue属性で指定した値 -->
                                <option value="${course.course_id}">${course.course_name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="追加"></td>
                    <td></td>
                </tr>
            </table>
           </form>
	</div>
</div>

<%@include file="../footer.html" %>
