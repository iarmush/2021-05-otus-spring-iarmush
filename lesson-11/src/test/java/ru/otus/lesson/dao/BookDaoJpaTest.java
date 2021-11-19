package ru.otus.lesson.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.lesson.domain.Book;

import java.util.List;

@DataJpaTest
class BookDaoJpaTest {
    public static final String TITLE = "b3";
    public static final String NEW_TITLE = "b4";
    public static final String FULL_NAME = "a3";
    public static final String NEW_FULL_NAME = "a4";
    public static final String NAME = "g3";
    public static final String NEW_NAME = "g4";

    @Autowired
    private BookDao bookDao;

    @Test
    void testSelectAll() {
        List<Book> result = bookDao.findAll();
        Assertions.assertEquals(3, result.size());
    }

    @Test
    void testSelectByTitle() {
        Book result = bookDao.findByTitle(TITLE).orElseThrow();
        Assertions.assertNotNull(result);
    }


}
