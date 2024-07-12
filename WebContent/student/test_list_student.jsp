<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>

	<div id="container">
	<%@ include file="menu.jsp" %>

	<div id="main-contents">
		<c:if test="${ttl_flg == 'student'}">
			<h2>成績一覧（学生）</h2>
		</c:if>
		<c:if test="${ttl_flg == 'subject'}">
			<h2>成績一覧（科目）</h2>
		</c:if>

		<div class="container-frame">
			<form class="list-arrange" method="post" action="TestListSubjectExecute.action" style="border-bottom: 1px solid #ddd;">
				<div class="list-elm">
					<p>科目情報</p>
				</div>
				<div class="list-elm" >
					<label for="class">入学年度</label><br>
					<select name="entYear" style="width:100px">
						<c:choose>
							<c:when test="${not empty beforeEntYear}">
								<c:forEach var="entYear" items="${studentEntYearSet}">
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
								<c:forEach var="entYear" items="${studentEntYearSet}">
								 	<option value="${entYear}">${entYear}</option>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</select>
				</div>

				<div class="list-elm">
					<label for="class">クラス</label><br>
					<select name="classNum" style="width:100px">
						<c:choose>
							<c:when test="${not empty beforeClassNum}">
								<c:forEach var="classNum" items="${classNumSet}">
									<c:choose>
									    <c:when test="${classNum.num == beforeClassNum}">
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
				</div>

				<div class="list-elm">
					<label for="class">科目</label><br>
					<select name="subjectCd" style="width:180px">
						<c:choose>
							<c:when test="${not empty beforeSubjectCd}">
								<c:forEach var="subjectCd" items="${subjectSet}">
									<c:choose>
									    <c:when test="${subjectCd.cd == beforeSubjectCd}">
									        <option value="${beforeSubjectCd}" selected>${beforeSubject.name}</option>
									    </c:when>
									    <c:otherwise>
									        <option value="${subjectCd.cd}">${subjectCd.name}</option>
									    </c:otherwise>

									</c:choose>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<option value="">--------</option>
								<c:forEach var="subjectCd" items="${subjectSet}">
								 	<option value="${subjectCd.cd}">${subjectCd.name}</option>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</select>
				</div>

				<div class="list-elm">
				    <div class="glay-buttons">
				    	<input type="submit" value="検索">
					</div>
				</div>

			    <c:if test="${not empty admissionYearError}">
					<div class="error-message">
					    <p>${admissionYearError}</p>
					</div>
			    </c:if>
			</form>


			<form class="list-arrange" action="TestListStudentExecute.action" method="post" style="width:66%">
					<div class="list-elm">
						<p>学生情報</p>
					</div>

			    <div class="list-elm" >
			        <label for="studentNo">学生番号</label><br>
			        <input type="text" id="studentNo" name="studentNo" style="width:260px; border-width:thin;" placeholder="学生番号を入力してください" <c:if test="${not empty beforeStudentNo}">value="${beforeStudentNo}"</c:if>>
			    </div>

				<div class="list-elm">
				    <div class="glay-buttons">
				    	<input type="submit" value="検索">
					</div>
				</div>
			</form>
		</div>



	    <c:if test="${not empty errorMsg}">
	    	<c:if test="${not empty student}">
	    		<div>氏名：${student.name}(${student.no})</div>
	    	</c:if>
			<div class="null-message">
				<p>${errorMsg}</p>
			</div>
	    </c:if>



		<c:if test="${not empty testListStudents}">
		<div>氏名：${student.name}(${student.no})</div>
		<table class="tbl">
		    <thead>
		        <tr>
		            <th>科目名</th>
		            <th>科目コード</th>
		            <th>回数</th>
		            <th>点数</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach var="testStudent" items="${testListStudents}">
		            <tr>
		                <td>${testStudent.subjectName}</td>
		                <td>${testStudent.subjectCd}</td>
		                <td>${testStudent.no}</td>
		                <td>${testStudent.point}</td>
		            </tr>
		        </c:forEach>
		    </tbody>
		</table>
		</c:if>



		<c:if test="${not empty testListSubjects}">
		<div>科目：${subject.name}</div>
		<table class="tbl">
			<thead>
				<tr>
					<th>入学年度</th>
					<th>クラス</th>
					<th>学生番号</th>
					<th>学生名</th>
					<th>１回</th>
					<th>２回</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="testSubject" items="${testListSubjects}">
				<tr>
					<td>${testSubject.entYear}</td>
					<td>${testSubject.classNum}</td>
					<td>${testSubject.studentNo}</td>
					<td>${testSubject.studentName}</td>
					<td>
						<c:forEach var="entry" items="${testSubject.points}">
							<c:choose>
								<c:when test="${entry.key == 1 && entry.value != null}">
									${entry.value}
								</c:when>
								<c:when test="${entry.key == 1 && entry.value == null}">
									-
								</c:when>
							</c:choose>
						</c:forEach>

	                </td>
	                <td>
	                    <c:forEach var="entry" items="${testSubject.points}">
							<c:choose>
								<c:when test="${entry.key == 2 && entry.value != null}">
									${entry.value}
								</c:when>
								<c:when test="${entry.key == 2 && entry.value == null}">
									-
								</c:when>
							</c:choose>
						</c:forEach>
	                </td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	    </c:if>
	    </div>
    </div>
<%@ include file="../footer.html" %>
