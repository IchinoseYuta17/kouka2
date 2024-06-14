<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
<style>

	h2{
	       text-align: left;
	       padding: 5px 20px;
	       background-color: #f2f2f2;
	}
	.main-container{
		margin:0 auto;
		width: 80%;
	}
	.main-container a{
		margin-right: 60px;
		margin-left: 20px;
	}
	.link-container{
		position:absolute;
		bottom: 320px;
	}
	.main-container p{
		text-align: center;
		padding: 5px;
		background-color: #99CC66;
	}
	footer{
    position: absolute;/*←絶対位置*/
    bottom: 0; /*下に固定*/
	}


</style>

<div class="main-container">
    <h2 class="form-title">学生情報登録</h2>
    <p>登録が完了しました</p>
    <div class = "link-container">
		<a href="StudentList.action">戻る</a>
	   	<a href="StudentList.action">学生一覧</a>
    </div>

</div>

<%@include file="../footer.html" %>