package student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import util.Util;

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


        // テストの回数を表示するためのリストを生成
        List<Integer> testNoList = new ArrayList<>();
        testNoList.add(1);
        testNoList.add(2);



        // セレクトボックスに必要な情報をセット
		Util.setStudentEntYearSet(req);
        Util.setClassNumSet(req);
        Util.setSubjects(req);
        req.setAttribute("testNoList", testNoList);



        // 登録する学生の空リストを作成
        List<Test> testList = new ArrayList<>();



        // jspからパラメーターの受け取り
        int testCount = Integer.parseInt(req.getParameter("testCount"));
        String subjectCd = req.getParameter("subjectCd");
        int num = Integer.parseInt(req.getParameter("num"));



        // 必要なオブジェクトを作成
        School school = teacher.getSchool();
        Subject subject = subjectDAO.get(subjectCd, school);


        // 得点のエラー箇所を判別するためのマップを定義
        Map<Integer, String> errorMessages = new HashMap<>();


        // iの値をインクリメントしながらjspからの値を取得する
        for (int i = 0; i < testCount; i++) {
            int entYear = Integer.parseInt(req.getParameter("entYear_" + i));
            String classNum = req.getParameter("classNum_" + i);
            String studentNo = req.getParameter("studentNo_" + i);
            String studentName = req.getParameter("studentName_" + i);
            String pointStr = req.getParameter("point_" + i);

            // 得点の入力が空の場合のエラー
            if (pointStr == "") {
            	errorMessages.put(i, "項目に値を入力してください。");

                // 必要なオブジェクトを作成
                Student student = studentDAO.studentGet(studentNo, school);

                Test test = new Test();

                student.setEntYear(entYear);
                student.setName(studentName);

                test.setStudent(student);
                test.setClassNum(classNum);
                test.setSubject(subject);
                test.setSchool(school);
                test.setNo(num);  // 回数
                test.setPoint(0); // 初期値の0に戻す

                // 送り返すリストに追加する
                testList.add(test);
            }
            else {
            	// 得点のエラー判定
            	int point = Integer.parseInt(pointStr);

            	// 入力値範囲のチェック
                if (point < 0 || point > 100) {
                	errorMessages.put(i, "得点は0〜100範囲内の正しい値を入力してください。");
                }

                // 必要なオブジェクトを作成
                Student student = studentDAO.studentGet(studentNo, school);

                Test test = new Test();

                student.setEntYear(entYear);
                student.setName(studentName);

                test.setStudent(student);
                test.setClassNum(classNum);
                test.setSubject(subject);
                test.setSchool(school);
                test.setNo(num);  // 回数を設定
                test.setPoint(point);

                // 送り返すリストに追加する
                testList.add(test);
            }
        }


        // errorMessagesに値が格納されていた場合はここからtest_regist.jspに画面遷移しエラーを表示
        if (!errorMessages.isEmpty()) {
        	req.setAttribute("testList", testList);
        	req.setAttribute("num", num);
        	req.setAttribute("subject", subject);
            req.setAttribute("errorMessages", errorMessages);
            return "test_regist.jsp";
        }

        // エラーがない場合にはDBに保存する
        boolean result = testDAO.save(testList);

        if (result) {
            req.setAttribute("message", "テストの登録に成功しました。");
            return "test_regist_done.jsp";
        } else {
            req.setAttribute("message", "テストの登録に失敗しました。");
            return "test_regist.jsp";
        }
    }
}
