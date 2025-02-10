package repositories;

import data.PostgresDB;
import models.Order;
import repositories.interfaces.IOrderRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private final Connection con;

    public OrderRepository() {
        this.con = PostgresDB.getInstance("jdbc:postgresql://localhost:5432", "postgres", "0000", "postgres").getConnection();
    }

    @Override
    public void addOrder(Order order) {
        try {
            String sql = "INSERT INTO orders (user_id, product_id, quantity, total_price, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, order.getUserId());
            st.setInt(2, order.getProductId());
            st.setInt(3, order.getQuantity());
            st.setDouble(4, order.getTotalPrice());
            st.setString(5, order.getStatus());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getOrderById(int id) {
        try {
            String sql = "SELECT * FROM orders WHERE order_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Order(
                        rs.getInt("order_id"),
                        rs.getInt("user_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price"),
                        rs.getString("status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT o.order_id, o.user_id, o.product_id, o.quantity, o.total_price, o.status, " +
                    "u.name AS user_name, p.name AS product_name " +
                    "FROM orders o " +
                    "JOIN users u ON o.user_id = u.user_id " +
                    "JOIN products p ON o.product_id = p.product_id";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("order_id"),
                        rs.getInt("user_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price"),
                        rs.getString("status"),
                        rs.getString("user_name"),
                        rs.getString("product_name")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void updateOrder(Order order) {
        try {
            String sql = "UPDATE orders SET user_id = ?, product_id = ?, quantity = ?, total_price = ?, status = ? WHERE order_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, order.getUserId());
            st.setInt(2, order.getProductId());
            st.setInt(3, order.getQuantity());
            st.setDouble(4, order.getTotalPrice());
            st.setString(5, order.getStatus());
            st.setInt(6, order.getId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrderStatus(int orderId, String status) {
        try {
            String sql = "UPDATE orders SET status = ? WHERE order_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, status);
            st.setInt(2, orderId);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT * FROM orders WHERE user_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("order_id"),
                        rs.getInt("user_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price"),
                        rs.getString("status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
}
//1