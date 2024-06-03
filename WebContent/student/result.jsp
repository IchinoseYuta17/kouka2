<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>

<!-- display:flexでmenu.jspとcontentを横並びにする -->
<div class="container" style="display:flex; height:100vh;">
	<%@include file="menu.jsp" %>
	<div class="content" style="flex:0 0 85%; padding-left:20px">

		<!-- サーブレットからのメッセージを受け取る -->
		<% String message = (String) request.getAttribute("message"); %>

		<p><%= message %></p>
		<br>
		<a href="/kouka2">Topページへ</a>
	</div>
</div>

<%@include file="../footer.html" %>
