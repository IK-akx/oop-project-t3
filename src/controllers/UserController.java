package controllers;

import controllers.interfaces.IUserController;
import models.*;
import services.interfaces.*;

import java.util.List;
import java.util.Scanner;

public class UserController implements IUserController {
    private IUserService userService;
    private IOrderService orderService;
    private IProductService productService;
    private INotificationService notificationService;
    private ISupportService supportService;

    public UserController(IUserService userService, IOrderService orderService, IProductService productService,
                          INotificationService notificationService, ISupportService supportService) {
        this.userService = userService;
        this.orderService = orderService;
        this.productService = productService;
        this.notificationService = notificationService;
        this.supportService = supportService;
    }

    Scanner scanner = new Scanner(System.in);


    private boolean isValidPass(String password) {
        return password != null && password.length() >= 6;
    }

    public void register(String name, String email, String password, boolean isAdmin) {
        if (isValidPass(password)) {
            userService.register(name, email, password, isAdmin);
            System.out.println("User register successfully.");
        } else {
            System.out.println("Invalid email or password format");
        }
    }

    public User login(String email, String password) {
        if (isValidPass(password)) {
            User user = userService.login(email, password);
            if (user != null) {
                return user;
            } else {
                System.out.println("Incorrect email or password.");
                return null;
            }
        } else {
            System.out.println("Invalid email or password format.");
            return null;
        }
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
        if (product != null) {
            if (product.getCount() >= quantity) {
                orderService.createOrder(userId, productId, quantity, totalPrice, status);
                productService.decreaseProductCount(productId, quantity);
                System.out.println("Order created successfully!");
                product = productService.getProductById(productId); // Обновляем данные о продукте
                if (product.getCount() <= 0) {
                    System.out.println("The product is out of stock.");
                }
            } else {
                System.out.println("Order failed: Insufficient stock. Only " + product.getCount() + " items left.");
            }
        } else {
            System.out.println("Order failed: Product not found.");
        }
    }

    public List<Order> viewOrders(int userId) {
        return orderService.getOrdersByUserId(userId);
    }

    public List<Notification> viewNotifications(String email) {
        return notificationService.getNotificationsByEmail(email);
    }

    public void viewProducts() {
        List<ProductCategory> categories = productService.getAllCategories();
        System.out.println("Categories:");
        for (ProductCategory category : categories) {
            System.out.println("ID: " + category.getId() + ", Name: " + category.getName());
        }

        System.out.println("Enter category ID to view products in that category, or type 'all' to view all products:");
        String input = scanner.nextLine();

        List<Product> products;
        if (input.equalsIgnoreCase("all")) {
            products = productService.getAllProducts();
        } else {
            int categoryId = Integer.parseInt(input);
            products = productService.getProductsByCategory(categoryId);
        }

        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : products) {
                System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + ", Price: " + product.getPrice() +
                        ", Count: " + product.getCount() + ", Category: " + product.getCategory().getName());
            }
        }
    }

    public void sendSupportMessage(String email, String message) {
        supportService.sendSupportMessage(email, message);
    }
}
