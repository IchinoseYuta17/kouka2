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
            test.setStudent(studentDAO.studentGet(rs.getString("STUDENT_NO"),school)); // 学生オブジェクトを設定
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
        String sql = "SELECT test.* FROM test JOIN student ON test.student_no = student.no WHERE student.ent_year = ? AND test.class_num = ? AND test.subject_cd = ? AND test.no = ? AND test.school_cd = ?";
        Connection con = getConnection();
     	PreparedStatement st = con.prepareStatement(sql);

    	st.setInt(1, entYear);
    	st.setString(2, classNum);
    	st.setString(3, subject.getCd());
    	st.setInt(4, num);
    	st.setString(5, school.getCd());

        ResultSet rs = st.executeQuery();

        List<Test> testList = postFilter(rs,school);

        st.close();
        con.close();

        return testList;

    }



    // メソッド: Testオブジェクトのリストを保存する(save())
    public boolean save(List<Test> list) throws Exception {
        try (
		Connection con = getConnection();){

            for (Test test : list) {
            	save(test,con);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // メソッド: 単一のTestオブジェクトを保存する
    private boolean save(Test test, Connection connection) throws Exception {
        String sql = "INSERT INTO test (student_no, subject_cd, school_cd, no, point, class_num) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, test.getStudent().getNo());
            st.setString(2, test.getSubject().getCd());
            st.setString(3, test.getSchool().getCd());
            st.setInt(4, test.getNo());
            st.setInt(5, test.getPoint());
            st.setString(6, test.getClassNum());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
