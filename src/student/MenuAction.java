package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class MenuAction extends Action{
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	try{
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher != null) {
            return "index.jsp";
        } else {
            return "login.jsp"; // ログイン画面にリダイレクト
        }
    	}catch(Exception e){
    		return "error.jsp";
    	}
    }
}
