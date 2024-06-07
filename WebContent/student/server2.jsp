<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test | test data</title>
</head>
<body>

<h2 style="color:#e9967a; text-align:center">TEST DATA PAGE</h2>

<!-- studentListがリストである場合、ループを使用して各要素を表示 -->
<c:forEach var="student" items="${studentList}">
    <h3 style="color:#333; text-align:center">data1</h3>
    <p style="color:#333; text-align:center">${student.classNum}</p>

    <h3 style="color:#333; text-align:center">data2</h3>
    <p style="color:#333; text-align:center">${student.name}</p>

    <h3 style="color:#333; text-align:center">data3</h3>
    <p style="color:#333; text-align:center">${student.entYear}</p>
</c:forEach>

<p style="color:#6495ed; text-align:center"><a href="index.html">Back to Top</a></p>

</body>
</html>
