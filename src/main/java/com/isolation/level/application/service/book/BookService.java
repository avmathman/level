package com.isolation.level.application.service.book;

import com.isolation.level.application.exception.ItemNotFoundException;
import com.isolation.level.application.model.BookModel;

import java.util.List;

/**
 * Provides methods for working with users.
 */
public interface BookService {

    /**
     * Creates new book.
     *
     * @param bookModel - The book to be created in database.
     * @return New book created in database.
     */
    BookModel createBook(BookModel bookModel);

    /**
     * Returns book by its ID if it exists.
     *
     * @param bookId - ID of the book to get.
     * @return The book specified by ID or null if no books found.
     * @throws ItemNotFoundException if book with the given id does not exist in database.
     */
    BookModel getBook(Long bookId);

    /**
     * Returns all books.
     *
     * @return The list of book.
     * @throws ItemNotFoundException if book with the given id does not exist in database.
     */
    List<BookModel> getAllBooks();
}
