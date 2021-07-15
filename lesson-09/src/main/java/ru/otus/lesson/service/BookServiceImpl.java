package ru.otus.lesson.service;

import org.springframework.stereotype.Service;
import ru.otus.lesson.dao.BookDao;
import ru.otus.lesson.domain.Book;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private static final String BOOK_DOESNT_EXIST = "Book doesn't exist";

    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void create(Book book) {
        bookDao.create(book);
    }

    @Override
    public List<Book> selectAll() {
        return bookDao.selectAll();
    }

    @Override
    public Book selectByTitle(String title) {
        Optional<Book> book = bookDao.selectByTitle(title);
        return book.orElseThrow(() -> {
            throw new IllegalArgumentException(BOOK_DOESNT_EXIST);
        });
    }

    @Override
    public void update(Book book) {
        bookDao.update(book);
    }

    @Override
    public void deleteByTitle(String title) {
        Book book = bookDao.selectByTitle(title).orElseThrow();

        bookDao.remove(book);
    }
}
