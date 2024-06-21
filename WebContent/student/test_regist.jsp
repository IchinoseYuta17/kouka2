<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<%@include file="header.jsp" %>

<div id="container">
<%@include file="menu.jsp" %>
<div id="main-contents">
	<h2>成績管理一覧画面ページ</h2>
	<div>
		<form class="list-container" method="post" action="TestRegist.action">
           	<div>
                   <label for="year">入学年度 </label><br>
                   <select name="f1">
						<option value="none"  selected>---------</option>
						<c:forEach var="studentEntYear" items="${studentEntYearSet}">
							<option value="${studentEntYear}">${studentEntYear}</option>
						</c:forEach>
					</select>
               </div>

               <div>
                   <label for="class">クラス </label><br>
                   <select id="class" name="f2" style="width: 150px;">
	                   <option value="null" selected>---------</option>
	                   <c:forEach var="classNum" items="${classNumSet}">
	                       <option value="${classNum.num}">${classNum.num}</option>
	                   </c:forEach>
                   </select>
               </div>
               <div>
                   <label for="class">科目 </label><br>
                   <select id="class" name="f3" style="width: 240px;">
	                   <option value="null" selected>---------</option>
	                   <c:forEach var="subject" items="${subjectSet}">
	                       <option value="${subject.cd}">${subject.name}</option>
	                   </c:forEach>
                   </select>
               </div>
				<div>
					<label for="class">回数 </label><br>
					<select id="class" name="f4" style="width: 50px;">
						<option value="null" selected>---------</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
					</select>
				</div>
			<div class="glay-buttons">
				<input type="submit" value="検索">
			</div>
		</form>
	</div>



		<c:choose>
			<c:when test="${testList != null && !testList.isEmpty()}">
				<label>科目：${subject.name}(${num}回)</label>
				<form method="post" action="TestRegistExecute.action">
					<table class="tbl">
				        <tr>
				            <th>入学年度</th>
				            <th>クラス</th>
				            <th>学生番号</th>
				            <th>氏名</th>
				            <th>点数</th>
				        </tr>
			        <c:forEach var="test" items="${testList}" varStatus="status">
			            <tr>
			                <td>
			                    <input type="hidden" name="entYear_${status.index}" value="${test.student.entYear}">
			                    ${test.student.entYear}
			                </td>
			                <td>
			                    <input type="hidden" name="classNum_${status.index}" value="${test.classNum}">
			                    ${test.classNum}
			                </td>
			                <td>
			                    <input type="hidden" name="studentNo_${status.index}" value="${test.student.no}">
			                    ${test.student.no}
			                </td>
			                <td>
			                    <input type="hidden" name="studentName_${status.index}" value="${test.student.name}">
			                    ${test.student.name}
			                </td>
			                <td>
			                    <input type="text" name="point_${status.index}" value="${test.point}">
			                </td>
			            </tr>
			        </c:forEach>
			    </table>

			    <div class="glay-buttons">
			        <input type="hidden" name="testCount" value="${testList.size()}">
			        <input type="hidden" name="subjectCd" value="${subject.cd}">
			        <input type="hidden" name="num" value="${num}">
			        <input type="submit" value="登録して終了">
			    </div>
			</form>
			</c:when>
			<c:otherwise>
				<c:if test="${flg == 1}">
					<div>学生情報が存在しませんでした</div>
				</c:if>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<%@include file="../footer.html" %>