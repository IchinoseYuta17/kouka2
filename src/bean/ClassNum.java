package bean;

// Serializableインターフェースを実装してBeanを作成する
public class ClassNum implements java.io.Serializable {

    // DBの項目名と同じ名前のprivateなフィールドを定義
    private String cd;     // 学校コード
    private String num;     // クラス番号

    // ゲッターメソッド
    public String getCd() {
        return cd;
    }
    public String getNum() {
        return num;
    }

    // セッターメソッド
    public void setCd(String cd) {
        this.cd = cd;
    }
    public void setNum(String num) {
        this.num = num;
    }
}
