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


        IUserRepository userRepository = new UserRepository();
        IOrderRepository orderRepository = new OrderRepository();
        IProductRepository productRepository = new ProductRepository();
        INotificationRepository notificationRepository = new NotificationRepository(db);
        ISupportRepository supportRepository = new SupportRepository(db);
        ICategoryRepository categoryRepository = new CategoryRepository();


        UserService userService = new UserService(userRepository);
        OrderService orderService = new OrderService(orderRepository);
        ProductService productService = new ProductService(productRepository, categoryRepository);
        NotificationService notificationService = new NotificationService(notificationRepository);
        SupportService supportService = new SupportService(supportRepository, userService);


        AdminController adminController = new AdminController( orderService, productService,userService, notificationService , supportService);
        UserController userController = new UserController(userService, orderService, productService, notificationService, supportService);


        MyApplication app = new MyApplication(userController, adminController, orderService);
        app.start();


        db.close();
    }
}
