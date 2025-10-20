package thanhtung.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import thanhtung.configs.SQLConnect;
import thanhtung.daos.CategoryDao;
import thanhtung.models.CategoryModel;

public class CategoryDaoImpl implements CategoryDao {

    SQLConnect db = new SQLConnect();

    @Override
    public List<CategoryModel> getAllCategories() {
        List<CategoryModel> list = new ArrayList<CategoryModel>();
        String sql = "SELECT * FROM dbo.Category";
        try (Connection con = db.getConnectionW();  // Sử dụng Windows Auth
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CategoryModel category = new CategoryModel();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                category.setImage(rs.getString("image"));
                category.setCreatedDate(rs.getDate("createdDate"));
                list.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public CategoryModel getCategoryById(int id) {
        String sql = "SELECT * FROM dbo.Category WHERE id = ?";
        try (Connection con = db.getConnectionW();  // Sử dụng Windows Auth
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CategoryModel category = new CategoryModel();
                    category.setId(rs.getInt("id"));
                    category.setName(rs.getString("name"));
                    category.setDescription(rs.getString("description"));
                    category.setImage(rs.getString("image"));
                    category.setCreatedDate(rs.getDate("createdDate"));
                    return category;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addCategory(CategoryModel category) {
        String sql = "INSERT INTO dbo.Category (name, description, image, createdDate) VALUES (?, ?, ?, ?)";
        try (Connection con = db.getConnectionW();  // Sử dụng Windows Auth
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setString(3, category.getImage());
            ps.setDate(4, category.getCreatedDate());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategory(CategoryModel category) {
        String sql = "UPDATE dbo.Category SET name = ?, description = ?, image = ? WHERE id = ?";
        try (Connection con = db.getConnectionW();  // Sử dụng Windows Auth
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setString(3, category.getImage());
            ps.setInt(4, category.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(int id) {
        String sql = "DELETE FROM dbo.Category WHERE id = ?";
        try (Connection con = db.getConnectionW();  // Sử dụng Windows Auth
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}