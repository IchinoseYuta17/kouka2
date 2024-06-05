package bean;

// Serializableインターフェースを実装してBeanを作成する
public class Student implements java.io.Serializable {

    // DBの項目名と同じ名前のprivateなフィールドを定義
    private String no;           // 学生番号
    private String name;         // 学生名
    private int entYear;        // 入学年度
    private String classNum;    // クラス番号
    private boolean isAttend;   // 在学中フラグ
    private String school;    // 学校コード

    // ゲッターメソッド
    public String getNo() {
        return no;
    }
    public String getName() {
        return name;
    }
    public int getENntYear() {
        return entYear;
    }
    public String getClassNum() {
        return classNum;
    }
    public boolean getIsAttend() {
        return isAttend;
    }
    public String getSchool() {
        return school;
    }

    // セッターメソッド
    public void setNo(String no) {
        this.no = no;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
    public void setIsAttend(boolean isAttend) {
        this.isAttend = isAttend;
    }
    public void setSchool(String school) {
        this.school = school;
    }
}
