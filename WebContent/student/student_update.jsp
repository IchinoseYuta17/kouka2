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
			    <label for="admissionYear">入学年度:</label>
			    <input type="text" name="ent_year" value="${student.entYear}" readonly style="border: none; background-color:#fff;">

			</div>


			<div class="form-group">
			    <label for="studentNumber">学生番号:</label>
			    <input type="text" name="student_no" value="${student.no}" readonly style="border: none; background-color:#fff;">

			</div>



            <div class="form-group">
                <label for="name">氏名:</label>
                <input type="text" name="name" value="${student.name}">
            </div>

            <div class="form-group">
                <label for="class">クラス:</label>
                <select name="class_num">
                    <c:forEach var="classNum" items="${classNumSet}">
                    	<option value="${classNum.num}">${classNum.num}</option>
					</c:forEach>
                </select>
            </div>

			 <c:choose>
				 <c:when test="${student.isAttend == 'TRUE'}">
					 <div>
					    <input type="checkbox" id="scales" name="is_attend" value="1" checked>
					    <label for="scales">在学中</label>
					  </div>
				  </c:when>
				  <c:otherwise>
  					 <div>
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
