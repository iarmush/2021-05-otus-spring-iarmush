package ru.otus.lesson.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.lesson.dao.GenreDao;
import ru.otus.lesson.domain.Genre;

import static org.mockito.Mockito.*;

class GenreServiceImplTest {
    private static final String NAME = "name";

    @Mock
    GenreDao genreDao;
    @InjectMocks
    GenreServiceImpl genreServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddGenre() {
        when(genreDao.insert(any())).thenReturn(0L);

        long result = genreServiceImpl.addGenre(NAME);
        Assertions.assertEquals(0L, result);
    }

    @Test
    void testGetByName() {
        Genre genre = new Genre(0L, NAME);

        when(genreDao.getByName(anyString())).thenReturn(genre);

        Genre result = genreServiceImpl.getByName(NAME);
        Assertions.assertEquals(genre, result);
    }
}
