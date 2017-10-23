package com.ness.services.service;

import java.util.List;

import com.ness.services.model.BookBO;

/**
 * This interface declares the methods that provides CRUD operations for
 * {@link com.javaadvent.bootrest.book.book} objects.
 * @author Petri Kainulainen
 */
public interface LibraryService {

    /**
     * Creates a new book entry.
     * @param book  The information of the created book entry.
     * @return      The information of the created book entry.
     */
    BookBO create(BookBO book);

    /**
     * Deletes a book entry.
     * @param id    The id of the deleted book entry.
     * @return      THe information of the deleted book entry.
     * @throws Exception 
     * @throws com.javaadvent.bootrest.book.bookNotFoundException if no book entry is found.
     */
    BookBO delete(String id) throws Exception;

    /**
     * Finds all book entries.
     * @return      The information of all book entries.
     */
    List<BookBO> findAll();

    /**
     * Finds a single book entry.
     * @param id    The id of the requested book entry.
     * @return      The information of the requested book entry.
     * @throws Exception 
     * @throws com.javaadvent.bootrest.book.bookNotFoundException if no book entry is found.
     */
    BookBO findById(String id) throws Exception;

    /**
     * Updates the information of a book entry.
     * @param book  The information of the updated book entry.
     * @return      The information of the updated book entry.
     * @throws Exception 
     * @throws com.javaadvent.bootrest.book.bookNotFoundException if no book entry is found.
     */
    BookBO update(BookBO book) throws Exception;
}
