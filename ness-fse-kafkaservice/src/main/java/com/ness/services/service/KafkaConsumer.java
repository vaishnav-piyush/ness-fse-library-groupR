package com.ness.services.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ness.services.model.AuditBO;

@Service
public class KafkaConsumer {
	private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

	@Autowired
	private KafkaAuditService auditService;
	
	@KafkaListener(topics="${spring.kafka.template.default-topic}")
    public void processMessage(AuditBO auditBO) {
		log.info("KafkaConsumer.processMessage(): Entry");
		try {
			log.info("KafkaConsumer.processMessage(): received content AuditBO: '{}'", auditBO.toString());
			auditService.insert(auditBO);
		} catch (Exception e) {
			log.error("KafkaConsumer.processMessage(): Exception Occurred During Audit Entry In DB: " + e.getMessage());
		}
		log.info("KafkaConsumer.processMessage(): Exit");
    }
}
