package com.isolation.level.application.service;

import com.isolation.level.application.mapper.BookMapper;
import com.isolation.level.application.model.BookModel;
import com.isolation.level.application.service.book.BookService;
import com.isolation.level.application.service.book.BookServiceImpl;
import com.isolation.level.infrastructure.repository.BookRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.Replace.ANY;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureEmbeddedDatabase(type = POSTGRES, replace = ANY, beanName = "dataSource")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;

    private BookMapper bookMapper;
    private BookService bookService;

    @BeforeAll
    public void setUp() {
        bookMapper = Mappers.getMapper(BookMapper.class);
        bookService = new BookServiceImpl(bookRepository, bookMapper);
    }

    @Test
    public void createBook_passBookEntity_shouldCreateBook() {

        // Assign
        BookModel bookModel = new BookModel();
        bookModel.setTitle("Title");
        bookModel.setAuthor("Author");
        bookModel.setLikes(1);

        // Act
        BookModel created = bookService.createBook(bookModel);

        // Assert
        assertNotNull(created);
        assertEquals(bookModel.getTitle(), created.getTitle());
        assertEquals(bookModel.getAuthor(), created.getAuthor());
        assertEquals(bookModel.getLikes(), created.getLikes());
    }

    @Test
    public void getBook_passBookId_shouldReturnBook() {
        BookModel book = bookService.getBook(1L);

        assertNotNull(book);
        assertEquals("READ_UNCOMMITTED", book.getTitle());
        assertEquals("Read Uncommitted Isolation", book.getAuthor());
        assertEquals(0, book.getLikes());
    }

    @Test
    public void getAllBooks_executeMethod_shouldReturnAllBooks() {
        List<BookModel> allBooks = bookService.getAllBooks();

        assertNotNull(allBooks);
        assertTrue(allBooks.size() > 0, "Size of books should be greater than 0");
        assertEquals("READ_UNCOMMITTED", allBooks.get(0).getTitle());
        assertEquals("Read Uncommitted Isolation", allBooks.get(0).getAuthor());
        assertEquals(0, allBooks.get(0).getLikes());
    }
}
