<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>

<div id="container">
<%@ include file="menu.jsp" %>


    <div id="main-contents" class = "form-container">
		<h2>学生情報変更</h2>
        <form action="SubjectUpdateExecute.action" method="post">
			<div class="form-group">

				<label for="subjectcode">科目コード</label>

				<c:choose>
					<c:when test="${empty beforeSubjectCd}">
						<input  type="text" name="subject_cd" value="${subject.cd}" style="border:none; outline:none;" readonly>
					</c:when>
					<c:otherwise>
							<input  type="text" name="subject_cd" value="${beforeSubjectCd}" style="border:none; outline:none;" readonly>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="form-group">
				<label for="studentNumber">科目名</label>
				<input type="text" name="subject_name" value="${subject.name}" required>
				<c:if test="${not empty enrolledSubjectnameError}">
						<p  class="error-message" style="margin-bottom:10px; margin-top:-10px">${enrolledSubjectnameError}</p>
				</c:if >
			</div>


			<div class="form-buttons">
					<input type="submit" value="変更">
			</div>
			<div class="return">
				<a href="SubjectList.action" >戻る</a>
			</div>

		</form>


	</div>
</div>


<%@ include file="../footer.html" %>
<script>


function validateForm() {
    let isValid = true;

    // 科目コードの検証
    if (!validateSubjectCd()) {
        isValid = false;
    }

    // 他の検証
    const admissionYearInput = document.getElementById('admissionYear'); // selectの要素を取得
    const entError = document.getElementById('entError'); // エラーメッセージ用のdivタグを取得

    if (admissionYearInput && admissionYearInput.value.trim() === '') {
        entError.textContent = '入学年度を指定してください';
        isValid = false;
    } else if (entError) {
        entError.textContent = '';
    }

    return isValid;
}
</script>