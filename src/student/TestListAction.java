package student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import bean.TestListSubject;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestListStudentDAO;
import dao.TestListSubjectDAO;
import tool.Action;
import util.Util;

public class TestListAction extends Action {

    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            // セッションからTeacherオブジェクトを取得
            Teacher teacher = Util.getUser(req);

            // Teacherオブジェクトがnullの場合はログインページにリダイレクト
            if (teacher == null) {
                return "login.jsp";
            }

            // リクエストパラメータを取得
            String studentNo = req.getParameter("studentNo");
            String entYearParam = req.getParameter("entYear");
            String classNum = req.getParameter("classNum");
            String subjectCd = req.getParameter("subjectCd");

            // 入力パラメータの検証
            if (studentNo == null || studentNo.isEmpty()) {
                req.setAttribute("errorMsg", "学生番号を入力してください。");
                return "test_list_student.jsp";
            }

            // 学生情報を取得
            StudentDAO studentDAO = new StudentDAO();
            Student student = studentDAO.studentGet(studentNo, teacher.getSchool());

            if (student == null) {
                req.setAttribute("errorMsg", "学生が見つかりません。");
                return "test_list_student.jsp";
            }

            // テストリストを取得
            TestListStudentDAO testListStudentDAO = new TestListStudentDAO();
            List<TestListStudent> testListStudents = testListStudentDAO.filter(student);

            req.setAttribute("testListStudents", testListStudents);

            // 科目別の処理
            if (entYearParam != null && !entYearParam.isEmpty() &&
                classNum != null && !classNum.isEmpty() &&
                subjectCd != null && !subjectCd.isEmpty()) {

                int entYear = Integer.parseInt(entYearParam);
                School school = teacher.getSchool();
                SubjectDAO subjectDAO = new SubjectDAO();
                Subject subject = subjectDAO.get(subjectCd, school);

                TestListSubjectDAO testListSubjectDAO = new TestListSubjectDAO();
                List<TestListSubject> testListSubjects = testListSubjectDAO.filter(entYear, classNum, subject, school);

                req.setAttribute("testListSubjects", testListSubjects);
            }

            // JSPページにフォワード
            return "test_list_student.jsp";

        } catch (Exception e) {
            // エラーハンドリング
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
