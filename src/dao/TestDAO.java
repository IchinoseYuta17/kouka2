package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDAO extends DAO {

	// TestDAO内でStudentDAO/SubjectDAO/SchoolDAOを使用できるように設定
    StudentDAO studentDAO = new StudentDAO();
    SubjectDAO subjectDAO = new SubjectDAO();
    SchoolDAO schoolDAO = new SchoolDAO();

    private String baseSql = "SELECT * FROM test WHERE ";

    // メソッド: Testオブジェクトを取得する
    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        Test test = new Test(); // 学生オブジェクトをインスタンス化
        String sql = baseSql + "student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?";
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, student.getNo());
        st.setString(2, subject.getCd());
        st.setString(3, school.getCd());
        st.setInt(4, no);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            test.setStudent(student); // 学生オブジェクトを設定
            test.setClassNum(rs.getString("CLASS_NUM")); // クラス番号を設定
            test.setSubject(subject); // 科目オブジェクトを設定
            test.setSchool(school); // 入学年度を設定
            test.setNo(rs.getInt("NO")); // 回数を設定
            test.setPoint(rs.getInt("POINT")); // 得点を設定
        }

        st.close(); // ステートメントをクローズ
        con.close(); // 接続をクローズ

        return test;
    }



    // メソッド: ResultSetをフィルタリングしてTestオブジェクトのリストを返す
    private List<Test> postFilter(ResultSet rs, School school) throws Exception {
        List<Test> testList = new ArrayList<>();

        while (rs.next()) {
        	Test test = new Test();
            test.setStudent(studentDAO.studentGet(rs.getString("NO"),school)); // 学生オブジェクトを設定
            test.setClassNum(rs.getString("CLASS_NUM")); // クラス番号を設定
            test.setSubject(subjectDAO.get(rs.getString("SUBJECT_CD"), school)); // 科目オブジェクトを設定
            test.setSchool(school); // 学校オブジェクトを設定
            test.setNo(rs.getInt("NO")); // 回数を設定
            test.setPoint(rs.getInt("POINT")); // 得点を設定
            testList.add(test);
            }

        return testList;
    }

    // メソッド: フィルタしてTestオブジェクトのリストを返す(postFilterメソッドを使用)
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        String sql = "SELECT student.NO, student.NAME, student.ENT_YEAR, student.CLASS_NUM,  student.SCHOOL_CD, test.SUBJECT_CD, test.NO, test.POINT FROM student LEFT JOIN ( SELECT student_no, SUBJECT_CD, NO, POINT FROM test WHERE no = ? AND subject_cd = ? ) AS test ON student.no = test.student_no where student.school_cd = ? and student.ent_year = ? and student.class_num = ?";
        Connection con = getConnection();
     	PreparedStatement st = con.prepareStatement(sql);

     	st.setInt(1, num);
     	st.setString(2, subject.getCd());
     	st.setString(3, school.getCd());
    	st.setInt(4, entYear);
    	st.setString(5, classNum);

        ResultSet rs = st.executeQuery();

        List<Test> testList = postFilter(rs,school);

        st.close();
        con.close();

        return testList;

    }



    // メソッド: Testオブジェクトのリストを保存する(save())
    public boolean save(List<Test> list) throws Exception {
        try (Connection con = getConnection()) {
            for (Test test : list) {
                String selectSql = "SELECT COUNT(*) FROM test WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?";
                try (PreparedStatement selectSt = con.prepareStatement(selectSql)) {
                    selectSt.setString(1, test.getStudent().getNo());
                    selectSt.setString(2, test.getSubject().getCd());
                    selectSt.setString(3, test.getSchool().getCd());
                    selectSt.setInt(4, test.getNo());

                    ResultSet rs = selectSt.executeQuery();
                    rs.next();
                    int count = rs.getInt(1);

                    if (count > 0) {
                        // レコードが存在する場合は更新
                        String updateSql = "UPDATE test SET class_num = ?, point = ? WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?";
                        try (PreparedStatement updateSt = con.prepareStatement(updateSql)) {
                            updateSt.setString(1, test.getClassNum());
                            updateSt.setInt(2, test.getPoint());
                            updateSt.setString(3, test.getStudent().getNo());
                            updateSt.setString(4, test.getSubject().getCd());
                            updateSt.setString(5, test.getSchool().getCd());
                            updateSt.setInt(6, test.getNo());
                            updateSt.executeUpdate();
                        }
                    } else {
                        // レコードが存在しない場合は挿入
                        String insertSql = "INSERT INTO test (student_no, class_num, subject_cd, school_cd, no, point) VALUES (?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement insertSt = con.prepareStatement(insertSql)) {
                            insertSt.setString(1, test.getStudent().getNo());
                            insertSt.setString(2, test.getClassNum());
                            insertSt.setString(3, test.getSubject().getCd());
                            insertSt.setString(4, test.getSchool().getCd());
                            insertSt.setInt(5, test.getNo());
                            insertSt.setInt(6, test.getPoint());
                            insertSt.executeUpdate();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



    // メソッド: Testオブジェクトのリストを削除する
    public boolean delete(List<Test> list) throws Exception {
        try (Connection con = getConnection();) {

            for (Test test : list) {
            	delete(test, con);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // メソッド: 単一のTestオブジェクトを削除する
    private boolean delete(Test test, Connection connection) throws Exception {
        String sql = "DELETE FROM test WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, test.getStudent().getNo());
            stmt.setString(2, test.getSubject().getCd());
            stmt.setString(3, test.getSchool().getCd());
            stmt.setInt(4, test.getNo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
