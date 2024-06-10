package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ログインページにリダイレクト
        req.getRequestDispatcher("/login.jsp").forward(req, res);
    }
}