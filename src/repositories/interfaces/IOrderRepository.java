package repositories.interfaces;

import models.Order;

import java.util.List;

public interface IOrderRepository {
    void addOrder(Order order);
    Order getOrderById(int id);
    List<Order> getAllOrders();
    void updateOrder(Order order);
    void updateOrderStatus(int orderId, String status);
}

