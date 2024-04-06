package com.dattp.notifitationservice.kafkalistener;

import com.dattp.notifitationservice.config.kafka.TopicKafkaConfig;
import com.dattp.notifitationservice.dto.kafka.table.TableResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class TableKafkaListener extends ListenerKafka{
  @KafkaListener(topics = TopicKafkaConfig.NEW_TABLE_TOPIC, groupId = "com.dattp.restaurant.notification.table_new", containerFactory = "factoryTable")
  public void listenNewTable(TableResponseDTO dto, Acknowledgment acknowledgment){
    try {
      telegramService.sendNotificationService(telegramService.genMessageTemplateTable(dto, "Thêm bàn mới"));
    }catch (Exception e){
      log.debug("=====================>  listenNewTable:Exception:{}", e.getMessage());
    }
    acknowledgment.acknowledge();
  }

  @KafkaListener(topics = TopicKafkaConfig.UPDATE_TABLE_TOPIC, groupId = "com.dattp.restaurant.notification.table_update", containerFactory = "factoryTable")
  public void listenUpdateTable(TableResponseDTO dto, Acknowledgment acknowledgment){
    try {
      telegramService.sendNotificationService(telegramService.genMessageTemplateTable(dto, "Cập nhật thông tin bàn"));
    }catch (Exception e){
      log.debug("=====================>  listenUpdateTable:Exception:{}", e.getMessage());
    }
    acknowledgment.acknowledge();
  }
}
