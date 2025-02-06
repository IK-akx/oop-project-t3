package repositories;

import data.interfaces.IDB;
import models.Order;
import repositories.interfaces.IOrderRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private final IDB db;

    public OrderRepository(IDB db) {
        this.db = db;
    }

    @Override
    public void addOrder(Order order) {
        try (Connection con = db.getConnection()) {
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
        try (Connection con = db.getConnection()) {
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
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM orders";
            PreparedStatement st = con.prepareStatement(sql);
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

    @Override
    public void updateOrder(Order order) {
        try (Connection con = db.getConnection()) {
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
}
