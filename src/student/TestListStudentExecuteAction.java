package student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDAO;
import dao.TestListStudentDAO;
import tool.Action;
import util.Util;

public class TestListStudentExecuteAction extends Action {

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
            req.setAttribute("student", student);
    		Util.setStudentEntYearSet(req);
            Util.setClassNumSet(req);
            Util.setSubjects(req);

            // JSPページにフォワード
            return "test_list_student.jsp";

        } catch (Exception e) {
            // エラーハンドリング
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
