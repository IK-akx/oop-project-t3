package repositories.interfaces;

import models.ProductCategory;

import java.util.List;

public interface ICategoryRepository {
    void addCategory(ProductCategory category);
    ProductCategory getCategoryById(int id);
    List<ProductCategory> getAllCategories();
    void updateCategory(ProductCategory category);
}
