package repositories.interfaces;

import models.Product;
import java.util.List;

public interface IProductRepository {
    void addProduct(Product product);
    Product getProductById(int id);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void decreaseProductCount(int productId, int quantity); // Новый метод для уменьшения количества продукта
    List<Product> getProductsByCategory(int categoryId);
}


