package repositories.interfaces;

import models.User;

import java.util.List;

public interface IUserRepository {
    void addUser(User user);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    void updateUser(User user);
}

