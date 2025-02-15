package repositories;

import data.PostgresDB;
import data.interfaces.IDB;
import models.SupportMessage;
import repositories.interfaces.ISupportRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupportRepository implements ISupportRepository {
    private final Connection con;

    public SupportRepository(IDB db) {
        this.con = PostgresDB.getInstance("jdbc:postgresql://localhost:5432", "postgres", "0000", "postgres").getConnection();
    }

    @Override
    public void addSupportMessage(SupportMessage supportMessage) {
        try {
            String sql = "INSERT INTO supportmessages (user_id, message, email) VALUES (?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, supportMessage.getUserId());
            st.setString(2, supportMessage.getMessage());
            st.setString(3, supportMessage.getEmail());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SupportMessage> getAllSupportMessages() {
        List<SupportMessage> supportMessages = new ArrayList<>();
        try  {
            String sql = "SELECT * FROM supportmessages";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                supportMessages.add(new SupportMessage(
                        rs.getInt("supportmessage_id"),
                        rs.getInt("user_id"),
                        rs.getString("message"),
                        rs.getString("email")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supportMessages;
    }

    @Override
    public List<SupportMessage> getSupportMessagesByUserId(int userId) {
        List<SupportMessage> supportMessages = new ArrayList<>();
        try  {
            String sql = "SELECT * FROM supportmessages WHERE user_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                supportMessages.add(new SupportMessage(
                        rs.getInt("supportmessage_id"),
                        rs.getInt("user_id"),
                        rs.getString("message"),
                        rs.getString("email")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supportMessages;
    }
}
