package com.isolation.level.application.service.book;

import com.isolation.level.application.mapper.BookMapper;
import com.isolation.level.application.model.BookModel;
import com.isolation.level.infrastructure.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    public BookServiceImpl(
            BookRepository repository,
            BookMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public BookModel createBook(BookModel bookModel) {
        return mapper.entityToModel(repository.save(mapper.modelToEntity(bookModel)));
    }

    @Override
    public BookModel getBook(Long bookId) {
        return mapper.entityToModel(repository.getReferenceById(bookId));
    }

    @Override
    public List<BookModel> getAllBooks() {
        return mapper.entitiesToModels(repository.findAll());
    }
}
