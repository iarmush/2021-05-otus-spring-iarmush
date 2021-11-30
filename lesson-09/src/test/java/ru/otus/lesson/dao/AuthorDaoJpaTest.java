package ru.otus.lesson.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.lesson.domain.Author;

@DataJpaTest
@Import(AuthorDaoJpa.class)
class AuthorDaoJpaTest {

    public static final String FULL_NAME = "a4";
    @Autowired
    private AuthorDaoJpa authorDaoJpa;

    @Test
    void testCreate() {
        long result = authorDaoJpa.create(FULL_NAME);
        Assertions.assertEquals(5, result);
    }

    @Test
    void testSelectByName() {
        authorDaoJpa.create(FULL_NAME);
        Author result = authorDaoJpa.selectByName(FULL_NAME).orElseThrow();
        Assertions.assertEquals(FULL_NAME, result.getFullName());
    }
}
