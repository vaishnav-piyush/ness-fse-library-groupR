package com.ness.services.service;

import static java.util.stream.Collectors.toList;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ness.services.error.BookNotFoundException;
import com.ness.services.model.Book;
import com.ness.services.model.BookBO;
import com.ness.services.repository.BookRepository;

/**
 * This service class saves {@link com.ness.fse.Book.Book} objects
 * to MongoDB database.
 * @author Petri Kainulainen
 */
@Service(value="LibraryService")
public class LibraryServiceImpl implements LibraryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryServiceImpl.class);

    private BookRepository repository;
    
    @Autowired
    LibraryServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public BookBO create(BookBO bookBO) {
        LOGGER.info("Creating a new Book entry with information: {}", bookBO);
        Book persisted = convertFromBO(bookBO);
        persisted = repository.save(persisted);
        LOGGER.info("Created a new Book entry with information: {}", persisted);
        return convertToBO(persisted);
    }

    @Override
    public BookBO delete(String id) throws Exception {
        LOGGER.info("Deleting a Book entry with id: {}", id);
        Book deleted = findBookById(id);
        repository.delete(deleted);
        LOGGER.info("Deleted Book entry with informtation: {}", deleted);
        return convertToBO(deleted);
    }

    @Override
    public List<BookBO> findAll() {
        LOGGER.info("Finding all Book entries.");
        List<Book> todoEntries = repository.findAll();
        LOGGER.info("Found {} Book entries", todoEntries.size());
        return convertToDTOs(todoEntries);
    }

    private List<BookBO> convertToDTOs(List<Book> models) {
        return models.stream().map(this::convertToBO).collect(toList());
    }

    @Override
    public BookBO findById(String id) throws Exception {
        LOGGER.info("Finding Book entry with id: {}", id);
        Book found = findBookById(id);
        LOGGER.info("Found Book entry: {}", found);
        return convertToBO(found);
    }

    @Override
    public BookBO update(BookBO bookBO) throws Exception{
        LOGGER.info("Updating Book entry with information: {}", bookBO);
        Book updated = findBookById(bookBO.getId());
		updated.update(bookBO.getIssueStatus(),
				bookBO.getIssuedTo(),
				new Date());
        updated = repository.save(updated);
        LOGGER.info("Updated Book entry with information: {}", updated);
        return convertToBO(updated);
    }

    private Book findBookById(String id) throws Exception{
        Book result = repository.findOne(id);        
        if (result == null){
        	LOGGER.info("findBookById: Unable To Search Book With Given Book Id: " + id);
        	throw new BookNotFoundException("40001","Unable To Search Book With Given Book Id: " + id);
        }
    	return result;
    }

    private BookBO convertToBO(Book book) {
        BookBO bookBO = new BookBO();

        bookBO.setAddedDate(book.getAddedDate());
        bookBO.setAuthor(book.getAuthor());
        bookBO.setDescription(book.getDescription());
        bookBO.setId(book.getId());
        bookBO.setIssuedTo(book.getIssuedTo());
        bookBO.setIssueStatus(book.getIssueStatus());
        bookBO.setRemovedDate(book.getRemovedDate());
        bookBO.setTitle(book.getTitle());
        bookBO.setUpdatedDate(book.getUpdatedDate());
        
        return bookBO;
    }
    
    private Book convertFromBO(BookBO bookBO) {
        Book book = new Book();
        
        book.setAddedDate(new Date());
        book.setAuthor(bookBO.getAuthor());
        book.setDescription(bookBO.getDescription());
        book.setIssuedTo(bookBO.getIssuedTo());
        book.setIssueStatus(bookBO.getIssueStatus());
        book.setTitle(bookBO.getTitle());
        book.setUpdatedDate(new Date());
        return book;
    }
}
