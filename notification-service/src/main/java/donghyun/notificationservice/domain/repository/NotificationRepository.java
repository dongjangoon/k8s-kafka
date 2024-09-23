package donghyun.notificationservice.domain.repository;

import donghyun.notificationservice.domain.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
