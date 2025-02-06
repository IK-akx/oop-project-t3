import java.util.Scanner;

import controllers.AdminController;
import controllers.UserController;
import models.Notification;
import models.Order;
import models.Product;
import models.User;

public class MyApplication {
    private UserController userController;
    private AdminController adminController;
    private Scanner scanner;

    public MyApplication(UserController userController, AdminController adminController) {
        this.userController = userController;
        this.adminController = adminController;
        this.scanner = new Scanner(System.in);
    }

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
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Is Admin or Customer? (A/C): ");
                String roleDefine = scanner.nextLine();
                boolean isAdmin = roleDefine.equals("A");

                String realAdminCode = "120628";
                if (isAdmin) {
                    System.out.print("Enter Admin Code: ");
                    String adminCode = scanner.nextLine();
                    if (!adminCode.equals(realAdminCode)) {
                        System.out.println("Incorrect Admin Code. Registration as Admin failed.");
                        continue;
                    }
                }

                scanner.nextLine();
                userController.register(name, email, password, isAdmin);
            } else if (choice == 2) {
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                User user = userController.login(email, password);

                if (user == null) {
                    System.out.println("Incorrect email or password.");
                    continue;
                }

                System.out.println("Welcome, " + user.getName());
                if (user.isAdmin()) {
                    while (true) {
                        System.out.println("1. Generate Report");
                        System.out.println("2. Update Order Status");
                        System.out.println("3. Send Notification");
                        System.out.println("4. View Products");
                        System.out.println("5. Update Product");
                        System.out.println("6. View Users");
                        System.out.println("7. Update User");
                        System.out.println("8. View Support Messages");
                        System.out.println("0. Logout");
                        int adminChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (adminChoice == 0) break;

                        if (adminChoice == 1) {
                            adminController.generateReport();
                        } else if (adminChoice == 2) {
                            System.out.print("Enter Order ID: ");
                            int orderId = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            System.out.print("Enter new status: ");
                            String status = scanner.nextLine();
                            adminController.updateOrderStatus(orderId, status);
                        } else if (adminChoice == 3) {
                            System.out.print("Enter customer email: ");
                            String customerEmail = scanner.nextLine();
                            System.out.print("Enter message: ");
                            String message = scanner.nextLine();
                            adminController.sendNotification(customerEmail, message);
                        } else if (adminChoice == 4) {
                            adminController.viewProducts();
                        } if (adminChoice == 5) {
                            System.out.print("Enter Product ID: ");
                            int productId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter new name: ");
                            String productName = scanner.nextLine();
                            System.out.print("Enter new price: ");
                            double price = scanner.nextDouble();
                            System.out.print("Enter new count: ");
                            int count = scanner.nextInt();
                            scanner.nextLine();

                            adminController.updateProduct(productId, productName, price, count);

                        } else if (adminChoice == 6) {
                            adminController.viewUsers();
                        } else if (adminChoice == 7) {
                            System.out.print("Enter user email: ");
                            String userEmail = scanner.nextLine();
                            System.out.print("Enter new name: ");
                            String userName = scanner.nextLine();
                            System.out.print("Enter new password: ");
                            String userPassword = scanner.nextLine();
                            adminController.updateUser(userEmail, userName, userPassword);
                        }else if (adminChoice == 8) {
                            adminController.viewSupportMessages();
                        }
                    }
                }else {
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

                        if (userChoice == 1) {
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
                                System.out.println("Order created successfully!");
                            } else {
                                System.out.println("Failed to create order: Product not found.");
                            }

                        } else if (userChoice == 2) {
                            System.out.println("Your Orders:");
                            for (Order order : userController.viewOrders()) {
                                System.out.println("ID: " + order.getId() + ", Product ID: " + order.getProductId() +
                                        ", Quantity: " + order.getQuantity() + ", Total Price: " + order.getTotalPrice() +
                                        ", Status: " + order.getStatus());
                            }
                        }
                        else if (userChoice == 3) {
                            System.out.println("Your Notifications:");
                            for (Notification notification : userController.viewNotifications(user.getEmail())) {
                                System.out.println(notification.getMessage());
                            }
                        } else if (userChoice == 4) {
                            System.out.println("Available Products:");
                            for (Product product : userController.viewProducts()) {
                                System.out.println("ID: " + product.getId() + ", Name: " + product.getName() +
                                        ", Price: " + product.getPrice());
                            }
                        } else if (userChoice == 5) {
                            System.out.print("Enter your message: ");
                            String message = scanner.nextLine();
                            userController.sendSupportMessage(user.getEmail(), message);
                        }
                    }
                }
            }
        }
    }
}
