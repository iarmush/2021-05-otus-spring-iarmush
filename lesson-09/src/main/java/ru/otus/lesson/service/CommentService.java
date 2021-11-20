package ru.otus.lesson.service;

import java.util.List;
import ru.otus.lesson.domain.Comment;

public interface CommentService {

    void createComment(Comment comment);

    List<Comment> selectAll();

    Comment selectByText(String text);

    void update(String text, String newText);

    void deleteByText(String text);
}
