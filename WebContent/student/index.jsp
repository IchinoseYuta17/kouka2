<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>

<div id="container">
	<%@include file="menu.jsp" %>
    <div id="main-contents">
		<h2>メニュー</h2>

        <div style="display: flex; justify-content: space-around;">

            <div class="menu-div" style="background-color:#DDBABA ;">
                <a href="StudentList.action" style="display: block; padding:55px 40px; text-align: center;">学生管理</a>
            </div>

            <div class="menu-div" style="background-color:#C3DDBA;">

                <div style="text-align: center;">
                	<div style="margin: 20px 0 5px 0;">成績管理</div>
					<ul class="index-test-ul">
						<li class="index-test-li"><a class="index-test-a-1" href="TestRegist.action" style="display: block;">成績登録</a></li>
                    	<li class="index-test-li"><a class="index-test-a-2" href="TestList.action" style="display: block;">成績参照</a></li>
					</ul>
                </div>
            </div>

            <div class="menu-div" style="background-color:#C3BADD;">
                <a href="SubjectList.action" style="display: block; padding:55px 40px; text-align: center;">科目管理</a>
            </div>

        </div>

    </div>
</div>

<%@include file="../footer.html" %>