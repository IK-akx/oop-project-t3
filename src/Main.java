import controllers.AdminController;
import controllers.UserController;
import data.interfaces.IDB;
import services.*;
import data.*;



public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "0000", "postgres");

        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        ProductService productService = new ProductService();
        NotificationService notificationService = new NotificationService();
        AdminController adminController = new AdminController(orderService, productService, userService, notificationService);
        SupportService supportService = new SupportService();
        UserController userController = new UserController(userService, orderService, productService, notificationService, supportService);

        MyApplication app = new MyApplication(userController, adminController);
        app.start();

        db.close();

    }
}