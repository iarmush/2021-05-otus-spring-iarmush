package ru.otus.lesson.dao;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.lesson.domain.Author;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.domain.Genre;

@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    public static final String NEW_TITLE = "b4";
    public static final String TITLE = "b3";
    public static final String FULL_NAME = "a3";
    public static final String NAME = "g3";
    @Autowired
    BookDaoJdbc bookDaoJdbc;

    @Test
    void testInsert() {
        Author author = new Author(1, TITLE);
        Genre genre = new Genre(1, NAME);
        Book book = new Book(NEW_TITLE, author, genre);

        long result = bookDaoJdbc.insert(book);
        Assertions.assertTrue(result > 0);
    }

    @Test
    void testSelectAll() {
        List<Book> result = bookDaoJdbc.selectAll();
        Assertions.assertEquals(3, result.size());
    }

    @Test
    void testSelectByTitle() {
        Optional<Book> result = bookDaoJdbc.selectByTitle(TITLE);
        Assertions.assertNotNull(result.get());
    }

    @Test
    void testUpdate() {
        Author author = new Author(1, FULL_NAME);
        Genre genre = new Genre(1, NAME);
        Book book = new Book(TITLE, author, genre);

        bookDaoJdbc.update(book);
    }

    @Test
    void testDeleteByTitle() {
        bookDaoJdbc.deleteByTitle(TITLE);
    }
}
