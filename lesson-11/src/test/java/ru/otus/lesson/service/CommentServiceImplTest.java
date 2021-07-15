package ru.otus.lesson.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.otus.lesson.dao.CommentDao;
import ru.otus.lesson.domain.Comment;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

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
        commentServiceImpl.create(comment);

        verify(commentDao).save(comment);
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

        when(commentDao.findByText(TEXT)).thenReturn(Optional.of(comment));

        Comment result = commentServiceImpl.selectByText(TEXT);
        Assertions.assertNotNull(result);

        verify(commentDao).findByText(TEXT);
    }

    @Test
    void testUpdate() {
        Comment comment = new Comment();
        when(commentDao.findByText(TEXT)).thenReturn(Optional.of(comment));

        commentServiceImpl.update(TEXT, NEW_TEXT);

        verify(commentDao).findByText(TEXT);
        verify(commentDao).save(comment);
    }

    @Test
    void testDelete() {
        when(commentDao.findByText(TEXT)).thenReturn(Optional.of(new Comment()));

        commentServiceImpl.delete(TEXT);

        verify(commentDao).findByText(TEXT);
        verify(commentDao).deleteByText(TEXT);
    }
}
