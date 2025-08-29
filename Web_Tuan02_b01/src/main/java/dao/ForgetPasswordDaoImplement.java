package dao;

import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ForgetPasswordDaoImplement implements ForgetPasswordDao {
    @Override
    public void update(String Email,String newPassword){
        String sql="UPDATE Users SET password = ? WHERE email = ?";
        try (Connection con = new DBConnection().connection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1,Email);
            ps.setString(2,newPassword);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
