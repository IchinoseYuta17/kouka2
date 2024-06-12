package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.Action;

public class StudentInsertAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		// ユーザーからの入力値を受け取る
		int student_id=Integer.parseInt(request.getParameter("student_id"));
		String name=request.getParameter("student_name");
		int course_id=Integer.parseInt(request.getParameter("course_id"));

		// Studentビーンに設定
		Student student=new Student();
		student.setStudent_id(student_id);
		student.setStudent_name(name);
		student.setCourse_id(course_id);
		// StudentDAOインスタンスを生成
		StudentDAO dao=new StudentDAO();
		// StudentDAOのstudentInsertメソッドを実行してデータベースに登録
		int line = dao.insertStudent(student);
		
		// lineが0でなければ登録成功
		if (line != 0) {
			request.setAttribute("message", "登録しました");
		} else {
			request.setAttribute("message", "登録に失敗しました");
		}
		return "result.jsp";
	}
}
