package com.ness.services.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ness.services.model.AuditBO;

@Service
public class KafkaProducer {
	private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
	
	@Autowired
	private KafkaTemplate<String, AuditBO> kafkaTemplate;
	
	@Value("${spring.kafka.template.default-topic}")
	String kafkaTopic;
	
	public void send(AuditBO message) {
		log.info("kafkaTopic Read From yml file: " + kafkaTopic);
	    kafkaTemplate.send(kafkaTopic, message);	    
	}
}
