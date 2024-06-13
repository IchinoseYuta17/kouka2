package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		// ユーザーからの入力値を受け取る
		String subject_cd=request.getParameter("subject_cd");
		String subject_name=request.getParameter("subject_name");

		Teacher teacher =(Teacher)request.getSession().getAttribute("teacher");

		// Subjectビーンに設定
		Subject subject=new Subject();
		subject.setCd(subject_cd);
		subject.setName(subject_name);
		subject.setSchool(teacher.getSchool());

		// SubjectDAOインスタンスを生成
		SubjectDAO dao=new SubjectDAO();
		// SubjectDAOのinsertメソッドを実行してデータベースに登録
		boolean line = dao.insert(subject);

		// lineが0でなければ登録成功
		if (line != false) {
			request.setAttribute("message", "登録しました");
		} else {
			request.setAttribute("message", "登録に失敗しました");
		}
		return "subject_create_done.jsp";
	}
}
