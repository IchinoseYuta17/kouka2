package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.Action;

public class ToStudentListAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		// StudentDAOの生成
		StudentDAO dao=new StudentDAO();
		// StudentDAOのstudentAllメソッドで学生を全件取得する
		List<Student> studentList=dao.studentAll();

		// "studentList"という名前でstudentListリストをセット
		// student_list.jsp側ではstudentListという名前で受け取ることができます
		request.setAttribute("studentList", studentList);


		// FrontControllerを使用しているためreturn文でフォワードできる
		return "student_list.jsp";
	}
}
