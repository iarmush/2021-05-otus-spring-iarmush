package ru.otus.lesson.service;

import ru.otus.lesson.domain.Comment;

import java.util.List;

public interface CommentService {
    void create(Comment comment);

    List<Comment> selectAll();

    Comment selectByText(String text);

    void update(String text, String newText);

    void delete(String text);
}
