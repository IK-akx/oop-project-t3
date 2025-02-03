package services;

public class NotificationService {
    public void sendNotification(String email, String message) {
        System.out.println("Sending notification to " + email + ": " + message);
    }
}