package services;

import models.Product;
import repositories.interfaces.IProductRepository;

import java.util.List;


public class ProductService {
    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    public Product getProductById(int productId) {
        return productRepository.getProductById(productId);
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    public void decreaseProductCount(int productId, int quantity) {
        productRepository.decreaseProductCount(productId, quantity);
    }
}
