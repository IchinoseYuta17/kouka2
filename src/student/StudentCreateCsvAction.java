package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import tool.Action;
import util.Util;

public class StudentCreateCsvAction extends Action {

    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	try{
        // セッションから教師情報を取得
        Teacher teacher = Util.getUser(req);
        Util.setEntYearSet(req);
        Util.setClassNumSet(req);

        // 教師情報がセッションに存在しない場合、エラーページにリダイレクト
        if (teacher == null) {
            req.setAttribute("message", "セッションが切れています。再ログインしてください。");
            return "error.jsp";
        }
       return "create_with_csv.jsp"; // 遷移先のJSPページ
    	}catch(Exception e){
    		return "error.jsp";
    	}
    }
}
