package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassNum;
import bean.School;
import bean.Teacher;
import dao.ClassNumDAO;
import tool.Action;
import util.Util;

public class StudentCreateAction extends Action {

    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションから教師情報を取得
        Teacher teacher = Util.getUser(req);
        Util.setEntYearSet(req);

        // 教師情報がセッションに存在しない場合、エラーページにリダイレクト
        if (teacher == null) {
            req.setAttribute("message", "セッションが切れています。再ログインしてください。");
            return "error.jsp";
        }

        // 教師の学校情報を取得
        School school = teacher.getSchool();

        // DAOを使用してクラスデータを取得
        ClassNumDAO classnum = new ClassNumDAO();
        List<ClassNum> classList = classnum.filter(school);

        // 取得したクラスデータをリクエストに設定
        req.setAttribute("classList", classList);

        return "student_create.jsp"; // 遷移先のJSPページ
    }
}
