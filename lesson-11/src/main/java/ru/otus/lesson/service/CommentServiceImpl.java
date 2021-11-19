package ru.otus.lesson.service;

import org.springframework.stereotype.Service;
import ru.otus.lesson.dao.CommentDao;
import ru.otus.lesson.domain.Comment;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private static final String COMMENT_DOESNT_EXIST = "Comment doesn't exist";

    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public void create(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public List<Comment> selectAll() {
        return commentDao.selectAll();
    }

    @Override
    public Comment selectByText(String text) {
        Optional<Comment> comment = commentDao.findByText(text);

        return comment.orElseThrow(() -> {
            throw new IllegalArgumentException(COMMENT_DOESNT_EXIST);
        });
    }

    @Override
    public void update(String text, String newText) {
        Comment comment = commentDao.findByText(text).orElseThrow(() -> {
            throw new IllegalArgumentException(COMMENT_DOESNT_EXIST);
        });

        comment.setText(newText);

        commentDao.save(comment);
    }

    @Override
    public void delete(String text) {
        commentDao.findByText(text);

        commentDao.deleteByText(text);
    }
}
