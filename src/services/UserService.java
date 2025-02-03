package services;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User("Admin", "admin@gmail.com", "adminpass", true));
    }

    public void register(String name, String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.println("User with this email already exists.");
                return;
            }
        }
        users.add(new User(name, email, password, false));
        System.out.println("User registered: " + name);
    }

    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void updateUser(String email, String name, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                user.setName(name);
                user.setPassword(password);
                System.out.println("User updated: " + email);
                return;
            }
        }
        System.out.println("User not found: " + email);
    }
}


