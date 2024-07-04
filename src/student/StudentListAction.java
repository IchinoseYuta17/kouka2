////教師ログインなしバージョン
//package student;
//
//import java.util.ArrayList;
//import java.util.List; // Listを使用するためのインポート
//
//import javax.servlet.http.HttpServletRequest; // HttpServletRequestを使用するためのインポート
//import javax.servlet.http.HttpServletResponse; // HttpServletResponseを使用するためのインポート
//
//import bean.Student; // Studentクラスを使用するためのインポート
//import dao.StudentDAO; // StudentDAOを使用するためのインポート
//import tool.Action;
//
//public class StudentListAction extends Action{
//
//    // executeメソッドは、HTTPリクエストを処理し、レスポンスを生成します
//    public String execute(
//            HttpServletRequest request, HttpServletResponse response
//        ) throws Exception {
//
//        // リクエストパラメータから情報を取得
//        String entYearStr = request.getParameter("year");
//        String classNum = request.getParameter("class");
//        String isAttendStr = request.getParameter("status");
//
//   //// リクエストパラメータから必要な情報を取得(テスト用：全てストリングで定義)
//////    String entYear = request.getParameter("year");
//////    String className = request.getParameter("class");
//////    String isAttend = request.getParameter("status");
////
////
//////本番用コード
//////      int entYear = Integer.parseInt(req.getParameter("data1"));
//////      String classNum = req.getParameter("data2");
//////      boolean isAttend = Boolean.parseBoolean(req.getParameter("data3"));
//
//        // パラメータのnullチェックとデフォルト値の設定
//        Integer entYear = (entYearStr != null && !entYearStr.equals("none")) ? Integer.parseInt(entYearStr) : null;
//        Boolean isAttend = (isAttendStr != null && isAttendStr.equals("1")) ? true : null;
//        classNum = (classNum != null && !classNum.equals("none")) ? classNum : null;
//        // デバッグ情報を出力
//        System.out.println("entYear: " + entYear);
//        System.out.println("classNum: " + classNum);
//        System.out.println("isAttend: " + isAttend);
//
//        // StudentDAOのインスタンスを作成
//        StudentDAO studentDAO = new StudentDAO();
//        List<Student> studentList;
//
//        // フィルタ条件に応じてDAOメソッドを呼び出す
//        if (entYear != null && classNum != null && isAttend != null) {
//            System.out.println("Calling: studentFilter(entYear, classNum, isAttend)");
//            studentList = studentDAO.studentFilter(entYear, classNum, isAttend);
//        } else if (entYear != null && isAttend != null) {
//            System.out.println("Calling: studentFilter(entYear, isAttend)");
//            studentList = studentDAO.studentFilter(entYear, isAttend);
//        } else if (isAttend != null) {
//            System.out.println("Calling: studentFilter(isAttend)");
//            studentList = studentDAO.studentFilter(isAttend);
//        } else {
//            // 全件取得など他の適切な処理を実装（例：entYearだけのフィルタなど）
//            studentList = new ArrayList<>(); // 適宜修正
//        }
//
//        // 検索結果の数を数える
//        int resultCount = studentList.size();
//
//        // リクエスト属性に学生リストを設定
//        request.setAttribute("studentList", studentList);
//        request.setAttribute("resultCount", resultCount);
//        return "student_list.jsp";
//    }
//}
//
//
//


//ーーーーーーーーーーーーーーーーーーーーーーーーーーー
//教師ログイン機能ありバージョン
package student;

import java.util.ArrayList;
import java.util.List; // リストを扱うためのライブラリをインポート

import javax.servlet.http.HttpServletRequest; // HTTPリクエストを扱うためのライブラリをインポート
import javax.servlet.http.HttpServletResponse; // HTTPレスポンスを扱うためのライブラリをインポート

import bean.School; // Schoolクラスを使用するためのインポート
import bean.Student; // Studentクラスを使用するためのインポート
import bean.Teacher; // Teacherクラスを使用するためのインポート
import dao.SchoolDAO;
import dao.StudentDAO; // StudentDAOを使用するためのインポート
import tool.Action;
import util.Util;

public class StudentListAction extends Action{

    // executeメソッドは、HTTPリクエストを処理し、レスポンスを生成する
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	// パラメータ "flg" を取得し、null チェックとデフォルト値設定
        String flgParam = req.getParameter("flg");
        int listFlg = (flgParam != null) ? Integer.parseInt(flgParam) : 0;

