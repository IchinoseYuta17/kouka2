<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
<style>
    h2 {
        text-align: left;
        padding: 5px 20px;
        background-color: #f2f2f2;
    }
    .main-container {
        margin: 0 auto;
        width: 80%;
        position: relative;
        padding-bottom: 40px; /* Space for the footer */
    }
    .main-container a {
        margin-right: 60px;
        margin-left: 20px;
    }
    .link-container {
        position: absolute;
        bottom: 20px; /* Adjust this value to control the distance from the bottom */
        width: 100%; /* Ensures the link is aligned left */
    }
    .link-container a {
        margin-left: 0; /* Aligns link to the left */
    }
    .main-container p {
        text-align: center;
        padding: 5px;
        background-color: #99CC66;
    }
    footer {
        position: absolute; /* 絶対位置 */
        bottom: 0; /* 下に固定 */
        width: 100%; /* Footer takes the full width */
        text-align: center; /* Center the text in the footer */
    }
</style>

<div class="main-container">
    <h2 class="form-title">科目情報変更</h2>
    <p>変更が完了しました</p>
    <div class="link-container">
        <a href="subject_list.jsp">科目一覧</a>
    </div>
</div>

<%@include file="../footer.html" %>
