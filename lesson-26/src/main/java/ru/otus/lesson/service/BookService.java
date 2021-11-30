package ru.otus.lesson.service;

import java.util.List;
import ru.otus.lesson.domain.jpa.Book;

public interface BookService {

    List<Book> getAll();

    Book saveBook(Book book);
}
