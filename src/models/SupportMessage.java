package models;

public class SupportMessage {
    private int supportMessageId;
    private int userId;
    private String message;
    private String email;

    public SupportMessage(int supportMessageId, int userId, String message, String email) {
        this.supportMessageId = supportMessageId;
        this.userId = userId;
        this.message = message;
        this.email = email;
    }

    public int getSupportMessageId() { return supportMessageId; }
    public void setSupportMessageId(int supportMessageId) { this.supportMessageId = supportMessageId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "SupportMessage ID: " + supportMessageId + ", User ID: " + userId + ", Email: " + email + ", Message: " + message;
    }
}
