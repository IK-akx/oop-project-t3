package services.interfaces;

import models.Product;
import models.ProductCategory;

import java.util.List;

public interface IProductService {
    void addProduct(Product product);
    Product getProductById(int id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(int categoryId);
    void updateProduct(Product product);
    void decreaseProductCount(int productId, int quantity);
    List<ProductCategory> getAllCategories();
    ProductCategory getCategoryById(int id);
    void updateCategory(ProductCategory category);
    void addCategory(ProductCategory category);
}
