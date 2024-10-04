package com.isolation.level.presentation.dto.history;

import lombok.Getter;
import lombok.Setter;

/**
 * The DTO with available fields for creating history.
 */
@Getter
@Setter
public class HistoryCreateDto {
    /**
     * The book title.
     */
    private String title;

    /**
     * The book likes.
     */
    private int likes;
}
