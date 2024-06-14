package bean;

// Serializableインターフェースを実装してBeanを作成する
public class Test implements java.io.Serializable {

    // DBの項目名と同じ名前のprivateなフィールドを定義
    private Student student;   // 学生オブジェクト
    private String classNum;	// クラス番号
    private Subject subject;   // 科目オブジェクト
    private School school;    // 学校オブジェクト
    private int no;             // 回数
    private int point;          // 得点

    // ゲッターメソッド
    public Student getStudent() {
        return student;
    }
    public String getClassNum() {
        return classNum;
    }
    public Subject getSubject() {
        return subject;
    }
    public School getSchool() {
        return school;
    }
    public int getNo() {
        return no;
    }
    public int getPoint() {
        return point;
    }


    // セッターメソッド
    public void setStudent(Student student) {
        this.student = student;
    }
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    public void setSchool(School school) {
        this.school = school;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public void setPoint(int point) {
        this.point = point;
    }

}
