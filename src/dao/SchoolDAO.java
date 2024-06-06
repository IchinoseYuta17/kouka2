package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;

public class SchoolDAO extends DAO {

    // ベースのSQLクエリを定義
    private String baseSql = "SELECT * FROM school WHERE CD = ?";

    // 学校情報を学校コード（cd）で取得するメソッド
    public School get(String cd) throws Exception {
        School school = null; // 学校オブジェクトを初期化
        Connection con = getConnection(); // データベース接続を取得

        // 学校コードを条件にしてSQLクエリを準備
        PreparedStatement st = con.prepareStatement(baseSql);
        st.setString(1, cd); // パラメータに学校コードを設定
        ResultSet rs = st.executeQuery(); // クエリを実行して結果セットを取得

        if (rs.next()) {
            school = new School(); // 学校オブジェクトをインスタンス化
            school.setCd(rs.getString("CD")); // 学校コードを設定
            school.setName(rs.getString("NAME")); // 学校名を設定
            // 他の学校情報の取得や設定があればここに追加
        }

        st.close(); // ステートメントをクローズ
        con.close(); // 接続をクローズ

        return school; // 学校オブジェクトを返す
    }
}
