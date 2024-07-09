<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>



<div id="container">
<%@include file="menu.jsp" %>
    <div id="main-contents">
		<div class="done-container">
		  <h2 class="form-title">エラー</h2>
		  <p class="done-text">選択されたレコードは存在しません</p>
		  <div class = "link-container">
		  	<a href="SubjectList.action">科目一覧</a>
	  	  </div>
	    </div>
	</div>
</div>
<%@include file="../footer.html" %>