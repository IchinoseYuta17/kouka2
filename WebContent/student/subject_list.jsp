<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>

<div id="container">
	<%@include file="menu.jsp" %>

    <div id="main-contents">
    	<h2>科目管理</h2>
    	<div class="new-registration-container">
        	<a href="SubjectCreate.action">新規登録</a>
        </div>
	    <table class="tbl">
	        <tr>
	            <th>科目コード</th>
	            <th>科目名</th>
	            <th></th>
	            <th></th>
	        </tr>
	        <c:forEach var="subject" items="${subjectList}">
	            <tr>
	                <td>${subject.cd}</td>
	                <td>${subject.name}</td>
	                <td><a href="SubjectUpdate.action?subject_cd=${subject.cd}">変更</a></td>
	                <td><a href="SubjectDelete.action?subject_cd=${subject.cd}">削除</a></td>

<c:choose>
    <c:when test="${subject.cd != null}">
        <a href="SubjectUpdate.action?subject_cd=${subject.cd}">変更</a>
    </c:when>
    <c:otherwise>
        <a href="phantom.jsp">変更</a>
    </c:otherwise>
</c:choose>

	            </tr>
	        </c:forEach>
	    </table>
	</div>
</div>

<%@include file="../footer.html" %>
