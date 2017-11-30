package com.ness.services.service;

import java.util.List;

import com.ness.services.model.AuditBO;


/**
 * This interface declares the methods that provides CRUD operations for
 * {@link com.javaadvent.bootrest.book.book} objects.
 * @author 
 */
public interface AuditService {
    /**
     * Finds a single book entry.
     * @param id    The id of the requested book entry.
     * @return      The information of the requested book entry.
     * @throws Exception 
     * @throws com.javaadvent.bootrest.book.bookNotFoundException if no book entry is found.
     */
    List<AuditBO> findById(String id) throws Exception;
    
    List<AuditBO> findAll() throws Exception;

}
