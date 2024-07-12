<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>

<div id="container">
<%@ include file="menu.jsp" %>


    <div id="main-contents" class = "form-container">
        <h2 class="form-title">学生情報登録</h2>

        <form action="StudentUpdateExecute.action" method="post">
			<div class="form-group">
			    <label for="admissionYear">入学年度</label>
			    <c:choose>
					<c:when test="${empty beforeStudent}">
						<input  type="text" name="ent_year" value="${student.entYear}" style="border:none; outline:none;" readonly>
					</c:when>
					<c:otherwise>
						<input  type="text" name="ent_year" value="${beforeStudent.entYear}" style="border:none; outline:none;" readonly>
					</c:otherwise>
				</c:choose>

			</div>


			<div class="form-group">
			    <label for="studentNumber">学生番号</label>
			    <c:choose>
					<c:when test="${empty beforeStudent}">
						<input  type="text" name="student_no" value="${student.no}" style="border:none; outline:none;" readonly>
					</c:when>
					<c:otherwise>
						<input  type="text" name="student_no" value="${beforeStudent.no}" style="border:none; outline:none;" readonly>
					</c:otherwise>
				</c:choose>
			</div>



            <div class="form-group">
                <label for="name">氏名</label>
                <c:choose>
					<c:when test="${empty beforeStudentName}">
						<input  type="text" name="name" value="${student.name}" required>
					</c:when>
					<c:otherwise>
						<input  type="text" name="name" value="${beforeStudentName}" required>
					</c:otherwise>
				</c:choose>
                <c:if test="${not empty nameError}">
        				${nameError}
   				</c:if>
   				<c:if test="${not empty errorMsg}">
						<p  class="error-message" style="margin-bottom:10px; margin-top:-10px">${errorMsg}</p>
				</c:if >
				<c:if test="${not empty nameBlankSpaceError}">
						<p  class="error-message" style="margin-bottom:10px; margin-top:-10px">${nameBlankSpaceError}</p>
				</c:if >
            </div>

            <div class="form-group">
                <label for="class">クラス</label>
                <select name="class_num">

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
		                <c:forEach var="classNum" items="${classNumSet}">
		                    <c:choose>
		                         <c:when test="${student.classNum == classNum.num}">
		                             <option value="${classNum.num}" selected>${classNum.num}</option>
		                         </c:when>
		                         <c:otherwise>
		                             <option value="${classNum.num}">${classNum.num}</option>
		                         </c:otherwise>
		                     </c:choose>
		                 </c:forEach>
	   				</c:otherwise>
   				</c:choose>
                </select>
            </div>

			 <c:choose>
				 <c:when test="${student.isAttend == 'TRUE'}">
					 <div class="checkbox-align">
					    <input type="checkbox" id="scales" name="is_attend" value="1" checked>
					    <label for="scales">在学中</label>
					  </div>
				  </c:when>
				  <c:otherwise>

  					 <div class="checkbox-align">
					    <input type="checkbox" id="scales" name="is_attend" value="1">
					    <label for="scales">在学中</label>
					 </div>
				  </c:otherwise>
			</c:choose>
            <div class="form-buttons" style="margin-top:10px; margin-bottom:10px">
                <input type="submit" value="変更">
            </div>
            <a href="StudentList.action">戻る</a>
        </form>
    </div>
</div>

<%@ include file="../footer.html" %>
