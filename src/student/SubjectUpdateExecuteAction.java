package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import tool.Action;
import util.Util;

public class SubjectUpdateExecuteAction extends Action {
	public String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		// ユーザーからの入力値を受け取る
		String subject_cd=request.getParameter("subject_cd");
		String subject_name=request.getParameter("subject_name");

		Teacher teacher =(Teacher)request.getSession().getAttribute("teacher");


		// SubjectDAOインスタンスを生成
		SubjectDAO dao=new SubjectDAO();

    	// 送られてきた値を初期表示に使用するのでセットしておく
    	request.setAttribute("beforeSubjectCd", subject_cd);
    	request.setAttribute("beforeSubjectName", subject_name);
		// 必要な情報をセットしてSubject_create.jspに送り返す
		Util.setSubjects(request);	// 入学年度の情報

		//科目変更のエラーチェック
		boolean hasError = false;

		// 入力値がnullの場合
	    if (subject_name == "") {
	    	// 表示するエラー文の設定
	    	request.setAttribute("nullError", "科目名を入力してください");
	        return "subject_update.jsp";
	    }

		Subject enrolledSubject = dao.getByName(subject_name, teacher.getSchool());
	    // enrolledStudent(在籍中の学生)が!=null(nullではない)の場合
	    if (enrolledSubject != null) {
	    	// 表示するエラー文の設定
	    	request.setAttribute("enrolledSubjectnameError", "科目名が重複しています");
	        hasError = true;
	    }

		// 20文字以内であるかを確認する追加コード
	    if (subject_name.length() > 20) {
	        // 表示するエラー文の設定
	        request.setAttribute("illegalnameError", "入力値が20文字以内である必要があります");
	        hasError = true;
	    }

	    if (subject_name == null || subject_name.matches("^\\d+$") || subject_name.isEmpty() || subject_name.trim().isEmpty()) {
	        // 表示するエラー文の設定
	        request.setAttribute("illegalCdError", "科目名が文字で構成されていません");
	        hasError = true;
	    }



	    if (hasError) {
	      return "subject_update.jsp";
	    }

		// Subjectビーンに設定
		Subject subject=new Subject();
		subject.setCd(subject_cd);
		subject.setName(subject_name);
		subject.setSchool(teacher.getSchool());

		// SubjectDAOのinsertメソッドを実行してデータベースに登録
		boolean line = dao.save(subject);



		// lineが0でなければ登録成功
		if (line != false) {
			request.setAttribute("message", "登録しました");
		} else {
			request.setAttribute("message", "登録に失敗しました");
		}
		return "subject_update_done.jsp";
	}
}
