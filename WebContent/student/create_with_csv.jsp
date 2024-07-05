<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>

<div id="container">
<%@ include file="menu.jsp" %>

    <div id="main-contents" class = "form-container" onsubmit="return validateForm()">

        <h2 class="form-title">学生情報登録(CSV)</h2>

		<form id="" action="StudentCreateCsvExecute.action" method="POST" enctype="multipart/form-data">

			<div class="form-group">
				<label for="csv" class="th">csv:</label>
					<div class="td">
						<input type="file" id="csv" name="csv" required>
					</div>
			</div>

			<div class="form-buttons">
			    <input type="submit" value="登録して終了">
			</div>

            <a href="StudentList.action">戻る</a>
        </form>

    </div>

</div>


    <%@ include file="../footer.html" %>