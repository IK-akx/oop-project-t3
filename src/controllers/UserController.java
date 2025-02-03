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

    public void register(String name, String email, String password) {
        userService.register(name, email, password);
    }

    public User login(String email, String password) {
        return userService.login(email, password);
    }

    public void createOrder(String product, int quantity) {
        orderService.createOrder(product, quantity);
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

