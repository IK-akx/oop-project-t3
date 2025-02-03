package services;

import models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private List<Product> products = new ArrayList<>();
    private int nextProductId = 1;

    public ProductService() {
        products.add(new Product(nextProductId++, "Example Product", 10.0));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void updateProduct(int productId, String name, double price) {
        for (Product product : products) {
            if (product.getId() == productId) {
                product.setName(name);
                product.setPrice(price);
                System.out.println("Product updated.");
                return;
            }
        }
        System.out.println("Product not found.");
    }
}
