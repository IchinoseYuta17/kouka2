package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDAO extends DAO {

    private String baseSql = "SELECT test.*, subject.name FROM test JOIN subject ON test.subject_cd = subject.cd WHERE test.student_no= ?";

    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> testListStudents = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(baseSql);
        st.setString(1, student.getNo());

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            TestListStudent testListStudent = new TestListStudent();
            testListStudent.setSubjectName(rs.getString("NAME"));
            testListStudent.setSubjectCd(rs.getString("SUBJECT_CD"));
            testListStudent.setNo(rs.getInt("NO"));
            testListStudent.setPoint(rs.getInt("POINT"));
            testListStudents.add(testListStudent);
        }

        rs.close();
        st.close();
        con.close();

        return testListStudents;
    }
    private List<TestListStudent> postFilter(ResultSet rs) throws SQLException {
        List<TestListStudent> testListStudents = new ArrayList<>();

        // 結果セットをオブジェクトにマッピング
        while (rs.next()) {
            TestListStudent testListStudent = new TestListStudent();
            testListStudent.setSubjectName(rs.getString("NAME"));
            testListStudent.setSubjectCd(rs.getString("SUBJECT_CD"));
            testListStudent.setNo(rs.getInt("NO"));
            testListStudent.setPoint(rs.getInt("POINT"));
            testListStudents.add(testListStudent);
        }

        return testListStudents; // 取得したテストリストを返す
    }
}
