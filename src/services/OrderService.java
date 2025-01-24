package services;

import models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders = new ArrayList<>();
    private int nextOrderId = 1;

    public void createOrder(String product, int quantity) {
        orders.add(new Order(nextOrderId++, product, quantity, "Pending"));
        System.out.println("Order created.");
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public void updateOrderStatus(int orderId, String status) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                order.setStatus(status);
                System.out.println("Order status updated.");
                return;
            }
        }
        System.out.println("Order not found.");
    }
}

