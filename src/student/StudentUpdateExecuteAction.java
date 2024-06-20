package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		// ユーザーからの入力値を受け取る
		//(このかたまりの受け取り名・受け取り方法などまだ確認が必要)
		int entYear=Integer.parseInt(request.getParameter("ent_year"));
		String no=request.getParameter("student_no");
		String name=request.getParameter("name");
		String classNum=request.getParameter("class_num");
		String isAttendStr = request.getParameter("is_attend");
		Boolean isAttend = (isAttendStr != null && isAttendStr.equals("1")) ? true : false;

		// セッションから教師情報を取得
		Teacher teacher =(Teacher)request.getSession().getAttribute("teacher");

		// Studentビーンに設定
		Student student=new Student();
		student.setNo(no);
		student.setName(name);
		student.setEntYear(entYear);
		student.setClassNum(classNum);
		student.setIsAttend(isAttend);
		student.setSchool(teacher.getSchool());



		// StudentDAOの生成
		StudentDAO dao=new StudentDAO();

		// 学生の更新
		Boolean line = dao.studentUpdate(student);

		// lineが0でなければ更新成功
		if (line != false) {
			request.setAttribute("message", "更新しました");
		} else {
			request.setAttribute("message", "更新に失敗しました");
		}
		return "student_update_done.jsp";
	}
}
