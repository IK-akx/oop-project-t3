package services;

import models.SupportMessage;
import models.User;
import repositories.interfaces.ISupportRepository;
import services.UserService;

import java.util.List;

public class SupportService {
    private final ISupportRepository supportMessageRepository;
    private final UserService userService;

    public SupportService(ISupportRepository supportMessageRepository, UserService userService) {
        this.supportMessageRepository = supportMessageRepository;
        this.userService = userService;
    }

    private int getUserIdByEmail(String email) {
        User user = userService.getUserByEmail(email);
        return user != null ? user.getId() : 0;
    }

    public void sendSupportMessage(String email, String message) {
        int userId = getUserIdByEmail(email);
        SupportMessage supportMessage = new SupportMessage(0, userId, message, email);

        supportMessageRepository.addSupportMessage(supportMessage);
    }


    public List<SupportMessage> getAllSupportMessages() {
        return supportMessageRepository.getAllSupportMessages();
    }

    public List<SupportMessage> getSupportMessagesByUserId(int userId) {
        return supportMessageRepository.getSupportMessagesByUserId(userId);
    }
}
