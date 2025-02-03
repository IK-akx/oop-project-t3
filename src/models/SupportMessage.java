package models;

public class SupportMessage {
    private String email;
    private String message;

    public SupportMessage(String email, String message) {
        this.email = email;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }
}