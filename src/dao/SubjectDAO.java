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
    public List<Subject> getAll(String schoolCd) throws SQLException {
        List<Subject> subjectList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();  // DAOクラスのgetConnection()を使用
            String sql = "SELECT * FROM subject WHERE school_cd = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, schoolCd);
            rs = ps.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setCd(rs.getString("cd"));
                subject.setName(rs.getString("name"));

                School school = new School();
                school.setCd(rs.getString("school_cd"));
                subject.setSchool(school);

                subjectList.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(ps);
            close(con);
        }
        return subjectList;
    }

    // 特定のIDに対応する科目を取得するメソッド
    public Subject get(String cd, String schoolCd) throws SQLException {
        Subject subject = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();  // DAOクラスのgetConnection()を使用
            String sql = "SELECT * FROM subject WHERE cd = ? AND school_cd = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, cd);
            ps.setString(2, schoolCd);
            rs = ps.executeQuery();

            if (rs.next()) {
                subject = new Subject();
                subject.setCd(rs.getString("cd"));
                subject.setName(rs.getString("name"));

                School school = new School();
                school.setCd(rs.getString("school_cd"));
                subject.setSchool(school);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(ps);
            close(con);
        }
        return subject;
    }

    // 科目を挿入するメソッド
    public boolean insert(Subject subject) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();  // DAOクラスのgetConnection()を使用
            String sql = "INSERT INTO subject (cd, name, school_cd) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, subject.getCd());
            ps.setString(2, subject.getName());
            ps.setString(3, subject.getSchool().getCd());

            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close(ps);
            close(con);
        }
    }

    // 科目を更新するメソッド
    public boolean update(Subject subject) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();  // DAOクラスのgetConnection()を使用
            String sql = "UPDATE subject SET name = ? WHERE cd = ? AND school_cd = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, subject.getName());
            ps.setString(2, subject.getCd());
            ps.setString(3, subject.getSchool().getCd());

            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close(ps);
            close(con);
        }
    }

    // 科目を削除するメソッド
    public boolean delete(String cd, String schoolCd) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();  // DAOクラスのgetConnection()を使用
            String sql = "DELETE FROM subject WHERE cd = ? AND school_cd = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, cd);
            ps.setString(2, schoolCd);

            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close(ps);
            close(con);
        }
    }

    // リソースをクローズするためのメソッド
    public void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
