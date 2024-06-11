<%@page contentType="text/html; charset=UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../header.html" %>

<%@include file="header.jsp" %>

<%@include file="menu.jsp" %>

<div class="main-contents">

    <p>メニュー</p>

    <div class="main-menu" style="display: flex; flex-direction: column;">

        <div style="display: flex; justify-content: space-around;">

            <div style="margin-right: 10px; flex: 1;">

                <a href="StudentList.action" style="display: block; background-color: #f5b2ac ; padding: 40px; text-align: center;">学生管理</a>

                <ul style="list-style: none; padding: 0;">

                </ul>

            </div>

            <div style="margin-right: 10px; flex: 1;">

                <span style="background-color: #ccffcc; padding: 3px; display: block; text-align: center;">成績管理

                    <ul style="list-style: none; padding: 0;">

                        <li><a href="#" style="display: block;">成績登録</a></li>

                        <li><a href="#" style="display: block;">成績参照</a></li>

                    </ul>

                </span>

            </div>

            <div style="flex: 1;">

                <a href="#" style="display: block; background-color: #e2c6ff; padding: 40px; text-align: center;">科目管理</a>

                <ul style="list-style: none; padding: 0;">

                </ul>

            </div>

        </div>

    </div>

</div>

<%@include file="../footer.html" %>