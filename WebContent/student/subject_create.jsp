<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>
<div id="container">
	<%@include file="menu.jsp" %>

    <div id="main-contents">
    <h2>科目情報登録</h2>
        <form action="SubjectCreateExecute.action" method="post">

			<div class="form-group">
			    <label for="name">科目コード</label>
			    <input type="text" id="name" name="subject_cd" placeholder="科目コードを入力してください" required>
			</div>

			<div class="form-group">
			    <label for="class">科目名</label>
			    <input type="text" id="name" name="subject_name" placeholder="科目名を入力してください" required>
			</div>
				<div class="form-buttons">
				    <input type="submit" value="登録" style="margin-top:20px; margin-bottom:20px;">
				</div>
				<div class="return">
					<a href="SubjectList.action" >戻る</a>
				</div>
		</form>
    </div>
</div>

<%@include file="../footer.html" %>