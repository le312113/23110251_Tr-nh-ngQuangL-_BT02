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
}
