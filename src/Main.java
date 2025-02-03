import java.util.Scanner;

import models.*;
import controllers.*;
import services.*;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        UserController userController = new UserController(userService);
        OrderService orderService = new OrderService();
        AdminController adminController = new AdminController(orderService);

        Scanner scanner = new Scanner(System.in);

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
                userController.register(name, email, password);
            } else if (choice == 2) {
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                User user = userController.login(email, password);

                if (user == null) {
                    System.out.println("Invalid email or password.");
                    continue;
                }

                System.out.println("Welcome, " + user.getName());
                if (user.isAdmin()) {
                    while (true) {
                        System.out.println("1. Generate Report");
                        System.out.println("2. Update Order Status");
                        System.out.println("0. Logout");
                        int adminChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (adminChoice == 0) break;

                        if (adminChoice == 1) {
                            adminController.generateReport();
                        } else if (adminChoice == 2) {
                            System.out.print("Enter Order ID: ");
                            int orderId = scanner.nextInt();
                            System.out.print("Enter new status: ");
                            scanner.nextLine();
                            String status = scanner.nextLine();
                            adminController.updateOrderStatus(orderId, status);
                        }
                    }
                } else {
                    while (true) {
                        System.out.println("1. Create Order");
                        System.out.println("2. View Orders");
                        System.out.println("0. Logout");
                        int userChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (userChoice == 0) break;

                        if (userChoice == 1) {
                            System.out.print("Enter product: ");
                            String product = scanner.nextLine();
                            System.out.print("Enter quantity: ");
                            int quantity = scanner.nextInt();
                            orderService.createOrder(product, quantity);
                        } else if (userChoice == 2) {
                            System.out.println("Your Orders:");
                            for (Order order : orderService.getAllOrders()) {
                                System.out.println("ID: " + order.getId() + ", Product: " + order.getProduct() +
                                        ", Quantity: " + order.getQuantity() + ", Status: " + order.getStatus());
                            }
                        }
                    }
                }
            }
        }
        scanner.close();
    }
}
