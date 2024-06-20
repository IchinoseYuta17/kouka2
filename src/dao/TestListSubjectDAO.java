package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDAO extends DAO {

    // SQLクエリのベース部分を定義
    // private String baseSql = "SELECT student.ent_year, test.class_num, test.student_no, student.name, test.point, FROM test JOIN subject ON test.subject_cd = subject.cd JOIN student ON test.student_no = student.no WHERE student.ent_year = ? AND test.class_num = ? AND subject.cd = ? AND test.school_cd = ?";
	private String baseSql = "SELECT test.*, student.ent_year, student.name, subject.name FROM test JOIN subject ON test.subject_cd = subject.cd JOIN student ON test.student_no = student.no WHERE student.ent_year = ? AND test.class_num = ? AND subject.cd = ? AND test.school_cd = ?";
    // フィルター条件に基づいてテストリストを取得するメソッド
    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<TestListSubject> testListSubjects = new ArrayList<>();

        // データベース接続を取得
        Connection con = getConnection();
        // SQL文を準備
        PreparedStatement st = con.prepareStatement(baseSql);

        // パラメータを設定
        st.setInt(1, entYear);
        st.setString(2, classNum);
        st.setString(3, subject.getCd());
        st.setString(4, school.getCd());

        // クエリを実行して結果を取得
        ResultSet rs = st.executeQuery();

        testListSubjects = postFilter(rs);

        // 結果セットを処理
        while (rs.next()) {
            TestListSubject testListSubject = new TestListSubject();
            testListSubject.setEntYear(rs.getInt("ent_year"));
            testListSubject.setStudentNo(rs.getString("student_no"));
            testListSubject.setStudentName(rs.getString("student_name"));
            testListSubject.setClassNum(rs.getString("class_num"));
            testListSubject.setNum(rs.getString("subject_name")); // 科目名をNumとして設定
            testListSubject.putPoint(rs.getInt("no"), rs.getInt("point")); // 仮にsubject_cdをキーとして点数を設定
            testListSubjects.add(testListSubject);
        }

        // リソースを閉じる
        rs.close();
        st.close();
        con.close();

        // テストリストを返す
        return testListSubjects;
    }
    private List<TestListSubject> postFilter(ResultSet rs) throws SQLException {
        List<TestListSubject> testListSubjects = new ArrayList<>();

        // 結果セットをオブジェクトにマッピング
        while (rs.next()) {
            TestListSubject testListSubject = new TestListSubject();
            testListSubject.setEntYear(rs.getInt("ent_year"));
            testListSubject.setStudentNo(rs.getString("student_no"));
            testListSubject.setStudentName(rs.getString("student_name"));
            testListSubject.setClassNum(rs.getString("class_num"));
            testListSubject.setNum(rs.getString("subject_name")); // 科目名をNumとして設定
            testListSubject.putPoint(rs.getInt("no"), rs.getInt("point")); // 仮にsubject_nameをキーとして点数を設定
            testListSubjects.add(testListSubject);
        }

        // テストリストを返す
        return testListSubjects;
    }
}
