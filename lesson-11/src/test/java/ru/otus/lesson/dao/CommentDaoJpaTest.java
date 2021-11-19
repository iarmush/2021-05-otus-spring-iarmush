package ru.otus.lesson.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.lesson.domain.Comment;

import java.util.List;

@DataJpaTest
class CommentDaoJpaTest {
    public static final String TITLE = "b3";
    public static final String NEW_TITLE = "b4";
    private final static String TEXT = "c3";
    private final static String NEW_TEXT = "c4";

    @Autowired
    private CommentDao commentDao;

    @Test
    void testSelectAll() {
        List<Comment> result = commentDao.selectAll();
        Assertions.assertEquals(3, result.size());
    }

    @Test
    void testSelectByText() {
        Comment result = commentDao.findByText(TEXT).orElseThrow();
        Assertions.assertNotNull(result);
    }
}
