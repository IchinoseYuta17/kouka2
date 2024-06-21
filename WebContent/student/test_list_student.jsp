<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<h2>学生別成績一覧画面ページ</h2>
<table class="tbl">
    <thead>
        <tr>
            <th>科目名</th>
            <th>科目コード</th>
            <th>学籍番号</th>
            <th>点数</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="testStudent" items="${testListStudents}">
            <tr>
                <td>${testStudent.subjectName}</td>
                <td>${testStudent.subjectCd}</td>
                <td>${testStudent.no}</td>
                <td>${testStudent.point}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<h2>科目別成績一覧画面ページ</h2>
<c:if test="${not empty testListSubjects}">
        <table>
            <thead>
                <tr>
                    <th>入学年度</th>
                    <th>クラス</th>
                    <th>学生番号</th>
                    <th>学生名</th>
                    <th>１回</th>
                    <th>２回</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="testSubject" items="${testListSubjects}">
                    <tr>
                        <td>${testSubject.entYear}</td>
                        <td>${testSubject.classNum}</td>
                        <td>${testSubject.studentNo}</td>
                        <td>${testSubject.studentName}</td>
                        <td>
                        <c:forEach var="entry" items="${testSubject.points}">
                        	${entry.value}
                    	</c:forEach><!-- 点数を表示 -->
                    	</td>
                        <td></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty testListSubjects}">
        <p>No data found.</p>
        <table>
            <thead>
                <tr>
                    <th>入学年度</th>
                    <th>クラス</th>
                    <th>学生番号</th>
                    <th>学生名</th>
                    <th>科目名</th>
                    <th>点数</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="test" items="${testListSubjects}">
                    <tr>
                        <td>${test.entYear}</td>
                        <td>${test.classNum}</td>
                        <td>${test.studentNo}</td>
                        <td>${test.studentName}</td>
                        <td>${test.Num}</td> <!-- 科目名を表示 -->
                        <td>${test.points[test.Num]}</td> <!-- 点数を表示 -->
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
<%@ include file="../footer.html" %>
