package ru.otus.lesson.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.otus.lesson.dao.CommentDao;
import ru.otus.lesson.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService {

    private static final String COMMENT_DOESNT_EXIST = "Comment doesn't exist";

    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public void createComment(Comment comment) {
        commentDao.create(comment);
    }

    @Override
    public List<Comment> selectAll() {
        return commentDao.selectAll();
    }

    @Override
    public Comment selectByText(String text) {
        Optional<Comment> comment = commentDao.selectByText(text);

        return comment.orElseThrow(() -> {
            throw new IllegalArgumentException(COMMENT_DOESNT_EXIST);
        });
    }

    @Override
    public void update(String text, String newText) {
        Comment comment = commentDao.selectByText(text).orElseThrow(() -> {
            throw new IllegalArgumentException(COMMENT_DOESNT_EXIST);
        });

        comment.setText(newText);

        commentDao.update(comment);
    }

    @Override
    public void deleteByText(String text) {
        Comment comment = commentDao.selectByText(text).orElseThrow();

        commentDao.remove(comment);
    }
}
