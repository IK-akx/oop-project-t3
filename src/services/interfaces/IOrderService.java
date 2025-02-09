package services.interfaces;

import models.Order;

import java.util.List;

public interface IOrderService {
    void createOrder(int userId, int productId, int quantity, double totalPrice, String status);
    List<Order> getAllOrders();
    List<Order> getOrdersSortedByPrice();
    void updateOrderStatus(int orderId, String status);
    List<Order> getOrdersByUserId(int userId);
}
