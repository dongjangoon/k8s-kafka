package donghyun.notificationservice.domain.service;

import donghyun.notificationservice.domain.model.Notification;
import donghyun.notificationservice.domain.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(NotificationRepository notificationRepository, SimpMessagingTemplate messagingTemplate) {
        this.notificationRepository = notificationRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "word-combinations", groupId = "notification-service-group")
    public void processWordCombination(String wordCombination) {
        String notificationContent = "New word combination: " + wordCombination;
        Notification notification = createNotification(notificationContent);
        messagingTemplate.convertAndSend("/topic/words", notification);
    }

    public Notification createNotification(String content) {
        Notification notification = new Notification(content);
        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public void deleteNotification(String id) {
        notificationRepository.deleteById(id);
    }
}
