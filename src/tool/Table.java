package tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(urlPatterns = {"/test/table"})
public class Table extends HttpServlet {
	public void doGet(
			HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		try {
			// DBへの接続
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup(
					"java:/comp/env/jdbc/book");
			Connection con = ds.getConnection();

			// SQL文を記述したプリペアドステートメントを準備
			PreparedStatement st = con.prepareStatement(
					"SELECT * FROM product");
			// SQL文の実行
			ResultSet rs = st.executeQuery();
			out.println("<h1>お寿司一覧</h1>");
			out.println("<table border=1><tr><th>id</th><th>寿司名</th><th>価格</th></tr>");

			while (rs.next()) {
				out.println("<tr>");
				out.println("<td>" + rs.getInt("id") + "</td>");
				out.println("<td>" + rs.getString("name") + "</td>");
				out.println("<td>" + rs.getInt("price") + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace(out);

		}

		//Page.footer(out);
	}
}
