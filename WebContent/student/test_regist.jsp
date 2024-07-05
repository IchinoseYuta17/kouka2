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
           		<div class="list-elm">
                   <label for="year">入学年度 </label>
                   <select name="f1" style="width: 120px;">
						<c:choose>
							<c:when test="${not empty beforeEntYear}">
								<c:forEach var="entYear" items="${studentEntYearSet}">
									<c:choose>
									    <c:when test="${entYear == beforeEntYear}">
									        <option value="${entYear}" selected>${entYear}</option>
									    </c:when>
									    <c:otherwise>
									        <option value="${entYear}">${entYear}</option>
									    </c:otherwise>

									</c:choose>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<option value="">--------</option>
								<c:forEach var="entYear" items="${studentEntYearSet}">
								 	<option value="${entYear}">${entYear}</option>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</select>
               </div>

               <div class="list-elm">
                   <label for="class">クラス </label>
                   <select id="class" name="f2" style="width: 120px;">
						<c:choose>
	                        <c:when test="${not empty beforeClassNum}">
	                            <c:forEach var="classNum" items="${classNumSet}">
	                                <c:choose>
	                                    <c:when test="${classNum.num == beforeClassNum}">
	                                        <option value="${classNum.num}" selected>${classNum.num}</option>
	                                    </c:when>
	                                    <c:otherwise>
	                                        <option value="${classNum.num}">${classNum.num}</option>
	                                    </c:otherwise>
	                                </c:choose>
	                            </c:forEach>
	                        </c:when>
	                        <c:otherwise>
	                            <option value="">--------</option>
	                            <c:forEach var="classNum" items="${classNumSet}">
	                                <option value="${classNum.num}">${classNum.num}</option>
	                            </c:forEach>
	                        </c:otherwise>
	                    </c:choose>
                   </select>
               </div>

               <div class="list-elm">
                   <label for="class">科目 </label>
                   <select id="class" name="f3" style="width: 250px;">
						<c:choose>
	                        <c:when test="${not empty beforeSubjectCd}">
	                            <c:forEach var="subject" items="${subjectSet}">
	                                <c:choose>
	                                    <c:when test="${subject.cd == beforeSubjectCd}">
	                                        <option value="${subject.cd}" selected>${subject.name}</option>
	                                    </c:when>
	                                    <c:otherwise>
	                                        <option value="${subject.cd}">${subject.name}</option>
	                                    </c:otherwise>
	                                </c:choose>
	                            </c:forEach>
	                        </c:when>
	                        <c:otherwise>
	                            <option value="">--------</option>
	                            <c:forEach var="subject" items="${subjectSet}">
	                                <option value="${subject.cd}">${subject.name}</option>
	                            </c:forEach>
	                        </c:otherwise>
	                    </c:choose>
                   </select>
				</div>

				<div class="list-elm">
					<label for="class">回数 </label>
					<select id="class" name="f4" style="width: 120px; margin-right:30px;">
						<c:choose>
	                        <c:when test="${not empty beforeNo}">
	                            <c:forEach var="testNo" items="${testNoList}">
	                                <c:choose>
	                                    <c:when test="${testNo == beforeNo}">
	                                        <option value="${beforeNo}" selected>${beforeNo}</option>
	                                    </c:when>
	                                    <c:otherwise>
	                                        <option value="${testNo}">${testNo}</option>
	                                    </c:otherwise>
	                                </c:choose>
	                            </c:forEach>
	                        </c:when>
	                        <c:otherwise>
	                            <option value="">--------</option>
	                            <c:forEach var="testNo" items="${testNoList}">
	                                <option value="${testNo}">${testNo}</option>
	                            </c:forEach>
	                        </c:otherwise>
	                    </c:choose>
					</select>
				</div>
			<div class="glay-buttons" style="margin-right:20px;">
				<input type="hidden" name="" value="${num}">
				<input type="hidden" name="flg" value=1>
				<input type="submit" value="検索">
			</div>
		</form>
	</div>
		<c:if test="${flg == 1}">
			<div class="error-message">
			    <p>${errorMsg}</p>
			</div>
		</c:if>


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
								<c:if test="${not empty errorMessages[status.index]}">
									<p class="error-message" >${errorMessages[status.index]}</p>
								</c:if>
			                </td>
			            </tr>
			        </c:forEach>
			    </table>

			    <div class="glay-buttons">
			        <input type="hidden" name="testCount" value="${testList.size()}">
			        <input type="hidden" name="subjectCd" value="${subject.cd}">
			        <input type="hidden" name="num" value="${num}">
			        <input type="hidden" name="flg" value=1>
			        <input type="submit" value="登録して終了">
			    </div>
			</form>
			</c:when>
			<c:otherwise>
				<c:if test="${flg == 2}">
					<div>学生情報が存在しませんでした</div>
				</c:if>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<%@include file="../footer.html" %>