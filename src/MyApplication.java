import java.util.List;
import java.util.Scanner;


import controllers.interfaces.IUserController;
import controllers.interfaces.IAdminController;
import models.Notification;
import models.Order;
import models.User;
import services.OrderService;

public class MyApplication {
    private IUserController userController;
    private IAdminController adminController;
    private OrderService orderService;

    public MyApplication(IUserController userController, IAdminController adminController, OrderService orderService) {
        this.userController = userController;
        this.adminController = adminController;
        this.orderService = orderService;
    }

    Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("Exiting the application.");
                break;
            }

            if (choice == 1) {
                register();
            } else if (choice == 2) {
                login();
            }
        }
    }

    private void register() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Is Admin or Customer? (A/C): ");
        String roleDefine = scanner.nextLine();
        boolean isAdmin = roleDefine.equalsIgnoreCase("A");

        if (isAdmin) {
            System.out.print("Enter Admin Code: ");
            String adminCode = scanner.nextLine();
            int realAdminCode = 120628;
            if (!adminCode.equals(String.valueOf(realAdminCode))) {
                System.out.println("Incorrect Admin Code. Registration as Admin failed.");
                return;
            }
        }

        userController.register(name, email, password, isAdmin);
    }

    private void login() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = userController.login(email, password);

        if (user == null) {
            System.out.println("Incorrect email or password.");
            return;
        }

        System.out.println("Welcome, " + user.getName());
        if (user.isAdmin()) {
            adminMenu();
        } else {
            userMenu(user);
        }
    }

    private void adminMenu() {
        while (true) {
            System.out.println("1. Generate Report");
            System.out.println("2. Update Order Status");
            System.out.println("3. Send Notification");
            System.out.println("4. View Products");
            System.out.println("5. Add Product");
            System.out.println("6. Update Product");
            System.out.println("7. View Users");
            System.out.println("8. Update User");
            System.out.println("9. View Support Messages");
            System.out.println("10. View Orders Sorted by Price");
            System.out.println("11. Update Category");
            System.out.println("12. Add New Category");
            System.out.println("0. Logout");
            int adminChoice = scanner.nextInt();
            scanner.nextLine();

            if (adminChoice == 0) break;

            switch (adminChoice) {
                case 1:
                    adminController.generateReport();
                    break;
                case 2:
                    System.out.print("Enter Order ID: ");
                    int orderId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new status: ");
                    String status = scanner.nextLine();
                    adminController.updateOrderStatus(orderId, status);
                    break;
                case 3:
                    System.out.print("Enter customer email: ");
                    String customerEmail = scanner.nextLine();
                    System.out.print("Enter message: ");
                    String message = scanner.nextLine();
                    adminController.sendNotification(customerEmail, message);
                    break;
                case 4:
                    adminController.viewProducts();
                    break;
                case 5:
                    adminController.addProduct();
                    break;
                case 6:
                    System.out.print("Enter Product ID: ");
                    int productId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter new price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter new count: ");
                    int count = scanner.nextInt();
                    System.out.print("Enter category ID: ");
                    int categoryId = scanner.nextInt();
                    scanner.nextLine();

                    adminController.updateProduct(productId, productName, price, count, categoryId);
                    break;
                case 7:
                    adminController.viewUsers();
                    break;
                case 8:
                    System.out.print("Enter user email: ");
                    String userEmail = scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter new password: ");
                    String userPassword = scanner.nextLine();
                    adminController.updateUser(userEmail, userName, userPassword);
                    break;
                case 9:
                    adminController.viewSupportMessages();
                    break;
                case 10:
                    List<Order> sortedOrders = adminController.getOrdersSortedByPrice();
                    System.out.println("Orders sorted by price:");
                    for (Order order : sortedOrders) {
                        System.out.println("ID: " + order.getId() + ", User ID: " + order.getUserId() +
                                ", Product ID: " + order.getProductId() + ", Quantity: " + order.getQuantity() +
                                ", Total Price: " + order.getTotalPrice() + ", Status: " + order.getStatus());
                    }
                    break;
                case 11:
                    adminController.updateCategory();
                    break;
                case 12:
                    adminController.addCategory();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void userMenu(User user) {
        while (true) {
            System.out.println("1. Create Order");
            System.out.println("2. View Orders");
            System.out.println("3. View Notifications");
            System.out.println("4. View Products");
            System.out.println("5. Send Support Message");
            System.out.println("0. Logout");
            int userChoice = scanner.nextInt();
            scanner.nextLine();

            if (userChoice == 0) break;

            switch (userChoice) {
                case 1:
                    System.out.print("Enter product ID: ");
                    int productId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    double price = userController.getProductPriceById(productId);
                    if (price > 0) {
                        double totalPrice = quantity * price;
                        userController.createOrder(user.getId(), productId, quantity, totalPrice, "Pending");
                    } else {
                        System.out.println("Failed to create order: Product not found.");
                    }
                    break;
                case 2:
                    System.out.println("Your Orders:");
                    for (Order order : userController.viewOrders(user.getId())) {
                        System.out.println("ID: " + order.getId() + ", Product ID: " + order.getProductId() +
                                ", Quantity: " + order.getQuantity() + ", Total Price: " + order.getTotalPrice() +
                                ", Status: " + order.getStatus());
                    }
                    break;
                case 3:
                    System.out.println("Your Notifications:");
                    for (Notification notification : userController.viewNotifications(user.getEmail())) {
                        System.out.println(notification.getMessage());
                    }
                    break;
                case 4:
                    userController.viewProducts();
                    break;
                case 5:
                    System.out.print("Enter your message: ");
                    String message = scanner.nextLine();
                    userController.sendSupportMessage(user.getEmail(), message);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}