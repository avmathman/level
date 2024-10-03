package com.isolation.level.presentation.dto.history;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class HistoryDto {

    /**
     * The book identifier.
     */
    private Long id;

    private String title;

    private int likes;

    private String status;

    private LocalDateTime created;
}
