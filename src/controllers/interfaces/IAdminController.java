package controllers.interfaces;

import models.Order;

import java.util.List;

public interface IAdminController {
    void generateReport();
    void updateOrderStatus(int orderId, String status);
    void sendNotification(String email, String message);
    void viewProducts();
    void addCategory();
    void updateCategory();
    void updateProduct(int productId, String name, double price, int count, int categoryId);
    void viewUsers();
    void updateUser(String email, String name, String password);
    void viewSupportMessages();
    void addProduct();
    List<Order> getOrdersSortedByPrice();
}
