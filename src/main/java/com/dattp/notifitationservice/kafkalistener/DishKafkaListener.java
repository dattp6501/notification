package com.dattp.notifitationservice.kafkalistener;

import com.dattp.notifitationservice.config.kafka.TopicKafkaConfig;
import com.dattp.notifitationservice.dto.kafka.dish.DishResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DishKafkaListener extends ListenerKafka {
  @KafkaListener(topics = TopicKafkaConfig.NEW_DISH_TOPIC, groupId = "com.dattp.restaurant.notification.dish_new", containerFactory = "factoryDish")
  public void listenerDishNew(DishResponseDTO dto, Acknowledgment acknowledgment) {
    try {
      telegramService.sendNotificationService(telegramService.genMessageTemplateDish(dto, "Thêm món"));
    } catch (Exception e) {
      log.debug("=====================>  listenerDishNew:Exception:{}", e.getMessage());
    }
    acknowledgment.acknowledge();
  }

  @KafkaListener(topics = TopicKafkaConfig.UPDATE_DISH_TOPIC, groupId = "com.dattp.restaurant.notification.dish_update", containerFactory = "factoryDish")
  public void listenerDishUpdate(DishResponseDTO dto, Acknowledgment acknowledgment) {
    try {
      telegramService.sendNotificationService(telegramService.genMessageTemplateDish(dto, "Cập nhật thông tin món"));
    } catch (Exception e) {
      log.debug("=====================>  listenerDishUpdate:Exception:{}", e.getMessage());
    }
    acknowledgment.acknowledge();
  }
}
