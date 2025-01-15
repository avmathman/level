package com.isolation.level.infrastructure.repository;

import com.isolation.level.domain.book.BookEntity;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.junit4.SpringRunner;


import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.Replace.ANY;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureEmbeddedDatabase(type = POSTGRES, replace = ANY, beanName = "dataSource")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void findByTitle_passesTitle_returnFoundBook() {
        BookEntity book = bookRepository
                .findByTitle("READ_COMMITTED")
                .orElse(null);

        assertNotNull(book);
        assertEquals("READ_COMMITTED", book.getTitle());
        assertEquals("Read Committed Isolation", book.getAuthor());
        assertEquals(0, book.getLikes());
    }

    @Test
    public void findByTitle_passesInvalidTitle_returnNull() {
        BookEntity book = bookRepository
                .findByTitle("INVALID_TITLE")
                .orElse(null);

        assertNull(book);
    }
}