        // セッションに必要なリストを設定
        Util.setStudentEntYearSet(req);
        Util.setClassNumSet(req);

     // 学生管理への画面遷移の最初に表示するリストの作成
		Teacher teacher =Util.getUser(req);
		if (teacher == null){
			return "login.jsp";
		}


		StudentDAO student = new StudentDAO();
		List<Student> allStudentList = student.studentListGet(teacher);


    	if (listFlg == 0){
	        int flg = 0;
	     // 検索結果の数を数える
	        int resultCount = allStudentList.size();

	         // リクエスト属性に全生徒リストと検索結果数、flg(0)を設定
	         req.setAttribute("allStudentList", allStudentList);
	         req.setAttribute("resultCount", resultCount);
	         req.setAttribute("flg", flg);
	         return "student_list.jsp";
    	}
    	else{
    		// 検索後の画面に表示するリストの作成

        	// リクエストパラメータから検索条件を取得
            School belongSchool = teacher.getSchool();
            String schoolCd = belongSchool.getCd();
            String entYearStr = req.getParameter("year");
            String classNum = req.getParameter("class");
            String isAttendStr = req.getParameter("status");



            // 学校オブジェクトを作成し、学校コードを設定
          	SchoolDAO schoolDAO = new SchoolDAO();
            School school = new School();
            school = schoolDAO.get(schoolCd);

            // StudentDAOのインスタンスを作成
            StudentDAO studentDAO = new StudentDAO();

            boolean hasError = false;

         // 全てのフィールドが未入力かどうかのチェック
         if (entYearStr == null || entYearStr.isEmpty() ||
             classNum == null || classNum.isEmpty() ||
             isAttendStr == null || isAttendStr.isEmpty()) {
             // 表示するエラー文の設定
             req.setAttribute("allFieldsError", "入学年度、クラス、および在学中のステータスをすべて入力してください");
             hasError = true;
             int flg = 0;
          // 検索結果の数を数える
             int resultCount = allStudentList.size();

             req.setAttribute("flg", flg);
             req.setAttribute("allStudentList", allStudentList);
	         req.setAttribute("resultCount", resultCount);
         }

         if (hasError) {
             // 送られてきた値を初期表示に使用するのでセットしておく
             req.setAttribute("beforeEntYear", entYearStr);
             req.setAttribute("beforeClassNum", classNum);
             req.setAttribute("beforeIsAttend", isAttendStr);
             // 必要な情報をセットしてstudent_list.jspに送り返す
             Util.setEntYearSet(req);    // 入学年度の情報
             Util.setClassNumSet(req);   // クラス番号の情報
             return "student_list.jsp";
         }

         Integer entYear = (entYearStr != null && !entYearStr.equals("none")) ? Integer.parseInt(entYearStr) : null;
         Boolean isAttend = (isAttendStr != null && isAttendStr.equals("1")) ? true : null;
       	classNum = (classNum != null && !classNum.equals("none")) ? classNum : null;

            // 返却するリストを空で定義
            List<Student> searchedStudentList = new ArrayList<>();

            if (entYear != null && classNum != null && isAttend != null) {
//              System.out.println("Calling: studentFilter(entYear, classNum, isAttend)");
              searchedStudentList = studentDAO.studentFilter(school, entYear, classNum, isAttend);
          } else if (entYear != null && isAttend != null) {
//              System.out.println("Calling: studentFilter(entYear, isAttend)");
              searchedStudentList = studentDAO.studentFilter(school,entYear, isAttend);
          } else if (isAttend != null) {
//              System.out.println("Calling: studentFilter(isAttend)");
              searchedStudentList = studentDAO.studentFilter(school,isAttend);
          } else {
              // 全件取得など他の適切な処理を実装（例：entYearだけのフィルタなど）
        	  searchedStudentList = new ArrayList<>(); // 適宜修正
          }

//             検索結果の数を数える
            int resultCount = searchedStudentList.size();
            int flg = 1;

            // リクエスト属性に学生リストと教師情報を設定
            req.setAttribute("searchedStudentList", searchedStudentList);
            req.setAttribute("resultCount", resultCount);
            req.setAttribute("flg", flg);
            return "student_list.jsp";
        }
	}
}
