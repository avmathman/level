package com.isolation.level.application.service.book;

import com.isolation.level.application.model.BookModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Override
    public BookModel createBook(BookModel bookModel) {
        return null;
    }

    @Override
    public BookModel getBook(Long bookId) {
        return null;
    }

    @Override
    public List<BookModel> getAllBooks() {
        return List.of();
    }
}
