	<%@page contentType="text/html; charset=UTF-8" %>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@include file="../header.html" %>
	<%@include file="header.jsp" %>
	<%@include file="menu.jsp" %>

	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html lang="ja">
	<head>
	  <meta charset="UTF-8">
	<style>
			h2 {
	        	text-align: left;
	        	padding:5px 20px;
	        	background-color: #f2f2f2;
	        }

	        .main-container{
	        	width:80%;
	        	margin: 0px auto;

	        }

	        .form-container{
	        	display:flex;
	        	flex-direction:column;
	        	margin-bottom:20px;
	        	margin-left:15px;
				}

			.form-group{
			margin-bottom:5px;
			}


	        }
	        .form-group label{
	        	display:block;

	        }

	        .readonly{
	         border: none;
	        }

	        .form-input{
			width:100%;
	        }

			.form-input label{
			margin-bottom:10px;

			}

			.form-input input{
			width:100%;
			margin-bottom:15px;
			padding:8px;
	       	border:1px solid #ccc;
	        border-radius: 4px;
			}

			#grade {
			margin-bottom:-10px;
			padding:8px;
	        border:1px solid #ccc;
	        border-radius: 4px;
			}

			.form-input select{
			width:100%;
			margin-bottom:15px;
			}


	        .form-buttons input{
	        display:block;
	          background-color: blue;
	          color: #fff;
	          border:none;
	          padding: 10px 20px;
	          cursor:pointer;
	          border-radius: 4px;
	                  }

			.form-buttons input{
			margin-top:15px;
			margin-bottom:10px;
			}

			.form-label{
			 display:flex;
			}

			.form-label input{
			margin-bottom:15px;
			}
	        footer{
	        posion:absolute;
	        bottom:0;
	        }
	        label{
	        	display:flex;
	        	align-items: center;
	        }

	</style>
	</head>
	<body>
	  <div class="main-container">
	    <h2>学生情報変更</h2>
	    <form id="myForm" action="student_update_done.jsp" method="post">
	    <div class="form-container">
	      <div class="form-group">
	        <label for="entranceYear">入学年度</label>
	         <input class="readonly" type="number" id="entranceYear" name="entYear" value="${student.entYear}" readonly>
	      </div>

	      <div class="form-group">
	        <label for="studentNumber">学生番号</label>
	        <input class="readonly" type="text" id="studentNumber" name="student_no" value="${student.no}" readonly >
	      </div>

	      <div class="form-input">
	        <label for="name">氏名</label>
	        <input type="text" id="name" name="name" placeholder="${student.name}" >
	        <label for="grade">クラス</label>

	        <select id="grade" name="class_num">
				<c:forEach var="classNum" items="${classNumList}">
	        		<option value="${classNum.num}">${classNum.num}</option>
				</c:forEach>
	        </select>


	      </div>


	      <div class="form-buttons">
	      	<div class="form-label">
	        <label for="is_attend">在学中</label>
	        <input type="checkbox" id="is_attend" name="is_attend" value="1"></div>
	        <input type="submit" value="変更">
	 		<a href="student_list.jsp" >戻る</a>
		 </div>
	 	</div>
	   </form>
	  </div>
	</body>
	</html>
	<%@include file="../footer.html" %>
