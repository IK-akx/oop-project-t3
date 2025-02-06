package repositories.interfaces;

import models.Notification;

import java.util.List;

public interface INotificationRepository {
    void addNotification(Notification notification);
    List<Notification> getNotificationsByEmail(String email);
}
