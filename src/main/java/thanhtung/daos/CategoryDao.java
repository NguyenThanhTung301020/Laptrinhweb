package thanhtung.daos;

import java.util.List;
import thanhtung.models.CategoryModel;

public interface CategoryDao {
    List<CategoryModel> getAllCategories();
    CategoryModel getCategoryById(int id);
    void addCategory(CategoryModel category);
    void updateCategory(CategoryModel category);
    void deleteCategory(int id);
}