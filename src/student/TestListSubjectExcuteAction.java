package student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SchoolDAO;
import dao.SubjectDAO;
import tool.Action;
import util.Util;

public class SubjectListAction extends Action{

    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            // セッションからTeacherオブジェクトを取得
            Teacher teacher = Util.getUser(req);

            // Teacherオブジェクトがnullの場合はログインページにリダイレクト
            if (teacher == null) {
                return "login.jsp";
            }

            // 学校コードを取得
            String schoolCd = teacher.getSchool().getCd();
            SchoolDAO schoolDAO = new SchoolDAO();
            School school = schoolDAO.get(schoolCd);

            // SubjectDAOを利用して科目リストを取得
            SubjectDAO subjectDAO = new SubjectDAO();
            List<Subject> subjectList = subjectDAO.filter(school);

            // 取得した科目リストをリクエスト属性に設定
            req.setAttribute("subjectList", subjectList);

            // フォワード先のページを指定してリクエストをフォワード
            return "test_list.jsp";
        } catch (Exception e) {
            // エラーハンドリング
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}

