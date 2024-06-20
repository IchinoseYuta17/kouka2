package bean;

// Serializableインターフェースを実装してBeanを作成する
public class School implements java.io.Serializable {

    // DBの項目名と同じ名前のprivateなフィールドを定義
    private String cd;           // 学校コード
    private String name;         // 学校名

    // ゲッターメソッド
    public String getCd() {
        return cd;
    }
    public String getName() {
        return name;
    }

    // セッターメソッド
    public void setCd(String cd) {
        this.cd = cd;
    }
    public void setName(String name) {
        this.name = name;
    }
}
