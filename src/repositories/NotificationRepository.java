package repositories;

import data.PostgresDB;
import data.interfaces.IDB;
import models.Notification;
import repositories.interfaces.INotificationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NotificationRepository implements INotificationRepository {
    private final Connection con;;

    public NotificationRepository(IDB db) {
        this.con = PostgresDB.getInstance("jdbc:postgresql://localhost:5432", "postgres", "0000", "postgres").getConnection();
    }

    @Override
    public void addNotification(Notification notification) {
        try {
            String sql = "INSERT INTO notifications (email, message) VALUES (?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, notification.getEmail());
            st.setString(2, notification.getMessage());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Notification> getNotificationsByEmail(String email) {
        List<Notification> notifications = new ArrayList<>();
        try {
            String sql = "SELECT * FROM notifications WHERE email = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                notifications.add(new Notification(rs.getString("email"), rs.getString("message")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notifications;
    }
}
//1