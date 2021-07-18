package ru.otus.lesson.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.lesson.dao.AuthorDao;
import ru.otus.lesson.domain.Author;

import java.util.Optional;

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
        authorServiceImpl.create(NAME);
    }

    @Test
    void testGetByName() {
        Author author = new Author(FULL_NAME);
        when(authorDao.findByFullName(anyString())).thenReturn(Optional.of(author));

        Author result = authorServiceImpl.selectByFullName(NAME).orElseThrow();
        Assertions.assertEquals(author, result);
    }
}
