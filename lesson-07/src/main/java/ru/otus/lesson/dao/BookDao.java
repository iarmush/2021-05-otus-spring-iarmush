package ru.otus.lesson.dao;

import ru.otus.lesson.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    long insert(Book book);

    List<Book> selectAll();

    Optional<Book> selectByTitle(String title);

    void update(Book book);

    void deleteByTitle(String title);
}
