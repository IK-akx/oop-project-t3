package repositories;

import data.PostgresDB;
import models.User;
import factories.UserFactory;
import repositories.interfaces.IUserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final Connection con;

    public UserRepository() {
        this.con = PostgresDB.getInstance("jdbc:postgresql://localhost:5432", "postgres", "0000", "postgres").getConnection();
    }

    @Override
    public void addUser(User user) {
        try {
            String sql = "INSERT INTO users (name, email, password, is_admin) VALUES (?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());
            st.setBoolean(4, user.isAdmin());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try (Connection con = PostgresDB.getInstance("jdbc:postgresql://localhost:5432", "postgres", "password", "mydb").getConnection()) {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                boolean isAdmin = rs.getBoolean("is_admin");

                String role = isAdmin ? "admin" : "customer";
                return UserFactory.createUser(role, id, name, email, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection con = PostgresDB.getInstance("jdbc:postgresql://localhost:5432", "postgres", "password", "mydb").getConnection()) {
            String sql = "SELECT * FROM users";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                boolean isAdmin = rs.getBoolean("is_admin");

                String role = isAdmin ? "admin" : "customer";
                users.add(UserFactory.createUser(role, id, name, email, password));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void updateUser(User user) {
        try {
            String sql = "UPDATE users SET name = ?, email = ?, password = ?, is_admin = ? WHERE user_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());
            st.setBoolean(4, user.isAdmin());
            st.setInt(5, user.getId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
