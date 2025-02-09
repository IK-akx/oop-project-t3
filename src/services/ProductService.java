package services;

import models.ProductCategory;
import models.Product;
import repositories.interfaces.ICategoryRepository;
import repositories.interfaces.IProductRepository;
import services.interfaces.IProductService;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService implements IProductService {
    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    public ProductService(IProductRepository productRepository, ICategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public List<Product> getProductsByCategory(int categoryId) {
        if (categoryId == -1) {
            return getAllProducts();
        }
        return productRepository.getProductsByCategory(categoryId);
    }

    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    public void decreaseProductCount(int productId, int quantity) {
        productRepository.decreaseProductCount(productId, quantity);
    }

    public List<String> getProductNames() {
        return productRepository.getAllProducts().stream()
                .map(Product::getName)
                .collect(Collectors.toList());
    }

    public void addCategory(ProductCategory category) {
        categoryRepository.addCategory(category);
    }

    public ProductCategory getCategoryById(int id) {
        return categoryRepository.getCategoryById(id);
    }

    public List<ProductCategory> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    public void updateCategory(ProductCategory category) {
        categoryRepository.updateCategory(category);
    }
}
