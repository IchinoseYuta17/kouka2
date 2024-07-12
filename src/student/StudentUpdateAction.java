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
import util.Util;

public class StudentUpdateAction extends Action {
	public String execute(
		HttpServletRequest req, HttpServletResponse res
	) throws Exception {
		try{

        // セッションから教師情報を取得
        Teacher teacher = (Teacher) req.getSession().getAttribute("teacher");
        Util.setClassNumSet(req);

		// リクエストパラメータを受け取る
		String no=req.getParameter("student_no");


		// StudentDAOの生成
		StudentDAO dao=new StudentDAO();
		Student student = dao.studentGet(no, teacher.getSchool());

		ClassNumDAO classNumDAO = new ClassNumDAO();
		List<ClassNum> classNumList = classNumDAO.filter(teacher.getSchool());

        // 取得したクラスデータをリクエストに設定
		req.setAttribute("student", student);
		req.setAttribute("teacher", teacher);
		req.setAttribute("classNumList", classNumList);

		return "student_update.jsp";
	}catch(Exception e){
		return "error.jsp";
	}
	}
}
