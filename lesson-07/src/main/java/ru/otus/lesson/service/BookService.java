package ru.otus.lesson.service;

import ru.otus.lesson.domain.Book;

import java.util.List;

public interface BookService {
    long insert(Book book);

    List<Book> selectAll();

    Book selectByTitle(String title);

    void update(Book book);

    void delete(String title);
}
