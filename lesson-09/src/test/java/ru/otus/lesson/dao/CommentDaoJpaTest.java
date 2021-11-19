package ru.otus.lesson.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.domain.Comment;

import javax.persistence.EntityManager;
import java.util.List;

@DataJpaTest
@Import(CommentDaoJpa.class)
class CommentDaoJpaTest {
    public static final String TITLE = "b3";
    public static final String NEW_TITLE = "b4";
    private final static String TEXT = "c3";
    private final static String NEW_TEXT = "c4";
    @Autowired
    private CommentDaoJpa commentDaoJpa;
    @Autowired
    private EntityManager entityManager;

    @Test
    void testCreate() {
        Book book = entityManager.createQuery("select b from Book b where b.title = :title", Book.class)
                .setParameter("title", TITLE)
                .getSingleResult();

        Comment comment = new Comment(TEXT, book);

        commentDaoJpa.create(comment);
    }

    @Test
    void testSelectAll() {
        List<Comment> result = commentDaoJpa.selectAll();
        Assertions.assertEquals(3, result.size());
    }

    @Test
    void testSelectByText() {
        Comment result = commentDaoJpa.selectByText(TEXT).orElseThrow();
        Assertions.assertNotNull(result);
    }

    @Test
    void testUpdate() {
        Comment comment = commentDaoJpa.selectByText(TEXT).orElseThrow();
        comment.setText(NEW_TEXT);

        commentDaoJpa.update(comment);

        Comment result = commentDaoJpa.selectByText(NEW_TEXT).orElseThrow();
        Assertions.assertEquals(result.getText(), NEW_TEXT);
    }

    @Test
    void testDeleteByText() {
        Comment comment = commentDaoJpa.selectByText(TEXT).orElseThrow();

        commentDaoJpa.remove(comment);
    }
}
