package ru.otus.lesson.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.lesson.dao.CommentDao;
import ru.otus.lesson.domain.Comment;

class CommentServiceImplTest {

    public static final String TEXT = "text";
    public static final String NEW_TEXT = "new text";

    @Mock
    CommentDao commentDao;
    @InjectMocks
    CommentServiceImpl commentServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(commentDao);
    }

    @Test
    void testCreateComment() {
        Comment comment = new Comment();
        commentServiceImpl.createComment(comment);

        verify(commentDao).create(comment);
    }

    @Test
    void testGetAll() {
        Comment comment = new Comment();

        when(commentDao.selectAll()).thenReturn(Collections.singletonList(comment));

        List<Comment> result = commentServiceImpl.selectAll();
        Assertions.assertEquals(1, result.size());

        verify(commentDao).selectAll();
    }

    @Test
    void testGetByText() {
        Comment comment = new Comment();

        when(commentDao.selectByText(TEXT)).thenReturn(Optional.of(comment));

        Comment result = commentServiceImpl.selectByText(TEXT);
        Assertions.assertNotNull(result);

        verify(commentDao).selectByText(TEXT);
    }

    @Test
    void testUpdate() {
        Comment comment = new Comment();
        when(commentDao.selectByText(TEXT)).thenReturn(Optional.of(comment));

        commentServiceImpl.update(TEXT, NEW_TEXT);

        verify(commentDao).selectByText(TEXT);
        verify(commentDao).update(comment);
    }

    @Test
    void testDelete() {
        Comment comment = new Comment();
        when(commentDao.selectByText(TEXT)).thenReturn(Optional.of(comment));

        commentServiceImpl.deleteByText(TEXT);

        verify(commentDao).selectByText(TEXT);
        verify(commentDao).remove(comment);
    }
}
