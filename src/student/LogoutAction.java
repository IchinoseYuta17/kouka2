// LogoutAction.java
package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
            System.out.println("Session invalidated.");
        } else {
            System.out.println("No session found.");
        }

        return "logout.jsp"; // ログインページにリダイレクト
    }
}
