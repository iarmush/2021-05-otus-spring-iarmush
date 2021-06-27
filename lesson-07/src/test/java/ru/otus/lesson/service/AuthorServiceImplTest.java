package ru.otus.lesson.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.lesson.dao.AuthorDao;
import ru.otus.lesson.domain.Author;

import static org.mockito.Mockito.*;

class AuthorServiceImplTest {
    private static final String FULL_NAME = "full_name";
    private static final String NAME = "name";

    @Mock
    AuthorDao authorDao;
    @InjectMocks
    AuthorServiceImpl authorServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAuthor() {
        when(authorDao.insert(any())).thenReturn(0L);

        long result = authorServiceImpl.addAuthor(NAME);
        Assertions.assertEquals(0L, result);
    }

    @Test
    void testGetByName() {
        Author author = new Author(0L, FULL_NAME);
        when(authorDao.getByName(anyString())).thenReturn(author);

        Author result = authorServiceImpl.getByName(NAME);
        Assertions.assertEquals(author, result);
    }
}