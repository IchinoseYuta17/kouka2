package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;

public class StudentDAO extends DAO {

//	// ==================== 以下、ログイン機能なしvar.のメソッド =========================
//    // ベースのSQLクエリを定義
//    private String baseSql = "SELECT * FROM student WHERE ";
//
//    // 学生情報を学生番号（no）で取得するメソッド
//    public Student studentGet(String no) throws Exception {
//        Student student = null; // 学生オブジェクトを初期化
//        Connection con = getConnection(); // データベース接続を取得
//
//        // 学生番号を条件にしてSQLクエリを準備
//        PreparedStatement st = con.prepareStatement(baseSql + "NO = ?");
//        st.setString(1, no); // パラメータに学生番号を設定
//        ResultSet rs = st.executeQuery(); // クエリを実行して結果セットを取得
//
//        if (rs.next()) {
//            student = new Student(); // 学生オブジェクトをインスタンス化
//            student.setNo(rs.getString("NO")); // 学生番号を設定
//            student.setName(rs.getString("Name")); // 学生名を設定
//            student.setEntYear(rs.getInt("ENT_YEAR")); // 入学年度を設定
//            student.setClassNum(rs.getString("CLASS_Num")); // クラス番号を設定
//            student.setIsAttend(rs.getBoolean("IS_ATTEND")); // 在学中フラグを設定
//
//            // 学校ビーンをインスタンス化して情報をセット
//            School school = new School();
//            school.setCd(rs.getString("SCHOOL_CD")); // 学校コードを設定
//            // 仮に学校名のカラム名が "SCHOOL_Num" だと仮定
//            school.setName(rs.getString("NAME"));
//            student.setSchool(school); // 学校情報を学生オブジェクトに設定
//        }
//
//        st.close(); // ステートメントをクローズ
//        con.close(); // 接続をクローズ
//
//        return student; // 学生オブジェクトを返す
//    }
//
//    // 結果セットから学生リストを生成するメソッド
//    private List<Student> studentPostfilter(ResultSet rSet) throws Exception {
//        List<Student> studentList = new ArrayList<>();
//        while (rSet.next()) {
//            Student student = new Student();
//            student.setNo(rSet.getString("NO"));
//            student.setName(rSet.getString("NAME"));
//            student.setEntYear(rSet.getInt("ENT_YEAR"));
//            student.setClassNum(rSet.getString("CLASS_NUM"));
//            student.setIsAttend(rSet.getBoolean("IS_ATTEND"));
//
//            studentList.add(student); // 学生リストに追加
//        }
//        return studentList; // 学生リストを返す
//    }
//
//    // 複数の条件で学生リストをフィルタリングするメソッド
//    public List<Student> studentFilter(int entYear, String classNum, Boolean isAttend) throws Exception {
//        Connection con = getConnection();
//
//        // SQLクエリを準備
//        String sql = baseSql + "ENT_YEAR = ? AND CLASS_NUM = ? AND IS_ATTEND = ?";
//        PreparedStatement st = con.prepareStatement(sql);
//        st.setInt(1, entYear);
//        st.setString(2, classNum);
//        st.setBoolean(3, isAttend);
//        ResultSet rs = st.executeQuery();
//
//        List<Student> studentList = studentPostfilter(rs);
//
//        st.close();
//        con.close();
//
//        return studentList;
//    }
//
// // 入学年度と在学中フラグで学生リストをフィルタリングするメソッド
//    public List<Student> studentFilter(int entYear, Boolean isAttend) throws Exception {
//        // データベース接続を取得
//        Connection con = getConnection();
//
//        // SQLクエリを準備
//        String sql = baseSql + "ENT_YEAR = ? AND IS_ATTEND = ?";
//        PreparedStatement st = con.prepareStatement(sql);
//        st.setInt(1, entYear); // 入学年度を設定
//        st.setBoolean(2, isAttend); // 在学中フラグを設定
//        ResultSet rs = st.executeQuery(); // クエリを実行し、結果を取得
//
//        // 取得した結果をフィルタリング
//        List<Student> studentList = studentPostfilter(rs);
//
//        // リソースを解放して接続をクローズ
//        st.close();
//        con.close();
//
//        // フィルタリングされた学生リストを返す
//        return studentList;
//    }
//
//    // 在学中フラグで学生リストをフィルタリングするメソッド
//    public List<Student> studentFilter(boolean isAttend) throws Exception {
//        Connection con = getConnection();
//
//        // SQLクエリを準備
//        String sql = baseSql + "IS_ATTEND = ?";
//        PreparedStatement st = con.prepareStatement(sql);
//        st.setBoolean(1, isAttend);
//        ResultSet rs = st.executeQuery();
//
//        List<Student> studentList = studentPostfilter(rs);
//
//        st.close();
//        con.close();
//
//        return studentList;
//    }

//    // 学生情報を保存(更新)するメソッド
//    public boolean studentUpdate(Student student) throws Exception {
//        Connection con = getConnection();
//        boolean isUpdate = studentGet(student.getNo()) != null; // 学生が存在するかどうかを確認
//
//        PreparedStatement st;
//        if (isUpdate) {
//            // 既存の学生情報を更新
//            st = con.prepareStatement(
//                "UPDATE student SET NAME = ?, ENT_YEAR = ?, CLASS_NUM = ?, IS_ATTEND = ?, SCHOOL_CD = ? WHERE NO = ?");
//            st.setString(6, student.getNo());
//        } else {
//            // 新しい学生情報を挿入
//            st = con.prepareStatement(
//                "INSERT INTO student (NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) VALUES (?, ?, ?, ?, ?, ?)");
//        }
//        st.setString(1, student.getNo());
//        st.setString(2, student.getName());
//        st.setInt(3, student.getEntYear());
//        st.setString(4, student.getClassNum());
//        st.setBoolean(5, student.getIsAttend());
//        st.setString(6, student.getSchool().getCd()); // 学校コードをセット
//
//        int line = st.executeUpdate(); // クエリを実行して更新された行数を取得
//
//        st.close();
//        con.close();
//
//        return line > 0; // 更新が成功したかどうかを返す
//    }
//
//    // 学生情報を削除するメソッド
//    public boolean studentDelete(Student student) throws Exception {
//        Connection con = getConnection();
//
//        // 学生情報を削除するSQLクエリを準備
//        PreparedStatement st = con.prepareStatement(
//            "DELETE FROM student WHERE NO = ?");
//        st.setString(1, student.getNo());
//        int line = st.executeUpdate(); // クエリを実行して削除された行数を取得
//
//        st.close();
//        con.close();
//
//        return line > 0; // 削除が成功したかどうかを返す
//    }
//
//
//
//
//
//
//
//
//
//
//
//


// ==================== 以下、ログイン機能実装時のメソッド =========================
//    // ベースのSQLクエリを定義
    private String baseSql = "SELECT * FROM student WHERE SCHOOL_CD = ?";
    List<Student> studentList = new ArrayList<>();
    public List<Student> studentListGet(Teacher teacher)throws Exception{
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement(baseSql)) {

               School school = teacher.getSchool();
               st.setString(1, school.getCd());

               try (ResultSet rs = st.executeQuery()) {
                   while (rs.next()) {
                       Student student = new Student();
                       student.setNo(rs.getString("NO")); // or rs.getString(1)
                       student.setName(rs.getString("NAME")); // or rs.getString(2)
                       student.setEntYear(rs.getInt("ENT_YEAR")); // or rs.getInt(3)
                       student.setClassNum(rs.getString("CLASS_NUM")); // or rs.getString(4)
                       student.setIsAttend(rs.getBoolean("IS_ATTEND")); // or rs.getBoolean(5)
                       studentList.add(student);
                   }
               }
           } catch (SQLException e) {
               // Handle any SQL errors here
               e.printStackTrace();
           }
           return studentList;
       }


    // 学生情報を学生番号（no）で取得するメソッド
    public Student studentGet(String no) throws Exception {
        Student student = null; // 学生オブジェクトを初期化
        Connection con = getConnection(); // データベース接続を取得

        // 学生番号を条件にしてSQLクエリを準備
        PreparedStatement st = con.prepareStatement(baseSql + " AND NO = ?");
        st.setString(1, no); // パラメータに学生番号を設定
        ResultSet rs = st.executeQuery(); // クエリを実行して結果セットを取得

        if (rs.next()) {
            student = new Student(); // 学生オブジェクトをインスタンス化
            student.setNo(rs.getString("NO")); // 学生番号を設定
            student.setName(rs.getString("NAME")); // 学生名を設定
            student.setEntYear(rs.getInt("ENT_YEAR")); // 入学年度を設定
            student.setClassNum(rs.getString("CLASS_NUM")); // クラス番号を設定
            student.setIsAttend(rs.getBoolean("IS_ATTEND")); // 在学中フラグを設定

            // 学校ビーンをインスタンス化して情報をセット
            School school = new School();
            school.setCd(rs.getString("SCHOOL_CD")); // 学校コードを設定
            // 仮に学校名のカラム名が "SCHOOL_Num" だと仮定
            school.setName(rs.getString("NAME"));
            student.setSchool(school); // 学校情報を学生オブジェクトに設定
        }

        st.close(); // ステートメントをクローズ
        con.close(); // 接続をクローズ

        return student; // 学生オブジェクトを返す
    }

    // 結果セットから学生リストを生成するメソッド
    private List<Student> studentPostfilter(ResultSet rSet, School school) throws Exception {
        List<Student> studentList = new ArrayList<>();
        while (rSet.next()) {
            Student student = new Student();
            student.setNo(rSet.getString("NO"));
            student.setName(rSet.getString("NAME"));
            student.setEntYear(rSet.getInt("ENT_YEAR"));
            student.setClassNum(rSet.getString("CLASS_NUM"));
            student.setIsAttend(rSet.getBoolean("IS_ATTEND"));

            student.setSchool(school); // 既存の学校情報をセット
            studentList.add(student); // 学生リストに追加
        }
        return studentList; // 学生リストを返す
    }

  // 複数の条件で学生リストをフィルタリングするメソッド
  public List<Student> studentFilter(School school, int entYear, String classNum, Boolean isAttend) throws Exception {
      Connection con = getConnection();

      // SQLクエリを準備
      String sql = baseSql + "AND ENT_YEAR = ? AND CLASS_NUM = ? AND IS_ATTEND = ?";
      PreparedStatement st = con.prepareStatement(sql);
      st.setString(1,school.getCd());
      st.setInt(2, entYear);
      st.setString(3, classNum);
      st.setBoolean(4, isAttend);
      ResultSet rs = st.executeQuery();

      List<Student> studentList = studentPostfilter(rs, school);

      st.close();
      con.close();

      return studentList;
  }


    // 複数の条件で学生リストをフィルタリングするメソッド
  public List<Student> studentFilter(School school, int entYear, Boolean isAttend) throws Exception {
  // データベース接続を取得
  Connection con = getConnection();

  // SQLクエリを準備
  String sql = baseSql + "AND ENT_YEAR = ? AND IS_ATTEND = ?";
  PreparedStatement st = con.prepareStatement(sql);
  st.setString(1,school.getCd());
  st.setInt(2, entYear); // 入学年度を設定
  st.setBoolean(3, isAttend); // 在学中フラグを設定
  ResultSet rs = st.executeQuery(); // クエリを実行し、結果を取得

  // 取得した結果をフィルタリング
  List<Student> studentList = studentPostfilter(rs,school);

  // リソースを解放して接続をクローズ
  st.close();
  con.close();

  // フィルタリングされた学生リストを返す
  return studentList;
}

