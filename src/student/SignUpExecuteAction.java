package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;

public class SignUpExecuteAction extends Action {
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String school_cd = req.getParameter("school_cd");

        boolean hasError = false;

        // 入力バリデーション
        if (id == null || id.isEmpty()) {
            req.setAttribute("idError", "IDを入力してください");
            hasError = true;
        }
        if (password == null || password.isEmpty()) {
            req.setAttribute("passwordError", "パスワードを入力してください");
            hasError = true;
        }
        if (name == null || name.isEmpty()) {
            req.setAttribute("nameError", "名前を入力してください");
            hasError = true;
        }
        if (school_cd == null || school_cd.isEmpty()) {
            req.setAttribute("school_cdError", "学校コードを入力してください");
            hasError = true;
        }

        // エラーがあればサインアップページに戻る
        if (hasError) {
            req.setAttribute("beforeId", id);
            req.setAttribute("beforePassword", password);
            req.setAttribute("beforeName", name);
            req.setAttribute("beforeSchoolCd", school_cd);
            return "signup.jsp";
        }

        TeacherDAO teacherDao = new TeacherDAO();

        try {
            // 新しい教師を作成
            Teacher newTeacher = new Teacher();
            newTeacher.setId(id);
            newTeacher.setPassword(password);
            newTeacher.setName(name);

            // Schoolオブジェクトを作成し、学校コードを設定
            School school = new School();
            school.setCd(school_cd);
            newTeacher.setSchool(school);

            // データベースに教師を追加
            boolean isCreated = teacherDao.create(newTeacher);

            if (!isCreated) {
                req.setAttribute("signupError", "サインアップ中にエラーが発生しました。もう一度お試しください。");
                return "signup.jsp";
            }

    	}catch(Exception e){
    		return "error.jsp";
    	}

        // サインアップが成功した場合、ログインページにリダイレクト
        return "login.jsp";
    }
}
