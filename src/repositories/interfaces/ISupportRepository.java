package repositories.interfaces;

import models.SupportMessage;
import java.util.List;

public interface ISupportRepository {
    void addSupportMessage(SupportMessage supportMessage);
    List<SupportMessage> getAllSupportMessages();
    List<SupportMessage> getSupportMessagesByUserId(int userId);
}

//1