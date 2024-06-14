<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>
<div id="container">
	<%@include file="menu.jsp" %>

    <div id="main-contents">
    <h2>科目情報削除</h2>
        <form action="SubjectDeleteExecute.action" method="post">

			<div class="form-group">
			    <p>${subject.name}(${subject.cd})を削除してもよろしいですか？</p>
			</div>

				<div class="form-buttons">
				    <input type="submit" value="削除" style="margin-top:20px; margin-bottom:20px;">
				</div>
				<div class="return">
					<a href="SubjectList.action" >戻る</a>
				</div>
			<input type="hidden" name="subject_cd" value="${subject.cd}">
			<input type="hidden" name="subject_name" value="${subject.name}">
		</form>
    </div>
</div>

<%@include file="../footer.html" %>