package thanhtung.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import thanhtung.configs.SQLConnect;
import thanhtung.daos.CategoryDao;
import thanhtung.models.CategoryModel;

public class CategoryDaoImpl implements CategoryDao {

    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    @Override
    public List<CategoryModel> getAllCategories() {
        List<CategoryModel> categories = new ArrayList<>();
        String sql = "SELECT * FROM [Category] ORDER BY id DESC";
        try {
            conn = new SQLConnect().getConnectionW();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CategoryModel category = new CategoryModel();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                category.setImage(rs.getString("image"));
                category.setCreatedDate(rs.getDate("createdDate"));
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return categories;
    }

    @Override
    public CategoryModel getCategoryById(int id) {
        String sql = "SELECT * FROM [Category] WHERE id = ?";
        try {
            conn = new SQLConnect().getConnectionW();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                CategoryModel category = new CategoryModel();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                category.setImage(rs.getString("image"));
                category.setCreatedDate(rs.getDate("createdDate"));
                return category;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void addCategory(CategoryModel category) {
        String sql = "INSERT INTO [Category] (name, description, image) VALUES (?,?,?)";
        try {
            conn = new SQLConnect().getConnectionW();
            ps = conn.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setString(3, category.getImage());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateCategory(CategoryModel category) {
        String sql = "UPDATE [Category] SET name = ?, description = ?, image = ? WHERE id = ?";
        try {
            conn = new SQLConnect().getConnectionW();
            ps = conn.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setString(3, category.getImage());
            ps.setInt(4, category.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteCategory(int id) {
        String sql = "DELETE FROM [Category] WHERE id = ?";
        try {
            conn = new SQLConnect().getConnectionW();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}