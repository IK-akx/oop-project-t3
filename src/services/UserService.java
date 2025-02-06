package services;

import models.User;
import models.Admin;
import models.Customer;
import repositories.interfaces.IUserRepository;

import java.util.List;

public class UserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String name, String email, String password, boolean isAdmin) {

        if (userRepository.getUserByEmail(email) != null) {
            System.out.println("User with this email already exists.");
            return;
        }
        User newUser;
        if (isAdmin) {
            newUser = new Admin(name, email, password);
        } else {
            newUser = new Customer(name, email, password);
        }
        userRepository.addUser(newUser);
        System.out.println("User registered: " + name);
    }


    public User login(String email, String password) {
        User user = userRepository.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void updateUser(String email, String name, String password) {
        User user = userRepository.getUserByEmail(email);
        if (user != null) {
            user.setName(name);
            user.setPassword(password);
            userRepository.updateUser(user);
        }
    }
}
