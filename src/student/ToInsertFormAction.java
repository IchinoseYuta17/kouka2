package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Course;
import dao.CourseDAO;
import tool.Action;

public class ToInsertFormAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		// コース情報を取得
		CourseDAO dao=new CourseDAO();
		List<Course> courseList=dao.courseAll();
		request.setAttribute("courseList", courseList);
		// FrontControllerを使用しているためreturn文でフォワードできる
		return "insert_form.jsp";
	}
}
