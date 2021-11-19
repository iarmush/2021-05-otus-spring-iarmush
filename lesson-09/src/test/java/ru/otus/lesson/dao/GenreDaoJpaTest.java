package ru.otus.lesson.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.lesson.domain.Genre;

@DataJpaTest
@Import(GenreDaoJpa.class)
class GenreDaoJpaTest {
    public static final String NEW_NAME = "g4";
    public static final String NAME = "g3";

    @Autowired
    private GenreDaoJpa genreDaoJpa;

    @Test
    void testCreate() {
        long result = genreDaoJpa.create(NEW_NAME);
        Assertions.assertEquals(4L, result);
    }

    @Test
    void testSelectByName() {
        Genre result = genreDaoJpa.selectByName(NAME).orElseThrow();
        Assertions.assertNotNull(result);
    }
}