// 在学中フラグで学生リストをフィルタリングするメソッド
public List<Student> studentFilter(School school, boolean isAttend) throws Exception {
  Connection con = getConnection();

  // SQLクエリを準備
  String sql = baseSql + "AND IS_ATTEND = ?";
  PreparedStatement st = con.prepareStatement(sql);
  st.setString(1,school.getCd());
  st.setBoolean(2, isAttend);
  ResultSet rs = st.executeQuery();

  List<Student> studentList = studentPostfilter(rs, school);

  st.close();
  con.close();

  return studentList;
}

    // 学生情報を保存(更新)するメソッド
    public boolean studentUpdate(Student student) throws Exception {
        Connection con = getConnection();
        boolean isUpdate = studentGet(student.getNo()) != null; // 学生が存在するかどうかを確認

        PreparedStatement st;
        if (isUpdate) {
            // 既存の学生情報を更新
            st = con.prepareStatement(
                "UPDATE student SET NAME = ?, ENT_YEAR = ?, CLASS_NUM = ?, IS_ATTEND = ?, SCHOOL_CD = ? WHERE NO = ?");
            st.setString(6, student.getNo());
        } else {
            // 新しい学生情報を挿入
            st = con.prepareStatement(
                "INSERT INTO student (NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) VALUES (?, ?, ?, ?, ?, ?)");
        }
        st.setString(1, student.getNo());
        st.setString(2, student.getName());
        st.setInt(3, student.getEntYear());
        st.setString(4, student.getClassNum());
        st.setBoolean(5, student.getIsAttend());
        st.setString(6, student.getSchool().getCd()); // 学校コードをセット

        int line = st.executeUpdate(); // クエリを実行して更新された行数を取得

        st.close();
        con.close();

        return line > 0; // 更新が成功したかどうかを返す
    }

    // 学生情報を削除するメソッド
    public boolean studentDelete(Student student) throws Exception {
        Connection con = getConnection();

        // 学生情報を削除するSQLクエリを準備
        PreparedStatement st = con.prepareStatement(
            "DELETE FROM student WHERE NO = ?");
        st.setString(1, student.getNo());
        int line = st.executeUpdate(); // クエリを実行して削除された行数を取得

        st.close();
        con.close();

        return line > 0; // 削除が成功したかどうかを返す
    }
}
