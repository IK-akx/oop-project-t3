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


        UserService userService = new UserService(userRepository);


        AdminController adminController = new AdminController( userService );
        //orderService, productService, ____ , notificationService
        UserController userController = new UserController(userService);
        //orderService, productService, notificationService, supportService

        MyApplication app = new MyApplication(userController, adminController);
        app.start();


        db.close();
    }
}
