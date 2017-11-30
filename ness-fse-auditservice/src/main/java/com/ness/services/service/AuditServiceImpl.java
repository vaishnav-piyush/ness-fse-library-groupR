package com.ness.services.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ness.services.model.AUDIT;
import com.ness.services.model.AuditBO;
import com.ness.services.repository.AuditRepository;

/**
 * This service class saves {@link com.ness.fse.Book.Book} objects
 * to MongoDB database.
 * @author Petri Kainulainen
 */
@Service(value="AuditService")
public class AuditServiceImpl implements AuditService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditServiceImpl.class);

    private AuditRepository repository;
    
    @Autowired
    AuditServiceImpl(AuditRepository repository) {
        this.repository = repository;
    }

    

    private List<AuditBO> convertToDTOs(List<AUDIT> models) {
        return models.stream().map(this::convertToBO).collect(toList());
    }

    @Override
    public List<AuditBO> findById(String id) throws Exception {
        LOGGER.info("Finding Book entry with id: {}", id);
        List<AUDIT> found = findBookById(id);
        LOGGER.info("Found Book entry: {}", found);
        return convertToDTOs(found);
    }
    
    @Override
    public List<AuditBO> findAll() throws Exception {
        List<AuditBO> found;
        List<AUDIT> result = (List<AUDIT>) this.repository.findAll();
        found = convertToDTOs(result);
        LOGGER.info("Found Book entry: {}", found);
        return found;
    }
    
    

    private List<AUDIT> findBookById(String id) throws Exception{
    	List<AUDIT> result = null; 
    	result = (List<AUDIT>) this.repository.findByEventId(id);    
    	if(result != null && result.size() > 0){
    		LOGGER.info("findBookById: Books Returned Count: " + result.size());
    	}else {    		
    		LOGGER.info("findBookById: Unable To Search Book With Given Book Id: " + id);
        	//throw new BookNotFoundException("40001","Unable To Search Book With Given Book Id: " + id);
        }
    	//List<AUDIT> result = null;
    	return result;
    }

    private AuditBO convertToBO(AUDIT audit) {
    	AuditBO auditBO = new AuditBO();

        auditBO.setId(audit.getId());
        auditBO.setUpdate_date(audit.getUpdatedDate());
        auditBO.setEvent_id(audit.getEventId());
        auditBO.setEvent_name(audit.getEventName());
        auditBO.setLogin_name(audit.getLoginName());
        auditBO.setNotes(audit.getNotes());
        
        return auditBO;
    }
    
    @SuppressWarnings("unused")
	private AUDIT convertFromBO(AuditBO auditBO) {
    	AUDIT audit = new AUDIT();
    	audit.setId(auditBO.getId());
    	audit.setEventId(auditBO.getEvent_id());
    	audit.setEventName(auditBO.getEvent_name());
    	audit.setLoginName(auditBO.getLogin_name());
    	audit.setNotes(auditBO.getNotes());
    	audit.setUpdatedDate(auditBO.getUpdate_date());
      
        return audit;
    }
}
