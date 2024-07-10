<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.sql.*" %>
    <title>サインアップ</title>

<body>
    <form action="SignUpExecute.action" method="post">
        <div>
            <label for="id">ID:</label>
            <input type="text" id="id" name="id" value="${beforeId != null ? beforeId : ''}">
            <span class="error-message">${idError != null ? idError : ''}</span>
        </div>
        <div>
            <label for="password">パスワード:</label>
            <input type="password" id="password" name="password" value="${beforePassword != null ? beforePassword : ''}">
            <span class="error-message">${passwordError != null ? passwordError : ''}</span>
        </div>
        <div>
            <label for="name">名前:</label>
            <input type="text" id="name" name="name" value="${beforeName != null ? beforeName : ''}">
            <span class="error-message">${nameError != null ? nameError : ''}</span>
        </div>
        <div>
            <label for="school_cd">学校コード:</label>
            <input type="text" id="school_cd" name="school_cd" value="${beforeSchoolCd != null ? beforeSchoolCd : ''}">
            <span class="error-message">${school_cdError != null ? school_cdError : ''}</span>
        </div>
        <div>
            <span class="error-message">${signupError != null ? signupError : ''}</span>
        </div>
        <div>
            <button type="submit">サインアップ</button>
        </div>
    </form>
</body>
<%@ include file="../footer.html" %>
