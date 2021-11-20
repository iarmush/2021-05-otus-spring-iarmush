package ru.otus.lesson.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.otus.lesson.domain.Book;
import ru.otus.lesson.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOK_DOESNT_EXIST = "Book doesn't exist";

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void create(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> selectAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book selectByTitle(String title) {
        Optional<Book> book = bookRepository.findByTitle(title);
        return book.orElseThrow(() -> {
            throw new IllegalArgumentException(BOOK_DOESNT_EXIST);
        });
    }

    @Override
    public Book selectById(String id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseThrow(() -> {
            throw new IllegalArgumentException(BOOK_DOESNT_EXIST);
        });
    }

    @Override
    public void update(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(String title) {
        bookRepository.deleteByTitle(title);
    }
}
