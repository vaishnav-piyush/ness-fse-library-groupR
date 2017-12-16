package com.ness.services.service;

import static java.util.stream.Collectors.toList;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ness.services.error.BookNotFoundException;
import com.ness.services.model.Book;
import com.ness.services.model.BookBO;
import com.ness.services.model.BookEventBO;
import com.ness.services.repository.BookRepository;

/**
 * This service class saves {@link com.ness.fse.Book.Book} objects
 * to MongoDB database.
 * @author Rohit Saxena
 */
@Service(value="LibraryService")
public class LibraryServiceImpl implements LibraryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryServiceImpl.class);

    private BookRepository repository;
    
    @Autowired
    LibraryServiceImpl(BookRepository repository) {
        this.repository = repository;
    }
    
    @Autowired
    private EventService eventService;

    @Override
    public BookBO create(BookBO bookBO) {
        LOGGER.info("Creating a new Book entry with information: {}", bookBO);
        Book persisted = convertFromBO(bookBO);
        persisted = repository.save(persisted);
        LOGGER.info("Created a new Book entry with information: {}", persisted);
        BookBO result = convertToBO(persisted);
        raiseEvent(result,BookEventBO.EVENT_BOOK_ADDED);
        return result;
    }

    @Override
    public BookBO delete(String id) throws Exception {
        LOGGER.info("Deleting a Book entry with id: {}", id);
        Book deleted = findBookById(id);
        repository.delete(deleted);
        LOGGER.info("Deleted Book entry with informtation: {}", deleted);
        BookBO result = convertToBO(deleted);
        raiseEvent(result,BookEventBO.EVENT_BOOK_DELETED);
        return result;
    }

    @Override
    public List<BookBO> findAll() {
        LOGGER.info("Finding all Book entries.");
        List<Book> todoEntries = repository.findAll();
        LOGGER.info("Found {} Book entries", todoEntries.size());
        List<BookBO> result = convertToDTOs(todoEntries);
        raiseEvent(result,BookEventBO.EVENT_BOOK_SEARCHED);
        return result;
    }

    private List<BookBO> convertToDTOs(List<Book> models) {
        return models.stream().map(this::convertToBO).collect(toList());
    }

    @Override
    public BookBO findById(String id) throws Exception {
        LOGGER.info("Finding Book entry with id: {}", id);
        Book found = findBookById(id);
        LOGGER.info("Found Book entry: {}", found);
        BookBO result = convertToBO(found);
        raiseEvent(result,BookEventBO.EVENT_BOOK_SEARCHED);
        return result;
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
        BookBO result = convertToBO(updated);
        raiseEvent(result,BookEventBO.EVENT_BOOK_UPDATED);
        return result;
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
    
    @Async
    private void raiseEvent(BookBO bookBO, String eventType){
    	LOGGER.info("LibraryServiceImpl.raiseEvent(): Entry: About To Raise " + eventType + " For bookBO: " + bookBO.toString());
    	BookEventBO<BookBO> eventBO = new BookEventBO<BookBO>(bookBO);
    	try {
			eventService.raiseEvent(eventBO, eventType);
		} catch (Exception e) {
			LOGGER.info("LibraryServiceImpl.raiseEvent(): Exception While Raising Event " + eventType + " : Error Message: " + e.getMessage());
			e.printStackTrace();
		}
    	LOGGER.info("LibraryServiceImpl.raiseEvent(): Exit: Event Raised");
    }
    
    @Async
    private void raiseEvent(List<BookBO> bookBO, String eventType){
    	LOGGER.info("LibraryServiceImpl.raiseEvent(): Entry: About To Raise " + eventType + " For bookBO List: " + bookBO.toString());
    	for (BookBO bookBO2 : bookBO) {
    		raiseEvent(bookBO2, eventType);
		}    	
    	LOGGER.info("LibraryServiceImpl.raiseEvent(): Exit: Event Raised");
    }
}
