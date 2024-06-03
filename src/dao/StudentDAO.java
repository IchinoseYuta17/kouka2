package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Course;
import bean.Student;

public class StudentDAO extends DAO {

	// 学生を全件取得するstudentAllメソッド
	public List<Student> studentAll() throws Exception {
		List<Student> studentList=new ArrayList<>();

		Connection con=getConnection();

		// コース情報も必要なのでcourseテーブルからも取得する
		PreparedStatement st=con.prepareStatement(
			"SELECT * FROM student,course WHERE student.course_id=course.course_id");
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			//学生ビーンをインスタンス化して情報をセット
			Student student=new Student();
			student.setStudent_id(rs.getInt("student_id"));
			student.setStudent_name(rs.getString("student_name"));
			student.setCourse_id(rs.getInt("course_id"));
			// コースビーンをインスタンス化して情報をセット
			Course course=new Course();
			course.setCourse_id(rs.getInt("course_id"));
			course.setCourse_name(rs.getString("course_name"));
			// コースビーンを学生ビーンにセット
			student.setCourse(course);
			studentList.add(student);
		}

		st.close();
		con.close();

		return studentList;
	}

	// 指定の学生を検索するsearchStudentメソッド
	public Student searchStudent(int student_id) throws Exception {
		Student student = null;
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"SELECT * FROM student,course WHERE student.student_id=? and student.course_id=course.course_id");
		st.setInt(1, student_id);
		ResultSet rs=st.executeQuery();

		if (rs.next()) {
			student=new Student();
			student.setStudent_id(rs.getInt("student_id"));
			student.setStudent_name(rs.getString("student_name"));
			student.setCourse_id(rs.getInt("course_id"));
			Course course=new Course();
			course.setCourse_id(rs.getInt("course_id"));
			course.setCourse_name(rs.getString("course_name"));
			student.setCourse(course);
		}
		return student;
	}

	// 学生を追加するinsertStudentメソッド
	public int insertStudent(Student student) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"INSERT INTO student VALUES(null,?,?)");
		st.setString(1, student.getStudent_name());
		st.setInt(2, student.getCourse_id());
		// insertしたレコード件数が返ってくる
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

	// 学生を削除するdeleteStudentメソッド
	public int deleteStudent(int student_id) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"DELETE FROM student WHERE student_id=?");
		st.setInt(1, student_id);
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

	// 学生を更新するupdateStudentメソッド
	// public int updateStudent(int studentid, String student_name, int course_id)
	public int updateStudent(Student student) throws Exception {
		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement(
			"UPDATE student SET student_name=?,course_id=? WHERE student_id=?");
		st.setString(1, student.getStudent_name());
		st.setInt(2, student.getCourse_id());
		st.setInt(3, student.getStudent_id());
		int line = st.executeUpdate();

		st.close();
		con.close();
		return line;
	}
}