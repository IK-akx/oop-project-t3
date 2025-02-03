package models;

public class Notification {
    private String email;
    private String message;

    public Notification(String email, String message) {
        setEmail(email);
        setMessage(message);
    }

    public void setEmail(String email) {this.email = email;}
    public void setMessage(String message) {this.message = message;}


    public String getEmail() {return email;}
    public String getMessage() {return message;}
}

