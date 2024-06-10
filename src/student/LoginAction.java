package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAction {
    private String id;
    private String password;
    private String jspName;
    private String successUrl;
    private String requestData;

    // コンストラクタ
    public LoginAction(String id, String password) {
        this.id = id;
        this.password = password;
    }

    // jspNameのゲッター
    public String getJspName() {
        return jspName;
    }

    // successUrlのゲッター
    public String getSuccessUrl() {
        return successUrl;
    }

    // requestDataのセッター
    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }



    // POSTリクエストに対するログインアクション
    public String post() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // データベースへの接続を確立
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/team_db", "username", "password");

            // 提供された認証情報が有効かどうかを確認するクエリ
            String sql = "SELECT * FROM users WHERE id = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // 有効な認証情報の場合、成功URLにリダイレクト
                successUrl = "menu.jsp";
            } else {
                // 無効な認証情報の場合、ログインページにリダイレクト
                successUrl = "login.jsp";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // リソースをクローズ
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return successUrl;
    }
}
