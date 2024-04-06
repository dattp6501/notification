package com.dattp.notifitationservice.kafkalistener;

import com.dattp.notifitationservice.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ListenerKafka {
  @Autowired @Lazy protected TelegramService telegramService;
}
