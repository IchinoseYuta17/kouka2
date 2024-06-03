package bean;

// Serializableインターフェースを実装してBeanを作成する
public class Student implements java.io.Serializable {

	// DBの項目名と同じ名前のprivateなフィールドを定義
	private int student_id;
	private String student_name;
	private int course_id;
	// その学生に対応したコース名を取得したいためcourseビーンをもたせる
	private Course course;

	// ゲッターメソッド キャメルケースはプロパティ名の先頭のみ
	// student_nameならばgetStudent_id()です。getStudent_Id()やgetStudentId()などではエラーになります。
	public int getStudent_id() {
		return student_id;
	}
	public String getStudent_name() {
		return student_name;
	}
	public int getCourse_id() {
		return course_id;
	}
	public Course getCourse() {
		return course;
	}

	// セッターメソッド
	public void setStudent_id(int id) {
		this.student_id=id;
	}
	public void setStudent_name(String student_name) {
		this.student_name=student_name;
	}
	public void setCourse_id(int course_id) {
		this.course_id=course_id;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
}
