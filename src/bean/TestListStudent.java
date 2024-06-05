package bean;

// Serializableインターフェースを実装してBeanを作成する
public class TestListStudent implements java.io.Serializable {

    // DBの項目名と同じ名前のprivateなフィールドを定義
    private String subjectName;   //科目名
    private String subjectCd;    // 科目コード
    private int num;        // 数？
    private int point;    // 点数


    // ゲッターメソッド
    public String getSubjectName() {
        return subjectName;
    }
    public String getSubjectCd() {
        return subjectCd;
    }
    public int getNum() {
        return num;
    }
    public int getPoint() {
        return point;
    }


    // セッターメソッド
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public void setPoint(int point) {
        this.point = point;
    }

}
