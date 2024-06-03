package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.Action;

public class ToUpdateSelectAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		// DAOの生成
		StudentDAO dao=new StudentDAO();
		// すべての学生を取得
		List<Student> studentList=dao.studentAll();
		// studentListを設定してjspにフォワード
		request.setAttribute("studentList", studentList);

		return "update_select.jsp";
	}
}
