package ru.otus.lesson.service;

import java.util.List;
import ru.otus.lesson.domain.Book;

public interface BookService {

    long insert(Book book);

    List<Book> selectAll();

    Book selectByTitle(String title);

    void update(Book book);

    void delete(String title);
}
