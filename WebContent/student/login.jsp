<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.sql.*" %>

    <title>得点管理システム</title>

<body>
    <div class="login-main-container">

        <h2>ログイン</h2>
        <!-- ログインフォーム -->
        <div class="login-container">
            <form method="post" action="LoginExecute.action">
                <div>
					<c:choose>
						<c:when test="${not empty beforeid}">
			                 <input type="text" id="id" name="id" <c:if test="${not empty beforeid}">value="${beforeid}"</c:if> placeholder="ID">
						</c:when>
						<c:otherwise>
							<input type="text" id="id" name="id" placeholder="ID">
						</c:otherwise>
					</c:choose>
                </div>

                <div>
                    <c:choose>
						<c:when test="${not empty beforepasswprd}">
							<input type="password" id="password" name="password" value="${beforepasswprd}" placeholder="パスワード">
                    		<input type="text" id="showPassword" class="show-password" readonly>
						</c:when>
						<c:otherwise>
							<input type="password" id="password" name="password" placeholder="パスワード">
                    		<input type="text" id="showPassword" class="show-password" readonly>
						</c:otherwise>
					</c:choose>
                </div>

                <div class="show-password-label">
                    <input type="checkbox" id="showPasswordCheckbox" onclick="togglePassword()">
                    <!-- タグを青色に変更 -->
                    <label for="showPasswordCheckbox" style="color: black;">パスコードを表示</label>
                </div>
                <div>
                    <!-- ログインボタンの色を青に変更 -->
                    <button type="submit">ログイン</button>
                </div>
            </form>
            <c:if test="${not empty notIdorPsError}">
					<div class="error-message">${notIdorPsError}</div>
			</c:if>
        </div>
    </div>
    <%@ include file="../footer.html" %>
    <script>
        function togglePassword() {
            var passwordInput = document.getElementById("password");
            var showPasswordInput = document.getElementById("showPassword");
            var checkbox = document.getElementById("showPasswordCheckbox");

            if (checkbox.checked) {
                passwordInput.style.display = "none";
                showPasswordInput.style.display = "block";
                showPasswordInput.value = passwordInput.value;
            } else {
                passwordInput.style.display = "block";
                showPasswordInput.style.display = "none";
            }
        }
    </script>

