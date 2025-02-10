package repositories;

import data.PostgresDB;
import models.Product;
import models.ProductCategory;
import repositories.interfaces.IProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private final Connection con;

    public ProductRepository() {
        this.con = PostgresDB.getInstance("jdbc:postgresql://localhost:5432", "postgres", "password", "mydb").getConnection();
    }

    @Override
    public void addProduct(Product product) {
        try {
            String sql = "INSERT INTO products (name, price, count, category_id) VALUES (?, ?, ?, ?) RETURNING product_id";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, product.getName());
            st.setDouble(2, product.getPrice());
            st.setInt(3, product.getCount());
            st.setInt(4, product.getCategory().getId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                product.setId(rs.getInt("product_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Product getProductById(int id) {
        try {
            String sql = "SELECT p.*, c.category_id AS category_id, c.name AS category_name FROM products p " +
                    "JOIN categories c ON p.category_id = c.category_id WHERE p.category_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int productId = rs.getInt("product_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int count = rs.getInt("count");
                ProductCategory category = new ProductCategory(rs.getInt("category_id"),
                        rs.getString("category_name"));

                return new Product(productId, name, price, count, category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT p.*, c.category_id AS category_id, c.name AS category_name FROM products p " +
                    "JOIN categories c ON p.category_id = c.category_id";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int count = rs.getInt("count");
                ProductCategory category = new ProductCategory(rs.getInt("category_id"),
                        rs.getString("category_name"));

                products.add(new Product(id, name, price, count, category));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void updateProduct(Product product) {
        try {
            String sql = "UPDATE products SET name = ?, price = ?, count = ?, category_id = ? WHERE product_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, product.getName());
            st.setDouble(2, product.getPrice());
            st.setInt(3, product.getCount());
            st.setInt(4, product.getCategory().getId());
            st.setInt(5, product.getId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void decreaseProductCount(int productId, int quantity) {
        try {
            String sql = "UPDATE products SET count = CASE WHEN count >= ? THEN count - ? ELSE count END WHERE product_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, quantity);
            st.setInt(2, quantity);
            st.setInt(3, productId);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT p.*, c.category_id AS category_id, c.name AS category_name FROM products p " +
                    "JOIN categories c ON p.category_id = c.category_id WHERE p.category_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, categoryId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int count = rs.getInt("count");
                ProductCategory category = new ProductCategory(rs.getInt("category_id"),
                        rs.getString("category_name"));

                products.add(new Product(id, name, price, count, category));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
//1