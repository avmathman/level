package com.isolation.level.presentation.dto.book;

import lombok.Getter;
import lombok.Setter;

/**
 * The DTO with available fields for creating book.
 */
@Getter
@Setter
public class BookCreateDto {

    /**
     * The book title.
     */
    private String title;

    /**
     * The book author.
     */
    private String author;
}
