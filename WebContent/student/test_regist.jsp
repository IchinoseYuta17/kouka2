<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>
<%@include file="menu.jsp" %>
<h2>成績管理一覧画面ページ</h2>

            <form method="post" action="TestRegist.action">
            	<div>
                    <label for="year">入学年度 </label><br>
                    <select id="year" name="f1" style="width: 240px;">
                    	<option value="null" selected>---------</option>

                        <option value="2022">2022</option>
                    	<option value="2023">2023</option>
                    </select>
                </div>

                <div>
                    <label for="year">クラス </label><br>
                    <select id="year" name="f2" style="width: 240px;">
                    <option value="null" selected>---------</option>
                    <c:forEach var="classNum" items="${classNumList}">
                        <option value="${classNum.num}">${classNum.num}</option>
                    </c:forEach>
                    </select>
                </div>
                <div>
                    <label for="class">科目 </label><br>
                    <select id="class" name="f3" style="width: 240px;">
                    <option value="null" selected>---------</option>
                    <c:forEach var="subject" items="${subjectList}">
                        <option value="${subject.cd}">${subject.name}</option>
                    </c:forEach>
                    </select>
                </div>
                <div>
                    <label for="class">回数 </label><br>
                    <select id="class" name="f4" style="width: 240px;">

                        <option value="null" selected>---------</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>

                    </select>
                </div>
            <div class="glay-buttons">
                <input type="submit" value="絞り込み">
            </div>
                <input type="hidden" name="flg" value="1">
            </form>

            <c:choose>
            <c:when test="testList != null">
                <label>検索結果:</label>
                <table class="tbl">
                    <tr>
                        <th>入学年度</th>
                        <th>クラス</th>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>点数</th>
                    </tr>
                    <c:forEach var="test" items="${testList}">
                        <tr>
                            <td>${test.student.entYear}</td>
                            <td>${test.classNum}</td>
                            <td>${test.student.no}</td>
                            <td>${test.student.name}</td>
                            <td>
                                <input type="text" name="point" value="${test.point}">
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>

            <c:otherwise>
            <label>検索結果:</label>
                <div>学生情報が存在しませんでした</div>
            </c:otherwise>
        </c:choose>

<%@include file="../footer.html" %>