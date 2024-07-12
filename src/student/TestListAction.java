package student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import tool.Action;
import util.Util;

public class TestListAction extends Action {

    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            // セッションからTeacherオブジェクトを取得
            Teacher teacher = Util.getUser(req);

            // Teacherオブジェクトがnullの場合はログインページにリダイレクト
            if (teacher == null) {
                return "login.jsp";
            }

    		Util.setStudentEntYearSet(req);
            Util.setClassNumSet(req);
            Util.setSubjects(req);


            // JSPページにフォワード
            return "test_list.jsp";

    	}catch(Exception e){
    		return "error.jsp";
    	}
    }
}
