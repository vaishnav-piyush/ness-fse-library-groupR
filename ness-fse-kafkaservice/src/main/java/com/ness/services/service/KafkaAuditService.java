package com.ness.services.service;

import com.ness.services.model.AuditBO;

/**
 * This interface declares the methods that provides CRUD operations for
 * {@link com.javaadvent.bootrest.book.book} objects.
 * @author Rohit Saxena
 */
public interface KafkaAuditService {

    /**
     * Creates a new Audit entry.
     * @param book  The information of the created book entry.
     * @return      The information of the created book entry.
     */
    public void insert(AuditBO auditBO) throws Exception;    
}
