package ru.otus.lesson.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.lesson.domain.Genre;

@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {
    public static final String NEW_NAME = "g4";
    public static final String NAME = "g3";

    @Autowired
    GenreDaoJdbc genreDaoJdbc;

    @Test
    void testInsert() {
        Genre genre = new Genre(NEW_NAME);
        long result = genreDaoJdbc.insert(genre);
        Assertions.assertEquals(4L, result);
    }

    @Test
    void testGetByName() {
        Genre result = genreDaoJdbc.getByName(NAME);
        Assertions.assertNotNull(result);
    }
}
