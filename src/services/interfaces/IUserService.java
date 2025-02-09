package services.interfaces;

import models.User;

import java.util.List;

public interface IUserService {
    void register(String name, String email, String password, boolean isAdmin);
    User login(String email, String password);
    List<User> getAllUsers();
    void updateUser(String email, String name, String password);
    User getUserByEmail(String email);
}
