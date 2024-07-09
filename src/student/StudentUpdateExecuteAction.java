package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.Action;
import util.Util;

public class StudentUpdateExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		// ユーザーからの入力値を受け取る
		//(このかたまりの受け取り名・受け取り方法などまだ確認が必要)
		int entYear=Integer.parseInt(request.getParameter("ent_year"));
		String no=request.getParameter("student_no");	// 学生番号
		String name=request.getParameter("name");
		String classNum=request.getParameter("class_num");
		String isAttendStr = request.getParameter("is_attend");
		Boolean isAttend = (isAttendStr != null && isAttendStr.equals("1")) ? true : false;

		// セッションから教師情報を取得
		Teacher teacher =(Teacher)request.getSession().getAttribute("teacher");


		// StudentDAOの生成
		StudentDAO dao=new StudentDAO();


	// ===== 入力チェック ========================================================================== //
	/* この中で入力値の値のエラーの判定を行う
	 * エラーの種類
	 * ・入力値が空値のエラー（学生氏名） */


	// エラーを判定するためのフラグhasErrorを定義(falseの場合はエラー無し。trueの場合はエラー有！)
	    boolean hasError = false;

		// name(学生氏名)の値が未入力かどうかのチェック(
	    if (name == null || name.isEmpty()) {
	    	// 表示するエラー文の設定
	    	request.setAttribute("nameError", "氏名を入力してください");
	        hasError = true;
	    }

	    int count2 = name.length();
        if (count2 > 10 || !isAlphabetic(name)) {
            request.setAttribute("errorMsg", "学生番号は文字10文字以内で入力してください。");
            hasError = true;
        }

	    // hasError = trueの場合は以下を実行
	    if (hasError) {
	    	Student beforeStudent = dao.studentGet(no, teacher.getSchool());
	    	// 送られてきた値を初期表示に使用するのでセットしておく
			request.setAttribute("student", beforeStudent);
			request.setAttribute("beforeClassNum", classNum);
			request.setAttribute("beforeIsAttend", isAttend);
			// 必要な情報をセットしてstudent_create.jspに送り返す
			Util.setClassNumSet(request);	// クラス番号の情報
			return "student_update.jsp";
	    }
	// ===== 入力チェック終了 ========================================================================== //




		// Studentビーンに設定
		Student student=new Student();
		student.setNo(no);
		student.setName(name);
		student.setEntYear(entYear);
		student.setClassNum(classNum);
		student.setIsAttend(isAttend);
		student.setSchool(teacher.getSchool());


		// 学生の更新
		Boolean line = dao.studentUpdate(student);

		// lineが0でなければ更新成功
		if (line != false) {
			request.setAttribute("message", "更新しました");
		} else {
			request.setAttribute("message", "更新に失敗しました");
		}
		return "student_update_done.jsp";
	}
    private boolean isAlphabetic(String str) {
        return str != null && str.matches("[\\p{L}]+");
    }
}
