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

public class TestListAction extends Action {

    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            Teacher teacher = Util.getUser(req);

            if (teacher == null) {
                return "login.jsp";
            }

            String studentNo = req.getParameter("studentNo");

            StudentDAO studentDAO = new StudentDAO();
            Student student = studentDAO.studentGet(studentNo, teacher.getSchool());

            if (student == null) {
                req.setAttribute("error", "学生が見つかりません。");
                return "error.jsp";
            }

            TestListStudentDAO testListStudentDAO = new TestListStudentDAO();
            List<TestListStudent> testList = testListStudentDAO.filter(student);

            req.setAttribute("testList", testList);

            return "test_list_student.jsp";

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
