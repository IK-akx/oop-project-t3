package factories;

import models.Admin;
import models.Customer;
import models.User;

public class UserFactory {
    public static User createUser(String role, int id, String name, String email, String password) {
        if (role.equalsIgnoreCase("admin")) {
            return new Admin(id, name, email, password);
        } else {
            return new Customer(id, name, email, password);
        }
    }
}
