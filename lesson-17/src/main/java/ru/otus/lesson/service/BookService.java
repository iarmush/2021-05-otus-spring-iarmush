package ru.otus.lesson.service;

import java.util.List;
import ru.otus.lesson.domain.Book;

public interface BookService {

    void create(Book book);

    List<Book> selectAll();

    Book selectByTitle(String title);

    Book selectById(String id);

    void update(Book book);

    void delete(String title);
}
