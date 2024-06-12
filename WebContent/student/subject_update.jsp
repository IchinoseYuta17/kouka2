<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <style>
    h2 {
      text-align: left;
      padding: 5px 20px;
      background-color: #f2f2f2;
    }

    .main-container {
      width: 80%;
      margin: 0px auto;
    }

    .form-container {
      display: flex;
      flex-direction: column;
      margin-bottom: 20px;
      margin-left: 15px;
    }

    .form-group {
      margin-bottom: 5px;
    }

    .form-group label {
     margin-bottom: 15px;
    }
    .form-group input {
    margin-bottom: 15px;
    }

    #subjectName{
    padding:8px;
	border:1px solid #ccc;
	border-radius: 4px;
	width:100%;
    }

    .form-group label {
      display: block;
    }

    .readonly {
      border: none;
    }

    .form-input {
      width: 100%;
    }

    .form-input label {
      margin-bottom: 10px;
    }

    .form-input input {
      width: 100%;
      margin-bottom: 15px;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }

    .form-buttons input {
      display: block;
      background-color: blue;
      color: #fff;
      border: none;
      padding: 10px 20px;
      cursor: pointer;
      border-radius: 4px;
    }

    .form-buttons input {
     margin-top:-10px;
	margin-bottom:10px;
    }

    .form-label {
      display: flex;
    }

    .form-label input {
      margin-bottom: 15px;
    }

	}
    footer {
      position: absolute;
      bottom: 0;
    }

    label {
      display: flex;
      align-items: center;
    }
  </style>
</head>
<body>
  <div class="main-container">
    <h2>科目情報変更</h2>
    <form id="myForm" action="subject_update_done.jsp" method="post">
      <div class="form-container">
        <div class="form-group">
          <label for="subjectCode">科目コード</label>
          <input class="readonly" type="text" id="subjectCode" name="subjectCode" value="F02" readonly>
          <label for="subjectName">科目名</label>
          <input type="text" id="subjectName" name="subjectName" placeholder="Javaプログラミング基礎" maxlength="20" required>
        </div>

        <div class="form-buttons">
          <input type="submit" value="変更">
          <a href="subject_list.jsp">戻る</a>
        </div>
      </div>
    </form>
  </div>
</body>
</html>
<%@include file="../footer.html" %>
