package bean;

// Serializableインターフェースを実装してBeanを作成する
public class Student implements java.io.Serializable {

    // DBの項目名と同じ名前のprivateなフィールドを定義
    private String NO;           // 学生番号
    private String NAME;         // 学生名
    private int ENT_YEAR;        // 入学年度
    private String CLASS_NUM;    // クラス番号
    private boolean IS_ATTEND;   // 在学中フラグ
    private String SCHOOL_CD;    // 学校コード

    // ゲッターメソッド
    public String getNO() {
        return NO;
    }
    public String getNAME() {
        return NAME;
    }
    public int getENT_YEAR() {
        return ENT_YEAR;
    }
    public String getCLASS_NUM() {
        return CLASS_NUM;
    }
    public boolean getIS_ATTEND() {
        return IS_ATTEND;
    }
    public String getSCHOOL_CD() {
        return SCHOOL_CD;
    }

    // セッターメソッド
    public void setNO(String NO) {
        this.NO = NO;
    }
    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
    public void setENT_YEAR(int ENT_YEAR) {
        this.ENT_YEAR = ENT_YEAR;
    }
    public void setCLASS_NUM(String CLASS_NUM) {
        this.CLASS_NUM = CLASS_NUM;
    }
    public void setIS_ATTEND(boolean IS_ATTEND) {
        this.IS_ATTEND = IS_ATTEND;
    }
    public void setSCHOOL_CD(String SCHOOL_CD) {
        this.SCHOOL_CD = SCHOOL_CD;
    }
}
