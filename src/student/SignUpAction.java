package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SignUpAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "signup.jsp";
    }
}
