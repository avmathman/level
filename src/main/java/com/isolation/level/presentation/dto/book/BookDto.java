package com.isolation.level.presentation.dto.book;

import lombok.Getter;
import lombok.Setter;

/**
 * The DTO of the book.
 */
@Getter
@Setter
public class BookDto {

    /**
     * The book identifier.
     */
    private Long id;

    /**
     * The book title.
     */
    private String title;

    /**
     * The book author.
     */
    private String author;
}
