package ru.otus.lesson.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.lesson.domain.Author;

@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    public static final String FULL_NAME = "a4";
    @Autowired
    AuthorDaoJdbc authorDaoJdbc;

    @Test
    void testInsert() {
        long result = authorDaoJdbc.insert(new Author(FULL_NAME));
        Assertions.assertEquals(5, result);
    }

    @Test
    void testGetByName() {
        authorDaoJdbc.insert(new Author(FULL_NAME));
        Author result = authorDaoJdbc.getByName(FULL_NAME);
        Assertions.assertEquals(FULL_NAME, result.getFullName());
    }
}
