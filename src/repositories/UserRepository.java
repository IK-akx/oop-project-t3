package repositories;

import data.interfaces.IDB;
import models.*;
import repositories.interfaces.IUserRepository;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public void addUser(User user) {
        try (Connection con = db.getConnection()) {
            String sql = "INSERT INTO users (name, email, password, is_admin) VALUES (?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());
            st.setBoolean(4, user.isAdmin());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                User user;
                if (rs.getBoolean("is_admin")) {
                    user = new Admin(rs.getString("name"), rs.getString("email"), rs.getString("password"));
                } else {
                    user = new Customer(rs.getString("name"), rs.getString("email"), rs.getString("password"));
                }
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM users";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                User user;
                if (rs.getBoolean("is_admin")) {
                    user = new Admin(rs.getString("name"), rs.getString("email"), rs.getString("password"));
                } else {
                    user = new Customer(rs.getString("name"), rs.getString("email"), rs.getString("password"));
                }
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void updateUser(User user) {
        try (Connection con = db.getConnection()) {
            String sql = "UPDATE users SET name = ?, password = ? WHERE email = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user.getName());
            st.setString(2, user.getPassword());
            st.setString(3, user.getEmail());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
