package bean;

// Serializableインターフェースを実装してBeanを作成する
public class Test implements java.io.Serializable {

    // DBの項目名と同じ名前のprivateなフィールドを定義
    private Student student;   // 学生番号
    private Subject subject;   // 科目コード
    private School school;    // 学校コード
    private int no;             // 回数
    private int point;          // 得点
    private String classNum;    // クラス番号

    // ゲッターメソッド
    public Student getStudent() {
        return student;
    }
    public Subject getSubjectCd() {
        return subject;
    }
    public School getSchoolCd() {
        return school;
    }
    public int getNo() {
        return no;
    }
    public int getPoint() {
        return point;
    }
    public String getClassNum() {
        return classNum;
    }


    // セッターメソッド
    public void setStudent(Student student) {
        this.student = student;
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
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

}
