package com.isolation.level.infrastructure.repository;

import com.isolation.level.domain.book.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Data repository for books.
 */
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}