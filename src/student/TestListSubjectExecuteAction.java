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
            int entYear = Integer.parseInt(req.getParameter("entYear"));
            String classNum = req.getParameter("classNum");
            String subjectCd = req.getParameter("subjectCd");

            // Subjectオブジェクトを作成
            Subject subject = new Subject();
            SubjectDAO subjectDAO = new SubjectDAO();
            subject = subjectDAO.get(subjectCd, school);

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

