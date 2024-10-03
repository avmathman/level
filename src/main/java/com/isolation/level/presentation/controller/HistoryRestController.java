package com.isolation.level.presentation.controller;

import com.isolation.level.application.service.book.BookService;
import com.isolation.level.application.service.history.HistoryService;
import com.isolation.level.presentation.common.ApplicatonApiLocations;
import com.isolation.level.presentation.dto.book.BookCreateDto;
import com.isolation.level.presentation.dto.book.BookDto;
import com.isolation.level.presentation.dto.history.HistoryCreateDto;
import com.isolation.level.presentation.dto.history.HistoryDto;
import com.isolation.level.presentation.mapper.history.HistoryCreateMapper;
import com.isolation.level.presentation.mapper.history.HistoryReadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        path = "${application.api.prefix:}" + ApplicatonApiLocations.HISTORY,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class HistoryRestController {

    private final HistoryService historyService;
    private final HistoryReadMapper historyReadMapper;
    private final HistoryCreateMapper historyCreateMapper;

    /**
     * Initializes a new {@link BookRestController} instance.
     *
     * @param historyService - {@link BookService} instance.
     * @param historyReadMapper - {@link HistoryReadMapper} instance.
     * @param historyCreateMapper - {@link HistoryCreateMapper} instance.
     */
    @Autowired
    public HistoryRestController(
            HistoryService historyService,
            HistoryReadMapper historyReadMapper,
            HistoryCreateMapper historyCreateMapper
    ) {
        this.historyService = historyService;
        this.historyReadMapper = historyReadMapper;
        this.historyCreateMapper = historyCreateMapper;
    }

    /**
     * REST API method to create a new book.
     *
     * @param historyCreateDto - The book {@link BookCreateDto} to create.
     * @return The created book instance.
     */
    @RequestMapping(
            path = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HistoryDto> createBook(
            @RequestBody HistoryCreateDto historyCreateDto
    ) {
        final HistoryDto bookDto = historyReadMapper.modelToDto(historyService.createHistory(historyCreateMapper.dtoToModel(historyCreateDto)));
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
    public ResponseEntity<HistoryDto> getBook(
            @PathVariable Long id
    ) {
        final HistoryDto historyDto = this.historyReadMapper.modelToDto(historyService.getHistory(id));
        return new ResponseEntity<>(historyDto, HttpStatus.OK);
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
    public ResponseEntity<List<HistoryDto>> getAllBooks() {
        List<HistoryDto> historyDtos = this.historyReadMapper.modelsToDtos(historyService.getAllHistory());
        return new ResponseEntity<>(historyDtos, HttpStatus.OK);
    }
}
