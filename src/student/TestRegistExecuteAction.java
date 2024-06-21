package student;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();

        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            req.setAttribute("message", "セッションが切れています。ログインし直してください。");
            return "login.jsp";
        }

        // 使用するDAOの生成
        StudentDAO studentDAO = new StudentDAO();
        SubjectDAO subjectDAO = new SubjectDAO();
        TestDAO testDAO = new TestDAO();

        // 登録する学生の空リストを作成
        List<Test> testList = new ArrayList<>();
        int testCount = Integer.parseInt(req.getParameter("testCount"));

        // jspからパラメーターの受け取り
        String subjectCd = req.getParameter("subjectCd");
        int num = Integer.parseInt(req.getParameter("num"));

        for (int i = 0; i < testCount; i++) {
            int entYear = Integer.parseInt(req.getParameter("entYear_" + i));
            String classNum = req.getParameter("classNum_" + i);
            String studentNo = req.getParameter("studentNo_" + i);
            String studentName = req.getParameter("studentName_" + i);
            String pointStr = req.getParameter("point_" + i);

            if (studentNo == null || classNum == null || pointStr == null) {
                req.setAttribute("message", "全ての項目を入力してください。");
                return "test_regist.jsp";
            }

            int point;
            try {
                point = Integer.parseInt(pointStr);
                if (point < 0 || point > 100) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                req.setAttribute("message", "得点は0〜100範囲内の正しい値を入力してください。");
                return "test_regist.jsp";
            }

            School school = teacher.getSchool();
            Student student = studentDAO.studentGet(studentNo, school);
            Subject subject = subjectDAO.get(subjectCd, school);

            Test test = new Test();
            student.setEntYear(entYear);
            student.setName(studentName);
            test.setStudent(student);
            test.setClassNum(classNum);
            test.setSubject(subject);
            test.setSchool(school);
            test.setNo(num);  // 回数を適切に設定
            test.setPoint(point);
            testList.add(test);
        }

        boolean result = testDAO.save(testList);

        if (result == true) {
            req.setAttribute("message", "テストの登録に成功しました。");
            return "test_regist_done.jsp";
        } else {
            req.setAttribute("message", "テストの登録に失敗しました。");
            return "test_regist.jsp";
        }
    }
}
