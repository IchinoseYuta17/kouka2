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

public class TestListStudentExecuteAction extends Action{

    // executeメソッドは、HTTPリクエストを処理し、レスポンスを生成する
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	// パラメータ "flg" を取得し、null チェックとデフォルト値設定
        String flgParam = req.getParameter("flg");
        int listFlg = (flgParam != null) ? Integer.parseInt(flgParam) : 0;

    	if (listFlg == 0){

		Teacher teacher =(Teacher)req.getSession().getAttribute("teacher");
		if (teacher == null){
			return "login.jsp";
		}

		StudentDAO student = new StudentDAO();
		List<Student> allStudentList = student.studentListGet(teacher);

        // 検索結果の数を数える
        int resultCount = allStudentList.size();
        int flg = 0;

         // リクエスト属性に全生徒リストと検索結果数、flg(0)を設定
         req.setAttribute("allStudentList", allStudentList);
         req.setAttribute("resultCount", resultCount);
         req.setAttribute("flg", flg);
         return "student_list.jsp";
    	}
    	else{
        	// リクエストパラメータから検索条件を取得
    		Teacher teacher =(Teacher)req.getSession().getAttribute("teacher");
            School belongSchool = teacher.getSchool();
            String schoolCd = belongSchool.getCd();
            String entYearStr = req.getParameter("year");
            String classNum = req.getParameter("class");
            String isAttendStr = req.getParameter("status");

            Integer entYear = (entYearStr != null && !entYearStr.equals("none")) ? Integer.parseInt(entYearStr) : null;
            Boolean isAttend = (isAttendStr != null && isAttendStr.equals("1")) ? true : null;
          	classNum = (classNum != null && !classNum.equals("none")) ? classNum : null;

            // 学校オブジェクトを作成し、学校コードを設定
          	SchoolDAO schoolDAO = new SchoolDAO();
            School school = new School();
            school = schoolDAO.get(schoolCd);

            // StudentDAOのインスタンスを作成
            StudentDAO studentDAO = new StudentDAO();

            // 返却するリストを空で定義
            List<Student> searchedStudentList = new ArrayList<>();

            // 教師情報を取得（仮に、教師情報は特定の学校に紐づいているとする）
//            Teacher teacher = new Teacher(); // 実際にはDAOから取得する
//            teacher.setId("teacherId"); // 仮の教師ID
//            teacher.setName("teacherName"); // 仮の教師名
//            teacher.setSchool(school); // 学校情報を設定

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




