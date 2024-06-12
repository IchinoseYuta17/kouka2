package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;


public class ClassNumDAO extends DAO {

    // ベースのSQLクエリを定義
    private String baseSql = "SELECT * FROM CLASS_NUM WHERE SCHOOL_CD = ?";

    // クラス番号リストを学校オブジェクトで取得するメソッド
    public List<ClassNum> filter(School school) throws Exception {

    	String schoolCd = school.getCd();
    	ClassNum classNum = null; // ClassNumオブジェクトを初期化

    	List<ClassNum> classNumList = new ArrayList<>(); // 返却する空のリストを作成

        Connection con = getConnection(); // データベース接続を取得

        // SQLクエリを準備
        PreparedStatement st = con.prepareStatement(baseSql);
        st.setString(1, schoolCd); // パラメータに学校コードを設定
        ResultSet rs = st.executeQuery(); // クエリを実行して結果セットを取得

        if (rs.next()) {
        	classNum = new ClassNum(); // クラス番号オブジェクトをインスタンス化
        	classNum.setSchoolCd(rs.getString("CD")); // 学校コードを設定
        	classNum.setClassNum(rs.getString("NUM")); // クラス番号を設定
        	classNumList.add(classNum);
        }

        st.close(); // ステートメントをクローズ
        con.close(); // 接続をクローズ

        return classNumList; // クラス番号リストを返す
    }
}

