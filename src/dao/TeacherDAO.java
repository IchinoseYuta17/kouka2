package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDAO extends DAO {

    // ログインメソッド
    public Teacher login(String id, String password) throws Exception {
        Teacher teacher = null; // 教師オブジェクトを初期化
        Connection con = getConnection(); // データベース接続を取得

        // 教師IDとパスワードを条件にしてSQLクエリを準備
        PreparedStatement st = con.prepareStatement("SELECT * FROM teacher AS T JOIN SCHOOL AS S ON T.SCHOOL_CD = S.CD WHERE ID = ? AND PASSWORD = ?");
        st.setString(1, id); // パラメータに教師IDを設定
        st.setString(2, password); // パラメータにパスワードを設定
        ResultSet rs = st.executeQuery(); // クエリを実行して結果セットを取得

        if (rs.next()) {
            teacher = new Teacher(); // 教師オブジェクトをインスタンス化
            teacher.setId(rs.getString("ID")); // 教師IDを設定
            teacher.setName(rs.getString("Name")); // 教師名を設定
            teacher.setPassword(rs.getString("Password")); // 教師のパスワードを設定

            // 学校ビーンをインスタンス化して情報をセット
            School school = new School();
            school.setCd(rs.getString("SCHOOL_CD")); // 学校コードを設定
            school.setName(rs.getString("SCHOOL_NAME")); // 学校名を設定
            teacher.setSchool(school); // 学校情報を教師オブジェクトに設定
        }

        st.close(); // ステートメントをクローズ
        con.close(); // 接続をクローズ

        return teacher; // 教師オブジェクトを返す
    }

    public Teacher get(String id) throws Exception {
        Teacher teacher = null; // 教師オブジェクトを初期化
        Connection con = getConnection(); // データベース接続を取得

        // 教師IDを条件にしてSQLクエリを準備
        PreparedStatement st = con.prepareStatement("SELECT * FROM TEACHER AS t JOIN SCHOOL AS s ON t.SCHOOL_CD = s.CD WHERE t.id= ?");
        st.setString(1, id); // パラメータに教師IDを設定
        ResultSet rs = st.executeQuery(); // クエリを実行して結果セットを取得

        if (rs.next()) {
            teacher = new Teacher(); // 教師オブジェクトをインスタンス化
            teacher.setId(rs.getString("ID")); // 教師IDを設定
            teacher.setPassword(rs.getString("PASSWORD")); // 教師のパスワードを設定
            teacher.setName(rs.getString("NAME")); // 教師名を設定

            // Schoolオブジェクトの生成と設定
            School school = new School();
            school.setCd(rs.getString("SCHOOL_CD")); // 学校コードを設定
            school.setName(rs.getString("SCHOOL_NAME")); // 学校名を設定
            teacher.setSchool(school); // 学校情報を教師オブジェクトに設定
        }
        return teacher; // 教師オブジェクトを返す
    }
}
