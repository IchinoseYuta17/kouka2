package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;
import util.Util;

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

		//科目登録のエラーチェック
		boolean hasError = false;

		Subject enrolledSubject2 = dao.getByName(subject_name, teacher.getSchool());
	    // enrolledStudent(在籍中の学生)が!=null(nullではない)の場合
	    if (enrolledSubject2 != null) {
	    	// 表示するエラー文の設定
	    	request.setAttribute("enrolledSubjectnameError", "科目名が重複しています");
	        hasError = true;
	    }

		Subject enrolledSubject = dao.get(subject_cd, teacher.getSchool());
	    // enrolledStudent(在籍中の学生)が!=null(nullではない)の場合
	    if (enrolledSubject != null) {
	    	// 表示するエラー文の設定
	    	request.setAttribute("enrolledSubjectCdError", "科目コードが重複しています");
	        hasError = true;
	    }

	    if (subject_cd == null || !subject_cd.matches("^[A-Z]\\d{2}$")) {
	        // 表示するエラー文の設定
	        request.setAttribute("illegalCdError", "入力値が大英字一文字と数字二文字で構成されていません");
	        hasError = true;
	    }



	    if (hasError) {
	    	// 送られてきた値を初期表示に使用するのでセットしておく
	    	request.setAttribute("beforeSubjectCd", subject_cd);
	    	request.setAttribute("beforeSubjectName", subject_name);
	      // 必要な情報をセットしてSubject_create.jspに送り返す
	      Util.setSubjects(request);	// 入学年度の情報
	      return "subject_create.jsp";
	    }



		// SubjectDAOのinsertメソッドを実行してデータベースに登録
		boolean line = dao.save(subject);

		// lineが0でなければ登録成功
		if (line != false) {
			request.setAttribute("message", "登録しました");
		} else {
			request.setAttribute("message", "登録に失敗しました");
		}
		return "subject_create_done.jsp";
	}
}
