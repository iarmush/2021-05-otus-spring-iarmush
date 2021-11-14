package ru.otus.lesson.service;

import ru.otus.lesson.domain.Book;

import java.util.List;

public interface BookService {
    void create(Book book);

    List<Book> selectAll();

    Book selectByTitle(String title);

    Book selectById(String id);

    void update(Book book);

    void delete(String title);
}
