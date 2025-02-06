package controllers;

import models.Notification;
import models.Product;
import models.User;
import models.Order;
import services.*;

import java.util.List;

public class UserController {
    private UserService userService;
    private OrderService orderService;
    private ProductService productService;
    private NotificationService notificationService;
    private SupportService supportService;

    public UserController(UserService userService, OrderService orderService, ProductService productService,
                          NotificationService notificationService, SupportService supportService) {
        this.userService = userService;
        this.orderService = orderService;
        this.productService = productService;
        this.notificationService = notificationService;
        this.supportService = supportService;
    }

    public void register(String name, String email, String password, boolean isAdmin) {
        userService.register(name, email, password, isAdmin);
    }

    public User login(String email, String password) {
        return userService.login(email, password);
    }

    public double getProductPriceById(int productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return product.getPrice();
        } else {
            System.out.println("Product not found with ID: " + productId);
            return 0.0;
        }
    }

    public void createOrder(int userId, int productId, int quantity, double totalPrice, String status) {
        Product product = productService.getProductById(productId);
        if (product != null && product.getCount() >= quantity) {
            orderService.createOrder(userId, productId, quantity, totalPrice, status);
            productService.decreaseProductCount(productId, quantity);
            System.out.println("Order created successfully!");
        } else {
            System.out.println("Order failed: Insufficient stock or product not found.");
        }
    }

    public List<Order> viewOrders() {
        return orderService.getAllOrders();
    }

    public List<Notification> viewNotifications(String email) {
        return notificationService.getNotificationsByEmail(email);
    }

    public List<Product> viewProducts() {
        return productService.getAllProducts();
    }

    public void sendSupportMessage(String email, String message) {
        supportService.sendSupportMessage(email, message);
    }
}
