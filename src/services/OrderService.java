package services;

import models.Order;
import repositories.interfaces.IOrderRepository;
import services.interfaces.IOrderService;

import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class OrderService implements IOrderService {
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

    public List<Order> getOrdersSortedByPrice() {
        return orderRepository.getAllOrders().stream()
                .sorted(Comparator.comparingDouble(Order::getTotalPrice))
                .collect(Collectors.toList());
    }

    public void updateOrderStatus(int orderId, String status) {
        orderRepository.updateOrderStatus(orderId, status);
    }

    public List<Order> getOrdersByUserId(int userId) {
        return orderRepository.getOrdersByUserId(userId);
    }
}
