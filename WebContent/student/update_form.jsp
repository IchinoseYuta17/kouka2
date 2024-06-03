<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>

<div class="container" style="display:flex; height:100vh;">
	<%@include file="menu.jsp" %>
	<div class="content" style="flex:0 0 85%; padding-left:20px">
        <form action="StudentUpdate.action" method="post">
            <table>
                <tr>
                    <td style="text-align:right">学生名:</td>
                    <td><input type="text" name="student_name" value="${student.student_name}" required></td>
                </tr>
                <tr>
                    <td style="text-align:right">コース名:</td>
                    <td>
                        <select name="course_id" required>
                            <c:forEach var="course" items="${courseList}">
                                <!-- もしもstudent.course_idとcourse.course_idが等しければデフォルトとしてselected属性を付加する -->
                                <c:choose>
                                    <c:when test="${student.course_id == course.course_id}">
                                        <option value="${course.course_id}" selected>${course.course_name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${course.course_id}">${course.course_name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="送信"></td>
                    <td></td>
                </tr>
            </table>
            <!-- 更新の条件式にstudent_idが必要なので隠しフィールドで送る -->
            <input type="hidden" name="student_id" value="${student.student_id}">
           </form>
	</div>
</div>

<%@include file="../footer.html" %>
