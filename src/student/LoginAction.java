//package student;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import tool.Action;
//
//public class LoginAction extends Action{
//    public String execute
//    	(HttpServletRequest req, HttpServletResponse res
//			) throws Exception {
//        // ログインページにリダイレクト
//        return "login.jsp";
//    }
//}
//

package student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// このURLパターンは ? で実行される
@WebServlet(urlPatterns={"/student/"})
public class LoginAction extends HttpServlet {
	public void doGet (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			// index.jspへフォワードするだけ
			request.getRequestDispatcher("login.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}
}
