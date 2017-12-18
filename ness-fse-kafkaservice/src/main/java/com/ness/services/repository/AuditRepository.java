package com.ness.services.repository;

import org.springframework.data.repository.CrudRepository;

import com.ness.services.model.Audit;


/**
 * This repository provides CRUD operations for {@link com.javaadvent.bootrest.Book.Book}
 * objects.
 * @author 
 */
public interface AuditRepository extends CrudRepository<Audit, Integer> {    
	
}
