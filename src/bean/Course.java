package bean;

// Serializableインターフェースを実装してBeanを作成する
public class Course implements java.io.Serializable {

	// DBの項目名と同じ名前のprivateなフィールドを定義
	private int course_id;
	private String course_name;

	// ゲッターメソッド キャメルケースはプロパティ名の先頭のみ
	// student_idならばgetStudent_id()です。getStudent_Id()やgetStudentId()などではエラーになります。
	public int getCourse_id() {
		return course_id;
	}
	public String getCourse_name() {
		return course_name;
	}

	// セッターメソッド
	public void setCourse_id(int id) {
		this.course_id=id;
	}
	public void setCourse_name(String course_name) {
		this.course_name=course_name;
	}
}
