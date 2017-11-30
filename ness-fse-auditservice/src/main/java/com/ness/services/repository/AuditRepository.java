package com.ness.services.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ness.services.model.AUDIT;
import java.lang.String;


/**
 * This repository provides CRUD operations for {@link com.javaadvent.bootrest.Book.Book}
 * objects.
 * @author 
 */
public interface AuditRepository extends CrudRepository<AUDIT, Integer> {
    
	List<AUDIT> findAll();
	List<AUDIT> findByEventId(String eventid);
}
