package com.isolation.level.domain.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {

    /**
     * The book identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The book title.
     */
    @Column(name = "title")
    private String title;

    /**
     * The book author.
     */
    @Column(name = "author")
    private String author;

    /**
     * The book like count.
     */
    @Column(name = "likes")
    private int likes;
}
