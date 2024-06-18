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
import dao.ClassNumDAO;
// DAOのインポート
import dao.SchoolDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import tool.Action;


public class TestRegistExecuteAction extends Action {

    // executeメソッド：主要な処理を行う
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	HttpSession session = req.getSession();

        // セッションからユーザーデータを取得
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            req.setAttribute("message", "セッションが切れています。ログインし直してください。");
            return "login.jsp";
        }


        // 使用するDAOの生成
        SchoolDAO schoolDAO = new SchoolDAO();
		StudentDAO studentDAO = new StudentDAO();
		SubjectDAO subjectDAO = new SubjectDAO();
		ClassNumDAO classNumDAO = new ClassNumDAO();
        TestDAO testDAO = new TestDAO();




        // リクエストデータの設定
        setRequestData(req, res);

        // 入力値のチェック
        String studentNo = req.getParameter("student_no");
        String classNum = req.getParameter("class_num");
        String subjectCd = req.getParameter("subject_cd");
        String schoolCd = req.getParameter("school_cd");
        // noとpointは一旦Strにて読み込み
        String noStr = req.getParameter("no");
        String pointStr = req.getParameter("point");



        if (studentNo == null || classNum == null || subjectCd == null || schoolCd == null || noStr == null || pointStr == null) {

        	req.setAttribute("message", "全ての項目を入力してください。");
            return "test_regist.jsp";
        }


        int no;
        int point;


        try {
            no = Integer.parseInt(noStr);
            point = Integer.parseInt(pointStr);
            if (point < 0 || point > 100) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            req.setAttribute("message", "得点は0〜100範囲内の正しい値を入力してください。");
            return "test_regist.jsp";
        }


        // Testオブジェクトを作成
        Test test = new Test();
        List<Test> testList = new ArrayList<>();

        School school = schoolDAO.get(schoolCd);
        Student student = studentDAO.studentGet(studentNo, school);
        Subject subject = subjectDAO.get(subjectCd,school);

        test.setStudent(student);
        test.setClassNum(classNum);
        test.setSubject(subject);
        test.setSchool(school);
        test.setNo(no);
        test.setPoint(point);
        testList.add(test);

        // TestDaoを使用してテストをデータベースに保存

        boolean result = testDAO.save(testList);

        if (result) {
            req.setAttribute("message", "テストの登録に成功しました。");
            return "test_regist_done.jsp";
        } else {
            req.setAttribute("message", "テストの登録に失敗しました。");
            return "test_regist.jsp";
        }
    }




    // setRequestDataメソッド：リクエストデータを設定
    private void setRequestData(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // リクエストからパラメータを取得して設定
        String studentId = req.getParameter("student_id");
        String classNum = req.getParameter("class_num");
        String subjectCd = req.getParameter("subject_cd");
        String schoolCd = req.getParameter("school_cd");
        String noStr = req.getParameter("no");
        String pointStr = req.getParameter("point");

        // 取得したパラメータをリクエスト属性に設定
        req.setAttribute("student_id", studentId);
        req.setAttribute("class_num", classNum);
        req.setAttribute("subject_cd", subjectCd);
        req.setAttribute("school_cd", schoolCd);
        req.setAttribute("no", noStr);
        req.setAttribute("point", pointStr);
    }
}
