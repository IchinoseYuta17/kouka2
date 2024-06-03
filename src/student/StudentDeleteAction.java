package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDAO;
import tool.Action;

public class StudentDeleteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		// リクエストパラメータからstudent_idを受け取る
		int student_id=Integer.parseInt(request.getParameter("student_id"));

		// DAOの生成
		StudentDAO dao=new StudentDAO();

		// メモの削除
		int line = dao.deleteStudent(student_id);

		// lineが0でなければ削除成功
		if (line != 0) {
			request.setAttribute("message", "削除しました");
		} else {
			request.setAttribute("message", "削除に失敗しました");
		}
		return "result.jsp";
	}
}
