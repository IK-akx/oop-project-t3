package services;

import models.Order;
import repositories.interfaces.IOrderRepository;

import java.util.List;

public class OrderService {
    private final IOrderRepository orderRepository;

    public OrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(int userId, int productId, int quantity, double totalPrice, String status) {
        Order order = new Order(0, userId, productId, quantity, totalPrice, status);
        orderRepository.addOrder(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public void updateOrderStatus(int orderId, String status) {
        Order order = orderRepository.getOrderById(orderId);
        if (order != null) {
            order.setStatus(status);
            orderRepository.updateOrder(order);
            System.out.println("Order status updated.");
        } else {
            System.out.println("Order not found.");
        }
    }
}
