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

            // タイトル表示の為のフラグを設定
            String ttl_flg = "student";
            req.setAttribute("ttl_flg", ttl_flg);

            // Teacherオブジェクトがnullの場合はログインページにリダイレクト
            if (teacher == null) {
                return "login.jsp";
            }

            // リクエストパラメータを取得
            String studentNo = req.getParameter("studentNo");


            // 学生情報を取得
            StudentDAO studentDAO = new StudentDAO();
            Student student = studentDAO.studentGet(studentNo, teacher.getSchool());


            // 送られてきた値を初期表示に使用するのでセット
            req.setAttribute("beforeStudentNo", studentNo);
            req.setAttribute("student", student);
            // セレクトボックスに必要な情報をセット
            Util.setStudentEntYearSet(req);
            Util.setClassNumSet(req);
            Util.setSubjects(req);


            // ===== 入力チェック ========================================================================== //

            // 入力パラメータの検証
            if (studentNo == null || studentNo.isEmpty()) {
                req.setAttribute("errorMsg", "学生番号を入力してください。");
                return "test_list_student.jsp";
            }
            int count = studentNo.length();
            if (count > 10 || !isNumeric(studentNo)) {
                req.setAttribute("errorMsg", "学生番号は数字10文字以内で入力してください。");
                return "test_list_student.jsp";
            }
            if (student == null) {
                req.setAttribute("errorMsg", "学生が見つかりません。");
                return "test_list_student.jsp";
            }

            // ===== 入力チェック終了 ========================================================================== //



            // 学生別のテストリストを取得
            TestListStudentDAO testListStudentDAO = new TestListStudentDAO();
            List<TestListStudent> testListStudents = testListStudentDAO.filter(student);


            // 該当データがない場合のエラーメッセージを設定
            if (testListStudents.isEmpty()) {
                req.setAttribute("errorMsg", "成績情報が存在しませんでした");
                return "test_list_student.jsp";
            }


            // 取得した学生別のリスト・学生情報を設定
            req.setAttribute("testListStudents", testListStudents);

            // JSPページにフォワード
            return "test_list_student.jsp";

    	}catch(Exception e){
    		return "error.jsp";
    	}

    }

    // 学生番号が数字のみで構成されているかどうかをチェックするメソッド
    private boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }
}