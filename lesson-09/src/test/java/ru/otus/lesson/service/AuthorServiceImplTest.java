package ru.otus.lesson.service;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.lesson.dao.AuthorDao;
import ru.otus.lesson.domain.Author;

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
        when(authorDao.create(any())).thenReturn(0L);

        long result = authorServiceImpl.create(NAME);
        Assertions.assertEquals(0L, result);
    }

    @Test
    void testGetByName() {
        Author author = new Author(FULL_NAME);
        when(authorDao.selectByName(anyString())).thenReturn(Optional.of(author));

        Author result = authorServiceImpl.selectByName(NAME).orElseThrow();
        Assertions.assertEquals(author, result);
    }
}