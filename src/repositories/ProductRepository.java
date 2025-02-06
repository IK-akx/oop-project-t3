package repositories;

import data.interfaces.IDB;
import models.Product;
import repositories.interfaces.IProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProductRepository implements IProductRepository {
    private final IDB db;

    public ProductRepository(IDB db) {
        this.db = db;
    }

    @Override
    public void addProduct(Product product) {
        try (Connection con = db.getConnection()) {
            String sql = "INSERT INTO products (name, price, count) VALUES (?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, product.getName());
            st.setDouble(2, product.getPrice());
            st.setInt(3, product.getCount());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProductById(int id) {
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM products WHERE product_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("count")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM products";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("count")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void updateProduct(Product product) {
        try (Connection con = db.getConnection()) {
            String sql = "UPDATE products SET name = ?, price = ?, count = ? WHERE product_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, product.getName());
            st.setDouble(2, product.getPrice());
            st.setInt(3, product.getCount());
            st.setInt(4, product.getId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decreaseProductCount(int productId, int quantity) {
        try (Connection con = db.getConnection()) {
            String sql = "UPDATE products SET count = count - ? WHERE product_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, quantity);
            st.setInt(2, productId);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
