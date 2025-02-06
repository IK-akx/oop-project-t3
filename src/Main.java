import controllers.AdminController;
import controllers.UserController;
import data.interfaces.IDB;
import data.PostgresDB;
import repositories.*;
import repositories.interfaces.*;
import services.*;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "0000", "postgres");


        IUserRepository userRepository = new UserRepository(db);
        IOrderRepository orderRepository = new OrderRepository(db);
        IProductRepository productRepository = new ProductRepository(db);
        INotificationRepository notificationRepository = new NotificationRepository(db);
        ISupportRepository supportRepository = new SupportRepository(db);


        UserService userService = new UserService(userRepository);
        OrderService orderService = new OrderService(orderRepository);
        ProductService productService = new ProductService(productRepository);
        NotificationService notificationService = new NotificationService(notificationRepository);
        SupportService supportService = new SupportService(supportRepository, userService);


        AdminController adminController = new AdminController( orderService, productService,userService, notificationService , supportService);
        UserController userController = new UserController(userService, orderService, productService, notificationService, supportService);


        MyApplication app = new MyApplication(userController, adminController);
        app.start();


        db.close();
    }
}
