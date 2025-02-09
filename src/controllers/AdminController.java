package controllers;

import controllers.interfaces.IAdminController;
import models.ProductCategory;
import services.interfaces.*;
import models.*;



import java.util.List;
import java.util.Scanner;

public class AdminController implements IAdminController {
    private IOrderService orderService;
    private IProductService productService;
    private IUserService userService;
    private INotificationService notificationService;
    private ISupportService supportService;

    public AdminController(IOrderService orderService, IProductService productService, IUserService userService, INotificationService notificationService, ISupportService supportService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
        this.notificationService = notificationService;
        this.supportService = supportService;
    }

    Scanner scanner = new Scanner(System.in);

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

    @Override
    public List<Order> getOrdersSortedByPrice() {
        return orderService.getOrdersSortedByPrice();
    }

    public void sendNotification(String email, String message) {
        notificationService.sendNotification(email, message);
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


    @Override
    public void addCategory() {
        System.out.println("Enter new category name:");
        String categoryName = scanner.nextLine();
        ProductCategory category = new ProductCategory(0, categoryName);
        productService.addCategory(category);
        System.out.println("New category added successfully!");
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

    public void updateCategory() {
        List<ProductCategory> categories = productService.getAllCategories();
        System.out.println("Categories:");
        for (ProductCategory category : categories) {
            System.out.println("ID: " + category.getId() + ", Name: " + category.getName());
        }

        System.out.println("Enter category ID to update:");
        int categoryId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.println("Enter new name for the category:");
        String newName = scanner.nextLine();

        ProductCategory category = new ProductCategory(categoryId, newName);
        productService.updateCategory(category);
        System.out.println("Category updated successfully!");
    }

    @Override
    public void addProduct() {
        System.out.println("Enter product name:");
        String productName = scanner.nextLine();
        System.out.println("Enter product price:");
        double price = scanner.nextDouble();
        System.out.println("Enter product count:");
        int count = scanner.nextInt();
        System.out.println("Enter category ID:");
        int categoryId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        ProductCategory category = productService.getCategoryById(categoryId);
        if (category == null) {
            System.out.println("Invalid category ID.");
            return;
        }

        Product product = new Product(0, productName, price, count, category);
        productService.addProduct(product);
        System.out.println("Product added successfully!");
    }
}
