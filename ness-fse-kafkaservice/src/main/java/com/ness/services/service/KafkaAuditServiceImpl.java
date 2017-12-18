package com.ness.services.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ness.services.model.Audit;
import com.ness.services.model.AuditBO;
import com.ness.services.repository.AuditRepository;

/**
 * This service class saves 
 * @author Rohit Saxena
 */
@Service(value="KafkaAuditService")
public class KafkaAuditServiceImpl implements KafkaAuditService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaAuditServiceImpl.class);
    private AuditRepository repository;
    
    @Autowired
    KafkaAuditServiceImpl(AuditRepository repository) {
        this.repository = repository;
    }

	/* (non-Javadoc)
	 * @see com.ness.services.service.KafkaMessageService#create(com.ness.services.model.AuditBO)
	 */
	@Override
	public void insert(AuditBO auditBO) throws Exception {
		LOGGER.info("KafkaMessageImpl.create(): Entry");
		Audit audit = null;
		if(auditBO != null){
			LOGGER.info("KafkaMessageImpl.create(): Creating a New Entry In Audit Table For Input Object: " + auditBO.toString());
			audit = constructAudit(auditBO);
			repository.save(audit);
			LOGGER.info("KafkaMessageImpl.create(): New Entry Created In Audit Table.");
		}else{
			LOGGER.info("KafkaMessageImpl.create(): New Entry Not Created since AuditBO Input is NULL");
		}
		LOGGER.info("KafkaMessageImpl.create(): Exit");
	}     
	
	private Audit constructAudit(AuditBO auditBO){
		Audit audit = new Audit();
		audit.setEventId(auditBO.getEventId());
		audit.setEventName(auditBO.getEventName());
		audit.setLoginName(auditBO.getLoginName());
		audit.setNotes(auditBO.getNotes());
		//audit.setUpdatedDate(auditBO.getUpdateDate() != null ?auditBO.getUpdateDate(): new Date());
		audit.setUpdatedDate(new Date());
		return audit;
	}
}
