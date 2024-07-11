<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>

<div id="container">
<%@ include file="menu.jsp" %>


    <div id="main-contents" class = "form-container" onsubmit="return validateForm()">
        <h2 class="form-title">学生情報登録</h2>

        <form action="StudentCreateExecute.action" method="post">



            <div class="form-group">
                <label for="admissionYear">入学年度</label>
                <select id="admissionYear" name="admissionYear">
                <c:choose>
					<c:when test="${not empty beforeEntYear}">

						<c:forEach var="entYear" items="${entYearSet}">
							<c:choose>
							    <c:when test="${entYear == beforeEntYear}">
							        <option value="${beforeEntYear}" selected>${beforeEntYear}</option>
							    </c:when>
							    <c:otherwise>
							        <option value="${entYear}">${entYear}</option>
							    </c:otherwise>

							</c:choose>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<option value="">--------</option>
						<c:forEach var="entYear" items="${entYearSet}">
						 	<option value="${entYear}">${entYear}</option>
						</c:forEach>
					</c:otherwise>
				</c:choose>
                </select>
				<div id="entError" class="error-message"></div>
            </div>



            <div class="form-group">
                <label for="studentNumber">学生番号</label>
                <input type="text" name="studentNumber" placeholder="学生番号を入力してください" <c:if test="${not empty beforeNo}"> value= "${beforeNo}" </c:if> required>
				<div class="error-message">
				<c:if test="${not empty studentNumberError}">
        				${studentNumberError}
   				</c:if>
				<c:if test="${not empty enrolledStudentNumberError}">
        				${enrolledStudentNumberError}
   				</c:if>
   				<c:if test="${not empty errorNumMsg}">
						${errorNumMsg}
				</c:if >
   				</div>
            </div>



            <div class="form-group">
                <label for="name">氏名</label>
                <input type="text" name="name" placeholder="氏名を入力してください" <c:if test="${not empty beforeName}"> value= "${beforeName}" </c:if> required>
                <c:if test="${not empty nameError}">
        				${nameError}
   				</c:if>
   				<c:if test="${not empty errorNameMsg}">
						<p  class="error-message" style="margin-bottom:10px; margin-top:-10px">${errorNameMsg}</p>
				</c:if >
            </div>



            <div class="form-group">
                <label for="class">クラス</label>
                <select name="class">
				<c:choose>
	                <c:when test="${not empty beforeClassNum}">
						<c:forEach var="classNum" items="${classNumSet}">
							<c:choose>
							    <c:when test="${classNum.num == beforClassNum}">
							        <option value="${beforeClassNum}" selected>${beforeClassNum}</option>
							    </c:when>
							    <c:otherwise>
							        <option value="${classNum.num}">${classNum.num}</option>
							    </c:otherwise>
							</c:choose>
						</c:forEach>
	   				</c:when>
	   				<c:otherwise>
	   					<option value="">--------</option>
						<c:forEach var="classNum" items="${classNumSet}">
						 	<option value="${classNum.num}">${classNum.num}</option>
						</c:forEach>
	   				</c:otherwise>
   				</c:choose>
                </select>
               <c:if test="${not empty studentClassError}">
					<div class="error-message">${studentClassError}</div>
				</c:if>
            </div>



            <div class="form-buttons">
                <input type="submit" value="登録して終了">
            </div>

			<div class="return">
            	<a href="StudentList.action">戻る</a>
            </div>
        </form>

    </div>
</div>


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


    <%@ include file="../footer.html" %>