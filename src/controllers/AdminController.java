package controllers;

import services.OrderService;
import models.Order;

import java.util.List;

public class AdminController {
    private OrderService orderService;

    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    public void generateReport() {
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            System.out.println("Order Report:");
            for (Order order : orders) {
                System.out.println("ID: " + order.getId() + ", Product: " + order.getProduct() +
                        ", Quantity: " + order.getQuantity() + ", Status: " + order.getStatus());
            }
        }
    }

    public void updateOrderStatus(int orderId, String status) {
        orderService.updateOrderStatus(orderId, status);
    }
}

