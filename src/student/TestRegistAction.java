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
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	try{
        // セッションからユーザーデータを取得
        Teacher teacher = Util.getUser(req);


    	// パラメータ "flg" を取得し、nullチェックとデフォルト値(0)を設定
        String flgParam = req.getParameter("flg");
        int listFlg = (flgParam != null) ? Integer.parseInt(flgParam) : 0;



        // 使用するDAOの生成
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



        // 入力値の受け取り(entYear/noは一旦Strにて読み込み)
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3"); // subjectの情報は表示は科目名で表示をし、こちらで受け取る情報は科目コードを受け取る
        String noStr = req.getParameter("f4");

        // 入力値のチェック、必要に応じて変換・デフォルト値(null)を設定
        Integer entYear = (entYearStr != null && !entYearStr.isEmpty()) ? Integer.parseInt(entYearStr) : null;
      	classNum = (classNum != null && !classNum.isEmpty()) ? classNum : null;
        subjectCd = (subjectCd != null && !subjectCd.isEmpty()) ? subjectCd : null;
        Integer num = (noStr != null && !noStr.isEmpty()) ? Integer.parseInt(noStr) : null;


        // 送られてきた値を初期表示に使用するのでセット
        req.setAttribute("beforeEntYear", entYearStr);
        req.setAttribute("beforeClassNum", classNum);
        req.setAttribute("beforeSubjectCd", subjectCd);
        req.setAttribute("beforeNo", noStr);



    	// 成績登録の初期画面の表示
    	if (listFlg == 0){

    		// teacherのセッションのエラーチェック
            if (teacher == null) {
                req.setAttribute("message", "セッションが切れています。ログインし直してください。");
                return "login.jsp";
            }

            // test_regist.jspにフォワードする(検索のみの画面)
            return "test_regist.jsp";

    	}



    	// 入力値不足エラーの処理
        if (listFlg == 1 && (entYear == null || classNum == null || subjectCd == null || num == null)) {

        	req.setAttribute("flg",listFlg);
        	req.setAttribute("errorMsg", "全ての項目を入力してください。");
            return "test_regist.jsp";
        }


        // testDAOのメソッドで使用するsubject / school オブジェクトを作成
        Subject subject = subjectDAO.get(subjectCd,teacher.getSchool());
        School school = teacher.getSchool();

        // testListを作成
        List<Test> testList = new ArrayList<>();
        // TestDaoを使用してデータ一覧を取得
        testList = testDAO.filter(entYear, classNum, subject, num, school);
        // testListにデータが返ってこなかった場合のエラー表示の為listFlgに2を代入
        listFlg = 2;
        req.setAttribute("subject", subject);
        req.setAttribute("num", num);
        req.setAttribute("testList", testList);
        req.setAttribute("flg", listFlg);
        return "test_regist.jsp";
    	}catch(Exception e){
    		return "error.jsp";
    	}
    }


//  // リクエストデータの設定（謎メソッド!!!）
//  setRequestData(req, res);


    // このメソッドには何をさせたい？よくわからん
    // setRequestDataメソッド：リクエストデータを設定
//    private void setRequestData(HttpServletRequest req, HttpServletResponse res) throws Exception {
//        // リクエストからパラメータを取得して設定
//        String studentId = req.getParameter("student_id");
//        String classNum = req.getParameter("class_num");
//        String subjectCd = req.getParameter("subject_cd");
//        String schoolCd = req.getParameter("school_cd");
//        String noStr = req.getParameter("no");
//        String pointStr = req.getParameter("point");
//
//        // 取得したパラメータをリクエスト属性に設定
//        req.setAttribute("student_id", studentId);
//        req.setAttribute("class_num", classNum);
//        req.setAttribute("subject_cd", subjectCd);
//        req.setAttribute("school_cd", schoolCd);
//        req.setAttribute("no", noStr);
//        req.setAttribute("point", pointStr);
//    }
}
