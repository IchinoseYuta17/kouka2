package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Teacher;
import tool.Action;

public class SubjectCreateAction extends Action {

    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションから教師情報を取得
        Teacher teacher = (Teacher) req.getSession().getAttribute("teacher");

        // 教師情報がセッションに存在しない場合、エラーページにリダイレクト
        if (teacher == null) {
            req.setAttribute("message", "セッションが切れています。再ログインしてください。");
            return "error.jsp";
        }

        // 教師の学校情報を取得
        School school = teacher.getSchool();

        // 取得したクラスデータをリクエストに設定
        req.setAttribute("school", school);

        return "subject_create.jsp"; // 遷移先のJSPページ
    }
}
