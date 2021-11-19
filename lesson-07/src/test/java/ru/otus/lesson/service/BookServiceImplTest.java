package ru.otus.lesson.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.lesson.dao.BookDao;
import ru.otus.lesson.domain.Author;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.domain.Genre;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

class BookServiceImplTest {
    private static final String TITLE = "title";
    private static final String FULL_NAME = "full_name";
    private static final String NAME = "name";

    @Mock
    BookDao bookDao;
    @InjectMocks
    BookServiceImpl bookServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        Author author = new Author(0L, FULL_NAME);
        Genre genre = new Genre(0L, NAME);
        Book book = new Book(0L, TITLE, author, genre);

        when(bookDao.insert(book)).thenReturn(0L);

        long result = bookServiceImpl.insert(book);
        Assertions.assertEquals(0L, result);
    }

    @Test
    void testSelectAll() {
        Author author = new Author(0L, FULL_NAME);
        Genre genre = new Genre(0L, NAME);
        Book book = new Book(0L, TITLE, author, genre);
        List<Book> bookList = Collections.singletonList(book);

        when(bookDao.selectAll()).thenReturn(bookList);

        List<Book> result = bookServiceImpl.selectAll();
        Assertions.assertEquals(bookList, result);
    }

    @Test
    void testSelectByTitle() {
        Author author = new Author(0L, FULL_NAME);
        Genre genre = new Genre(0L, NAME);
        Book book = new Book(0L, TITLE, author, genre);

        when(bookDao.selectByTitle(anyString())).thenReturn(Optional.of(book));

        Book result = bookServiceImpl.selectByTitle(TITLE);
        Assertions.assertEquals(book, result);
    }

    @Test
    void testUpdate() {
        Author author = new Author(0L, FULL_NAME);
        Genre genre = new Genre(0L, NAME);
        Book book = new Book(0L, TITLE, author, genre);

        bookServiceImpl.update(book);
    }

    @Test
    void testDelete() {
        Author author = new Author(0L, FULL_NAME);
        Genre genre = new Genre(0L, NAME);
        Book book = new Book(0L, TITLE, author, genre);

        when(bookDao.selectByTitle(TITLE)).thenReturn(Optional.of(book));

        bookServiceImpl.delete(TITLE);
    }
}
