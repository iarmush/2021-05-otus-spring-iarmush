package ru.otus.lesson.service;

import ru.otus.lesson.domain.Comment;

import java.util.List;

public interface CommentService {
    void createComment(Comment comment);

    List<Comment> selectAll();

    Comment selectByText(String text);

    void update(String text, String newText);

    void deleteByText(String text);
}
