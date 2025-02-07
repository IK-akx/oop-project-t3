package controllers;

import categories.ProductCategory;
import services.*;
import models.*;



import java.util.List;

public class AdminController {
    private OrderService orderService;
    private ProductService productService;
    private UserService userService;
    private NotificationService notificationService;
    private SupportService supportService;

    public AdminController(OrderService orderService, ProductService productService, UserService userService, NotificationService notificationService, SupportService supportService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
        this.notificationService = notificationService;
        this.supportService = supportService;
    }

    public void generateReport() {
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            System.out.println("Order Report:");
            for (Order order : orders) {
                System.out.println("ID: " + order.getId() + ", User: " + order.getUserName() + ", Product: " + order.getProductName() +
                        ", Quantity: " + order.getQuantity() + ", Total Price: " + order.getTotalPrice() +
                        ", Status: " + order.getStatus());
            }
        }
    }

    public void updateOrderStatus(int orderId, String status) {
        orderService.updateOrderStatus(orderId, status);
    }

    public void sendNotification(String email, String message) {
        notificationService.sendNotification(email, message);
    }

    public void viewProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : products) {
                System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + ", Price: " + product.getPrice() +
                        ", Count: " + product.getCount() + ", Category: " + product.getCategory().getName());
            }
        }
    }

    public void addCategory(String name) {
        productService.addCategory(new ProductCategory(0, name));
        System.out.println("Category added successfully!");
    }

    public void updateCategory(int categoryId, String name) {
        productService.updateCategory(new ProductCategory(categoryId, name));
        System.out.println("Category updated successfully!");
    }

    public void updateProduct(int productId, String name, double price, int count, int categoryId) {
        ProductCategory category = productService.getCategoryById(categoryId);
        if (category == null) {
            System.out.println("Invalid category ID.");
            return;
        }

        Product product = new Product(productId, name, price, count, category);
        productService.updateProduct(product);
        System.out.println("Product updated successfully!");
    }

    public void decreaseProductCount(int productId, int quantity) {
        productService.decreaseProductCount(productId, quantity);
        System.out.println("Product count updated for product ID: " + productId);
    }

    public void viewUsers() {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void updateUser(String email, String name, String password) {
        userService.updateUser(email, name, password);
    }

    public void viewSupportMessages() {
        List<SupportMessage> supportMessages = supportService.getAllSupportMessages();
        if (supportMessages.isEmpty()) {
            System.out.println("No support messages available.");
        } else {
            System.out.println("Support Messages:");
            for (SupportMessage message : supportMessages) {
                System.out.println(message);
            }
        }
    }
}
