package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.Action;
import util.Util;

public class StudentCreateExecuteAction extends Action {
	public String execute(
		HttpServletRequest req, HttpServletResponse res
	) throws Exception {
	try {
		// ユーザーからの入力値を受け取る
		String entYearStr=req.getParameter("admissionYear");
		String no=req.getParameter("studentNumber");
		String name=req.getParameter("name");
		String classNum=req.getParameter("class");

		Boolean isAttend = true;

		Teacher teacher =(Teacher)req.getSession().getAttribute("teacher");

		// StudentDAOインスタンスを生成
		StudentDAO dao=new StudentDAO();


		// ===== 入力チェック ========================================================================== //
		/* この中で入力値の値のエラーの判定を行う
		 * エラーの種類
		 * ・入力値が空値のエラー（入学年度・学生番号・学生氏名）
		 * ・入力された学生番号がすでに存在する場合のエラー(学生番号の重複)
		 * ・入力された学生番号の値が指定値と反するエラー(7桁を超える場合・全角で入力された場合)
		 * ・他もある？ある場合はここに追加 */


		// エラーを判定するためのフラグhasErrorを定義(falseの場合はエラー無し。trueの場合はエラー有！)
        boolean hasError = false;

        // entYearStr(入学年度)の値が未入力かどうかのチェック(null, isEmpty()のチェックをする時にはチェックしたい値はstr型にする)
        if (entYearStr == null || entYearStr.isEmpty()) {
        	// 表示するエラー文の設定
        	req.setAttribute("admissionYearError", "入学年度を選択してください");
            hasError = true;
        }

    	// no(学生番号)の値が未入力かどうかのチェック(
        if (no == null || no.isEmpty()) {
        	// 表示するエラー文の設定
        	req.setAttribute("studentNumberError", "学生番号を入力してください");
            hasError = true;
        }

        if (classNum == null || classNum.isEmpty()) {
        	req.setAttribute("studentClassError", "クラスを選択してください");
        	hasError = true;
        }

        // 学生番号の重複があるかのチェック
		/* no:学生番号
		 * teacher.getSchool():学校情報
		 * 学生番号と学校情報をもとに学生情報(1件の学生お豆)を取得し、enrolledStudentに格納する。
		 * 学生情報の取得にはStudentDAOのstudentGet()を使用している。
		 * ここでenrolledStudentに学生の情報が入ってくる = noの学生番号を持つ学生はすでに存在しているとわかる */
        Student enrolledStudent = dao.studentGet(no, teacher.getSchool());
        // enrolledStudent(在籍中の学生)が!=null(nullではない)の場合
        if (enrolledStudent != null) {
        	// 表示するエラー文の設定
        	req.setAttribute("enrolledStudentNumberError", "学生番号が重複しています");
            hasError = true;
        }

    	// name(学生氏名)の値が未入力かどうかのチェック(
        if (name == null || name.isEmpty() || name.trim().isEmpty()) {
        	// 表示するエラー文の設定
        	req.setAttribute("nameError", "氏名を入力してください");
            hasError = true;
        }

	    int count = no.length();
        if (count > 10 || !isNumeric(no)) {
            req.setAttribute("errorNumMsg", "学生番号は数字10文字以内で入力してください。");
            hasError = true;
        }

        int count2 = name.length();
	    if (count2 > 10 || name.matches("^\\d+$")) {
	        // 表示するエラー文の設定
	        req.setAttribute("errorNameMsg", "学生氏名は文字10文字以内で入力してください。");
	        hasError = true;
	    }
        // hasError = trueの場合は以下を実行
        if (hasError) {
        	// 送られてきた値を初期表示に使用するのでセットしておく
        	req.setAttribute("beforeEntYear", entYearStr);
        	req.setAttribute("beforeNo", no);
        	req.setAttribute("beforeName", name);
        	req.setAttribute("beforeClassNum", classNum);
            // 必要な情報をセットしてstudent_create.jspに送り返す
            Util.setEntYearSet(req);	// 入学年度の情報
            Util.setClassNumSet(req);	// クラス番号の情報
            return "student_create.jsp";
        }
		// ===== 入力チェック終了 ========================================================================== //



        // 以下、Studentオブジェクトを作成し、データベースに保存する処理を行う
		int entYear = Integer.parseInt(entYearStr);

		// Studentビーンに設定
		Student student=new Student();
		student.setNo(no);
		student.setName(name);
		student.setEntYear(entYear);
		student.setClassNum(classNum);
		student.setIsAttend(isAttend);
		student.setSchool(teacher.getSchool());


		// StudentDAOのstudentInsertメソッドを実行してデータベースに登録
		boolean line = dao.studentUpdate(student);
		// 学生番号が数字のみで構成されているかどうかをチェックするメソッド

		// lineが0でなければ登録成功
		if (line != false) {
			req.setAttribute("message", "登録しました");
		} else {
			req.setAttribute("message", "登録に失敗しました");
		}
		return "student_create_done.jsp";

	}catch(Exception e){
		return "error.jsp";
	}
}
    private boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }
}
