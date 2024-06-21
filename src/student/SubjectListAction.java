package student;

import java.util.ArrayList;
import java.util.List; // リストを扱うためのライブラリをインポート

import javax.servlet.http.HttpServletRequest; // HTTPリクエストを扱うためのライブラリをインポート
import javax.servlet.http.HttpServletResponse; // HTTPレスポンスを扱うためのライブラリをインポート

import bean.Subject; // Studentクラスを使用するためのインポート
import bean.Teacher; // Teacherクラスを使用するためのインポート
import dao.SubjectDAO; // StudentDAOを使用するためのインポート
import tool.Action;
import util.Util;

public class SubjectListAction extends Action{

// executeメソッドは、HTTPリクエストを処理し、レスポンスを生成する
public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

	Teacher teacher = Util.getUser(req);
	SubjectDAO subjectDAO = new SubjectDAO();

	List<Subject> subjectList = new ArrayList<>();
	subjectList = subjectDAO.filter(teacher.getSchool());

	req.setAttribute("subjectList", subjectList);
	return "subject_list.jsp";
	}
}

