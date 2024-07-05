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

        boolean hasError = false;

        // entYearStr(入学年度)の値が未入力かどうかのチェック(null, isEmpty()のチェックをする時にはチェックしたい値はstr型にする)
        if (id == null || id.isEmpty() || password == null || password.isEmpty()){
        	// 表示するエラー文の設定
        	req.setAttribute("notIdorPsError", "IDまたはパスワードが確認できませんでした");
            hasError = true;
        }
        if (hasError) {
        	// 送られてきた値を初期表示に使用するのでセットしておく
        	req.setAttribute("beforeid", id);
        	req.setAttribute("beforepassword", password);

          return "login.jsp";
        }

        if (teacher != null) {
            HttpSession session = req.getSession();
            session.setAttribute("teacher", teacher);
            return "index.jsp"; // メニュー画面にリダイレクト
        } else {
            req.setAttribute("error", "Invalid ID or Password");
            return "login.jsp";
        }
    }
}