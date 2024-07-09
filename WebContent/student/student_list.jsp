<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.sql.*" %>

<style>
	select {
		width: 180px;
	}
	a{
		margin:5px;
	}
</style>
<body>
	<div id="container">
		<%@ include file="menu.jsp" %>

		<div id="main-contents">
			<h2>学生管理</h2>
			<div class="new-registration-container">
				<a href="StudentCreate.action" class="new-registration">新規登録</a>
				<a href="StudentCreateCsv.action" class="new-registration">CSVで新規登録</a>
			</div>
			<div>
				<form class="list-container" method="post" action="StudentList.action">
					<div class="list-elm">
						<label for="year">入学年度 </label><br>
						<select name="year">
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
						<label for="class">クラス </label><br>
						<select name="class">
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
						<div class="checkbox-align">
							<input type="checkbox" id="status" name="status" value="1" <c:if test="${param.status == '1'}">checked</c:if>>
							 <label for="status">在学中</label>
						</div>
					</div>
					<div class="list-elm">
						<div class="glay-buttons">
							<input type="submit" value="絞り込み">
						</div>
					</div>
					<input type="hidden" name="flg" value="1">
				</form>
				<c:if test="${not empty allFieldsError}">
					<div class="error-message">${allFieldsError}</div>
				</c:if>
			</div>
			<c:choose>
				<c:when test="${flg == 0}">
					<c:if test="${resultCount > 0}">
						<label>検索結果:${resultCount}件</label>
					</c:if>
					<table class="tbl">
						<tr>
							<th>入学年度</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>クラス</th>
							<th class="enrolled">在学中</th>
							<th></th>
						</tr>
						<c:forEach var="student" items="${allStudentList}">
							<tr>
								<td>${student.entYear}</td>
								<td>${student.no}</td>
								<td>${student.name}</td>
								<td>${student.classNum}</td>
								<td class="enrolled">
									<c:choose>
										<c:when test="${student.isAttend == 'TRUE'}">
											○
										</c:when>
										<c:otherwise>
											×
										</c:otherwise>
									</c:choose>
								</td>
								<td><a href="StudentUpdate.action?student_no=${student.no}">変更</a></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:when test="${flg == 1}">
					<c:if test="${resultCount > 0}">
						<label>検索結果:${resultCount}件</label>
					</c:if>
					<c:if test="${resultCount == 0}">
						<div>学生情報が存在しませんでした</div>
					</c:if>
					<c:if test="${resultCount > 0}">
						<table class="tbl">
							<tr>
								<th>入学年度</th>
								<th>学生番号</th>
								<th>氏名</th>
								<th>クラス</th>
								<th>在学中</th>
								<th></th>
							</tr>
							<c:forEach var="student" items="${searchedStudentList}">
								<tr>
									<td>${student.entYear}</td>
									<td>${student.no}</td>
									<td>${student.name}</td>
									<td>${student.classNum}</td>
									<td>
										<c:choose>
											<c:when test="${student.isAttend == 'TRUE'}">
												○
											</c:when>
											<c:otherwise>
												×
											</c:otherwise>
										</c:choose>
									</td>
									<td><a href="StudentUpdate.action?student_no=${student.no}">変更</a></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</c:when>
				<c:otherwise>
					<div>学生情報が存在しませんでした</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<%@ include file="../footer.html" %>
</body>
