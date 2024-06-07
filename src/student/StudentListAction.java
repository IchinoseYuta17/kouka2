//教師ログインなしバージョン
package student;

import java.util.List; // Listを使用するためのインポート

import javax.servlet.http.HttpServletRequest; // HttpServletRequestを使用するためのインポート
import javax.servlet.http.HttpServletResponse; // HttpServletResponseを使用するためのインポート

import bean.Student; // Studentクラスを使用するためのインポート
import dao.StudentDAO; // StudentDAOを使用するためのインポート
import tool.Action;

public class StudentListAction extends Action{

    // executeメソッドは、HTTPリクエストを処理し、レスポンスを生成します
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {
        // リクエストパラメータから必要な情報を取得

      String entYear = request.getParameter("data1");
      String className = request.getParameter("data2");
      String isAttend = request.getParameter("data3");




//        int entYear = Integer.parseInt(req.getParameter("data1"));
//        String classNum = req.getParameter("data2");
//        boolean isAttend = Boolean.parseBoolean(req.getParameter("data3"));


        // StudentDAOのインスタンスを作成
        StudentDAO studentDAO = new StudentDAO();

        // 条件に基づいて学生リストを取得
        List<Student> studentList = studentDAO.studentFilter4(entYear, className, isAttend);

        // リクエスト属性に学生リストを設定
        request.setAttribute("studentList", studentList);
        return "server2.jsp";
    }
}





//ーーーーーーーーーーーーーーーーーーーーーーーーーーー
//教師ログイン機能ありバージョン
//package tool;
//
//import java.util.List; // リストを扱うためのライブラリをインポート
//
//import javax.servlet.http.HttpServletRequest; // HTTPリクエストを扱うためのライブラリをインポート
//import javax.servlet.http.HttpServletResponse; // HTTPレスポンスを扱うためのライブラリをインポート
//
//import bean.School; // Schoolクラスを使用するためのインポート
//import bean.Student; // Studentクラスを使用するためのインポート
//import bean.Teacher; // Teacherクラスを使用するためのインポート
//import dao.StudentDAO; // StudentDAOを使用するためのインポート
//
//public class StudentListAction {
//
//    // executeメソッドは、HTTPリクエストを処理し、レスポンスを生成する
//    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
//        // リクエストパラメータから必要な情報を取得
//        String schoolCd = req.getParameter("schoolCd");
//        int entYear = Integer.parseInt(req.getParameter("entYear"));
//        String classNum = req.getParameter("classNum");
//        boolean isAttend = Boolean.parseBoolean(req.getParameter("isAttend"));
//
//        // 学校オブジェクトを作成し、学校コードを設定
//        School school = new School();
//        school.setCd(schoolCd);
//
//        // StudentDAOのインスタンスを作成
//        StudentDAO studentDAO = new StudentDAO();
//
//        // 条件に基づいて学生リストを取得
//        List<Student> studentList = studentDAO.studentFilter4(school, entYear, classNum, isAttend);
//
//        // 教師情報を取得（仮に、教師情報は特定の学校に紐づいているとする）
//        Teacher teacher = new Teacher(); // 実際にはDAOから取得する
//        teacher.setId("teacherId"); // 仮の教師ID
//        teacher.setName("teacherName"); // 仮の教師名
//        teacher.setSchool(school); // 学校情報を設定
//
//        // リクエスト属性に学生リストと教師情報を設定
//        req.setAttribute("studentList", studentList);
//        req.setAttribute("teacher", teacher);
//
//        // フォワード先の設定（仮にJSPにフォワードすると仮定）
//        req.getRequestDispatcher("/WEB-INF/jsp/studentList.jsp").forward(req, res);
//    }
//}
