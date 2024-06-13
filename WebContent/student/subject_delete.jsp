<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>
<div id="container">
	<%@include file="menu.jsp" %>

    <div id="main-contents">
    <h2>科目情報削除</h2>
    <p>${subject.name}を削除してもよろしいですか？</p>
        <form action="SubjectCreateExecute.action" method="post">
			

				<div class="red-buttons">
				    <input type="submit" value="削除" style="margin-top:20px; margin-bottom:20px;">
				</div>
				<div class="return">
					<a href="subject_list.action" >戻る</a>
				</div>        
		</form>
    </div>
</div>

<%@include file="../footer.html" %>