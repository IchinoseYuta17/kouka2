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
			    <span id="subject_cd_error" style="color:red;"></span>
			    <c:if test="${not empty enrolledSubjectCdError}">
        						<p " class="error-message" style="margin-bottom:10px;margin-top:-20px;">${enrolledSubjectCdError}</p>
   				</c:if>

			</div>

			<div class="form-group">
			    <label for="subject_name">科目名</label>
			    <input type="text" id="subject_name" name="subject_name" placeholder="科目名を入力してください" <c:if test="${not empty beforeSubjectName}"> value= "${beforeSubjectName}" </c:if> required>
			    <span id="subject_name_error" style="color:red;"></span>
			</div>

			<div class="form-uttons">
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
	  const admissionYearInput = document.getElementById('admissionYear'); // selectの要素を取得
	  const entError = document.getElementById('entError'); // エラーメッセージ用のdivタグを取得

	  if (admissionYearInput.value.trim() === '') {
	    entError.textContent = '入学年度を指定してください';
	    isValid = false;
	  } else {
	    entError.textContent = '';
	  }
	  return isValid;
	}
</script>