package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.Action;

public class StudentCreateExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		// ユーザーからの入力値を受け取る
		int entYear=Integer.parseInt(request.getParameter("admissionYear"));
		String no=request.getParameter("studentNumber");
		String name=request.getParameter("name");
		String classNum=request.getParameter("class");

		Boolean isAttend = true;

		Teacher teacher =(Teacher)request.getSession().getAttribute("teacher");

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
			request.setAttribute("message", "登録しました");
		} else {
			request.setAttribute("message", "登録に失敗しました");
		}
		return "student_create_done.jsp";
	}
}
