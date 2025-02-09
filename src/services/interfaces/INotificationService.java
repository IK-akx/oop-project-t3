package services.interfaces;

import models.Notification;

import java.util.List;

public interface INotificationService {
    void sendNotification(String email, String message);
    List<Notification> getNotificationsByEmail(String email);
}
