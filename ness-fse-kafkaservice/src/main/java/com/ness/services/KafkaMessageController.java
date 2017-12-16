package com.ness.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ness.services.model.AuditBO;
import com.ness.services.service.KafkaProducer;

@RestController
@RequestMapping(value="/kafka")
public class KafkaMessageController {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageController.class);
	
	@Autowired
	private KafkaProducer producer;
	
	@RequestMapping(value="/audit", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)	
	public void produce(@RequestBody AuditBO auditBO){
		LOGGER.info("KafkaMessageController.produce() Entry"); 
		producer.send(auditBO);	
		LOGGER.info("KafkaMessageController.produce() Exit");		
	}
}
