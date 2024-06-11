<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.sql.*" %>

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
            /* 横から縦に変更 */
            display: block;
            padding: 20px;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 20px;
            text-align: center; /* 中央揃え */
        }
        .form-container form {
            /* 横から縦に変更 */
            display: flex;
            flex-direction: column;
            align-items: center; /* 中央揃え */
        }
        .form-container form > div {
            margin-bottom: 10px; /* 各要素の間隔を調整 */
        }
        .form-container .new-registration {
            margin-left: auto;
            margin-bottom: 10px;
        }
        .main-container {
            width: 80%;
            margin: 0 auto;
        }
        button {
            background-color: #007bff; /* 青色に変更 */
            color: white;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
            width: 100%; /* ボタンの幅を100%に設定 */
        }
        button:hover {
            background-color: #0056b3; /* ホバー時の色を変更 */
        }
        input[type="text"], input[type="password"], input[type="checkbox"] {
            width: 100%; /* 入力フィールドの幅を100%に設定 */
            box-sizing: border-box; /* 幅の指定にpaddingとborderを含める */
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #99c3e6; /* 枠線を薄い青色に設定 */
            border-radius: 5px; /* 角を丸める */
            color: black; /* 文字色を黒に設定 */
        }
        .show-password {
            display: none; /* 最初は非表示 */
        }
        .show-password-label {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }
        /* タグの背景色を薄い青に設定 */
        h2, #username, #password {
            background-color: #e6f0ff; /* 薄い青色 */
        }
		    #username::placeholder {
			font-size: smaller;
	        color: #999999; /* デフォルトの文字色 */
	        position: absolute;
	        top: 10px; /* 上からの距離 */
	        left: 10px; /* 左からの距離 */
	    }
 		h2 {
            text-align: left;
            padding: 10px 20px;
            background-color: #f2f2f2; /* 背景色をグレーに変更 */
            color: black; /* 文字色を黒に変更 */
            align-items: center;
            }
    </style>
</head>
<body>
    <div class="main-container">

        <h2>ログイン</h2>
        <!-- ログインフォーム -->
        <div class="form-container">
            <form method="post" action="LoginExecute.action">
                <div>
                    <!-- タグを青色に変更 -->
                   <input type="text" id="id" name="id" placeholder="ID">

                </div>
                <div>
                    <!-- タグを青色に変更 -->
                    <a>PASSWORD</a>
                    <input type="password" id="password" name="password" placeholder="パスワード">
                    <input type="text" id="showPassword" class="show-password" readonly>
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

