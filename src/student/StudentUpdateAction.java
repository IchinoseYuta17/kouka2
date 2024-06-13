package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassNum;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDAO;
import dao.StudentDAO;
import tool.Action;

public class StudentUpdateAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

        // セッションから教師情報を取得
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");

		// リクエストパラメータを受け取る
		String no=request.getParameter("student_no");


		// StudentDAOの生成
		StudentDAO dao=new StudentDAO();
		Student student = dao.studentGet(no, teacher.getSchool());

		ClassNumDAO classNumDAO = new ClassNumDAO();
		List<ClassNum> classNumList = classNumDAO.filter(teacher.getSchool());

        // 取得したクラスデータをリクエストに設定
		request.setAttribute("student", student);
		request.setAttribute("teacher", teacher);
		request.setAttribute("classNumList", classNumList);

		return "student_update.jsp";
	}
}
