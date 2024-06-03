package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.Action;

public class StudentUpdateAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		// リクエストパラメータを受け取る
		int student_id=Integer.parseInt(request.getParameter("student_id"));
		String student_name=request.getParameter("student_name");
		int course_id=Integer.parseInt(request.getParameter("course_id"));

		// StudentBeanの生成
		Student student=new Student();
		student.setStudent_id(student_id);
		student.setStudent_name(student_name);
		student.setCourse_id(course_id);

		// StudentDAOの生成
		StudentDAO dao=new StudentDAO();

		// 学生の更新
		int line = dao.updateStudent(student);

		// lineが0でなければ更新成功
		if (line != 0) {
			request.setAttribute("message", "更新しました");
		} else {
			request.setAttribute("message", "更新に失敗しました");
		}
		return "result.jsp";
	}
}
