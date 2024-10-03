package com.isolation.level.application.service.history;

import com.isolation.level.application.exception.ItemNotFoundException;
import com.isolation.level.application.model.HistoryModel;

import java.util.List;

public interface HistoryService {

    /**
     * Creates new book with isolation level of READ_COMMITED.
     *
     * @param historyModel - The book to be created in database.
     * @return New book created in database.
     */
    HistoryModel createHistoryReadCommitted(HistoryModel historyModel);

    /**
     * Creates new book.
     *
     * @param historyModel - The book to be created in database with isolation level of REPEATABLE_READ.
     * @return New book created in database.
     */
    HistoryModel createHistoryRepeatableRead(HistoryModel historyModel);

    /**
     * Creates new book.
     *
     * @param historyModel - The book to be created in database with isolation level of SERIALIZABLE.
     * @return New book created in database.
     */
    HistoryModel createHistorySerializable(HistoryModel historyModel);

    /**
     * Returns book by its ID if it exists.
     *
     * @param historyId - ID of the book to get.
     * @return The book specified by ID or null if no books found.
     * @throws ItemNotFoundException if book with the given id does not exist in database.
     */
    HistoryModel getHistory(Long historyId);

    /**
     * Returns all books.
     *
     * @return The list of book.
     * @throws ItemNotFoundException if book with the given id does not exist in database.
     */
    List<HistoryModel> getAllHistory();

    /**
     * Deletes all history.
     */
    void deleteAllHistory();
}
