<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>



<div id="container">
<%@include file="menu.jsp" %>
    <div id="main-contents">
		<div class="done-container">
		  <h2 class="form-title">成績管理</h2>
		  <p class="done-text">登録が完了しました</p>
		  <div class = "link-container">
			<a href="TestRegist.action">戻る</a>
		  	<a href="TestList.action">成績参照</a>
	  	  </div>
	    </div>
	</div>
</div>
<%@include file="../footer.html" %>



