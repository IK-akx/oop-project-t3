package services;

import models.Notification;
import repositories.interfaces.INotificationRepository;

import java.util.List;

public class NotificationService {
    private final INotificationRepository notificationRepository;

    public NotificationService(INotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void sendNotification(String email, String message) {
        Notification notification = new Notification(email, message);
        notificationRepository.addNotification(notification);
        System.out.println("Notification sent to " + email + ": " + message);
    }

    public List<Notification> getNotificationsByEmail(String email) {
        return notificationRepository.getNotificationsByEmail(email);
    }
}
