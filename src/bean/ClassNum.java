package bean;

// Serializableインターフェースを実装してBeanを作成する
public class ClassNum implements java.io.Serializable {

    // DBの項目名と同じ名前のprivateなフィールドを定義
    private String Cd;     // 学校コード
    private String Num;     // クラス番号

    // ゲッターメソッド
    public String getSchoolCd() {
        return Cd;
    }
    public String getClassNum() {
        return Num;
    }

    // セッターメソッド
    public void setSchoolCd(String Cd) {
        this.Cd = Cd;
    }
    public void setClassNum(String Num) {
        this.Num = Num;
    }
}
