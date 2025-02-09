package repositories;

import data.PostgresDB;
import models.ProductCategory;
import repositories.interfaces.ICategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements ICategoryRepository {
    private final Connection con;

    public CategoryRepository() {
        this.con = PostgresDB.getInstance("jdbc:postgresql://localhost:5432", "postgres", "0000", "postgres").getConnection();
    }

    @Override
    public void addCategory(ProductCategory category) {
        try {
            String sql = "INSERT INTO categories (name) VALUES (?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, category.getName());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductCategory getCategoryById(int id) {
        try {
            String sql = "SELECT * FROM categories WHERE category_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new ProductCategory(rs.getInt("category_id"), rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductCategory> getAllCategories() {
        List<ProductCategory> categories = new ArrayList<>();
        try {
            String sql = "SELECT * FROM categories";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                categories.add(new ProductCategory(rs.getInt("category_id"), rs.getString("name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public void updateCategory(ProductCategory category) {
        try {
            String sql = "UPDATE categories SET name = ? WHERE category_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, category.getName());
            st.setInt(2, category.getId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
