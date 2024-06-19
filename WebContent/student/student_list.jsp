<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.sql.*" %>

<style>
	select{
	width:180px;
	}
</style>
<body>
    <div id="container">
		<%@ include file="menu.jsp" %>

 	<div id="main-contents">
		<h2>学生管理</h2>
		<div class="new-registration-container">
			<a href="StudentCreate.action" class="new-registration">新規登録</a>
		</div>
		<div >
			<form class="list-container" method="post" action="StudentList.action">
				<div class="list-elm">
					<label for="year">入学年度 </label><br>
					<select name="year">
						<option value="none" selected>---------</option>
						<option value="2014">2014年</option>
						<option value="2015">2015年</option>
						<option value="2016">2016年</option>
						<option value="2017">2017年</option>
						<option value="2018">2018年</option>
						<option value="2019">2019年</option>
						<option value="2020">2020年</option>
						<option value="2021">2021年</option>
						<option value="2022">2022年</option>
						<option value="2023">2023年</option>
						<option value="2024">2024年</option>
						<option value="2025">2025年</option>
						<option value="2026">2026年</option>
						<option value="2027">2027年</option>
						<option value="2028">2028年</option>
						<option value="2029">2029年</option>
						<option value="2030">2030年</option>
						<option value="2031">2031年</option>
						<option value="2032">2032年</option>
						<option value="2033">2033年</option>
						<option value="2034">2034年</option>
					</select>
				</div>
				<div class="list-elm">
					<label for="class">クラス </label><br>
					<select name="class">
						<option value="none" selected>---------</option>
                        <option value="131">131</option>
                        <option value="132">132</option>
                        <option value="133">133</option>
					</select>
				</div>
				<div class="list-elm">
					<input type="checkbox" id="status" name="status" value="1">在学中
				</div>
				<div class="list-elm">
					<div class="glay-buttons">
						<input type="submit" value="絞り込み">
					</div>
				</div>
			<input type="hidden" name="flg" value="1">
			</form>
		</div>
	<c:choose>
		<c:when test="${flg == 0}">
			<label>検索結果:</label>
			<table class="tbl">
				<tr>
				<th>入学年度</th>
				<th>学生番号</th>
				<th>氏名</th>
				<th>クラス</th>
				<th>在学中</th>
				<th></th>
				</tr>
			<c:forEach var="student" items="${allStudentList}">
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
		</c:when>
		<c:when test="${flg == 1}">
			<label>検索結果:</label>
			<table class="tbl">
			<tr>
				<th>入学年度</th>
				<th>学生番号</th>
				<th>氏名</th>
				<th>クラス</th>
				<th>在学中</th>
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
		</c:when>
		<c:otherwise>
			<div>学生情報が存在しませんでした</div>
		</c:otherwise>
	</c:choose>
	</div>
</div>
<%@ include file="../footer.html" %>
