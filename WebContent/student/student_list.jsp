<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>得点管理システム</title>
    <style>
        /* 必要なスタイルを追加 */
        body {
            font-family: Arial, sans-serif;
        }
        h1 {
            text-align: center;
        }
        h2 {
        	text-align: left;
        	padding:5px 20px;
        	background-color: #f2f2f2;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px auto;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .menu {
            margin: 20px;
        }
        .menu a {
            margin-right: 10px;
        }
        .form-container {
            position: relative;
            text-align: center;
            margin-bottom: 20px;
        }
        .form-container form {
            display: flex;
            justify-content: space-between;
            margin: 10px;
        }
        .form-container .new-registration {
            position:relative ;
            bottom: 30px;
        }
        .main-container{
        	width:100%;
        	margin: 10px;
        }
    </style>
</head>
<body>
    <div class="main-container">
        <h2>学生管理</h2>
		<div>
			<div>
				<a href="new_student.jsp" class="new-registration">新規登録</a>
			</div>
	        <div class="form-container">
	            <form method="get" action="student_list.jsp">
		            <div>
						<label for="year">入学年度 </label><br>
							<select id="year" name="year">
							<option value="">---------</option>
							</select>
					</div>
					<div>
						<label for="class">クラス </label><br>
							<select id="class" name="class">
							<option value="">---------</option>
							</select>
					</div>
					<div>
						<input type="checkbox" id="status" name="status" value="1">在学中
					</div>
					<div>
						<button type="submit">絞込み</button>
		            </div>
	            </form>
			</div>
		</div>
		<label>検索結果:</label>
	        <table>
	            <tr>
	                <th>入学年度</th>
	                <th>学生番号</th>
	                <th>氏名</th>
	                <th>クラス</th>
	                <th>在学中</th>
	                <th>変更</th>
	            </tr>

	        </table>
    </div>
    <%@ include file="../footer.html" %>
</body>
</html>
