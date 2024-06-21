package student;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.SubjectDAO;
import dao.TestDAO;
import tool.Action;
import util.Util;


public class TestRegistAction extends Action {

    // executeメソッド：主要な処理を行う
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {


        // セッションからユーザーデータを取得
        Teacher teacher = Util.getUser(req);;
        if (teacher == null) {
            req.setAttribute("message", "セッションが切れています。ログインし直してください。");
            return "login.jsp";
        }


        // 使用するDAOの生成
		SubjectDAO subjectDAO = new SubjectDAO();
        TestDAO testDAO = new TestDAO();


		// リクエスト属性にクラスリストと科目リストを設定
		Util.setStudentEntYearSet(req);
        Util.setClassNumSet(req);
        Util.setSubjects(req);




        // リクエストデータの設定（謎メソッド!!!）
        setRequestData(req, res);





        // 入力値のチェック(entYear/noは一旦Strにて読み込み)
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        // subjectの情報は表示は科目名で表示をし、こちらで受け取る情報は科目コードを受け取る
        String subjectCd = req.getParameter("f3");
        String noStr = req.getParameter("f4");



        if (entYearStr == null || classNum == null || subjectCd == null || noStr == null) {
        	req.setAttribute("message", "全ての項目を入力してください。");
            return "test_regist.jsp";
        }



        // Strからintへキャスト
        int entYear = Integer.parseInt(entYearStr);
        int num = Integer.parseInt(noStr);


        Subject subject = subjectDAO.get(subjectCd,teacher.getSchool());
        School school = teacher.getSchool();

        // 一覧で返すtestListを作成
        List<Test> testList = new ArrayList<>();
        // TestDaoを使用してデータ一覧を取得
        testList = testDAO.filter(entYear, classNum, subject, num, school);

        int flg = 1;

        req.setAttribute("subject", subject);
        req.setAttribute("num", num);
        req.setAttribute("testList", testList);
        req.setAttribute("flg", flg);
        return "test_regist.jsp";

    }




    // このメソッドには何をさせたい？よくわからん
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
