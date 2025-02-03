package services;

import models.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private List<Notification> notifications;

    public NotificationService() {
        this.notifications = new ArrayList<>();
    }

    public void sendNotification(String email, String message) {
        Notification notification = new Notification(email, message);
        notifications.add(notification);
        System.out.println("Notification sent to " + email + ": " + message);
    }

    public List<Notification> getNotificationsByEmail(String email) {
        List<Notification> userNotifications = new ArrayList<>();
        for (Notification notification : notifications) {
            if (notification.getEmail().equals(email)) {
                userNotifications.add(notification);
            }
        }
        return userNotifications;
    }
}