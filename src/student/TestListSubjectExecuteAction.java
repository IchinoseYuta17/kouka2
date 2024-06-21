package student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.SubjectDAO;
import dao.TestListSubjectDAO;
import tool.Action;
import util.Util;

public class TestListSubjectExecuteAction extends Action {

    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            // セッションからTeacherオブジェクトを取得
            Teacher teacher = Util.getUser(req);

            // Teacherオブジェクトがnullの場合はログインページにリダイレクト
            if (teacher == null) {
                return "login.jsp";
            }

            // 学校オブジェクトを取得
            School school = teacher.getSchool();

            // リクエストから入学年度、クラス、および科目を取得
            int entYear = Integer.parseInt(req.getParameter("entYear"));
            String classNum = req.getParameter("classNum");
            String subjectCd = req.getParameter("subjectCd");

            // Subjectオブジェクトを作成
            Subject subject = new Subject();
            SubjectDAO subjectDAO = new SubjectDAO();
            subject = subjectDAO.get(subjectCd, school);

            // TestListSubjectDAOを利用してテストリストを取得
            TestListSubjectDAO testListSubjectDAO = new TestListSubjectDAO();
            List<TestListSubject> testListSubjects = testListSubjectDAO.filter(entYear, classNum, subject, school);

            // 取得したテストリストをリクエスト属性に設定
            req.setAttribute("testListSubjects", testListSubjects);

            // フォワード先のページを指定してリクエストをフォワード
            return "test_list_student.jsp";
        } catch (Exception e) {
            // エラーハンドリング
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
