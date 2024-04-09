package com.dattp.notifitationservice.config.kafka;

import com.dattp.notifitationservice.dto.kafka.booking.BookingResponseDTO;
import com.dattp.notifitationservice.dto.kafka.dish.DishResponseDTO;
import com.dattp.notifitationservice.dto.kafka.table.TableResponseDTO;
import com.dattp.notifitationservice.dto.kafka.user.UserResponseDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class ComsumerKafkaConfig {
    @Value("${kafka.test}")
    private boolean KAFKA_TEST;

    @Value("${spring.kafka.bootstrap-servers}")
    private String BOOTSTRAP_SERVERS;

    @Value("${spring.kafka.properties.sasl.jaas.config}")
    private String SASL_JAAS_CONFIG;
    @Value("${spring.kafka.properties.sasl.mechanism}")
    private String SASL_MECHANISM;
    @Value("${spring.kafka.properties.security.protocol}")
    private String SECURITY_PROTOCOL;
    // 
    public Map<String, Object> comsumerConfigJSON(){
        Map<String,Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,  "com.dattp.notification");
        if(KAFKA_TEST){
            props.put("sasl.jaas.config", SASL_JAAS_CONFIG);
            props.put("sasl.mechanism", SASL_MECHANISM);
            props.put("security.protocol", SECURITY_PROTOCOL);
        }
        return props;
    }
    //dish
    @Bean
    public ConsumerFactory<String, DishResponseDTO> consumerFactoryDish(){
        JsonDeserializer<DishResponseDTO> jsonDeserializernew = new JsonDeserializer<>(DishResponseDTO.class, false);
        jsonDeserializernew.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(comsumerConfigJSON(),new StringDeserializer(), jsonDeserializernew);
    }
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,DishResponseDTO>> factoryDish(ConsumerFactory<String,DishResponseDTO> consumerFactoryDish){
        ConcurrentKafkaListenerContainerFactory<String,DishResponseDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryDish);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }
    //user
    @Bean
    public ConsumerFactory<String, UserResponseDTO> consumerFactoryUser(){
        JsonDeserializer<UserResponseDTO> jsonDeserializernew = new JsonDeserializer<>(UserResponseDTO.class, false);
        jsonDeserializernew.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(comsumerConfigJSON(),new StringDeserializer(), jsonDeserializernew);
    }
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,UserResponseDTO>> factoryUser(ConsumerFactory<String,UserResponseDTO> consumerFactoryUser){
        ConcurrentKafkaListenerContainerFactory<String,UserResponseDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryUser);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }
    //table
    @Bean
    public ConsumerFactory<String, TableResponseDTO> consumerFactoryTable(){
        JsonDeserializer<TableResponseDTO> jsonDeserializernew = new JsonDeserializer<>(TableResponseDTO.class, false);
        jsonDeserializernew.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(comsumerConfigJSON(),new StringDeserializer(), jsonDeserializernew);
    }
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,TableResponseDTO>> factoryTable(ConsumerFactory<String,TableResponseDTO> consumerFactoryTable){
        ConcurrentKafkaListenerContainerFactory<String,TableResponseDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryTable);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }


//    // booking
    @Bean
    public ConsumerFactory<String, BookingResponseDTO> consumerFactoryBooking(){
        JsonDeserializer<BookingResponseDTO> jsonDeserializernew = new JsonDeserializer<>(BookingResponseDTO.class, false);
        jsonDeserializernew.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(comsumerConfigJSON(),new StringDeserializer(), jsonDeserializernew);
    }
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,BookingResponseDTO>> factoryBooking(ConsumerFactory<String,BookingResponseDTO> consumerFactoryBooking){
        ConcurrentKafkaListenerContainerFactory<String,BookingResponseDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryBooking);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }
}
