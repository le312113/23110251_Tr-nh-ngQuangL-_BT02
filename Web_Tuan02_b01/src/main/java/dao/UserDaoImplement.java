package dao;
import model.User;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImplement implements UserDao {

    private User mapRow(ResultSet rs) throws Exception {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setEmail(rs.getString("email"));
        user.setUserName(rs.getString("username"));
        user.setFullName(rs.getString("fullname"));
        user.setPassWord(rs.getString("password"));
        user.setAvatar(rs.getString("avatar"));
        user.setRoleid(rs.getInt("roleid"));
        user.setPhone(rs.getString("phone"));
        user.setCreatedDate(rs.getTimestamp("createdDate"));
        return user;
    }

    @Override
    public User get(String username) {
        String sql = "SELECT id, email, username, fullname, password, avatar, roleid, phone, createdDate "
                + "FROM Users WHERE username = ?";

        try (Connection con = new DBConnection().connection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            System.out.println("DEBUG DAO param username=" + username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("DEBUG DAO found user=" + rs.getString("username")
                            + " | dbPass=" + rs.getString("password"));
                    return mapRow(rs);
                } else {
                    System.out.println("DEBUG DAO: No user found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void insert(User u) {
        String sql = "INSERT INTO Users(email,username,fullname,password,avatar,roleid,phone,createdDate) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        try (Connection con = new DBConnection().connection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getUserName());
            ps.setString(3, u.getFullName());
            ps.setString(4, u.getPassWord()); // thực tế nên mã hoá
            ps.setString(5, u.getAvatar());
            ps.setInt(6, u.getRoleid());
            ps.setString(7, u.getPhone());
            ps.setTimestamp(8, new java.sql.Timestamp(u.getCreatedDate().getTime()));
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public boolean checkExistEmail(String email) {
        String sql = "SELECT 1 FROM Users WHERE email = ?";
        try (Connection con = new DBConnection().connection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean checkExistUsername(String username) {
        String sql = "SELECT 1 FROM Users WHERE username = ?";
        try (Connection con = new DBConnection().connection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        String sql = "SELECT 1 FROM Users WHERE phone = ?";
        try (Connection con = new DBConnection().connection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}
