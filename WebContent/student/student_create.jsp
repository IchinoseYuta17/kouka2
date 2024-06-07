<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
 <style>
        body {
            font-family: Arial, sans-serif;
        }
        
        form {
            width: 50%;
            margin: 120px 30px;
            
        }
        
    label {
        display: block;
        margin-top: 10px;
        margin-bottom: 5px;
    }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0 15px 0;
            box-sizing: border-box;
        }
        input[type="submit"] {
        	margin: 50px 0px;
            display: block;
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            cursor: pointer;
            width: 20%;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        select {
        	width: 100%;
        	height: 40px;
        }
        
        
    .form-title {
        display: block;
        margin: 0 20px ;
        padding: 10px;
            }
    </style>
    
<h2 class="form-title">学生情報登録</h2>
<form action="" method="post">
        <label for="admissionYear">入学年度:</label>
	    <select id="admissionYear" name="admissionYear">
	        <option value="">--------</option>

	    </select>
        <label for="studentNumber">学生番号:</label>
        <input type="text" id="studentNumber" name="studentNumber" placeholder="学生番号を入力してください">

        <label for="name">氏名:</label>
        <input type="text" id="name" name="name" placeholder="氏名を入力してください">

        <label for="class">クラス:</label>
	    <select id="class" name="class">
	        <option value="">101</option>

	    </select>
        <input type="submit" value="登録して終了">
    </form>


<%@include file="../footer.html" %>