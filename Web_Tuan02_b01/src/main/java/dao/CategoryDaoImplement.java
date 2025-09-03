package dao;

import model.Category;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImplement implements CategoryDao {

    @Override
    public void insert(Category category) {
        String sql = "INSERT INTO Category(cate_name, icons, user_id) VALUES (?,?,?)";
        try (Connection con = new DBConnection().connection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, category.getCatename());
            ps.setString(2, category.getIcon());
            ps.setInt(3, category.getUserId());

            int rows = ps.executeUpdate();
            System.out.println("DEBUG insert affected rows = " + rows);
        } catch (Exception e) {
            System.err.println("ERROR INSERT Category: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void edit(Category category) {
        String sql = "UPDATE Category SET cate_name = ?, icons=? WHERE cate_id = ?";
        try {
            Connection con = new DBConnection().connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, category.getCatename());
            ps.setString(2, category.getIcon());
            ps.setInt(3, category.getCateid());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Category WHERE cate_id = ?";
        try {
            Connection con = new DBConnection().connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Category get(int id) {
        String sql = "SELECT * FROM Category WHERE cate_id = ? ";
        try {
            Connection con = new DBConnection().connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCateid(rs.getInt("cate_id"));
                category.setCatename(rs.getString("cate_name"));
                category.setIcon(rs.getString("icons"));
                return category;
            }} catch (Exception e) {

            e.printStackTrace();}
        return null;
    }
    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT cate_id, cate_name, icons, user_id FROM dbo.Category";
        try (Connection conn = new DBConnection().connection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Category category = new Category();
                category.setCateid(rs.getInt("cate_id"));
                category.setCatename(rs.getString("cate_name"));
                category.setIcon(rs.getString("icons"));
                category.setUserId(rs.getInt("user_id")); // QUAN TRỌNG
                categories.add(category);
            }
            System.out.println("DEBUG getAll() size = " + categories.size());
        } catch (Exception e) {
            System.err.println("ERROR getAll Category: " + e.getMessage());
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public List<Category> search(String keyword) {
        List<Category> categories = new ArrayList<Category>();
        return  categories;
    }
    @Override
    public List<Category> getAllByUser(int userId) {
        String sql = "SELECT id, cate_name, icons, user_id FROM Category WHERE user_id = ?";
        List<Category> categories = new ArrayList<>();
        try {
            Connection conn = new DBConnection().connection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Category category = new Category();
                    category.setUserId(rs.getInt("id"));
                    category.setCatename(rs.getString("cate_name"));
                    category.setIcon(rs.getString("icons"));
                    category.setUserId(rs.getInt("user_id"));
                    categories.add(category);
                }
            }
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
    } catch (Exception e) {
            e.printStackTrace();}
        return categories;
    }
    public Category get(String name){
        return  null;
    }
}
