package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Course;
import bean.Student;
import dao.CourseDAO;
import dao.StudentDAO;
import tool.Action;

public class ToUpdateFormAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// リクエストパラメータからstudent_idを受け取る
		int student_id = Integer.parseInt(request.getParameter("student_id"));

		// StudentDAOの生成
		StudentDAO dao = new StudentDAO();
		// 対象の学生を取得
		Student student = dao.searchStudent(student_id);

		// CourseDAOの生成
		CourseDAO dao2 = new CourseDAO();
		// コースを全件取得
		List<Course> courseList = dao2.courseAll();

		// studentとcourseListを設定してjspにフォワード
		request.setAttribute("student", student);
		request.setAttribute("courseList", courseList);

		return "update_form.jsp";
	}
}
