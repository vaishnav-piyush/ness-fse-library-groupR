package com.ness.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ness.services.model.BookBO;
import com.ness.services.service.LibraryService;

@RestController
@RequestMapping("/book")
public class LibraryController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryController.class);
    
    @Autowired
    private LibraryService service;
    
    /*@RequestMapping(value = "/issue",method = RequestMethod.POST)*/
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('book_write')")
    public BookBO create(@RequestBody @Valid BookBO bookBO) {
        LOGGER.info("Creating a new Book entry with information: {}", bookBO);
        BookBO created = service.create(bookBO);
        LOGGER.info("Created a new Book entry with information: {}", created);
        return created;
    }

    @PreAuthorize("hasRole('book_write')")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public BookBO delete(@PathVariable("id") String id) throws Exception {
        LOGGER.info("Deleting Book entry with id: {}", id);
        BookBO deleted = service.delete(id);
        LOGGER.info("Deleted Book entry with information: {}", deleted);
        return deleted;
    }

    @PreAuthorize("hasRole('book_read')")
    @RequestMapping(method = RequestMethod.GET)
    public List<BookBO> findAll() {
        LOGGER.info("Finding all Book entries");
        List<BookBO> bookBOs = service.findAll();
        LOGGER.info("Found {} Book entries", bookBOs.size());
        return bookBOs;
    }

    @PreAuthorize("hasRole('book_read')")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public BookBO findById(@PathVariable("id") String id) throws Exception {
        LOGGER.info("Finding Book entry with id: {}", id);
        BookBO bookBO = service.findById(id);
        LOGGER.info("Found Book entry with information: {}", bookBO);
        return bookBO;
    }

    @PreAuthorize("hasRole('book_reserve')")
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public BookBO update(@RequestBody @Valid BookBO bookBO) throws Exception {
        LOGGER.info("Updating todo entry with information: {}", bookBO);
        BookBO updated = service.update(bookBO);
        LOGGER.info("Updated todo entry with information: {}", updated);
        return updated;
    }

    /*@ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleTodoNotFound(TodoNotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
    } */  	
}
