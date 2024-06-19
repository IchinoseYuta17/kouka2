package util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import bean.ClassNum;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;

public class Util {

    // セッションからTeacherオブジェクトを取得するメソッド
    public static Teacher getUser(HttpServletRequest request) {
        return (Teacher) request.getSession().getAttribute("teacher");
    }

    // リクエスト属性にクラス番号のセットを設定するメソッド
    public static void setClassNumSet(HttpServletRequest request) {
        try {
            Teacher teacher = getUser(request);
            if (teacher != null) {
                ClassNumDAO classNumDAO = new ClassNumDAO();
                List<ClassNum> classNumSet = classNumDAO.filter(teacher.getSchool());
                request.setAttribute("classNumSet", classNumSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    // リクエスト属性に入学年度のセットを設定するメソッド
//    public static void setEntYearSet(HttpServletRequest request) {
//        try {
//            Teacher teacher = getUser(request);
//            if (teacher != null) {
//                StudentDAO studentDAO = new StudentDAO();
//                List<Student> students = studentDAO.studentListGet(teacher);
//                List<Integer> entYearSet = students.stream()
//                                                    .map(Student::getEntYear)
//                                                    .distinct()
//                                                    .collect(Collectors.toList());
//                request.setAttribute("entYearSet", entYearSet);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void setEntYearSet(HttpServletRequest request) {
        try {
            // 現在の年を取得
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);

            // 10年前から10年後までの年リストを生成
            List<Integer> entYearSet = new ArrayList<>();
            for (int i = currentYear - 10; i <= currentYear + 10; i++) {
                entYearSet.add(i);
            }

            // リクエストに年リストをセット
            request.setAttribute("entYearSet", entYearSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // リクエスト属性に科目のセットを設定するメソッド
    public static void setSubjects(HttpServletRequest request) {
        try {
            SubjectDAO subjectDAO = new SubjectDAO();
            Teacher teacher = getUser(request);
            List<Subject> subjectSet = subjectDAO.filter(teacher.getSchool());
            request.setAttribute("subjectSet", subjectSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // リクエスト属性に番号のセットを設定するメソッド
    public static void setNumSet(HttpServletRequest request) {
        try {
            Teacher teacher = getUser(request);
            if (teacher != null) {
                StudentDAO studentDAO = new StudentDAO();
                List<Student> students = studentDAO.studentListGet(teacher);
                List<String> numSet = students.stream()
                                              .map(Student::getNo)
                                              .collect(Collectors.toList());
                request.setAttribute("numSet", numSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
