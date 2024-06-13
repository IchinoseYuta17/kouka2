package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDAO extends DAO {

    // 特定の学校に所属する全ての科目を取得するメソッド
    public List<Subject> filter(School school) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Subject> subjectList = new ArrayList<>();

        try {
            con = getConnection();
            String sql = "SELECT * FROM subject WHERE school_cd = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, school.getCd());
            rs = ps.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setCd(rs.getString("cd"));
                subject.setName(rs.getString("name"));
                subject.setSchool(school);
                subjectList.add(subject);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            con.close();
        }

        return subjectList;
    }


    // 特定のIDに対応する科目を取得するメソッド
    public Subject get(String cd, School school) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Subject subject = null;

        try {
            con = getConnection();
            String sql = "SELECT * FROM subject WHERE cd = ? AND school_cd = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, cd);
            ps.setString(2, school.getCd());
            rs = ps.executeQuery();

            if (rs.next()) {
                subject = new Subject();
                subject.setCd(rs.getString("cd"));
                subject.setName(rs.getString("name"));
                subject.setSchool(school);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
            con.close();
        }

        return subject;
    }


    // 学生情報を保存(更新)するメソッド
    public boolean save(Subject subject) throws Exception {
        Connection con = getConnection();
        boolean isUpdate = get(subject.getCd(),subject.getSchool()) != null; // 学生が存在するかどうかを確認

        PreparedStatement st;
        if (isUpdate) {
            // 既存の学生情報を更新
            st = con.prepareStatement(
                "UPDATE SUBJECT SET NAME = ? WHERE CD = ?");
            st.setString(1, subject.getName());
            st.setString(2, subject.getCd());
        } else {
            // 新しい学生情報を挿入
            st = con.prepareStatement(
                "INSERT INTO SUBJECT (NO, NAME,SCHOOL_CD) VALUES (?, ?, ?)");
            st.setString(1, subject.getCd());
            st.setString(2, subject.getName());
            st.setString(3, subject.getSchool().getCd());
        }

        int line = st.executeUpdate(); // クエリを実行して更新された行数を取得

        st.close();
        con.close();

        return line > 0; // 更新が成功したかどうかを返す
    }


    // 科目を削除するメソッド
    public boolean delete(Subject subject) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;

        try {
            con = getConnection();
            String sql = "DELETE FROM subject WHERE cd = ? AND school_cd = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, subject.getCd());
            ps.setString(2, subject.getSchool().getCd());

            result = ps.executeUpdate();
        }  catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ps.close();
            con.close();
        }

        return result > 0;
    }


}
