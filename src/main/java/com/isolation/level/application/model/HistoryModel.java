package com.isolation.level.application.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * The model of the history.
 */
@Getter
@Setter
public class HistoryModel {

    /**
     * The history identifier.
     */
    private Long id;

    /**
     * The book title.
     */
    private String title;

    /**
     * The history likes.
     */
    private int likes;

    /**
     * The history status.
     */
    private String status;

    /**
     * The history created.
     */
    private LocalDateTime created;
}
