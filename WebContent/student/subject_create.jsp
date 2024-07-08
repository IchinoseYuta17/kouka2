<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>
<div id="container">
	<%@include file="menu.jsp" %>

    <div id="main-contents">
    <h2>科目情報登録</h2>
        <form action="SubjectCreateExecute.action" method="post" onsubmit="return validateForm()">
			<div class="form-group">
			    <label for="subject_cd">科目コード</label>
			    <input type="text" id="subject_cd" name="subject_cd" placeholder="科目コードを入力してください" <c:if test="${not empty beforeSubjectCd}"> value= "${beforeSubjectCd}" </c:if> required>

				    <c:if test="${not empty enrolledSubjectCdError}">
						<p  class="error-message" style="margin-bottom:10px; margin-top:-10px">${enrolledSubjectCdError}</p>
				    </c:if >
				    <c:if test="${not empty illegalCdError}">
						<p  class="error-message" style="margin-bottom:10px; margin-top:-10px">${illegalCdError}</p>
				    </c:if>

			</div>

			<div class="form-group">
			    <label for="subject_name">科目名</label>
			    <input type="text" id="subject_name" name="subject_name" placeholder="科目名を入力してください" <c:if test="${not empty beforeSubjectName}"> value= "${beforeSubjectName}" </c:if> required>
			    <c:if test="${not empty enrolledSubjectnameError}">
						<p  class="error-message" style="margin-bottom:10px; margin-top:-10px">${enrolledSubjectnameError}</p>
				    </c:if >

				    <c:if test="${not empty illegalnameError}">
						<p  class="error-message" style="margin-bottom:10px; margin-top:-10px">${illegalnameError}</p>
				    </c:if >
			</div>

			<div class="form-buttons">
			    <input type="submit" value="登録" style="margin-top:20px; margin-bottom:20px;">
			</div>

			<div class="return">
			    <a href="SubjectList.action">戻る</a>
			</div>
		</form>
    </div>
</div>

<%@include file="../footer.html" %>

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