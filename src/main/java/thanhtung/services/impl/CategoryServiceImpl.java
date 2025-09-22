package thanhtung.services.impl;

import java.util.List;

import thanhtung.daos.CategoryDao;
import thanhtung.daos.impl.CategoryDaoImpl;
import thanhtung.models.CategoryModel;
import thanhtung.services.CategoryServices;

public class CategoryServiceImpl implements CategoryServices {

    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<CategoryModel> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @Override
    public CategoryModel getCategoryById(int id) {
        return categoryDao.getCategoryById(id);
    }

    @Override
    public boolean addCategory(CategoryModel category) {
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            return false;
        }
        // Mặc định image nếu không upload
        if (category.getImage() == null || category.getImage().trim().isEmpty()) {
            category.setImage("uploads/default-avatar.jpg"); // Ảnh bạn gửi
        }
        categoryDao.addCategory(category);
        return true;
    }

    @Override
    public boolean updateCategory(CategoryModel category) {
        if (category.getId() == 0 || category.getName() == null || category.getName().trim().isEmpty()) {
            return false;
        }
        // Giữ image cũ nếu không upload mới
        categoryDao.updateCategory(category);
        return true;
    }

    @Override
    public boolean deleteCategory(int id) {
        if (id == 0) {
            return false;
        }
        categoryDao.deleteCategory(id);
        return true;
    }
}