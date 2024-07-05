<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>

<div id="container">
<%@include file="menu.jsp" %>
    <div id="main-contents">
		<div class="done-container">
		  <h2 class="form-title">学生情報登録</h2>
		  <p class="done-text">登録時にエラーが発生しました</p>
		  <div class = "link-container">
		  	<label>成功件数 : ${successCount}</label><br>
		  	<label>データ件数 : ${totalCount}</label><br>
		  	<label style="margin:5px;">エラー内容 : ${errors}</label><br>
			<a href="StudentList.action">戻る</a>
		  	<a href="StudentList.action">学生一覧</a>
	  	  </div>

	    </div>
	</div>
</div>
<%@include file="../footer.html" %>



