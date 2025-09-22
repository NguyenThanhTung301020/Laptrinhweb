package thanhtung.services;

import java.util.List;
import thanhtung.models.CategoryModel;

public interface CategoryServices {
    List<CategoryModel> getAllCategories();
    CategoryModel getCategoryById(int id);
    boolean addCategory(CategoryModel category);
    boolean updateCategory(CategoryModel category);
    boolean deleteCategory(int id);
}