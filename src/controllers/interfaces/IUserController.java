package controllers.interfaces;

import models.Notification;
import models.Order;
import models.User;

import java.util.List;

public interface IUserController {
    void register(String name, String email, String password, boolean isAdmin);
    User login(String email, String password);
    double getProductPriceById(int productId);
    void createOrder(int userId, int productId, int quantity, double totalPrice, String status);
    List<Order> viewOrders(int userId);
    List<Notification> viewNotifications(String email);
    void viewProducts();
    void sendSupportMessage(String email, String message);
}
