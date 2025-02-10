package repositories.interfaces;

import models.Order;

import java.util.List;

public interface IOrderRepository {
    void addOrder(Order order);
    List<Order> getAllOrders();
    void updateOrderStatus(int orderId, String status);
    List<Order> getOrdersByUserId(int userId);
    void updateOrder(Order order);
    Order getOrderById(int id);
}

