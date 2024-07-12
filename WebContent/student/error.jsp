<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>



<div id="container">
<%@include file="menu.jsp" %>
    <div id="main-contents">
		<p>エラーが発生しました</p>
		<br>
		<p  class="error-message" style="margin-bottom:10px; margin-top:-10px">${errorMsg}</p>
	</div>
</div>
<%@include file="../footer.html" %>