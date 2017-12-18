package com.ness.services.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.ness.services.model.AuditBO;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
 
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;
	
	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;
	
    @Bean
    public ConsumerFactory<String, AuditBO> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props,new StringDeserializer(),new JsonDeserializer<>(AuditBO.class));
    }
 
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AuditBO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AuditBO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}