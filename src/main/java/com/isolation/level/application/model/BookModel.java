package com.isolation.level.application.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The model of the book.
 */
@Getter
@Setter
public class BookModel {

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

    /**
     * The book description.
     */
    private String description;

    /**
     * The book like count.
     */
    private int likes;
}
