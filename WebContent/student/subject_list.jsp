<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
<h2>科目管理一覧画面ページ</h2>

<table class="tbl">
                    <tr>
                        <th>科目コード</th>
                        <th>科目</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <c:forEach var="subject" items="${subjectList}">
                        <tr>
                            <td>${subject.cd}</td>
                            <td>${subject.name}</td>

                            <td><a href="StudentUpdate.action?student_no=${student.no}">変更</a>
                            </td>
                            <td><a href="StudentUpdate.action?student_no=${student.no}">削除</a></td>
                        </tr>
                    </c:forEach>
                </table>
<%@include file="../footer.html" %>