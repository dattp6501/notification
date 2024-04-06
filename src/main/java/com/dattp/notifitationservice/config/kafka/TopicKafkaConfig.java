package com.dattp.notifitationservice.config.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicKafkaConfig {
  @Value("${kafka.partition}")
  public int partition;

  public static final String NEW_USER_TOPIC = "com.dattp.restaurant.auth.new_user";

  public static final String NEW_DISH_TOPIC = "com.dattp.restaurant.product.new_dish";
  public static final String UPDATE_DISH_TOPIC = "com.dattp.restaurant.product.update_dish";

  public static final String NEW_TABLE_TOPIC = "com.dattp.restaurant.product.new_table";
  public static final String UPDATE_TABLE_TOPIC = "com.dattp.restaurant.product.update_table";
}