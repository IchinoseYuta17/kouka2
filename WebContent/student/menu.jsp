<%@page pageEncoding="UTF-8" %>
<head>
	    <style>

        .global-navi ul {
            list-style-type: none;
            padding: 0;
        }

        .global-navi ul li {
            margin-bottom: 15px;
        }

        .global-navi {
         margin-left:20px;
        }

        .global-navi nav li {
            padding-left: 20px;
        }
    </style>
</head>
<div class="global-navi" style="border-right: 2px solid black; flex:0 0 15%">
    <ul>

    <li><a href="Menu.action">メニュー</a></li>
    <li><a href="student_list.jsp">学生管理</a></li>

	<li>
		<nav>成績管理
			<ul>
				<li><a href="subject_create.jsp">成績登録</a></li>
				<li><a href="test_list.jsp">成績参照</a></li>
			</ul>
		</nav>
	</li>
	<li><a href="subject_list.jsp">科目管理</a></li>

	</ul>


</div>

