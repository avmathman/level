package com.isolation.level.presentation.controller;

import com.isolation.level.application.service.book.BookService;
import com.isolation.level.presentation.common.ApplicatonApiLocations;
import com.isolation.level.presentation.dto.book.BookCreateDto;
import com.isolation.level.presentation.dto.book.BookDto;
import com.isolation.level.presentation.mapper.book.BookCreateMapper;
import com.isolation.level.presentation.mapper.book.BookReadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        path = "${application.api.prefix:}" + ApplicatonApiLocations.BOOK,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class BookRestController {

    private final BookService bookService;
    private final BookReadMapper bookReadMapper;
    private final BookCreateMapper bookCreateMapper;

    /**
     * Initializes a new {@link BookRestController} instance.
     *
     * @param bookService - {@link BookService} instance.
     * @param bookReadMapper - {@link BookReadMapper} instance.
     * @param bookCreateMapper - {@link BookCreateMapper} instance.
     */
    @Autowired
    public BookRestController(
            BookService bookService,
            BookReadMapper bookReadMapper,
            BookCreateMapper bookCreateMapper
    ) {
        this.bookService = bookService;
        this.bookReadMapper = bookReadMapper;
        this.bookCreateMapper = bookCreateMapper;
    }

    /**
     * REST API method to create a new book.
     *
     * @param bookCreateDto - The book {@link BookCreateDto} to create.
     * @return The created book instance.
     */
    @RequestMapping(
            path = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookDto> createBook(
            @RequestBody BookCreateDto bookCreateDto
    ) {
        final BookDto bookDto = bookReadMapper.modelToDto(bookService.createBook(bookCreateMapper.dtoToModel(bookCreateDto)));
        return new ResponseEntity<>(bookDto, HttpStatus.CREATED);
    }

    /**
     * REST API method to retrieve book by its ID.
     *
     * @param id - The book ID.
     * @return the book instance.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookDto> getBook(
            @PathVariable Long id
    ) {
        final BookDto bookDto = this.bookReadMapper.modelToDto(bookService.getBook(id));
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    /**
     * REST API method to retrieve list of books.
     *
     * @return The list of book instances.
     */
    @RequestMapping(
            path = "/all",
            method = RequestMethod.GET
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books = this.bookReadMapper.modelsToDtos(this.bookService.getAllBooks());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
