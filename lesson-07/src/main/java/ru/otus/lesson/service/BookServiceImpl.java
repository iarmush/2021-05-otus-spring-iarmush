package ru.otus.lesson.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.otus.lesson.dao.BookDao;
import ru.otus.lesson.domain.Book;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOK_DOESNT_EXIST = "Book doesn't exist";

    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public long insert(Book book) {
        return bookDao.insert(book);
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
    public void delete(String title) {
        Optional<Book> book = bookDao.selectByTitle(title);
        book.orElseThrow(() -> {
            throw new IllegalArgumentException(BOOK_DOESNT_EXIST);
        });

        bookDao.deleteByTitle(title);
    }
}
