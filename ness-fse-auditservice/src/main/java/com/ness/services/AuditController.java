package com.ness.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ness.services.model.AuditBO;
import com.ness.services.service.AuditService;

@RestController
@RequestMapping("/audit")
public class AuditController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditController.class);
    
    @Autowired
    private AuditService service;
    
    @PreAuthorize("hasRole('audit_trail')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<AuditBO> findById(@PathVariable("id") String id) throws Exception {
        LOGGER.info("Finding audit entry with book id: {}", id);
        List<AuditBO> auditBOlist = service.findById(id);
        LOGGER.info("Found audit entry with information: {}", auditBOlist);
        return auditBOlist;
    }
    
    @PreAuthorize("hasRole('audit_trail')")
    @RequestMapping(method = RequestMethod.GET)
    public List<AuditBO> findAll() throws Exception {
        List<AuditBO> auditBOlist = service.findAll();
        LOGGER.info("Found audit entry with information: {}", auditBOlist);
        return auditBOlist;
    }

    /*@ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleTodoNotFound(TodoNotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
    } */  	
}
