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

        try {// 指定されたidの教師を取得
            if (teacher == null) {
                // 教師が見つからない場合、エラーメッセージを設定
                req.setAttribute("notIdorPsError", "IDまたはパスワードが確認できませんでした");
                hasError = true;
            }
        } catch (Exception e) {
            // データベースアクセス中に例外が発生した場合
            req.setAttribute("notIdorPsError", "教師情報の確認中にエラーが発生しました");
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
            req.setAttribute("errorMsg", "Invalid ID or Password");
            return "error.jsp";
        }
    }
}