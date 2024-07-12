package student;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.School;
import bean.Student;
import dao.StudentDAO;
import tool.Action;

/**
 * CSVファイルから学生情報を読み込み、データベースに登録するアクションクラス
 */
@MultipartConfig(
    maxFileSize=10000000,
    maxRequestSize=10000000,
    fileSizeThreshold=10000000
)
public class StudentCreateCsvExecuteAction extends Action {

    /**
     * CSVファイルを処理し、学生情報をデータベースに登録する
     * @param req HTTPリクエスト
     * @param res HTTPレスポンス
     * @return 処理完了後のJSPページ名
     * @throws Exception 処理中に発生した例外
     */
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	try{
        req.setCharacterEncoding("UTF-8");
        Part csv = req.getPart("csv");
        BufferedReader br = null;
        StudentDAO dao = new StudentDAO();
        List<String> errors = new ArrayList<>();
        int successCount = 0;
        int totalCount = 0;

        try {
            InputStream is = csv.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "Shift_JIS");
            br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
            	// エラーを検出した行を特定するためのカウント
                totalCount++;
                try {
                    String[] data = line.split(",");
                    // CSVの列数のに誤りがある場合のエラー処理
                    if (data.length != 6) {
                        throw new IllegalArgumentException("CSVの列数が不正です。期待される列数: 6, 実際の列数: " + data.length);
                    }
                    Student student = createStudentFromCsvData(data);
                    boolean success = dao.studentUpdate(student);
                    if (!success) {
                        throw new Exception("学生情報の更新に失敗しました。");
                    }
                    successCount++;
                } catch (Exception e) {
                    errors.add(totalCount + "行目: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new ServletException("CSVファイルの処理中にエラーが発生しました。", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    throw new ServletException("BufferedReaderのクローズ中にエラーが発生しました。", e);
                }
            }
        }

        req.setAttribute("successCount", successCount);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("errors", errors);

        return errors.isEmpty() ? "student_create_done.jsp" : "student_create_error.jsp";
    	}catch(Exception e){
    		return "error.jsp";
    	}
    }

    /**
     * CSVデータから学生オブジェクトを作成する
     * @param data CSVの1行分のデータ配列
     * @return 作成された学生オブジェクト
     * @throws IllegalArgumentException データが不正な場合
     */
    private Student createStudentFromCsvData(String[] data) throws IllegalArgumentException {
        Student student = new Student();
        try {
            student.setNo(data[0].trim().replaceFirst("^\"", "").replaceFirst("\"$", "")); // 先頭の"を除去
            student.setName(data[1].trim());
            student.setEntYear(Integer.parseInt(data[2].trim()));
            student.setClassNum(data[3].trim());
            student.setIsAttend(Boolean.parseBoolean(data[4].trim()));

            School school = new School();
            school.setCd(data[5].trim().replaceFirst("^\"", "").replaceFirst("\"$", ""));
            student.setSchool(school);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("入学年度の形式が不正です: " + data[2]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("出席状況の形式が不正です: " + data[4]);
        }

        return student;
    }
}