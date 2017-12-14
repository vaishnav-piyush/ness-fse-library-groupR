package com.ness.services.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ness.services.model.Book;

/**
 * This repository provides CRUD operations for {@link com.javaadvent.bootrest.Book.Book}
 * objects.
 * @author Rohit Saxena
 */
//public interface BookRepository extends Repository<> {
public interface BookRepository extends MongoRepository<Book, String> {

    /**
     * Deletes a Book entry from the database.
     * @param deleted   The deleted Book entry.
     */
    void delete(Book deleted);

    /**
     * Finds all Book entries from the database.
     * @return  The information of all Book entries that are found from the database.
     */
    List<Book> findAll();

    /**
     * Finds the information of a single Book entry.
     * @param id    The id of the requested Book entry.
     * @return      The information of the found Book entry. If no Book entry
     *              is found, this method returns an empty {@link java.util.Optional} object.
     */
    Optional<Book> findById(String id);

    /**
     * Saves a new Book entry to the database.
     * @param saved The information of the saved Book entry.
     * @return      The information of the saved Book entry.
     */
    Book save(Book saved);
}
