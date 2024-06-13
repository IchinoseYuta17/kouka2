<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>

<div id="container">
<%@ include file="menu.jsp" %>

    
    <div id="main-contents" class = "form-container">
		<h2>学生情報変更</h2>    
        <form action="subject_update.action" method="post">
			<div class="form-group">
				<label for="subjectcode">科目コード</label>
				<input  type="number" name="subjec_cd" value="${subject.cd}" style="border:none; outline:none;" readonly>	
			</div>

			<div class="form-group">
				<label for="studentNumber">科目名</label>
				<input type="text" name="subject_name" >
			</div>
	

			<div class="form-buttons">
					<input type="submit" value="変更">
			</div>
			<div class="return">
				<a href="subject_list.action" >戻る</a>
			</div>
			
		</form>

	
	</div>
</div>


<%@ include file="../footer.html" %>
