package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;

public class SubjectDeleteAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
		) throws Exception {

    // セッションから教師情報を取得
    Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");

	// リクエストパラメータを受け取る
	String subjectCd=request.getParameter("subject_cd");


	// StudentDAOの生成
	SubjectDAO dao=new SubjectDAO();
	Subject subject = dao.get(subjectCd, teacher.getSchool());
	if(subject == null){
		return "error.jsp";
	}
    // 取得したクラスデータをリクエストに設定
	request.setAttribute("subject", subject);
	request.setAttribute("teacher", teacher);

	return "subject_delete.jsp";
	}
}
