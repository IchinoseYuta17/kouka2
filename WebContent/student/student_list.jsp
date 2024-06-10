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
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        h1 {
            text-align: center;
        }
        h2 {
            text-align: left;
            padding: 10px 20px;
            background-color: #f2f2f2;
        }
        .menu {
            margin: 20px;
        }
        .menu a {
            margin-right: 10px;
        }
        .form-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .form-container form {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
        }
        .form-container form > div {
            margin-right: 10px;
        }
        .form-container .new-registration {
            margin-left: auto;
            margin-bottom: 10px;
        }
        .main-container {
            width: 80%;
            margin: 0 auto;
        }
        .tbl {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        .tbl th, .tbl td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }
        .tbl th {
            background-color: #f2f2f2;
        }
        .new-registration-container {
            display: flex;
            justify-content: flex-end;
            margin-bottom: 20px;
        }
        button {
            background-color: #6c757d;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
        }
        button:hover {
            background-color: #5a6268;
        }

        /* 入学年度とクラスの間の空白を調整 */
        .form-container div {
            margin-right: 20px; /* 例：20pxに設定 */
        }

        /* 在学中と絞り込みの間の空白を調整 */
        .form-container div:last-child {
            margin-left: 50px; /* 例：20pxに設定 */
        }
    </style>
</head>
<body>
    <div class="main-container">
        <h2>学生管理</h2>
        <div class="new-registration-container">
            <a href="new_student.jsp" class="new-registration">新規登録</a>
        </div>
        <div class="form-container">
            <form method="post" action="StudentList.action">
                <div>
                    <label for="year">入学年度 </label><br>
                    <select id="year" name="year" style="width: 240px;">
                        <option value="none" selected>---------</option>
                        <option value="2022">2022</option>
                        <option value="2023">2023</option>
                        <option value="2024">2024</option>
                    </select>
                </div>
                <div>
                    <label for="class">クラス </label><br>
                    <select id="class" name="class" style="width: 240px;">
                        <option value="none" selected>---------</option>
                        <option value="131">131</option>
                        <option value="132">132</option>
                        <option value="133">133</option>
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
        <c:choose>
            <c:when test="${not empty studentList}">
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
                    <c:forEach var="student" items="${studentList}">
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
                            <td><a href="#">変更</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <div>学生情報が存在しませんでした</div>
            </c:otherwise>
        </c:choose>
    </div>
    <%@ include file="../footer.html" %>
</body>
</html>
