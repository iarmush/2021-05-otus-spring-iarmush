package ru.otus.lesson.dao;

import java.util.List;
import java.util.Optional;
import ru.otus.lesson.domain.Comment;

public interface CommentDao {

    void create(Comment comment);

    List<Comment> selectAll();

    Optional<Comment> selectByText(String text);

    void update(Comment comment);

    void remove(Comment comment);
}
