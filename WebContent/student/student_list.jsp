<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.html" %>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>得点管理システム</title>
    <style>
        /* 必要なスタイルを追加 */
        body {
            font-family: Arial, sans-serif;
        }
        h1 {
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px auto;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .menu {
            margin: 20px;
        }
        .menu a {
            margin-right: 10px;
        }
        .form-container {
            position: relative;
            text-align: center;
            margin-bottom: 20px;
        }
        .form-container form {
            display: inline-block;
        }
        .form-container .new-registration {
            position:relative ;
            bottom: 30px;
        }
    </style>
</head>
<body>
    <div>
        <h2>学生管理</h2>
      <div>
        <div class="form-container">
            <form method="get" action="student_list.jsp">
            <div>
                <label for="year">入学年度 </label><br>

                <select id="year" name="year">
                    <option value="">---------</option>
                </select>
			</div>
			<div>
                <label for="class">クラス </label><br>
                <select id="class" name="class">
                    <option value="">---------</option>
                 </select>
			</div>
                <input type="checkbox" id="status" name="status" value="1">在学中
                <button type="submit">絞込み</button>
            </form>
            </div>
            <div>
            <a href="new_student.jsp" class="new-registration">新規登録</a>
            </div>
      </div>
        <label>検索結果:</label>
        <table>
            <tr>
                <th>入学年度</th>
                <th>学生番号</th>
                <th>氏名</th>
                <th>クラス</th>
                <th>在学中</th>
                <th>変更</th>
            </tr>
            <%
                String url = "jdbc:mysql://localhost:3306/student_management";
                String user = "root";
                String password = "password";
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;

                String year = request.getParameter("year");
                String studentClass = request.getParameter("class");
                String status = request.getParameter("status");

                String query = "SELECT * FROM students WHERE 1=1";
                if (year != null && !year.isEmpty()) {
                    query += " AND enrollment_year = ?";
                }
                if (studentClass != null && !studentClass.isEmpty()) {
                    query += " AND class = ?";
                }
                if (status != null && !status.isEmpty()) {
                    query += " AND enrolled = ?";
                }

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection(url, user, password);
                    pstmt = conn.prepareStatement(query);

                    int index = 1;
                    if (year != null && !year.isEmpty()) {
                        pstmt.setInt(index++, Integer.parseInt(year));
                    }
                    if (studentClass != null && !studentClass.isEmpty()) {
                        pstmt.setString(index++, studentClass);
                    }
                    if (status != null && !status.isEmpty()) {
                        pstmt.setBoolean(index++, "1".equals(status));
                    }

                    rs = pstmt.executeQuery();

                    while (rs.next()) {
                        int enrollmentYear = rs.getInt("enrollment_year");
                        String studentId = rs.getString("student_id");
                        String name = rs.getString("name");
                        String studentClassDb = rs.getString("class");
                        boolean enrolled = rs.getBoolean("enrolled");
            %>
            <tr>
                <td><%= enrollmentYear %></td>
                <td><%= studentId %></td>
                <td><%= name %></td>
                <td><%= studentClassDb %></td>
                <td><%= enrolled ? "○" : "×" %></td>
                <td><a href="edit_student.jsp?id=<%= studentId %>">変更</a></td>
            </tr>
            <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
                    try { if (pstmt != null) pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
                    try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
                }
            %>
        </table>
    </div>
    <%@ include file="../footer.html" %>
</body>
</html>
