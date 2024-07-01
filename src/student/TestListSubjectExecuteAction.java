package student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            String entYearStr = req.getParameter("entYear");
            String classNum = req.getParameter("classNum");
            String subjectCd = req.getParameter("subjectCd");

            // Subjectオブジェクトを作成
            Subject subject = new Subject();
            SubjectDAO subjectDAO = new SubjectDAO();
            subject = subjectDAO.get(subjectCd, school);

            // --------------入力チェック----------------------------------------
            boolean hasError = false;

            // entYear(入学年度)の値が未入力かどうかのチェック(null, isEmpty()のチェックをする時にはチェックしたい値はstr型にする)
            if (entYearStr == null || entYearStr.isEmpty()) {
                // 表示するエラー文の設定
                req.setAttribute("admissionYearError", "入学年度とクラスと科目を選択してください");
                hasError = true;
            }

            // class(クラス)の値が未入力かどうかのチェック(null, isEmpty()のチェックをする時にはチェックしたい値はstr型にする)
            if (classNum == null || classNum.isEmpty()) {
                // 表示するエラー文の設定
                req.setAttribute("admissionYearError", "入学年度とクラスと科目を選択してください");
                hasError = true;
            }

            // subjectCd(科目)の値が未入力かどうかのチェック(null, isEmpty()のチェックをする時にはチェックしたい値はstr型にする)
            if (subjectCd == null || subjectCd.isEmpty()) {
                // 表示するエラー文の設定
                req.setAttribute("admissionYearError", "入学年度とクラスと科目を選択してください");
                hasError = true;
            }

            // hasError = trueの場合は以下を実行
            if (hasError) {
                // 送られてきた値を初期表示に使用するのでセットしておく
                req.setAttribute("beforeEntYear", entYearStr);
                req.setAttribute("beforeClassNum", classNum);
                req.setAttribute("beforeSubjectCd", subjectCd);
                // 必要な情報をセットしてtest_list.jspに送り返す
                Util.setEntYearSet(req); // 入学年度の情報
                Util.setClassNumSet(req); // クラス番号の情報
                Util.setSubjects(req);
                return "test_list.jsp";
            }
            // ===== 入力チェック終了 ========================================================================== //

            int entYear = Integer.parseInt(entYearStr);

            // TestListSubjectDAOを利用してテストリストを取得
            TestListSubjectDAO testListSubjectDAO = new TestListSubjectDAO();
            List<TestListSubject> testListSubjects = testListSubjectDAO.filter(entYear, classNum, subject, school);

            // 学生番号ごとにデータを統合
            Map<String, TestListSubject> mergedData = new HashMap<>();

            for (TestListSubject testSubject : testListSubjects) {

                // TestListSubject型のtestSubjectから学生番号を取得
                String studentNo = testSubject.getStudentNo();

                // Map<S,TLS>mergedDataにkeyがtestSubjectの学生番号と一致するデータが存在するかを判定
                if (mergedData.containsKey(studentNo)) {
                    TestListSubject existing = mergedData.get(studentNo);

                    // 既存のデータに1回目のスコアを追加
                    if (testSubject.getPoints().containsKey(1)) {
                        existing.getPoints().put(1, testSubject.getPoints().get(1));
                    }

                    // 既存のデータに2回目のスコアを追加
                    if (testSubject.getPoints().containsKey(2)) {
                        existing.getPoints().put(2, testSubject.getPoints().get(2));
                    }
                } else {
                    // 新しい学生データを追加する前に1回と2回の値を確認して設定する
                    if (!testSubject.getPoints().containsKey(1)) {
                        testSubject.getPoints().put(1, null);
                    }
                    if (!testSubject.getPoints().containsKey(2)) {
                        testSubject.getPoints().put(2, null);
                    }
                    mergedData.put(studentNo, testSubject);
                }
            }

            List<TestListSubject> mergedList = new ArrayList<>(mergedData.values());

            // 取得した統合済みテストリストをリクエスト属性に設定
            req.setAttribute("testListSubjects", mergedList);
            req.setAttribute("subject", subject);
            Util.setStudentEntYearSet(req);
            Util.setClassNumSet(req);
            Util.setSubjects(req);

            // フォワード先のページを指定してリクエストをフォワード
            return "test_list_student.jsp";
        } catch (Exception e) {
            // エラーハンドリング
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
