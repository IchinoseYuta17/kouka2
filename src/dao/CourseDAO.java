package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Course;

public class CourseDAO extends DAO {

	// コースを全件取得するcourseAllメソッド
	public List<Course> courseAll() throws Exception {
		List<Course> courseList=new ArrayList<>();

		Connection con=getConnection();

		PreparedStatement st=con.prepareStatement("SELECT * FROM course");
		ResultSet rs=st.executeQuery();

		while (rs.next()) {
			Course course=new Course();
			course.setCourse_id(rs.getInt("course_id"));
			course.setCourse_name(rs.getString("course_name"));
			courseList.add(course);
		}

		st.close();
		con.close();

		return courseList;
	}
}