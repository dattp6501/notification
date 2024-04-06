package com.dattp.notifitationservice.kafkalistener;

import com.dattp.notifitationservice.config.kafka.TopicKafkaConfig;
import com.dattp.notifitationservice.dto.kafka.user.UserResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class UserKafkaListener extends ListenerKafka{
    @KafkaListener(topics = TopicKafkaConfig.NEW_USER_TOPIC, groupId = "com.dattp.restaurant.notification.auth.new_user", containerFactory = "factoryUser")
    public void listenNewUser(UserResponseDTO dto, Acknowledgment acknowledgment){
        try {
            telegramService.sendNotificationService(telegramService.genMessageTemplateUser(dto, "Tài khoản người dùng mới"));
        }catch (Exception e){
            log.debug("=====================>  listenNewUser:Exception:{}", e.getMessage());
        }
        acknowledgment.acknowledge();
    }
}