package com.dattp.notifitationservice.config;

import com.dattp.notifitationservice.service.TelegramService;
import com.dattp.notifitationservice.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.format.DateTimeFormatter;

@Configuration
@EnableScheduling
public class Monitor {
  @Autowired
  private TelegramService telegramService;

  @Scheduled(initialDelay = 2000, fixedDelay = 1200000)
  public void isRunning() {
    String message =
        DateUtils.getcurrentLocalDateTime()
            .plusHours(7)
            .format(DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd"))
            + ": NOTIFICATION ===> RUNNING";
    telegramService.sendNotificationMonitorSystem(message);
  }
}
