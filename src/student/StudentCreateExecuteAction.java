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
		// ユーザーからの入力値を受け取る
		String entYearStr=req.getParameter("admissionYear");
		String no=req.getParameter("studentNumber");
		String name=req.getParameter("name");
		String classNum=req.getParameter("class");

		Boolean isAttend = true;

		Teacher teacher =(Teacher)req.getSession().getAttribute("teacher");




		// 入力チェック
        boolean hasError = false;

        if (entYearStr == null || entYearStr.isEmpty()) {
        	req.setAttribute("admissionYearError", "入学年度を選択してください");
            hasError = true;
        }
        if (no == null || no.isEmpty()) {
        	req.setAttribute("studentNumberError", "学生番号を入力してください");
            hasError = true;
        }
        if (name == null || name.isEmpty()) {
        	req.setAttribute("nameError", "氏名を入力してください");
            hasError = true;
        }

        if (hasError) {
            Util.setEntYearSet(req);
            Util.setClassNumSet(req);
            return "student_create.jsp";
        }

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

		// StudentDAOインスタンスを生成
		StudentDAO dao=new StudentDAO();
		// StudentDAOのstudentInsertメソッドを実行してデータベースに登録
		boolean line = dao.studentUpdate(student);

		// lineが0でなければ登録成功
		if (line != false) {
			req.setAttribute("message", "登録しました");
		} else {
			req.setAttribute("message", "登録に失敗しました");
		}
		return "student_create_done.jsp";
	}
}
