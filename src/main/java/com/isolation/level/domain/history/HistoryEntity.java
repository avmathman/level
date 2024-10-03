package com.isolation.level.domain.history;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoryEntity {

    /**
     * The book identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "likes")
    private int likes;

    @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime created;
}
