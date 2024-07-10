<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.sql.*" %>

    <title>得点管理システム</title>

<body>
    <div class="login-main-container">


        <!-- ログインフォーム -->
        <div class="login-container">
          <h2 class="login-ttl">ログイン</h2>
            <form method="post" action="LoginExecute.action">

                <div>
                    <!-- タグを青色に変更 -->
                   <input type="text" id="id" name="id" placeholder="ID" required>

                </div>

                <div>
                    <!-- タグを青色に変更 -->
                    <input type="password" id="password" name="password" placeholder="パスワード" required>
                    <input type="text" id="showPassword" class="show-password" >
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

