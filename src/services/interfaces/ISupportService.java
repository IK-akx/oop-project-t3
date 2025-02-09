package services.interfaces;

import models.SupportMessage;

import java.util.List;

public interface ISupportService {
    void sendSupportMessage(String email, String message);
    List<SupportMessage> getAllSupportMessages();
    List<SupportMessage> getSupportMessagesByUserId(int userId);
}
