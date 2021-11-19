package ru.otus.lesson.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.lesson.domain.Author;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.domain.Genre;

import java.util.List;

@DataJpaTest
@Import(BookDaoJpa.class)
class BookDaoJpaTest {
    public static final String TITLE = "b3";
    public static final String NEW_TITLE = "b4";
    public static final String FULL_NAME = "a3";
    public static final String NEW_FULL_NAME = "a4";
    public static final String NAME = "g3";
    public static final String NEW_NAME = "g4";

    @Autowired
    private BookDaoJpa bookDaoJpa;

    @Test
    void testCreate() {
        Author author = new Author(NEW_FULL_NAME);
        Genre genre = new Genre(NEW_NAME);
        Book book = new Book(NEW_TITLE, author, genre);

        bookDaoJpa.create(book);

        Book result = bookDaoJpa.selectByTitle(NEW_TITLE).orElseThrow();
        Assertions.assertEquals(result.getTitle(), NEW_TITLE);
    }

    @Test
    void testSelectAll() {
        List<Book> result = bookDaoJpa.selectAll();
        Assertions.assertEquals(3, result.size());
    }

    @Test
    void testSelectByTitle() {
        Book result = bookDaoJpa.selectByTitle(TITLE).orElseThrow();
        Assertions.assertNotNull(result);
    }

    @Test
    void testUpdate() {
        Author author = new Author(NEW_FULL_NAME);
        Genre genre = new Genre(NEW_NAME);

        Book book = bookDaoJpa.selectByTitle(TITLE).orElseThrow();
        book.setAuthor(author);
        book.setGenre(genre);

        bookDaoJpa.update(book);

        Book result = bookDaoJpa.selectByTitle(TITLE).orElseThrow();
        Assertions.assertEquals(result.getAuthor().getFullName(), author.getFullName());
        Assertions.assertEquals(result.getGenre().getName(), genre.getName());
    }

    @Test
    void testDelete() {
        Book book = bookDaoJpa.selectByTitle(TITLE).orElseThrow();

        bookDaoJpa.remove(book);
    }
}
