package services;

import models.SupportMessage;

import java.util.ArrayList;
import java.util.List;

public class SupportService {
    private List<SupportMessage> supportMessages;

    public SupportService() {
        this.supportMessages = new ArrayList<>();
    }

    public void sendSupportMessage(String email, String message) {
        SupportMessage supportMessage = new SupportMessage(email, message);
        supportMessages.add(supportMessage);
        System.out.println("Support message sent: " + message);
    }

    public List<SupportMessage> getSupportMessages() {
        return supportMessages;
    }
}