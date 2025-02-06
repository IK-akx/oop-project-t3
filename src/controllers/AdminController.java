package controllers;

import services.*;
import models.*;

import java.util.List;

public class AdminController {
    private OrderService orderService;
    private ProductService productService;
    private UserService userService;
    private NotificationService notificationService;

    //OrderService orderService, ProductService productService, UserService userService, NotificationService notificationService
    public AdminController(UserService userService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public void generateReport() {
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            System.out.println("Order Report:");
            for (Order order : orders) {
                System.out.println("ID: " + order.getId() + ", Product: " + order.getProduct() +
                        ", Quantity: " + order.getQuantity() + ", Status: " + order.getStatus());
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
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void updateProduct(int productId, String name, double price) {
        productService.updateProduct(productId, name, price);
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
}
