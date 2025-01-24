package controllers;

import services.UserService;
import models.User;

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void register(String name, String email, String password) {
        userService.register(name, email, password);
    }

    public User login(String email, String password) {
        return userService.login(email, password);
    }
}


