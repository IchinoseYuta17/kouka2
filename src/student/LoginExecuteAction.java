package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;

public class LoginExecuteAction extends Action {
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        TeacherDAO teacherDao = new TeacherDAO();
        Teacher teacher = teacherDao.login(id, password);

        if (teacher != null) {
            HttpSession session = req.getSession();
            session.setAttribute("teacher", teacher);
            return "index.jsp"; // メニュー画面にリダイレクト
        } else {
            req.setAttribute("error", "Invalid ID or Password");
            return "error.jsp";
        }
    }
}